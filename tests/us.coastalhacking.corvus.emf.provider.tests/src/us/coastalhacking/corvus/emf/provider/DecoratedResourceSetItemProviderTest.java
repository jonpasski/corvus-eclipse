package us.coastalhacking.corvus.emf.provider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.DragAndDropCommand;
import org.eclipse.emf.edit.command.DragAndDropFeedback;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import us.coastalhacking.corvus.emf.GetMaybeAddCommand;
import us.coastalhacking.corvus.emf.SaveCommand;
import us.coastalhacking.corvus.test.util.AbstractProjectTest;
import us.coastalhacking.corvus.test.util.TestId;
import us.coastalhacking.corvus.test.util.TestIds;
import us.coastalhacking.corvus.test.util.TestRoot;
import us.coastalhacking.corvus.test.util.UtilFactory;
import us.coastalhacking.corvus.test.util.provider.UtilItemProviderAdapterFactory;

class DecoratedResourceSetItemProviderTest extends AbstractProjectTest {

	public DecoratedResourceSetItemProviderTest() throws Exception {
		super();
	}

	CommandStack commandStack;
	ResourceSet rs;
	ComposedAdapterFactory adapterFactory;
	EditingDomain editingDomain;
	Map<String, Object> options;
	List<URI> uris;
	URI uri;

	// Bypass the registry lookups
	Resource resource;
	TestRoot root = UtilFactory.eINSTANCE.createTestRoot();
	DecoratedResourceSetItemProvider provider;

	@BeforeEach
	void subBeforeEach() {
		commandStack = new BasicCommandStack();
		rs = new ResourceSetImpl();
		adapterFactory = new ComposedAdapterFactory();
		adapterFactory.addAdapterFactory(new DecoratedResourceItemProviderAdapterFactory());
		editingDomain = new AdapterFactoryEditingDomain(adapterFactory, commandStack, rs);
		options = Collections.emptyMap();
		uris = new ArrayList<>();

		uri = URI.createPlatformResourceURI(project.getFile("default.xmi").getFullPath().toPortableString(), true);
		uris.add(uri);
		// Bypass the registry lookups
		resource = new XMIResourceImpl();
		resource.setURI(uri);
		resource.setTrackingModification(true);
		rs.getResources().add(resource);

		resource.getContents().add(root);
		assertTrue(resource.isModified());
		provider = new DecoratedResourceSetItemProvider(new AdapterFactoryImpl());
	}

	@Test
	void shouldCreateSaveCommandAndExecute() {
		// Prep
		CommandParameter commandParameter = new CommandParameter(editingDomain.getResourceSet(), options, uris);
		// Execute
		Command command = provider.createCommand(null, editingDomain, SaveCommand.class, commandParameter);
		command.execute();

		// Verify
		assertTrue(command instanceof SaveCommand);
		assertFalse(command.canUndo());
		assertTrue(command.canExecute());
		assertFalse(resource.isModified());
		assertTrue(command.getAffectedObjects().contains(uri));

		// While we're here
		command.dispose();
		assertTrue(command.getAffectedObjects().isEmpty());
	}

	@Test
	void shouldCreateSaveCommandNotExecute() {
		// Prep
		CommandParameter commandParameter = new CommandParameter(editingDomain.getResourceSet(), options,
				Collections.emptyList());

		// Execute
		Command command = provider.createCommand(null, editingDomain, SaveCommand.class, commandParameter);
		assertFalse(command.canExecute());
	}

	@Test
	void shouldCreateDnDForUriMap() {
		// Prep
		float location = -1.0F; // not used
		int operations = DragAndDropFeedback.DROP_NONE; // not used
		int operation = DragAndDropFeedback.DROP_NONE; // not used
		Map<URI, URI> uris = new HashMap<>();
		URI leftFoo = URI.createURI("testa:foo");
		URI rightFoo = URI.createURI("foo");
		uris.put(leftFoo, rightFoo);
		URI leftBar = URI.createURI("testa:bar");
		URI rightBar = URI.createURI("bar");
		uris.put(leftBar, rightBar);
		assertNotEquals(editingDomain.getResourceSet().getURIConverter().normalize(leftFoo), rightFoo);
		assertNotEquals(editingDomain.getResourceSet().getURIConverter().normalize(leftBar), rightBar);

		// Execute and verify
		Command actual = DragAndDropCommand.create(editingDomain, editingDomain.getResourceSet(), location, operations,
				operation, uris.entrySet());
		assertTrue(actual.canExecute());
		editingDomain.getCommandStack().execute(actual);

		assertEquals(editingDomain.getResourceSet().getURIConverter().normalize(leftFoo), rightFoo);
		assertEquals(editingDomain.getResourceSet().getURIConverter().normalize(leftBar), rightBar);
		assertTrue(actual.getAffectedObjects().size() == 2);
		assertTrue(actual.getAffectedObjects().containsAll(uris.keySet()));

		// Undo and verify
		assertTrue(actual.canUndo());
		actual.undo();
		assertNotEquals(editingDomain.getResourceSet().getURIConverter().normalize(leftFoo), rightFoo);
		assertNotEquals(editingDomain.getResourceSet().getURIConverter().normalize(leftBar), rightBar);
		assertTrue(actual.getAffectedObjects().isEmpty());

		// Redo and verify
		actual.redo();
		assertEquals(editingDomain.getResourceSet().getURIConverter().normalize(leftFoo), rightFoo);
		assertEquals(editingDomain.getResourceSet().getURIConverter().normalize(leftBar), rightBar);
		assertTrue(actual.getAffectedObjects().size() == 2);
		assertTrue(actual.getAffectedObjects().containsAll(uris.keySet()));
	}

	EObject populateRootReturnExpected(TestRoot root, String key, String id) {
		TestId expectedObject = UtilFactory.eINSTANCE.createTestId();
		expectedObject.setKey(key);
		TestId previouslyUnexistingObject = UtilFactory.eINSTANCE.createTestId();
		previouslyUnexistingObject.setIntrinsicId(id);
		TestIds testIds = UtilFactory.eINSTANCE.createTestIds();
		testIds.getTestIds().add(expectedObject);
		testIds.getTestIds().add(previouslyUnexistingObject);
		root.getTestIds().add(testIds);
		return expectedObject;
	}

	@Test
	void shouldGetMaybeAddCommandReturnExisting() throws Exception {
		// Prep
		//
		// Populate real root
		final String existingKey = "expectedKey";
		final String existsNotSearchedId = EcoreUtil.generateUUID();
		final EObject expectedObject = populateRootReturnExpected(root, existingKey, existsNotSearchedId);
		final TestIds expectedTestIds = root.getTestIds().get(0);
		// Should exist in resource but not search results
		assertEquals(expectedObject, expectedTestIds.getTestIds().get(0));
		adapterFactory.addAdapterFactory(new UtilItemProviderAdapterFactory());

		final TestRoot searchRoot = UtilFactory.eINSTANCE.createTestRoot();
		final String addedId = EcoreUtil.generateUUID();
		// ignore return value, not needed here
		populateRootReturnExpected(searchRoot, existingKey, addedId);
		final TestId addedObject = searchRoot.getTestIds().get(0).getTestIds().get(1);
		final Command command = GetMaybeAddCommand.createCommand(editingDomain, uri, searchRoot);

		// Execute
		command.execute();

		// Verify
		// root, testids, expected test id, unexpected test id
		assertTrue(command.getResult().size() == 4);
		@SuppressWarnings("unchecked")
		List<EObject> realResults = (List<EObject>) command.getResult();
		assertTrue(EcoreUtil.equals(root, realResults.get(0)));
		assertTrue(EcoreUtil.equals(expectedTestIds, realResults.get(1)));
		assertTrue(EcoreUtil.equals(expectedObject, realResults.get(2)));
		assertEquals(EcoreUtil.getID(addedObject), EcoreUtil.getID(realResults.get(3)));
	}

}

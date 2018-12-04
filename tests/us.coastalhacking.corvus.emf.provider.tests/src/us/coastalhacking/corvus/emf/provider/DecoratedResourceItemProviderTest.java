package us.coastalhacking.corvus.emf.provider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import us.coastalhacking.corvus.emf.GetCommand;
import us.coastalhacking.corvus.test.util.AbstractProjectTest;
import us.coastalhacking.corvus.test.util.TestId;
import us.coastalhacking.corvus.test.util.TestIds;
import us.coastalhacking.corvus.test.util.TestRoot;
import us.coastalhacking.corvus.test.util.UtilFactory;

class DecoratedResourceItemProviderTest extends AbstractProjectTest {

	public DecoratedResourceItemProviderTest() throws Exception {
		super();
	}

	CommandStack commandStack;
	ResourceSet rs;
	ComposedAdapterFactory adapterFactory;
	EditingDomain editingDomain;
	List<String> fragments;
	URI physical;
	URI logical;

	// Bypass the registry lookups
	Resource resource;
	TestRoot root;
	TestIds testIds;
	TestId testId1;
	TestId testId2;
	
	CommandParameter commandParameter;

	@BeforeEach
	void subBeforeEach() {

		commandStack = new BasicCommandStack();
		rs = new ResourceSetImpl();
		adapterFactory = new ComposedAdapterFactory();
		adapterFactory.addAdapterFactory(new DecoratedResourceItemProviderAdapterFactory());
		editingDomain = new AdapterFactoryEditingDomain(adapterFactory, commandStack, rs);

		logical = URI.createURI("test:" + getClass().getName());
		physical = URI.createPlatformResourceURI(project.getFile("default.xmi").getFullPath().toPortableString(), true);
		rs.getURIConverter().getURIMap().put(logical, physical);

		// Bypass the registry lookups
		resource = new XMIResourceImpl();
		resource.setURI(logical);
		rs.getResources().add(resource);

		// Populate
		testId1 = UtilFactory.eINSTANCE.createTestId();
		testId1.setKey("testId1");
		testId2 = UtilFactory.eINSTANCE.createTestId();
		testId2.setIntrinsicId(EcoreUtil.generateUUID());
		testIds = UtilFactory.eINSTANCE.createTestIds();
		testIds.getTestIds().add(testId1);
		testIds.getTestIds().add(testId2);
		root = UtilFactory.eINSTANCE.createTestRoot();
		root.getTestIds().add(testIds);
		resource.getContents().add(root);

		fragments = new ArrayList<>();
		fragments.add(resource.getURIFragment(testId1));
		fragments.add(resource.getURIFragment(testId2));

		commandParameter = new CommandParameter(resource, null, fragments);
	}

	@Test
	void shouldGetCommandAndExecute() throws Exception {
		// Prep
		Command command = editingDomain.createCommand(GetCommand.class, commandParameter);
		assertTrue(command.canExecute());

		// Execute
		command.execute();

		// Verify
		final Collection<?> result = command.getResult();
		assertTrue(result.size() == 2);
		final Iterator<?> iter = result.iterator();
		assertEquals(testId1, (EObject)iter.next());
		assertEquals(testId2, (EObject)iter.next());
	}
	
	@Test
	void shouldGetCommandAndNotExecuteEmptyList() throws Exception {
		// Prep
		commandParameter = new CommandParameter(logical, null, Collections.emptyList());
		Command command = editingDomain.createCommand(GetCommand.class, commandParameter);
		
		// Verify
		assertFalse(command.canExecute());
	}
	
	
	@Test
	void shouldGetCommandAndNotExecuteNoURI() throws Exception {
		// Prep
		commandParameter = new CommandParameter(new Object(), null, fragments);
		Command command = editingDomain.createCommand(GetCommand.class, commandParameter);

		// Verify
		assertFalse(command.canExecute());
	}
}

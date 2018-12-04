package us.coastalhacking.corvus.emf.provider;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import us.coastalhacking.corvus.test.util.AbstractProjectTest;
import us.coastalhacking.corvus.test.util.TestRoot;
import us.coastalhacking.corvus.test.util.UtilFactory;

class SaveUriJobProviderTest extends AbstractProjectTest {

	public SaveUriJobProviderTest() throws Exception {
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

	// TODO: copypasta'd from sibling test
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
	}

	@Test
	void shouldRunInWorkspace() throws Exception {
		// Execute
		SaveUriJob job = new SaveUriJob(editingDomain, options, uris);
		job.schedule();
		
		// Verify
		assertTrue(job.join(3000, null));
		assertFalse(resource.isModified());
	}

}

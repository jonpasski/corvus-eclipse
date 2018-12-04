package us.coastalhacking.corvus.emf.provider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.MultiRule;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.ChangeFactory;
import org.eclipse.emf.ecore.change.FeatureChange;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.transaction.TransactionChangeDescription;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import us.coastalhacking.corvus.emf.EmfApi;
import us.coastalhacking.corvus.test.util.AbstractProjectTest;

class ResourceModifiedListenerProviderTest extends AbstractProjectTest {

	public ResourceModifiedListenerProviderTest() throws Exception {
		super();
	}

	@Disabled(value="Not supported")
	@Test
	void shouldConfigure() throws Exception {
		// Prep
		Map<String, Object> filterProps = new HashMap<>();
		filterProps.put(EmfApi.ResourceSetListener.Properties.ID, EmfApi.ResourceSetListener.Properties.ResourceModifiedListener.ID);

		// Execute & verify
		ResourceModifiedListenerProvider provider = (ResourceModifiedListenerProvider) serviceTrackerHelper(filterProps);
		assertNotNull(provider);
	}

	@Test
	void shouldGetRule() throws Exception {
		// Mock and setup
		final IFile file = project.getFile("shouldGetRule");
		String expectedText = "This is text";
		file.create(new ByteArrayInputStream(expectedText.getBytes()), false, null);

		URI physical = URI.createPlatformResourceURI(file.getFullPath().toPortableString(), true);
		assertTrue(physical.isPlatform());
		Set<URI> uris = new HashSet<>();
		uris.add(physical);

		ISchedulingRule modifyResource = workspace.getRuleFactory().modifyRule(file);

		ResourceModifiedListenerProvider provider = new ResourceModifiedListenerProvider();
		provider.workspace = workspace;

		// Execute and verify
		ISchedulingRule rule = provider.getRule(uris);
		assertTrue(rule instanceof MultiRule);
		MultiRule multiRule = (MultiRule) rule;
		assertTrue(multiRule.contains(modifyResource));
	}

	@Test
	void shouldAddUris() {
		ResourceSet rs = new ResourceSetImpl();

		URI changedPhysical = URI.createPlatformResourceURI(
				project.getFile("changePhysical.xmi").getFullPath().toPortableString(), true);
		URI changedLogical = URI.createURI("change:logical");
		rs.getURIConverter().getURIMap().put(changedLogical, changedPhysical);

		URI detachedPhysical = URI.createPlatformResourceURI(
				project.getFile("detachedPhysical.xmi").getFullPath().toPortableString(), true);
		URI detachedLogical = URI.createURI("detached:logical");
		rs.getURIConverter().getURIMap().put(detachedLogical, detachedPhysical);

		Resource changedResource = new ResourceImpl();
		changedResource.setURI(changedLogical);
		rs.getResources().add(changedResource);

		Resource detachedResource = new ResourceImpl();
		detachedResource.setURI(detachedLogical);
		rs.getResources().add(detachedResource);

		TransactionChangeDescription changeDescription = mock(TransactionChangeDescription.class);
		ChangeDescription description = ChangeFactory.eINSTANCE.createChangeDescription();

		EObject changedEObject = mock(EObject.class);
		when(changedEObject.eResource()).thenReturn(changedResource);
		Map.Entry<EObject, EList<FeatureChange>> entry = ChangeFactory.eINSTANCE
				.createEObjectToChangesMapEntry(changedEObject);
		description.getObjectChanges().add(entry);
		when(changeDescription.getObjectChanges()).thenReturn(description.getObjectChanges());

		EObject detachedEObject = mock(EObject.class);
		when(detachedEObject.eResource()).thenReturn(detachedResource);
		EList<EObject> detached = new BasicEList<EObject>();
		detached.add(detachedEObject);
		when(changeDescription.getObjectsToDetach()).thenReturn(detached);

		// Call & verify
		Set<URI> set = new HashSet<>();
		ResourceModifiedListenerProvider provider = new ResourceModifiedListenerProvider();
		provider.addUris(set, changeDescription);
		assertEquals(2, set.size());
		assertTrue(set.contains(changedPhysical));
		assertTrue(set.contains(detachedPhysical));
	}
}

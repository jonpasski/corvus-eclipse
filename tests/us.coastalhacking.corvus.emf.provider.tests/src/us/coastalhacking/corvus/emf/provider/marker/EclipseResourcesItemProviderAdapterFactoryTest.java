package us.coastalhacking.corvus.emf.provider.marker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import us.coastalhacking.corvus.test.util.AbstractProjectTest;

class EclipseResourcesItemProviderAdapterFactoryTest extends AbstractProjectTest {

	public EclipseResourcesItemProviderAdapterFactoryTest() throws Exception {
		super();
	}

	// Mock
	//
	EclipseResourcesItemProviderAdapterFactory provider;
	IResource mockResource;
	IMarker mockMarker;

	@BeforeEach
	void subBeforeEach() throws Exception {
		// Mock
		//
		provider = new EclipseResourcesItemProviderAdapterFactory();
		mockMarker = mock(IMarker.class);
		mockResource = mock(IResource.class);
	}

	@Test
	void shouldBeFactoryForType() {
		// Verify
		assertTrue(provider.isFactoryForType(mockMarker));
		assertTrue(provider.isFactoryForType(mockResource));
		assertTrue(provider.isFactoryForType(IEditingDomainItemProvider.class));
		assertTrue(provider.isFactoryForType(IItemLabelProvider.class));
		assertTrue(provider.isFactoryForType(IStructuredItemContentProvider.class));
		assertTrue(provider.isFactoryForType(ITreeItemContentProvider.class));
		assertTrue(provider.isFactoryForType(EclipseResourcesItemProviderAdapterFactory.eclipseResourcesPackage));
	}

	@Test
	void shouldNotBeFactoryForType() {
		// Verify
		assertFalse(provider.isFactoryForType(provider));
		assertFalse(provider.isFactoryForType(IMarker.class));
	}

	@Test
	void shouldResolveObject() {
		// Prep
		Object expected = new Object();

		// Execute
		Object actual = provider.resolve(expected, EclipseResourcesItemProviderAdapterFactory.eclipseResourcesPackage);

		// Verify
		assertEquals(expected, actual);
	}

	@Test
	void shouldResolveIMarker() {
		// Execute
		Object actual = provider.resolve(mockMarker, IEditingDomainItemProvider.class);

		// Verify
		assertTrue(actual instanceof IMarkerItemProvider);
	}

	@Test
	void shouldResolveIResource() {
		// Execute
		Object actual = provider.resolve(mockResource, IEditingDomainItemProvider.class);

		// Verify
		assertTrue(actual instanceof IResourceItemProvider);
	}

	@Test
	void shouldCreateIMarkerAdapter() {
		// Execute
		Adapter actual = provider.createIMarkerAdapter();

		// Verify
		assertTrue(actual instanceof IMarkerItemProvider);
	}

	@Test
	void shouldCreateIResourceAdapter() {
		// Execute
		Adapter actual = provider.createIResourceAdapter();

		// Verify
		assertTrue(actual instanceof IResourceItemProvider);
	}

	@Test
	void shouldResourceChange() {
		fail("Not implemented");
	}

	@Test
	void shouldConvertIMarkerDeltaAddNotification() {
		fail("Not implemented");
	}

	@Test
	void shouldConvertIMarkerDeltaRemoveNotification() {
		fail("Not implemented");
	}

	@Test
	void shouldConvertIMarkerDeltaMultipleNotification() {
		fail("Not implemented");
	}
}

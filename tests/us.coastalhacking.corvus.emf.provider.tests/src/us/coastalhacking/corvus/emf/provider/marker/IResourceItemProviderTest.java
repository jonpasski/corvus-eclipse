package us.coastalhacking.corvus.emf.provider.marker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collection;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IResourceItemProviderTest {

	// Mock
	//
	final String fullPath = "/this/is/a/fullpath";
	IPath mockPath;
	IResourceItemProvider provider;
	IResource mockResource;
	IMarker mockMarker;
	ComposedAdapterFactory mockRoot;
	IMarker[] mockMarkers;

	@BeforeEach
	void subBeforeEach() throws Exception {
		// Mock
		//
		mockPath = mock(IPath.class);
		when(mockPath.toString()).thenReturn(fullPath);
		mockResource = mock(IResource.class);
		when(mockResource.getFullPath()).thenReturn(mockPath);
		mockMarker = mock(IMarker.class);
		mockMarkers = new IMarker[] { mockMarker };
		when(mockResource.findMarkers(anyString(), anyBoolean(), anyInt())).thenReturn(mockMarkers);

		mockRoot = mock(ComposedAdapterFactory.class);
		when(mockRoot.getRootAdapterFactory()).thenReturn(mockRoot);
		provider = new IResourceItemProvider(mockRoot);
	}

	@Test
	void shouldGetText() {
		// Execute
		String actual = provider.getText(mockResource);

		// Verify
		assertEquals(fullPath, actual);
	}

	@Test
	void shouldGetChildren() {
		// Execute
		Collection<?> actual = provider.getChildren(mockResource);

		// Verify
		assertTrue(actual.size() == 1);
		assertTrue(actual.contains(mockMarker));
	}

	@Test
	void shouldGetParent() {
		// Execute
		Object actual = provider.getParent(mockResource);

		// Verify
		assertNull(actual);
	}

	@Test
	void shouldGetNewChildrenDescriptors() {
		// Execute
		Collection<?> actual = provider.getNewChildDescriptors(null, null, null);

		// Verify
		assertTrue(actual.isEmpty());
	}

	@Test
	void shouldCreateCommand() {
		fail("Not yet implemented");
	}
}

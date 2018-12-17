package us.coastalhacking.corvus.emf.provider.marker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IMarkerItemProviderTest {

	// Mock
	//
	final String message = "this is the message";
	Map<String, Object> mockMap;
	Set<Entry<String, Object>> mockEntrySet;
	Entry<String, Object> mockEntryA;
	Entry<String, Object> mockEntryB;

	IMarkerItemProvider provider;
	IResource mockResource;
	IMarker mockMarker;
	ComposedAdapterFactory mockRoot;
	IMarker[] mockMarkers;

	@SuppressWarnings("unchecked")
	@BeforeEach
	void subBeforeEach() throws Exception {
		// Mock
		//
		mockMap = mock(Map.class);
		mockEntrySet = new HashSet<>();
		when(mockMap.entrySet()).thenReturn(mockEntrySet);
		mockEntryA = mock(Entry.class);
		mockEntrySet.add(mockEntryA);
		mockEntryB = mock(Entry.class);
		mockEntrySet.add(mockEntryB);

		mockMarker = mock(IMarker.class);
		when(mockMarker.getAttributes()).thenReturn(mockMap);
		when(mockMarker.getAttribute(eq(IMarker.MESSAGE), anyString())).thenReturn(message);
		mockResource = mock(IResource.class);
		when(mockMarker.getResource()).thenReturn(mockResource);
		mockMarkers = new IMarker[] { mockMarker };
		when(mockResource.findMarkers(anyString(), anyBoolean(), anyInt())).thenReturn(mockMarkers);

		mockRoot = mock(ComposedAdapterFactory.class);
		when(mockRoot.getRootAdapterFactory()).thenReturn(mockRoot);
		provider = new IMarkerItemProvider(mockRoot);
	}

	@Test
	void shouldGetText() {
		// Execute
		String actual = provider.getText(mockMarker);

		// Verify
		assertEquals(message, actual);
	}

	@Test
	void shouldGetChildren() {
		// Execute
		Collection<?> actual = provider.getChildren(mockMarker);

		// Verify
		assertTrue(actual.size() == 2);
		assertTrue(actual.contains(mockEntryA));
		assertTrue(actual.contains(mockEntryB));
	}

	@Test
	void shouldGetParent() {
		// Execute
		Object actual = provider.getParent(mockMarker);

		// Verify
		assertEquals(mockResource, actual);
	}

	@Test
	void shouldGetNewChildrenDescriptors() {
		// Execute
		Collection<?> actual = provider.getNewChildDescriptors(null, null, null);

		// Verify
		assertTrue(actual.isEmpty());
	}

}

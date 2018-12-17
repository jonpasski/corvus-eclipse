package us.coastalhacking.corvus.emf.provider.marker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.Optional;

import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import us.coastalhacking.corvus.emf.ILocationItemProvider;
import us.coastalhacking.corvus.emf.MarkerApi;
import us.coastalhacking.corvus.emf.MarkerApi.Marker.Base;
import us.coastalhacking.corvus.emf.lsp.DocumentUri;
import us.coastalhacking.corvus.emf.lsp.Range;

class IMarkerDeltaItemProviderTest {

	ComposeableAdapterFactory adapterFactory;
	IMarkerDeltaItemProvider provider;
	IMarkerDelta mockDelta;
	final Integer startChar = 3;
	final Integer startLine = 5;
	final Integer endChar = 2;
	final Integer endLine = 7;
	final String documentUri = "documentUri";

	ILocationItemProvider mockLocation;
	Optional<Integer> optStartChar;
	Optional<Integer> optStartLine;
	Optional<Integer> optEndChar;
	Optional<Integer> optEndLine;
	Optional<String> optDocumentUri;

	@BeforeEach
	void subBeforeEach() {
		adapterFactory = mock(ComposeableAdapterFactory.class);
		when(adapterFactory.getRootAdapterFactory()).thenReturn(adapterFactory);
		provider = new IMarkerDeltaItemProvider(adapterFactory);
		mockDelta = mock(IMarkerDelta.class);

		optStartChar = Optional.of(startChar);
		when(mockDelta.getAttribute(Base.START_CHAR)).thenReturn(startChar);

		optStartLine = Optional.of(startLine);
		when(mockDelta.getAttribute(Base.START_LINE)).thenReturn(startLine);

		optEndChar = Optional.of(endChar);
		when(mockDelta.getAttribute(Base.END_CHAR)).thenReturn(endChar);

		optEndLine = Optional.of(endLine);
		when(mockDelta.getAttribute(Base.END_LINE)).thenReturn(endLine);
		optDocumentUri = Optional.of(documentUri);

		mockLocation = mock(ILocationItemProvider.class);
		when(adapterFactory.adapt(mockDelta, ILocationItemProvider.class)).thenReturn(mockLocation);
		when(mockLocation.getDocumentUri(eq(mockDelta))).thenReturn(optDocumentUri);
		when(mockLocation.getStartCharacter(eq(mockDelta))).thenReturn(optStartChar);
		when(mockLocation.getStartLine(eq(mockDelta))).thenReturn(optStartLine);
		when(mockLocation.getEndCharacter(eq(mockDelta))).thenReturn(optEndChar);
		when(mockLocation.getEndLine(eq(mockDelta))).thenReturn(optEndLine);
	}


	@Test
	void shouldGetAddNotification() {
		// Prep
		when(mockDelta.getKind()).thenReturn(IResourceDelta.ADDED);

		// Execute
		Notification actualNotification = provider.getNotification(mockDelta);
		
		// Verify
		assertNotNull(actualNotification);
		assertEquals(Notification.ADD, actualNotification.getEventType());
		assertNull(actualNotification.getOldValue());
		Object newValue = actualNotification.getNewValue();
		assertTrue(newValue instanceof Range);
		Range<?> actualRange = (Range<?>) newValue;
		assertEquals(Integer.class, actualRange.getRangeType());
		assertEquals(optStartChar.get(), actualRange.getStartCharacter());
		assertEquals(optStartLine.get(), actualRange.getStartLine());
		assertEquals(optEndChar.get(), actualRange.getEndCharacter());
		assertEquals(optEndLine.get(), actualRange.getEndLine());
		assertEquals(DocumentUri.DOCUMENT_URI__RANGE, actualNotification.getFeatureID(Range.class));
		Object notifier = actualNotification.getNotifier();
		assertTrue(notifier instanceof DocumentUri);
		DocumentUri actualUri = (DocumentUri) notifier;
		assertEquals(optDocumentUri.get(), actualUri.getUri());
	}

	@Test
	void shouldGetRemoveNotification() {
		// Prep
		when(mockDelta.getKind()).thenReturn(IResourceDelta.REMOVED);

		// Execute
		Notification actualNotification = provider.getNotification(mockDelta);
		
		// Verify
		assertNotNull(actualNotification);
		assertEquals(Notification.REMOVE, actualNotification.getEventType());
		assertNull(actualNotification.getNewValue());
		Object newValue = actualNotification.getOldValue();
		assertTrue(newValue instanceof Range);
		Range<?> actualRange = (Range<?>) newValue;
		assertEquals(Integer.class, actualRange.getRangeType());
		assertEquals(optStartChar.get(), actualRange.getStartCharacter());
		assertEquals(optStartLine.get(), actualRange.getStartLine());
		assertEquals(optEndChar.get(), actualRange.getEndCharacter());
		assertEquals(optEndLine.get(), actualRange.getEndLine());
		assertEquals(DocumentUri.DOCUMENT_URI__RANGE, actualNotification.getFeatureID(Range.class));
		Object notifier = actualNotification.getNotifier();
		assertTrue(notifier instanceof DocumentUri);
		DocumentUri actualUri = (DocumentUri) notifier;
		assertEquals(optDocumentUri.get(), actualUri.getUri());
	}

	@Test
	void shouldGetStartCharacter() {
		// Execute
		Optional<Integer> actual = provider.getStartCharacter(mockDelta);

		// Verify
		assertEquals(startChar, actual.get());
	}

	@Test
	void shouldGetStartLine() {
		// Prep
		// 1-relative to 0-relative
		Integer expected = startLine - 1;

		// Execute
		Optional<Integer> actual = provider.getStartLine(mockDelta);

		// Verify
		assertEquals(expected, actual.get());
	}

	@Test
	void shouldGetEndCharacter() {
		// Execute
		Optional<Integer> actual = provider.getEndCharacter(mockDelta);

		// Verify
		assertEquals(endChar, actual.get());
	}

	@Test
	void shouldGetEndLine() {
		// Prep
		// 1-relative to 0-relative
		Integer expected = endLine - 1;

		// Execute
		Optional<Integer> actual = provider.getEndLine(mockDelta);

		// Verify
		assertEquals(expected, actual.get());
	}

	@Test
	void shouldGetDocumentUri() {
		// Prep
		IPath mockPath = mock(IPath.class);
		String fullPath = "/full/path";
		when(mockPath.toString()).thenReturn(fullPath);
		IResource mockResource = mock(IResource.class);
		when(mockResource.getFullPath()).thenReturn(mockPath);
		when(mockDelta.getResource()).thenReturn(mockResource);
		String markerType = "marker.value";
		when(mockDelta.getType()).thenReturn(markerType);

		// Execute
		Optional<String> actual = provider.getDocumentUri(mockDelta);

		// Verify
		assertEquals(String.format("%s%s?%s=%s", "platform:/resource", fullPath, MarkerApi.DocumentUri.PARAM_TYPE,
				markerType), actual.get());
	}

}

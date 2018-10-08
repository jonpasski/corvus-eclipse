package us.coastalhacking.corvus.eclipse.ui.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.concurrent.CompletableFuture;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.text.ITextSelection;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import us.coastalhacking.corvus.eclipse.ui.api.MarkerSupport.Coordinate;
import us.coastalhacking.corvus.eclipse.ui.impl.MarkerSupportImpl;
import us.coastalhacking.corvus.eclipse.ui.impl.MarkerSupportImpl.ResourceJob;

class MarkerSupportImplTest {

	@Test
	void shouldGetCoordinate() throws Exception {
		int charStart = 0;
		int length = 10;
		int startLine = 0;
		String expectedText = "Australian magpie";

		ITextSelection textSelection = mock(ITextSelection.class);
		when(textSelection.getOffset()).thenReturn(charStart);
		when(textSelection.getLength()).thenReturn(length);
		when(textSelection.getStartLine()).thenReturn(startLine);
		when(textSelection.getText()).thenReturn(expectedText);

		ESelectionService selectionService = mock(ESelectionService.class);
		when(selectionService.getSelection()).thenReturn(textSelection);

		IEclipseContext context = mock(IEclipseContext.class);
		when(context.get(ESelectionService.class)).thenReturn(selectionService);

		MarkerSupportImpl impl = new MarkerSupportImpl();
		impl.context = context;
		Coordinate coordinate = impl.getSelectedCoordinate().get();

		assertEquals(charStart, coordinate.charStart());
		assertEquals(charStart + length, coordinate.charEnd());
		assertEquals(startLine, coordinate.lineNumber());
		assertEquals(expectedText, coordinate.selected());
	}

	@Disabled("Needs to run as itest")
	@Test
	void shouldRunResourceJob() throws Exception {

		@SuppressWarnings("unchecked")
		CompletableFuture<Void> future = mock(CompletableFuture.class);
		IResource resource = mock(IResource.class);
		IMarker marker = mock(IMarker.class);
		when(resource.createMarker(any())).thenReturn(marker);
		Coordinate coordinate = mock(Coordinate.class);

		int charStart = 0;
		int charEnd = 10;
		int lineNumber = 0;
		String selected = "Common raven";
		when(coordinate.charStart()).thenReturn(charStart);
		when(coordinate.charEnd()).thenReturn(charEnd);
		when(coordinate.lineNumber()).thenReturn(lineNumber);
		when(coordinate.selected()).thenReturn(selected);

		String markerId = "";
		MarkerSupportImpl impl = new MarkerSupportImpl();
		impl.id = "foo.bar";
		ResourceJob job = impl.new ResourceJob(future, markerId, resource, coordinate);
		job.runInWorkspace(null);

		verify(marker).setAttribute(IMarker.CHAR_START, charStart);
		verify(marker).setAttribute(IMarker.CHAR_END, charEnd);
		// 1 offset
		verify(marker).setAttribute(IMarker.LINE_NUMBER, lineNumber + 1);
		verify(marker).setAttribute(IMarker.MESSAGE, selected);
	}

	@Test
	void shouldActivate() {
		MarkerSupportImpl impl = new MarkerSupportImpl();
		impl.activate(new String[] { "key1", "key2" }, new String[] { "id1", "id2" });
		assertEquals(impl.markerKeysIds.get("key1"), "id1");
		assertEquals(impl.markerKeysIds.inverse().get("id2"), "key2");
	}

}

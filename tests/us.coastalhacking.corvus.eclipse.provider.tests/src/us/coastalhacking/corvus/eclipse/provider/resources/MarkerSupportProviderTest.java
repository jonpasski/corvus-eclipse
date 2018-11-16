package us.coastalhacking.corvus.eclipse.provider.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.junit.jupiter.api.Test;

import us.coastalhacking.corvus.eclipse.EclipseApi;
import us.coastalhacking.corvus.eclipse.MarkerSupport;
import us.coastalhacking.corvus.eclipse.MarkerSupport.Coordinate;
import us.coastalhacking.corvus.eclipse.provider.resources.MarkerSupportProvider;
import us.coastalhacking.corvus.test.util.AbstractProjectTest;

public class MarkerSupportProviderTest extends AbstractProjectTest {

	public MarkerSupportProviderTest() throws Exception {
		super();
	}

	@Test
	void shouldConfigure() throws Exception {	
		MarkerSupport supportProvider = serviceTrackerHelper(MarkerSupport.class);
		assertNotNull(supportProvider);
	}

	@Test
	void shouldCreateMarker() throws Exception {

		String selected = "This";
		int lineNumber = 1;
		int charStart = 0;
		int charEnd = 4;
		MarkerSupport.Coordinate coordinate = new MarkerSupport.Coordinate() {
			
			@Override
			public String selected() {
				return selected;
			}
			
			@Override
			public int lineNumber() {
				return lineNumber;
			}
			
			@Override
			public int charStart() {
				return charStart;
			}
			
			@Override
			public int charEnd() {
				return charEnd;
			}
		};
		String expectedText = "This is text";
		String filename = "shouldGetRule";
		final IFile file = createFile(filename, expectedText);
		
		MarkerSupportProvider provider = new MarkerSupportProvider();
		provider.workspace = workspace;
		
		String markerType = EclipseApi.Marker.BASE_MARKER;
		// Call and verify
		IMarker[] markers = file.findMarkers(markerType, true, IResource.DEPTH_ZERO);
		assertEquals(0, markers.length);
		Future<Void> future = provider.createMarker(coordinate, file.getFullPath().toPortableString(), markerType);
		future.get();
		assertTrue(future.isDone());
		markers = file.findMarkers(markerType, true, IResource.DEPTH_ZERO);
		assertEquals(1, markers.length);
		IMarker marker = markers[0];
		assertEquals(markerType, marker.getType());
	}
	
	public interface EditorResource extends IResource, IEditorInput {

	}

	@Test
	void shouldPopulateResource() throws Exception {

		// Mock & prep
		IEclipseContext context = mock(IEclipseContext.class);
		EditorResource expected = mock(EditorResource.class);
		IEditorPart editor = mock(IEditorPart.class);
		when(editor.getEditorInput()).thenReturn(expected);
		IWorkbenchPage page = mock(IWorkbenchPage.class);
		when(page.getActiveEditor()).thenReturn(editor);
		IWorkbenchWindow window = mock(IWorkbenchWindow.class);
		when(window.getActivePage()).thenReturn(page);
		IWorkbench workbench = mock(IWorkbench.class);
		when(workbench.getActiveWorkbenchWindow()).thenReturn(window);
		when(context.get(IWorkbench.class)).thenReturn(workbench);
		final CompletableFuture<IResource> future = new CompletableFuture<>();

		// Call & verify
		MarkerSupportProvider provider = new MarkerSupportProvider();
		provider.eclipseContext = context;
		provider.populateResource(future);
		IResource actual = future.get();
		assertEquals(expected, actual);

	}

	@Test
	void shouldGetSelectedCoordinate() throws Exception {

		// Mock and Prep
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
		MarkerSupportProvider provider = new MarkerSupportProvider();
		provider.eclipseContext = context;

		// Call & verify
		Coordinate coordinate = provider.getSelectedCoordinate().get();
		assertEquals(charStart, coordinate.charStart());
		assertEquals(charStart + length, coordinate.charEnd());
		assertEquals(startLine, coordinate.lineNumber());
		assertEquals(expectedText, coordinate.selected());
	}


/*
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
		MarkerSupportProvider impl = new MarkerSupportProvider();
		impl.id = "foo.bar";
		ResourceJob job = impl.new ResourceJob(future, markerId, resource, coordinate);
		job.runInWorkspace(null);

		verify(marker).setAttribute(IMarker.CHAR_START, charStart);
		verify(marker).setAttribute(IMarker.CHAR_END, charEnd);
		// 1 offset
		verify(marker).setAttribute(IMarker.LINE_NUMBER, lineNumber + 1);
		verify(marker).setAttribute(IMarker.MESSAGE, selected);
	}	
 */
}

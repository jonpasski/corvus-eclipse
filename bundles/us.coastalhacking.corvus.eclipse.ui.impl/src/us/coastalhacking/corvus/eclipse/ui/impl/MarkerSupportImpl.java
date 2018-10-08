package us.coastalhacking.corvus.eclipse.ui.impl;

import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.di.UISynchronize;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.ide.ResourceUtil;

import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.Streams;

import us.coastalhacking.corvus.eclipse.ui.api.MarkerSupport;

class MarkerSupportImpl implements MarkerSupport {

	protected IEclipseContext context;

	protected String id;

	protected BiMap<String, String> markerKeysIds;

	class ResourceJob extends WorkspaceJob {

		final IResource resource;
		final String markerId;
		final CompletableFuture<Void> future;
		final Coordinate coordinate;

		public ResourceJob(CompletableFuture<Void> future, String markerId, IResource resource, Coordinate coordinate) {
			super(String.format("New marker '%s' on resource '%s' job", markerId, resource.getLocationURI()));
			this.resource = resource;
			this.markerId = markerId;
			this.future = future;
			this.coordinate = coordinate;
		}

		@Override
		public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
			IStatus result = null;
			try {
				IMarker marker = resource.createMarker(markerId);
				// Offset by 1
				int lineNo = coordinate.lineNumber() + 1;
				if (marker.isSubtypeOf(IMarker.TEXT)) {
					marker.setAttribute(IMarker.CHAR_START, coordinate.charStart());
					marker.setAttribute(IMarker.CHAR_END, coordinate.charEnd());
					marker.setAttribute(IMarker.LINE_NUMBER, lineNo);
				}

				if (marker.isSubtypeOf(IMarker.BOOKMARK)) {
					marker.setAttribute(IMarker.MESSAGE, coordinate.selected());
					marker.setAttribute(IMarker.LOCATION, String.format("line %s", lineNo));
				}
				future.complete(null);
				result = Status.OK_STATUS;
			} catch (Exception e) {
				future.completeExceptionally(e);
				result = new Status(IStatus.ERROR, id, e.getMessage(), e);
			}
			return result;
		}

	}

	protected void activate(String[] markerKeys, String[] markerIds) {
		assert markerKeys.length == markerIds.length;
		markerKeysIds = ImmutableBiMap.copyOf(Streams
				.zip(Arrays.stream(markerKeys), Arrays.stream(markerIds),
						(key, id) -> new AbstractMap.SimpleEntry<String, String>(key, id))
				.collect(Collectors.toList()));
	}

	protected void deactivate() {
		markerKeysIds = null;
	}

	@Override
	public boolean isSupported(String markerKey) {
		return markerKeysIds.containsKey(markerKey);
	}

	@Override
	public String getMarkerId(String markerKey) {
		return markerKeysIds.get(markerKey);
	}

	@Override
	public String getMarkerKey(String markerId) {
		return markerKeysIds.inverse().get(markerId);
	}

	@Override
	public Future<IResource> getActiveEditorResource() {
		final CompletableFuture<IResource> future = new CompletableFuture<>();
		try {
			final IWorkbench workbench = context.get(IWorkbench.class);
			UISynchronize uiSync = context.get(UISynchronize.class);
			// The active window only can be obtained via the UI thread
			uiSync.asyncExec(() -> {
				try {
					final IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
					final IWorkbenchPage page = window.getActivePage();
					final IEditorPart editor = page.getActiveEditor();
					IResource resource = ResourceUtil.getResource(editor.getEditorInput());
					if (resource != null) {
						future.complete(resource);
					} else {
						future.completeExceptionally(new NullPointerException(
								"ResourceUtil.getResource returned null for the active editor input."));
					}
				} catch (Exception e) {
					future.completeExceptionally(e);
				}
			});
		} catch (Exception e) {
			future.completeExceptionally(e);
		}
		return future;
	}

	@Override
	public Optional<Coordinate> getSelectedCoordinate() throws Exception {

		ESelectionService selectionService = context.get(ESelectionService.class);
		Object selection = selectionService.getSelection();
		if (selection instanceof ITextSelection) {
			return Optional.of(new TextSelectionCoordinate((ITextSelection) selection));
		}
		return Optional.empty();
	}

	protected class TextSelectionCoordinate implements Coordinate {

		private final int charStart;
		private final int charEnd;
		private final int lineStart;
		private final String message;

		public TextSelectionCoordinate(ITextSelection textSelection) {
			charStart = textSelection.getOffset();
			charEnd = textSelection.getOffset() + textSelection.getLength();
			lineStart = textSelection.getStartLine();
			message = textSelection.getText();
		}

		@Override
		public int charStart() {
			return charStart;
		}

		@Override
		public int charEnd() {
			return charEnd;
		}

		@Override
		public int lineNumber() {
			return lineStart;
		}

		@Override
		public String selected() {
			return message;
		}
	}

	@Override
	public Future<Void> createMarker(String markerKey) {
		final CompletableFuture<Void> future = new CompletableFuture<>();
		// TODO: add a logging callback to the future via thenAccept
		future.exceptionally(ex -> {
			ex.printStackTrace();
			return null;
		});
		try {
			if (!isSupported(markerKey))
				throw new UnsupportedOperationException("Unsupported marker key " + markerKey);

			final String markerId = getMarkerId(markerKey);
			final IResource resource = getActiveEditorResource().get();
			final Coordinate coordinate = getSelectedCoordinate().get();
			final WorkspaceJob job = new ResourceJob(future, markerId, resource, coordinate);
			job.setRule(resource);
			job.schedule();
		} catch (Exception e) {
			future.completeExceptionally(e);
		}
		return future;
	}
}

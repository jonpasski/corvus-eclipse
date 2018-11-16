package us.coastalhacking.corvus.eclipse.provider.resources;

import java.text.MessageFormat;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.di.UISynchronize;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.ide.ResourceUtil;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

import us.coastalhacking.corvus.eclipse.EclipseApi;
import us.coastalhacking.corvus.eclipse.MarkerSupport;

@Component(service = MarkerSupport.class, configurationPid = EclipseApi.MarkerSupport.Component.CONFIG_PID, immediate=true)
public class MarkerSupportProvider implements MarkerSupport {

	@Reference
	IWorkbench workbench;

	@Reference
	IWorkspace workspace;
	
	IEclipseContext eclipseContext;
	
	@Activate
	void activate(BundleContext bundleContext) {
		this.id = bundleContext.getBundle().getSymbolicName();
		eclipseContext = workbench.getApplication().getContext();
	}

	@Deactivate
	protected void deactivate() {
		this.id = null;
	}
	
	protected String id;

	@Override
	public Future<Void> createMarker(Coordinate coordinate, String resourceFullPath, String markerType) {

		final CompletableFuture<Void> future = new CompletableFuture<>();
		// TODO: add a logging callback to the future via thenAccept
		future.exceptionally(ex -> {
			ex.printStackTrace();
			return null;
		});

		try {
			final IResource resource = workspace.getRoot().findMember(resourceFullPath);
			if (resource == null || !resource.exists()) {
				throw new IllegalArgumentException(MessageFormat.format("Attempting to create a marker on an non-existant resource {0}", resourceFullPath));				
			}
			
			final WorkspaceJob job = new MarkerResourceJob(id, future, markerType, resource, coordinate);
			job.setRule(resource);
			job.schedule();
		} catch (Exception e) {
			future.completeExceptionally(e);
		}
		return future;
	}


	@Override
	public Future<IResource> getActiveEditorResource() {
		final CompletableFuture<IResource> future = new CompletableFuture<>();
		try {
			UISynchronize uiSync = eclipseContext.get(UISynchronize.class);
			// The active window only can be obtained via the UI thread
			uiSync.asyncExec(() -> {
				populateResource(future);
			});
		} catch (Exception e) {
			future.completeExceptionally(e);
		}
		return future;
	}

	void populateResource(CompletableFuture<IResource> future) {
		try {
			final org.eclipse.ui.IWorkbench workbench = eclipseContext.get(org.eclipse.ui.IWorkbench.class);
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
	}

	@Override
	public Optional<Coordinate> getSelectedCoordinate() throws Exception {

		ESelectionService selectionService = eclipseContext.get(ESelectionService.class);
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

}

package us.coastalhacking.corvus.eclipse.ui.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.e4.core.commands.ECommandService;
import org.eclipse.e4.core.commands.EHandlerService;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.di.UISynchronize;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.texteditor.ITextEditor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;

import us.coastalhacking.corvus.eclipse.ui.api.EclipseUiApi;

@SuppressWarnings("restriction")
class RoundtripITest {

	static BundleContext bundleContext;
	static IWorkbench workbench;
	static IEclipseContext context;
	static IWorkspace workspace;
	static org.eclipse.ui.IWorkbench workbench3;

	@BeforeAll
	static void beforeAll() throws Exception {
		bundleContext = FrameworkUtil.getBundle(RoundtripITest.class).getBundleContext();
		assertNotNull(bundleContext);
		workbench = TestUtils.getService(bundleContext, IWorkbench.class, 500);
		assertNotNull(workbench);
		context = workbench.getApplication().getContext();
		workspace = context.get(IWorkspace.class);
		assertNotNull(workspace);
		workbench3 = context.get(org.eclipse.ui.IWorkbench.class);
		assertNotNull(workbench3);

	}

	@AfterAll
	static void afterAll() {
		workspace = null;
		context = null;
		workbench = null;
		bundleContext = null;
	}

	@Test
	void shouldRoundTrip() throws Exception {

		// Create project in workspace
		IProject project = workspace.getRoot().getProject(getClass().getName());
		project.create(null);
		project.open(null);

		// Create file in project
		IFile file = project.getFile("shouldRoundTrip.txt");
		String fullPath = file.getFullPath().toPortableString();
		String expectedText = "This is text";
		file.create(new ByteArrayInputStream(String.format("%s\n\n", expectedText).getBytes()), false, null);

		// Add IRCL to observe marker being created, with countdown latch
		CountDownLatch latch = new CountDownLatch(1);
		workspace.addResourceChangeListener(new TestListener(latch, fullPath));

		// All on UI thread
		UISynchronize uiSync = context.get(UISynchronize.class);
		uiSync.asyncExec(() -> {
			try {
				// open resource in editor?
				IEditorRegistry registry = context.get(IEditorRegistry.class);
				IEditorDescriptor desc = registry.getDefaultEditor(file.getName());
				assertNotNull(desc);
				IWorkbenchWindow window = workbench3.getActiveWorkbenchWindow();
				IWorkbenchPage page = window.getActivePage();
				assertNotNull(page);
				IEditorInput input = new FileEditorInput(file);
				IEditorPart part = page.openEditor(input, desc.getId());

				// Select content in file
				ISelectionProvider selectionProvider = part.getSite().getSelectionProvider();
				IDocument document = ((ITextEditor) part).getDocumentProvider().getDocument(input);
				TextSelection textSelection = new TextSelection(document, 0, expectedText.length());
				selectionProvider.setSelection(textSelection);

				// Call UI action
				EHandlerService handlerService = context.get(EHandlerService.class);
				String commandId = "us.coastalhacking.corvus.eclipse.ui.command";
				Map<String, Object> params = new HashMap<>();
				params.put(EclipseUiApi.CommandParameter.ACTION, EclipseUiApi.CommandParameter.ACTION_ADD);
				params.put(EclipseUiApi.CommandParameter.MARKER, "entrypoint");
				ECommandService commandService = context.get(ECommandService.class);
				ParameterizedCommand command = commandService.createCommand(commandId, params);

				assertNotNull(handlerService);
				if (handlerService.canExecute(command)) {
					handlerService.executeHandler(command);
				}

			} catch (Exception e) {
				fail(e);
			}
		});

		// Wait on latch
		assertTrue(latch.await(3, TimeUnit.SECONDS));
	}

	static class TestListener implements IResourceChangeListener {

		private final CountDownLatch latch;
		private final String fullPath;

		public TestListener(CountDownLatch latch, String fullPath) {
			this.latch = latch;
			this.fullPath = fullPath;

		}

		@Override
		public void resourceChanged(IResourceChangeEvent event) {

			String markerId = "us.coastalhacking.corvus.eclipse.resources.entrypoint";
			IMarkerDelta[] deltas = event.findMarkerDeltas(markerId, true);
			if (deltas.length > 0 && deltas[0].getResource().getFullPath().toPortableString().equals(fullPath)) {
				latch.countDown();
			}
		}

	}
}

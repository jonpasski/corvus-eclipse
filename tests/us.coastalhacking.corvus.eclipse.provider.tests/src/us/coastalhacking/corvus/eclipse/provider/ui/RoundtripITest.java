package us.coastalhacking.corvus.eclipse.provider.ui;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import us.coastalhacking.corvus.eclipse.EclipseApi;
import us.coastalhacking.corvus.test.util.AbstractProjectTest;
import us.coastalhacking.corvus.test.util.TestUtils;

@SuppressWarnings("restriction")
class RoundtripITest extends AbstractProjectTest {

	public RoundtripITest() throws Exception {
		super();
	}

	IWorkbench workbench;
	IEclipseContext eclipseContext;
	org.eclipse.ui.IWorkbench workbench3;

	@BeforeEach
	void beforeSubEach() throws Exception {
		workbench = TestUtils.getService(getBundleContext(), IWorkbench.class, 500);
		assertNotNull(workbench);
		eclipseContext = workbench.getApplication().getContext();
		workbench3 = eclipseContext.get(org.eclipse.ui.IWorkbench.class);
		assertNotNull(workbench3);
	}

	final static String MARKER = EclipseApi.Marker.ENTRY_POINT;
	
	@Test
	void shouldRoundTrip() throws Exception {
		IFile file = createFile("shouldRoundTrip.txt", "This is text\n\n");
		String fullPath = file.getFullPath().toPortableString();
		String expectedText = "This is text";
		
		// Add IRCL to observe marker being created, with countdown latch
		CountDownLatch latch = new CountDownLatch(1);
		workspace.addResourceChangeListener(new TestListener(latch, fullPath));

		// All on UI thread
		UISynchronize uiSync = eclipseContext.get(UISynchronize.class);
		uiSync.asyncExec(() -> {
			try {
				// open resource in editor?
				IEditorRegistry registry = eclipseContext.get(IEditorRegistry.class);
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
				EHandlerService handlerService = eclipseContext.get(EHandlerService.class);
				
				String commandId = EclipseApi.Fragment.Command.ID;
				Map<String, Object> params = new HashMap<>();
				params.put(EclipseApi.Fragment.CommandParameter.ACTION, EclipseApi.Fragment.CommandParameter.ACTION_ADD);
				params.put(EclipseApi.Fragment.CommandParameter.MARKER, MARKER);
				ECommandService commandService = eclipseContext.get(ECommandService.class);
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
		assertTrue(latch.await(10, TimeUnit.SECONDS));
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

			String markerId = RoundtripITest.MARKER;
			IMarkerDelta[] deltas = event.findMarkerDeltas(markerId, true);
			if (deltas.length > 0 && deltas[0].getResource().getFullPath().toPortableString().equals(fullPath)) {
				latch.countDown();
			}
		}

	}
}

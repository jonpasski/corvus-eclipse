package us.coastalhacking.corvus.eclipse.editor.provider;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.eclipse.core.resources.IFile;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.di.UISynchronize;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.TransactionalEditingDomain.Factory;
import org.eclipse.emf.transaction.TransactionalEditingDomain.Registry;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.part.FileEditorInput;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import us.coastalhacking.corvus.emf.EmfApi;
import us.coastalhacking.corvus.test.util.AbstractProjectTest;

class SemioticsEditorProviderTest extends AbstractProjectTest {

	public SemioticsEditorProviderTest() throws Exception {
		super();
	}

	IWorkbench workbench;
	org.eclipse.ui.IWorkbench workbench3;
	IEclipseContext eclipseContext;
	Registry registry;
	Factory factory;

	@BeforeEach
	void subBeforeEach() throws Exception {
		workbench = serviceTrackerHelper(IWorkbench.class);
		assertNotNull(workbench);
		eclipseContext = workbench.getApplication().getContext();
		workbench3 = eclipseContext.get(org.eclipse.ui.IWorkbench.class);
		assertNotNull(eclipseContext);
		Map<String, Object> props = new HashMap<>();
		String projectName = project.getFullPath().toPortableString();
		props.put(EmfApi.ResourceInitializer.Properties.PROJECT, projectName);
		// TODO move URI code out of here into util
		props.put(EmfApi.TransactionalEditingDomain.Properties.ID, URI.encodeSegment(projectName, true));

		factory = configurationHelper(Factory.class, EmfApi.CorvusTransactionalFactory.Component.CONFIG_PID, props,
				timeout);
		assertNotNull(factory);

		registry = configurationHelper(Registry.class, EmfApi.CorvusTransactionalRegistry.Component.CONFIG_PID, props,
				timeout);
		assertNotNull(registry);		
	}

	@AfterEach
	protected void afterEach() {
		registry = null;
		factory = null;
		eclipseContext = null;
		workbench = null;
	}

	@Test
	
	void shouldConfigure() throws Exception {
		// Need to have an IFile that's selected for the provider to work
		IFile file = createFile("foo.semiotics", "");

		UISynchronize uiSync = eclipseContext.get(UISynchronize.class);
		CountDownLatch latch = new CountDownLatch(1);
		uiSync.asyncExec(() -> {
			try {
				// UI thread stuff here
				IEditorRegistry registry = eclipseContext.get(IEditorRegistry.class);
				IEditorDescriptor desc = registry.getDefaultEditor(file.getName());
				assertNotNull(desc);
				IWorkbenchWindow window = workbench3.getActiveWorkbenchWindow();
				IWorkbenchPage page = window.getActivePage();
				assertNotNull(page);

				// Now select the file, needed for the editor provider
				ISelectionProvider selectionProvider = page.getActivePart().getSite().getSelectionProvider();
				IStructuredSelection structured = new StructuredSelection(file);
				selectionProvider.setSelection(structured);

				IEditorInput input = new FileEditorInput(file);
				IEditorPart part = page.openEditor(input, desc.getId());
				assertTrue(part instanceof SemioticsEditorProvider);
				latch.countDown();
			} catch (Exception e) {
				fail(e);
			}
		});
		
		assertTrue(latch.await(3, TimeUnit.SECONDS));
	}

}

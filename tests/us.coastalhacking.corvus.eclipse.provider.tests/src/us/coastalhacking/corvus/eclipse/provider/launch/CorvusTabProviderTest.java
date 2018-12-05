package us.coastalhacking.corvus.eclipse.provider.launch;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.ILaunchConfigurationTab2;
import org.eclipse.swt.widgets.Text;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import us.coastalhacking.corvus.eclipse.EclipseApi;
import us.coastalhacking.corvus.emf.TransactionIdUtil;
import us.coastalhacking.corvus.test.util.AbstractProjectTest;

class CorvusTabProviderTest extends AbstractProjectTest {

	public CorvusTabProviderTest() throws Exception {
		super();
	}

	TransactionIdUtil idUtil;
	CorvusTabProvider provider;
	TransactionIdUtil mockUtil;
	IWorkspace mockSpace;
	IWorkspaceRoot mockRoot;
	Text projectText;
	String projectName = CorvusTabProviderTest.class.getName();
	IProject mockProject;

	@BeforeEach
	void subBeforeEach() throws Exception {
		// Dependencies for OSGi
		//
		idUtil = serviceTrackerHelper(TransactionIdUtil.class);

		// Mocking
		//
		provider = new CorvusTabProvider();

		mockUtil = mock(TransactionIdUtil.class);
		when(mockUtil.getId(anyMap())).thenReturn(projectName);
		provider.idUtil = mockUtil;

		mockSpace = mock(IWorkspace.class);
		provider.workspace = mockSpace;
		mockRoot = mock(IWorkspaceRoot.class);
		when(mockSpace.getRoot()).thenReturn(mockRoot);
		mockProject = mock(IProject.class);
		when(mockProject.getName()).thenReturn(projectName);
		when(mockRoot.getProject(projectName)).thenReturn(mockProject);

		projectText = mock(Text.class);
		provider.projectText = projectText;
		when(projectText.getText()).thenReturn(projectName);
	}

	@Test
	void shouldGetViaOsgi() throws Exception {
		ILaunchConfigurationTab2 service = serviceTrackerHelper(ILaunchConfigurationTab2.class);
		assertTrue(service instanceof CorvusTabProvider);
	}

	@Test
	void shouldSetDefaults() {
		// Prep
		ILaunchConfigurationWorkingCopy configuration = mock(ILaunchConfigurationWorkingCopy.class);

		// Execute
		provider.setDefaults(configuration);

		// Verify
		verify(configuration, times(1)).setAttribute(EclipseApi.IResourceChangeListener.Properties.MARKER_TYPE,
				EclipseApi.Marker.BASE_MARKER);
	}

	@Test
	void shouldInitializeFrom() {
		// Prep
		ILaunchConfiguration config = mock(ILaunchConfiguration.class);

		// Execute
		provider.initializeFrom(config);

		// Verify
		verify(projectText, times(1)).setText(projectName);
	}

	@Test
	void shouldPerformApply() throws Exception {
		// Prep
		//
		@SuppressWarnings("unchecked") Map<String, Object> attributes = (Map<String, Object>)mock(Map.class);
		ILaunchConfigurationWorkingCopy configuration = mock(ILaunchConfigurationWorkingCopy.class);
		when(configuration.getAttributes()).thenReturn(attributes);
		// TODO: don't bury this in the tab provider & here
		String filteredProjectName = projectName.substring(1);
		
		// Execute
		provider.performApply(configuration);

		// Verify
		verify(configuration, times(1)).rename(filteredProjectName);
		verify(mockUtil, times(1)).putId(attributes, projectName);
	}

	@Disabled
	@Test
	void shouldCreateControl() {
	}

	@Disabled
	@Test
	void shouldGetName() {
	}
	
	@Disabled
	@Test
	void shouldListenerWidgetSelected() {
	}
}

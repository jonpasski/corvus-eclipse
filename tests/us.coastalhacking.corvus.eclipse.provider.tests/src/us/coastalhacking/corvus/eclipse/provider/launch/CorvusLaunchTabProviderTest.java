package us.coastalhacking.corvus.eclipse.provider.launch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.eclipse.debug.ui.CommonTab;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab2;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.osgi.service.component.ComponentConstants;
import org.osgi.service.component.ComponentFactory;
import org.osgi.service.component.ComponentInstance;

import us.coastalhacking.corvus.eclipse.EclipseApi;
import us.coastalhacking.corvus.emf.TransactionIdUtil;
import us.coastalhacking.corvus.test.util.AbstractProjectTest;

class CorvusLaunchTabProviderTest extends AbstractProjectTest {

	public CorvusLaunchTabProviderTest() throws Exception {
		super();
	}

	// OSGi
	TransactionIdUtil idUtil;
	ILaunchConfigurationTab2 corvusTab;
	ComponentInstance instance;

	// Mocks
	ILaunchConfigurationTab2 mockTab;
	CorvusLaunchTabProvider provider;
	ILaunchConfigurationDialog dialog;

	@BeforeEach
	void subBeforeEach() throws Exception {
		// Dependencies for OSGi
		//
		idUtil = serviceTrackerHelper(TransactionIdUtil.class);
		corvusTab = serviceTrackerHelper(ILaunchConfigurationTab2.class);

		// Mocks
		//
		mockTab = mock(ILaunchConfigurationTab2.class);
		provider = new CorvusLaunchTabProvider();
		provider.corvusTab = mockTab;

		dialog = mock(ILaunchConfigurationDialog.class);
	}

	@AfterEach
	void subAfterEach() throws Exception {
		if (instance != null) {
			instance.dispose();
		}
	}

	@Test
	void shouldGetViaOsgi() throws Exception {
		// Prep
		Map<String, Object> filterProps = new HashMap<>();
		filterProps.put(ComponentConstants.COMPONENT_FACTORY, EclipseApi.CorvusLaunchTab.Component.FACTORY);

		// Execute
		ComponentFactory factory = (ComponentFactory) serviceTrackerHelper(filterProps);
		instance = factory.newInstance(new Hashtable<>());

		// Verify
		assertTrue(instance.getInstance() instanceof CorvusLaunchTabProvider);
	}

	@Test
	void shouldCreateTabs() {
		// Execute
		provider.createTabs(dialog, "not used");

		// Verify
		assertTrue(provider.getTabs().length == 2);
		assertEquals(mockTab, provider.getTabs()[0]);
		assertTrue(provider.getTabs()[1] instanceof CommonTab);
	}

}

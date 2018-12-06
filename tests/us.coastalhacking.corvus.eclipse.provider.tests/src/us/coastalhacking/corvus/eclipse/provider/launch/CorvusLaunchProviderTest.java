package us.coastalhacking.corvus.eclipse.provider.launch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain.Factory;
import org.eclipse.emf.transaction.TransactionalEditingDomain.Registry;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.osgi.service.component.ComponentConstants;
import org.osgi.service.component.ComponentFactory;
import org.osgi.service.component.ComponentInstance;
import org.osgi.service.log.Logger;
import org.osgi.service.log.LoggerFactory;

import us.coastalhacking.corvus.eclipse.EclipseApi;
import us.coastalhacking.corvus.emf.EmfApi;
import us.coastalhacking.corvus.emf.TransactionIdUtil;
import us.coastalhacking.corvus.test.util.AbstractProjectTest;

class CorvusLaunchProviderTest extends AbstractProjectTest {

	public CorvusLaunchProviderTest() throws Exception {
		super();
	}

	// OSGi Dependencies
	//
	TransactionIdUtil idUtil;
	Map<String, Object> props;
	String id;
	Factory factory;
	Registry registry;
	IEditingDomainProvider domainProvider;
	ComponentFactory appFactory;
	ComponentInstance launcherInstance;
	LoggerFactory loggerFactory;

	// Mock
	//
	CorvusLaunchProvider provider;
	TransactionIdUtil mockIdUtil;
	ComponentFactory mockFactory;
	ILaunch mockLaunch;
	IProcess mockProcess;
	ComponentInstance mockLauncherInstance;
	ILaunchConfiguration mockConfig;
	Map<String, Object> mockProps;
	LoggerFactory mockLoggerFactory;
	Logger mockLogger;
	DebugException mockException;
	ArgumentCaptor<DebugException> exceptionCaptor;

	@BeforeEach
	void subBeforeEach() throws Exception {
		// OSGi
		//
		idUtil = serviceTrackerHelper(TransactionIdUtil.class);
		props = new HashMap<>();
		props.put(EclipseApi.IResourceChangeListener.Properties.MARKER_TYPE, EclipseApi.Marker.BASE_MARKER);
		id = idUtil.getId(project);
		idUtil.putId(props, id);
		factory = serviceTrackerHelper(Factory.class);
		registry = serviceTrackerHelper(Registry.class);
		domainProvider = configurationHelper(IEditingDomainProvider.class,
				EmfApi.IEditingDomainProvider.Component.CONFIG_PID, props, timeout);
		assertNotNull(domainProvider);
		final Map<String, Object> appFilterProps = new HashMap<>();
		appFilterProps.put(ComponentConstants.COMPONENT_FACTORY, EclipseApi.CorvusApp.Component.FACTORY);
		appFactory = (ComponentFactory) serviceTrackerHelper(appFilterProps);
		loggerFactory = serviceTrackerHelper(LoggerFactory.class);

		// Mock
		//
		provider = new CorvusLaunchProvider();
		mockLaunch = mock(ILaunch.class);
		mockProcess = mock(IProcess.class);
		when(mockProcess.canTerminate()).thenReturn(true);
		mockLauncherInstance = mock(ComponentInstance.class);
		when(mockLauncherInstance.getInstance()).thenReturn(mockLaunch);
		mockFactory = mock(ComponentFactory.class);
		when(mockFactory.newInstance(any())).thenReturn(mockLauncherInstance);

		provider.appFactory = mockFactory;
		mockIdUtil = mock(TransactionIdUtil.class);
		provider.idUtil = mockIdUtil;
		mockProps = new HashMap<>();

		mockConfig = mock(ILaunchConfiguration.class);

		mockLogger = mock(Logger.class);
		mockLoggerFactory = mock(LoggerFactory.class);
		when(mockLoggerFactory.getLogger(any(Class.class))).thenReturn(mockLogger);
		provider.loggerFactory = mockLoggerFactory;
		assertNull(provider.logger);

		mockException = mock(DebugException.class);
		exceptionCaptor = ArgumentCaptor.forClass(DebugException.class);
	}

	@AfterEach
	void subAfterEach() {
		if (launcherInstance != null)
			launcherInstance.dispose();
	}

	@Test
	void shouldGetViaOsgi() throws Exception {
		// Prep
		Map<String, Object> launcherFilterProps = new HashMap<>();
		launcherFilterProps.put(ComponentConstants.COMPONENT_FACTORY, EclipseApi.CorvusLaunch.Component.FACTORY);

		// Execute
		ComponentFactory launcherFactory = (ComponentFactory) serviceTrackerHelper(launcherFilterProps);
		launcherInstance = launcherFactory.newInstance(new Hashtable<>(props));

		// Verify
		assertTrue(launcherInstance.getInstance() instanceof CorvusLaunchProvider);
	}

	@Test
	void shouldLaunch() throws CoreException {
		// Prep
		provider.props = mockProps;
		ArgumentCaptor<IProcess> processCapture = ArgumentCaptor.forClass(IProcess.class);

		// Execute
		provider.launch(mockConfig, "not used", mockLaunch, null);

		// Verify
		verify(mockConfig, atLeast(1)).getAttributes();
		provider.launches.contains(mockLaunch);
		verify(mockLaunch, times(1)).addProcess(processCapture.capture());
		IProcess process = processCapture.getValue();
		assertTrue(process instanceof CorvusAppProcessBase);
	}

	@Test
	void shouldActivate() {
		// Execute
		provider.activate(mockProps);

		// Verify
		assertEquals(mockProps, provider.props);
		assertEquals(mockLogger, provider.logger);
	}

	@Test
	void shouldDeactivate() throws Exception {
		// Prep
		when(mockLaunch.getProcesses()).thenReturn(new IProcess[] { mockProcess });
		provider.launches.add(mockLaunch);

		// Execute
		provider.deactivate();

		// Verify
		assertTrue(provider.deactivated.get());
		verify(mockProcess, times(1)).terminate();
		assertTrue(provider.launches.isEmpty());

		// Execute again
		provider.deactivate();

		// Verify
		verify(mockProcess, times(1)).terminate();
	}

	@Test
	void shouldNotTerminate() throws DebugException {
		// Prep
		when(mockLaunch.getProcesses()).thenReturn(new IProcess[] { mockProcess });
		doThrow(mockException).when(mockProcess).terminate();
		provider.launches.add(mockLaunch);
		provider.logger = mockLogger;

		// Execute
		provider.deactivate();

		// Verify
		verify(mockLogger, times(1)).warn(anyString(), eq(mockProcess), exceptionCaptor.capture());
		assertEquals(mockException, exceptionCaptor.getValue());
	}
}

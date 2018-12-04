package us.coastalhacking.corvus.eclipse.provider.app;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain.Factory;
import org.eclipse.emf.transaction.TransactionalEditingDomain.Registry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.osgi.framework.Constants;
import org.osgi.service.component.ComponentConstants;
import org.osgi.service.component.ComponentFactory;
import org.osgi.service.component.ComponentInstance;

import us.coastalhacking.corvus.eclipse.EclipseApi;
import us.coastalhacking.corvus.emf.EmfApi;
import us.coastalhacking.corvus.emf.TransactionIdUtil;
import us.coastalhacking.corvus.test.util.AbstractProjectTest;

class CorvusAppProviderTest extends AbstractProjectTest {

	public CorvusAppProviderTest() throws Exception {
		super();
	}

	TransactionIdUtil idUtil;
	Map<String, Object> props;
	String id;
	Factory factory;
	Registry registry;
	IEditingDomainProvider domainProvider;
	final String markerType = EclipseApi.Marker.BASE_MARKER;
	
	@BeforeEach
	void subBeforeEach() throws Exception {
		idUtil = serviceTrackerHelper(TransactionIdUtil.class);
		assertNotNull(idUtil);
		props = new HashMap<>();
		props.put(EclipseApi.IResourceChangeListener.Properties.MARKER_TYPE, markerType);

		id = idUtil.getId(project);
		idUtil.putId(props, id);

		factory = serviceTrackerHelper(Factory.class);
		assertNotNull(factory);
		registry = serviceTrackerHelper(Registry.class);
		assertNotNull(registry);
		
		domainProvider = configurationHelper(IEditingDomainProvider.class, EmfApi.IEditingDomainProvider.Component.CONFIG_PID, props, timeout*2);
		assertNotNull(domainProvider);
	}

	@Test
	void shouldConfigure() throws Exception {
		// Get component factory
		Map<String, Object> filterProps = new HashMap<>();
		filterProps.put(ComponentConstants.COMPONENT_FACTORY, EclipseApi.CorvusApp.Component.FACTORY);
		ComponentFactory appFactory = (ComponentFactory) serviceTrackerHelper(filterProps);
		assertNotNull(appFactory);
		ComponentInstance instance = appFactory.newInstance(new Hashtable<>(props));
		Object service = instance.getInstance();
		assertNotNull(service);
		assertTrue(service instanceof CorvusAppFactoryProvider);

		// Since the app does not require the below services directly test for them differently
		final Map<Class<?>, Boolean> found = new HashMap<>();
		// Needs to stay in sync with app's manually configured PIDs
		found.put(IEditingDomainProvider.class, false);
		found.put(IResourceChangeListener.class, false);

		found.keySet().forEach(clz -> {
			Map<String, Object> filter = new HashMap<>();
			filter.put(EmfApi.TransactionalEditingDomain.Properties.ID, id);
			filter.put(Constants.OBJECTCLASS, clz.getName());
			try {
				Object svc = serviceTrackerHelper(filter);
				assertNotNull(svc);			
				found.put(clz, true);
			} catch (Exception e) {
				fail(e);
			}
		});

		for (Map.Entry<Class<?>, Boolean> entry : found.entrySet()) {
			if (entry.getValue() == false) {
				fail(String.format("Did not find an expected instance of class %s", entry.getKey()));
			}
		}

		instance.dispose();
	}

}

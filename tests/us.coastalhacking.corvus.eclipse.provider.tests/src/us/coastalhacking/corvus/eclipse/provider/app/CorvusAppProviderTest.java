package us.coastalhacking.corvus.eclipse.provider.app;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.emf.transaction.TransactionalEditingDomain.Factory;
import org.eclipse.emf.transaction.TransactionalEditingDomain.Registry;
import org.junit.jupiter.api.Test;
import org.osgi.service.component.ComponentConstants;
import org.osgi.service.component.ComponentFactory;
import org.osgi.service.component.ComponentInstance;

import us.coastalhacking.corvus.eclipse.EclipseApi;
import us.coastalhacking.corvus.emf.EmfApi;
import us.coastalhacking.corvus.emf.ResourceInitializer;
import us.coastalhacking.corvus.test.util.AbstractProjectTest;
import us.coastalhacking.corvus.test.util.TestUtils;

class CorvusAppProviderTest extends AbstractProjectTest {

	public CorvusAppProviderTest() throws Exception {
		super();
	}

	@Test
	void shouldConfigure() throws Exception {
		// Prep
		Hashtable<String, Object> props = new Hashtable<>();
		props.put(EmfApi.ResourceInitializer.Properties.PROJECT, project.getFullPath().toPortableString());
		props.put(EclipseApi.IResourceChangeListener.Properties.MARKER_TYPE, EclipseApi.Marker.BASE_MARKER);
		String transactionId = "test." + getClass().getName();
		props.put(EmfApi.TransactionalEditingDomain.Properties.ID, transactionId);

		// Get factory
		ComponentFactory appFactory = TestUtils.getService(getBundleContext(), ComponentFactory.class, 1250,
				String.format("(%s=%s)", ComponentConstants.COMPONENT_FACTORY, EclipseApi.CorvusApp.Component.FACTORY));
		assertNotNull(appFactory);
		ComponentInstance instance = appFactory.newInstance(props);
		Object service = instance.getInstance();
		assertNotNull(service);
		assertTrue(service instanceof CorvusAppFactoryProvider);

		// Since the app does not require the below services directly test for them differently
		final Map<Class<?>, Boolean> found = new HashMap<>();
		found.put(ResourceInitializer.class, false);
		found.put(Factory.class, false);
		found.put(Registry.class, false);
		found.put(IResourceChangeListener.class, false);

		String filter = String.format("(%s=%s)", EmfApi.TransactionalEditingDomain.Properties.ID, transactionId);
		found.keySet().forEach(clz -> {
			try {
				Object svc = TestUtils.getService(getBundleContext(), clz, 250, filter);
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

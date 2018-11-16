package us.coastalhacking.corvus.emf.provider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain.Factory;
import org.junit.jupiter.api.Test;
import org.osgi.framework.ServiceRegistration;

import us.coastalhacking.corvus.emf.EmfApi;
import us.coastalhacking.corvus.emf.ResourceInitializer;
import us.coastalhacking.corvus.test.util.AbstractProjectTest;
import us.coastalhacking.corvus.test.util.UtilFactory;
import us.coastalhacking.corvus.test.util.UtilPackage;

class CorvusTransactionalFactoryProviderTest extends AbstractProjectTest {

	public CorvusTransactionalFactoryProviderTest() throws Exception {
		super();
	}

	@Test
	void shouldConfigure() throws Exception {
		final String logical = "test:" + getClass().getName();
		final String projectName = project.getFullPath().toPortableString();
		final String physical = "shouldConfigure.xmi";

		ResourceInitializer testInitializer = new ResourceInitializer() {
			@Override
			public String getLogical() {
				return logical;
			}

			@Override
			public String getPhysical() {
				return physical;
			}
			
			@Override
			public EObject getRoot() {
				return UtilFactory.eINSTANCE.createTestRoot();
			}
		};

		// Get the factory first
		Map<String, Object> props = new HashMap<>();
		props.put(EmfApi.ResourceInitializer.Properties.PROJECT, projectName);

		Factory service = configurationHelper(Factory.class,
				EmfApi.CorvusTransactionalFactory.Component.CONFIG_PID, props, timeout);
		assertNotNull(service);

		// Then register the service afterward to test dynamic / greedy OSGi reference binding
		ServiceRegistration<ResourceInitializer> reg = getBundleContext().registerService(ResourceInitializer.class, testInitializer, new Hashtable<>());
		serviceRegistrations.add(reg);
		ResourceInitializer actualInitializer = getBundleContext().getService(reg.getReference());
		assertEquals(testInitializer, actualInitializer);

		TransactionalEditingDomain domain = service.createEditingDomain();
		
		domain.runExclusive(() -> {
			Resource resource = domain.getResourceSet().getResource(URI.createURI(logical), true);
			assertNotNull(resource);
			EObject root = resource.getContents().get(0);
			assertTrue(root.eClass().equals(UtilPackage.Literals.TEST_ROOT));
		});
	}
}

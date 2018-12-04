package us.coastalhacking.corvus.emf.provider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain.Factory;
import org.eclipse.emf.transaction.TransactionalEditingDomain.Registry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.osgi.framework.ServiceRegistration;

import us.coastalhacking.corvus.emf.ResourceInitializer;
import us.coastalhacking.corvus.emf.TransactionIdUtil;
import us.coastalhacking.corvus.test.util.AbstractProjectTest;
import us.coastalhacking.corvus.test.util.UtilFactory;

class CorvusTransactionalRegistryProviderTest extends AbstractProjectTest {

	public CorvusTransactionalRegistryProviderTest() throws Exception {
		super();
	}

	TransactionIdUtil idUtil;
	Map<String, Object> props;
	String id;
	Factory factory;

	final String logical = "test:" + getClass().getName();
	final String physical = "physical.xmi";

	@BeforeEach
	void subBeforeEach() throws Exception {

		ResourceInitializer testInitializer = new ResourceInitializer() {
			@Override
			public String getLogical() {
				return logical;
			}

			@Override
			public String getFilename() {
				return physical;
			}

			@Override
			public EObject getRoot() {
				return UtilFactory.eINSTANCE.createTestRoot();
			}
		};

		// Then register the service afterward to test dynamic / greedy OSGi reference
		// binding
		ServiceRegistration<ResourceInitializer> reg = getBundleContext().registerService(ResourceInitializer.class,
				testInitializer, new Hashtable<>());
		serviceRegistrations.add(reg);
		ResourceInitializer actualInitializer = getBundleContext().getService(reg.getReference());
		assertEquals(testInitializer, actualInitializer);

		idUtil = serviceTrackerHelper(TransactionIdUtil.class);
		assertNotNull(idUtil);
		props = new HashMap<>();
		id = idUtil.getId(project);
		idUtil.putId(props, id);
		factory = serviceTrackerHelper(Factory.class);
		assertNotNull(factory);
	}

	@Test
	void shouldConfigure() throws Exception {

		// Configure registry
		CorvusTransactionalRegistryProvider provider = (CorvusTransactionalRegistryProvider) serviceTrackerHelper(
				Registry.class);
		assertNotNull(provider);

		// Verify
		assertNotNull(provider);
		assertTrue(provider.registries.isEmpty());
		String transactionalId = "this.does.not.exist";
		TransactionalEditingDomain domain = provider.getEditingDomain(transactionalId);
		assertNotNull(domain);
		assertFalse(provider.registries.isEmpty());
		assertEquals(domain, provider.registries.get(transactionalId));

		TransactionalEditingDomain removedDomain = provider.remove(transactionalId);
		assertTrue(provider.registries.isEmpty());
		assertNotNull(removedDomain);
		assertEquals(domain, removedDomain);

		// Simulate clean-up
		provider.add(transactionalId, domain);
		assertFalse(provider.registries.isEmpty());
		provider.deactivate();
		assertTrue(provider.registries.isEmpty());

	}

}

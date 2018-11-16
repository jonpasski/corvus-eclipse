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
import org.junit.jupiter.api.Test;

import us.coastalhacking.corvus.emf.EmfApi;
import us.coastalhacking.corvus.emf.ResourceInitializer;
import us.coastalhacking.corvus.test.util.AbstractProjectTest;
import us.coastalhacking.corvus.test.util.UtilFactory;

class CorvusTransactionalRegistryProviderTest extends AbstractProjectTest {

	public CorvusTransactionalRegistryProviderTest() throws Exception {
		super();
	}

	@Test
	void shouldConfigure() throws Exception {

		final String logical = "test:" + getClass().getName();
		final String projectName = project.getFullPath().toPortableString();
		final String physical = "shouldConfigure.xmi"; //project.getFile("shouldConfigure.xmi").getFullPath().toPortableString();

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

		Map<String, Object> props = new HashMap<>();
		props.put(EmfApi.ResourceInitializer.Properties.PROJECT, projectName);
		serviceRegistrations.add(
				getBundleContext().registerService(ResourceInitializer.class, testInitializer, new Hashtable<>(props)));

		// Configure factory
		configurationHelper(Factory.class,
				EmfApi.CorvusTransactionalFactory.Component.CONFIG_PID, props, timeout);

		// Configure registry
		CorvusTransactionalRegistryProvider provider = (CorvusTransactionalRegistryProvider)configurationHelper(Registry.class,
				EmfApi.CorvusTransactionalRegistry.Component.CONFIG_PID, props, timeout);
		
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

package us.coastalhacking.corvus.emf.provider;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.eclipse.emf.transaction.TransactionalEditingDomain.Factory;
import org.junit.jupiter.api.Test;

import us.coastalhacking.corvus.test.util.AbstractProjectTest;

class CorvusTransactionalFactoryProviderTest extends AbstractProjectTest {

	public CorvusTransactionalFactoryProviderTest() throws Exception {
		super();
	}
	
	@Test
	void shouldConfigure() throws Exception {
		Factory factory = serviceTrackerHelper(Factory.class);
		assertTrue(factory instanceof CorvusTransactionalFactoryProvider);
	}
}

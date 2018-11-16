package us.coastalhacking.corvus.emf.provider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.junit.jupiter.api.Test;

import us.coastalhacking.corvus.emf.EmfApi;
import us.coastalhacking.corvus.emf.ResourceInitializer;
import us.coastalhacking.corvus.semiotics.SemioticsPackage;
import us.coastalhacking.corvus.test.util.AbstractCMTest;

class ResourceInitializerProviderTest extends AbstractCMTest {

	public ResourceInitializerProviderTest() throws Exception {
		super();
	}

	final EClass ROOT = SemioticsPackage.Literals.ROOT;

	void assertService(String logical, EClass literal, Class<? extends ResourceInitializer> providerType)
			throws Exception {
		final Map<String, Object> filterProps = new HashMap<>();
		filterProps.put(EmfApi.ResourceInitializer.Properties.LOGICAL, logical);
		Object service = serviceTrackerHelper(filterProps);
		assertTrue(providerType.isInstance(service));
		ResourceInitializer provider = providerType.cast(service);
		assertEquals(literal, provider.getRoot().eClass());
	}

	@Test
	void shouldConfigureEclipseResources() throws Exception {
		assertService(EmfApi.ResourceInitializer.EclipseResources.Properties.LOGICAL,
				SemioticsPackage.Literals.IWORKSPACE_ROOT, EclipseResourcesInitializerProvider.class);
	}

	@Test
	void shouldConfigureEntryPoint() throws Exception {
		assertService(EmfApi.ResourceInitializer.EntryPoint.Properties.LOGICAL, ROOT,
				EntryPointResourceInitializerProvider.class);
	}
}

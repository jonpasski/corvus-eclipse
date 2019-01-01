package us.coastalhacking.corvus.emf.provider;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.osgi.framework.Filter;
import org.osgi.framework.ServiceReference;

import us.coastalhacking.corvus.emf.EventApi;
import us.coastalhacking.corvus.emf.SupportedTypesFunction;
import us.coastalhacking.corvus.test.util.AbstractCMTest;

class SupportedTypesFunctionProviderTest extends AbstractCMTest {

	public SupportedTypesFunctionProviderTest() throws Exception {
		super();
	}

	SupportedTypesFunctionProvider provider;
	ServiceReference<?> mockRef;
	Map<String, Object> matchMap;

	@BeforeEach
	void subBeforeEach() {
		provider = new SupportedTypesFunctionProvider();
		mockRef = Mockito.mock(ServiceReference.class);
		matchMap = new HashMap<>();

	}

	@Test
	void shouldGetService() throws Exception {
		// Execute
		SupportedTypesFunction service = serviceTrackerHelper(SupportedTypesFunction.class);
		
		// Verify
		Assertions.assertNotNull(service);
	}

	@Test
	void shouldApplySingleSupportedType() throws Exception {
		// Prep
		String expectedType = "foo.bar.biz";
		matchMap.put(EventApi.Properties.SUPPORTED_TYPES, expectedType);
		Mockito.when(mockRef.getProperty(EventApi.Properties.SUPPORTED_TYPES)).thenReturn(expectedType);

		// Execute
		Optional<Filter> result = provider.apply(mockRef);

		// Verify
		Assertions.assertTrue(result.isPresent());
		Assertions.assertTrue(result.get().matches(matchMap));
	}

	@Test
	void shouldApplyMultipleSupportedTypes() throws Exception {
		// Prep
		String expectedType1 = "foo.bar.biz";
		String expectedType2 = "biz.baz.boo";
		String[] expectedTypes = new String[] {expectedType1, expectedType2};
		matchMap.put(EventApi.Properties.SUPPORTED_TYPES, expectedTypes);
		Mockito.when(mockRef.getProperty(EventApi.Properties.SUPPORTED_TYPES)).thenReturn(expectedTypes);

		// Execute
		Optional<Filter> result = provider.apply(mockRef);

		// Verify
		Assertions.assertTrue(result.isPresent());
		Assertions.assertTrue(result.get().matches(matchMap));
	}
}

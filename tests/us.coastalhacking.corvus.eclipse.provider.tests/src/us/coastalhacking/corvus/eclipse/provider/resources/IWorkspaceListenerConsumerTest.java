package us.coastalhacking.corvus.eclipse.provider.resources;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IResourceChangeListener;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.osgi.framework.Constants;

import us.coastalhacking.corvus.emf.MarkerApi;
import us.coastalhacking.corvus.test.util.AbstractProjectTest;

class IWorkspaceListenerConsumerTest extends AbstractProjectTest {

	public IWorkspaceListenerConsumerTest() throws Exception {
		super();
	}

	@BeforeEach
	void subBeforeEach() {
	}

	@Test
	void shouldGetService() throws Exception {
		// Prep
		Map<String, Object> filterProps = new HashMap<>();
		filterProps.put(Constants.OBJECTCLASS, new String[] { IResourceChangeListener.class.getName() });
		filterProps.put(MarkerApi.Properties.TYPE, MarkerApi.Base.NAME);

		// Execute & verify
		Object service = serviceTrackerHelper(filterProps);

		// Further verify
		Assertions.assertTrue(service instanceof ResourceChangeEventPublisherProvider);

	}

	@Test
	void shouldSetResourceChangeListener() {
		fail("Not yet implemented");
	}

	@Test
	void shouldUnsetResourceChangeListener() {
		fail("Not yet implemented");
	}

}

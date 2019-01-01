package us.coastalhacking.corvus.eclipse.provider.resources;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.osgi.framework.Constants;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

import us.coastalhacking.corvus.emf.EventApi;
import us.coastalhacking.corvus.emf.EventFunction;
import us.coastalhacking.corvus.emf.MarkerApi;
import us.coastalhacking.corvus.test.util.AbstractCMTest;

class ResourceChangeEventPublisherProviderTest extends AbstractCMTest {

	public ResourceChangeEventPublisherProviderTest() throws Exception {
		super();
	}

	ResourceChangeEventPublisherProvider provider;
	final String expectedType = "expectedType";
	Map<String, Object> props;
	IResourceChangeEvent mockResourceChangeEvent;
	IMarkerDelta mockMarkerDelta;
	EventFunction<IMarkerDelta> mockEventFunc;
	Event mockEvent;
	Optional<Event> maybeEvent;
	EventAdmin mockEventAdmin;

	@SuppressWarnings("unchecked")
	@BeforeEach
	void subBeforeEach() throws Exception {
		// Verify OSGi dependencies
		serviceTrackerHelper(EventAdmin.class);
		Map<String, Object> filterProps = new HashMap<>();
		filterProps.put(Constants.OBJECTCLASS, EventFunction.class.getName());
		filterProps.put(EventApi.Properties.SUPPORTED_TYPES, IMarkerDelta.class.getName());
		serviceTrackerHelper(filterProps);

		provider = new ResourceChangeEventPublisherProvider();
		props = new HashMap<>();
		mockResourceChangeEvent = Mockito.mock(IResourceChangeEvent.class);
		mockMarkerDelta = Mockito.mock(IMarkerDelta.class);
		Mockito.when(mockResourceChangeEvent.findMarkerDeltas(expectedType, true))
				.thenReturn(new IMarkerDelta[] { mockMarkerDelta });
		mockEventFunc = Mockito.mock(EventFunction.class);
		mockEvent = new Event("corvus/ADD", Collections.emptyMap());
		maybeEvent = Optional.of(mockEvent);
		Mockito.when(mockEventFunc.apply(mockMarkerDelta)).thenReturn(maybeEvent);
		mockEventAdmin = Mockito.mock(EventAdmin.class);
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
	void shouldActivate() {
		// Prep
		Assertions.assertNull(provider.markerType);
		props.put(MarkerApi.Properties.TYPE, expectedType);

		// Execute
		provider.activate(props);

		// Verify
		Assertions.assertEquals(expectedType, provider.markerType);
	}

	@Test
	void shouldResourceChanged() {
		// Prep
		provider.markerType = expectedType;
		provider.eventFunc = mockEventFunc;
		provider.eventAdmin = mockEventAdmin;

		// Execute
		provider.resourceChanged(mockResourceChangeEvent);

		// Verify
		Mockito.verify(mockEventAdmin, Mockito.times(1)).postEvent(Mockito.eq(mockEvent));
	}
}

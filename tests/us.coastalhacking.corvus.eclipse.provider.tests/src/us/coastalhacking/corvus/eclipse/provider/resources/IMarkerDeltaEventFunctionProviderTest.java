package us.coastalhacking.corvus.eclipse.provider.resources;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.IPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.osgi.framework.Constants;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventProperties;

import us.coastalhacking.corvus.eclipse.EclipseEventApi;
import us.coastalhacking.corvus.emf.EventApi;
import us.coastalhacking.corvus.emf.EventFunction;
import us.coastalhacking.corvus.emf.MarkerApi;
import us.coastalhacking.corvus.test.util.AbstractCMTest;

class IMarkerDeltaEventFunctionProviderTest extends AbstractCMTest {

	public IMarkerDeltaEventFunctionProviderTest() throws Exception {
		super();
	}

	IMarkerDeltaEventFunctionProvider provider;
	Map<String, Object> props;
	IMarkerDelta mockDelta;
	Map<String, Object> attributes;
	final String markerType = "foo.bar.type";
	IResource mockResource;
	IPath mockPath;
	final String mockFullPath = "/someProject/some/resource.xyz";

	@BeforeEach
	void subBeforeEach() {
		provider = new IMarkerDeltaEventFunctionProvider();
		props = new HashMap<>();
		mockDelta = Mockito.mock(IMarkerDelta.class);
		attributes = new HashMap<>();
		Mockito.when(mockDelta.getAttributes()).thenReturn(attributes);
		Mockito.when(mockDelta.getType()).thenReturn(markerType);
		mockPath = Mockito.mock(IPath.class);
		Mockito.when(mockPath.toString()).thenReturn(mockFullPath);
		mockResource = Mockito.mock(IResource.class);
		Mockito.when(mockDelta.getResource()).thenReturn(mockResource);
		Mockito.when(mockResource.getFullPath()).thenReturn(mockPath);
		// getResource().getFullPath().toString(),
	}

	@Test
	void shouldGetService() throws Exception {
		// Prep
		Map<String, Object> filterProps = new HashMap<>();
		filterProps.put(Constants.OBJECTCLASS, EventFunction.class.getName());
		filterProps.put(EventApi.Properties.SUPPORTED_TYPES, new String[] { IMarkerDelta.class.getName() });

		// Execute & verify
		serviceTrackerHelper(filterProps);
	}

	@Test
	void shouldActivate() {
		// Prep
		Assertions.assertNull(provider.props);

		// Execute
		provider.activate(props);

		// Verify
		Assertions.assertEquals(props, provider.props);
	}


	@Test
	void shouldApplyAdd() {
		// Prep
		provider.props = props;
		Mockito.when(mockDelta.getKind()).thenReturn(IResourceDelta.ADDED);
		
		// Execute
		Optional<Event> maybeEvent = provider.apply(mockDelta);
		
		// Verify
		Assertions.assertTrue(maybeEvent.isPresent());
		Event actualEvent = maybeEvent.get();
		Assertions.assertEquals(EclipseEventApi.Topic.IRESOURCE__IMARKER_ADD, actualEvent.getTopic());
	}
	
	@Test
	void shouldApplyRemove() {
		// Prep
		provider.props = props;
		Mockito.when(mockDelta.getKind()).thenReturn(IResourceDelta.REMOVED);
		
		// Execute
		Optional<Event> maybeEvent = provider.apply(mockDelta);
		
		// Verify
		Assertions.assertTrue(maybeEvent.isPresent());
		Event actualEvent = maybeEvent.get();
		Assertions.assertEquals(EclipseEventApi.Topic.IRESOURCE__IMARKER_REMOVE, actualEvent.getTopic());
	}

	@Test
	void shouldCreateEventProperties() {
		// Prep
		String attrKey = "expectedAttrKey";
		String attrValue = "expectedAttrValue";
		attributes.put(attrKey, attrValue);
		String propKey = "propKey";
		String propValue = "propValue";
		props.put(propKey, propValue);
		
		// Execute
		EventProperties eventProperties = provider.createEventProperties(props, mockDelta);
		
		// Verify
		@SuppressWarnings("unchecked") Map<String, Object> actualAttributes = (Map<String, Object>)eventProperties.get(MarkerApi.Properties.ATTRIBUTES);
		Assertions.assertNotNull(actualAttributes);
		Assertions.assertTrue(actualAttributes.containsKey(attrKey));
		Assertions.assertEquals(attrValue, actualAttributes.get(attrKey));
		Assertions.assertTrue(eventProperties.containsKey(propKey));
		Assertions.assertEquals(propValue, eventProperties.get(propKey));
		Assertions.assertEquals(markerType, eventProperties.get(MarkerApi.Properties.TYPE));
		Assertions.assertEquals("platform:/resource" + mockFullPath, eventProperties.get(MarkerApi.Properties.URI));
	}
}

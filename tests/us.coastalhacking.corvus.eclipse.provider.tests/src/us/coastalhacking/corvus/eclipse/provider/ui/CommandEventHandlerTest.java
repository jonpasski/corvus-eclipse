package us.coastalhacking.corvus.eclipse.provider.ui;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Future;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.junit.jupiter.api.Test;
import org.osgi.framework.Constants;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

import us.coastalhacking.corvus.eclipse.MarkerSupport;
import us.coastalhacking.corvus.eclipse.MarkerSupport.Coordinate;
import us.coastalhacking.corvus.test.util.AbstractCMTest;
import us.coastalhacking.corvus.eclipse.EclipseApi;

class CommandEventHandlerTest extends AbstractCMTest {

	public CommandEventHandlerTest() throws Exception {
		super();
	}

	@Test
	void shouldConfigure() throws Exception {
		// Verify dependencies
		MarkerSupport markerSupport = serviceTrackerHelper(MarkerSupport.class);
		assertNotNull(markerSupport);
		
		Map<String, Object> props = new HashMap<>();
		props.put(EventConstants.EVENT_TOPIC, EclipseApi.Event.Marker.TOPIC_ALL);
		props.put(Constants.OBJECTCLASS, EventHandler.class.getName());
		Object object = serviceTrackerHelper(props);
		assertNotNull(object);
		assertTrue(object instanceof EventHandler);
	}
	
	@Test
	void shouldHandleEvent() throws Exception {

		// Prep & mock
		Coordinate coordinate = mock(Coordinate.class);
		Optional<Coordinate> maybeCoordinate = Optional.of(coordinate);
		MarkerSupport support = mock(MarkerSupport.class);
		when(support.getSelectedCoordinate()).thenReturn(maybeCoordinate);
		IPath path = mock(IPath.class);
		String fullPath = "/project/resource";
		when(path.toPortableString()).thenReturn(fullPath);
		IResource resource = mock(IResource.class);
		when(resource.getFullPath()).thenReturn(path);
		@SuppressWarnings("unchecked")
		Future<IResource> future = mock(Future.class);
		when(future.get()).thenReturn(resource);
		when(support.getActiveEditorResource()).thenReturn(future);
		Map<String, Object> map = new HashMap<>();
		String markerKey = "markerKey";
		map.put(EclipseApi.Event.Marker.PROP_TYPE, markerKey);
		CommandEventHandler eventHandler = new CommandEventHandler();
		eventHandler.markerSupport = support;

		Event event = new Event(EclipseApi.Event.Marker.TOPIC_ADD, map);

		// Call & verify
		eventHandler.handleEvent(event);		
		verify(support, times(1)).createMarker(coordinate, fullPath, markerKey);
	}

}

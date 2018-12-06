package us.coastalhacking.corvus.eclipse.provider.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.osgi.framework.Constants;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.osgi.service.log.Logger;
import org.osgi.service.log.LoggerFactory;

import us.coastalhacking.corvus.eclipse.EclipseApi;
import us.coastalhacking.corvus.eclipse.MarkerSupport;
import us.coastalhacking.corvus.eclipse.MarkerSupport.Coordinate;
import us.coastalhacking.corvus.test.util.AbstractProjectTest;

class CommandEventHandlerTest extends AbstractProjectTest {

	public CommandEventHandlerTest() throws Exception {
		super();
	}

	// Mocks
	//
	static final String mockMarkerKey = "mockKey";
	Coordinate mockCoordinate = mock(Coordinate.class);
	Optional<Coordinate> mockMaybeCoordinate;
	MarkerSupport mockSupport;
	Event mockEvent;
	Logger mockLogger;
	LoggerFactory mockLoggerFactory;
	Map<String, Object> mockProps;
	CommandEventHandler provider;

	@BeforeEach
	void subBeforeEach() throws Exception {
		provider = new CommandEventHandler();

		mockCoordinate = mock(Coordinate.class);
		mockMaybeCoordinate = Optional.of(mockCoordinate);
		mockSupport = mock(MarkerSupport.class);
		provider.markerSupport = mockSupport;

		mockLogger = mock(Logger.class);
		mockLoggerFactory = mock(LoggerFactory.class);
		when(mockLoggerFactory.getLogger(any(Class.class))).thenReturn(mockLogger);

		mockProps = new HashMap<>();
		mockProps.put(EclipseApi.Event.Marker.PROP_TYPE, mockMarkerKey);
		mockEvent = new Event(EclipseApi.Event.Marker.TOPIC_ADD, mockProps);

		provider.loggerFactory = mockLoggerFactory;
		assertNull(provider.logger);
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

	@Test
	void shouldNotHandleEvent() throws Exception {
		// Prep
		Exception mockException = mock(Exception.class);
		when(mockSupport.getSelectedCoordinate()).thenThrow(mockException);
		ArgumentCaptor<Exception> exceptionCaptor = ArgumentCaptor.forClass(Exception.class);
		provider.logger = mockLogger;

		// Execute
		provider.handleEvent(mockEvent);

		// Verify
		verify(mockLogger, times(1)).warn(anyString(), eq(mockMarkerKey), exceptionCaptor.capture());
		assertEquals(mockException, exceptionCaptor.getValue());
	}

	@Test
	void shouldActivate() {
		// Execute
		provider.activate();

		// Verify
		assertNotNull(provider.logger);
	}

}

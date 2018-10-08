package us.coastalhacking.corvus.eclipse.ui.impl;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.osgi.service.event.Event;

import us.coastalhacking.corvus.eclipse.ui.api.MarkerSupport;
import us.coastalhacking.corvus.eclipse.ui.impl.CommandEventHandler;
import us.coastalhacking.corvus.eclipse.ui.api.EclipseUiApi;

public class CommandEventHandlerTest {

	@Test
	public void shouldHandleAdd() {
		Map<String, Object> map = new HashMap<>();
		map.put(EclipseUiApi.Event.Marker.PROP_TYPE, "");
		Event event = new Event(EclipseUiApi.Event.Marker.TOPIC_ADD, map);
		MarkerSupport markerSupport = mock(MarkerSupport.class);
		CommandEventHandler handler = new CommandEventHandler();
		handler.markerSupport = markerSupport;
		
		handler.handleEvent(event);
		
		verify(markerSupport, times(1)).createMarker(any());
	}

}

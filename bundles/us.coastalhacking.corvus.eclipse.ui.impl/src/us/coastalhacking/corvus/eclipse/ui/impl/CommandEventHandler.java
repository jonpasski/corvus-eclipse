package us.coastalhacking.corvus.eclipse.ui.impl;

import org.eclipse.e4.ui.workbench.IWorkbench;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

import us.coastalhacking.corvus.eclipse.ui.api.MarkerSupport;
import us.coastalhacking.corvus.eclipse.ui.api.EclipseUiApi;

@Component(service = EventHandler.class, property = {
		EventConstants.EVENT_TOPIC + "=" + EclipseUiApi.Event.Marker.TOPIC_ALL })
public class CommandEventHandler implements EventHandler {

	@Reference
	IWorkbench workbench;

	@Reference
	MarkerSupport markerSupport;

	@Override
	public void handleEvent(Event event) {
		final String markerKey = (String) event.getProperty(EclipseUiApi.Event.Marker.PROP_TYPE);

		switch (event.getTopic()) {
		case EclipseUiApi.Event.Marker.TOPIC_ADD:
			markerSupport.createMarker(markerKey);
			break;
		default:
			throw new UnsupportedOperationException("Invalid topic: " + event.getTopic());			
		}
	}

}

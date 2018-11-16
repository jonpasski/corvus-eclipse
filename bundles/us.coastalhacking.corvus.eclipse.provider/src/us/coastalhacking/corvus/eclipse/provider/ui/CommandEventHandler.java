package us.coastalhacking.corvus.eclipse.provider.ui;

import org.eclipse.core.resources.IResource;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

import us.coastalhacking.corvus.eclipse.MarkerSupport;
import us.coastalhacking.corvus.eclipse.MarkerSupport.Coordinate;
import us.coastalhacking.corvus.eclipse.EclipseApi;

@Component(service = EventHandler.class, property = {
		EventConstants.EVENT_TOPIC + "=" + EclipseApi.Event.Marker.TOPIC_ALL })
public class CommandEventHandler implements EventHandler {

	@Reference
	MarkerSupport markerSupport;

	@Override
	public void handleEvent(Event event) {
		final String markerKey = (String) event.getProperty(EclipseApi.Event.Marker.PROP_TYPE);

		Coordinate coordinate;
		try {
			coordinate = markerSupport.getSelectedCoordinate().get();
			IResource resource = markerSupport.getActiveEditorResource().get();
			switch (event.getTopic()) {
			case EclipseApi.Event.Marker.TOPIC_ADD:
				markerSupport.createMarker(coordinate, resource.getFullPath().toPortableString(), markerKey);
				break;
			default:
				throw new UnsupportedOperationException("Invalid topic: " + event.getTopic());			
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
	}

}

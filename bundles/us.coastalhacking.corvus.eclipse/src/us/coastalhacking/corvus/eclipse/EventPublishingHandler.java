package us.coastalhacking.corvus.eclipse;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

public class EventPublishingHandler {

	@Inject
	private EventAdmin eventAdmin;

	@Execute
	public void execute(@Named(EclipseApi.Fragment.CommandParameter.MARKER) String markerType, @Named(EclipseApi.Fragment.CommandParameter.ACTION) String action) {
		final Map<String, Object> props = new HashMap<>();
		props.put(EclipseApi.Event.Marker.PROP_TYPE, markerType);

		String topic = "";
		switch (action) {
		case EclipseApi.Fragment.CommandParameter.ACTION_ADD:
			topic = EclipseApi.Event.Marker.TOPIC_ADD;
			break;
		default:
			throw new IllegalArgumentException("Unsupported value: " + action);
		}
		eventAdmin.postEvent(new Event(topic, props));
	}
}

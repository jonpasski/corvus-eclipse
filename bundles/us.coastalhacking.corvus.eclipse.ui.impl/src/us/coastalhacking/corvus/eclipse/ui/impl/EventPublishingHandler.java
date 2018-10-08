package us.coastalhacking.corvus.eclipse.ui.impl;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

import us.coastalhacking.corvus.eclipse.ui.api.EclipseUiApi;

public class EventPublishingHandler {

	@Inject
	private EventAdmin eventAdmin;

	@Execute
	public void execute(@Named(EclipseUiApi.CommandParameter.MARKER) String markerType, @Named(EclipseUiApi.CommandParameter.ACTION) String action) {
		final Map<String, Object> props = new HashMap<>();
		props.put(EclipseUiApi.Event.Marker.PROP_TYPE, markerType);

		String topic = "";
		switch (action) {
		case EclipseUiApi.CommandParameter.ACTION_ADD:
			topic = EclipseUiApi.Event.Marker.TOPIC_ADD;
			break;
		default:
			throw new IllegalArgumentException("Unsupported value: " + action);
		}
		eventAdmin.postEvent(new Event(topic, props));
	}
}

package us.coastalhacking.corvus.eclipse.provider.resources;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.emf.common.util.URI;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventProperties;

import us.coastalhacking.corvus.eclipse.EclipseApi;
import us.coastalhacking.corvus.eclipse.EclipseEventApi;
import us.coastalhacking.corvus.emf.EventFunction;
import us.coastalhacking.corvus.emf.MarkerApi;

@Component(service = EventFunction.class, configurationPid = EclipseApi.IMarkerDeltaEventFunction.CONFIG_PID, configurationPolicy = ConfigurationPolicy.REQUIRE, immediate = true, property = {
		ResourceChangeEventPublisherProvider.SUPPORTED_TYPES__IMARKER_DELTA })
public class IMarkerDeltaEventFunctionProvider implements EventFunction<IMarkerDelta> {

	Map<String, Object> props;

	@Activate
	void activate(Map<String, Object> props) {
		this.props = props;
	}

	@Override
	public Optional<Event> apply(IMarkerDelta delta) {
		Event result = null;
		switch (delta.getKind()) {
		case IResourceDelta.ADDED: {
			result = new Event(EclipseEventApi.Topic.IRESOURCE__IMARKER_ADD, createEventProperties(props, delta));
			break;
		}
		case IResourceDelta.REMOVED: {
			result = new Event(EclipseEventApi.Topic.IRESOURCE__IMARKER_REMOVE, createEventProperties(props, delta));
			break;
		}
		}
		return Optional.ofNullable(result);
	}

	EventProperties createEventProperties(Map<String, Object> properties, IMarkerDelta delta) {
		// Ensure the supported types property is added to the event properties
		Map<String, Object> map = new HashMap<>(properties);
		map.put(MarkerApi.Properties.ATTRIBUTES, delta.getAttributes());
		map.put(MarkerApi.Properties.TYPE, delta.getType());
		map.put(MarkerApi.Properties.URI,
				URI.createPlatformResourceURI(delta.getResource().getFullPath().toString(), true).toString());
		return new EventProperties(map);
	}

}

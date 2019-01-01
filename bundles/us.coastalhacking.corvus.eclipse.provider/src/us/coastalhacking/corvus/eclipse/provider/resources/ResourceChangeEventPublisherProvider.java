package us.coastalhacking.corvus.eclipse.provider.resources;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.EventAdmin;

import us.coastalhacking.corvus.eclipse.EclipseApi;
import us.coastalhacking.corvus.emf.EventApi;
import us.coastalhacking.corvus.emf.EventFunction;
import us.coastalhacking.corvus.emf.MarkerApi;

// Using a singleton scope since IWorkspace is also a singleton
// Otherwise, two or more providers may be configured via different bundles
// 
@Component(service = IResourceChangeListener.class, configurationPid = EclipseApi.IResourceChangeListener.Component.CONFIG_PID, configurationPolicy = ConfigurationPolicy.REQUIRE, immediate = true, property = {
		ResourceChangeEventPublisherProvider.MARKER_TYPE__BASE })
public class ResourceChangeEventPublisherProvider implements IResourceChangeListener {

	// TODO: move these to a constants interface
	static final String MARKER_TYPE__BASE = MarkerApi.Properties.TYPE + "=" + MarkerApi.Base.NAME;
	static final String SUPPORTED_TYPES__IMARKER_DELTA = EventApi.Properties.SUPPORTED_TYPES
			+ "=org.eclipse.core.resources.IMarkerDelta";

	String markerType;

	@Reference
	EventAdmin eventAdmin;

	@Reference(name = EventApi.Reference.EVENT_FUNCTION, target = "("
			+ ResourceChangeEventPublisherProvider.SUPPORTED_TYPES__IMARKER_DELTA + ")")
	EventFunction<IMarkerDelta> eventFunc;

	@Activate
	void activate(Map<String, Object> props) {
		markerType = props.get(MarkerApi.Properties.TYPE).toString();
	}

	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		Arrays.stream(event.findMarkerDeltas(markerType, true))
			.map(eventFunc)
			.filter(Optional::isPresent)
			.map(Optional::get)
			.forEach(e 
				-> eventAdmin.postEvent(e));
	}
}

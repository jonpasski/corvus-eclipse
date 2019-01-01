package us.coastalhacking.corvus.eclipse;

import org.eclipse.core.resources.IResource;

import us.coastalhacking.corvus.emf.EventApi;
import us.coastalhacking.corvus.emf.EventApi.EventType;

public interface EclipseEventApi {

	interface Topic {
		String IRESOURCE__IMARKER_ADD = EventApi.topic(IResource.class.getName(), "marker", EventType.ADD);
		String IRESOURCE__IMARKER_REMOVE = EventApi.topic(IResource.class.getName(), "marker", EventType.REMOVE);
	}
}

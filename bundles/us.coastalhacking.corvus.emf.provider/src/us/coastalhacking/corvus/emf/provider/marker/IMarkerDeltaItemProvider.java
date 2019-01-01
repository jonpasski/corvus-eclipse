package us.coastalhacking.corvus.emf.provider.marker;

import java.net.URISyntaxException;
import java.util.Optional;

//import org.apache.http.client.utils.URIBuilder;
import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;

import us.coastalhacking.corvus.emf.ILocationItemProvider;
import us.coastalhacking.corvus.emf.INotificationItemProvider;
import us.coastalhacking.corvus.emf.MarkerApi;
import us.coastalhacking.corvus.emf.MarkerApi.Marker.Base;

public class IMarkerDeltaItemProvider extends ItemProviderAdapter
		implements IChangeNotifier, ILocationItemProvider, INotificationItemProvider {

	public IMarkerDeltaItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	public Optional<Integer> getStartCharacter(Object object) {
		IMarkerDelta delta = (IMarkerDelta) object;
		return Optional.ofNullable((Integer) delta.getAttribute(Base.START_CHAR));
	}

	@Override
	public Optional<Integer> getStartLine(Object object) {
		IMarkerDelta delta = (IMarkerDelta) object;
		// Convert from 1-relative to 0-relative
		Integer line = (Integer) delta.getAttribute(Base.START_LINE);
		if (line != null) {
			line--;
		}
		return Optional.ofNullable(line);
	}

	@Override
	public Optional<Integer> getEndCharacter(Object object) {
		IMarkerDelta delta = (IMarkerDelta) object;
		return Optional.ofNullable((Integer) delta.getAttribute(Base.END_CHAR));
	}

	@Override
	public Optional<Integer> getEndLine(Object object) {
		IMarkerDelta delta = (IMarkerDelta) object;
		// Convert from 1-relative to 0-relative
		Integer line = (Integer) delta.getAttribute(Base.END_LINE);
		if (line != null) {
			line--;
		}
		return Optional.ofNullable(line);
	}

	@Override
	public Optional<String> getDocumentUri(Object object) {
		IMarkerDelta delta = (IMarkerDelta) object;
		IResource resource = delta.getResource();
		String fullPath = resource.getFullPath().toString();
		URI resourceUri = URI.createPlatformResourceURI(fullPath, true);

		Optional<String> result = Optional.empty();
		// FIXME: commented out to remove apache dep
//		try {
//			URIBuilder builder = new URIBuilder(resourceUri.toString());
//			String markerType = delta.getType();
//			builder.addParameter(MarkerApi.DocumentUri.PARAM_TYPE, markerType);
//			result = Optional.of(builder.build().toString());
//		} catch (URISyntaxException e) {
//			// gobble
//		}
		return result;
	}

	@Override
	public Optional<Integer> getEventType(Object object) {
		IMarkerDelta delta = (IMarkerDelta) object;
		switch (delta.getKind()) {
		case IResourceDelta.ADDED:
			return Optional.of(Notification.ADD);
		case IResourceDelta.REMOVED:
			return Optional.of(Notification.REMOVE);
		}
		return Optional.empty();
	}

	@Override
	public Object getOldValue(Object object) {
		IMarkerDelta delta = (IMarkerDelta) object;
		switch (delta.getKind()) {
		case IResourceDelta.REMOVED:
			return getRootAdapterFactory().adapt(delta, ILocationItemProvider.class);
		}
		return null;
	}

	@Override
	public Object getNewValue(Object object) {
		IMarkerDelta delta = (IMarkerDelta) object;
		switch (delta.getKind()) {
		case IResourceDelta.ADDED:
			return getRootAdapterFactory().adapt(delta, ILocationItemProvider.class);
		}
		return null;
	}

//	@Override
//	public Notification getNotification(Object object) {
//		IMarkerDelta delta = (IMarkerDelta) object;
//
//		Object adapter = getRootAdapterFactory().adapt(delta, ILocationItemProvider.class);
//		Notification result = null;
//
//		try {
//			if (adapter instanceof ILocationItemProvider) {
//				ILocationItemProvider lp = (ILocationItemProvider) adapter;
//					
//				class LocationNotification extends NotificationImpl {
//	
//					public LocationNotification(int eventType, Object oldValue, Object newValue) {
//						super(eventType, oldValue, newValue);
//					}
//	
//					@Override
//					public int getFeatureID(Class<?> expectedClass) {
//						return ILocationItemProvider.LOCATION__URI_RANGE;
//					}
//	
//					@Override
//					public Object getNotifier() {
//						return documentUri;
//					}
//				}
//	
//				IMarkerDelta delta = (IMarkerDelta) object;
//				switch (delta.getKind()) {
//				// TODO: IResourceDelta.CHANGED
//				case IResourceDelta.ADDED:
//					result = new LocationNotification(Notification.ADD, null, range);
//					break;
//				case IResourceDelta.REMOVED:
//					result = new LocationNotification(Notification.REMOVE, range, null);
//					break;
//				}
//			}
//		} catch (NoSuchElementException e) {
//			// gobble Optional runtime exception
//		}
//		return result;
//	}
}

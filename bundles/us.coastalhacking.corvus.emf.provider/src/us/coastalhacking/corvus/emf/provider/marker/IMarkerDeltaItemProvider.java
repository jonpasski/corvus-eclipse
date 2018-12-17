package us.coastalhacking.corvus.emf.provider.marker;

import java.net.URISyntaxException;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.apache.http.client.utils.URIBuilder;
import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;

import us.coastalhacking.corvus.emf.ILocationItemProvider;
import us.coastalhacking.corvus.emf.NotificationItemProvider;
import us.coastalhacking.corvus.emf.MarkerApi;
import us.coastalhacking.corvus.emf.MarkerApi.Marker.Base;
import us.coastalhacking.corvus.emf.lsp.DocumentUri;
import us.coastalhacking.corvus.emf.provider.lsp.ImmutableDocumentUri;
import us.coastalhacking.corvus.emf.provider.lsp.ImmutableRange;

public class IMarkerDeltaItemProvider extends ItemProviderAdapter
		implements IChangeNotifier, NotificationItemProvider,  ILocationItemProvider {

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
		try {
			URIBuilder builder = new URIBuilder(resourceUri.toString());
			String markerType = delta.getType();
			builder.addParameter(MarkerApi.DocumentUri.PARAM_TYPE, markerType);
			result = Optional.of(builder.build().toString()); 
		} catch (URISyntaxException e) {
			// gobble
		}
		return result;
	}

	@Override
	public Notification getNotification(Object object) {
		Object adapter = getRootAdapterFactory().adapt(object, ILocationItemProvider.class);
		Notification result = null;

		try {
			if (adapter instanceof ILocationItemProvider) {
				ILocationItemProvider lp = (ILocationItemProvider) adapter;
				ImmutableRange<Integer> range = new ImmutableRange.ImmutableInteger(lp.getStartCharacter(object).get(), lp.getStartLine(object).get(),
						lp.getEndCharacter(object).get(), lp.getEndLine(object).get());
	
				ImmutableDocumentUri documentUri = new ImmutableDocumentUri(lp.getDocumentUri(object).get(), range);
	
				class DocumentUriNotification extends NotificationImpl {
	
					public DocumentUriNotification(int eventType, Object oldValue, Object newValue) {
						super(eventType, oldValue, newValue);
					}
	
					@Override
					public int getFeatureID(Class<?> expectedClass) {
						return DocumentUri.DOCUMENT_URI__RANGE;
					}
	
					@Override
					public Object getNotifier() {
						return documentUri;
					}
				}
	
				IMarkerDelta delta = (IMarkerDelta) object;
				switch (delta.getKind()) {
				// TODO: IResourceDelta.CHANGED
				case IResourceDelta.ADDED:
					result = new DocumentUriNotification(Notification.ADD, null, range);
					break;
				case IResourceDelta.REMOVED:
					result = new DocumentUriNotification(Notification.REMOVE, range, null);
					break;
				}
			}
		} catch (NoSuchElementException e) {
			// gobble Optional runtime exception
		}
		return result;
	}
}

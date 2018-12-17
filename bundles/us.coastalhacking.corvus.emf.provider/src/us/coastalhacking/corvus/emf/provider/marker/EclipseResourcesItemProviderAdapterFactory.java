package us.coastalhacking.corvus.emf.provider.marker;

import java.util.Collection;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

import us.coastalhacking.corvus.emf.ILocationItemProvider;
import us.coastalhacking.corvus.emf.NotificationItemProvider;
import us.coastalhacking.corvus.emf.lsp.DocumentUri;
import us.coastalhacking.corvus.emf.provider.ItemProviderAdapterFactoryBase;

// TODO: Whiteboard as a thread-safe AdapterFactory so the IEditingDomainProvider can add to its composed
public class EclipseResourcesItemProviderAdapterFactory extends ItemProviderAdapterFactoryBase
		implements IResourceChangeListener {

	protected static Package eclipseResourcesPackage = IResource.class.getPackage();

	protected IMarkerItemProvider iMarkerItemProvider;
	protected IResourceItemProvider iResourceItemProvider;
	protected IMarkerDeltaItemProvider iMarkerDeltaItemProvider;

	public EclipseResourcesItemProviderAdapterFactory() {
		// Minimal needed
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		// TODO: test me!
		supportedTypes.add(NotificationItemProvider.class);
		supportedTypes.add(ILocationItemProvider.class);

		iMarkerItemProvider = new IMarkerItemProvider(this);
		iResourceItemProvider = new IResourceItemProvider(this);
		iMarkerDeltaItemProvider = new IMarkerDeltaItemProvider(this);
	}

	// IResourceChangeListener
	//
	// TODO: test me!
	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		Object adapter = getRootAdapterFactory().adapt(event, IEditingDomainItemProvider.class);
		if (adapter instanceof IEditingDomainItemProvider) {
			IEditingDomainItemProvider editingDomainProvider = (IEditingDomainItemProvider) adapter;
			Collection<?> children = editingDomainProvider.getChildren(event);

			for (Object child : children) {
				Object notificationAdapter = getRootAdapterFactory().adapt(event, NotificationItemProvider.class);

				if (notificationAdapter instanceof NotificationItemProvider) {
					NotificationItemProvider notificationProvider = (NotificationItemProvider) notificationAdapter;
					Object maybeNotification = notificationProvider.getNotification(child);

					if (maybeNotification instanceof Notification) {
						Notification notification = (Notification) maybeNotification;

						// Ranges are analogous to markers
						if (notification.getFeatureID(DocumentUri.class) == DocumentUri.DOCUMENT_URI__RANGE
								&& notification.getNotifier() instanceof DocumentUri) {
							iMarkerDeltaItemProvider.fireNotifyChanged(notification);
						}
					}
				}
			}
		}
	}

	// IAdapterFactor
	//
	@Override
	public boolean isFactoryForType(Object type) {
		// TODO: Tested?
		return type == eclipseResourcesPackage ||
				// TODO: Tested?
				type instanceof IMarker || type instanceof IResource ||
				// TODO: test
				type instanceof IMarkerDelta || type instanceof IResourceChangeEvent
				|| super.isFactoryForType(type);
	}

	@Override
	protected Object resolve(Object object, Object type) {
		if (object instanceof IMarker) {
			return createIMarkerAdapter();
		} else if (object instanceof IResource) {
			return createIResourceAdapter();
		} else if (object instanceof IMarkerDelta) {
			return createIMarkerDeltaAdapter();
		}
		return super.resolve(object, type);
	}

	protected Adapter createIMarkerAdapter() {
		return iMarkerItemProvider;
	}

	protected Adapter createIResourceAdapter() {
		return iResourceItemProvider;
	}

	protected Adapter createIMarkerDeltaAdapter() {
		return iMarkerDeltaItemProvider;
	}

}

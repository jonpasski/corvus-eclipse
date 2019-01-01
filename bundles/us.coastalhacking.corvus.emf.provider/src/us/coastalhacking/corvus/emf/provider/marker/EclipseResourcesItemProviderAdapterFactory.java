package us.coastalhacking.corvus.emf.provider.marker;

import java.util.Collection;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

import us.coastalhacking.corvus.emf.ILocationItemProvider;
import us.coastalhacking.corvus.emf.INotificationItemProvider;
import us.coastalhacking.corvus.emf.provider.ItemProviderAdapterFactoryBase;

// TODO: Whiteboard as a thread-safe AdapterFactory so the IEditingDomainProvider can add to its composed
public class EclipseResourcesItemProviderAdapterFactory extends ItemProviderAdapterFactoryBase
		implements IResourceChangeListener {

	protected static Package eclipseResourcesPackage = IResource.class.getPackage();

	protected IMarkerItemProvider iMarkerItemProvider;
	protected IResourceItemProvider iResourceItemProvider;
	protected IMarkerDeltaItemProvider iMarkerDeltaItemProvider;
	protected IResourceChangeEventItemProvider iResourceChangeEventItemProvider;

	public EclipseResourcesItemProviderAdapterFactory() {
		// Minimal needed
		supportedTypes.add(IEditingDomainItemProvider.class);
		supportedTypes.add(IItemLabelProvider.class);
		supportedTypes.add(ITreeItemContentProvider.class);
		supportedTypes.add(IStructuredItemContentProvider.class);
		// TODO: test me!
		supportedTypes.add(INotificationItemProvider.class);
		supportedTypes.add(ILocationItemProvider.class);

		iMarkerItemProvider = new IMarkerItemProvider(this);
		iResourceItemProvider = new IResourceItemProvider(this);
		iMarkerDeltaItemProvider = new IMarkerDeltaItemProvider(this);
		iResourceChangeEventItemProvider = new IResourceChangeEventItemProvider(this);
	}

	// IResourceChangeListener
	//
	// TODO: test me!
	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		Object adapter = getRootAdapterFactory().adapt(event, IEditingDomainItemProvider.class);
		if (adapter instanceof IEditingDomainItemProvider) {
			final Object source = event.getSource();
			IEditingDomainItemProvider editingDomainProvider = (IEditingDomainItemProvider) adapter;
			Collection<?> children = editingDomainProvider.getChildren(event);
			for (Object child : children) {
				notifyChild(source, child);
			}
		}
	}

	protected void notifyChild(Object source, Object child) {
		Object notificationAdapter = getRootAdapterFactory().adapt(child, INotificationItemProvider.class);
		if (notificationAdapter instanceof INotificationItemProvider) {
			INotificationItemProvider notificationProvider = (INotificationItemProvider) notificationAdapter;
			notificationProvider.getEventType(child).ifPresent(eventType -> {

				fireNotifyChanged(new NotificationImpl(eventType, notificationProvider.getOldValue(child),
						notificationProvider.getNewValue(child)) {
					public Object getNotifier() {
						return source;
					}
				});
			});
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
				type instanceof IMarkerDelta || type instanceof IResourceChangeEvent || super.isFactoryForType(type);
	}

	@Override
	protected Object resolve(Object object, Object type) {
		if (object instanceof IMarker) {
			return createIMarkerAdapter();
		} else if (object instanceof IResource) {
			return createIResourceAdapter();
		} else if (object instanceof IMarkerDelta) {
			return createIMarkerDeltaAdapter();
		} else if (object instanceof IResourceChangeEvent) {
			return createIResourceChangeEventAdapter();
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

	protected Adapter createIResourceChangeEventAdapter() {
		return iResourceChangeEventItemProvider;
	}

}

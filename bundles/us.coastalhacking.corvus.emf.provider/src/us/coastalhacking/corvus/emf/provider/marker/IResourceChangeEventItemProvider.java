package us.coastalhacking.corvus.emf.provider.marker;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;

import us.coastalhacking.corvus.emf.MarkerApi;

public class IResourceChangeEventItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider {

	public IResourceChangeEventItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}
	
	@Override
	public Collection<?> getChildren(Object object) {
		IResourceChangeEvent event = (IResourceChangeEvent)object;
		// TODO: support IResourceDeltas
		return Arrays.asList(event.findMarkerDeltas(MarkerApi.Marker.Base.TYPE, true));
	}
	
	@Override
	public Object getParent(Object object) {
		return null;
	}
	
	@Override
	public Collection<?> getNewChildDescriptors(Object object, EditingDomain editingDomain, Object sibling) {
		return Collections.emptyList();
	}
}

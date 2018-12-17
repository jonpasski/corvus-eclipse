package us.coastalhacking.corvus.emf.provider.marker;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;

public class IMarkerItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider, IItemLabelProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider {

	public IMarkerItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	public String getText(Object object) {
		IMarker marker = (IMarker) object;
		return marker.getAttribute(IMarker.MESSAGE, super.getText(object));
	}

	@Override
	public Collection<?> getChildren(Object object) {
		IMarker marker = (IMarker) object;
		try {
			return marker.getAttributes().entrySet();
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	@Override
	public Object getParent(Object object) {
		return ((IMarker) object).getResource();
	}

	@Override
	public Collection<?> getNewChildDescriptors(Object object, EditingDomain editingDomain, Object sibling) {
		return Collections.emptyList();
	}
}

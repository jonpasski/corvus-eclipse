package us.coastalhacking.corvus.emf.provider.marker;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;

import com.google.common.base.Strings;

import us.coastalhacking.corvus.emf.MarkerApi;

// TODO: implement IItemPropertySource
public class IResourceItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider,
		IItemLabelProvider, IStructuredItemContentProvider, ITreeItemContentProvider {

	public IResourceItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	public String getText(Object object) {
		IResource resource = (IResource) object;
		return resource.getFullPath().toString();
	}

	@Override
	public Object getParent(Object object) {
		return null;
	}

	@Override
	public Collection<?> getChildren(Object object) {
		IResource resource = (IResource) object;
		try {
			return Arrays.asList(resource.findMarkers(MarkerApi.Type.BASE, true, IResource.DEPTH_ONE));
		} catch (CoreException e) {
			// TODO log
			e.printStackTrace();
		}
		return Collections.emptyList();
	}

	@Override
	public Collection<?> getNewChildDescriptors(Object object, EditingDomain editingDomain, Object sibling) {
		return Collections.emptyList();
	}

//	@Override
//	public Command createCommand(Object object, EditingDomain domain, Class<? extends Command> commandClass,
//			CommandParameter commandParameter) {
//		IResource resource = (IResource)commandParameter.getOwner();
//		
//		String markerType = (String)commandParameter.getFeature();
//		Map<String, Object> attributes = commandParameter.getCollection().stream().filter(Entry.class::isInstance).map(Entry.class::cast).collect(Collectors.toMap(es -> es.getKey().toString(),Entry::getValue));
//
//		if (!Strings.isNullOrEmpty(markerType) && !attributes.isEmpty() && attributes.size() == commandParameter.getCollection().size()) {
//
//			if (commandClass == AddCommand.class) {
//				// TODO: Create marker add command via workspace job
//			} else if (commandClass == RemoveCommand.class) {
//
//			}
//		}
//
//		return super.createCommand(object, domain, commandClass, commandParameter);
//	}
}

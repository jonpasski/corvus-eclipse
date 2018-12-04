package us.coastalhacking.corvus.eclipse.provider.entrypoint;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.TriggerListener;
import org.osgi.service.component.annotations.Component;

import us.coastalhacking.corvus.eclipse.EclipseApi;
import us.coastalhacking.corvus.emf.EmfApi;
import us.coastalhacking.corvus.semiotics.IMarker;
import us.coastalhacking.corvus.semiotics.MarkerEntryPoint;
import us.coastalhacking.corvus.semiotics.Root;
import us.coastalhacking.corvus.semiotics.SemioticsFactory;
import us.coastalhacking.corvus.semiotics.SemioticsPackage;
import us.coastalhacking.corvus.semiotics.Signified;

@Component(service = ResourceSetListener.class, immediate = true, property = EmfApi.ResourceSetListener.Properties.ID
		+ "=" + EclipseApi.ResourceSetListener.Properties.MarkerToEntryPoint.ID)
public class MarkerToEntryPointProvider extends TriggerListener {

	private NotificationFilter filter;
	URI epLogicalUri;

	public MarkerToEntryPointProvider() {
		filter = NotificationFilter.createFeatureFilter(SemioticsPackage.Literals.IRESOURCE__MARKERS);
		epLogicalUri = URI.createURI(EmfApi.ResourceInitializer.EntryPoint.Properties.LOGICAL);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Command trigger(TransactionalEditingDomain domain, Notification notification) {

		switch (notification.getEventType()) {
		case Notification.ADD_MANY:
		case Notification.ADD: {
			Collection<IMarker> markers;
			if (notification.getEventType() == Notification.ADD_MANY) {
				markers = (Collection<IMarker>) notification.getNewValue();
			} else {
				markers = Collections.singleton((IMarker) notification.getNewValue());
			}
			return addViaMarkers(domain, epLogicalUri, markers);
		}
		case Notification.REMOVE:
		case Notification.REMOVE_MANY: {
			Collection<IMarker> markers;
			if (notification.getEventType() == Notification.REMOVE_MANY) {
				markers = (Collection<IMarker>) notification.getOldValue();
			} else {
				markers = Collections.singleton((IMarker) notification.getOldValue());
			}
			return removeViaMarkers(domain, epLogicalUri, markers);
		}

		default: {
			break;
		}
		}
		return null;
	}

	Command addViaMarkers(TransactionalEditingDomain domain, URI uri, Collection<IMarker> markers) {
		final Resource epResource = domain.getResourceSet().getResource(uri, true);
		final Root root = (Root) epResource.getContents().get(0);
		final CompoundCommand result = new CompoundCommand();
		for (IMarker marker : markers) {
			MarkerEntryPoint ep = SemioticsFactory.eINSTANCE.createMarkerEntryPoint();
			result.append(AddCommand.create(domain, root, SemioticsPackage.Literals.ROOT__SEMIOTICS, ep));
			result.append(AddCommand.create(domain, ep, SemioticsPackage.Literals.SIGNIFIED__SIGNIFIERS, marker));
			result.append(AddCommand.create(domain, marker, SemioticsPackage.Literals.SIGNIFIER__SIGNIFIEDS, ep));
		}
		return result;
	}

	Command removeViaMarkers(TransactionalEditingDomain domain, URI uri, Collection<IMarker> markers) {
		final Resource resource = domain.getResourceSet().getResource(uri, true);
		final Root root = (Root) resource.getContents().get(0);
		final CompoundCommand result = new CompoundCommand();
		for (IMarker marker : markers) {
			for (Signified s : marker.getSignifieds()) {
				if (s.eClass().equals(SemioticsPackage.Literals.MARKER_ENTRY_POINT)) {
					result.append(RemoveCommand.create(domain, root, SemioticsPackage.Literals.ROOT__SEMIOTICS, s));
					result.append(
							RemoveCommand.create(domain, s, SemioticsPackage.Literals.SIGNIFIED__SIGNIFIERS, marker));
				}
			}
		}
		return result;
	}

	@Override
	public NotificationFilter getFilter() {
		return filter;
	}
}

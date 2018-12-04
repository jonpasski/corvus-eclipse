package us.coastalhacking.corvus.eclipse.provider.resources;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

import us.coastalhacking.corvus.eclipse.EclipseApi;
import us.coastalhacking.corvus.emf.EmfApi;
import us.coastalhacking.corvus.semiotics.IMarker;
import us.coastalhacking.corvus.semiotics.IResource;
import us.coastalhacking.corvus.semiotics.IWorkspaceRoot;
import us.coastalhacking.corvus.semiotics.SemioticsFactory;

@Component(service = IResourceChangeListener.class, configurationPid = EclipseApi.IResourceChangeListener.Component.CONFIG_PID, configurationPolicy = ConfigurationPolicy.REQUIRE, immediate = true)
public class EclipseResourcesChangeListenerProvider implements IResourceChangeListener {

	private String markerType;
	private URI uri;
	private SemioticsFactory factory = SemioticsFactory.eINSTANCE;
	static final String UNCONTAINED = "/-1";
	static final String EMPTY = "";

	@Reference
	IWorkspace workspace;

	@Reference(name = EmfApi.IEditingDomainProvider.Reference.NAME)
	IEditingDomainProvider domainProvider;

	@Activate
	void activate(Map<String, Object> props) {
		String markerType = (String) props.get(EclipseApi.IResourceChangeListener.Properties.MARKER_TYPE);
		workspace.addResourceChangeListener(this);
		this.markerType = markerType;
		this.uri = URI.createURI(EmfApi.ResourceInitializer.EclipseResources.Properties.LOGICAL);
	}

	@Deactivate
	void deactivate() {
		workspace.removeResourceChangeListener(this);
	}

	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		if (event.getType() != IResourceChangeEvent.POST_CHANGE) {
			return;
		}

		final IMarkerDelta[] deltas = event.findMarkerDeltas(markerType, true);
		if (deltas == null || deltas.length == 0) {
			return;
		}

		// Do not attempt to save the resource within a change event
		// else you get the hose again via an exception with message:
		// "The resource tree is locked for modifications."
		// TODO: convert from recording command to normal commands & execute on stack
		TransactionalEditingDomain transactionalDomain = (TransactionalEditingDomain)domainProvider.getEditingDomain();
		final RecordingCommand command = new RecordingCommand(transactionalDomain, "Marker change", "Marker change") {
			@Override
			protected void doExecute() {
				final Resource existingResource = domainProvider.getEditingDomain().getResourceSet().getResource(uri, true);
				processIMarkerDeltas(deltas, factory, existingResource);
			}
		};
		domainProvider.getEditingDomain().getCommandStack().execute(command);
	}

	void processIMarkerDeltas(IMarkerDelta[] deltas, SemioticsFactory factory, Resource existingResource) {
		final IWorkspaceRoot modelRoot = factory.createIWorkspaceRoot();
		final Resource modelResource = new ResourceImpl();
		modelResource.getContents().add(modelRoot);

		final IWorkspaceRoot existingRoot = (IWorkspaceRoot) existingResource.getContents().get(0);

		for (IMarkerDelta delta : deltas) {
			final org.eclipse.core.resources.IResource deltaResource = delta.getResource();
			final String portableFullPath = deltaResource.getFullPath().toPortableString();
			final IResource modelIResource = factory.createIResource();
			modelIResource.setFullPath(portableFullPath);
			modelIResource.setWorkspaceRoot(modelRoot);

			// Now process the marker change
			final IMarker modelMarker = factory.createIMarker();
			modelMarker.setId(delta.getId());

			switch (delta.getKind()) {
			case IResourceDelta.ADDED: {
				final String fragment = getFragment(modelRoot, modelIResource);
				final IResource existingIResource = getOrCreateExistingResource(fragment, existingRoot, modelIResource);
				added(delta, existingIResource, modelMarker);
				break;
			}
			case IResourceDelta.REMOVED: {
				Optional<IMarker> maybeMarker = getExistingMarker(existingResource, modelIResource, modelMarker);
				removed(maybeMarker);
				break;
			}
			case IResourceDelta.CHANGED: {
				Optional<IMarker> maybeMarker = getExistingMarker(existingResource, modelIResource, modelMarker);
				changed(delta, maybeMarker);
				break;
			}
			default:
				// TODO log - unsupported
			}
		}
	}

	Optional<IMarker> getExistingMarker(Resource existingResource, IResource modelIResource, IMarker modelMarker) {

		// Hack to generate the fragment
		modelIResource.getMarkers().add(modelMarker);
		String markerFragment = modelIResource.eResource().getURIFragment(modelMarker);
		modelIResource.getMarkers().remove(modelMarker);

		try {
			return Optional.of((IMarker) existingResource.getEObject(markerFragment));
		} catch (Exception e) {
		}
		return Optional.empty();
	}

	String getFragment(IWorkspaceRoot root, IResource iResource) {
		// Hack: Add to our local resource so we can generate the expected fragment
		String fragment = root.eResource().getURIFragment(iResource);
		if (UNCONTAINED.equals(fragment)) {
			root.getMembers().add(iResource);
			fragment = root.eResource().getURIFragment(iResource);
		}
		return fragment;
	}

	IResource getOrCreateExistingResource(String fragment, IWorkspaceRoot existingRoot, IResource modelIResource) {
		IResource existingIResource;
		try {
			existingIResource = (IResource) existingRoot.eResource().getEObject(fragment);
		} catch (Exception e) {
			existingIResource = null;
		}
		if (existingIResource == null) {
			final Copier copier = new Copier();
			existingIResource = (IResource) copier.copy(modelIResource);
			copier.copyReferences();
			existingRoot.getMembers().add(existingIResource);
		}
		return existingIResource;
	}

	void added(IMarkerDelta delta, IResource existingIResource, IMarker modelMarker) {

		// attributes
		for (Map.Entry<String, Object> entry : delta.getAttributes().entrySet()) {
			modelMarker.getAttributes().put(entry.getKey(), entry.getValue().toString());
		}

		// type
		modelMarker.setType(delta.getType());

		// creation time (from existing marker)
		try {
			modelMarker.setCreationTime(delta.getMarker().getCreationTime());
		} catch (CoreException e) {
			// TODO log - post-change event but couldn't get creation time
		}

		// Then add to the resource
		modelMarker.setResource(existingIResource);
	}

	void removed(Optional<IMarker> maybeMarker) {
		if (maybeMarker.isPresent()) {
			// https://eclipsesource.com/blogs/2015/05/26/emf-dos-and-donts-11/
			// tl;dr, avoid EcoreUtil.delete
			EcoreUtil.remove(maybeMarker.get());
		} else {
			// TODO log - attempted to delete a marker that doesn't exist
		}
	}

	void changed(IMarkerDelta delta, Optional<IMarker> maybeMarker) {
		if (maybeMarker.isPresent()) {
			IMarker existingMarker = maybeMarker.get();

			// type
			try {
				if (!delta.getMarker().getType().equals(delta.getType())) {
					existingMarker.setType(delta.getMarker().getType());
				}
			} catch (CoreException e) {
				// TODO log - post-change event but couldn't get existing marker type
			}

			// attributes
			try {
				Map<String, Object> newAttr = delta.getMarker().getAttributes();
				Map<String, Object> oldAttr = delta.getAttributes();

				// Remove
				Set<String> toRemove = new HashSet<>(oldAttr.keySet());
				toRemove.removeIf(s -> newAttr.containsKey(s));
				existingMarker.getAttributes().removeIf(e -> toRemove.contains(e.getKey()));

				// Add and Change
				Set<String> toAddChange = new HashSet<>(oldAttr.keySet());
				toAddChange.removeAll(toRemove);
				toAddChange
						.removeIf(s -> Optional.ofNullable(oldAttr.get(s)).equals(Optional.ofNullable(newAttr.get(s))));

				Set<String> toAdd = new HashSet<>(newAttr.keySet());
				toAdd.removeIf(s -> oldAttr.containsKey(s));
				toAddChange.addAll(toAdd);

				for (String key : toAddChange) {
					Object valueObj = newAttr.get(key);
					String value = valueObj == null ? EMPTY : valueObj.toString();
					existingMarker.getAttributes().put(key, value);
				}
			} catch (CoreException e) {
				// TODO log - could not get attributes from an existing marker
				return;
			}

		} else {
			// TODO log - attempted to change a marker that doesn't exist
		}
	}

}

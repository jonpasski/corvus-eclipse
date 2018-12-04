package us.coastalhacking.corvus.emf.provider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AbstractOverrideableCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.DragAndDropCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import us.coastalhacking.corvus.emf.GetCommand;
import us.coastalhacking.corvus.emf.GetMaybeAddCommand;
import us.coastalhacking.corvus.emf.SaveCommand;

// TODO: extend ResourceSetItemProvider instead of decorating
@Deprecated
public class DecoratedResourceSetItemProvider extends BaseDecoratedItemProvider {

	public DecoratedResourceSetItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	public Command createCommand(Object object, EditingDomain editingDomain, Class<? extends Command> commandClass,
			CommandParameter commandParameter) {

		if (commandClass == DragAndDropCommand.class) {
			Command command = getDnDCommand(editingDomain, commandParameter);
			if (command.canExecute()) {
				return command;
			}
		} else if (commandClass == SaveCommand.class && commandParameter.getFeature() instanceof Map) {
			return getSaveCommand(editingDomain, commandParameter);
		} else if (commandClass == GetMaybeAddCommand.class && commandParameter.getFeature() instanceof URI
				&& commandParameter.getValue() instanceof EObject) {
			return getMaybeAddCommand(editingDomain, commandParameter);
		}
		return super.createCommand(object, editingDomain, commandClass, commandParameter);
	}

	// Simulate a DnD onto the RS to add URIs to its URIMap
	// Change to not use a map entry set as its input. rather, one by one for each resource initializer
	@Deprecated
	Command getDnDCommand(EditingDomain editingDomain, CommandParameter commandParameter) {
		final Map<URI, URI> uris = commandParameter.getCollection().stream().filter(Map.Entry.class::isInstance)
				.map(Map.Entry.class::cast)
				.collect(Collectors.toMap(e -> URI.class.cast(e.getKey()), e -> URI.class.cast(e.getValue())));

		// There are other DnD commands so be strict on its contents
		if (commandParameter.getCollection().size() == uris.size()) {
			class AddToUriMap extends AbstractOverrideableCommand implements AbstractCommand.NonDirtying {

				protected final List<URI> addedUris = new ArrayList<>();

				protected AddToUriMap() {
					super(editingDomain);
				}

				@Override
				protected boolean prepare() {
					return !uris.isEmpty();
				}

				@Override
				public void doExecute() {
					addedUris.clear();
					getDomain().getResourceSet().getURIConverter().getURIMap().putAll(uris);
					addedUris.addAll(uris.keySet());
				}

				@Override
				public void doUndo() {
					uris.entrySet().stream().forEach(es -> {
						getDomain().getResourceSet().getURIConverter().getURIMap().remove(es.getKey(), es.getValue());
						addedUris.remove(es.getKey());
					});
				}

				@Override
				public void doRedo() {
					doExecute();
				}

				@Override
				public Collection<?> doGetAffectedObjects() {
					return new ArrayList<>(addedUris);
				}
			}
			return new AddToUriMap();
		}
		return UnexecutableCommand.INSTANCE;
	}

	Command getSaveCommand(EditingDomain editingDomain, CommandParameter commandParameter) {
		final Collection<?> collection = commandParameter.getCollection();
		@SuppressWarnings("unchecked")
		final Map<String, Object> options = (Map<String, Object>) commandParameter.getFeature();
		final List<URI> uris = collection.stream().filter(URI.class::isInstance).map(URI.class::cast)
				.collect(Collectors.toList());

		class SaveCommandBase extends AbstractOverrideableCommand implements SaveCommand {

			final List<URI> savedUris = new ArrayList<>();

			protected SaveCommandBase() {
				super(editingDomain);
			}

			@Override
			protected boolean prepare() {
				return !uris.isEmpty();
			}

			@Override
			public void doExecute() {

				for (URI uri : uris) {
					Resource resource;
					try {
						resource = domain.getResourceSet().getResource(uri, true);
					} catch (Exception e) {
						resource = domain.getResourceSet().getResource(uri, false);
						// TODO: log
					}
					try {
						resource.save(options);
						savedUris.add(uri);
					} catch (IOException e) {
						// TODO: log / change results?
					}
				}
			}

			@Override
			public Collection<?> doGetAffectedObjects() {
				return new ArrayList<>(savedUris);
			}

			@Override
			public boolean doCanUndo() {
				return false;
			}

			@Override
			public void doUndo() {
				// Ignored
			}

			@Override
			public void doRedo() {
				// Ignored
			}

			@Override
			public void doDispose() {
				savedUris.clear();
				uris.clear();
			}
		}

		return new SaveCommandBase();
	}

	Command getMaybeAddCommand(EditingDomain editingDomain, CommandParameter commandParameter) {
		final ResourceSet resourceSet = (ResourceSet) commandParameter.getOwner();
		final URI resourceUri = (URI) commandParameter.getFeature();
		final EObject searchRoot = (EObject) commandParameter.getValue();

		class GetMaybeAddFragmentCommand extends CompoundCommand {

			List<EObject> results;

			public GetMaybeAddFragmentCommand() {
				super(CompoundCommand.MERGE_COMMAND_ALL);
			}

			@Override
			protected Collection<?> getMergedResultCollection() {
				return results;
			};
		}

		GetMaybeAddFragmentCommand compoundCmd = new GetMaybeAddFragmentCommand();

		// Get the resource via a DnD on the resource set
		final Command dndCmd = DragAndDropCommand.create(editingDomain, resourceSet, -1f, DragAndDropCommand.DROP_NONE,
				DragAndDropCommand.DROP_NONE, Collections.singleton(resourceUri));

		if (compoundCmd.appendAndExecute(dndCmd) && !dndCmd.getAffectedObjects().isEmpty()) {
			// Add the root to a "virtual" resource so the EObjects have resolvable URI
			// fragments
			final Resource virtual = new ResourceImpl();
			virtual.getContents().add(searchRoot);

			// Populate
			final List<EObject> searchedObjects = new ArrayList<>();
			final List<String> searchedFragments = new ArrayList<>();
			virtual.getAllContents().forEachRemaining(eo -> {
				searchedFragments.add(virtual.getURIFragment(eo));
				searchedObjects.add(eo);
			});

			// See ResourceSetItemProvider.createDragAndDropCommand
			final Resource resource = (Resource) dndCmd.getAffectedObjects().iterator().next();
			// Get all the EObjects (or nulls) via the fragments
			final Command getCmd = GetCommand.createCommand(editingDomain, resource, searchedFragments);

			if (compoundCmd.appendAndExecute(getCmd) && !getCmd.getResult().isEmpty()) {
				// Don't filter for instance check since nulls should be preserved
				final List<EObject> getCmdResults = getCmd.getResult().stream().map(EObject.class::cast)
						.collect(Collectors.toList());

				// These should match
				if (getCmdResults.size() == searchedObjects.size()) {
					final List<EObject> localResults = new ArrayList<>(searchedObjects.size());
					final EcoreUtil.Copier copier = new ShallowCopier();

					IntStream.range(0, searchedObjects.size()).forEach(i -> {
						EObject result = getCmdResults.get(i);

						if (result == null) {
							// Create and append an Add command
							EObject lookedUp = searchedObjects.get(i);
							EObject toAdd = copier.copy(lookedUp);
							EObject key = lookedUp.eContainer();

							// Use the owner's index as an index to the result list
							if (key != null) {
								int index = searchedObjects.indexOf(key);

								if (index != -1) {
									EObject owner = getCmdResults.get(index);
									Command addCommand = AddCommand.create(editingDomain, owner, null, toAdd);
									if (compoundCmd.appendAndExecute(addCommand)) {
										result = (EObject) addCommand.getResult().iterator().next();
									}
								}
							}
						}
						localResults.add(i, result);
					});
					compoundCmd.results = localResults;
				}
			}
		}

		return compoundCmd;
	}
}

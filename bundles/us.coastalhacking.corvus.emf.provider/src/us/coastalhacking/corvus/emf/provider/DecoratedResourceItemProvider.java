package us.coastalhacking.corvus.emf.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.AbstractOverrideableCommand;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;

import us.coastalhacking.corvus.emf.GetCommand;

//TODO: extend ResourceItemProvider instead of decorating
@Deprecated
public class DecoratedResourceItemProvider extends BaseDecoratedItemProvider {

	public DecoratedResourceItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	public Command createCommand(Object object, EditingDomain editingDomain, Class<? extends Command> commandClass,
			CommandParameter commandParameter) {

		if (commandClass == GetCommand.class) {
			final Resource resource = (Resource)commandParameter.getOwner();
			final List<String> fragments = commandParameter.getCollection().stream().filter(Object.class::isInstance)
					.map(Object::toString).collect(Collectors.toList());
			if (fragments.size() == commandParameter.getCollection().size()) {
				class GetFragmentCommand extends AbstractOverrideableCommand implements AbstractCommand.NonDirtying {

					final protected List<EObject> results = new ArrayList<>(fragments.size());

					protected GetFragmentCommand() {
						super(editingDomain);
					}

					@Override
					public void doExecute() {
						results.clear();
						results.addAll(fragments.stream().map(fragment -> resource.getEObject(fragment))
								.collect(Collectors.toList()));
					}
					@Override
					protected boolean prepare() {
						return !fragments.isEmpty();
					}

					@Override
					public void doUndo() {
						results.clear();
					}

					@Override
					public void doRedo() {
						doExecute();
					}

					@Override
					public Collection<?> doGetResult() {
						return results;
					}
				}
				return new GetFragmentCommand();
			}
		}
		return super.createCommand(object, editingDomain, commandClass, commandParameter);
	}
}

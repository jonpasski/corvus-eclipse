package us.coastalhacking.corvus.emf;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;

public interface GetMaybeAddCommand extends GetCommand {

	static Command createCommand(EditingDomain domain, URI uri, EObject root) {
		return domain.createCommand(GetMaybeAddCommand.class, new CommandParameter(domain.getResourceSet(), uri, root));
	}
}

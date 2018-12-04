package us.coastalhacking.corvus.emf;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;

public interface GetCommand extends Command {

	static Command createCommand(EditingDomain domain, Resource resource, Collection<String> fragments) {
		return domain.createCommand(GetCommand.class, new CommandParameter(resource, null, fragments));
	}
}

package us.coastalhacking.corvus.emf;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;

public interface SaveCommand extends Command {

	/**
	 * Via the domain's resource set and using the provided options, save
	 * the following list of {@link URI URIs}.
	 * 
	 * @param domain an editing domain
	 * @param options a map of save-related resource options
	 * @param uris a list of URIs to save
	 * @return a command to execute
	 */
	static Command create(EditingDomain domain, final Map<String, Object> options, final List<URI> uris) {
		return domain.createCommand(SaveCommand.class, new CommandParameter(domain.getResourceSet(), options, uris));
	}
}

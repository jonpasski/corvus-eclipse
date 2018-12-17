package us.coastalhacking.corvus.emf.command;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;

public interface AddEclipseResourcesCommand extends Command {

	/**
	 * Add an Eclipse Resources domain object to the editing domain
	 * 
	 * @param editingDomain the editing domain
	 * @param owner an {@link org.eclipse.core.resources.IResource IResource} or {@link org.eclipse.core.resources.IWorkspace IWorkspace}
	 * @param value an IResource or {@link org.eclipse.core.resources.IMarker IMarker}
	 * @return a command
	 */
	static Command createCommand(EditingDomain editingDomain, Object owner, Object value) {
		return editingDomain.createCommand(AddCommand.class, new CommandParameter(owner, null, value));
	}
}

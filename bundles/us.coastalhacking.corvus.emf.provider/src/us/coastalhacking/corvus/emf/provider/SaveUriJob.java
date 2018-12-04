package us.coastalhacking.corvus.emf.provider;

import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.domain.EditingDomain;

import us.coastalhacking.corvus.emf.SaveCommand;

public class SaveUriJob extends WorkspaceJob {

	final EditingDomain domain;
	final Map<String, Object> options;
	final List<URI> uris;
	
	public SaveUriJob(EditingDomain domain, Map<String, Object> options, List<URI> uris) {
		super("Save URIs Job");
		this.domain = domain;
		this.options = options;
		this.uris = uris;
	}

	@Override
	public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
		final Command command = SaveCommand.create(domain, options, uris);
		domain.getCommandStack().execute(command);
		return Status.OK_STATUS;
	}

}

package us.coastalhacking.corvus.emf.provider;

import java.util.Collection;
import java.util.Map;

import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

public abstract class AbstractSaveResourcesJob extends WorkspaceJob {

	private final TransactionalEditingDomain domain;
	private final Map<String, Object> options;
	private final Collection<URI> uris;


	public AbstractSaveResourcesJob(Collection<URI> uris, TransactionalEditingDomain domain, Map<String, Object> options) {
		super("Save resources job");
		this.domain = domain;
		this.options = options;
		this.uris = uris;
	}

	@Override
	// TODO: better diagnostic logging 
	public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
		final RecordingCommand command = new RecordingCommand(domain, "Corvus EMF Saving Resource Job", "Corvus EMF Saving Resource Job") {
			@Override
			protected void doExecute() {
				uris.stream().forEach(uri -> {
					forEachUri(domain, uri, options);
				});
			}
		};
		domain.getCommandStack().execute(command);
		return Status.OK_STATUS;
	}


	protected abstract void forEachUri(TransactionalEditingDomain domain, URI uri, Map<String, Object> options);
}

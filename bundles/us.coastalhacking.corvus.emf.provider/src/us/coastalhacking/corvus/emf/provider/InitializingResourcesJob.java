package us.coastalhacking.corvus.emf.provider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import us.coastalhacking.corvus.emf.ResourceInitializer;

// TODO: consider making a component factory and referencing
// the transaction id util to get the project uri
@Deprecated
public class InitializingResourcesJob extends WorkspaceJob {

	private final List<ResourceInitializer> initializers;
	private final URI projectUri;
	private final TransactionalEditingDomain editingDomain;

	private final List<URI> physicalUris;
	private final static String JOB_NAME = "Initializing Corvus EMF resources";

	public InitializingResourcesJob(TransactionalEditingDomain editingDomain, URI projectUri,
			List<ResourceInitializer> initializers) {
		super(JOB_NAME);
		this.editingDomain = editingDomain;
		this.projectUri = projectUri;
		this.initializers = new ArrayList<>(initializers);
		this.physicalUris = new ArrayList<>();
	}

	@Override
	// TODO: better diagnostic logging
	public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
		final RecordingCommand command = new RecordingCommand(editingDomain, JOB_NAME, JOB_NAME) {
			@Override
			protected void doExecute() {

				for (ResourceInitializer initializer : initializers) {

					final URI logicalUri = URI.createURI(initializer.getLogical());
					final URI physicalUri = projectUri.appendSegment(initializer.getFilename());
					physicalUris.add(physicalUri);
					editingDomain.getResourceSet().getURIConverter().getURIMap().put(logicalUri, physicalUri);

					Resource resource;
					try {
						resource = editingDomain.getResourceSet().getResource(logicalUri, true);
					} catch (Exception e) {
						resource = editingDomain.getResourceSet().getResource(logicalUri, false);
					}
					if (resource.getContents().isEmpty()) {
						resource.getContents().add(initializer.getRoot());
					}
					try {
						resource.save(initializer.getOptions());
					} catch (IOException e) {
						// TODO better diagnostics
						throw new RuntimeException(e);
					}
				}
			}
		};
		editingDomain.getCommandStack().execute(command);

		return Status.OK_STATUS;
	}
	
	public List<URI> getPhysicalUris() {
		// Hide our backing list
		return new ArrayList<>(physicalUris);
	}

}

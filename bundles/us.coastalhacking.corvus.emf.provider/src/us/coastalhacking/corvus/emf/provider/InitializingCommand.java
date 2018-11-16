package us.coastalhacking.corvus.emf.provider;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

public class InitializingCommand extends RecordingCommand {

	private final TransactionalEditingDomain domain;
	private final URI key;
	private final URI value;
	private final EObject root;

	public InitializingCommand(TransactionalEditingDomain domain, URI key, URI value, EObject root) {
		super(domain);
		this.domain = domain;
		this.key = key;
		this.value = value;
		this.root = root;
	}

	@Override
	protected void doExecute() {
		domain.getResourceSet().getURIConverter().getURIMap().put(key, value);
		Resource resource;
		try {
			resource = domain.getResourceSet().getResource(key, true);
		} catch (Exception e) {
			resource = domain.getResourceSet().getResource(key, false);
		}
		if (resource.getContents().isEmpty()) {
			resource.getContents().add(root);	
		}
		// TODO: get XMI options and pass here via properties
		try {
			resource.save(null);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}

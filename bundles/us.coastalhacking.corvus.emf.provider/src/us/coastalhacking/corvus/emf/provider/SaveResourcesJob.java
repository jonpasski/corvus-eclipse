package us.coastalhacking.corvus.emf.provider;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

@Deprecated
public class SaveResourcesJob extends AbstractSaveResourcesJob {


	public SaveResourcesJob(Collection<URI> uris, TransactionalEditingDomain domain, Map<String, Object> options) {
		super(uris, domain, options);
	}

	@Override
	protected void forEachUri(TransactionalEditingDomain domain, URI uri, Map<String, Object> options) {
		Resource resource = domain.getResourceSet().getResource(uri, true);
		try {
			resource.save(options);
		} catch (IOException e) {
			// TODO log
			e.printStackTrace();
		}
	}

}

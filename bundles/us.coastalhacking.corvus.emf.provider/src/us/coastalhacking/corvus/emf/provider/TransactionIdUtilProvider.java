package us.coastalhacking.corvus.emf.provider;

import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.osgi.service.component.annotations.Component;

import us.coastalhacking.corvus.emf.EmfApi;
import us.coastalhacking.corvus.emf.TransactionIdUtil;

@Component(service = TransactionIdUtil.class, immediate = true)
public class TransactionIdUtilProvider implements TransactionIdUtil {

	@Override
	public String getId(Map<String, Object> props) {
		return (String) props.get(EmfApi.TransactionalEditingDomain.Properties.ID);
	}

	@Override
	public URI getUri(Map<String, Object> props) {
		return getUri(getId(props));
	}

	@Override
	public URI getUri(String id) {
		// Do not gratuitously encode
		return URI.createPlatformResourceURI(id, false);
	}

	@Override
	public String getId(IProject project) {
		return project.getFullPath().toPortableString();
	}

	@Override
	public void putId(Map<String, Object> props, String id) {
		props.put(EmfApi.TransactionalEditingDomain.Properties.ID, id);
		//props.put(EmfApi.ResourceInitializer.Properties.PROJECT, id);
	}
}

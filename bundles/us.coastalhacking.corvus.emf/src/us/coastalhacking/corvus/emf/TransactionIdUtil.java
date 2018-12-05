package us.coastalhacking.corvus.emf;

import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;

// TODO: if these can return null, return Optional instead
public interface TransactionIdUtil {

	String getId(Map<String, Object> props);
	
	URI getUri(Map<String, Object> props);

	URI getUri(String id);
	
	String getId(IProject project);
	
	void putId(Map<String, Object> props, String id);
}

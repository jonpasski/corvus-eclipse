package us.coastalhacking.corvus.emf;

import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

public interface ResourceInitializer {

	/**
	 * Return a logical consumable by
	 * {@link org.eclipse.emf.common.util.URI#createURI(String)}
	 * 
	 * @return the logical URI
	 */
	String getLogical();

	/**
	 * Return a relative file name
	 * 
	 * @return the physical URI
	 */
	String getFilename();

	/**
	 * Returns the root {@link EObject} for some expected {@link Resource}
	 * 
	 * @return a root EObject
	 */
	EObject getRoot();

	default Map<String, Object> getOptions() {
		return Collections.emptyMap();
	};
}

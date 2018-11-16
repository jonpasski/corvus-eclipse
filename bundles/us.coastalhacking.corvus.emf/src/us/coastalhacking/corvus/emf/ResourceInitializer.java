package us.coastalhacking.corvus.emf;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

public interface ResourceInitializer {

	/**
	 * Return a logical consumable by {@link org.eclipse.emf.common.util.URI#createURI(String)}
	 * 
	 * @return the logical URI
	 */
	String getLogical();

	/**
	 * Return a physical URI consumable by {@link org.eclipse.emf.common.util.URI#createPlatformResourceURI(String)} 
	 * 
	 * @return the physical URI
	 */
	String getPhysical();

	/**
	 * Returns the root {@link EObject} for some expected {@link Resource}
	 * 
	 * @return a root EObject
	 */
	EObject getRoot();
	
	// TODO
	// default Map<String, Object> getResourceOptions() {...}
}

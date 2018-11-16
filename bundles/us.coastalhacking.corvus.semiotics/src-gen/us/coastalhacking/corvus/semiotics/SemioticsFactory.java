/**
 */
package us.coastalhacking.corvus.semiotics;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see us.coastalhacking.corvus.semiotics.SemioticsPackage
 * @generated
 */
public interface SemioticsFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SemioticsFactory eINSTANCE = us.coastalhacking.corvus.semiotics.impl.SemioticsFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Root</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Root</em>'.
	 * @generated
	 */
	Root createRoot();

	/**
	 * Returns a new object of class '<em>IMarker</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>IMarker</em>'.
	 * @generated
	 */
	IMarker createIMarker();

	/**
	 * Returns a new object of class '<em>IResource</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>IResource</em>'.
	 * @generated
	 */
	IResource createIResource();

	/**
	 * Returns a new object of class '<em>IWorkspace Root</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>IWorkspace Root</em>'.
	 * @generated
	 */
	IWorkspaceRoot createIWorkspaceRoot();

	/**
	 * Returns a new object of class '<em>Marker Entry Point</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Marker Entry Point</em>'.
	 * @generated
	 */
	MarkerEntryPoint createMarkerEntryPoint();

	/**
	 * Returns a new object of class '<em>Marker Sink</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Marker Sink</em>'.
	 * @generated
	 */
	MarkerSink createMarkerSink();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	SemioticsPackage getSemioticsPackage();

} //SemioticsFactory

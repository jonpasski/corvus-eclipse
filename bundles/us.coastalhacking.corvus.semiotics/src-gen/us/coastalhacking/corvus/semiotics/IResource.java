/**
 */
package us.coastalhacking.corvus.semiotics;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IResource</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link us.coastalhacking.corvus.semiotics.IResource#getFullPath <em>Full Path</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.semiotics.IResource#getMarkers <em>Markers</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.semiotics.IResource#getWorkspaceRoot <em>Workspace Root</em>}</li>
 * </ul>
 *
 * @see us.coastalhacking.corvus.semiotics.SemioticsPackage#getIResource()
 * @model
 * @generated
 */
public interface IResource extends Semiotics {
	/**
	 * Returns the value of the '<em><b>Full Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Full Path</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Full Path</em>' attribute.
	 * @see #setFullPath(String)
	 * @see us.coastalhacking.corvus.semiotics.SemioticsPackage#getIResource_FullPath()
	 * @model unique="false"
	 * @generated
	 */
	String getFullPath();

	/**
	 * Sets the value of the '{@link us.coastalhacking.corvus.semiotics.IResource#getFullPath <em>Full Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Full Path</em>' attribute.
	 * @see #getFullPath()
	 * @generated
	 */
	void setFullPath(String value);

	/**
	 * Returns the value of the '<em><b>Markers</b></em>' containment reference list.
	 * The list contents are of type {@link us.coastalhacking.corvus.semiotics.IMarker}.
	 * It is bidirectional and its opposite is '{@link us.coastalhacking.corvus.semiotics.IMarker#getResource <em>Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Markers</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Markers</em>' containment reference list.
	 * @see us.coastalhacking.corvus.semiotics.SemioticsPackage#getIResource_Markers()
	 * @see us.coastalhacking.corvus.semiotics.IMarker#getResource
	 * @model opposite="resource" containment="true" keys="id"
	 * @generated
	 */
	EList<IMarker> getMarkers();

	/**
	 * Returns the value of the '<em><b>Workspace Root</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link us.coastalhacking.corvus.semiotics.IWorkspaceRoot#getMembers <em>Members</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Workspace Root</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Workspace Root</em>' container reference.
	 * @see #setWorkspaceRoot(IWorkspaceRoot)
	 * @see us.coastalhacking.corvus.semiotics.SemioticsPackage#getIResource_WorkspaceRoot()
	 * @see us.coastalhacking.corvus.semiotics.IWorkspaceRoot#getMembers
	 * @model opposite="members" transient="false"
	 * @generated
	 */
	IWorkspaceRoot getWorkspaceRoot();

	/**
	 * Sets the value of the '{@link us.coastalhacking.corvus.semiotics.IResource#getWorkspaceRoot <em>Workspace Root</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Workspace Root</em>' container reference.
	 * @see #getWorkspaceRoot()
	 * @generated
	 */
	void setWorkspaceRoot(IWorkspaceRoot value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" unique="false"
	 *        annotation="http://www.eclipse.org/emf/2002/GenModel body='return this.getFullPath();'"
	 * @generated
	 */
	String getKey();

} // IResource

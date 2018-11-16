/**
 */
package us.coastalhacking.corvus.semiotics;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>IWorkspace Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link us.coastalhacking.corvus.semiotics.IWorkspaceRoot#getMembers <em>Members</em>}</li>
 * </ul>
 *
 * @see us.coastalhacking.corvus.semiotics.SemioticsPackage#getIWorkspaceRoot()
 * @model
 * @generated
 */
public interface IWorkspaceRoot extends Semiotics {
	/**
	 * Returns the value of the '<em><b>Members</b></em>' containment reference list.
	 * The list contents are of type {@link us.coastalhacking.corvus.semiotics.IResource}.
	 * It is bidirectional and its opposite is '{@link us.coastalhacking.corvus.semiotics.IResource#getWorkspaceRoot <em>Workspace Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Members</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Members</em>' containment reference list.
	 * @see us.coastalhacking.corvus.semiotics.SemioticsPackage#getIWorkspaceRoot_Members()
	 * @see us.coastalhacking.corvus.semiotics.IResource#getWorkspaceRoot
	 * @model opposite="workspaceRoot" containment="true" keys="fullPath"
	 * @generated
	 */
	EList<IResource> getMembers();

} // IWorkspaceRoot

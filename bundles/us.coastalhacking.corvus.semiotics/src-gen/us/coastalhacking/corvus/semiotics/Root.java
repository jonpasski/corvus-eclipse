/**
 */
package us.coastalhacking.corvus.semiotics;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link us.coastalhacking.corvus.semiotics.Root#getSemiotics <em>Semiotics</em>}</li>
 * </ul>
 *
 * @see us.coastalhacking.corvus.semiotics.SemioticsPackage#getRoot()
 * @model
 * @generated
 */
public interface Root extends EObject {
	/**
	 * Returns the value of the '<em><b>Semiotics</b></em>' containment reference list.
	 * The list contents are of type {@link us.coastalhacking.corvus.semiotics.Semiotics}.
	 * It is bidirectional and its opposite is '{@link us.coastalhacking.corvus.semiotics.Semiotics#getRoot <em>Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Semiotics</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Semiotics</em>' containment reference list.
	 * @see us.coastalhacking.corvus.semiotics.SemioticsPackage#getRoot_Semiotics()
	 * @see us.coastalhacking.corvus.semiotics.Semiotics#getRoot
	 * @model opposite="root" containment="true" keys="key"
	 * @generated
	 */
	EList<Semiotics> getSemiotics();

} // Root

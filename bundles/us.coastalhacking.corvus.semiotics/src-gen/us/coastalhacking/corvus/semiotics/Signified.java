/**
 */
package us.coastalhacking.corvus.semiotics;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Signified</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link us.coastalhacking.corvus.semiotics.Signified#getUuid <em>Uuid</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.semiotics.Signified#getSignifiers <em>Signifiers</em>}</li>
 * </ul>
 *
 * @see us.coastalhacking.corvus.semiotics.SemioticsPackage#getSignified()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface Signified extends EObject {
	/**
	 * Returns the value of the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uuid</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uuid</em>' attribute.
	 * @see #setUuid(String)
	 * @see us.coastalhacking.corvus.semiotics.SemioticsPackage#getSignified_Uuid()
	 * @model unique="false" id="true"
	 * @generated
	 */
	String getUuid();

	/**
	 * Sets the value of the '{@link us.coastalhacking.corvus.semiotics.Signified#getUuid <em>Uuid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Uuid</em>' attribute.
	 * @see #getUuid()
	 * @generated
	 */
	void setUuid(String value);

	/**
	 * Returns the value of the '<em><b>Signifiers</b></em>' reference list.
	 * The list contents are of type {@link us.coastalhacking.corvus.semiotics.Signifier}.
	 * It is bidirectional and its opposite is '{@link us.coastalhacking.corvus.semiotics.Signifier#getSignifieds <em>Signifieds</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Signifiers</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Signifiers</em>' reference list.
	 * @see us.coastalhacking.corvus.semiotics.SemioticsPackage#getSignified_Signifiers()
	 * @see us.coastalhacking.corvus.semiotics.Signifier#getSignifieds
	 * @model opposite="signifieds"
	 * @generated
	 */
	EList<Signifier> getSignifiers();

} // Signified

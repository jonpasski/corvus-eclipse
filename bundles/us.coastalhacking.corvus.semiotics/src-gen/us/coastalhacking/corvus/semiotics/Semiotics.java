/**
 */
package us.coastalhacking.corvus.semiotics;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Semiotics</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link us.coastalhacking.corvus.semiotics.Semiotics#getRoot <em>Root</em>}</li>
 * </ul>
 *
 * @see us.coastalhacking.corvus.semiotics.SemioticsPackage#getSemiotics()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface Semiotics extends Signifier, Signified {
	/**
	 * Returns the value of the '<em><b>Root</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link us.coastalhacking.corvus.semiotics.Root#getSemiotics <em>Semiotics</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Root</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Root</em>' container reference.
	 * @see #setRoot(Root)
	 * @see us.coastalhacking.corvus.semiotics.SemioticsPackage#getSemiotics_Root()
	 * @see us.coastalhacking.corvus.semiotics.Root#getSemiotics
	 * @model opposite="semiotics" transient="false"
	 * @generated
	 */
	Root getRoot();

	/**
	 * Sets the value of the '{@link us.coastalhacking.corvus.semiotics.Semiotics#getRoot <em>Root</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Root</em>' container reference.
	 * @see #getRoot()
	 * @generated
	 */
	void setRoot(Root value);

} // Semiotics

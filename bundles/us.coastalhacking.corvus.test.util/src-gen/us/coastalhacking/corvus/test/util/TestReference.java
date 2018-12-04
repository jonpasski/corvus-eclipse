/**
 */
package us.coastalhacking.corvus.test.util;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link us.coastalhacking.corvus.test.util.TestReference#getRoot <em>Root</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.test.util.TestReference#getTestId <em>Test Id</em>}</li>
 * </ul>
 *
 * @see us.coastalhacking.corvus.test.util.UtilPackage#getTestReference()
 * @model
 * @generated
 */
public interface TestReference extends EObject {
	/**
	 * Returns the value of the '<em><b>Root</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link us.coastalhacking.corvus.test.util.TestRoot#getTestReferences <em>Test References</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Root</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Root</em>' container reference.
	 * @see #setRoot(TestRoot)
	 * @see us.coastalhacking.corvus.test.util.UtilPackage#getTestReference_Root()
	 * @see us.coastalhacking.corvus.test.util.TestRoot#getTestReferences
	 * @model opposite="testReferences" transient="false"
	 * @generated
	 */
	TestRoot getRoot();

	/**
	 * Sets the value of the '{@link us.coastalhacking.corvus.test.util.TestReference#getRoot <em>Root</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Root</em>' container reference.
	 * @see #getRoot()
	 * @generated
	 */
	void setRoot(TestRoot value);

	/**
	 * Returns the value of the '<em><b>Test Id</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link us.coastalhacking.corvus.test.util.TestId#getTestReference <em>Test Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Test Id</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test Id</em>' reference.
	 * @see #setTestId(TestId)
	 * @see us.coastalhacking.corvus.test.util.UtilPackage#getTestReference_TestId()
	 * @see us.coastalhacking.corvus.test.util.TestId#getTestReference
	 * @model opposite="testReference"
	 * @generated
	 */
	TestId getTestId();

	/**
	 * Sets the value of the '{@link us.coastalhacking.corvus.test.util.TestReference#getTestId <em>Test Id</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Test Id</em>' reference.
	 * @see #getTestId()
	 * @generated
	 */
	void setTestId(TestId value);

} // TestReference

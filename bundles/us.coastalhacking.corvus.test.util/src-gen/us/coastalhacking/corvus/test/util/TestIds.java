/**
 */
package us.coastalhacking.corvus.test.util;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test Ids</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link us.coastalhacking.corvus.test.util.TestIds#getTestIds <em>Test Ids</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.test.util.TestIds#getRoot <em>Root</em>}</li>
 * </ul>
 *
 * @see us.coastalhacking.corvus.test.util.UtilPackage#getTestIds()
 * @model
 * @generated
 */
public interface TestIds extends EObject {
	/**
	 * Returns the value of the '<em><b>Test Ids</b></em>' containment reference list.
	 * The list contents are of type {@link us.coastalhacking.corvus.test.util.TestId}.
	 * It is bidirectional and its opposite is '{@link us.coastalhacking.corvus.test.util.TestId#getRoot <em>Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Test Ids</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test Ids</em>' containment reference list.
	 * @see us.coastalhacking.corvus.test.util.UtilPackage#getTestIds_TestIds()
	 * @see us.coastalhacking.corvus.test.util.TestId#getRoot
	 * @model opposite="root" containment="true" keys="key"
	 * @generated
	 */
	EList<TestId> getTestIds();

	/**
	 * Returns the value of the '<em><b>Root</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link us.coastalhacking.corvus.test.util.TestRoot#getTestIds <em>Test Ids</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Root</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Root</em>' container reference.
	 * @see #setRoot(TestRoot)
	 * @see us.coastalhacking.corvus.test.util.UtilPackage#getTestIds_Root()
	 * @see us.coastalhacking.corvus.test.util.TestRoot#getTestIds
	 * @model opposite="testIds" transient="false"
	 * @generated
	 */
	TestRoot getRoot();

	/**
	 * Sets the value of the '{@link us.coastalhacking.corvus.test.util.TestIds#getRoot <em>Root</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Root</em>' container reference.
	 * @see #getRoot()
	 * @generated
	 */
	void setRoot(TestRoot value);

} // TestIds

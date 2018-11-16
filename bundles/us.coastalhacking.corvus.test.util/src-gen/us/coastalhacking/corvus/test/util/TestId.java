/**
 */
package us.coastalhacking.corvus.test.util;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test Id</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link us.coastalhacking.corvus.test.util.TestId#getIntrinsicId <em>Intrinsic Id</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.test.util.TestId#getKey <em>Key</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.test.util.TestId#getRoot <em>Root</em>}</li>
 * </ul>
 *
 * @see us.coastalhacking.corvus.test.util.UtilPackage#getTestId()
 * @model
 * @generated
 */
public interface TestId extends EObject {
	/**
	 * Returns the value of the '<em><b>Intrinsic Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Intrinsic Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Intrinsic Id</em>' attribute.
	 * @see #setIntrinsicId(String)
	 * @see us.coastalhacking.corvus.test.util.UtilPackage#getTestId_IntrinsicId()
	 * @model unique="false" id="true"
	 * @generated
	 */
	String getIntrinsicId();

	/**
	 * Sets the value of the '{@link us.coastalhacking.corvus.test.util.TestId#getIntrinsicId <em>Intrinsic Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Intrinsic Id</em>' attribute.
	 * @see #getIntrinsicId()
	 * @generated
	 */
	void setIntrinsicId(String value);

	/**
	 * Returns the value of the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Key</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Key</em>' attribute.
	 * @see #setKey(String)
	 * @see us.coastalhacking.corvus.test.util.UtilPackage#getTestId_Key()
	 * @model unique="false"
	 * @generated
	 */
	String getKey();

	/**
	 * Sets the value of the '{@link us.coastalhacking.corvus.test.util.TestId#getKey <em>Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Key</em>' attribute.
	 * @see #getKey()
	 * @generated
	 */
	void setKey(String value);

	/**
	 * Returns the value of the '<em><b>Root</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link us.coastalhacking.corvus.test.util.TestIds#getTestIds <em>Test Ids</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Root</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Root</em>' container reference.
	 * @see #setRoot(TestIds)
	 * @see us.coastalhacking.corvus.test.util.UtilPackage#getTestId_Root()
	 * @see us.coastalhacking.corvus.test.util.TestIds#getTestIds
	 * @model opposite="testIds" transient="false"
	 * @generated
	 */
	TestIds getRoot();

	/**
	 * Sets the value of the '{@link us.coastalhacking.corvus.test.util.TestId#getRoot <em>Root</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Root</em>' container reference.
	 * @see #getRoot()
	 * @generated
	 */
	void setRoot(TestIds value);

} // TestId

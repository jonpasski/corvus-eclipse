/**
 */
package us.coastalhacking.corvus.test.util;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link us.coastalhacking.corvus.test.util.TestRoot#getTestIds <em>Test Ids</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.test.util.TestRoot#getTestReferences <em>Test References</em>}</li>
 * </ul>
 *
 * @see us.coastalhacking.corvus.test.util.UtilPackage#getTestRoot()
 * @model
 * @generated
 */
public interface TestRoot extends EObject {
	/**
	 * Returns the value of the '<em><b>Test Ids</b></em>' containment reference list.
	 * The list contents are of type {@link us.coastalhacking.corvus.test.util.TestIds}.
	 * It is bidirectional and its opposite is '{@link us.coastalhacking.corvus.test.util.TestIds#getRoot <em>Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Test Ids</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test Ids</em>' containment reference list.
	 * @see us.coastalhacking.corvus.test.util.UtilPackage#getTestRoot_TestIds()
	 * @see us.coastalhacking.corvus.test.util.TestIds#getRoot
	 * @model opposite="root" containment="true"
	 * @generated
	 */
	EList<TestIds> getTestIds();

	/**
	 * Returns the value of the '<em><b>Test References</b></em>' containment reference list.
	 * The list contents are of type {@link us.coastalhacking.corvus.test.util.TestReference}.
	 * It is bidirectional and its opposite is '{@link us.coastalhacking.corvus.test.util.TestReference#getRoot <em>Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Test References</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Test References</em>' containment reference list.
	 * @see us.coastalhacking.corvus.test.util.UtilPackage#getTestRoot_TestReferences()
	 * @see us.coastalhacking.corvus.test.util.TestReference#getRoot
	 * @model opposite="root" containment="true"
	 * @generated
	 */
	EList<TestReference> getTestReferences();

} // TestRoot

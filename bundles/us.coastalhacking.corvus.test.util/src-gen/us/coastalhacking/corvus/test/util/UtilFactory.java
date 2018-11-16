/**
 */
package us.coastalhacking.corvus.test.util;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see us.coastalhacking.corvus.test.util.UtilPackage
 * @generated
 */
public interface UtilFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	UtilFactory eINSTANCE = us.coastalhacking.corvus.test.util.impl.UtilFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Test Root</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Root</em>'.
	 * @generated
	 */
	TestRoot createTestRoot();

	/**
	 * Returns a new object of class '<em>Test Ids</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Ids</em>'.
	 * @generated
	 */
	TestIds createTestIds();

	/**
	 * Returns a new object of class '<em>Test Id</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Test Id</em>'.
	 * @generated
	 */
	TestId createTestId();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	UtilPackage getUtilPackage();

} //UtilFactory

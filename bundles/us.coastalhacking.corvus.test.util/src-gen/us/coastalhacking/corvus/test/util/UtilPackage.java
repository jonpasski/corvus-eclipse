/**
 */
package us.coastalhacking.corvus.test.util;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see us.coastalhacking.corvus.test.util.UtilFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/emf/2002/GenModel basePackage='us.coastalhacking.corvus.test'"
 * @generated
 */
public interface UtilPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "util";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "us.coastalhacking.corvus.test.util";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "util";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	UtilPackage eINSTANCE = us.coastalhacking.corvus.test.util.impl.UtilPackageImpl.init();

	/**
	 * The meta object id for the '{@link us.coastalhacking.corvus.test.util.impl.TestRootImpl <em>Test Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see us.coastalhacking.corvus.test.util.impl.TestRootImpl
	 * @see us.coastalhacking.corvus.test.util.impl.UtilPackageImpl#getTestRoot()
	 * @generated
	 */
	int TEST_ROOT = 0;

	/**
	 * The number of structural features of the '<em>Test Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_ROOT_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Test Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_ROOT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link us.coastalhacking.corvus.test.util.impl.TestIdsImpl <em>Test Ids</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see us.coastalhacking.corvus.test.util.impl.TestIdsImpl
	 * @see us.coastalhacking.corvus.test.util.impl.UtilPackageImpl#getTestIds()
	 * @generated
	 */
	int TEST_IDS = 1;

	/**
	 * The feature id for the '<em><b>Test Ids</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_IDS__TEST_IDS = 0;

	/**
	 * The number of structural features of the '<em>Test Ids</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_IDS_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Test Ids</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_IDS_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link us.coastalhacking.corvus.test.util.impl.TestIdImpl <em>Test Id</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see us.coastalhacking.corvus.test.util.impl.TestIdImpl
	 * @see us.coastalhacking.corvus.test.util.impl.UtilPackageImpl#getTestId()
	 * @generated
	 */
	int TEST_ID = 2;

	/**
	 * The feature id for the '<em><b>Intrinsic Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_ID__INTRINSIC_ID = 0;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_ID__KEY = 1;

	/**
	 * The feature id for the '<em><b>Root</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_ID__ROOT = 2;

	/**
	 * The number of structural features of the '<em>Test Id</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_ID_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Test Id</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_ID_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link us.coastalhacking.corvus.test.util.TestRoot <em>Test Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Root</em>'.
	 * @see us.coastalhacking.corvus.test.util.TestRoot
	 * @generated
	 */
	EClass getTestRoot();

	/**
	 * Returns the meta object for class '{@link us.coastalhacking.corvus.test.util.TestIds <em>Test Ids</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Ids</em>'.
	 * @see us.coastalhacking.corvus.test.util.TestIds
	 * @generated
	 */
	EClass getTestIds();

	/**
	 * Returns the meta object for the containment reference list '{@link us.coastalhacking.corvus.test.util.TestIds#getTestIds <em>Test Ids</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Test Ids</em>'.
	 * @see us.coastalhacking.corvus.test.util.TestIds#getTestIds()
	 * @see #getTestIds()
	 * @generated
	 */
	EReference getTestIds_TestIds();

	/**
	 * Returns the meta object for class '{@link us.coastalhacking.corvus.test.util.TestId <em>Test Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Id</em>'.
	 * @see us.coastalhacking.corvus.test.util.TestId
	 * @generated
	 */
	EClass getTestId();

	/**
	 * Returns the meta object for the attribute '{@link us.coastalhacking.corvus.test.util.TestId#getIntrinsicId <em>Intrinsic Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Intrinsic Id</em>'.
	 * @see us.coastalhacking.corvus.test.util.TestId#getIntrinsicId()
	 * @see #getTestId()
	 * @generated
	 */
	EAttribute getTestId_IntrinsicId();

	/**
	 * Returns the meta object for the attribute '{@link us.coastalhacking.corvus.test.util.TestId#getKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see us.coastalhacking.corvus.test.util.TestId#getKey()
	 * @see #getTestId()
	 * @generated
	 */
	EAttribute getTestId_Key();

	/**
	 * Returns the meta object for the container reference '{@link us.coastalhacking.corvus.test.util.TestId#getRoot <em>Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Root</em>'.
	 * @see us.coastalhacking.corvus.test.util.TestId#getRoot()
	 * @see #getTestId()
	 * @generated
	 */
	EReference getTestId_Root();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	UtilFactory getUtilFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link us.coastalhacking.corvus.test.util.impl.TestRootImpl <em>Test Root</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see us.coastalhacking.corvus.test.util.impl.TestRootImpl
		 * @see us.coastalhacking.corvus.test.util.impl.UtilPackageImpl#getTestRoot()
		 * @generated
		 */
		EClass TEST_ROOT = eINSTANCE.getTestRoot();

		/**
		 * The meta object literal for the '{@link us.coastalhacking.corvus.test.util.impl.TestIdsImpl <em>Test Ids</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see us.coastalhacking.corvus.test.util.impl.TestIdsImpl
		 * @see us.coastalhacking.corvus.test.util.impl.UtilPackageImpl#getTestIds()
		 * @generated
		 */
		EClass TEST_IDS = eINSTANCE.getTestIds();

		/**
		 * The meta object literal for the '<em><b>Test Ids</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_IDS__TEST_IDS = eINSTANCE.getTestIds_TestIds();

		/**
		 * The meta object literal for the '{@link us.coastalhacking.corvus.test.util.impl.TestIdImpl <em>Test Id</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see us.coastalhacking.corvus.test.util.impl.TestIdImpl
		 * @see us.coastalhacking.corvus.test.util.impl.UtilPackageImpl#getTestId()
		 * @generated
		 */
		EClass TEST_ID = eINSTANCE.getTestId();

		/**
		 * The meta object literal for the '<em><b>Intrinsic Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_ID__INTRINSIC_ID = eINSTANCE.getTestId_IntrinsicId();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TEST_ID__KEY = eINSTANCE.getTestId_Key();

		/**
		 * The meta object literal for the '<em><b>Root</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_ID__ROOT = eINSTANCE.getTestId_Root();

	}

} //UtilPackage

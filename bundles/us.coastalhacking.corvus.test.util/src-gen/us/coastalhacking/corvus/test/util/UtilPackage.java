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
 *        annotation="http://www.eclipse.org/emf/2002/GenModel childCreationExtenders='true' editDirectory='/us.coastalhacking.corvus.test.util/src-gen' modelPluginVariables='org.eclipse.xtext.xbase.lib org.eclipse.emf.ecore.xcore.lib org.eclipse.core.resources' basePackage='us.coastalhacking.corvus.test'"
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
	 * The feature id for the '<em><b>Test Ids</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_ROOT__TEST_IDS = 0;

	/**
	 * The feature id for the '<em><b>Test References</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_ROOT__TEST_REFERENCES = 1;

	/**
	 * The number of structural features of the '<em>Test Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_ROOT_FEATURE_COUNT = 2;

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
	 * The feature id for the '<em><b>Root</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_IDS__ROOT = 1;

	/**
	 * The number of structural features of the '<em>Test Ids</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_IDS_FEATURE_COUNT = 2;

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
	 * The feature id for the '<em><b>Test Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_ID__TEST_REFERENCE = 3;

	/**
	 * The number of structural features of the '<em>Test Id</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_ID_FEATURE_COUNT = 4;

	/**
	 * The number of operations of the '<em>Test Id</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_ID_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link us.coastalhacking.corvus.test.util.impl.TestReferenceImpl <em>Test Reference</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see us.coastalhacking.corvus.test.util.impl.TestReferenceImpl
	 * @see us.coastalhacking.corvus.test.util.impl.UtilPackageImpl#getTestReference()
	 * @generated
	 */
	int TEST_REFERENCE = 3;

	/**
	 * The feature id for the '<em><b>Root</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_REFERENCE__ROOT = 0;

	/**
	 * The feature id for the '<em><b>Test Id</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_REFERENCE__TEST_ID = 1;

	/**
	 * The number of structural features of the '<em>Test Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_REFERENCE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Test Reference</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TEST_REFERENCE_OPERATION_COUNT = 0;


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
	 * Returns the meta object for the containment reference list '{@link us.coastalhacking.corvus.test.util.TestRoot#getTestIds <em>Test Ids</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Test Ids</em>'.
	 * @see us.coastalhacking.corvus.test.util.TestRoot#getTestIds()
	 * @see #getTestRoot()
	 * @generated
	 */
	EReference getTestRoot_TestIds();

	/**
	 * Returns the meta object for the containment reference list '{@link us.coastalhacking.corvus.test.util.TestRoot#getTestReferences <em>Test References</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Test References</em>'.
	 * @see us.coastalhacking.corvus.test.util.TestRoot#getTestReferences()
	 * @see #getTestRoot()
	 * @generated
	 */
	EReference getTestRoot_TestReferences();

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
	 * Returns the meta object for the container reference '{@link us.coastalhacking.corvus.test.util.TestIds#getRoot <em>Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Root</em>'.
	 * @see us.coastalhacking.corvus.test.util.TestIds#getRoot()
	 * @see #getTestIds()
	 * @generated
	 */
	EReference getTestIds_Root();

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
	 * Returns the meta object for the reference '{@link us.coastalhacking.corvus.test.util.TestId#getTestReference <em>Test Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Test Reference</em>'.
	 * @see us.coastalhacking.corvus.test.util.TestId#getTestReference()
	 * @see #getTestId()
	 * @generated
	 */
	EReference getTestId_TestReference();

	/**
	 * Returns the meta object for class '{@link us.coastalhacking.corvus.test.util.TestReference <em>Test Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Test Reference</em>'.
	 * @see us.coastalhacking.corvus.test.util.TestReference
	 * @generated
	 */
	EClass getTestReference();

	/**
	 * Returns the meta object for the container reference '{@link us.coastalhacking.corvus.test.util.TestReference#getRoot <em>Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Root</em>'.
	 * @see us.coastalhacking.corvus.test.util.TestReference#getRoot()
	 * @see #getTestReference()
	 * @generated
	 */
	EReference getTestReference_Root();

	/**
	 * Returns the meta object for the reference '{@link us.coastalhacking.corvus.test.util.TestReference#getTestId <em>Test Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Test Id</em>'.
	 * @see us.coastalhacking.corvus.test.util.TestReference#getTestId()
	 * @see #getTestReference()
	 * @generated
	 */
	EReference getTestReference_TestId();

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
		 * The meta object literal for the '<em><b>Test Ids</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_ROOT__TEST_IDS = eINSTANCE.getTestRoot_TestIds();

		/**
		 * The meta object literal for the '<em><b>Test References</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_ROOT__TEST_REFERENCES = eINSTANCE.getTestRoot_TestReferences();

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
		 * The meta object literal for the '<em><b>Root</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_IDS__ROOT = eINSTANCE.getTestIds_Root();

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

		/**
		 * The meta object literal for the '<em><b>Test Reference</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_ID__TEST_REFERENCE = eINSTANCE.getTestId_TestReference();

		/**
		 * The meta object literal for the '{@link us.coastalhacking.corvus.test.util.impl.TestReferenceImpl <em>Test Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see us.coastalhacking.corvus.test.util.impl.TestReferenceImpl
		 * @see us.coastalhacking.corvus.test.util.impl.UtilPackageImpl#getTestReference()
		 * @generated
		 */
		EClass TEST_REFERENCE = eINSTANCE.getTestReference();

		/**
		 * The meta object literal for the '<em><b>Root</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_REFERENCE__ROOT = eINSTANCE.getTestReference_Root();

		/**
		 * The meta object literal for the '<em><b>Test Id</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEST_REFERENCE__TEST_ID = eINSTANCE.getTestReference_TestId();

	}

} //UtilPackage

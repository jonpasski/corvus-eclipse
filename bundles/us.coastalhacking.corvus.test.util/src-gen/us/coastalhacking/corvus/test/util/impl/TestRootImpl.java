/**
 */
package us.coastalhacking.corvus.test.util.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import us.coastalhacking.corvus.test.util.TestIds;
import us.coastalhacking.corvus.test.util.TestReference;
import us.coastalhacking.corvus.test.util.TestRoot;
import us.coastalhacking.corvus.test.util.UtilPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link us.coastalhacking.corvus.test.util.impl.TestRootImpl#getTestIds <em>Test Ids</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.test.util.impl.TestRootImpl#getTestReferences <em>Test References</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TestRootImpl extends MinimalEObjectImpl.Container implements TestRoot {
	/**
	 * The cached value of the '{@link #getTestIds() <em>Test Ids</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestIds()
	 * @generated
	 * @ordered
	 */
	protected EList<TestIds> testIds;

	/**
	 * The cached value of the '{@link #getTestReferences() <em>Test References</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestReferences()
	 * @generated
	 * @ordered
	 */
	protected EList<TestReference> testReferences;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestRootImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UtilPackage.Literals.TEST_ROOT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TestIds> getTestIds() {
		if (testIds == null) {
			testIds = new EObjectContainmentWithInverseEList<TestIds>(TestIds.class, this, UtilPackage.TEST_ROOT__TEST_IDS, UtilPackage.TEST_IDS__ROOT);
		}
		return testIds;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TestReference> getTestReferences() {
		if (testReferences == null) {
			testReferences = new EObjectContainmentWithInverseEList<TestReference>(TestReference.class, this, UtilPackage.TEST_ROOT__TEST_REFERENCES, UtilPackage.TEST_REFERENCE__ROOT);
		}
		return testReferences;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UtilPackage.TEST_ROOT__TEST_IDS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTestIds()).basicAdd(otherEnd, msgs);
			case UtilPackage.TEST_ROOT__TEST_REFERENCES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTestReferences()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UtilPackage.TEST_ROOT__TEST_IDS:
				return ((InternalEList<?>)getTestIds()).basicRemove(otherEnd, msgs);
			case UtilPackage.TEST_ROOT__TEST_REFERENCES:
				return ((InternalEList<?>)getTestReferences()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case UtilPackage.TEST_ROOT__TEST_IDS:
				return getTestIds();
			case UtilPackage.TEST_ROOT__TEST_REFERENCES:
				return getTestReferences();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case UtilPackage.TEST_ROOT__TEST_IDS:
				getTestIds().clear();
				getTestIds().addAll((Collection<? extends TestIds>)newValue);
				return;
			case UtilPackage.TEST_ROOT__TEST_REFERENCES:
				getTestReferences().clear();
				getTestReferences().addAll((Collection<? extends TestReference>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case UtilPackage.TEST_ROOT__TEST_IDS:
				getTestIds().clear();
				return;
			case UtilPackage.TEST_ROOT__TEST_REFERENCES:
				getTestReferences().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case UtilPackage.TEST_ROOT__TEST_IDS:
				return testIds != null && !testIds.isEmpty();
			case UtilPackage.TEST_ROOT__TEST_REFERENCES:
				return testReferences != null && !testReferences.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TestRootImpl

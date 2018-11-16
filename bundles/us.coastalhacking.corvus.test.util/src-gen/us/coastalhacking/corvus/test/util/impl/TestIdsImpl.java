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

import us.coastalhacking.corvus.test.util.TestId;
import us.coastalhacking.corvus.test.util.TestIds;
import us.coastalhacking.corvus.test.util.UtilPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Ids</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link us.coastalhacking.corvus.test.util.impl.TestIdsImpl#getTestIds <em>Test Ids</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TestIdsImpl extends MinimalEObjectImpl.Container implements TestIds {
	/**
	 * The cached value of the '{@link #getTestIds() <em>Test Ids</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestIds()
	 * @generated
	 * @ordered
	 */
	protected EList<TestId> testIds;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestIdsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UtilPackage.Literals.TEST_IDS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TestId> getTestIds() {
		if (testIds == null) {
			testIds = new EObjectContainmentWithInverseEList<TestId>(TestId.class, this, UtilPackage.TEST_IDS__TEST_IDS, UtilPackage.TEST_ID__ROOT);
		}
		return testIds;
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
			case UtilPackage.TEST_IDS__TEST_IDS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getTestIds()).basicAdd(otherEnd, msgs);
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
			case UtilPackage.TEST_IDS__TEST_IDS:
				return ((InternalEList<?>)getTestIds()).basicRemove(otherEnd, msgs);
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
			case UtilPackage.TEST_IDS__TEST_IDS:
				return getTestIds();
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
			case UtilPackage.TEST_IDS__TEST_IDS:
				getTestIds().clear();
				getTestIds().addAll((Collection<? extends TestId>)newValue);
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
			case UtilPackage.TEST_IDS__TEST_IDS:
				getTestIds().clear();
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
			case UtilPackage.TEST_IDS__TEST_IDS:
				return testIds != null && !testIds.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TestIdsImpl

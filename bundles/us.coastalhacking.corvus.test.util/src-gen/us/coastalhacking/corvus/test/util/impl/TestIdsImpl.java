/**
 */
package us.coastalhacking.corvus.test.util.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import us.coastalhacking.corvus.test.util.TestId;
import us.coastalhacking.corvus.test.util.TestIds;
import us.coastalhacking.corvus.test.util.TestRoot;
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
 *   <li>{@link us.coastalhacking.corvus.test.util.impl.TestIdsImpl#getRoot <em>Root</em>}</li>
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
	public TestRoot getRoot() {
		if (eContainerFeatureID() != UtilPackage.TEST_IDS__ROOT) return null;
		return (TestRoot)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestRoot basicGetRoot() {
		if (eContainerFeatureID() != UtilPackage.TEST_IDS__ROOT) return null;
		return (TestRoot)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRoot(TestRoot newRoot, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newRoot, UtilPackage.TEST_IDS__ROOT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRoot(TestRoot newRoot) {
		if (newRoot != eInternalContainer() || (eContainerFeatureID() != UtilPackage.TEST_IDS__ROOT && newRoot != null)) {
			if (EcoreUtil.isAncestor(this, newRoot))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newRoot != null)
				msgs = ((InternalEObject)newRoot).eInverseAdd(this, UtilPackage.TEST_ROOT__TEST_IDS, TestRoot.class, msgs);
			msgs = basicSetRoot(newRoot, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UtilPackage.TEST_IDS__ROOT, newRoot, newRoot));
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
			case UtilPackage.TEST_IDS__ROOT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetRoot((TestRoot)otherEnd, msgs);
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
			case UtilPackage.TEST_IDS__ROOT:
				return basicSetRoot(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case UtilPackage.TEST_IDS__ROOT:
				return eInternalContainer().eInverseRemove(this, UtilPackage.TEST_ROOT__TEST_IDS, TestRoot.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
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
			case UtilPackage.TEST_IDS__ROOT:
				if (resolve) return getRoot();
				return basicGetRoot();
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
			case UtilPackage.TEST_IDS__ROOT:
				setRoot((TestRoot)newValue);
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
			case UtilPackage.TEST_IDS__ROOT:
				setRoot((TestRoot)null);
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
			case UtilPackage.TEST_IDS__ROOT:
				return basicGetRoot() != null;
		}
		return super.eIsSet(featureID);
	}

} //TestIdsImpl

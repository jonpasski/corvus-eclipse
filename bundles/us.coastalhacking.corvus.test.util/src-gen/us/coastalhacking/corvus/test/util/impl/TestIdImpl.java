/**
 */
package us.coastalhacking.corvus.test.util.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import us.coastalhacking.corvus.test.util.TestId;
import us.coastalhacking.corvus.test.util.TestIds;
import us.coastalhacking.corvus.test.util.UtilPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Id</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link us.coastalhacking.corvus.test.util.impl.TestIdImpl#getIntrinsicId <em>Intrinsic Id</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.test.util.impl.TestIdImpl#getKey <em>Key</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.test.util.impl.TestIdImpl#getRoot <em>Root</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TestIdImpl extends MinimalEObjectImpl.Container implements TestId {
	/**
	 * The default value of the '{@link #getIntrinsicId() <em>Intrinsic Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIntrinsicId()
	 * @generated
	 * @ordered
	 */
	protected static final String INTRINSIC_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIntrinsicId() <em>Intrinsic Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIntrinsicId()
	 * @generated
	 * @ordered
	 */
	protected String intrinsicId = INTRINSIC_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getKey() <em>Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKey()
	 * @generated
	 * @ordered
	 */
	protected static final String KEY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getKey() <em>Key</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKey()
	 * @generated
	 * @ordered
	 */
	protected String key = KEY_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestIdImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UtilPackage.Literals.TEST_ID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIntrinsicId() {
		return intrinsicId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIntrinsicId(String newIntrinsicId) {
		String oldIntrinsicId = intrinsicId;
		intrinsicId = newIntrinsicId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UtilPackage.TEST_ID__INTRINSIC_ID, oldIntrinsicId, intrinsicId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getKey() {
		return key;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKey(String newKey) {
		String oldKey = key;
		key = newKey;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UtilPackage.TEST_ID__KEY, oldKey, key));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestIds getRoot() {
		if (eContainerFeatureID() != UtilPackage.TEST_ID__ROOT) return null;
		return (TestIds)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestIds basicGetRoot() {
		if (eContainerFeatureID() != UtilPackage.TEST_ID__ROOT) return null;
		return (TestIds)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRoot(TestIds newRoot, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newRoot, UtilPackage.TEST_ID__ROOT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRoot(TestIds newRoot) {
		if (newRoot != eInternalContainer() || (eContainerFeatureID() != UtilPackage.TEST_ID__ROOT && newRoot != null)) {
			if (EcoreUtil.isAncestor(this, newRoot))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newRoot != null)
				msgs = ((InternalEObject)newRoot).eInverseAdd(this, UtilPackage.TEST_IDS__TEST_IDS, TestIds.class, msgs);
			msgs = basicSetRoot(newRoot, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UtilPackage.TEST_ID__ROOT, newRoot, newRoot));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UtilPackage.TEST_ID__ROOT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetRoot((TestIds)otherEnd, msgs);
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
			case UtilPackage.TEST_ID__ROOT:
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
			case UtilPackage.TEST_ID__ROOT:
				return eInternalContainer().eInverseRemove(this, UtilPackage.TEST_IDS__TEST_IDS, TestIds.class, msgs);
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
			case UtilPackage.TEST_ID__INTRINSIC_ID:
				return getIntrinsicId();
			case UtilPackage.TEST_ID__KEY:
				return getKey();
			case UtilPackage.TEST_ID__ROOT:
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
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case UtilPackage.TEST_ID__INTRINSIC_ID:
				setIntrinsicId((String)newValue);
				return;
			case UtilPackage.TEST_ID__KEY:
				setKey((String)newValue);
				return;
			case UtilPackage.TEST_ID__ROOT:
				setRoot((TestIds)newValue);
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
			case UtilPackage.TEST_ID__INTRINSIC_ID:
				setIntrinsicId(INTRINSIC_ID_EDEFAULT);
				return;
			case UtilPackage.TEST_ID__KEY:
				setKey(KEY_EDEFAULT);
				return;
			case UtilPackage.TEST_ID__ROOT:
				setRoot((TestIds)null);
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
			case UtilPackage.TEST_ID__INTRINSIC_ID:
				return INTRINSIC_ID_EDEFAULT == null ? intrinsicId != null : !INTRINSIC_ID_EDEFAULT.equals(intrinsicId);
			case UtilPackage.TEST_ID__KEY:
				return KEY_EDEFAULT == null ? key != null : !KEY_EDEFAULT.equals(key);
			case UtilPackage.TEST_ID__ROOT:
				return basicGetRoot() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (intrinsicId: ");
		result.append(intrinsicId);
		result.append(", key: ");
		result.append(key);
		result.append(')');
		return result.toString();
	}

} //TestIdImpl

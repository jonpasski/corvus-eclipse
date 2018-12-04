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
import us.coastalhacking.corvus.test.util.TestReference;
import us.coastalhacking.corvus.test.util.TestRoot;
import us.coastalhacking.corvus.test.util.UtilPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Test Reference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link us.coastalhacking.corvus.test.util.impl.TestReferenceImpl#getRoot <em>Root</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.test.util.impl.TestReferenceImpl#getTestId <em>Test Id</em>}</li>
 * </ul>
 *
 * @generated
 */
public class TestReferenceImpl extends MinimalEObjectImpl.Container implements TestReference {
	/**
	 * The cached value of the '{@link #getTestId() <em>Test Id</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTestId()
	 * @generated
	 * @ordered
	 */
	protected TestId testId;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestReferenceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return UtilPackage.Literals.TEST_REFERENCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestRoot getRoot() {
		if (eContainerFeatureID() != UtilPackage.TEST_REFERENCE__ROOT) return null;
		return (TestRoot)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestRoot basicGetRoot() {
		if (eContainerFeatureID() != UtilPackage.TEST_REFERENCE__ROOT) return null;
		return (TestRoot)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRoot(TestRoot newRoot, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newRoot, UtilPackage.TEST_REFERENCE__ROOT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRoot(TestRoot newRoot) {
		if (newRoot != eInternalContainer() || (eContainerFeatureID() != UtilPackage.TEST_REFERENCE__ROOT && newRoot != null)) {
			if (EcoreUtil.isAncestor(this, newRoot))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newRoot != null)
				msgs = ((InternalEObject)newRoot).eInverseAdd(this, UtilPackage.TEST_ROOT__TEST_REFERENCES, TestRoot.class, msgs);
			msgs = basicSetRoot(newRoot, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UtilPackage.TEST_REFERENCE__ROOT, newRoot, newRoot));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestId getTestId() {
		if (testId != null && testId.eIsProxy()) {
			InternalEObject oldTestId = (InternalEObject)testId;
			testId = (TestId)eResolveProxy(oldTestId);
			if (testId != oldTestId) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, UtilPackage.TEST_REFERENCE__TEST_ID, oldTestId, testId));
			}
		}
		return testId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestId basicGetTestId() {
		return testId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTestId(TestId newTestId, NotificationChain msgs) {
		TestId oldTestId = testId;
		testId = newTestId;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, UtilPackage.TEST_REFERENCE__TEST_ID, oldTestId, newTestId);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTestId(TestId newTestId) {
		if (newTestId != testId) {
			NotificationChain msgs = null;
			if (testId != null)
				msgs = ((InternalEObject)testId).eInverseRemove(this, UtilPackage.TEST_ID__TEST_REFERENCE, TestId.class, msgs);
			if (newTestId != null)
				msgs = ((InternalEObject)newTestId).eInverseAdd(this, UtilPackage.TEST_ID__TEST_REFERENCE, TestId.class, msgs);
			msgs = basicSetTestId(newTestId, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, UtilPackage.TEST_REFERENCE__TEST_ID, newTestId, newTestId));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case UtilPackage.TEST_REFERENCE__ROOT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetRoot((TestRoot)otherEnd, msgs);
			case UtilPackage.TEST_REFERENCE__TEST_ID:
				if (testId != null)
					msgs = ((InternalEObject)testId).eInverseRemove(this, UtilPackage.TEST_ID__TEST_REFERENCE, TestId.class, msgs);
				return basicSetTestId((TestId)otherEnd, msgs);
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
			case UtilPackage.TEST_REFERENCE__ROOT:
				return basicSetRoot(null, msgs);
			case UtilPackage.TEST_REFERENCE__TEST_ID:
				return basicSetTestId(null, msgs);
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
			case UtilPackage.TEST_REFERENCE__ROOT:
				return eInternalContainer().eInverseRemove(this, UtilPackage.TEST_ROOT__TEST_REFERENCES, TestRoot.class, msgs);
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
			case UtilPackage.TEST_REFERENCE__ROOT:
				if (resolve) return getRoot();
				return basicGetRoot();
			case UtilPackage.TEST_REFERENCE__TEST_ID:
				if (resolve) return getTestId();
				return basicGetTestId();
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
			case UtilPackage.TEST_REFERENCE__ROOT:
				setRoot((TestRoot)newValue);
				return;
			case UtilPackage.TEST_REFERENCE__TEST_ID:
				setTestId((TestId)newValue);
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
			case UtilPackage.TEST_REFERENCE__ROOT:
				setRoot((TestRoot)null);
				return;
			case UtilPackage.TEST_REFERENCE__TEST_ID:
				setTestId((TestId)null);
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
			case UtilPackage.TEST_REFERENCE__ROOT:
				return basicGetRoot() != null;
			case UtilPackage.TEST_REFERENCE__TEST_ID:
				return testId != null;
		}
		return super.eIsSet(featureID);
	}

} //TestReferenceImpl

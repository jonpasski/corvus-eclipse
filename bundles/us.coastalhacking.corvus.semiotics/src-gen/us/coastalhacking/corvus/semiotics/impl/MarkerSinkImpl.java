/**
 */
package us.coastalhacking.corvus.semiotics.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import us.coastalhacking.corvus.semiotics.MarkerSink;
import us.coastalhacking.corvus.semiotics.Root;
import us.coastalhacking.corvus.semiotics.Semiotics;
import us.coastalhacking.corvus.semiotics.SemioticsPackage;
import us.coastalhacking.corvus.semiotics.Signified;
import us.coastalhacking.corvus.semiotics.Signifier;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Marker Sink</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link us.coastalhacking.corvus.semiotics.impl.MarkerSinkImpl#getKey <em>Key</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.semiotics.impl.MarkerSinkImpl#getSignifieds <em>Signifieds</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.semiotics.impl.MarkerSinkImpl#getUuid <em>Uuid</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.semiotics.impl.MarkerSinkImpl#getSignifiers <em>Signifiers</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.semiotics.impl.MarkerSinkImpl#getRoot <em>Root</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MarkerSinkImpl extends MinimalEObjectImpl.Container implements MarkerSink {
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
	 * The cached value of the '{@link #getSignifieds() <em>Signifieds</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSignifieds()
	 * @generated
	 * @ordered
	 */
	protected EList<Signified> signifieds;

	/**
	 * The default value of the '{@link #getUuid() <em>Uuid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUuid()
	 * @generated
	 * @ordered
	 */
	protected static final String UUID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUuid() <em>Uuid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUuid()
	 * @generated
	 * @ordered
	 */
	protected String uuid = UUID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSignifiers() <em>Signifiers</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSignifiers()
	 * @generated
	 * @ordered
	 */
	protected EList<Signifier> signifiers;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MarkerSinkImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SemioticsPackage.Literals.MARKER_SINK;
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
			eNotify(new ENotificationImpl(this, Notification.SET, SemioticsPackage.MARKER_SINK__KEY, oldKey, key));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Signified> getSignifieds() {
		if (signifieds == null) {
			signifieds = new EObjectWithInverseResolvingEList.ManyInverse<Signified>(Signified.class, this, SemioticsPackage.MARKER_SINK__SIGNIFIEDS, SemioticsPackage.SIGNIFIED__SIGNIFIERS);
		}
		return signifieds;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUuid(String newUuid) {
		String oldUuid = uuid;
		uuid = newUuid;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SemioticsPackage.MARKER_SINK__UUID, oldUuid, uuid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Signifier> getSignifiers() {
		if (signifiers == null) {
			signifiers = new EObjectWithInverseResolvingEList.ManyInverse<Signifier>(Signifier.class, this, SemioticsPackage.MARKER_SINK__SIGNIFIERS, SemioticsPackage.SIGNIFIER__SIGNIFIEDS);
		}
		return signifiers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Root getRoot() {
		if (eContainerFeatureID() != SemioticsPackage.MARKER_SINK__ROOT) return null;
		return (Root)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Root basicGetRoot() {
		if (eContainerFeatureID() != SemioticsPackage.MARKER_SINK__ROOT) return null;
		return (Root)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRoot(Root newRoot, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newRoot, SemioticsPackage.MARKER_SINK__ROOT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRoot(Root newRoot) {
		if (newRoot != eInternalContainer() || (eContainerFeatureID() != SemioticsPackage.MARKER_SINK__ROOT && newRoot != null)) {
			if (EcoreUtil.isAncestor(this, newRoot))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newRoot != null)
				msgs = ((InternalEObject)newRoot).eInverseAdd(this, SemioticsPackage.ROOT__SEMIOTICS, Root.class, msgs);
			msgs = basicSetRoot(newRoot, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SemioticsPackage.MARKER_SINK__ROOT, newRoot, newRoot));
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
			case SemioticsPackage.MARKER_SINK__SIGNIFIEDS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSignifieds()).basicAdd(otherEnd, msgs);
			case SemioticsPackage.MARKER_SINK__SIGNIFIERS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSignifiers()).basicAdd(otherEnd, msgs);
			case SemioticsPackage.MARKER_SINK__ROOT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetRoot((Root)otherEnd, msgs);
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
			case SemioticsPackage.MARKER_SINK__SIGNIFIEDS:
				return ((InternalEList<?>)getSignifieds()).basicRemove(otherEnd, msgs);
			case SemioticsPackage.MARKER_SINK__SIGNIFIERS:
				return ((InternalEList<?>)getSignifiers()).basicRemove(otherEnd, msgs);
			case SemioticsPackage.MARKER_SINK__ROOT:
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
			case SemioticsPackage.MARKER_SINK__ROOT:
				return eInternalContainer().eInverseRemove(this, SemioticsPackage.ROOT__SEMIOTICS, Root.class, msgs);
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
			case SemioticsPackage.MARKER_SINK__KEY:
				return getKey();
			case SemioticsPackage.MARKER_SINK__SIGNIFIEDS:
				return getSignifieds();
			case SemioticsPackage.MARKER_SINK__UUID:
				return getUuid();
			case SemioticsPackage.MARKER_SINK__SIGNIFIERS:
				return getSignifiers();
			case SemioticsPackage.MARKER_SINK__ROOT:
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
			case SemioticsPackage.MARKER_SINK__KEY:
				setKey((String)newValue);
				return;
			case SemioticsPackage.MARKER_SINK__SIGNIFIEDS:
				getSignifieds().clear();
				getSignifieds().addAll((Collection<? extends Signified>)newValue);
				return;
			case SemioticsPackage.MARKER_SINK__UUID:
				setUuid((String)newValue);
				return;
			case SemioticsPackage.MARKER_SINK__SIGNIFIERS:
				getSignifiers().clear();
				getSignifiers().addAll((Collection<? extends Signifier>)newValue);
				return;
			case SemioticsPackage.MARKER_SINK__ROOT:
				setRoot((Root)newValue);
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
			case SemioticsPackage.MARKER_SINK__KEY:
				setKey(KEY_EDEFAULT);
				return;
			case SemioticsPackage.MARKER_SINK__SIGNIFIEDS:
				getSignifieds().clear();
				return;
			case SemioticsPackage.MARKER_SINK__UUID:
				setUuid(UUID_EDEFAULT);
				return;
			case SemioticsPackage.MARKER_SINK__SIGNIFIERS:
				getSignifiers().clear();
				return;
			case SemioticsPackage.MARKER_SINK__ROOT:
				setRoot((Root)null);
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
			case SemioticsPackage.MARKER_SINK__KEY:
				return KEY_EDEFAULT == null ? key != null : !KEY_EDEFAULT.equals(key);
			case SemioticsPackage.MARKER_SINK__SIGNIFIEDS:
				return signifieds != null && !signifieds.isEmpty();
			case SemioticsPackage.MARKER_SINK__UUID:
				return UUID_EDEFAULT == null ? uuid != null : !UUID_EDEFAULT.equals(uuid);
			case SemioticsPackage.MARKER_SINK__SIGNIFIERS:
				return signifiers != null && !signifiers.isEmpty();
			case SemioticsPackage.MARKER_SINK__ROOT:
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
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == Signifier.class) {
			switch (derivedFeatureID) {
				case SemioticsPackage.MARKER_SINK__KEY: return SemioticsPackage.SIGNIFIER__KEY;
				case SemioticsPackage.MARKER_SINK__SIGNIFIEDS: return SemioticsPackage.SIGNIFIER__SIGNIFIEDS;
				default: return -1;
			}
		}
		if (baseClass == Signified.class) {
			switch (derivedFeatureID) {
				case SemioticsPackage.MARKER_SINK__UUID: return SemioticsPackage.SIGNIFIED__UUID;
				case SemioticsPackage.MARKER_SINK__SIGNIFIERS: return SemioticsPackage.SIGNIFIED__SIGNIFIERS;
				default: return -1;
			}
		}
		if (baseClass == Semiotics.class) {
			switch (derivedFeatureID) {
				case SemioticsPackage.MARKER_SINK__ROOT: return SemioticsPackage.SEMIOTICS__ROOT;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == Signifier.class) {
			switch (baseFeatureID) {
				case SemioticsPackage.SIGNIFIER__KEY: return SemioticsPackage.MARKER_SINK__KEY;
				case SemioticsPackage.SIGNIFIER__SIGNIFIEDS: return SemioticsPackage.MARKER_SINK__SIGNIFIEDS;
				default: return -1;
			}
		}
		if (baseClass == Signified.class) {
			switch (baseFeatureID) {
				case SemioticsPackage.SIGNIFIED__UUID: return SemioticsPackage.MARKER_SINK__UUID;
				case SemioticsPackage.SIGNIFIED__SIGNIFIERS: return SemioticsPackage.MARKER_SINK__SIGNIFIERS;
				default: return -1;
			}
		}
		if (baseClass == Semiotics.class) {
			switch (baseFeatureID) {
				case SemioticsPackage.SEMIOTICS__ROOT: return SemioticsPackage.MARKER_SINK__ROOT;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(" (key: ");
		result.append(key);
		result.append(", uuid: ");
		result.append(uuid);
		result.append(')');
		return result.toString();
	}

} //MarkerSinkImpl

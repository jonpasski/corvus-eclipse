/**
 */
package us.coastalhacking.corvus.semiotics.impl;

import java.lang.reflect.InvocationTargetException;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import us.coastalhacking.corvus.semiotics.IMarker;
import us.coastalhacking.corvus.semiotics.IResource;
import us.coastalhacking.corvus.semiotics.IWorkspaceRoot;
import us.coastalhacking.corvus.semiotics.Root;
import us.coastalhacking.corvus.semiotics.SemioticsPackage;
import us.coastalhacking.corvus.semiotics.Signified;
import us.coastalhacking.corvus.semiotics.Signifier;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IResource</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link us.coastalhacking.corvus.semiotics.impl.IResourceImpl#getKey <em>Key</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.semiotics.impl.IResourceImpl#getSignifieds <em>Signifieds</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.semiotics.impl.IResourceImpl#getUuid <em>Uuid</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.semiotics.impl.IResourceImpl#getSignifiers <em>Signifiers</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.semiotics.impl.IResourceImpl#getRoot <em>Root</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.semiotics.impl.IResourceImpl#getFullPath <em>Full Path</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.semiotics.impl.IResourceImpl#getMarkers <em>Markers</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.semiotics.impl.IResourceImpl#getWorkspaceRoot <em>Workspace Root</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IResourceImpl extends MinimalEObjectImpl.Container implements IResource {
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
	 * The default value of the '{@link #getFullPath() <em>Full Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFullPath()
	 * @generated
	 * @ordered
	 */
	protected static final String FULL_PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFullPath() <em>Full Path</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFullPath()
	 * @generated
	 * @ordered
	 */
	protected String fullPath = FULL_PATH_EDEFAULT;

	/**
	 * The cached value of the '{@link #getMarkers() <em>Markers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMarkers()
	 * @generated
	 * @ordered
	 */
	protected EList<IMarker> markers;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IResourceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SemioticsPackage.Literals.IRESOURCE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getKey_() {
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
			eNotify(new ENotificationImpl(this, Notification.SET, SemioticsPackage.IRESOURCE__KEY, oldKey, key));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Signified> getSignifieds() {
		if (signifieds == null) {
			signifieds = new EObjectWithInverseResolvingEList.ManyInverse<Signified>(Signified.class, this, SemioticsPackage.IRESOURCE__SIGNIFIEDS, SemioticsPackage.SIGNIFIED__SIGNIFIERS);
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
			eNotify(new ENotificationImpl(this, Notification.SET, SemioticsPackage.IRESOURCE__UUID, oldUuid, uuid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Signifier> getSignifiers() {
		if (signifiers == null) {
			signifiers = new EObjectWithInverseResolvingEList.ManyInverse<Signifier>(Signifier.class, this, SemioticsPackage.IRESOURCE__SIGNIFIERS, SemioticsPackage.SIGNIFIER__SIGNIFIEDS);
		}
		return signifiers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Root getRoot() {
		if (eContainerFeatureID() != SemioticsPackage.IRESOURCE__ROOT) return null;
		return (Root)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Root basicGetRoot() {
		if (eContainerFeatureID() != SemioticsPackage.IRESOURCE__ROOT) return null;
		return (Root)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRoot(Root newRoot, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newRoot, SemioticsPackage.IRESOURCE__ROOT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRoot(Root newRoot) {
		if (newRoot != eInternalContainer() || (eContainerFeatureID() != SemioticsPackage.IRESOURCE__ROOT && newRoot != null)) {
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
			eNotify(new ENotificationImpl(this, Notification.SET, SemioticsPackage.IRESOURCE__ROOT, newRoot, newRoot));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getFullPath() {
		return fullPath;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFullPath(String newFullPath) {
		String oldFullPath = fullPath;
		fullPath = newFullPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SemioticsPackage.IRESOURCE__FULL_PATH, oldFullPath, fullPath));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IMarker> getMarkers() {
		if (markers == null) {
			markers = new EObjectContainmentWithInverseEList<IMarker>(IMarker.class, this, SemioticsPackage.IRESOURCE__MARKERS, SemioticsPackage.IMARKER__RESOURCE);
		}
		return markers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IWorkspaceRoot getWorkspaceRoot() {
		if (eContainerFeatureID() != SemioticsPackage.IRESOURCE__WORKSPACE_ROOT) return null;
		return (IWorkspaceRoot)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IWorkspaceRoot basicGetWorkspaceRoot() {
		if (eContainerFeatureID() != SemioticsPackage.IRESOURCE__WORKSPACE_ROOT) return null;
		return (IWorkspaceRoot)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWorkspaceRoot(IWorkspaceRoot newWorkspaceRoot, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newWorkspaceRoot, SemioticsPackage.IRESOURCE__WORKSPACE_ROOT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWorkspaceRoot(IWorkspaceRoot newWorkspaceRoot) {
		if (newWorkspaceRoot != eInternalContainer() || (eContainerFeatureID() != SemioticsPackage.IRESOURCE__WORKSPACE_ROOT && newWorkspaceRoot != null)) {
			if (EcoreUtil.isAncestor(this, newWorkspaceRoot))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newWorkspaceRoot != null)
				msgs = ((InternalEObject)newWorkspaceRoot).eInverseAdd(this, SemioticsPackage.IWORKSPACE_ROOT__MEMBERS, IWorkspaceRoot.class, msgs);
			msgs = basicSetWorkspaceRoot(newWorkspaceRoot, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SemioticsPackage.IRESOURCE__WORKSPACE_ROOT, newWorkspaceRoot, newWorkspaceRoot));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getKey() {
		return this.getFullPath();
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
			case SemioticsPackage.IRESOURCE__SIGNIFIEDS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSignifieds()).basicAdd(otherEnd, msgs);
			case SemioticsPackage.IRESOURCE__SIGNIFIERS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSignifiers()).basicAdd(otherEnd, msgs);
			case SemioticsPackage.IRESOURCE__ROOT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetRoot((Root)otherEnd, msgs);
			case SemioticsPackage.IRESOURCE__MARKERS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getMarkers()).basicAdd(otherEnd, msgs);
			case SemioticsPackage.IRESOURCE__WORKSPACE_ROOT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetWorkspaceRoot((IWorkspaceRoot)otherEnd, msgs);
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
			case SemioticsPackage.IRESOURCE__SIGNIFIEDS:
				return ((InternalEList<?>)getSignifieds()).basicRemove(otherEnd, msgs);
			case SemioticsPackage.IRESOURCE__SIGNIFIERS:
				return ((InternalEList<?>)getSignifiers()).basicRemove(otherEnd, msgs);
			case SemioticsPackage.IRESOURCE__ROOT:
				return basicSetRoot(null, msgs);
			case SemioticsPackage.IRESOURCE__MARKERS:
				return ((InternalEList<?>)getMarkers()).basicRemove(otherEnd, msgs);
			case SemioticsPackage.IRESOURCE__WORKSPACE_ROOT:
				return basicSetWorkspaceRoot(null, msgs);
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
			case SemioticsPackage.IRESOURCE__ROOT:
				return eInternalContainer().eInverseRemove(this, SemioticsPackage.ROOT__SEMIOTICS, Root.class, msgs);
			case SemioticsPackage.IRESOURCE__WORKSPACE_ROOT:
				return eInternalContainer().eInverseRemove(this, SemioticsPackage.IWORKSPACE_ROOT__MEMBERS, IWorkspaceRoot.class, msgs);
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
			case SemioticsPackage.IRESOURCE__KEY:
				return getKey();
			case SemioticsPackage.IRESOURCE__SIGNIFIEDS:
				return getSignifieds();
			case SemioticsPackage.IRESOURCE__UUID:
				return getUuid();
			case SemioticsPackage.IRESOURCE__SIGNIFIERS:
				return getSignifiers();
			case SemioticsPackage.IRESOURCE__ROOT:
				if (resolve) return getRoot();
				return basicGetRoot();
			case SemioticsPackage.IRESOURCE__FULL_PATH:
				return getFullPath();
			case SemioticsPackage.IRESOURCE__MARKERS:
				return getMarkers();
			case SemioticsPackage.IRESOURCE__WORKSPACE_ROOT:
				if (resolve) return getWorkspaceRoot();
				return basicGetWorkspaceRoot();
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
			case SemioticsPackage.IRESOURCE__KEY:
				setKey((String)newValue);
				return;
			case SemioticsPackage.IRESOURCE__SIGNIFIEDS:
				getSignifieds().clear();
				getSignifieds().addAll((Collection<? extends Signified>)newValue);
				return;
			case SemioticsPackage.IRESOURCE__UUID:
				setUuid((String)newValue);
				return;
			case SemioticsPackage.IRESOURCE__SIGNIFIERS:
				getSignifiers().clear();
				getSignifiers().addAll((Collection<? extends Signifier>)newValue);
				return;
			case SemioticsPackage.IRESOURCE__ROOT:
				setRoot((Root)newValue);
				return;
			case SemioticsPackage.IRESOURCE__FULL_PATH:
				setFullPath((String)newValue);
				return;
			case SemioticsPackage.IRESOURCE__MARKERS:
				getMarkers().clear();
				getMarkers().addAll((Collection<? extends IMarker>)newValue);
				return;
			case SemioticsPackage.IRESOURCE__WORKSPACE_ROOT:
				setWorkspaceRoot((IWorkspaceRoot)newValue);
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
			case SemioticsPackage.IRESOURCE__KEY:
				setKey(KEY_EDEFAULT);
				return;
			case SemioticsPackage.IRESOURCE__SIGNIFIEDS:
				getSignifieds().clear();
				return;
			case SemioticsPackage.IRESOURCE__UUID:
				setUuid(UUID_EDEFAULT);
				return;
			case SemioticsPackage.IRESOURCE__SIGNIFIERS:
				getSignifiers().clear();
				return;
			case SemioticsPackage.IRESOURCE__ROOT:
				setRoot((Root)null);
				return;
			case SemioticsPackage.IRESOURCE__FULL_PATH:
				setFullPath(FULL_PATH_EDEFAULT);
				return;
			case SemioticsPackage.IRESOURCE__MARKERS:
				getMarkers().clear();
				return;
			case SemioticsPackage.IRESOURCE__WORKSPACE_ROOT:
				setWorkspaceRoot((IWorkspaceRoot)null);
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
			case SemioticsPackage.IRESOURCE__KEY:
				return KEY_EDEFAULT == null ? key != null : !KEY_EDEFAULT.equals(key);
			case SemioticsPackage.IRESOURCE__SIGNIFIEDS:
				return signifieds != null && !signifieds.isEmpty();
			case SemioticsPackage.IRESOURCE__UUID:
				return UUID_EDEFAULT == null ? uuid != null : !UUID_EDEFAULT.equals(uuid);
			case SemioticsPackage.IRESOURCE__SIGNIFIERS:
				return signifiers != null && !signifiers.isEmpty();
			case SemioticsPackage.IRESOURCE__ROOT:
				return basicGetRoot() != null;
			case SemioticsPackage.IRESOURCE__FULL_PATH:
				return FULL_PATH_EDEFAULT == null ? fullPath != null : !FULL_PATH_EDEFAULT.equals(fullPath);
			case SemioticsPackage.IRESOURCE__MARKERS:
				return markers != null && !markers.isEmpty();
			case SemioticsPackage.IRESOURCE__WORKSPACE_ROOT:
				return basicGetWorkspaceRoot() != null;
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
		if (baseClass == Signified.class) {
			switch (derivedFeatureID) {
				case SemioticsPackage.IRESOURCE__UUID: return SemioticsPackage.SIGNIFIED__UUID;
				case SemioticsPackage.IRESOURCE__SIGNIFIERS: return SemioticsPackage.SIGNIFIED__SIGNIFIERS;
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
		if (baseClass == Signified.class) {
			switch (baseFeatureID) {
				case SemioticsPackage.SIGNIFIED__UUID: return SemioticsPackage.IRESOURCE__UUID;
				case SemioticsPackage.SIGNIFIED__SIGNIFIERS: return SemioticsPackage.IRESOURCE__SIGNIFIERS;
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
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
			case SemioticsPackage.IRESOURCE___GET_KEY:
				return getKey();
		}
		return super.eInvoke(operationID, arguments);
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
		result.append(", fullPath: ");
		result.append(fullPath);
		result.append(')');
		return result.toString();
	}

} //IResourceImpl

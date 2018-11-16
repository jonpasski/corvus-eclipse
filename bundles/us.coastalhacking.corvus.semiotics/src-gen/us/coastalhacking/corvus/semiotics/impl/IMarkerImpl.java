/**
 */
package us.coastalhacking.corvus.semiotics.impl;

import java.lang.reflect.InvocationTargetException;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import us.coastalhacking.corvus.semiotics.IMarker;
import us.coastalhacking.corvus.semiotics.IResource;
import us.coastalhacking.corvus.semiotics.Root;
import us.coastalhacking.corvus.semiotics.SemioticsPackage;
import us.coastalhacking.corvus.semiotics.Signified;
import us.coastalhacking.corvus.semiotics.Signifier;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>IMarker</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link us.coastalhacking.corvus.semiotics.impl.IMarkerImpl#getKey <em>Key</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.semiotics.impl.IMarkerImpl#getSignifieds <em>Signifieds</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.semiotics.impl.IMarkerImpl#getUuid <em>Uuid</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.semiotics.impl.IMarkerImpl#getSignifiers <em>Signifiers</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.semiotics.impl.IMarkerImpl#getRoot <em>Root</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.semiotics.impl.IMarkerImpl#getId <em>Id</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.semiotics.impl.IMarkerImpl#getCreationTime <em>Creation Time</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.semiotics.impl.IMarkerImpl#getType <em>Type</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.semiotics.impl.IMarkerImpl#getAttributes <em>Attributes</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.semiotics.impl.IMarkerImpl#getResource <em>Resource</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.semiotics.impl.IMarkerImpl#getMessage <em>Message</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.semiotics.impl.IMarkerImpl#getLocation <em>Location</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.semiotics.impl.IMarkerImpl#getCharStart <em>Char Start</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.semiotics.impl.IMarkerImpl#getCharEnd <em>Char End</em>}</li>
 *   <li>{@link us.coastalhacking.corvus.semiotics.impl.IMarkerImpl#getLineNumber <em>Line Number</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IMarkerImpl extends MinimalEObjectImpl.Container implements IMarker {
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
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final long ID_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected long id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getCreationTime() <em>Creation Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreationTime()
	 * @generated
	 * @ordered
	 */
	protected static final long CREATION_TIME_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getCreationTime() <em>Creation Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCreationTime()
	 * @generated
	 * @ordered
	 */
	protected long creationTime = CREATION_TIME_EDEFAULT;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected String type = TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAttributes() <em>Attributes</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAttributes()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> attributes;

	/**
	 * The default value of the '{@link #getMessage() <em>Message</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMessage()
	 * @generated
	 * @ordered
	 */
	protected static final String MESSAGE_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getLocation() <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocation()
	 * @generated
	 * @ordered
	 */
	protected static final String LOCATION_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getCharStart() <em>Char Start</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCharStart()
	 * @generated
	 * @ordered
	 */
	protected static final Integer CHAR_START_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getCharEnd() <em>Char End</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCharEnd()
	 * @generated
	 * @ordered
	 */
	protected static final Integer CHAR_END_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getLineNumber() <em>Line Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLineNumber()
	 * @generated
	 * @ordered
	 */
	protected static final Integer LINE_NUMBER_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IMarkerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SemioticsPackage.Literals.IMARKER;
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
			eNotify(new ENotificationImpl(this, Notification.SET, SemioticsPackage.IMARKER__KEY, oldKey, key));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Signified> getSignifieds() {
		if (signifieds == null) {
			signifieds = new EObjectWithInverseResolvingEList.ManyInverse<Signified>(Signified.class, this, SemioticsPackage.IMARKER__SIGNIFIEDS, SemioticsPackage.SIGNIFIED__SIGNIFIERS);
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
			eNotify(new ENotificationImpl(this, Notification.SET, SemioticsPackage.IMARKER__UUID, oldUuid, uuid));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Signifier> getSignifiers() {
		if (signifiers == null) {
			signifiers = new EObjectWithInverseResolvingEList.ManyInverse<Signifier>(Signifier.class, this, SemioticsPackage.IMARKER__SIGNIFIERS, SemioticsPackage.SIGNIFIER__SIGNIFIEDS);
		}
		return signifiers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Root getRoot() {
		if (eContainerFeatureID() != SemioticsPackage.IMARKER__ROOT) return null;
		return (Root)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Root basicGetRoot() {
		if (eContainerFeatureID() != SemioticsPackage.IMARKER__ROOT) return null;
		return (Root)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRoot(Root newRoot, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newRoot, SemioticsPackage.IMARKER__ROOT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRoot(Root newRoot) {
		if (newRoot != eInternalContainer() || (eContainerFeatureID() != SemioticsPackage.IMARKER__ROOT && newRoot != null)) {
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
			eNotify(new ENotificationImpl(this, Notification.SET, SemioticsPackage.IMARKER__ROOT, newRoot, newRoot));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(long newId) {
		long oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SemioticsPackage.IMARKER__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getCreationTime() {
		return creationTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCreationTime(long newCreationTime) {
		long oldCreationTime = creationTime;
		creationTime = newCreationTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SemioticsPackage.IMARKER__CREATION_TIME, oldCreationTime, creationTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(String newType) {
		String oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SemioticsPackage.IMARKER__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getAttributes() {
		if (attributes == null) {
			attributes = new EcoreEMap<String,String>(SemioticsPackage.Literals.ATTRIBUTE, AttributeImpl.class, this, SemioticsPackage.IMARKER__ATTRIBUTES);
		}
		return attributes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IResource getResource() {
		if (eContainerFeatureID() != SemioticsPackage.IMARKER__RESOURCE) return null;
		return (IResource)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IResource basicGetResource() {
		if (eContainerFeatureID() != SemioticsPackage.IMARKER__RESOURCE) return null;
		return (IResource)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetResource(IResource newResource, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newResource, SemioticsPackage.IMARKER__RESOURCE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setResource(IResource newResource) {
		if (newResource != eInternalContainer() || (eContainerFeatureID() != SemioticsPackage.IMARKER__RESOURCE && newResource != null)) {
			if (EcoreUtil.isAncestor(this, newResource))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newResource != null)
				msgs = ((InternalEObject)newResource).eInverseAdd(this, SemioticsPackage.IRESOURCE__MARKERS, IResource.class, msgs);
			msgs = basicSetResource(newResource, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SemioticsPackage.IMARKER__RESOURCE, newResource, newResource));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMessage() {
		return this.getString(org.eclipse.core.resources.IMarker.MESSAGE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLocation() {
		return this.getString(org.eclipse.core.resources.IMarker.LOCATION);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getCharStart() {
		return this.getInt(org.eclipse.core.resources.IMarker.CHAR_START);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getCharEnd() {
		return this.getInt(org.eclipse.core.resources.IMarker.CHAR_END);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getLineNumber() {
		return this.getInt(org.eclipse.core.resources.IMarker.LINE_NUMBER);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Integer getInt(final String key) {
		boolean _containsKey = this.getAttributes().containsKey(key);
		if (_containsKey) {
			return Integer.valueOf(Integer.parseInt(this.getAttributes().get(key)));
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getString(final String key) {
		boolean _containsKey = this.getAttributes().containsKey(key);
		if (_containsKey) {
			return this.getAttributes().get(key);
		}
		return null;
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
			case SemioticsPackage.IMARKER__SIGNIFIEDS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSignifieds()).basicAdd(otherEnd, msgs);
			case SemioticsPackage.IMARKER__SIGNIFIERS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSignifiers()).basicAdd(otherEnd, msgs);
			case SemioticsPackage.IMARKER__ROOT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetRoot((Root)otherEnd, msgs);
			case SemioticsPackage.IMARKER__RESOURCE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetResource((IResource)otherEnd, msgs);
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
			case SemioticsPackage.IMARKER__SIGNIFIEDS:
				return ((InternalEList<?>)getSignifieds()).basicRemove(otherEnd, msgs);
			case SemioticsPackage.IMARKER__SIGNIFIERS:
				return ((InternalEList<?>)getSignifiers()).basicRemove(otherEnd, msgs);
			case SemioticsPackage.IMARKER__ROOT:
				return basicSetRoot(null, msgs);
			case SemioticsPackage.IMARKER__ATTRIBUTES:
				return ((InternalEList<?>)getAttributes()).basicRemove(otherEnd, msgs);
			case SemioticsPackage.IMARKER__RESOURCE:
				return basicSetResource(null, msgs);
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
			case SemioticsPackage.IMARKER__ROOT:
				return eInternalContainer().eInverseRemove(this, SemioticsPackage.ROOT__SEMIOTICS, Root.class, msgs);
			case SemioticsPackage.IMARKER__RESOURCE:
				return eInternalContainer().eInverseRemove(this, SemioticsPackage.IRESOURCE__MARKERS, IResource.class, msgs);
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
			case SemioticsPackage.IMARKER__KEY:
				return getKey();
			case SemioticsPackage.IMARKER__SIGNIFIEDS:
				return getSignifieds();
			case SemioticsPackage.IMARKER__UUID:
				return getUuid();
			case SemioticsPackage.IMARKER__SIGNIFIERS:
				return getSignifiers();
			case SemioticsPackage.IMARKER__ROOT:
				if (resolve) return getRoot();
				return basicGetRoot();
			case SemioticsPackage.IMARKER__ID:
				return getId();
			case SemioticsPackage.IMARKER__CREATION_TIME:
				return getCreationTime();
			case SemioticsPackage.IMARKER__TYPE:
				return getType();
			case SemioticsPackage.IMARKER__ATTRIBUTES:
				if (coreType) return getAttributes();
				else return getAttributes().map();
			case SemioticsPackage.IMARKER__RESOURCE:
				if (resolve) return getResource();
				return basicGetResource();
			case SemioticsPackage.IMARKER__MESSAGE:
				return getMessage();
			case SemioticsPackage.IMARKER__LOCATION:
				return getLocation();
			case SemioticsPackage.IMARKER__CHAR_START:
				return getCharStart();
			case SemioticsPackage.IMARKER__CHAR_END:
				return getCharEnd();
			case SemioticsPackage.IMARKER__LINE_NUMBER:
				return getLineNumber();
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
			case SemioticsPackage.IMARKER__KEY:
				setKey((String)newValue);
				return;
			case SemioticsPackage.IMARKER__SIGNIFIEDS:
				getSignifieds().clear();
				getSignifieds().addAll((Collection<? extends Signified>)newValue);
				return;
			case SemioticsPackage.IMARKER__UUID:
				setUuid((String)newValue);
				return;
			case SemioticsPackage.IMARKER__SIGNIFIERS:
				getSignifiers().clear();
				getSignifiers().addAll((Collection<? extends Signifier>)newValue);
				return;
			case SemioticsPackage.IMARKER__ROOT:
				setRoot((Root)newValue);
				return;
			case SemioticsPackage.IMARKER__ID:
				setId((Long)newValue);
				return;
			case SemioticsPackage.IMARKER__CREATION_TIME:
				setCreationTime((Long)newValue);
				return;
			case SemioticsPackage.IMARKER__TYPE:
				setType((String)newValue);
				return;
			case SemioticsPackage.IMARKER__ATTRIBUTES:
				((EStructuralFeature.Setting)getAttributes()).set(newValue);
				return;
			case SemioticsPackage.IMARKER__RESOURCE:
				setResource((IResource)newValue);
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
			case SemioticsPackage.IMARKER__KEY:
				setKey(KEY_EDEFAULT);
				return;
			case SemioticsPackage.IMARKER__SIGNIFIEDS:
				getSignifieds().clear();
				return;
			case SemioticsPackage.IMARKER__UUID:
				setUuid(UUID_EDEFAULT);
				return;
			case SemioticsPackage.IMARKER__SIGNIFIERS:
				getSignifiers().clear();
				return;
			case SemioticsPackage.IMARKER__ROOT:
				setRoot((Root)null);
				return;
			case SemioticsPackage.IMARKER__ID:
				setId(ID_EDEFAULT);
				return;
			case SemioticsPackage.IMARKER__CREATION_TIME:
				setCreationTime(CREATION_TIME_EDEFAULT);
				return;
			case SemioticsPackage.IMARKER__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case SemioticsPackage.IMARKER__ATTRIBUTES:
				getAttributes().clear();
				return;
			case SemioticsPackage.IMARKER__RESOURCE:
				setResource((IResource)null);
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
			case SemioticsPackage.IMARKER__KEY:
				return KEY_EDEFAULT == null ? key != null : !KEY_EDEFAULT.equals(key);
			case SemioticsPackage.IMARKER__SIGNIFIEDS:
				return signifieds != null && !signifieds.isEmpty();
			case SemioticsPackage.IMARKER__UUID:
				return UUID_EDEFAULT == null ? uuid != null : !UUID_EDEFAULT.equals(uuid);
			case SemioticsPackage.IMARKER__SIGNIFIERS:
				return signifiers != null && !signifiers.isEmpty();
			case SemioticsPackage.IMARKER__ROOT:
				return basicGetRoot() != null;
			case SemioticsPackage.IMARKER__ID:
				return id != ID_EDEFAULT;
			case SemioticsPackage.IMARKER__CREATION_TIME:
				return creationTime != CREATION_TIME_EDEFAULT;
			case SemioticsPackage.IMARKER__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
			case SemioticsPackage.IMARKER__ATTRIBUTES:
				return attributes != null && !attributes.isEmpty();
			case SemioticsPackage.IMARKER__RESOURCE:
				return basicGetResource() != null;
			case SemioticsPackage.IMARKER__MESSAGE:
				return MESSAGE_EDEFAULT == null ? getMessage() != null : !MESSAGE_EDEFAULT.equals(getMessage());
			case SemioticsPackage.IMARKER__LOCATION:
				return LOCATION_EDEFAULT == null ? getLocation() != null : !LOCATION_EDEFAULT.equals(getLocation());
			case SemioticsPackage.IMARKER__CHAR_START:
				return CHAR_START_EDEFAULT == null ? getCharStart() != null : !CHAR_START_EDEFAULT.equals(getCharStart());
			case SemioticsPackage.IMARKER__CHAR_END:
				return CHAR_END_EDEFAULT == null ? getCharEnd() != null : !CHAR_END_EDEFAULT.equals(getCharEnd());
			case SemioticsPackage.IMARKER__LINE_NUMBER:
				return LINE_NUMBER_EDEFAULT == null ? getLineNumber() != null : !LINE_NUMBER_EDEFAULT.equals(getLineNumber());
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
				case SemioticsPackage.IMARKER__UUID: return SemioticsPackage.SIGNIFIED__UUID;
				case SemioticsPackage.IMARKER__SIGNIFIERS: return SemioticsPackage.SIGNIFIED__SIGNIFIERS;
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
				case SemioticsPackage.SIGNIFIED__UUID: return SemioticsPackage.IMARKER__UUID;
				case SemioticsPackage.SIGNIFIED__SIGNIFIERS: return SemioticsPackage.IMARKER__SIGNIFIERS;
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
			case SemioticsPackage.IMARKER___GET_INT__STRING:
				return getInt((String)arguments.get(0));
			case SemioticsPackage.IMARKER___GET_STRING__STRING:
				return getString((String)arguments.get(0));
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
		result.append(", id: ");
		result.append(id);
		result.append(", creationTime: ");
		result.append(creationTime);
		result.append(", type: ");
		result.append(type);
		result.append(')');
		return result.toString();
	}

} //IMarkerImpl

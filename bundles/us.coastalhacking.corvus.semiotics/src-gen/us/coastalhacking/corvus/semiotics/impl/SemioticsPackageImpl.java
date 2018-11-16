/**
 */
package us.coastalhacking.corvus.semiotics.impl;

import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import us.coastalhacking.corvus.semiotics.EntryPoint;
import us.coastalhacking.corvus.semiotics.IMarker;
import us.coastalhacking.corvus.semiotics.IResource;
import us.coastalhacking.corvus.semiotics.IWorkspaceRoot;
import us.coastalhacking.corvus.semiotics.MarkerEntryPoint;
import us.coastalhacking.corvus.semiotics.MarkerSink;
import us.coastalhacking.corvus.semiotics.Root;
import us.coastalhacking.corvus.semiotics.Semiotics;
import us.coastalhacking.corvus.semiotics.SemioticsFactory;
import us.coastalhacking.corvus.semiotics.SemioticsPackage;
import us.coastalhacking.corvus.semiotics.Signified;
import us.coastalhacking.corvus.semiotics.Signifier;
import us.coastalhacking.corvus.semiotics.Sink;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SemioticsPackageImpl extends EPackageImpl implements SemioticsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass semioticsEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass signifierEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass signifiedEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rootEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass attributeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iMarkerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iResourceEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iWorkspaceRootEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass entryPointEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass markerEntryPointEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sinkEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass markerSinkEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see us.coastalhacking.corvus.semiotics.SemioticsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private SemioticsPackageImpl() {
		super(eNS_URI, SemioticsFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link SemioticsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static SemioticsPackage init() {
		if (isInited) return (SemioticsPackage)EPackage.Registry.INSTANCE.getEPackage(SemioticsPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredSemioticsPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		SemioticsPackageImpl theSemioticsPackage = registeredSemioticsPackage instanceof SemioticsPackageImpl ? (SemioticsPackageImpl)registeredSemioticsPackage : new SemioticsPackageImpl();

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theSemioticsPackage.createPackageContents();

		// Initialize created meta-data
		theSemioticsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theSemioticsPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(SemioticsPackage.eNS_URI, theSemioticsPackage);
		return theSemioticsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSemiotics() {
		return semioticsEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSemiotics_Root() {
		return (EReference)semioticsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSignifier() {
		return signifierEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSignifier_Key() {
		return (EAttribute)signifierEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSignifier_Signifieds() {
		return (EReference)signifierEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSignified() {
		return signifiedEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSignified_Uuid() {
		return (EAttribute)signifiedEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSignified_Signifiers() {
		return (EReference)signifiedEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRoot() {
		return rootEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getRoot_Semiotics() {
		return (EReference)rootEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAttribute() {
		return attributeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAttribute_Key() {
		return (EAttribute)attributeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAttribute_Value() {
		return (EAttribute)attributeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIMarker() {
		return iMarkerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIMarker_Id() {
		return (EAttribute)iMarkerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIMarker_CreationTime() {
		return (EAttribute)iMarkerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIMarker_Type() {
		return (EAttribute)iMarkerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIMarker_Attributes() {
		return (EReference)iMarkerEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIMarker_Resource() {
		return (EReference)iMarkerEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIMarker_Message() {
		return (EAttribute)iMarkerEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIMarker_Location() {
		return (EAttribute)iMarkerEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIMarker_CharStart() {
		return (EAttribute)iMarkerEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIMarker_CharEnd() {
		return (EAttribute)iMarkerEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIMarker_LineNumber() {
		return (EAttribute)iMarkerEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getIMarker__GetInt__String() {
		return iMarkerEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getIMarker__GetString__String() {
		return iMarkerEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIResource() {
		return iResourceEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIResource_FullPath() {
		return (EAttribute)iResourceEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIResource_Markers() {
		return (EReference)iResourceEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIResource_WorkspaceRoot() {
		return (EReference)iResourceEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EOperation getIResource__GetKey() {
		return iResourceEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIWorkspaceRoot() {
		return iWorkspaceRootEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getIWorkspaceRoot_Members() {
		return (EReference)iWorkspaceRootEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getEntryPoint() {
		return entryPointEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMarkerEntryPoint() {
		return markerEntryPointEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSink() {
		return sinkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getMarkerSink() {
		return markerSinkEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SemioticsFactory getSemioticsFactory() {
		return (SemioticsFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		semioticsEClass = createEClass(SEMIOTICS);
		createEReference(semioticsEClass, SEMIOTICS__ROOT);

		signifierEClass = createEClass(SIGNIFIER);
		createEAttribute(signifierEClass, SIGNIFIER__KEY);
		createEReference(signifierEClass, SIGNIFIER__SIGNIFIEDS);

		signifiedEClass = createEClass(SIGNIFIED);
		createEAttribute(signifiedEClass, SIGNIFIED__UUID);
		createEReference(signifiedEClass, SIGNIFIED__SIGNIFIERS);

		rootEClass = createEClass(ROOT);
		createEReference(rootEClass, ROOT__SEMIOTICS);

		attributeEClass = createEClass(ATTRIBUTE);
		createEAttribute(attributeEClass, ATTRIBUTE__KEY);
		createEAttribute(attributeEClass, ATTRIBUTE__VALUE);

		iMarkerEClass = createEClass(IMARKER);
		createEAttribute(iMarkerEClass, IMARKER__ID);
		createEAttribute(iMarkerEClass, IMARKER__CREATION_TIME);
		createEAttribute(iMarkerEClass, IMARKER__TYPE);
		createEReference(iMarkerEClass, IMARKER__ATTRIBUTES);
		createEReference(iMarkerEClass, IMARKER__RESOURCE);
		createEAttribute(iMarkerEClass, IMARKER__MESSAGE);
		createEAttribute(iMarkerEClass, IMARKER__LOCATION);
		createEAttribute(iMarkerEClass, IMARKER__CHAR_START);
		createEAttribute(iMarkerEClass, IMARKER__CHAR_END);
		createEAttribute(iMarkerEClass, IMARKER__LINE_NUMBER);
		createEOperation(iMarkerEClass, IMARKER___GET_INT__STRING);
		createEOperation(iMarkerEClass, IMARKER___GET_STRING__STRING);

		iResourceEClass = createEClass(IRESOURCE);
		createEAttribute(iResourceEClass, IRESOURCE__FULL_PATH);
		createEReference(iResourceEClass, IRESOURCE__MARKERS);
		createEReference(iResourceEClass, IRESOURCE__WORKSPACE_ROOT);
		createEOperation(iResourceEClass, IRESOURCE___GET_KEY);

		iWorkspaceRootEClass = createEClass(IWORKSPACE_ROOT);
		createEReference(iWorkspaceRootEClass, IWORKSPACE_ROOT__MEMBERS);

		entryPointEClass = createEClass(ENTRY_POINT);

		markerEntryPointEClass = createEClass(MARKER_ENTRY_POINT);

		sinkEClass = createEClass(SINK);

		markerSinkEClass = createEClass(MARKER_SINK);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		EcorePackage theEcorePackage = (EcorePackage)EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		semioticsEClass.getESuperTypes().add(this.getSignifier());
		semioticsEClass.getESuperTypes().add(this.getSignified());
		iMarkerEClass.getESuperTypes().add(this.getSemiotics());
		iResourceEClass.getESuperTypes().add(this.getSemiotics());
		iWorkspaceRootEClass.getESuperTypes().add(this.getSemiotics());
		markerEntryPointEClass.getESuperTypes().add(this.getEntryPoint());
		markerEntryPointEClass.getESuperTypes().add(this.getSemiotics());
		markerSinkEClass.getESuperTypes().add(this.getSink());
		markerSinkEClass.getESuperTypes().add(this.getSemiotics());

		// Initialize classes, features, and operations; add parameters
		initEClass(semioticsEClass, Semiotics.class, "Semiotics", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSemiotics_Root(), this.getRoot(), this.getRoot_Semiotics(), "root", null, 0, 1, Semiotics.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(signifierEClass, Signifier.class, "Signifier", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSignifier_Key(), theEcorePackage.getEString(), "key", null, 0, 1, Signifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSignifier_Signifieds(), this.getSignified(), this.getSignified_Signifiers(), "signifieds", null, 0, -1, Signifier.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(signifiedEClass, Signified.class, "Signified", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSignified_Uuid(), theEcorePackage.getEString(), "uuid", null, 0, 1, Signified.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSignified_Signifiers(), this.getSignifier(), this.getSignifier_Signifieds(), "signifiers", null, 0, -1, Signified.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(rootEClass, Root.class, "Root", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getRoot_Semiotics(), this.getSemiotics(), this.getSemiotics_Root(), "semiotics", null, 0, -1, Root.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		getRoot_Semiotics().getEKeys().add(this.getSignifier_Key());

		initEClass(attributeEClass, Map.Entry.class, "Attribute", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAttribute_Key(), theEcorePackage.getEString(), "key", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttribute_Value(), theEcorePackage.getEString(), "value", null, 0, 1, Map.Entry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iMarkerEClass, IMarker.class, "IMarker", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIMarker_Id(), theEcorePackage.getELong(), "id", null, 0, 1, IMarker.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIMarker_CreationTime(), theEcorePackage.getELong(), "creationTime", null, 0, 1, IMarker.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIMarker_Type(), theEcorePackage.getEString(), "type", null, 0, 1, IMarker.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIMarker_Attributes(), this.getAttribute(), null, "attributes", null, 0, -1, IMarker.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIMarker_Resource(), this.getIResource(), this.getIResource_Markers(), "resource", null, 0, 1, IMarker.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIMarker_Message(), theEcorePackage.getEString(), "message", null, 0, 1, IMarker.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getIMarker_Location(), theEcorePackage.getEString(), "location", null, 0, 1, IMarker.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getIMarker_CharStart(), theEcorePackage.getEIntegerObject(), "charStart", null, 0, 1, IMarker.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getIMarker_CharEnd(), theEcorePackage.getEIntegerObject(), "charEnd", null, 0, 1, IMarker.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getIMarker_LineNumber(), theEcorePackage.getEIntegerObject(), "lineNumber", null, 0, 1, IMarker.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		EOperation op = initEOperation(getIMarker__GetInt__String(), theEcorePackage.getEIntegerObject(), "getInt", 0, 1, !IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEString(), "key", 0, 1, !IS_UNIQUE, IS_ORDERED);

		op = initEOperation(getIMarker__GetString__String(), theEcorePackage.getEString(), "getString", 0, 1, !IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theEcorePackage.getEString(), "key", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEClass(iResourceEClass, IResource.class, "IResource", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIResource_FullPath(), theEcorePackage.getEString(), "fullPath", null, 0, 1, IResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getIResource_Markers(), this.getIMarker(), this.getIMarker_Resource(), "markers", null, 0, -1, IResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		getIResource_Markers().getEKeys().add(this.getIMarker_Id());
		initEReference(getIResource_WorkspaceRoot(), this.getIWorkspaceRoot(), this.getIWorkspaceRoot_Members(), "workspaceRoot", null, 0, 1, IResource.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getIResource__GetKey(), theEcorePackage.getEString(), "getKey", 0, 1, !IS_UNIQUE, IS_ORDERED);

		initEClass(iWorkspaceRootEClass, IWorkspaceRoot.class, "IWorkspaceRoot", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getIWorkspaceRoot_Members(), this.getIResource(), this.getIResource_WorkspaceRoot(), "members", null, 0, -1, IWorkspaceRoot.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		getIWorkspaceRoot_Members().getEKeys().add(this.getIResource_FullPath());

		initEClass(entryPointEClass, EntryPoint.class, "EntryPoint", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(markerEntryPointEClass, MarkerEntryPoint.class, "MarkerEntryPoint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(sinkEClass, Sink.class, "Sink", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(markerSinkEClass, MarkerSink.class, "MarkerSink", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //SemioticsPackageImpl

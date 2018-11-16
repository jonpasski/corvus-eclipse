/**
 */
package us.coastalhacking.corvus.semiotics;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
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
 * @see us.coastalhacking.corvus.semiotics.SemioticsFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/emf/2002/GenModel childCreationExtenders='true' editDirectory='/us.coastalhacking.corvus.semiotics/src-gen' providerPackageSuffix='edit' modelPluginVariables='org.eclipse.xtext.xbase.lib org.eclipse.emf.ecore.xcore.lib org.eclipse.core.resources' presentationPackageSuffix='editor' basePackage='us.coastalhacking.corvus'"
 * @generated
 */
public interface SemioticsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "semiotics";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "https://model.coastalhacking.us/corvus/semiotics/0.1";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "semiotics";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SemioticsPackage eINSTANCE = us.coastalhacking.corvus.semiotics.impl.SemioticsPackageImpl.init();

	/**
	 * The meta object id for the '{@link us.coastalhacking.corvus.semiotics.Signifier <em>Signifier</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see us.coastalhacking.corvus.semiotics.Signifier
	 * @see us.coastalhacking.corvus.semiotics.impl.SemioticsPackageImpl#getSignifier()
	 * @generated
	 */
	int SIGNIFIER = 1;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNIFIER__KEY = 0;

	/**
	 * The feature id for the '<em><b>Signifieds</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNIFIER__SIGNIFIEDS = 1;

	/**
	 * The number of structural features of the '<em>Signifier</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNIFIER_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Signifier</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNIFIER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link us.coastalhacking.corvus.semiotics.Semiotics <em>Semiotics</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see us.coastalhacking.corvus.semiotics.Semiotics
	 * @see us.coastalhacking.corvus.semiotics.impl.SemioticsPackageImpl#getSemiotics()
	 * @generated
	 */
	int SEMIOTICS = 0;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMIOTICS__KEY = SIGNIFIER__KEY;

	/**
	 * The feature id for the '<em><b>Signifieds</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMIOTICS__SIGNIFIEDS = SIGNIFIER__SIGNIFIEDS;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMIOTICS__UUID = SIGNIFIER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Signifiers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMIOTICS__SIGNIFIERS = SIGNIFIER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Root</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMIOTICS__ROOT = SIGNIFIER_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Semiotics</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMIOTICS_FEATURE_COUNT = SIGNIFIER_FEATURE_COUNT + 3;

	/**
	 * The number of operations of the '<em>Semiotics</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEMIOTICS_OPERATION_COUNT = SIGNIFIER_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link us.coastalhacking.corvus.semiotics.Signified <em>Signified</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see us.coastalhacking.corvus.semiotics.Signified
	 * @see us.coastalhacking.corvus.semiotics.impl.SemioticsPackageImpl#getSignified()
	 * @generated
	 */
	int SIGNIFIED = 2;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNIFIED__UUID = 0;

	/**
	 * The feature id for the '<em><b>Signifiers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNIFIED__SIGNIFIERS = 1;

	/**
	 * The number of structural features of the '<em>Signified</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNIFIED_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Signified</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SIGNIFIED_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link us.coastalhacking.corvus.semiotics.impl.RootImpl <em>Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see us.coastalhacking.corvus.semiotics.impl.RootImpl
	 * @see us.coastalhacking.corvus.semiotics.impl.SemioticsPackageImpl#getRoot()
	 * @generated
	 */
	int ROOT = 3;

	/**
	 * The feature id for the '<em><b>Semiotics</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT__SEMIOTICS = 0;

	/**
	 * The number of structural features of the '<em>Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROOT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link us.coastalhacking.corvus.semiotics.impl.AttributeImpl <em>Attribute</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see us.coastalhacking.corvus.semiotics.impl.AttributeImpl
	 * @see us.coastalhacking.corvus.semiotics.impl.SemioticsPackageImpl#getAttribute()
	 * @generated
	 */
	int ATTRIBUTE = 4;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__KEY = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE__VALUE = 1;

	/**
	 * The number of structural features of the '<em>Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ATTRIBUTE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link us.coastalhacking.corvus.semiotics.impl.IMarkerImpl <em>IMarker</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see us.coastalhacking.corvus.semiotics.impl.IMarkerImpl
	 * @see us.coastalhacking.corvus.semiotics.impl.SemioticsPackageImpl#getIMarker()
	 * @generated
	 */
	int IMARKER = 5;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMARKER__KEY = SEMIOTICS__KEY;

	/**
	 * The feature id for the '<em><b>Signifieds</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMARKER__SIGNIFIEDS = SEMIOTICS__SIGNIFIEDS;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMARKER__UUID = SEMIOTICS__UUID;

	/**
	 * The feature id for the '<em><b>Signifiers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMARKER__SIGNIFIERS = SEMIOTICS__SIGNIFIERS;

	/**
	 * The feature id for the '<em><b>Root</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMARKER__ROOT = SEMIOTICS__ROOT;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMARKER__ID = SEMIOTICS_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Creation Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMARKER__CREATION_TIME = SEMIOTICS_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMARKER__TYPE = SEMIOTICS_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMARKER__ATTRIBUTES = SEMIOTICS_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Resource</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMARKER__RESOURCE = SEMIOTICS_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Message</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMARKER__MESSAGE = SEMIOTICS_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMARKER__LOCATION = SEMIOTICS_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Char Start</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMARKER__CHAR_START = SEMIOTICS_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Char End</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMARKER__CHAR_END = SEMIOTICS_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Line Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMARKER__LINE_NUMBER = SEMIOTICS_FEATURE_COUNT + 9;

	/**
	 * The number of structural features of the '<em>IMarker</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMARKER_FEATURE_COUNT = SEMIOTICS_FEATURE_COUNT + 10;

	/**
	 * The operation id for the '<em>Get Int</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMARKER___GET_INT__STRING = SEMIOTICS_OPERATION_COUNT + 0;

	/**
	 * The operation id for the '<em>Get String</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMARKER___GET_STRING__STRING = SEMIOTICS_OPERATION_COUNT + 1;

	/**
	 * The number of operations of the '<em>IMarker</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IMARKER_OPERATION_COUNT = SEMIOTICS_OPERATION_COUNT + 2;

	/**
	 * The meta object id for the '{@link us.coastalhacking.corvus.semiotics.impl.IResourceImpl <em>IResource</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see us.coastalhacking.corvus.semiotics.impl.IResourceImpl
	 * @see us.coastalhacking.corvus.semiotics.impl.SemioticsPackageImpl#getIResource()
	 * @generated
	 */
	int IRESOURCE = 6;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IRESOURCE__KEY = SEMIOTICS__KEY;

	/**
	 * The feature id for the '<em><b>Signifieds</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IRESOURCE__SIGNIFIEDS = SEMIOTICS__SIGNIFIEDS;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IRESOURCE__UUID = SEMIOTICS__UUID;

	/**
	 * The feature id for the '<em><b>Signifiers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IRESOURCE__SIGNIFIERS = SEMIOTICS__SIGNIFIERS;

	/**
	 * The feature id for the '<em><b>Root</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IRESOURCE__ROOT = SEMIOTICS__ROOT;

	/**
	 * The feature id for the '<em><b>Full Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IRESOURCE__FULL_PATH = SEMIOTICS_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Markers</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IRESOURCE__MARKERS = SEMIOTICS_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Workspace Root</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IRESOURCE__WORKSPACE_ROOT = SEMIOTICS_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>IResource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IRESOURCE_FEATURE_COUNT = SEMIOTICS_FEATURE_COUNT + 3;

	/**
	 * The operation id for the '<em>Get Key</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IRESOURCE___GET_KEY = SEMIOTICS_OPERATION_COUNT + 0;

	/**
	 * The number of operations of the '<em>IResource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IRESOURCE_OPERATION_COUNT = SEMIOTICS_OPERATION_COUNT + 1;

	/**
	 * The meta object id for the '{@link us.coastalhacking.corvus.semiotics.impl.IWorkspaceRootImpl <em>IWorkspace Root</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see us.coastalhacking.corvus.semiotics.impl.IWorkspaceRootImpl
	 * @see us.coastalhacking.corvus.semiotics.impl.SemioticsPackageImpl#getIWorkspaceRoot()
	 * @generated
	 */
	int IWORKSPACE_ROOT = 7;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IWORKSPACE_ROOT__KEY = SEMIOTICS__KEY;

	/**
	 * The feature id for the '<em><b>Signifieds</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IWORKSPACE_ROOT__SIGNIFIEDS = SEMIOTICS__SIGNIFIEDS;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IWORKSPACE_ROOT__UUID = SEMIOTICS__UUID;

	/**
	 * The feature id for the '<em><b>Signifiers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IWORKSPACE_ROOT__SIGNIFIERS = SEMIOTICS__SIGNIFIERS;

	/**
	 * The feature id for the '<em><b>Root</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IWORKSPACE_ROOT__ROOT = SEMIOTICS__ROOT;

	/**
	 * The feature id for the '<em><b>Members</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IWORKSPACE_ROOT__MEMBERS = SEMIOTICS_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>IWorkspace Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IWORKSPACE_ROOT_FEATURE_COUNT = SEMIOTICS_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>IWorkspace Root</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IWORKSPACE_ROOT_OPERATION_COUNT = SEMIOTICS_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link us.coastalhacking.corvus.semiotics.EntryPoint <em>Entry Point</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see us.coastalhacking.corvus.semiotics.EntryPoint
	 * @see us.coastalhacking.corvus.semiotics.impl.SemioticsPackageImpl#getEntryPoint()
	 * @generated
	 */
	int ENTRY_POINT = 8;

	/**
	 * The number of structural features of the '<em>Entry Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Entry Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENTRY_POINT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link us.coastalhacking.corvus.semiotics.impl.MarkerEntryPointImpl <em>Marker Entry Point</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see us.coastalhacking.corvus.semiotics.impl.MarkerEntryPointImpl
	 * @see us.coastalhacking.corvus.semiotics.impl.SemioticsPackageImpl#getMarkerEntryPoint()
	 * @generated
	 */
	int MARKER_ENTRY_POINT = 9;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKER_ENTRY_POINT__KEY = ENTRY_POINT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Signifieds</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKER_ENTRY_POINT__SIGNIFIEDS = ENTRY_POINT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKER_ENTRY_POINT__UUID = ENTRY_POINT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Signifiers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKER_ENTRY_POINT__SIGNIFIERS = ENTRY_POINT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Root</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKER_ENTRY_POINT__ROOT = ENTRY_POINT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Marker Entry Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKER_ENTRY_POINT_FEATURE_COUNT = ENTRY_POINT_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>Marker Entry Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKER_ENTRY_POINT_OPERATION_COUNT = ENTRY_POINT_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link us.coastalhacking.corvus.semiotics.Sink <em>Sink</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see us.coastalhacking.corvus.semiotics.Sink
	 * @see us.coastalhacking.corvus.semiotics.impl.SemioticsPackageImpl#getSink()
	 * @generated
	 */
	int SINK = 10;

	/**
	 * The number of structural features of the '<em>Sink</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINK_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Sink</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SINK_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link us.coastalhacking.corvus.semiotics.impl.MarkerSinkImpl <em>Marker Sink</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see us.coastalhacking.corvus.semiotics.impl.MarkerSinkImpl
	 * @see us.coastalhacking.corvus.semiotics.impl.SemioticsPackageImpl#getMarkerSink()
	 * @generated
	 */
	int MARKER_SINK = 11;

	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKER_SINK__KEY = SINK_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Signifieds</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKER_SINK__SIGNIFIEDS = SINK_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Uuid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKER_SINK__UUID = SINK_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Signifiers</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKER_SINK__SIGNIFIERS = SINK_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Root</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKER_SINK__ROOT = SINK_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Marker Sink</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKER_SINK_FEATURE_COUNT = SINK_FEATURE_COUNT + 5;

	/**
	 * The number of operations of the '<em>Marker Sink</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MARKER_SINK_OPERATION_COUNT = SINK_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link us.coastalhacking.corvus.semiotics.Semiotics <em>Semiotics</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Semiotics</em>'.
	 * @see us.coastalhacking.corvus.semiotics.Semiotics
	 * @generated
	 */
	EClass getSemiotics();

	/**
	 * Returns the meta object for the container reference '{@link us.coastalhacking.corvus.semiotics.Semiotics#getRoot <em>Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Root</em>'.
	 * @see us.coastalhacking.corvus.semiotics.Semiotics#getRoot()
	 * @see #getSemiotics()
	 * @generated
	 */
	EReference getSemiotics_Root();

	/**
	 * Returns the meta object for class '{@link us.coastalhacking.corvus.semiotics.Signifier <em>Signifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Signifier</em>'.
	 * @see us.coastalhacking.corvus.semiotics.Signifier
	 * @generated
	 */
	EClass getSignifier();

	/**
	 * Returns the meta object for the attribute '{@link us.coastalhacking.corvus.semiotics.Signifier#getKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see us.coastalhacking.corvus.semiotics.Signifier#getKey()
	 * @see #getSignifier()
	 * @generated
	 */
	EAttribute getSignifier_Key();

	/**
	 * Returns the meta object for the reference list '{@link us.coastalhacking.corvus.semiotics.Signifier#getSignifieds <em>Signifieds</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Signifieds</em>'.
	 * @see us.coastalhacking.corvus.semiotics.Signifier#getSignifieds()
	 * @see #getSignifier()
	 * @generated
	 */
	EReference getSignifier_Signifieds();

	/**
	 * Returns the meta object for class '{@link us.coastalhacking.corvus.semiotics.Signified <em>Signified</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Signified</em>'.
	 * @see us.coastalhacking.corvus.semiotics.Signified
	 * @generated
	 */
	EClass getSignified();

	/**
	 * Returns the meta object for the attribute '{@link us.coastalhacking.corvus.semiotics.Signified#getUuid <em>Uuid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uuid</em>'.
	 * @see us.coastalhacking.corvus.semiotics.Signified#getUuid()
	 * @see #getSignified()
	 * @generated
	 */
	EAttribute getSignified_Uuid();

	/**
	 * Returns the meta object for the reference list '{@link us.coastalhacking.corvus.semiotics.Signified#getSignifiers <em>Signifiers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Signifiers</em>'.
	 * @see us.coastalhacking.corvus.semiotics.Signified#getSignifiers()
	 * @see #getSignified()
	 * @generated
	 */
	EReference getSignified_Signifiers();

	/**
	 * Returns the meta object for class '{@link us.coastalhacking.corvus.semiotics.Root <em>Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Root</em>'.
	 * @see us.coastalhacking.corvus.semiotics.Root
	 * @generated
	 */
	EClass getRoot();

	/**
	 * Returns the meta object for the containment reference list '{@link us.coastalhacking.corvus.semiotics.Root#getSemiotics <em>Semiotics</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Semiotics</em>'.
	 * @see us.coastalhacking.corvus.semiotics.Root#getSemiotics()
	 * @see #getRoot()
	 * @generated
	 */
	EReference getRoot_Semiotics();

	/**
	 * Returns the meta object for class '{@link java.util.Map.Entry <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Attribute</em>'.
	 * @see java.util.Map.Entry
	 * @model keyUnique="false" keyDataType="org.eclipse.emf.ecore.EString"
	 *        valueUnique="false" valueDataType="org.eclipse.emf.ecore.EString"
	 * @generated
	 */
	EClass getAttribute();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see java.util.Map.Entry
	 * @see #getAttribute()
	 * @generated
	 */
	EAttribute getAttribute_Key();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see java.util.Map.Entry
	 * @see #getAttribute()
	 * @generated
	 */
	EAttribute getAttribute_Value();

	/**
	 * Returns the meta object for class '{@link us.coastalhacking.corvus.semiotics.IMarker <em>IMarker</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IMarker</em>'.
	 * @see us.coastalhacking.corvus.semiotics.IMarker
	 * @generated
	 */
	EClass getIMarker();

	/**
	 * Returns the meta object for the attribute '{@link us.coastalhacking.corvus.semiotics.IMarker#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see us.coastalhacking.corvus.semiotics.IMarker#getId()
	 * @see #getIMarker()
	 * @generated
	 */
	EAttribute getIMarker_Id();

	/**
	 * Returns the meta object for the attribute '{@link us.coastalhacking.corvus.semiotics.IMarker#getCreationTime <em>Creation Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Creation Time</em>'.
	 * @see us.coastalhacking.corvus.semiotics.IMarker#getCreationTime()
	 * @see #getIMarker()
	 * @generated
	 */
	EAttribute getIMarker_CreationTime();

	/**
	 * Returns the meta object for the attribute '{@link us.coastalhacking.corvus.semiotics.IMarker#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see us.coastalhacking.corvus.semiotics.IMarker#getType()
	 * @see #getIMarker()
	 * @generated
	 */
	EAttribute getIMarker_Type();

	/**
	 * Returns the meta object for the map '{@link us.coastalhacking.corvus.semiotics.IMarker#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the map '<em>Attributes</em>'.
	 * @see us.coastalhacking.corvus.semiotics.IMarker#getAttributes()
	 * @see #getIMarker()
	 * @generated
	 */
	EReference getIMarker_Attributes();

	/**
	 * Returns the meta object for the container reference '{@link us.coastalhacking.corvus.semiotics.IMarker#getResource <em>Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Resource</em>'.
	 * @see us.coastalhacking.corvus.semiotics.IMarker#getResource()
	 * @see #getIMarker()
	 * @generated
	 */
	EReference getIMarker_Resource();

	/**
	 * Returns the meta object for the attribute '{@link us.coastalhacking.corvus.semiotics.IMarker#getMessage <em>Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Message</em>'.
	 * @see us.coastalhacking.corvus.semiotics.IMarker#getMessage()
	 * @see #getIMarker()
	 * @generated
	 */
	EAttribute getIMarker_Message();

	/**
	 * Returns the meta object for the attribute '{@link us.coastalhacking.corvus.semiotics.IMarker#getLocation <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Location</em>'.
	 * @see us.coastalhacking.corvus.semiotics.IMarker#getLocation()
	 * @see #getIMarker()
	 * @generated
	 */
	EAttribute getIMarker_Location();

	/**
	 * Returns the meta object for the attribute '{@link us.coastalhacking.corvus.semiotics.IMarker#getCharStart <em>Char Start</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Char Start</em>'.
	 * @see us.coastalhacking.corvus.semiotics.IMarker#getCharStart()
	 * @see #getIMarker()
	 * @generated
	 */
	EAttribute getIMarker_CharStart();

	/**
	 * Returns the meta object for the attribute '{@link us.coastalhacking.corvus.semiotics.IMarker#getCharEnd <em>Char End</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Char End</em>'.
	 * @see us.coastalhacking.corvus.semiotics.IMarker#getCharEnd()
	 * @see #getIMarker()
	 * @generated
	 */
	EAttribute getIMarker_CharEnd();

	/**
	 * Returns the meta object for the attribute '{@link us.coastalhacking.corvus.semiotics.IMarker#getLineNumber <em>Line Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Line Number</em>'.
	 * @see us.coastalhacking.corvus.semiotics.IMarker#getLineNumber()
	 * @see #getIMarker()
	 * @generated
	 */
	EAttribute getIMarker_LineNumber();

	/**
	 * Returns the meta object for the '{@link us.coastalhacking.corvus.semiotics.IMarker#getInt(java.lang.String) <em>Get Int</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Int</em>' operation.
	 * @see us.coastalhacking.corvus.semiotics.IMarker#getInt(java.lang.String)
	 * @generated
	 */
	EOperation getIMarker__GetInt__String();

	/**
	 * Returns the meta object for the '{@link us.coastalhacking.corvus.semiotics.IMarker#getString(java.lang.String) <em>Get String</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get String</em>' operation.
	 * @see us.coastalhacking.corvus.semiotics.IMarker#getString(java.lang.String)
	 * @generated
	 */
	EOperation getIMarker__GetString__String();

	/**
	 * Returns the meta object for class '{@link us.coastalhacking.corvus.semiotics.IResource <em>IResource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IResource</em>'.
	 * @see us.coastalhacking.corvus.semiotics.IResource
	 * @generated
	 */
	EClass getIResource();

	/**
	 * Returns the meta object for the attribute '{@link us.coastalhacking.corvus.semiotics.IResource#getFullPath <em>Full Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Full Path</em>'.
	 * @see us.coastalhacking.corvus.semiotics.IResource#getFullPath()
	 * @see #getIResource()
	 * @generated
	 */
	EAttribute getIResource_FullPath();

	/**
	 * Returns the meta object for the containment reference list '{@link us.coastalhacking.corvus.semiotics.IResource#getMarkers <em>Markers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Markers</em>'.
	 * @see us.coastalhacking.corvus.semiotics.IResource#getMarkers()
	 * @see #getIResource()
	 * @generated
	 */
	EReference getIResource_Markers();

	/**
	 * Returns the meta object for the container reference '{@link us.coastalhacking.corvus.semiotics.IResource#getWorkspaceRoot <em>Workspace Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Workspace Root</em>'.
	 * @see us.coastalhacking.corvus.semiotics.IResource#getWorkspaceRoot()
	 * @see #getIResource()
	 * @generated
	 */
	EReference getIResource_WorkspaceRoot();

	/**
	 * Returns the meta object for the '{@link us.coastalhacking.corvus.semiotics.IResource#getKey() <em>Get Key</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Key</em>' operation.
	 * @see us.coastalhacking.corvus.semiotics.IResource#getKey()
	 * @generated
	 */
	EOperation getIResource__GetKey();

	/**
	 * Returns the meta object for class '{@link us.coastalhacking.corvus.semiotics.IWorkspaceRoot <em>IWorkspace Root</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>IWorkspace Root</em>'.
	 * @see us.coastalhacking.corvus.semiotics.IWorkspaceRoot
	 * @generated
	 */
	EClass getIWorkspaceRoot();

	/**
	 * Returns the meta object for the containment reference list '{@link us.coastalhacking.corvus.semiotics.IWorkspaceRoot#getMembers <em>Members</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Members</em>'.
	 * @see us.coastalhacking.corvus.semiotics.IWorkspaceRoot#getMembers()
	 * @see #getIWorkspaceRoot()
	 * @generated
	 */
	EReference getIWorkspaceRoot_Members();

	/**
	 * Returns the meta object for class '{@link us.coastalhacking.corvus.semiotics.EntryPoint <em>Entry Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Entry Point</em>'.
	 * @see us.coastalhacking.corvus.semiotics.EntryPoint
	 * @generated
	 */
	EClass getEntryPoint();

	/**
	 * Returns the meta object for class '{@link us.coastalhacking.corvus.semiotics.MarkerEntryPoint <em>Marker Entry Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Marker Entry Point</em>'.
	 * @see us.coastalhacking.corvus.semiotics.MarkerEntryPoint
	 * @generated
	 */
	EClass getMarkerEntryPoint();

	/**
	 * Returns the meta object for class '{@link us.coastalhacking.corvus.semiotics.Sink <em>Sink</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sink</em>'.
	 * @see us.coastalhacking.corvus.semiotics.Sink
	 * @generated
	 */
	EClass getSink();

	/**
	 * Returns the meta object for class '{@link us.coastalhacking.corvus.semiotics.MarkerSink <em>Marker Sink</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Marker Sink</em>'.
	 * @see us.coastalhacking.corvus.semiotics.MarkerSink
	 * @generated
	 */
	EClass getMarkerSink();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SemioticsFactory getSemioticsFactory();

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
		 * The meta object literal for the '{@link us.coastalhacking.corvus.semiotics.Semiotics <em>Semiotics</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see us.coastalhacking.corvus.semiotics.Semiotics
		 * @see us.coastalhacking.corvus.semiotics.impl.SemioticsPackageImpl#getSemiotics()
		 * @generated
		 */
		EClass SEMIOTICS = eINSTANCE.getSemiotics();

		/**
		 * The meta object literal for the '<em><b>Root</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SEMIOTICS__ROOT = eINSTANCE.getSemiotics_Root();

		/**
		 * The meta object literal for the '{@link us.coastalhacking.corvus.semiotics.Signifier <em>Signifier</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see us.coastalhacking.corvus.semiotics.Signifier
		 * @see us.coastalhacking.corvus.semiotics.impl.SemioticsPackageImpl#getSignifier()
		 * @generated
		 */
		EClass SIGNIFIER = eINSTANCE.getSignifier();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIGNIFIER__KEY = eINSTANCE.getSignifier_Key();

		/**
		 * The meta object literal for the '<em><b>Signifieds</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SIGNIFIER__SIGNIFIEDS = eINSTANCE.getSignifier_Signifieds();

		/**
		 * The meta object literal for the '{@link us.coastalhacking.corvus.semiotics.Signified <em>Signified</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see us.coastalhacking.corvus.semiotics.Signified
		 * @see us.coastalhacking.corvus.semiotics.impl.SemioticsPackageImpl#getSignified()
		 * @generated
		 */
		EClass SIGNIFIED = eINSTANCE.getSignified();

		/**
		 * The meta object literal for the '<em><b>Uuid</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SIGNIFIED__UUID = eINSTANCE.getSignified_Uuid();

		/**
		 * The meta object literal for the '<em><b>Signifiers</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SIGNIFIED__SIGNIFIERS = eINSTANCE.getSignified_Signifiers();

		/**
		 * The meta object literal for the '{@link us.coastalhacking.corvus.semiotics.impl.RootImpl <em>Root</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see us.coastalhacking.corvus.semiotics.impl.RootImpl
		 * @see us.coastalhacking.corvus.semiotics.impl.SemioticsPackageImpl#getRoot()
		 * @generated
		 */
		EClass ROOT = eINSTANCE.getRoot();

		/**
		 * The meta object literal for the '<em><b>Semiotics</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROOT__SEMIOTICS = eINSTANCE.getRoot_Semiotics();

		/**
		 * The meta object literal for the '{@link us.coastalhacking.corvus.semiotics.impl.AttributeImpl <em>Attribute</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see us.coastalhacking.corvus.semiotics.impl.AttributeImpl
		 * @see us.coastalhacking.corvus.semiotics.impl.SemioticsPackageImpl#getAttribute()
		 * @generated
		 */
		EClass ATTRIBUTE = eINSTANCE.getAttribute();

		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE__KEY = eINSTANCE.getAttribute_Key();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ATTRIBUTE__VALUE = eINSTANCE.getAttribute_Value();

		/**
		 * The meta object literal for the '{@link us.coastalhacking.corvus.semiotics.impl.IMarkerImpl <em>IMarker</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see us.coastalhacking.corvus.semiotics.impl.IMarkerImpl
		 * @see us.coastalhacking.corvus.semiotics.impl.SemioticsPackageImpl#getIMarker()
		 * @generated
		 */
		EClass IMARKER = eINSTANCE.getIMarker();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMARKER__ID = eINSTANCE.getIMarker_Id();

		/**
		 * The meta object literal for the '<em><b>Creation Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMARKER__CREATION_TIME = eINSTANCE.getIMarker_CreationTime();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMARKER__TYPE = eINSTANCE.getIMarker_Type();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' map feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMARKER__ATTRIBUTES = eINSTANCE.getIMarker_Attributes();

		/**
		 * The meta object literal for the '<em><b>Resource</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMARKER__RESOURCE = eINSTANCE.getIMarker_Resource();

		/**
		 * The meta object literal for the '<em><b>Message</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMARKER__MESSAGE = eINSTANCE.getIMarker_Message();

		/**
		 * The meta object literal for the '<em><b>Location</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMARKER__LOCATION = eINSTANCE.getIMarker_Location();

		/**
		 * The meta object literal for the '<em><b>Char Start</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMARKER__CHAR_START = eINSTANCE.getIMarker_CharStart();

		/**
		 * The meta object literal for the '<em><b>Char End</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMARKER__CHAR_END = eINSTANCE.getIMarker_CharEnd();

		/**
		 * The meta object literal for the '<em><b>Line Number</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMARKER__LINE_NUMBER = eINSTANCE.getIMarker_LineNumber();

		/**
		 * The meta object literal for the '<em><b>Get Int</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation IMARKER___GET_INT__STRING = eINSTANCE.getIMarker__GetInt__String();

		/**
		 * The meta object literal for the '<em><b>Get String</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation IMARKER___GET_STRING__STRING = eINSTANCE.getIMarker__GetString__String();

		/**
		 * The meta object literal for the '{@link us.coastalhacking.corvus.semiotics.impl.IResourceImpl <em>IResource</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see us.coastalhacking.corvus.semiotics.impl.IResourceImpl
		 * @see us.coastalhacking.corvus.semiotics.impl.SemioticsPackageImpl#getIResource()
		 * @generated
		 */
		EClass IRESOURCE = eINSTANCE.getIResource();

		/**
		 * The meta object literal for the '<em><b>Full Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IRESOURCE__FULL_PATH = eINSTANCE.getIResource_FullPath();

		/**
		 * The meta object literal for the '<em><b>Markers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IRESOURCE__MARKERS = eINSTANCE.getIResource_Markers();

		/**
		 * The meta object literal for the '<em><b>Workspace Root</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IRESOURCE__WORKSPACE_ROOT = eINSTANCE.getIResource_WorkspaceRoot();

		/**
		 * The meta object literal for the '<em><b>Get Key</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation IRESOURCE___GET_KEY = eINSTANCE.getIResource__GetKey();

		/**
		 * The meta object literal for the '{@link us.coastalhacking.corvus.semiotics.impl.IWorkspaceRootImpl <em>IWorkspace Root</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see us.coastalhacking.corvus.semiotics.impl.IWorkspaceRootImpl
		 * @see us.coastalhacking.corvus.semiotics.impl.SemioticsPackageImpl#getIWorkspaceRoot()
		 * @generated
		 */
		EClass IWORKSPACE_ROOT = eINSTANCE.getIWorkspaceRoot();

		/**
		 * The meta object literal for the '<em><b>Members</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IWORKSPACE_ROOT__MEMBERS = eINSTANCE.getIWorkspaceRoot_Members();

		/**
		 * The meta object literal for the '{@link us.coastalhacking.corvus.semiotics.EntryPoint <em>Entry Point</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see us.coastalhacking.corvus.semiotics.EntryPoint
		 * @see us.coastalhacking.corvus.semiotics.impl.SemioticsPackageImpl#getEntryPoint()
		 * @generated
		 */
		EClass ENTRY_POINT = eINSTANCE.getEntryPoint();

		/**
		 * The meta object literal for the '{@link us.coastalhacking.corvus.semiotics.impl.MarkerEntryPointImpl <em>Marker Entry Point</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see us.coastalhacking.corvus.semiotics.impl.MarkerEntryPointImpl
		 * @see us.coastalhacking.corvus.semiotics.impl.SemioticsPackageImpl#getMarkerEntryPoint()
		 * @generated
		 */
		EClass MARKER_ENTRY_POINT = eINSTANCE.getMarkerEntryPoint();

		/**
		 * The meta object literal for the '{@link us.coastalhacking.corvus.semiotics.Sink <em>Sink</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see us.coastalhacking.corvus.semiotics.Sink
		 * @see us.coastalhacking.corvus.semiotics.impl.SemioticsPackageImpl#getSink()
		 * @generated
		 */
		EClass SINK = eINSTANCE.getSink();

		/**
		 * The meta object literal for the '{@link us.coastalhacking.corvus.semiotics.impl.MarkerSinkImpl <em>Marker Sink</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see us.coastalhacking.corvus.semiotics.impl.MarkerSinkImpl
		 * @see us.coastalhacking.corvus.semiotics.impl.SemioticsPackageImpl#getMarkerSink()
		 * @generated
		 */
		EClass MARKER_SINK = eINSTANCE.getMarkerSink();

	}

} //SemioticsPackage

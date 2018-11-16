/**
 */
package us.coastalhacking.corvus.semiotics.impl;

import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import us.coastalhacking.corvus.semiotics.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SemioticsFactoryImpl extends EFactoryImpl implements SemioticsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SemioticsFactory init() {
		try {
			SemioticsFactory theSemioticsFactory = (SemioticsFactory)EPackage.Registry.INSTANCE.getEFactory(SemioticsPackage.eNS_URI);
			if (theSemioticsFactory != null) {
				return theSemioticsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new SemioticsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SemioticsFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case SemioticsPackage.ROOT: return createRoot();
			case SemioticsPackage.ATTRIBUTE: return (EObject)createAttribute();
			case SemioticsPackage.IMARKER: return createIMarker();
			case SemioticsPackage.IRESOURCE: return createIResource();
			case SemioticsPackage.IWORKSPACE_ROOT: return createIWorkspaceRoot();
			case SemioticsPackage.MARKER_ENTRY_POINT: return createMarkerEntryPoint();
			case SemioticsPackage.MARKER_SINK: return createMarkerSink();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Root createRoot() {
		RootImpl root = new RootImpl();
		return root;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map.Entry<String, String> createAttribute() {
		AttributeImpl attribute = new AttributeImpl();
		return attribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IMarker createIMarker() {
		IMarkerImpl iMarker = new IMarkerImpl();
		return iMarker;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IResource createIResource() {
		IResourceImpl iResource = new IResourceImpl();
		return iResource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IWorkspaceRoot createIWorkspaceRoot() {
		IWorkspaceRootImpl iWorkspaceRoot = new IWorkspaceRootImpl();
		return iWorkspaceRoot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MarkerEntryPoint createMarkerEntryPoint() {
		MarkerEntryPointImpl markerEntryPoint = new MarkerEntryPointImpl();
		return markerEntryPoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MarkerSink createMarkerSink() {
		MarkerSinkImpl markerSink = new MarkerSinkImpl();
		return markerSink;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SemioticsPackage getSemioticsPackage() {
		return (SemioticsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static SemioticsPackage getPackage() {
		return SemioticsPackage.eINSTANCE;
	}

} //SemioticsFactoryImpl

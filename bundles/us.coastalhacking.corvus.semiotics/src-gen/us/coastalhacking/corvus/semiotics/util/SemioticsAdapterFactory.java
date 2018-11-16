/**
 */
package us.coastalhacking.corvus.semiotics.util;

import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import us.coastalhacking.corvus.semiotics.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see us.coastalhacking.corvus.semiotics.SemioticsPackage
 * @generated
 */
public class SemioticsAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static SemioticsPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SemioticsAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = SemioticsPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SemioticsSwitch<Adapter> modelSwitch =
		new SemioticsSwitch<Adapter>() {
			@Override
			public Adapter caseSemiotics(Semiotics object) {
				return createSemioticsAdapter();
			}
			@Override
			public Adapter caseSignifier(Signifier object) {
				return createSignifierAdapter();
			}
			@Override
			public Adapter caseSignified(Signified object) {
				return createSignifiedAdapter();
			}
			@Override
			public Adapter caseRoot(Root object) {
				return createRootAdapter();
			}
			@Override
			public Adapter caseAttribute(Map.Entry<String, String> object) {
				return createAttributeAdapter();
			}
			@Override
			public Adapter caseIMarker(IMarker object) {
				return createIMarkerAdapter();
			}
			@Override
			public Adapter caseIResource(IResource object) {
				return createIResourceAdapter();
			}
			@Override
			public Adapter caseIWorkspaceRoot(IWorkspaceRoot object) {
				return createIWorkspaceRootAdapter();
			}
			@Override
			public Adapter caseEntryPoint(EntryPoint object) {
				return createEntryPointAdapter();
			}
			@Override
			public Adapter caseMarkerEntryPoint(MarkerEntryPoint object) {
				return createMarkerEntryPointAdapter();
			}
			@Override
			public Adapter caseSink(Sink object) {
				return createSinkAdapter();
			}
			@Override
			public Adapter caseMarkerSink(MarkerSink object) {
				return createMarkerSinkAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link us.coastalhacking.corvus.semiotics.Semiotics <em>Semiotics</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see us.coastalhacking.corvus.semiotics.Semiotics
	 * @generated
	 */
	public Adapter createSemioticsAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link us.coastalhacking.corvus.semiotics.Signifier <em>Signifier</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see us.coastalhacking.corvus.semiotics.Signifier
	 * @generated
	 */
	public Adapter createSignifierAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link us.coastalhacking.corvus.semiotics.Signified <em>Signified</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see us.coastalhacking.corvus.semiotics.Signified
	 * @generated
	 */
	public Adapter createSignifiedAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link us.coastalhacking.corvus.semiotics.Root <em>Root</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see us.coastalhacking.corvus.semiotics.Root
	 * @generated
	 */
	public Adapter createRootAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createAttributeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link us.coastalhacking.corvus.semiotics.IMarker <em>IMarker</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see us.coastalhacking.corvus.semiotics.IMarker
	 * @generated
	 */
	public Adapter createIMarkerAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link us.coastalhacking.corvus.semiotics.IResource <em>IResource</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see us.coastalhacking.corvus.semiotics.IResource
	 * @generated
	 */
	public Adapter createIResourceAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link us.coastalhacking.corvus.semiotics.IWorkspaceRoot <em>IWorkspace Root</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see us.coastalhacking.corvus.semiotics.IWorkspaceRoot
	 * @generated
	 */
	public Adapter createIWorkspaceRootAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link us.coastalhacking.corvus.semiotics.EntryPoint <em>Entry Point</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see us.coastalhacking.corvus.semiotics.EntryPoint
	 * @generated
	 */
	public Adapter createEntryPointAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link us.coastalhacking.corvus.semiotics.MarkerEntryPoint <em>Marker Entry Point</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see us.coastalhacking.corvus.semiotics.MarkerEntryPoint
	 * @generated
	 */
	public Adapter createMarkerEntryPointAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link us.coastalhacking.corvus.semiotics.Sink <em>Sink</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see us.coastalhacking.corvus.semiotics.Sink
	 * @generated
	 */
	public Adapter createSinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link us.coastalhacking.corvus.semiotics.MarkerSink <em>Marker Sink</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see us.coastalhacking.corvus.semiotics.MarkerSink
	 * @generated
	 */
	public Adapter createMarkerSinkAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //SemioticsAdapterFactory

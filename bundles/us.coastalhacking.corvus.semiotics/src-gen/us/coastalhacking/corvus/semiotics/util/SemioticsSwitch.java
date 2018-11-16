/**
 */
package us.coastalhacking.corvus.semiotics.util;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import us.coastalhacking.corvus.semiotics.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see us.coastalhacking.corvus.semiotics.SemioticsPackage
 * @generated
 */
public class SemioticsSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static SemioticsPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SemioticsSwitch() {
		if (modelPackage == null) {
			modelPackage = SemioticsPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case SemioticsPackage.SEMIOTICS: {
				Semiotics semiotics = (Semiotics)theEObject;
				T result = caseSemiotics(semiotics);
				if (result == null) result = caseSignifier(semiotics);
				if (result == null) result = caseSignified(semiotics);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SemioticsPackage.SIGNIFIER: {
				Signifier signifier = (Signifier)theEObject;
				T result = caseSignifier(signifier);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SemioticsPackage.SIGNIFIED: {
				Signified signified = (Signified)theEObject;
				T result = caseSignified(signified);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SemioticsPackage.ROOT: {
				Root root = (Root)theEObject;
				T result = caseRoot(root);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SemioticsPackage.ATTRIBUTE: {
				@SuppressWarnings("unchecked") Map.Entry<String, String> attribute = (Map.Entry<String, String>)theEObject;
				T result = caseAttribute(attribute);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SemioticsPackage.IMARKER: {
				IMarker iMarker = (IMarker)theEObject;
				T result = caseIMarker(iMarker);
				if (result == null) result = caseSemiotics(iMarker);
				if (result == null) result = caseSignifier(iMarker);
				if (result == null) result = caseSignified(iMarker);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SemioticsPackage.IRESOURCE: {
				IResource iResource = (IResource)theEObject;
				T result = caseIResource(iResource);
				if (result == null) result = caseSemiotics(iResource);
				if (result == null) result = caseSignifier(iResource);
				if (result == null) result = caseSignified(iResource);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SemioticsPackage.IWORKSPACE_ROOT: {
				IWorkspaceRoot iWorkspaceRoot = (IWorkspaceRoot)theEObject;
				T result = caseIWorkspaceRoot(iWorkspaceRoot);
				if (result == null) result = caseSemiotics(iWorkspaceRoot);
				if (result == null) result = caseSignifier(iWorkspaceRoot);
				if (result == null) result = caseSignified(iWorkspaceRoot);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SemioticsPackage.ENTRY_POINT: {
				EntryPoint entryPoint = (EntryPoint)theEObject;
				T result = caseEntryPoint(entryPoint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SemioticsPackage.MARKER_ENTRY_POINT: {
				MarkerEntryPoint markerEntryPoint = (MarkerEntryPoint)theEObject;
				T result = caseMarkerEntryPoint(markerEntryPoint);
				if (result == null) result = caseEntryPoint(markerEntryPoint);
				if (result == null) result = caseSemiotics(markerEntryPoint);
				if (result == null) result = caseSignifier(markerEntryPoint);
				if (result == null) result = caseSignified(markerEntryPoint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SemioticsPackage.SINK: {
				Sink sink = (Sink)theEObject;
				T result = caseSink(sink);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case SemioticsPackage.MARKER_SINK: {
				MarkerSink markerSink = (MarkerSink)theEObject;
				T result = caseMarkerSink(markerSink);
				if (result == null) result = caseSink(markerSink);
				if (result == null) result = caseSemiotics(markerSink);
				if (result == null) result = caseSignifier(markerSink);
				if (result == null) result = caseSignified(markerSink);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Semiotics</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Semiotics</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSemiotics(Semiotics object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Signifier</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Signifier</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSignifier(Signifier object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Signified</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Signified</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSignified(Signified object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Root</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Root</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRoot(Root object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Attribute</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Attribute</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAttribute(Map.Entry<String, String> object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IMarker</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IMarker</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIMarker(IMarker object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IResource</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IResource</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIResource(IResource object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>IWorkspace Root</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IWorkspace Root</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseIWorkspaceRoot(IWorkspaceRoot object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Entry Point</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Entry Point</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEntryPoint(EntryPoint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Marker Entry Point</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Marker Entry Point</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMarkerEntryPoint(MarkerEntryPoint object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sink</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sink</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSink(Sink object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Marker Sink</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Marker Sink</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMarkerSink(MarkerSink object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //SemioticsSwitch

package us.coastalhacking.corvus.emf.provider;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;

public class ShallowCopier extends Copier {

	private static final long serialVersionUID = -3287110634505273804L;

	@Override
	protected void copyReference(EReference eReference, EObject eObject, EObject copyEObject) {
		// Ignored, only copy attributes and proxy uri
	}
	
	@Override
	public void copyReferences() {
		// Ignored, only copy attributes and proxy uri
	}
}

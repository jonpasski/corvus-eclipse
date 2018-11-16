package us.coastalhacking.corvus.emf.provider;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.jupiter.api.Test;

import us.coastalhacking.corvus.semiotics.IMarker;
import us.coastalhacking.corvus.semiotics.IResource;
import us.coastalhacking.corvus.semiotics.MarkerEntryPoint;
import us.coastalhacking.corvus.semiotics.Root;
import us.coastalhacking.corvus.semiotics.SemioticsFactory;

class SemioticsTests {

	@Test
	void shouldReturnMarkerMessageViaMarkerEntryPoint_getName() {
		IMarker marker = SemioticsFactory.eINSTANCE.createIMarker();
		marker.setId(1234L);
		String message = "Your Name";
		marker.getAttributes().put(org.eclipse.core.resources.IMarker.MESSAGE, message);

		ResourceImpl resource = new ResourceImpl();
		Root root = SemioticsFactory.eINSTANCE.createRoot();
		resource.getContents().add(root);
		MarkerEntryPoint ep = SemioticsFactory.eINSTANCE.createMarkerEntryPoint();
		ep.getSignifiers().add(marker);
		root.getSemiotics().add(ep);
		
		assertEquals("//@semiotics[key='Your%20Name']", resource.getURIFragment(ep)); 
	}

	/*
	 * Some models elide SemioticsPackage.Literals.SEMIOTICS__NAME via an
	 * EOperation (e.g. SemioticsPackage.Literals.IRESOURCE___GET_KEY). The
	 * test below verifies that the operation still provides the key for 
	 * SemioticsPackage.Literals.ROOT__SEMIOTICS.
	 * 
	 * Other models don
	 */
	@Test
	void shouldSupportIntrinsicOrKeyed() {
		ResourceImpl resource = new ResourceImpl();
		Root root = SemioticsFactory.eINSTANCE.createRoot();
		resource.getContents().add(root);

		IResource keyedResource = SemioticsFactory.eINSTANCE.createIResource();
		keyedResource.setFullPath("/foo/bar");
		root.getSemiotics().add(keyedResource);
		assertEquals("//@semiotics[key='%2Ffoo%2Fbar']", resource.getURIFragment(keyedResource)); 

		IResource intrinsicResource = SemioticsFactory.eINSTANCE.createIResource();
		String uuid = EcoreUtil.generateUUID();
		intrinsicResource.setUuid(uuid);
		root.getSemiotics().add(intrinsicResource);
		assertEquals(uuid, resource.getURIFragment(intrinsicResource));
	}
}

package us.coastalhacking.corvus.emf.provider;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import us.coastalhacking.corvus.test.util.TestId;
import us.coastalhacking.corvus.test.util.TestIds;
import us.coastalhacking.corvus.test.util.TestReference;
import us.coastalhacking.corvus.test.util.TestRoot;
import us.coastalhacking.corvus.test.util.UtilFactory;
import us.coastalhacking.corvus.test.util.UtilPackage;

class ShallowCopierTest {

	TestRoot root;
	TestIds testIds;
	TestId testId1;
	TestReference testReference;
	EcoreUtil.Copier copier;
	List<EObject> all;

	// Prep
	@BeforeEach
	void beforeEach() {
		root = UtilFactory.eINSTANCE.createTestRoot();
		testReference = UtilFactory.eINSTANCE.createTestReference();
		root.getTestReferences().add(testReference);
		testIds = UtilFactory.eINSTANCE.createTestIds();
		testIds.setRoot(root);
		testId1 = UtilFactory.eINSTANCE.createTestId();
		String expectedKey = "someKey";
		testId1.setKey(expectedKey);
		testId1.setTestReference(testReference);
		testIds.getTestIds().add(testId1);
		copier = new ShallowCopier();

		// Copying bidirectional references requires the copier to contain both
		// references
		all = new ArrayList<>();
		all.add(testId1);
		all.add(testReference);

		assertTrue(testId1.eIsSet(UtilPackage.Literals.TEST_ID__ROOT));
		assertTrue(testId1.eIsSet(UtilPackage.Literals.TEST_ID__TEST_REFERENCE));
		assertTrue(testId1.eIsSet(UtilPackage.Literals.TEST_ID__KEY));
		assertTrue(testReference.eIsSet(UtilPackage.Literals.TEST_REFERENCE__ROOT));
		assertTrue(testReference.eIsSet(UtilPackage.Literals.TEST_REFERENCE__TEST_ID));
	}

	@Test
	void shouldProve() {
		// Prep
		copier = new EcoreUtil.Copier();

		// Execute
		Collection<EObject> copiedAll = copier.copyAll(all);
		copier.copyReferences();

		// Verify
		copiedAll.stream().forEach(eo -> {
			if (eo.eClass() == UtilPackage.Literals.TEST_ID) {
				assertFalse(eo.eIsSet(UtilPackage.Literals.TEST_ID__ROOT));
				assertTrue(eo.eIsSet(UtilPackage.Literals.TEST_ID__TEST_REFERENCE));
				assertTrue(eo.eIsSet(UtilPackage.Literals.TEST_ID__KEY));
			} else if (eo.eClass() == UtilPackage.Literals.TEST_REFERENCE) {
				assertFalse(eo.eIsSet(UtilPackage.Literals.TEST_REFERENCE__ROOT));
				assertTrue(eo.eIsSet(UtilPackage.Literals.TEST_REFERENCE__TEST_ID));
			} else {
				fail("Invalid copied class: " + eo.eClass().toString());
			}
		});
	}

	@Test
	void shouldOnlyCopyAttribute() {
		// Execute
		Collection<EObject> copiedAll = copier.copyAll(all);
		copier.copyReferences();

		// Verify
		copiedAll.stream().forEach(eo -> {
			if (eo.eClass() == UtilPackage.Literals.TEST_ID) {
				assertFalse(eo.eIsSet(UtilPackage.Literals.TEST_ID__ROOT));
				assertFalse(eo.eIsSet(UtilPackage.Literals.TEST_ID__TEST_REFERENCE));
				assertTrue(eo.eIsSet(UtilPackage.Literals.TEST_ID__KEY));
			} else if (eo.eClass() == UtilPackage.Literals.TEST_REFERENCE) {
				assertFalse(eo.eIsSet(UtilPackage.Literals.TEST_REFERENCE__ROOT));
				assertFalse(eo.eIsSet(UtilPackage.Literals.TEST_REFERENCE__TEST_ID));
			} else {
				fail("Invalid copied class: " + eo.eClass().toString());
			}
		});
	}
}

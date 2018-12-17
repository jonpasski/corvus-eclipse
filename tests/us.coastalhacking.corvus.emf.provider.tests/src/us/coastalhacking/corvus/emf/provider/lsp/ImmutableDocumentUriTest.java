package us.coastalhacking.corvus.emf.provider.lsp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ImmutableDocumentUriTest {

	ImmutableDocumentUri provider;
	ImmutableRange<Integer> mockRange;
	final String documentUri = "expectedUri";

	@SuppressWarnings("unchecked")
	@BeforeEach
	void subBeforeEach() {
		mockRange = mock(ImmutableRange.class);
		provider = new ImmutableDocumentUri(documentUri, mockRange);
	}

	@Test
	void shouldGetUri() {
		// Verify
		assertEquals(documentUri, provider.getUri());
	}

	@Test
	void shouldGetRange() {
		// Verify
		assertEquals(mockRange, provider.getRange());
	}
}

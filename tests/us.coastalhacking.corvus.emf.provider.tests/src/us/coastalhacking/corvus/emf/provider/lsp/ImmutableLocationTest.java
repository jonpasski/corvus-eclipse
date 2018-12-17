package us.coastalhacking.corvus.emf.provider.lsp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import us.coastalhacking.corvus.emf.lsp.Location;

class ImmutableLocationTest {

	Location provider;
	ImmutableDocumentUri mockUri;

	@BeforeEach
	void subBeforeEach() {
		mockUri = mock(ImmutableDocumentUri.class);
		provider = new ImmutableLocation(mockUri);
	}

	@Test
	void shouldGetDocumentUri() {
		// Verify
		assertEquals(mockUri, provider.getDocumentUri());
	}

}

package us.coastalhacking.corvus.emf.provider.lsp;

import us.coastalhacking.corvus.emf.lsp.Location;

public class ImmutableLocation implements Location {

	private final ImmutableDocumentUri documentUri;

	public ImmutableLocation(ImmutableDocumentUri documentUri) {
		this.documentUri = documentUri;
	}

	@Override
	public ImmutableDocumentUri getDocumentUri() {
		return documentUri;
	}
}

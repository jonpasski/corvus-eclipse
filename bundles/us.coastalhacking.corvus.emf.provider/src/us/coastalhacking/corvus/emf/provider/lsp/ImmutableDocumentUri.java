package us.coastalhacking.corvus.emf.provider.lsp;

import us.coastalhacking.corvus.emf.lsp.DocumentUri;

public class ImmutableDocumentUri implements DocumentUri {

	private final ImmutableRange<? extends Number> range;
	private final String uri;

	public ImmutableDocumentUri(String uri, ImmutableRange<?> range) {
		this.uri = new String(uri);
		this.range = range;
	}

	@Override
	public String getUri() {
		return uri;
	}

	public ImmutableRange<? extends Number> getRange() {
		return range;
	}
}

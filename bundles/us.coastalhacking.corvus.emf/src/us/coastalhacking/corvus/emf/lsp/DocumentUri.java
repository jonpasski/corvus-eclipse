package us.coastalhacking.corvus.emf.lsp;

public interface DocumentUri {

	// Feature IDs
	int DOCUMENT_URI__URI = 0;
	int DOCUMENT_URI__RANGE = 1;

	String getUri();

	Range<? extends Number> getRange();
}

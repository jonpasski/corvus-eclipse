package us.coastalhacking.corvus.emf.lsp;

public interface Location {

	// Feature ID
	int LOCATION__DOCUMENT_URI = 0;
	
	DocumentUri getDocumentUri();

}

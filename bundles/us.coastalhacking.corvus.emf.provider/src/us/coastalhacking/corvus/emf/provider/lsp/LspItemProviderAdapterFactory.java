package us.coastalhacking.corvus.emf.provider.lsp;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;

import us.coastalhacking.corvus.emf.ILocationItemProvider;
import us.coastalhacking.corvus.emf.lsp.DocumentUri;
import us.coastalhacking.corvus.emf.lsp.Location;
import us.coastalhacking.corvus.emf.provider.ItemProviderAdapterFactoryBase;

public class LspItemProviderAdapterFactory extends ItemProviderAdapterFactoryBase {

	protected static Package PACKAGE = Location.class.getPackage();
	
	public LspItemProviderAdapterFactory() {
		supportedTypes.add(DocumentUri.class);
	}
	
	protected Adapter createDocumentUriAdapter() {
		return new DocumentUriItemProvider(this);
	}

	@Override
	public boolean isFactoryForType(Object type) {
		return type == PACKAGE || type instanceof ILocationItemProvider || super.isFactoryForType(type);  

	}
}

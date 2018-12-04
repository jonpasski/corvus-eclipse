package us.coastalhacking.corvus.emf.provider;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.provider.DecoratorAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemProviderDecorator;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;

//TODO: extend ResourceItemProviderAdapterFactory instead of decorating
@Deprecated
public class DecoratedResourceItemProviderAdapterFactory extends DecoratorAdapterFactory {

	public DecoratedResourceItemProviderAdapterFactory() {
		super(new ResourceItemProviderAdapterFactory());
	}

	@Override
	protected IItemProviderDecorator createItemProviderDecorator(Object target, Object type) {
		// Currently only extending commands
		if (type == IEditingDomainItemProvider.class) {
			// Mimics conditional logic of ResourceItemProviderAdapterFactory.createAdapter(Notifier)
			if (target instanceof Resource) {
				return new DecoratedResourceItemProvider(this);
			}
			return new DecoratedResourceSetItemProvider(this);
		}
		return new BaseDecoratedItemProvider(this);
	}

}


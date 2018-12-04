package us.coastalhacking.corvus.emf.provider;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderDecorator;

public class BaseDecoratedItemProvider extends ItemProviderDecorator implements Adapter, IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {

	public BaseDecoratedItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	public Notifier getTarget() {
		if (decoratedItemProvider instanceof Adapter) {
			return ((Adapter) decoratedItemProvider).getTarget();
		}
		return null;
	}

	@Override
	public void setTarget(Notifier newTarget) {
		if (decoratedItemProvider instanceof Adapter) {
			((Adapter) decoratedItemProvider).setTarget(newTarget);
		}
	}
}

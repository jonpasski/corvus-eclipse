package us.coastalhacking.corvus.emf.provider;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.edit.provider.ChangeNotifier;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.Disposable;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.INotifyChangedListener;

public class ItemProviderAdapterFactoryBase extends AdapterFactoryImpl
		implements ComposeableAdapterFactory, IChangeNotifier, IDisposable {

	// IAdapterFactor
	//
	protected Collection<Object> supportedTypes = new ArrayList<Object>();

	@Override
	public boolean isFactoryForType(Object type) {
		return supportedTypes.contains(type) ? true : super.isFactoryForType(type);
	}

	// EMF Generator Patterns below

	// ComposeableAdapterFactory
	// TODO: replace with atomic reference
	//
	protected ComposedAdapterFactory parentAdapterFactory;

	@Override
	public ComposeableAdapterFactory getRootAdapterFactory() {
		return parentAdapterFactory == null ? this : parentAdapterFactory.getRootAdapterFactory();
	}

	@Override
	public void setParentAdapterFactory(ComposedAdapterFactory parentAdapterFactory) {
		this.parentAdapterFactory = parentAdapterFactory;
	}

	// IChangeNotifier
	// TODO: replace with concurrent version
	//
	protected IChangeNotifier changeNotifier = new ChangeNotifier();

	@Override
	public void addListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.addListener(notifyChangedListener);
	}

	@Override
	public void removeListener(INotifyChangedListener notifyChangedListener) {
		changeNotifier.removeListener(notifyChangedListener);
	}

	@Override
	public void fireNotifyChanged(Notification notification) {
		changeNotifier.fireNotifyChanged(notification);

		if (parentAdapterFactory != null) {
			parentAdapterFactory.fireNotifyChanged(notification);
		}
	}

	// IDisposable
	// TODO: replace with concurrent version
	//
	protected Disposable disposable = new Disposable();

	@Override
	protected void associate(Adapter adapter, Notifier target) {
		super.associate(adapter, target);
		if (adapter instanceof IDisposable) {
			disposable.add(adapter);
		}
	}

	public void dispose() {
		disposable.dispose();
	}
}

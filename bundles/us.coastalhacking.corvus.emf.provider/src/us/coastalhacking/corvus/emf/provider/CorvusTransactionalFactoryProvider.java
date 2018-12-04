package us.coastalhacking.corvus.emf.provider;

import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain.Factory;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.eclipse.emf.workspace.WorkspaceEditingDomainFactory;
import org.eclipse.emf.workspace.impl.WorkspaceCommandStackImpl;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ServiceScope;

@Component(service = Factory.class, immediate=true, scope=ServiceScope.SINGLETON)
public class CorvusTransactionalFactoryProvider extends WorkspaceEditingDomainFactory {
	
	@Override
	public synchronized TransactionalEditingDomain createEditingDomain(IOperationHistory history) {
		WorkspaceCommandStackImpl stack = new WorkspaceCommandStackImpl(history);
		stack.setResourceUndoContextPolicy(getResourceUndoContextPolicy());

		// TODO: whiteboard adapter factories w/o using eclipse extensions
		final ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(
				ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		adapterFactory.addAdapterFactory(new DecoratedResourceItemProviderAdapterFactory());
		final TransactionalEditingDomain result = new TransactionalEditingDomainImpl(adapterFactory, stack);

		mapResourceSet(result);

		return result;
	}
}

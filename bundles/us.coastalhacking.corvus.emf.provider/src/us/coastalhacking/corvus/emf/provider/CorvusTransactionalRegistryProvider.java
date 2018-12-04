package us.coastalhacking.corvus.emf.provider;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain.Factory;
import org.eclipse.emf.transaction.TransactionalEditingDomain.Registry;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

@Component(service = Registry.class, immediate=true, scope=ServiceScope.SINGLETON)
public class CorvusTransactionalRegistryProvider implements Registry {

	final Map<String, TransactionalEditingDomain> registries = new ConcurrentHashMap<>();

	@Reference
	Factory factory;

	@Deactivate
	void deactivate() {
		registries.values().stream().forEach(ted -> ted.dispose());
		registries.clear();
	}

	@Override
	public TransactionalEditingDomain getEditingDomain(String id) {
		if (registries.containsKey(id)) {
			return registries.get(id);
		}
		TransactionalEditingDomain domain = factory.createEditingDomain();
		add(id, domain);
		return domain;
	}

	@Override
	public void add(String id, TransactionalEditingDomain domain) {
		registries.put(id, domain);
	}

	@Override
	public TransactionalEditingDomain remove(String id) {
		return registries.remove(id);
	}

}

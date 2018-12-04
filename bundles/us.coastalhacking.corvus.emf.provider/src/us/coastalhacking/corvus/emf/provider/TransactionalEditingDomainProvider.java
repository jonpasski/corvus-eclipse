package us.coastalhacking.corvus.emf.provider;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.core.runtime.jobs.JobGroup;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.DragAndDropCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain.Registry;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

import us.coastalhacking.corvus.emf.EmfApi;
import us.coastalhacking.corvus.emf.GetMaybeAddCommand;
import us.coastalhacking.corvus.emf.ResourceInitializer;
import us.coastalhacking.corvus.emf.TransactionIdUtil;

@Component(service = IEditingDomainProvider.class, configurationPid = EmfApi.IEditingDomainProvider.Component.CONFIG_PID, configurationPolicy = ConfigurationPolicy.REQUIRE)
public class TransactionalEditingDomainProvider implements IEditingDomainProvider {

	AtomicBoolean activated = new AtomicBoolean(false);
	AtomicBoolean deactivated = new AtomicBoolean(false);
	final List<ResourceSetListener> listeners = new CopyOnWriteArrayList<>();
	final List<ResourceInitializer> initializers = new CopyOnWriteArrayList<>();
	static final int NOT_USED = -1;

	JobGroup jobGroup;
	TransactionalEditingDomain editingDomain;
	String transactionId;
	URI projectUri;

	@Reference
	TransactionIdUtil transactionIdUtil;

	@Reference
	Registry registry;

	@Reference(cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC, policyOption = ReferencePolicyOption.GREEDY)
	void setResourceSetListeners(ResourceSetListener listener) throws Exception {
		if (activated.get()) {
			editingDomain.addResourceSetListener(listener);
		} else {
			listeners.add(listener);
		}
	}

	void unsetResourceSetListeners(ResourceSetListener listener) {
		if (activated.get()) {
			editingDomain.removeResourceSetListener(listener);
		} else {
			listeners.remove(listener);
		}
	}

	@Reference(cardinality = ReferenceCardinality.MULTIPLE, policy = ReferencePolicy.DYNAMIC, policyOption = ReferencePolicyOption.GREEDY)
	void setInitializer(ResourceInitializer initializer) {
		if (activated.get()) {
			initializeResource(editingDomain, projectUri, Collections.singletonList(initializer));
		} else {
			initializers.add(initializer);
		}
	}

	void unsetInitializer(ResourceInitializer initializer) {
		if (!activated.get()) {
			initializers.remove(initializer);
		}
	}

	@Activate
	void activate(Map<String, Object> props) {
		transactionId = transactionIdUtil.getId(props);
		projectUri = transactionIdUtil.getUri(transactionId);
		editingDomain = registry.getEditingDomain(transactionId);
		jobGroup = new JobGroup(MessageFormat.format("Initializing resources for transaction ID {0}", transactionId), 0,
				0);
		activated.set(true);

		for (ResourceSetListener listener : listeners) {
			editingDomain.addResourceSetListener(listener);
		}
		listeners.clear();
		initializeResource(editingDomain, projectUri, initializers);
		initializers.clear();
	}

	@Deactivate
	void deactivate() {
		if (deactivated.getAndSet(true)) {
			return;
		}
		registry.remove(transactionId);
		editingDomain.dispose();
	}

	@Override
	public synchronized TransactionalEditingDomain getEditingDomain() {
		return editingDomain;
	}

	// TODO: move this to a ResourceInitializerItemProvider.createCommand...
	void initializeResource(EditingDomain editingDomain, URI projectUri, List<ResourceInitializer> initializers) {
		final Map<URI, URI> entries = new HashMap<>();
		final Map<URI, EObject> roots = new HashMap<>();

		for (ResourceInitializer ri : initializers) {
			final URI logicalUri = URI.createURI(ri.getLogical());
			final URI physicalUri = projectUri.appendSegment(ri.getFilename());
			entries.put(logicalUri, physicalUri);
			roots.put(logicalUri, ri.getRoot());
		}

		final CompoundCommand compound = new CompoundCommand(CompoundCommand.LAST_COMMAND_ALL);

		compound.append(DragAndDropCommand.create(editingDomain, editingDomain.getResourceSet(), NOT_USED, NOT_USED,
				NOT_USED, entries.entrySet()));

		roots.entrySet().stream().forEach(es -> {
			compound.append(GetMaybeAddCommand.createCommand(editingDomain, es.getKey(), es.getValue()));
		});

		editingDomain.getCommandStack().execute(compound.unwrap());
	}
}

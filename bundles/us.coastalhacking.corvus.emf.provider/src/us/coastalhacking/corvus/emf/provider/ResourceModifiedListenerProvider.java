package us.coastalhacking.corvus.emf.provider;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.eclipse.core.resources.IResourceRuleFactory;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.MultiRule;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.change.FeatureChange;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.TransactionChangeDescription;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain.Registry;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

import us.coastalhacking.corvus.emf.EmfApi;

@Component(service=ResourceSetListener.class, configurationPid=EmfApi.ResourceModifiedListener.Component.CONFIG_PID, configurationPolicy=ConfigurationPolicy.REQUIRE, immediate=true)
public class ResourceModifiedListenerProvider implements ResourceSetListener {

	private String transId;
	private TransactionalEditingDomain domain;

	@Reference
	IWorkspace workspace;

	@Reference(name=EmfApi.ResourceModifiedListener.Reference.REGISTRY)
	Registry registry;

	@Activate
	void activate(Map<String, Object> props) {
		transId = (String)props.get(EmfApi.TransactionalEditingDomain.Properties.ID);
		domain = registry.getEditingDomain(transId);
		domain.addResourceSetListener(this);
	}
	
	@Deactivate
	void deactivate() {
		domain.removeResourceSetListener(this);
	}

	@Override
	public NotificationFilter getFilter() {
		return NotificationFilter.NOT_TOUCH;
	}

	@Override
	public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {
		return null;
	}

	@Override
	public void resourceSetChanged(ResourceSetChangeEvent event) {
		final Transaction tx = event.getTransaction();
		Optional.ofNullable(tx.getChangeDescription()).ifPresent(desc -> {
			final Set<URI> uris = new HashSet<>();
			addUris(uris, desc);

			if (!uris.isEmpty()) {
				SaveResourceJob job = new SaveResourceJob(uris, domain, getOptions());
				job.setRule(getRule(uris));
				job.schedule();
			}
		});
	}

	void addUris(Set<URI> uris, TransactionChangeDescription description) {
		// bug somewhere in EMFT when getting the keySet versus iterator, so using
		// iterator
		EMap<EObject, EList<FeatureChange>> objectChanges = description.getObjectChanges();
		Iterator<Map.Entry<EObject, EList<FeatureChange>>> iter = objectChanges.iterator();
		iter.forEachRemaining(entry -> {
			addUri(entry.getKey(), uris);
		});
		
		// In EMFT, description.getObjectChanges() returns objects attached to the graph, either newly added or pre-existing,
		// that have changed. description.getObjectsToDetach returns, for whatever reason, those objects recently added
		Iterator<EObject> iterDetach = description.getObjectsToDetach().iterator();
		iterDetach.forEachRemaining(eo -> {
			addUri(eo, uris);
		});
	}
	
	void addUri(EObject eObject, Set<URI> uris) {
		Optional.ofNullable(eObject.eResource()).ifPresent(r -> {
			URI physical = r.getResourceSet().getURIConverter().normalize(r.getURI());
			if (physical.isPlatform()) {
				uris.add(physical);
			}		
		});
	}
	
	@Override
	public boolean isAggregatePrecommitListener() {
		return false;
	}

	@Override
	public boolean isPrecommitOnly() {
		return false;
	}

	@Override
	public boolean isPostcommitOnly() {
		return true;
	}

	protected Map<String, Object> getOptions() {
		return Collections.emptyMap();
	}

	protected ISchedulingRule getRule(Set<URI> uris) {
		IWorkspaceRoot root = workspace.getRoot();
		IResourceRuleFactory ruleFactory = workspace.getRuleFactory();
		ISchedulingRule[] rules = uris.stream().filter(URI::isPlatform).map(u -> u.toPlatformString(true))
				.map(s -> root.findMember(s)).map(r -> ruleFactory.modifyRule(r)).toArray(ISchedulingRule[]::new);
		return new MultiRule(rules);
	}

	public static class SaveResourceJob extends WorkspaceJob {

		private final TransactionalEditingDomain domain;
		private final Map<String, Object> options;
		private final Collection<URI> uris;

		public SaveResourceJob(Collection<URI> uris, TransactionalEditingDomain domain, Map<String, Object> options) {
			super("Auto-save resources");
			this.domain = domain;
			this.options = options;
			this.uris = uris;
		}

		@Override
		public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
			final RecordingCommand command = new RecordingCommand(domain, "Marker change", "Marker change") {
				@Override
				protected void doExecute() {

					uris.stream().forEach(uri -> {
						Resource resource = domain.getResourceSet().getResource(uri, true);
						try {
							resource.save(options);
						} catch (IOException e) {
							// TODO log
							e.printStackTrace();
						}
					});
				}
			};
			domain.getCommandStack().execute(command);
			return Status.OK_STATUS;
		}

	}
}

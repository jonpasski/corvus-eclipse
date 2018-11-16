package us.coastalhacking.corvus.emf.provider;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain.Factory;
import org.eclipse.emf.workspace.WorkspaceEditingDomainFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

import us.coastalhacking.corvus.emf.EmfApi;
import us.coastalhacking.corvus.emf.ResourceInitializer;

@Component(service = Factory.class, configurationPid = EmfApi.CorvusTransactionalFactory.Component.CONFIG_PID, configurationPolicy = ConfigurationPolicy.REQUIRE, immediate=true)
public class CorvusTransactionalFactoryProvider extends WorkspaceEditingDomainFactory {

	List<ResourceInitializer> initializers = new CopyOnWriteArrayList<>();

	// BUG: doesn't add to existing TEDs
	@Reference(name = EmfApi.CorvusTransactionalFactory.Reference.INITIALIZERS, policy=ReferencePolicy.DYNAMIC, policyOption=ReferencePolicyOption.GREEDY, cardinality=ReferenceCardinality.MULTIPLE)
	void setInitializer(ResourceInitializer initializer) {
		initializers.add(initializer);
	}
	// BUG: doesn't remove from existing TEDs
	void unsetInitializer(ResourceInitializer initializer) {
		initializers.remove(initializer);
	}

	Map<String, Object> props;
	URI projectUri; 

	@Activate
	void activate(Map<String, Object> props) {
		this.props = props;
		String project = (String) props.get(EmfApi.ResourceInitializer.Properties.PROJECT);
		projectUri = URI.createPlatformResourceURI(project, true);
	}

	@Override
	public synchronized TransactionalEditingDomain createEditingDomain() {
		TransactionalEditingDomain domain = super.createEditingDomain();
		for (ResourceInitializer ri : initializers) {
			final URI logical = URI.createURI(ri.getLogical());
			final URI physical = projectUri.appendSegment(ri.getPhysical()); 
			Command command = new InitializingCommand(domain, logical, physical, ri.getRoot());
			domain.getCommandStack().execute(command);
		}

		return domain;
	}
}

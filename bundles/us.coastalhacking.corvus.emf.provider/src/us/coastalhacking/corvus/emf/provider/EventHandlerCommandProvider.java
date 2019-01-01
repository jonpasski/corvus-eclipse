package us.coastalhacking.corvus.emf.provider;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.osgi.framework.Filter;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

import us.coastalhacking.corvus.emf.CommandBiFunction;
import us.coastalhacking.corvus.emf.EmfApi;
import us.coastalhacking.corvus.emf.EventApi;
import us.coastalhacking.corvus.emf.SupportedTypesFunction;

@Component(service = EventHandler.class, property = { EventHandlerCommandProvider.CORVUS_TOPICS_PROPERTY })
public class EventHandlerCommandProvider implements EventHandler {

	protected static final String CORVUS_TOPICS_PROPERTY = EventConstants.EVENT_TOPIC + "=" + EventApi.PREFIX + "/*";

	final Map<Filter, Optional<CommandBiFunction>> filterCommands = new ConcurrentHashMap<>();

	ComponentContext context;

	@Reference
	SupportedTypesFunction supportedTypesFilter;

	@Reference(cardinality=ReferenceCardinality.MULTIPLE, policy=ReferencePolicy.DYNAMIC, policyOption=ReferencePolicyOption.GREEDY)
	void setCommandFunction(ServiceReference<CommandBiFunction> sr) {
		supportedTypesFilter.apply(sr).ifPresent(
				filter -> filterCommands.put(filter, Optional.ofNullable(context.getBundleContext().getService(sr))));
	}

	void unsetCommandFunction(ServiceReference<CommandBiFunction> sr) {
		supportedTypesFilter.apply(sr).ifPresent(filterCommands::remove);
	}

	@Reference(name = EmfApi.IEditingDomainProvider.Reference.NAME)
	IEditingDomainProvider domainProvider;

	@Activate
	void activate(ComponentContext ctx) {
		this.context = ctx;
	}

	@Override
	public void handleEvent(Event event) {
		final EditingDomain domain = domainProvider.getEditingDomain();
		filterCommands.entrySet().stream()
			.filter(es -> event.matches(es.getKey()))
			.forEach(es -> es.getValue().ifPresent(
					bf -> bf.apply(event, domain).ifPresent(
							cmd -> domain.getCommandStack().execute(cmd))));
	}
}

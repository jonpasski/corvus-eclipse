package us.coastalhacking.corvus.emf.provider;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Optional;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.Filter;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

import us.coastalhacking.corvus.emf.CommandBiFunction;
import us.coastalhacking.corvus.emf.EventApi;
import us.coastalhacking.corvus.emf.SupportedTypesFunction;
import us.coastalhacking.corvus.test.util.AbstractCMTest;

class EventHandlerCommandProviderTest extends AbstractCMTest {

	public EventHandlerCommandProviderTest() throws Exception {
		super();
	}

	SupportedTypesFunction osgiSupportedTypes;
	IEditingDomainProvider mockDomainProvider;
	EditingDomain mockDomain;
	CommandStack mockStack;
	EventHandlerCommandProvider provider;
	Map<String, Object> props;
	ComponentContext componentContext;
	BundleContext mockBundleContext;
	SupportedTypesFunction mockSupportedTypes;
	CommandBiFunction mockCommandBiFunction;
	Optional<CommandBiFunction> mockOptionalCommandBiFunction;
	ServiceReference<CommandBiFunction> mockSRBiFunction;
	Filter mockFilter;
	Optional<Filter> mockOptionalFilter;
	Event mockEvent;
	Command mockCommand;
	Optional<Command> mockOptionalCommand;

	@SuppressWarnings("unchecked")
	@BeforeEach
	void subBeforeEach() throws Exception {
		osgiSupportedTypes = serviceTrackerHelper(SupportedTypesFunction.class);
		mockDomainProvider = Mockito.mock(IEditingDomainProvider.class);
		mockDomain = Mockito.mock(EditingDomain.class);
		Mockito.when(mockDomainProvider.getEditingDomain()).thenReturn(mockDomain);
		mockStack = Mockito.mock(CommandStack.class);
		Mockito.when(mockDomain.getCommandStack()).thenReturn(mockStack);
		registerService(IEditingDomainProvider.class, mockDomainProvider, new Hashtable<>());
		props = new HashMap<>();
		provider = new EventHandlerCommandProvider();
		mockSupportedTypes = Mockito.mock(SupportedTypesFunction.class);
		provider.supportedTypesFilter = mockSupportedTypes;
		componentContext = Mockito.mock(ComponentContext.class);
		mockBundleContext = Mockito.mock(BundleContext.class);
		Mockito.when(componentContext.getBundleContext()).thenReturn(mockBundleContext);

		mockCommandBiFunction = Mockito.mock(CommandBiFunction.class);
		mockOptionalCommandBiFunction = Optional.of(mockCommandBiFunction);
		mockSRBiFunction = Mockito.mock(ServiceReference.class);
		mockFilter = Mockito.mock(Filter.class);
		mockOptionalFilter = Optional.of(mockFilter);
		Mockito.when(mockSupportedTypes.apply(mockSRBiFunction)).thenReturn(mockOptionalFilter);

		mockCommand = Mockito.mock(Command.class);
		mockOptionalCommand = Optional.of(mockCommand);

	}

	@Test
	void shouldGetService() throws Exception {
		// Prep
		props.put(EventConstants.EVENT_TOPIC, EventApi.PREFIX + "/*");
		props.put(Constants.OBJECTCLASS, new String[] { EventHandler.class.getName() });

		// Execute and verify
		serviceTrackerHelper(props);
	}

	@Test
	void shouldSetCommandFunction() {
		// Prep
		provider.context = componentContext;

		// Execute
		provider.setCommandFunction(mockSRBiFunction);

		// Verify
		Assertions.assertTrue(provider.filterCommands.containsKey(mockFilter));
		Mockito.verify(mockBundleContext, Mockito.times(1)).getService(Mockito.eq(mockSRBiFunction));
	}

	@Test
	void shouldUnsetCommandFunction() {
		// Prep
		provider.context = componentContext;
		provider.filterCommands.put(mockFilter, mockOptionalCommandBiFunction);

		// Execute
		provider.unsetCommandFunction(mockSRBiFunction);

		// Verify
		Assertions.assertFalse(provider.filterCommands.containsKey(mockFilter));
	}

	@Test
	void shouldActivate() {
		// Prep
		Assertions.assertNull(provider.context);

		// Execute
		provider.activate(componentContext);

		// Verify
		Assertions.assertEquals(componentContext, provider.context);
	}

	@Test
	void shouldHandleEvent() {
		// Prep
		provider.domainProvider = mockDomainProvider;
		mockEvent = new Event("foo/bar/BIZ", props);
		Mockito.when(mockFilter.matchCase(Mockito.any())).thenReturn(true);
		Mockito.when(mockCommandBiFunction.apply(mockEvent, mockDomain)).thenReturn(mockOptionalCommand);
		provider.filterCommands.put(mockFilter, mockOptionalCommandBiFunction);

		// Execute
		provider.handleEvent(mockEvent);

		// Verify
		Mockito.verify(mockFilter, Mockito.times(1)).matchCase(Mockito.any());
		Mockito.verify(mockStack, Mockito.times(1)).execute(Mockito.eq(mockCommand));
	}

}

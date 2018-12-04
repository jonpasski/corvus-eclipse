package us.coastalhacking.corvus.eclipse.provider.entrypoint;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionalEditingDomainImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import us.coastalhacking.corvus.eclipse.EclipseApi;
import us.coastalhacking.corvus.emf.EmfApi;
import us.coastalhacking.corvus.semiotics.IMarker;
import us.coastalhacking.corvus.semiotics.IResource;
import us.coastalhacking.corvus.semiotics.MarkerEntryPoint;
import us.coastalhacking.corvus.semiotics.Root;
import us.coastalhacking.corvus.semiotics.SemioticsFactory;
import us.coastalhacking.corvus.semiotics.SemioticsPackage;
import us.coastalhacking.corvus.test.util.AbstractProjectTest;

class MarkerToEntryPointProviderTest extends AbstractProjectTest {

	public MarkerToEntryPointProviderTest() throws Exception {
		super();
	}

	TransactionalEditingDomain domain;
	Resource epResource;
	Resource markerResource;
	URI epLogical;
	URI epPhysical;
	URI markerLogical;
	URI markerPhysical;
	MarkerToEntryPointProvider provider;
	Root epRoot;
	Root markerRoot;
	MarkerEntryPoint ep;
	IMarker marker;
	IResource resource;

	@BeforeEach
	void subBeforeEach() throws Exception {
		domain = new TransactionalEditingDomainImpl(new ComposedAdapterFactory());
		epResource = new ResourceImpl();
		markerResource = new ResourceImpl();
		epRoot = SemioticsFactory.eINSTANCE.createRoot();
		epResource.getContents().add(epRoot);
		ep = SemioticsFactory.eINSTANCE.createMarkerEntryPoint();

		markerRoot = SemioticsFactory.eINSTANCE.createRoot();
		marker = SemioticsFactory.eINSTANCE.createIMarker();
		marker.setId(ThreadLocalRandom.current().nextLong());
		resource = SemioticsFactory.eINSTANCE.createIResource();
		resource.setFullPath(project.getFile(EcoreUtil.generateUUID()).getFullPath().toPortableString());
		resource.getMarkers().add(marker);
		markerRoot.getSemiotics().add(resource);
		markerResource.getContents().add(markerRoot);
		epLogical = URI.createURI("test:ep.logical");
		markerLogical = URI.createURI("test:marker.logical");
		provider = new MarkerToEntryPointProvider();
		provider.epLogicalUri = epLogical;
		epPhysical = URI.createPlatformResourceURI(
				project.getFile(EcoreUtil.generateUUID()).getFullPath().toPortableString(), true);
		epResource.setURI(epLogical);
		markerPhysical = URI.createPlatformResourceURI(
				project.getFile(EcoreUtil.generateUUID()).getFullPath().toPortableString(), true);
		markerResource.setURI(markerLogical);

		RecordingCommand init = new RecordingCommand(domain) {
			@Override
			protected void doExecute() {
				// Simulate a resource initializer
				domain.getResourceSet().getURIConverter().getURIMap().put(epLogical, epPhysical);
				domain.getResourceSet().getURIConverter().getURIMap().put(markerLogical, markerPhysical);
				domain.getResourceSet().getResources().add(epResource);
				domain.getResourceSet().getResources().add(markerResource);
			}
		};
		domain.getCommandStack().execute(init);
	}

	@Test
	void shouldConfigureForOsgi() throws Exception {
		Map<String, Object> filterProps = new HashMap<>();
		filterProps.put(EmfApi.ResourceSetListener.Properties.ID, EclipseApi.ResourceSetListener.Properties.MarkerToEntryPoint.ID);
		MarkerToEntryPointProvider provider = (MarkerToEntryPointProvider) serviceTrackerHelper(filterProps);
		assertNotNull(provider);
	}

	@Test
	void shouldAddMarkerEntryPoint() throws Exception {
		// prep
		IMarker marker = SemioticsFactory.eINSTANCE.createIMarker();
		Notification notification = new NotificationImpl(Notification.ADD, null, marker);

		// execute
		Command command = provider.trigger(domain, notification);
		assertNotNull(command);
		domain.getCommandStack().execute(command);

		// verify
		MarkerEntryPoint ep = (MarkerEntryPoint) epRoot.getSemiotics().get(0);
		assertNotNull(ep);
		assertEquals(marker, ep.getSignifiers().get(0));
		assertEquals(ep, marker.getSignifieds().get(0));
	}

	@Test
	void shouldAddManyMarkerEntryPoint() throws Exception {
		// prep
		IMarker marker = SemioticsFactory.eINSTANCE.createIMarker();
		Collection<IMarker> markers = Collections.singleton(marker);
		Notification notification = new NotificationImpl(Notification.ADD_MANY, null, markers);

		// execute
		Command command = provider.trigger(domain, notification);
		assertNotNull(command);
		domain.getCommandStack().execute(command);

		// verify
		MarkerEntryPoint ep = (MarkerEntryPoint) epRoot.getSemiotics().get(0);
		assertNotNull(ep);
		assertEquals(marker, ep.getSignifiers().get(0));
		assertEquals(ep, marker.getSignifieds().get(0));

	}

	CommandWrapper wrapCommand(Command command) {
		return new CommandWrapper(command) {
			@Override
			public void execute() {
				super.execute();
			}
		};
	}

	CompoundCommand wrapCompound(CompoundCommand compound) {
		CompoundCommand wrappedCompound = new CompoundCommand() {
			@Override
			public void append(Command command) {
				super.append(wrapCommand(command));
			}
		};
		for (Command c : compound.getCommandList()) {
			wrappedCompound.append(c);
		}
		return wrappedCompound;
	}

	@Test
	void shouldRemoveMarkerEntryPoint() throws Exception {
		// prep
		CompoundCommand compound = new CompoundCommand();
		compound.append(AddCommand.create(domain, epRoot, SemioticsPackage.Literals.ROOT__SEMIOTICS, ep));
		compound.append(AddCommand.create(domain, ep, SemioticsPackage.Literals.SIGNIFIED__SIGNIFIERS, marker));
		compound.append(AddCommand.create(domain, marker, SemioticsPackage.Literals.SIGNIFIER__SIGNIFIEDS, ep));
		domain.getCommandStack().execute(compound);
		Notification removeNotification = new NotificationImpl(Notification.REMOVE, marker, null);
		assertFalse(epRoot.getSemiotics().isEmpty());
		assertFalse(ep.getSignifiers().isEmpty());
		assertFalse(marker.getSignifieds().isEmpty());

		// execute
		Command removeCommand = provider.trigger(domain, removeNotification);
		assertNotNull(removeCommand);
		domain.getCommandStack().execute(wrapCompound((CompoundCommand) removeCommand));

		// verify
		assertTrue(epRoot.getSemiotics().isEmpty());
		assertTrue(ep.getSignifiers().isEmpty());
		assertTrue(marker.getSignifieds().isEmpty());
	}

	@Test
	void shouldRemoveManyMarkerEntryPoint() throws Exception {
		// prep
		CompoundCommand compound = new CompoundCommand();
		compound.append(AddCommand.create(domain, epRoot, SemioticsPackage.Literals.ROOT__SEMIOTICS, ep));
		compound.append(AddCommand.create(domain, ep, SemioticsPackage.Literals.SIGNIFIED__SIGNIFIERS, marker));
		compound.append(AddCommand.create(domain, marker, SemioticsPackage.Literals.SIGNIFIER__SIGNIFIEDS, ep));
		domain.getCommandStack().execute(compound);
		assertFalse(epRoot.getSemiotics().isEmpty());
		assertFalse(ep.getSignifiers().isEmpty());
		assertFalse(marker.getSignifieds().isEmpty());
		Notification removeNotification = new NotificationImpl(Notification.REMOVE_MANY, Collections.singleton(marker),
				null);

		// execute
		Command removeCommand = provider.trigger(domain, removeNotification);
		assertNotNull(removeCommand);
		domain.getCommandStack().execute(wrapCompound((CompoundCommand) removeCommand));

		// verify
		assertTrue(epRoot.getSemiotics().isEmpty());
		assertTrue(ep.getSignifiers().isEmpty());
		assertTrue(marker.getSignifieds().isEmpty());
	}
}

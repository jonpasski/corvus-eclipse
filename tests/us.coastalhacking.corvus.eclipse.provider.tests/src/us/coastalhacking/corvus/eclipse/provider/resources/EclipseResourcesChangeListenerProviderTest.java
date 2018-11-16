package us.coastalhacking.corvus.eclipse.provider.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain.Factory;
import org.eclipse.emf.transaction.TransactionalEditingDomain.Registry;
import org.junit.jupiter.api.Test;

import us.coastalhacking.corvus.eclipse.EclipseApi;
import us.coastalhacking.corvus.emf.EmfApi;
import us.coastalhacking.corvus.semiotics.IWorkspaceRoot;
import us.coastalhacking.corvus.semiotics.SemioticsFactory;
import us.coastalhacking.corvus.semiotics.SemioticsPackage;
import us.coastalhacking.corvus.semiotics.util.SemioticsSwitch;
import us.coastalhacking.corvus.test.util.AbstractProjectTest;

class EclipseResourcesChangeListenerProviderTest extends AbstractProjectTest {

	public EclipseResourcesChangeListenerProviderTest() throws Exception {
		super();
	}
	
	SemioticsFactory factory = SemioticsFactory.eINSTANCE;

	static final String CORNIX_FULL_PATH = "/corvidae/corvus/corvus_cornix";
	static final long GW20170817 = ZonedDateTime
			.parse("Aug 17 2017 12:41:04.4 UTC", DateTimeFormatter.ofPattern("MMM d uuuu HH:mm:ss.S z")).toInstant()
			.toEpochMilli();
	static final long MARKER_ID = 42L;
	static final String MARKER_TYPE = EclipseApi.Marker.BASE_MARKER;
	
	@Test
	void shouldConfigureOsgi() throws Exception {
		// Properties
		final Map<String, Object> props = new HashMap<>();
		final String fullPath = project.getFullPath().toPortableString();
		props.put(EmfApi.ResourceInitializer.Properties.PROJECT, fullPath);
		final String markerType = EclipseApi.Marker.BASE_MARKER;
		props.put(EclipseApi.IResourceChangeListener.Properties.MARKER_TYPE, markerType);
		String transactionId = "shouldConfigureOsgi";
		props.put(EmfApi.TransactionalEditingDomain.Properties.ID, transactionId);

		// Configure factory
		Factory factory = configurationHelper(Factory.class,
				EmfApi.CorvusTransactionalFactory.Component.CONFIG_PID, props, timeout);
		assertNotNull(factory);
		
		// Configure registry
		Registry registry = configurationHelper(Registry.class,
				EmfApi.CorvusTransactionalRegistry.Component.CONFIG_PID, props, timeout);
		assertNotNull(registry);

		// Configure change listener
		EclipseResourcesChangeListenerProvider provider = (EclipseResourcesChangeListenerProvider)configurationHelper(IResourceChangeListener.class, EclipseApi.IResourceChangeListener.Component.CONFIG_PID, props, timeout);
		assertNotNull(provider);	
	}


	abstract static class RunSwitch {

		TransactionalEditingDomain domain;
		URI uri;

		public RunSwitch(TransactionalEditingDomain domain, URI uri) {
			this.domain = domain;
			this.uri = uri;
		}

		abstract void caseIMarker(us.coastalhacking.corvus.semiotics.IMarker object);

		void run() throws Exception {
			SemioticsSwitch<Boolean> sw = new SemioticsSwitch<Boolean>() {
				@Override
				public Boolean caseIMarker(us.coastalhacking.corvus.semiotics.IMarker object) {
					RunSwitch.this.caseIMarker(object);
					return false;
				}

				@Override
				public Boolean defaultCase(EObject object) {
					return true;
				}
			};

			domain.runExclusive(() -> {
				Resource resource = domain.getResourceSet().getResource(uri, false);
				TreeIterator<EObject> iter = resource.getAllContents();
				while (iter.hasNext()) {
					if (!sw.doSwitch(iter.next())) {
						iter.prune();
					}
				}
			});

		}
	}
	
	@Test
	void shouldAddRemoveChangeMarkersViaOsgiProvider() throws Exception {
		// Properties
		final Map<String, Object> props = new HashMap<>();
		final String fullPath = project.getFullPath().toPortableString();
		props.put(EmfApi.ResourceInitializer.Properties.PROJECT, fullPath);
		props.put(EclipseApi.IResourceChangeListener.Properties.MARKER_TYPE, MARKER_TYPE);
		String transactionId = "shouldAddRemoveChangeMarkersViaOsgiProvider";
		props.put(EmfApi.TransactionalEditingDomain.Properties.ID, transactionId);

		// Configure initializer
//		configurationHelper(ResourceInitializer.class, EclipseApi.ResourceInitializer.Component.CONFIG_PID, props, timeout);

		// Configure factory
		Factory factory = configurationHelper(Factory.class,
				EmfApi.CorvusTransactionalFactory.Component.CONFIG_PID, props, timeout);
		assertNotNull(factory);

		// Configure registry
		Registry registry = configurationHelper(Registry.class,
				EmfApi.CorvusTransactionalRegistry.Component.CONFIG_PID, props, timeout);
		assertNotNull(registry);	
		TransactionalEditingDomain domain = registry.getEditingDomain(transactionId);
		
		// Configure change listener
		EclipseResourcesChangeListenerProvider provider = (EclipseResourcesChangeListenerProvider)configurationHelper(IResourceChangeListener.class, EclipseApi.IResourceChangeListener.Component.CONFIG_PID, props, timeout);
		assertNotNull(provider);

		long changeId = MARKER_ID;
		long addId = changeId + 1;
		long removeId = addId + 1;

		final IMarkerDelta[] deltas = mockDeltas(changeId, addId, removeId);
		IResourceChangeEvent mockEvent = mock(IResourceChangeEvent.class);
		when(mockEvent.getType()).thenReturn(IResourceChangeEvent.POST_CHANGE);
		when(mockEvent.findMarkerDeltas(MARKER_TYPE, true)).thenReturn(deltas);

		URI eclipseUri = URI.createURI(EmfApi.ResourceInitializer.EclipseResources.Properties.LOGICAL);

		// Test & verify
		RunSwitch beforeSwitch = new RunSwitch(domain, eclipseUri) {
			@Override
			void caseIMarker(us.coastalhacking.corvus.semiotics.IMarker object) {
				assertTrue(object.getId() == removeId || object.getId() == changeId);
			}
		};
		beforeSwitch.run();

		provider.resourceChanged(mockEvent);

		RunSwitch afterSwitch = new RunSwitch(domain, eclipseUri) {
			@Override
			void caseIMarker(us.coastalhacking.corvus.semiotics.IMarker object) {
				assertTrue(object.getId() == addId || object.getId() == changeId);
			}
		};
		afterSwitch.run();
		
	}

	us.coastalhacking.corvus.semiotics.IResource createIResource(Resource resource, String fullPath, boolean contain) {
		IWorkspaceRoot root = factory.createIWorkspaceRoot();
		resource.getContents().add(root);
		us.coastalhacking.corvus.semiotics.IResource iResource = factory.createIResource();
		iResource.setFullPath(fullPath);
		if (contain) {
			root.getMembers().add(iResource);
		}
		return iResource;
	}

	us.coastalhacking.corvus.semiotics.IResource createIResource(Resource resource, String fullPath) {
		return createIResource(resource, fullPath, true);
	}

	us.coastalhacking.corvus.semiotics.IMarker createMarker(long id, us.coastalhacking.corvus.semiotics.IResource iResource) {
		us.coastalhacking.corvus.semiotics.IMarker marker = factory.createIMarker();
		marker.setId(id);
		if (iResource != null) {
			iResource.getMarkers().add(marker);
		}
		return marker;
	}

	us.coastalhacking.corvus.semiotics.IMarker createMarker(long id) {
		return createMarker(id, null);
	}

	@Test
	void shouldGetExistingMarker() {
		String expectedFullPath = CORNIX_FULL_PATH;
		long expectedId = 42L;

		// Prep existing
		Resource existingResource = new ResourceImpl();
		us.coastalhacking.corvus.semiotics.IResource existingIResource = createIResource(existingResource, expectedFullPath);
		createMarker(expectedId, existingIResource);

		// Prep test
		Resource modelResource = new ResourceImpl();
		us.coastalhacking.corvus.semiotics.IResource modelIResource = createIResource(modelResource, expectedFullPath);
		// Detached
		us.coastalhacking.corvus.semiotics.IMarker modelMarker = createMarker(expectedId);

		// Test & verify
		EclipseResourcesChangeListenerProvider provider = new EclipseResourcesChangeListenerProvider();
		Optional<us.coastalhacking.corvus.semiotics.IMarker> maybeMarker = provider.getExistingMarker(existingResource, modelIResource, modelMarker);
		assertTrue(maybeMarker.isPresent());
		assertEquals(maybeMarker.get().getId(), expectedId);
	}

	@Test
	void shouldNotGetExistingMarker() {
		String expectedFullPath = CORNIX_FULL_PATH;
		long expectedId = 42L;
		long modelId = 24L;

		// Prep existing
		Resource existingResource = new ResourceImpl();
		us.coastalhacking.corvus.semiotics.IResource existingIResource = createIResource(existingResource, expectedFullPath);
		createMarker(expectedId, existingIResource);

		// Prep test
		Resource modelResource = new ResourceImpl();
		us.coastalhacking.corvus.semiotics.IResource modelIResource = createIResource(modelResource, expectedFullPath);
		// Detached
		us.coastalhacking.corvus.semiotics.IMarker modelMarker = createMarker(modelId);

		// Test & verify
		EclipseResourcesChangeListenerProvider provider = new EclipseResourcesChangeListenerProvider();
		Optional<us.coastalhacking.corvus.semiotics.IMarker> maybeMarker = provider.getExistingMarker(existingResource, modelIResource, modelMarker);
		assertFalse(maybeMarker.isPresent());
	}

	String expectedFragment(String path) throws Exception {
		String encodedPath = URLEncoder.encode(path, StandardCharsets.UTF_8.name());
		return MessageFormat.format("//@members[fullPath=''{0}'']", encodedPath);
	}

	@Test
	void shouldGenerateFragmentUncontained() throws Exception {
		Resource resource = new ResourceImpl();
		String path = CORNIX_FULL_PATH;
		us.coastalhacking.corvus.semiotics.IResource iResource = createIResource(resource, path, false);
		us.coastalhacking.corvus.semiotics.IWorkspaceRoot root = (IWorkspaceRoot) resource.getContents().get(0);
		String expectedFragment = expectedFragment(path);

		// Test & verify
		EclipseResourcesChangeListenerProvider provider = new EclipseResourcesChangeListenerProvider();
		String actualFragment = provider.getFragment(root, iResource);
		assertEquals(expectedFragment, actualFragment);
	}

	@Test
	void shouldGenerateFragmentContained() throws Exception {
		Resource resource = new ResourceImpl();
		String path = CORNIX_FULL_PATH;
		us.coastalhacking.corvus.semiotics.IResource iResource = createIResource(resource, path);
		String expectedFragment = expectedFragment(path);

		// Test & verify
		EclipseResourcesChangeListenerProvider base = new EclipseResourcesChangeListenerProvider();
		String actualFragment = base.getFragment(iResource.getWorkspaceRoot(), iResource);
		assertEquals(expectedFragment, actualFragment);
	}

	@Test
	void shouldGetExistingIResource() {
		Resource existingResource = new ResourceImpl();
		String path = CORNIX_FULL_PATH;
		us.coastalhacking.corvus.semiotics.IResource existingIResource = createIResource(existingResource, path);

		Resource modelResource = new ResourceImpl();
		us.coastalhacking.corvus.semiotics.IResource modelIResource = createIResource(modelResource, path);

		// Test & verify
		EclipseResourcesChangeListenerProvider base = new EclipseResourcesChangeListenerProvider();
		String fragment = base.getFragment(modelIResource.getWorkspaceRoot(), modelIResource);
		us.coastalhacking.corvus.semiotics.IResource actualIResource = base.getOrCreateExistingResource(fragment, existingIResource.getWorkspaceRoot(),
				modelIResource);
		assertTrue(EcoreUtil.equals(existingIResource, actualIResource));
	}

	@Test
	void shouldCreateNewIResource() {
		Resource existingResource = new ResourceImpl();
		String path = CORNIX_FULL_PATH;
		us.coastalhacking.corvus.semiotics.IResource missingIResource = createIResource(existingResource, path, false);
		IWorkspaceRoot existingRoot = (IWorkspaceRoot) existingResource.getContents().get(0);

		Resource modelResource = new ResourceImpl();
		us.coastalhacking.corvus.semiotics.IResource modelIResource = createIResource(modelResource, path);

		// Test & verify
		EclipseResourcesChangeListenerProvider provider = new EclipseResourcesChangeListenerProvider();
		String fragment = provider.getFragment(modelIResource.getWorkspaceRoot(), modelIResource);
		us.coastalhacking.corvus.semiotics.IResource actualIResource = provider.getOrCreateExistingResource(fragment, existingRoot, modelIResource);

		// now add missing iresource to root, which shouldn't affect the above values
		// this is to ensure the EqualityHelper sees both features set to similar values
		existingRoot.getMembers().add(missingIResource);
		assertTrue(EcoreUtil.equals(missingIResource, actualIResource));
	}

	IMarkerDelta mockDelta(int kind, long markerId, String deltaMarkerType, String markerType,
			Map<String, Object> deltaAttributes, Map<String, Object> markerAttributes, long creationTime)
			throws Exception {
		final IMarkerDelta mockDelta = mock(IMarkerDelta.class);
		when(mockDelta.getId()).thenReturn(markerId);
		when(mockDelta.getAttributes()).thenReturn(deltaAttributes);
		when(mockDelta.getType()).thenReturn(deltaMarkerType);

		org.eclipse.core.resources.IMarker iMarker = mock(org.eclipse.core.resources.IMarker.class);
		when(iMarker.getType()).thenReturn(markerType);
		when(iMarker.getAttributes()).thenReturn(markerAttributes);
		when(mockDelta.getMarker()).thenReturn(iMarker);
		if (creationTime == -1L) {
			when(iMarker.getCreationTime()).thenThrow(CoreException.class);
		} else {
			when(iMarker.getCreationTime()).thenReturn(creationTime);
		}
		if (kind != -1) {
			when(mockDelta.getKind()).thenReturn(kind);
		}
		return mockDelta;
	}

	IMarkerDelta mockDelta(long markerId, String deltaMarkerType, String markerType,
			Map<String, Object> deltaAttributes, Map<String, Object> markerAttributes, long creationTime)
			throws Exception {
		return mockDelta(-1, markerId, deltaMarkerType, markerType, deltaAttributes, markerAttributes, creationTime);
	}

	IMarkerDelta mockDelta(int kind, long markerId, String markerType, Map<String, Object> attributes,
			long creationTime) throws Exception {
		return mockDelta(kind, markerId, markerType, markerType, attributes, attributes, creationTime);
	}

	IMarkerDelta mockDelta(long markerId, String markerType, Map<String, Object> attributes, long creationTime)
			throws Exception {
		return mockDelta(-1, markerId, markerType, attributes, creationTime);
	}

	@Test
	void shouldAddMarker() throws Exception {
		long expectedId = MARKER_ID;
		long expectedCreationTime = GW20170817;
		// Prep & mock
		Resource existingResource = new ResourceImpl();
		us.coastalhacking.corvus.semiotics.IResource existingIResource = createIResource(existingResource, CORNIX_FULL_PATH);

		Resource modelResource = new ResourceImpl();
		us.coastalhacking.corvus.semiotics.IResource modelIResource = createIResource(modelResource, CORNIX_FULL_PATH);
		us.coastalhacking.corvus.semiotics.IMarker expectedMarker = createMarker(expectedId, modelIResource);
		String key = "key";
		String value = "value";
		expectedMarker.getAttributes().put(key, value);

		String markerType = MARKER_TYPE;
		final Map<String, Object> attributes = new HashMap<>();
		attributes.putAll(expectedMarker.getAttributes().map());

		final IMarkerDelta mockDelta = mockDelta(expectedId, markerType, attributes, expectedCreationTime);

		// Test & verify
		assertTrue(existingIResource.getMarkers().isEmpty());
		EclipseResourcesChangeListenerProvider base = new EclipseResourcesChangeListenerProvider();
		base.added(mockDelta, existingIResource, expectedMarker);
		us.coastalhacking.corvus.semiotics.IMarker actualMarker = existingIResource.getMarkers().get(0);
		assertFalse(existingIResource.getMarkers().isEmpty());
		assertTrue(EcoreUtil.equals(expectedMarker, actualMarker));
		assertTrue(expectedMarker.getAttributes().containsKey(key));
		assertEquals(value, expectedMarker.getAttributes().get(key));
	}

	@Test
	void shouldRemoveMarker() {
		// Prep
		Resource existingResource = new ResourceImpl();
		us.coastalhacking.corvus.semiotics.IResource existingIResource = createIResource(existingResource, CORNIX_FULL_PATH);
		us.coastalhacking.corvus.semiotics.IMarker existingMarker = createMarker(MARKER_ID, existingIResource);

		// Test & verify
		EclipseResourcesChangeListenerProvider base = new EclipseResourcesChangeListenerProvider();
		Optional<us.coastalhacking.corvus.semiotics.IMarker> maybeMarker = Optional.of(existingMarker);
		assertFalse(existingIResource.getMarkers().isEmpty());
		base.removed(maybeMarker);
		assertTrue(existingIResource.getMarkers().isEmpty());
	}

	@Test
	void shouldChangeTypeAndAttribute() throws Exception {
		String oldValue = "oldValue";
		String newValue = "newValue";
		String oldType = MARKER_TYPE;
		String newType = "new.type";
		String toRemoveKey = "removeMe";
		String toChangeKey = "changeMe";
		String toAddKey = "addMe";
		String toStayKey = "keepMe";

		Resource existingResource = new ResourceImpl();
		us.coastalhacking.corvus.semiotics.IResource existingIResource = createIResource(existingResource, CORNIX_FULL_PATH);
		us.coastalhacking.corvus.semiotics.IMarker existingMarker = createMarker(MARKER_ID, existingIResource);
		existingMarker.getAttributes().put(toRemoveKey, oldValue);
		existingMarker.getAttributes().put(toChangeKey, oldValue);
		existingMarker.getAttributes().put(toStayKey, oldValue);
		existingMarker.setType(oldType);

		// Needs: delta.getMarker().getType(); delta.getType();
		// delta.getMarker().getAttributes() - new; delta.getAttributes() - old;
		final Map<String, Object> oldAttributes = new HashMap<>();
		oldAttributes.putAll(existingMarker.getAttributes().map());

		final Map<String, Object> newAttributes = new HashMap<>();
		newAttributes.put(toChangeKey, newValue);
		newAttributes.put(toAddKey, newValue);
		newAttributes.put(toStayKey, oldValue);

		final IMarkerDelta mockDelta = mockDelta(MARKER_ID, oldType, newType, oldAttributes, newAttributes, GW20170817);

		// Test & verify
		assertTrue(existingMarker.getAttributes().containsKey(toRemoveKey));
		assertFalse(existingMarker.getAttributes().containsKey(toAddKey));
		assertEquals(oldValue, existingMarker.getAttributes().get(toChangeKey));
		assertEquals(oldValue, existingMarker.getAttributes().get(toStayKey));
		EclipseResourcesChangeListenerProvider base = new EclipseResourcesChangeListenerProvider();
		Optional<us.coastalhacking.corvus.semiotics.IMarker> maybeMarker = Optional.of(existingMarker);
		base.changed(mockDelta, maybeMarker);
		existingMarker = existingIResource.getMarkers().get(0);
		assertFalse(existingMarker.getAttributes().containsKey(toRemoveKey));
		assertTrue(existingMarker.getAttributes().containsKey(toAddKey));
		assertEquals(newValue, existingMarker.getAttributes().get(toAddKey));
		assertEquals(newValue, existingMarker.getAttributes().get(toChangeKey));
		assertEquals(oldValue, existingMarker.getAttributes().get(toStayKey));
	}

	org.eclipse.core.resources.IResource addResourceToMarkerMock(org.eclipse.core.resources.IResource resource,
			IMarkerDelta delta) {
		when(delta.getResource()).thenReturn(resource);
		return resource;
	}

	IMarkerDelta[] mockDeltas(long changeId, long addId, long removeId) throws Exception {
		org.eclipse.core.resources.IResource markerResource = mock(org.eclipse.core.resources.IResource.class);
		IPath iPath = mock(IPath.class);
		when(iPath.toPortableString()).thenReturn(CORNIX_FULL_PATH);
		when(markerResource.getFullPath()).thenReturn(iPath);
		final IMarkerDelta changeDelta = mockDelta(IResourceDelta.CHANGED, changeId, MARKER_TYPE,
				Collections.emptyMap(), GW20170817);
		addResourceToMarkerMock(markerResource, changeDelta);
		final IMarkerDelta addDelta = mockDelta(IResourceDelta.ADDED, addId, MARKER_TYPE, Collections.emptyMap(),
				GW20170817);
		addResourceToMarkerMock(markerResource, addDelta);
		final IMarkerDelta removeDelta = mockDelta(IResourceDelta.REMOVED, removeId, MARKER_TYPE,
				Collections.emptyMap(), GW20170817);
		addResourceToMarkerMock(markerResource, removeDelta);
		return new IMarkerDelta[] { changeDelta, addDelta, removeDelta };
	}

	@Test
	void shouldProcessIMarkerDeltas() throws Exception {
		long changeId = MARKER_ID;
		long addId = changeId + 1;
		long removeId = addId + 1;

		final IMarkerDelta[] deltas = mockDeltas(changeId, addId, removeId);

		Resource existingResource = new ResourceImpl();
		us.coastalhacking.corvus.semiotics.IResource existingIResource = createIResource(existingResource, CORNIX_FULL_PATH);
		String changeFragment = existingResource.getURIFragment(createMarker(changeId, existingIResource));
		String removeFragment = existingResource.getURIFragment(createMarker(removeId, existingIResource));
		// hack to detach
		us.coastalhacking.corvus.semiotics.IMarker addedMarker = createMarker(addId, existingIResource);

		String addedFragment = existingResource.getURIFragment(addedMarker);
		addedMarker.eUnset(SemioticsPackage.Literals.IMARKER__RESOURCE);

		// Test & verify
		assertNotNull(existingResource.getEObject(removeFragment));
		assertNotNull(existingResource.getEObject(changeFragment));
		assertNull(existingResource.getEObject(addedFragment));
		EclipseResourcesChangeListenerProvider base = new EclipseResourcesChangeListenerProvider();
		base.processIMarkerDeltas(deltas, factory, existingResource);
		assertNull(existingResource.getEObject(removeFragment));
		assertNotNull(existingResource.getEObject(changeFragment));
		assertNotNull(existingResource.getEObject(addedFragment));
	}

//
//	
//	@Test
//	void shouldActivateAndResourceChanged() throws Exception {
//		// Initialize TED
//		String file = "shouldActivateAndResourceChange.xmi";
//		IFile iFile = project.getFile(file);
//		
//		URI key = URI.createURI("test:shouldActivateAndResourceChanged");
//		URI value = URI.createFileURI(file);
//		final IWorkspaceRoot root = factory.createIWorkspaceRoot();
//		
//		
//		
//		
//		final TransactionalEditingDomain domain = TransactionalEditingDomain.Factory.INSTANCE.createEditingDomain();
//		final Command command = new InitializingCommand(domain, key, value, root); 
//		domain.getCommandStack().execute(command);
//
//		EclipseResourcesChangeListenerProvider base = new EclipseResourcesChangeListenerProvider();
//		base.activate(MARKER_TYPE, domain, file);
//
//		long changeId = MARKER_ID;
//		long addId = changeId + 1;
//		long removeId = addId + 1;
//
//		final IMarkerDelta[] deltas = mockDeltas(changeId, addId, removeId);
//		IResourceChangeEvent mockEvent = mock(IResourceChangeEvent.class);
//		when(mockEvent.getType()).thenReturn(IResourceChangeEvent.POST_CHANGE);
//		when(mockEvent.findMarkerDeltas(MARKER_TYPE, true)).thenReturn(deltas);
//
//		// Test & verify
//		RunSwitch beforeSwitch = new RunSwitch(domain, value) {
//			@Override
//			void caseIMarker(us.coastalhacking.corvus.semiotics.IMarker object) {
//				assertTrue(object.getId() == removeId || object.getId() == changeId);
//			}
//		};
//		beforeSwitch.run();
//
//		base.resourceChanged(mockEvent);
//
//		RunSwitch afterSwitch = new RunSwitch(domain, value) {
//			@Override
//			void caseIMarker(us.coastalhacking.corvus.semiotics.IMarker object) {
//				assertTrue(object.getId() == addId || object.getId() == changeId);
//			}
//		};
//		afterSwitch.run();
//	}
//	

}

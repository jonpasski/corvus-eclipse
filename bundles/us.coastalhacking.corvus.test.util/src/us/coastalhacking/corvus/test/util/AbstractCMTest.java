package us.coastalhacking.corvus.test.util;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.rules.TemporaryFolder;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.Filter;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.cm.ConfigurationEvent;
import org.osgi.service.cm.ConfigurationListener;
import org.osgi.util.tracker.ServiceTracker;

public abstract class AbstractCMTest {

	protected final static Logger LOGGER = Logger.getLogger(AbstractCMTest.class.getName());
	
	private BundleContext context;

	private ConfigurationAdmin configAdmin;

	protected final List<ServiceTracker<?, ?>> serviceTrackers = new CopyOnWriteArrayList<>();
	protected final List<ServiceRegistration<?>> serviceRegistrations = new CopyOnWriteArrayList<>();
	protected final List<Configuration> configurations = new CopyOnWriteArrayList<>();
	protected final Map<String, CompletableFuture<Void>> configurationFutures = new ConcurrentHashMap<>();

	protected static final long timeout = 1000; // milliseconds;

	public AbstractCMTest() throws Exception {
	}

	@Deprecated
	@Rule
	private TemporaryFolder folder = getTempFolder();

	@BeforeEach
	protected void beforeEach() throws Exception {
		assertTrue(serviceTrackers.isEmpty());
		assertTrue(serviceRegistrations.isEmpty());
		assertTrue(configurations.isEmpty());

		// Register our per-test listener
		serviceRegistrations.add(getBundleContext().registerService(org.osgi.service.cm.ConfigurationListener.class,
				new TestCaseConfigurationListener(), null));
	}

	@AfterEach
	protected void afterEach() throws Exception {
		// First delete the configurations
		configurations.forEach(c -> {
			try {
				c.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		CompletableFuture<Void> combinedFuture = CompletableFuture
				.allOf(configurationFutures.values().stream().toArray(CompletableFuture[]::new));
		// block until all the configurations are deleted
		combinedFuture.get(timeout, TimeUnit.MILLISECONDS);
		configurations.clear();

		// Then delete the registrations. Reversing these results in the configuration
		// listener being deleted prematurely
		serviceRegistrations.forEach(sr -> {
			try {
				sr.unregister();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		});
		serviceRegistrations.clear();
		serviceTrackers.forEach(st -> {
			st.close();
		});
		serviceTrackers.clear();
	}

	protected Object serviceTrackerHelper(Map<String, Object> filterProps) throws Exception {
		return serviceTrackerHelper(
				new ServiceTracker<>(getBundleContext(), getBundleContext().createFilter(toFilter(filterProps)), null), timeout);
	}

	protected <T> T serviceTrackerHelper(Class<T> clazz) throws Exception {
		return serviceTrackerHelper(new ServiceTracker<>(getBundleContext(), clazz, null), timeout);
	}

	protected <T> T serviceTrackerHelper(ServiceTracker<?, T> st, long timeout) throws Exception {
		serviceTrackers.add(st);
		st.open();
		// TODO: log
		T service = st.waitForService(timeout);
		assertNotNull(service);
		return service;
	}

	protected String toFilterWithPid(String servicePid, Map<String, Object> props) {
		return String.format("(&(%s=%s)%s)", Constants.SERVICE_PID, servicePid, props.entrySet().stream().map(
				es -> String.format("(%s=%s)", es.getKey(), weaklyEscapeForLdapSearchFilter((String) es.getValue())))
				.collect(Collectors.joining()));
	}
	
	protected String toFilter(Map<String, Object> props) {
		return String.format("(&%s)", props.entrySet().stream().map(
				es -> String.format("(%s=%s)", es.getKey(), weaklyEscapeForLdapSearchFilter((String) es.getValue())))
				.collect(Collectors.joining()));
	}

	@Deprecated
	protected String toFilter(Class<?> clazz, String servicePid, Map<String, Object> props) {
		return String.format("(&(%s=%s)%s)", Constants.SERVICE_PID, servicePid, props.entrySet().stream().map(
				es -> String.format("(%s=%s)", es.getKey(), weaklyEscapeForLdapSearchFilter((String) es.getValue())))
				.collect(Collectors.joining()));
	}

	// Don't use me in prod! I'm weak and just for testing...
	protected String weaklyEscapeForLdapSearchFilter(String unescaped) {
		final StringBuilder result = new StringBuilder(unescaped.length());
		unescaped.chars().forEach(i -> {
			char c = (char) i;
			switch (c) {
			case '(':
				result.append("\\(");
				break;
			case ')':
				result.append("\\)");
				break;
			default:
				result.append(c);
				break;
			}
		});
		return result.toString();
	}

	protected Object configurationHelper(Class<?>[] clazzes, String factoryPid, Map<String, Object> props, long timeout)
			throws Exception {
		assertNotNull(getConfigAdmin());
		// Create the configuration
		final Configuration configuration = getConfigAdmin().createFactoryConfiguration(factoryPid, "?");
		return configurationHelper(configuration, props, timeout);
	}

	protected Object configurationHelper(Configuration configuration, Map<String, Object> props, long timeout)
			throws Exception {
		configurations.add(configuration);
		configurationFutures.put(configuration.getPid(), new CompletableFuture<>());
		// Updating it with the passed-in properties
		// Strictly return the specific service, ensuring the desired properties are
		// also present
		final String filter = toFilterWithPid(configuration.getPid(), props);
		return serviceTrackerHelper(
				new ServiceTracker<Object, Object>(getBundleContext(), getBundleContext().createFilter(filter), null),
				timeout);
	}

	protected <S> S configurationHelper(Class<S> clazz, String factoryPid, Map<String, Object> props, long timeout)
			throws Exception {
		assertNotNull(getConfigAdmin());
		// Create the configuration
		final Configuration configuration = getConfigAdmin().createFactoryConfiguration(factoryPid, "?");
		// TODO: log

		configurations.add(configuration);
		configurationFutures.put(configuration.getPid(), new CompletableFuture<>());
		// Updating it with the passed-in properties
		configuration.update(new Hashtable<>(props));
		// Strictly return the specific service, ensuring the desired properties are
		// also present

		final String toFilter = toFilterWithPid(configuration.getPid(), props);
		final Filter filter = getBundleContext().createFilter(toFilter);
		final ServiceTracker<S, S> tracker = new ServiceTracker<>(getBundleContext(), filter, null);

		S service = serviceTrackerHelper(tracker, timeout);

		return service;
	}

	protected class TestCaseConfigurationListener implements ConfigurationListener {

		@Override
		public void configurationEvent(ConfigurationEvent event) {
			if (event.getType() == ConfigurationEvent.CM_DELETED && configurationFutures.containsKey(event.getPid())) {
				configurationFutures.get(event.getPid()).complete(null);
			}
		}
	}

	@Deprecated
	protected TemporaryFolder getTempFolder() throws Exception {
		if (folder == null) {
			folder = new TemporaryFolder();
			folder.create();
		}
		return folder;
	}

	@Deprecated
	protected String getTempFolderNewFile(String newFile) throws Exception {
		final Path path = Paths.get(getTempFolder().getRoot().getAbsolutePath(), newFile);
		return path.toAbsolutePath().toString();
	}

	protected BundleContext getBundleContext() {
		if (context == null) {
			context = FrameworkUtil.getBundle(getClass()).getBundleContext();
		}
		return context;
	}

	protected ConfigurationAdmin getConfigAdmin() throws Exception {
		if (configAdmin == null) {
			configAdmin = serviceTrackerHelper(new ServiceTracker<>(getBundleContext(), ConfigurationAdmin.class, null),
					timeout);
		}
		return configAdmin;
	}
	
	protected <T> void registerService(Class<T> serviceInterface, T service, Map<String, Object> props) {
		serviceRegistrations.add(getBundleContext().registerService(serviceInterface,
				service, new Hashtable<>(props)));
	}
}
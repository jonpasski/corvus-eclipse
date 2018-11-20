package us.coastalhacking.corvus.eclipse;

import java.text.MessageFormat;
import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IExecutableExtensionFactory;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.ComponentConstants;
import org.osgi.service.component.ComponentFactory;
import org.osgi.service.component.ComponentInstance;

@Deprecated
public class CorvusLaunchFactory implements IExecutableExtensionFactory, IExecutableExtension {

	private BundleContext context;
	private Object data;
	private Map<String, Object> props;
	private static final String CORVUS_TIMESTAMP = "corvus.timestamp";
	private Map<String, ServiceReference<ComponentFactory>> factoryRefs = new HashMap<>();

	public CorvusLaunchFactory() {
		context = FrameworkUtil.getBundle(CorvusLaunchFactory.class).getBundleContext();
	}

	@Override
	public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
			throws CoreException {
		this.data = data;
		this.props = toProps(config, propertyName, data);
	}

	Map<String, Object> toProps(IConfigurationElement config, String propertyName, Object data) {
		Map<String, Object> results = new HashMap<>();
		Instant instant = Instant.now();
		long epochNow = instant.toEpochMilli();
		results.put(CORVUS_TIMESTAMP, epochNow);
		return results;
	}

	@Override
	//
	public Object create() throws CoreException {

		String filter = getFilter(data.toString());
		ServiceReference<ComponentFactory> sr = getReference(context, filter, factoryRefs);

		ComponentFactory factory = context.getService(sr);
		ComponentInstance instance = factory.newInstance(new Hashtable<>(props));
		return instance.getInstance();
	}

	ServiceReference<ComponentFactory> getReference(BundleContext context, String filter, Map<String, ServiceReference<ComponentFactory>> factoryRefs) throws CoreException {
		ServiceReference<ComponentFactory> sr = factoryRefs.get(filter);
		if (sr != null) {
			return sr;
		}

		try {
			Collection<ServiceReference<ComponentFactory>> references = context
					.getServiceReferences(ComponentFactory.class, getFilter(data.toString()));
			if (references.iterator().hasNext()) {
				sr = references.iterator().next();
			}
		} catch (Exception e) {
			// gobble
		}

		if (sr == null) {
			Bundle bundle = context.getBundle();
			throw new CoreException(new Status(IStatus.ERROR, bundle.getSymbolicName(),
					MessageFormat.format("Could not get ComponentFactory service references for", data.toString())));
		}
		factoryRefs.put(filter, sr);
		return sr;
	}

	String getFilter(String data) {
		// We trust plugin.xml data to not be malicious, hence unescaped
		return String.format("(%s=%s)", ComponentConstants.COMPONENT_FACTORY, data);
	}
}

package us.coastalhacking.corvus.eclipse.provider.app;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.osgi.service.cm.Configuration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

import us.coastalhacking.corvus.eclipse.EclipseApi;
import us.coastalhacking.corvus.emf.EmfApi;
import us.coastalhacking.corvus.util.ConfigurationAdminHelper;

@Component(factory = EclipseApi.CorvusApp.Component.FACTORY)
public class CorvusAppFactoryProvider {

	protected final List<Configuration> configurations = new CopyOnWriteArrayList<>();

	@Reference
	ConfigurationAdminHelper helper;

	private String transactionId;

	@Activate
	void activate(Map<String, Object> oldProps) throws Exception {
		Hashtable<String, Object> newProps = new Hashtable<>(oldProps);
		// Create a target filter and apply to all things which should be targeted
		transactionId = (String) newProps.get(EmfApi.TransactionalEditingDomain.Properties.ID);
		String[] targets = { EclipseApi.IResourceChangeListener.Reference.REGISTRY,
				EmfApi.ResourceModifiedListener.Reference.REGISTRY,
				EmfApi.CorvusTransactionalRegistry.Reference.NAME};
		helper.target(newProps, Arrays.stream(targets).sequential(),
				EmfApi.TransactionalEditingDomain.Properties.ID, transactionId);

		// Ordered
		String[] pids = { EmfApi.CorvusTransactionalFactory.Component.CONFIG_PID,
				EmfApi.CorvusTransactionalRegistry.Component.CONFIG_PID,
				EmfApi.ResourceModifiedListener.Component.CONFIG_PID,
				EclipseApi.IResourceChangeListener.Component.CONFIG_PID,
				EclipseApi.TriggerListener.EntryPoint.Component.CONFIG_PID};
		configurePids(helper, pids, newProps, configurations);

//		helper.configure(EntryPointApi.ResourceInitializer.Component.CONFIG_PID, newProps, configurations);
//		helper.configure(CorvusAppApi.EclipseResourcesInitializer.Component.CONFIG_PID, newProps, configurations);
//		helper.configure(CorvusAppApi.CorvusTransactionalFactory.Component.CONFIG_PID, newProps, configurations);
//		helper.configure(CorvusAppApi.CorvusTransactionalRegistry.Component.CONFIG_PID, newProps, configurations);
//		helper.configure(CorvusAppApi.ResourceModifiedListener.Component.CONFIG_PID, newProps, configurations);
//		helper.configure(CorvusAppApi.EclipseResourcesChangeListener.Component.CONFIG_PID, newProps, configurations);
	}

	void configurePids(ConfigurationAdminHelper helper, String[] pids, Hashtable<String, Object> props,
			List<Configuration> configurations) {
		Arrays.stream(pids).forEach(pid -> {
			try {
				helper.configure(pid, props, configurations);
			} catch (Exception e) {
				// TODO log
				e.printStackTrace();
			}
		});
	}

	@Deactivate
	void deactivate() {
		helper.deactivateConfigurations(configurations);
	}

}

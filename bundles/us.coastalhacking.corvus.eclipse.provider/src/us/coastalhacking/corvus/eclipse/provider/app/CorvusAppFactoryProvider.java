package us.coastalhacking.corvus.eclipse.provider.app;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.osgi.service.cm.Configuration;
import org.osgi.service.component.ComponentFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.log.Logger;
import org.osgi.service.log.LoggerFactory;

import us.coastalhacking.corvus.eclipse.EclipseApi;
import us.coastalhacking.corvus.emf.EmfApi;
import us.coastalhacking.corvus.emf.TransactionIdUtil;
import us.coastalhacking.corvus.util.ConfigurationAdminHelper;

@Component(factory = EclipseApi.CorvusApp.Component.FACTORY)
public class CorvusAppFactoryProvider {

	protected final List<Configuration> configurations = new CopyOnWriteArrayList<>();

	@Reference
	LoggerFactory loggerFactory;
	Logger logger;

	@Reference
	ConfigurationAdminHelper helper;

	@Reference
	TransactionIdUtil idUtil;

	private String transactionId;

	@Activate
	void activate(Map<String, Object> props) throws Exception {
		logger = loggerFactory.getLogger(ComponentFactory.class);

		Hashtable<String, Object> newProps = new Hashtable<>(props);
		transactionId = idUtil.getId(props);
		String[] targets = { EmfApi.IEditingDomainProvider.Reference.NAME };
		helper.target(newProps, Arrays.stream(targets).sequential(), EmfApi.TransactionalEditingDomain.Properties.ID,
				transactionId);

		// Ordered
		String[] pids = { EmfApi.IEditingDomainProvider.Component.CONFIG_PID,
				// https://github.com/CoastalHacking/corvus-eclipse/issues/35
				// EmfApi.ResourceModifiedListener.Component.CONFIG_PID,
				EclipseApi.IResourceChangeListener.Component.CONFIG_PID, };
		configurePids(helper, pids, newProps, configurations);
	}

	void configurePids(ConfigurationAdminHelper helper, String[] pids, Hashtable<String, Object> props,
			List<Configuration> configurations) {
		Arrays.stream(pids).forEach(pid -> {
			try {
				helper.configure(pid, props, configurations);
			} catch (Exception e) {
				logger.warn("Could not configure PID {}", pid, e);
			}
		});
	}

	@Deactivate
	void deactivate() {
		helper.deactivateConfigurations(configurations);
	}

}

package us.coastalhacking.corvus.eclipse.ui.impl;

import org.eclipse.e4.ui.workbench.IWorkbench;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

import us.coastalhacking.corvus.eclipse.ui.api.MarkerSupport;
import us.coastalhacking.corvus.eclipse.ui.impl.MarkerSupportImpl;

@Component(configurationPid = "us.coastalhacking.corvus.eclipse.ui.api.MarkerSupport", configurationPolicy = ConfigurationPolicy.OPTIONAL, service = MarkerSupport.class, property = {
		"markerKeys=entrypoint", "markerIds=us.coastalhacking.corvus.eclipse.resources.entrypoint" })
public class MarkerSupportProvider extends MarkerSupportImpl {

	@interface Config {
		String[] markerKeys();

		String[] markerIds();
	}

	@Reference
	IWorkbench workbench;

	@Activate
	void activate(BundleContext bundleContext, Config config) {
		this.id = bundleContext.getBundle().getSymbolicName();
		this.context = workbench.getApplication().getContext();
		super.activate(config.markerKeys(), config.markerIds());
	}

	@Deactivate
	protected void deactivate() {
		super.deactivate();
		this.context = null;
		this.id = null;
	}

}

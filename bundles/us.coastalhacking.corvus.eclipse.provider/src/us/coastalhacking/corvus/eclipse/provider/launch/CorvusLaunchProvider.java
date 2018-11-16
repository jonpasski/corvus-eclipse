package us.coastalhacking.corvus.eclipse.provider.launch;

import java.util.Hashtable;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.ILaunchConfigurationDelegate2;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.LaunchConfigurationDelegate;
import org.osgi.service.component.ComponentConstants;
import org.osgi.service.component.ComponentFactory;
import org.osgi.service.component.ComponentInstance;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import us.coastalhacking.corvus.eclipse.EclipseApi;
import us.coastalhacking.corvus.emf.EmfApi;

@Component(service=ILaunchConfigurationDelegate2.class, factory=EclipseApi.CorvusLaunch.Component.FACTORY)
public class CorvusLaunchProvider extends LaunchConfigurationDelegate {

	@Reference(name=EclipseApi.CorvusLaunch.Reference.APP_FACTORY, target="(" + ComponentConstants.COMPONENT_FACTORY + "=" + EclipseApi.CorvusApp.Component.FACTORY + ")")
	ComponentFactory appFactory;

	private Map<String, Object> props;
	
	@Activate
	void activate(Map<String, Object> props) {
		this.props = props;
	}
	
	@Override
	public void launch(ILaunchConfiguration configuration, String mode, ILaunch launch, IProgressMonitor monitor)
			throws CoreException {
		// FIXME: leaking instances; consider saving instance, adding event listener
		// and then dispose when process terminates, via an event admin event
		Hashtable<String, Object> newProps = new Hashtable<>(props);
		newProps.putAll(configuration.getAttributes());

		String transId = (String)newProps.get(EmfApi.TransactionalEditingDomain.Properties.ID);
		ComponentInstance appInstance = appFactory.newInstance(newProps);
		IProcess appProcess = new CorvusAppProcessBase(launch, appInstance, transId);
		launch.addProcess(appProcess);			
	}

}

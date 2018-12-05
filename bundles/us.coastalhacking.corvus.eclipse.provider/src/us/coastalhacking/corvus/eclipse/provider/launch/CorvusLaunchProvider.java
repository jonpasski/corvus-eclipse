package us.coastalhacking.corvus.eclipse.provider.launch;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.LaunchConfigurationDelegate;
import org.osgi.service.component.ComponentConstants;
import org.osgi.service.component.ComponentFactory;
import org.osgi.service.component.ComponentInstance;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

import us.coastalhacking.corvus.eclipse.EclipseApi;
import us.coastalhacking.corvus.emf.TransactionIdUtil;

@Component(factory = EclipseApi.CorvusLaunch.Component.FACTORY)
public class CorvusLaunchProvider extends LaunchConfigurationDelegate {

	@Reference(name = EclipseApi.CorvusLaunch.Reference.APP_FACTORY, target = "(" + ComponentConstants.COMPONENT_FACTORY
			+ "=" + EclipseApi.CorvusApp.Component.FACTORY + ")")
	ComponentFactory appFactory;

	Map<String, Object> props;
	final List<ILaunch> launches = new CopyOnWriteArrayList<>();
	AtomicBoolean deactivated = new AtomicBoolean(false);

	@Reference
	TransactionIdUtil idUtil;

	@Activate
	void activate(Map<String, Object> props) {
		this.props = props;
	}

	@Deactivate
	void deactivate() {
		if (deactivated.getAndSet(true)) {
			return;
		}

		launches.stream().flatMap(l -> Arrays.stream(l.getProcesses())).filter(IProcess::canTerminate).forEach(p -> {
			try {
				p.terminate();
			} catch (DebugException e) {
				// TODO log
				e.printStackTrace();
			}
		});
		launches.clear();
	}

	@Override
	public void launch(ILaunchConfiguration configuration, String mode, ILaunch launch, IProgressMonitor monitor)
			throws CoreException {
		final Hashtable<String, Object> newProps = new Hashtable<>(props);
		newProps.putAll(configuration.getAttributes());
		final ComponentInstance instance = appFactory.newInstance(newProps);
		launches.add(launch);

		final String id = idUtil.getId(newProps);
		IProcess process = new CorvusAppProcessBase(launch, instance, id);
		launch.addProcess(process);
	}

}

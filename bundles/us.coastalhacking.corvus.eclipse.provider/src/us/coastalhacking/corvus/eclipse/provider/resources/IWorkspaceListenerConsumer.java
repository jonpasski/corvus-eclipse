package us.coastalhacking.corvus.eclipse.provider.resources;

import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IWorkspace;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.ConfigurationPolicy;
import org.osgi.service.component.annotations.Reference;

import us.coastalhacking.corvus.eclipse.EclipseApi;

@Component(service = IWorkspaceListenerConsumer.class, configurationPid = EclipseApi.IWorkspaceListenerConsumer.CONFIG_PID, configurationPolicy = ConfigurationPolicy.REQUIRE, immediate = true)
public class IWorkspaceListenerConsumer {

	@Reference
	IWorkspace workspace;

	@Reference
	void setResourceChangeListener(IResourceChangeListener changeListener) {
		workspace.addResourceChangeListener(changeListener);
	}

	void unsetResourceChangeListener(IResourceChangeListener changeListener) {
		workspace.removeResourceChangeListener(changeListener);
	}
}

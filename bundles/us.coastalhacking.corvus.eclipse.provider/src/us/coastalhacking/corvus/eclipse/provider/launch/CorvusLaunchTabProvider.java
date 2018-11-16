package us.coastalhacking.corvus.eclipse.provider.launch;

import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.CommonTab;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;
import org.eclipse.debug.ui.ILaunchConfigurationTab2;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import us.coastalhacking.corvus.eclipse.EclipseApi;

@Component(factory=EclipseApi.CorvusLaunchTab.Component.FACTORY)
public class CorvusLaunchTabProvider extends AbstractLaunchConfigurationTabGroup {

	@Reference
	ILaunchConfigurationTab2 corvusTab;
	
	@Override
	public void createTabs(ILaunchConfigurationDialog dialog, String mode) {
        setTabs(new ILaunchConfigurationTab[] {corvusTab, new CommonTab()});		
	}

}

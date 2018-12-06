package us.coastalhacking.corvus.eclipse.provider.launch;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.ui.AbstractLaunchConfigurationTab;
import org.eclipse.debug.ui.ILaunchConfigurationTab2;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;
import org.osgi.service.log.Logger;
import org.osgi.service.log.LoggerFactory;

import us.coastalhacking.corvus.eclipse.EclipseApi;
import us.coastalhacking.corvus.emf.TransactionIdUtil;

@Component(service = ILaunchConfigurationTab2.class, scope = ServiceScope.PROTOTYPE)
public class CorvusTabProvider extends AbstractLaunchConfigurationTab {

	@Reference
	LoggerFactory loggerFactory;
	Logger logger;

	@Reference
	IWorkspace workspace;

	@Reference
	TransactionIdUtil idUtil;

	ButtonListener buttonListener = new ButtonListener();

	Text projectText;

	@Activate
	void activate() {
		logger  = loggerFactory.getLogger(ILaunchConfigurationTab2.class);
	}
	
	@Override
	public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
		// called once
		configuration.setAttribute(EclipseApi.IResourceChangeListener.Properties.MARKER_TYPE,
				EclipseApi.Marker.BASE_MARKER);
	}

	@Override
	public void initializeFrom(ILaunchConfiguration configuration) {
		// called once
		String projectName;
		try {
			projectName = idUtil.getId(configuration.getAttributes());
		} catch (CoreException e) {
			logger.warn("Could not get attributes from configuration", e);
			projectName = null;
		}

		if (projectName != null) {
			final IProject maybeProject = workspace.getRoot().getProject(projectName);
			if (maybeProject != null) {
				projectText.setText(projectName);
			} else {
				logger.warn("Transaction ID '{}' does not match a project in the workspace", projectName);
			}
		}
	}

	@Override
	public void performApply(ILaunchConfigurationWorkingCopy configuration) {
		if (!projectText.getText().isEmpty()) {
			final String id = projectText.getText();
			// Remove first forward slash
			configuration.rename(id.substring(1));
			try {
				idUtil.putId(configuration.getAttributes(), id);
			} catch (CoreException e) {
				logger.warn("Could not get attributes from configuration", e);
			}
		}
	}

	@Override
	public String getName() {
		return "Corvus";
	}

	@Override
	public void createControl(Composite parent) {
		Composite comp = new Group(parent, SWT.BORDER);
		setControl(comp);

		GridLayoutFactory.swtDefaults().numColumns(3).applyTo(comp);

		// Project
		Label label = new Label(comp, SWT.NONE);
		label.setText("Project:");
		GridDataFactory.swtDefaults().applyTo(label);

		projectText = new Text(comp, SWT.BORDER);
		projectText.addModifyListener(buttonListener);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).applyTo(projectText);

		Button button = new Button(comp, SWT.PUSH);
		button.setFont(comp.getFont());
		button.setText("Browse...");
		button.addSelectionListener(buttonListener);
		GridDataFactory.swtDefaults().applyTo(button);
	}

	public class ButtonListener implements SelectionListener, ModifyListener {

		@Override
		public void modifyText(ModifyEvent e) {
			updateLaunchConfigurationDialog();
		}

		@Override
		public void widgetSelected(SelectionEvent e) {
			ILabelProvider labelProvider = new LabelProvider() {
				public String getText(Object element) {
					if (element instanceof IProject) {
						return ((IProject) element).getFullPath().toPortableString();
					}
					return null;
				}
			};

			ElementListSelectionDialog dialog = new ElementListSelectionDialog(getShell(), labelProvider);
			dialog.setTitle("Corvus Project Selection");
			dialog.setMessage("Select a project to store Corvus files");
			dialog.setElements(workspace.getRoot().getProjects());

			if (dialog.open() == Window.OK) {
				IProject project = (IProject) dialog.getFirstResult();
				if (project != null) {
					String id = idUtil.getId(project);
					CorvusTabProvider.this.projectText.setText(id);
				}
			}
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
		}
	}
}

package us.coastalhacking.corvus.eclipse.editor.provider;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.adapter.Adapter;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.ui.ViewerPane;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.celleditor.AdapterFactoryTreeEditor;
import org.eclipse.emf.edit.ui.util.EditUIUtil;
import org.eclipse.emf.edit.ui.view.ExtendedPropertySheetPage;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain.Registry;
import org.eclipse.emf.transaction.ui.provider.TransactionalAdapterFactoryContentProvider;
import org.eclipse.emf.transaction.ui.provider.TransactionalAdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.PropertySheetPage;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import us.coastalhacking.corvus.emf.EmfApi;
import us.coastalhacking.corvus.semiotics.editor.SemioticsEditor;
import us.coastalhacking.corvus.semiotics.editor.SemioticsEditorPlugin;

@SuppressWarnings("restriction")
@Component(factory = "us.coastalhacking.corvus.semiotics.editor.SemioticsEditor")
public class SemioticsEditorProvider extends SemioticsEditor {

	@Reference
	IWorkbench workbench;
	// Set by above
	IEclipseContext e4Context;
	ESelectionService selectionService;

	@Reference(name = EmfApi.CorvusTransactionalRegistry.Reference.NAME)
	Registry registry;

	@Activate
	void activate(Map<String, Object> props) {
		e4Context = workbench.getApplication().getContext();
		selectionService = e4Context.get(ESelectionService.class);
		Object selection = selectionService.getSelection();
		Adapter adapter = e4Context.get(Adapter.class);
		IStructuredSelection structured = adapter.adapt(selection, IStructuredSelection.class);
		IResource resource = adapter.adapt(structured.getFirstElement(), IResource.class);
		if (resource != null) {
			// TODO: move to util project
			// also see CorvusTabProvider.performApply
			String projectName = resource.getProject().getFullPath().toPortableString();
			String encodedProjectName = URI.encodeSegment(projectName, true);
			EditingDomain transactional = registry.getEditingDomain(encodedProjectName);
			if (transactional == null) {
				throw new IllegalStateException(
						MessageFormat.format("No editing domain found for project {0}", projectName));
			} else {
				if (transactional instanceof AdapterFactoryEditingDomain) {
					editingDomain = (AdapterFactoryEditingDomain) transactional;
					if (editingDomain.getAdapterFactory() instanceof ComposedAdapterFactory) {
						adapterFactory = (ComposedAdapterFactory) editingDomain.getAdapterFactory();
					} else {
						throw new IllegalStateException(MessageFormat.format(
								"Editing domain for project {0} does not used composed adapter factories",
								projectName));
					}
				} else {
					throw new IllegalStateException(MessageFormat.format(
							"Editing domain for project {0} is not an instance of AdapterFactoryEditingDomain",
							projectName));
				}
			}
		} else {
			throw new IllegalStateException("No resource selected, no way to determine project");
		}
	}

	@Override
	protected void initializeEditingDomain() {
		// Do nothing, initialized during activation\
	}

	protected static String getString(String key) {
		return SemioticsEditorPlugin.INSTANCE.getString(key);
	}

	// Copy-pasta'd from the auto-gen'd editor and updated per "Tutorial: EMF Model
	// Transaction Workspace Integration"
	@Override
	public void createPages() {
		// Creates the model from the editor input
		//
		createModel();

		// Only creates the other pages if there is something that can be edited
		//
		if (!getEditingDomain().getResourceSet().getResources().isEmpty()) {
			// Create a page for the selection tree view.
			//
			{
				ViewerPane viewerPane = new ViewerPane(getSite().getPage(), SemioticsEditorProvider.this) {
					@Override
					public Viewer createViewer(Composite composite) {
						Tree tree = new Tree(composite, SWT.MULTI);
						TreeViewer newTreeViewer = new TreeViewer(tree);
						return newTreeViewer;
					}

					@Override
					public void requestActivation() {
						super.requestActivation();
						setCurrentViewerPane(this);
					}
				};
				viewerPane.createControl(getContainer());

				selectionViewer = (TreeViewer) viewerPane.getViewer();
				// TODO: EMFT change, how to test?
				selectionViewer.setContentProvider(new TransactionalAdapterFactoryContentProvider(
						(TransactionalEditingDomain) getEditingDomain(), adapterFactory));
				selectionViewer.setUseHashlookup(true);
				// TODO: EMFT change, how to test?
				selectionViewer.setLabelProvider(new TransactionalAdapterFactoryLabelProvider(
						(TransactionalEditingDomain) getEditingDomain(), adapterFactory));
				// selectionViewer.setInput(editingDomain.getResourceSet());
				selectionViewer.setInput(getResource());
				selectionViewer.setSelection(
						new StructuredSelection(editingDomain.getResourceSet().getResources().get(0)), true);
				viewerPane.setTitle(editingDomain.getResourceSet());

				new AdapterFactoryTreeEditor(selectionViewer.getTree(), adapterFactory);

				createContextMenuFor(selectionViewer);
				int pageIndex = addPage(viewerPane.getControl());
				setPageText(pageIndex, getString("_UI_SelectionPage_label"));
			}

			// Create a page for the parent tree view.
			//
			{
				ViewerPane viewerPane = new ViewerPane(getSite().getPage(), SemioticsEditorProvider.this) {
					@Override
					public Viewer createViewer(Composite composite) {
						Tree tree = new Tree(composite, SWT.MULTI);
						TreeViewer newTreeViewer = new TreeViewer(tree);
						return newTreeViewer;
					}

					@Override
					public void requestActivation() {
						super.requestActivation();
						setCurrentViewerPane(this);
					}
				};
				viewerPane.createControl(getContainer());

				parentViewer = (TreeViewer) viewerPane.getViewer();
				parentViewer.setAutoExpandLevel(30);
				// TODO: EMFT change next 2 lines, how to test?
				parentViewer.setContentProvider(new ReverseAdapterFactoryContentProvider(adapterFactory));
				parentViewer.setLabelProvider(new TransactionalAdapterFactoryLabelProvider(
						(TransactionalEditingDomain) getEditingDomain(), adapterFactory));

				createContextMenuFor(parentViewer);
				int pageIndex = addPage(viewerPane.getControl());
				setPageText(pageIndex, getString("_UI_ParentPage_label"));
			}

			// This is the page for the list viewer
			//
			{
				ViewerPane viewerPane = new ViewerPane(getSite().getPage(), SemioticsEditorProvider.this) {
					@Override
					public Viewer createViewer(Composite composite) {
						return new ListViewer(composite);
					}

					@Override
					public void requestActivation() {
						super.requestActivation();
						setCurrentViewerPane(this);
					}
				};
				viewerPane.createControl(getContainer());
				listViewer = (ListViewer) viewerPane.getViewer();
				// TODO: EMFT change next 2 lines, how to test?
				listViewer.setContentProvider(new TransactionalAdapterFactoryContentProvider(
						(TransactionalEditingDomain) getEditingDomain(), adapterFactory));
				listViewer.setLabelProvider(new TransactionalAdapterFactoryLabelProvider(
						(TransactionalEditingDomain) getEditingDomain(), adapterFactory));

				createContextMenuFor(listViewer);
				int pageIndex = addPage(viewerPane.getControl());
				setPageText(pageIndex, getString("_UI_ListPage_label"));
			}

			// This is the page for the tree viewer
			//
			{
				ViewerPane viewerPane = new ViewerPane(getSite().getPage(), SemioticsEditorProvider.this) {
					@Override
					public Viewer createViewer(Composite composite) {
						return new TreeViewer(composite);
					}

					@Override
					public void requestActivation() {
						super.requestActivation();
						setCurrentViewerPane(this);
					}
				};
				viewerPane.createControl(getContainer());
				treeViewer = (TreeViewer) viewerPane.getViewer();
				// TODO: EMFT change next 2 lines, how to test?
				treeViewer.setContentProvider(new TransactionalAdapterFactoryContentProvider(
						(TransactionalEditingDomain) getEditingDomain(), adapterFactory));
				treeViewer.setLabelProvider(new TransactionalAdapterFactoryLabelProvider(
						(TransactionalEditingDomain) getEditingDomain(), adapterFactory));

				new AdapterFactoryTreeEditor(treeViewer.getTree(), adapterFactory);

				createContextMenuFor(treeViewer);
				int pageIndex = addPage(viewerPane.getControl());
				setPageText(pageIndex, getString("_UI_TreePage_label"));
			}

			// This is the page for the table viewer.
			//
			{
				ViewerPane viewerPane = new ViewerPane(getSite().getPage(), SemioticsEditorProvider.this) {
					@Override
					public Viewer createViewer(Composite composite) {
						return new TableViewer(composite);
					}

					@Override
					public void requestActivation() {
						super.requestActivation();
						setCurrentViewerPane(this);
					}
				};
				viewerPane.createControl(getContainer());
				tableViewer = (TableViewer) viewerPane.getViewer();

				Table table = tableViewer.getTable();
				TableLayout layout = new TableLayout();
				table.setLayout(layout);
				table.setHeaderVisible(true);
				table.setLinesVisible(true);

				TableColumn objectColumn = new TableColumn(table, SWT.NONE);
				layout.addColumnData(new ColumnWeightData(3, 100, true));
				objectColumn.setText(getString("_UI_ObjectColumn_label"));
				objectColumn.setResizable(true);

				TableColumn selfColumn = new TableColumn(table, SWT.NONE);
				layout.addColumnData(new ColumnWeightData(2, 100, true));
				selfColumn.setText(getString("_UI_SelfColumn_label"));
				selfColumn.setResizable(true);

				tableViewer.setColumnProperties(new String[] { "a", "b" });
				// TODO: EMFT change next 2 lines, how to test?
				tableViewer.setContentProvider(new TransactionalAdapterFactoryContentProvider(
						(TransactionalEditingDomain) getEditingDomain(), adapterFactory));
				tableViewer.setLabelProvider(new TransactionalAdapterFactoryLabelProvider(
						(TransactionalEditingDomain) getEditingDomain(), adapterFactory));

				createContextMenuFor(tableViewer);
				int pageIndex = addPage(viewerPane.getControl());
				setPageText(pageIndex, getString("_UI_TablePage_label"));
			}

			// This is the page for the table tree viewer.
			//
			{
				ViewerPane viewerPane = new ViewerPane(getSite().getPage(), SemioticsEditorProvider.this) {
					@Override
					public Viewer createViewer(Composite composite) {
						return new TreeViewer(composite);
					}

					@Override
					public void requestActivation() {
						super.requestActivation();
						setCurrentViewerPane(this);
					}
				};
				viewerPane.createControl(getContainer());

				treeViewerWithColumns = (TreeViewer) viewerPane.getViewer();

				Tree tree = treeViewerWithColumns.getTree();
				tree.setLayoutData(new FillLayout());
				tree.setHeaderVisible(true);
				tree.setLinesVisible(true);

				TreeColumn objectColumn = new TreeColumn(tree, SWT.NONE);
				objectColumn.setText(getString("_UI_ObjectColumn_label"));
				objectColumn.setResizable(true);
				objectColumn.setWidth(250);

				TreeColumn selfColumn = new TreeColumn(tree, SWT.NONE);
				selfColumn.setText(getString("_UI_SelfColumn_label"));
				selfColumn.setResizable(true);
				selfColumn.setWidth(200);

				treeViewerWithColumns.setColumnProperties(new String[] { "a", "b" });
				// TODO: EMFT change next 2 lines, how to test?
				treeViewerWithColumns.setContentProvider(new TransactionalAdapterFactoryContentProvider(
						(TransactionalEditingDomain) getEditingDomain(), adapterFactory));
				treeViewerWithColumns.setLabelProvider(new TransactionalAdapterFactoryLabelProvider(
						(TransactionalEditingDomain) getEditingDomain(), adapterFactory));

				createContextMenuFor(treeViewerWithColumns);
				int pageIndex = addPage(viewerPane.getControl());
				setPageText(pageIndex, getString("_UI_TreeWithColumnsPage_label"));
			}

			getSite().getShell().getDisplay().asyncExec(new Runnable() {
				public void run() {
					if (!getContainer().isDisposed()) {
						setActivePage(0);
					}
				}
			});
		}

		// Ensures that this editor will only display the page's tab
		// area if there are more than one page
		//
		getContainer().addControlListener(new ControlAdapter() {
			boolean guard = false;

			@Override
			public void controlResized(ControlEvent event) {
				if (!guard) {
					guard = true;
					hideTabs();
					guard = false;
				}
			}
		});

		getSite().getShell().getDisplay().asyncExec(new Runnable() {
			public void run() {
				updateProblemIndication();
			}
		});
	}

	// Copy-pasta'd from super
	@Override
	public IPropertySheetPage getPropertySheetPage() {
		PropertySheetPage propertySheetPage = new ExtendedPropertySheetPage(editingDomain,
				ExtendedPropertySheetPage.Decoration.NONE, null, 0, false) {
			@Override
			public void setSelectionToViewer(List<?> selection) {
				SemioticsEditorProvider.this.setSelectionToViewer(selection);
				SemioticsEditorProvider.this.setFocus();
			}

			@Override
			public void setActionBars(IActionBars actionBars) {
				super.setActionBars(actionBars);
				getActionBarContributor().shareGlobalActions(this, actionBars);
			}
		};
		// TODO: EMFT change, how to test?
		propertySheetPage.setPropertySourceProvider(new TransactionalAdapterFactoryContentProvider(
				(TransactionalEditingDomain) getEditingDomain(), adapterFactory));
		propertySheetPages.add(propertySheetPage);

		return propertySheetPage;

	}

	// By the time we're called the resource has already been initialized
	protected Resource getResource() {
		final URI resourceURI = EditUIUtil.getURI(getEditorInput(), editingDomain.getResourceSet().getURIConverter());
		return editingDomain.getResourceSet().getResource(resourceURI, true);
	}

	@Override
	// TODO: test for emft changes
	public void doSave(IProgressMonitor progressMonitor) {
		// Save only resources that have actually changed.
		//
		final Map<Object, Object> saveOptions = new HashMap<Object, Object>();
		saveOptions.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED, Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
		saveOptions.put(Resource.OPTION_LINE_DELIMITER, Resource.OPTION_LINE_DELIMITER_UNSPECIFIED);

		// Do the work within an operation because this is a long running activity that
		// modifies the workbench.
		//
		WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
			// This is the method that gets invoked when the operation runs.
			//
			@Override
			public void execute(IProgressMonitor monitor) {

				try {
					((TransactionalEditingDomain) getEditingDomain()).runExclusive(new Runnable() {
						public void run() {
							Resource resource = getResource();
							if (isPersisted(resource) && !editingDomain.isReadOnly(resource)) {
								try {
									long timeStamp = resource.getTimeStamp();
									resource.save(saveOptions);
									if (resource.getTimeStamp() != timeStamp) {
										savedResources.add(resource);
									}
								} catch (Exception exception) {
									resourceToDiagnosticMap.put(resource, analyzeResourceProblems(resource, exception));
								}
							}
						}
					});
				} catch (Exception exception) {
					SemioticsEditorPlugin.INSTANCE.log(exception);
				}
			}
		};

		updateProblemIndication = false;
		try {
			// This runs the options, and shows progress.
			//
			new ProgressMonitorDialog(getSite().getShell()).run(true, false, operation);

			// Refresh the necessary state.
			//
			((BasicCommandStack) editingDomain.getCommandStack()).saveIsDone();
			firePropertyChange(IEditorPart.PROP_DIRTY);
		} catch (Exception exception) {
			// Something went wrong that shouldn't.
			//
			SemioticsEditorPlugin.INSTANCE.log(exception);
		}
		updateProblemIndication = true;
		updateProblemIndication();
	}

}

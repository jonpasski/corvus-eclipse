
## CorvusTabProvider

### performApply

```
	@Override
	public void performApply(ILaunchConfigurationWorkingCopy configuration) {
		if (!projectText.getText().isEmpty()) {
			String projectName = projectText.getText();
			// TODO: use eclipse resources here instead of JRE
			// TODO: change URI from file to project in resource initializer when creating URIs
			java.nio.file.Path path = Paths.get(projectName, "corvus.eclipseResources");
			configuration.setAttribute(CorvusLaunchApi.EclipseResourcesInitializer.Properties.PHYSICAL, path.toAbsolutePath().toString());
			configuration.setAttribute(PROJECT_NAME, projectName);

		}
	}
```

The above needs to be extensible somehow.

For each Corvus Resource, provide:
* Its physical path relative to an arbitrary and relative project directory

### setDefaults

```
	@Override
	public void setDefaults(ILaunchConfigurationWorkingCopy configuration) {
		// called once
		String transactionId = String.format("corvus.transaction.%s", Instant.now().toEpochMilli());
		configuration.rename(transactionId);
		configuration.setAttribute(CorvusLaunchApi.TransactionalEditingDomain.Properties.ID, transactionId);
		configuration.setAttribute(CorvusLaunchApi.EclipseResourcesInitializer.Properties.LOGICAL, CorvusLaunchApi.EclipseResourcesInitializer.Properties.DEFAULT_LOGICAL);
		
		configuration.setAttribute(CorvusLaunchApi.EclipseResourcesChangeListener.Properties.MARKER_TYPE, CorvusLaunchApi.BASE_MARKER);
	}
```

Need a list of logical URIs? Or can the Corvus Resource provide its own via a common property, like below?

```
@Componet(service=CorvusResource.class, property={CorvusResource.LOGICAL + "=" + EclipseResourcesInitializer.Properties.DEFAULT_LOGICAL})
EclipseResourcesResource extends BaseCorvusResource

```

## CorvusAppProvider.activate

```
	@Activate
	void activate(Map<String, Object> oldProps) throws Exception {
		Hashtable<String, Object> newProps = new Hashtable<>(oldProps);
		// Create a target filter and apply to all things which should be targeted
		transactionId = (String) newProps.get(CorvusAppApi.TransactionalEditingDomain.Properties.ID);
		String[] targets = {
			CorvusAppApi.CorvusTransactionalFactory.Reference.INITIALIZERS,
			CorvusAppApi.EclipseResourcesChangeListener.Reference.REGISTRY,
			CorvusAppApi.ResourceModifiedListener.Reference.REGISTRY
		};
		helper.target(newProps, Arrays.stream(targets).sequential(), CorvusAppApi.TransactionalEditingDomain.Properties.ID,
				transactionId);

		// Ordered
		helper.configure(EntryPointApi.ResourceInitializer.Component.CONFIG_PID, newProps, configurations);
		helper.configure(CorvusAppApi.EclipseResourcesInitializer.Component.CONFIG_PID, newProps, configurations);
		helper.configure(CorvusAppApi.CorvusTransactionalFactory.Component.CONFIG_PID, newProps, configurations);
		helper.configure(CorvusAppApi.CorvusTransactionalRegistry.Component.CONFIG_PID, newProps, configurations);
		helper.configure(CorvusAppApi.ResourceModifiedListener.Component.CONFIG_PID, newProps, configurations);
		helper.configure(CorvusAppApi.EclipseResourcesChangeListener.Component.CONFIG_PID, newProps, configurations);
	}

```

Top part: Corvus Tool need to provide a list of references that should be targeted

Bottom part: Corvus Tool need to provide a list of PIDs to configure




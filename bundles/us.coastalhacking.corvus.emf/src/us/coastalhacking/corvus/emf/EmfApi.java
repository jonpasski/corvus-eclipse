package us.coastalhacking.corvus.emf;

public interface EmfApi {
	interface TransactionalEditingDomain {
		interface Properties {
			String ID = "corvus.emf.id";
		}
	}

	interface ResourceInitializer {
		interface Properties {
			String LOGICAL = "corvus.emf.initializer.logical";
			String PHYSICAL = "corvus.emf.initializer.physical";
			@Deprecated
			String PROJECT = "corvus.emf.initializer.project";
		}
		
		interface EclipseResources {
			interface Properties {
				String LOGICAL = "corvus:eclipse";
				String PHYSICAL = "eclipse.semiotics";
			}
		}
		
		interface EntryPoint {
			interface Properties {
				String LOGICAL = "corvus:entrypoint";
				String PHYSICAL = "entrypoint.semiotics";
			}
		}
	}
	
	interface CorvusTransactionalFactory {
		interface Component {
			String CONFIG_PID = "corvus.emf.factory";
		}

		interface Reference {
			String INITIALIZERS = "corvus.emf.factory.initializers";
		}
	}
	
	interface CorvusTransactionalRegistry {
		interface Component {
			String CONFIG_PID = "corvus.emf.registry";
		}

		interface Reference {
			String FACTORY = "corvus.emf.registry.factory";
			String NAME = "corvus.emf.registry";
		}
	}

	interface ResourceModifiedListener {
		interface Component {
			String CONFIG_PID = "corvus.emf.resourcemodified";
		}

		interface Reference {
			@Deprecated
			String REGISTRY = "corvus.emf.resourcemodified.registry";
		}
	}

}

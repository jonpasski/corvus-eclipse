package us.coastalhacking.corvus.emf;

public interface EmfApi {
	
	interface ResourceSetListener {
		interface Properties {
			String ID = "corvus.emf.rsl";
			
			interface ResourceModifiedListener {
				String ID = "corvus.emf.resourcemodified";
			}
		}
	}

	interface IEditingDomainProvider {
		interface Component {
			String CONFIG_PID = "corvus.emf.iedp";
		}
		interface Reference {
			String NAME = EmfApi.IEditingDomainProvider.Component.CONFIG_PID  + ".name";
		}
	}
	
	interface TransactionalEditingDomain {
		interface Properties {
			String ID = "corvus.emf.id";
		}
	}

	interface ResourceInitializer {
		interface Properties {
			String LOGICAL = "corvus.emf.initializer.logical";
			String PHYSICAL = "corvus.emf.initializer.physical";
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

	@Deprecated
	interface ResourceModifiedListener {
		interface Component {
			String CONFIG_PID = "corvus.emf.resourcemodified";
		}
	}

}

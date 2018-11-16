package us.coastalhacking.corvus.eclipse;

public interface EclipseApi {

	interface Marker {
		String BASE_MARKER = "us.coastalhacking.corvus.eclipse.base";
		String ENTRY_POINT = "us.coastalhacking.corvus.eclipse.entrypoint";
	}

	interface Fragment {
		interface Command {
			String ID = "us.coastalhacking.corvus.eclipse.command";
		}

		interface CommandParameter {
			String MARKER = "us.coastalhacking.corvus.eclipse.commandparameter.marker";
			String ACTION = "us.coastalhacking.corvus.eclipse.commandparameter.action";
			String ACTION_ADD = "add";
		}

		interface Menu {
			String ID = "us.coastalhacking.corvus.eclipse.menu";
		}
	}

	interface Event {
		interface Marker {
			String TOPIC_ALL = "us/coastalhacking/corvus/eclipse/marker/*";
			String TOPIC_ADD = "us/coastalhacking/corvus/eclipse/marker/ADD";
			String PROP_TYPE = "corvus.eclipse.marker.type";
		}
	}

	interface IResourceChangeListener {
		interface Component {
			String CONFIG_PID = "corvus.eclipse.ircl";
		}

		interface Properties {
			String MARKER_TYPE = "corvus.eclipse.ircl.markertype";
		}

		interface Reference {
			String REGISTRY = "corvus.eclipse.ircl.registry";
		}
	}

	interface ResourceModifiedListener {
		interface Component {
			String CONFIG_PID = "corvus.resourcemodified.listener";
		}

		interface Reference {
			String REGISTRY = "corvus.resourcemodified.listener.registry";
		}
	}

	interface MarkerSupport {
		interface Component {
			String CONFIG_PID = "corvus.eclipse.markersupport";
		}

		interface Reference {
			String MARKER_PROVIDER = "corvus.markersupport.markerprovider";
		}
	}

	@Deprecated
	interface EntryPointTrigger {
		interface Component {
			String CONFIG_PID = "corvus.entrypoint.trigger";
		}
	}

	interface TriggerListener {
		interface EntryPoint {
			interface Component {
				String CONFIG_PID = "corvus.triggers.entrypoint";
			}	
		}
	}

	interface CorvusApp {
		interface Component {
			String FACTORY = "corvus.eclipse.app.factory";
		}
	}

	interface CorvusLaunch {
		interface Component {
			String FACTORY = "corvus.launch.factory.launcher";
		}

		interface Reference {
			String APP_FACTORY = "corvus.launch.app.factory";
		}
	}

	interface CorvusLaunchTab {
		interface Component {
			String FACTORY = "corvus.launch.factory.tabgroup";
		}
	}
}

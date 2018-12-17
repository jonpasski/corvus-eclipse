package us.coastalhacking.corvus.eclipse;

import us.coastalhacking.corvus.emf.MarkerApi;

public interface EclipseApi {

	interface ResourceSetListener {
		interface Properties {
			interface MarkerToEntryPoint {
				String ID = "corvus.eclipse.rsl.entrypoint.id";
			}
		}
	}

	interface Marker {
		// FIXME: dirty hack because item providers are in emf package
		String BASE_MARKER = MarkerApi.Type.BASE;
		String ENTRY_POINT = MarkerApi.Type.ENTRY_POINT;
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

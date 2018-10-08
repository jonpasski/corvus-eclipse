package us.coastalhacking.corvus.eclipse.ui.api;

public interface EclipseUiApi {

	interface CommandParameter {
		String MARKER = "us.coastalhacking.corvus.eclipse.ui.commandparameter.marker";
		String ACTION = "us.coastalhacking.corvus.eclipse.ui.commandparameter.action";
		String ACTION_ADD = "add";
	}

	interface Event {

		interface Marker {
			String TOPIC_ALL = "us/coastalhacking/corvus/eclipse/ui/marker/*";
			String TOPIC_ADD = "us/coastalhacking/corvus/eclipse/ui/marker/ADD";
			String PROP_TYPE = "corvus.eclipse.marker.type";
		}
	}

}

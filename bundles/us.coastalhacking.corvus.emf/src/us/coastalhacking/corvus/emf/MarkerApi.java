package us.coastalhacking.corvus.emf;

// TODO: move to Eclipse package when adapter factories are whiteboared
public interface MarkerApi {

	interface DocumentUri {
		String PARAM_TYPE = "markerType";		
	}

	interface Marker {
		interface Base {
			String TYPE = "us.coastalhacking.corvus.eclipse.base";
			// 1-relative, org.eclipse.core.resources.IMarker.LINE_NUMBER
			String START_LINE = "lineNumber";
			// 1-relative
			String END_LINE = "lineNumberEnd";
			// 0-relative, org.eclipse.core.resources.IMarker.CHAR_START
			String START_CHAR = "charStart";
			// 0-relative, org.eclipse.core.resources.IMarker.CHAR_END
			String END_CHAR = "charEnd";
		}

		interface EntryPoint {
			String TYPE = "us.coastalhacking.corvus.eclipse.entrypoint";
		}
	}

	interface Type {
		@Deprecated
		String BASE = Marker.Base.TYPE;

		@Deprecated
		String ENTRY_POINT = Marker.EntryPoint.TYPE;
	}

	interface Feature {
		interface IResource {
			// org.eclipse.core.resources.IResource#findMarkers(String, boolean, int)
			int IRESOURCE__IMARKERS = 0;
		}

		interface IMarker {
			// org.eclipse.core.resources.IMarker.getResource()
			int IMARKER__IRESOURCE = 0;
		}
	}
}

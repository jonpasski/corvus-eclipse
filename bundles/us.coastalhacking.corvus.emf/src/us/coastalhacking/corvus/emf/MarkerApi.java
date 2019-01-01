package us.coastalhacking.corvus.emf;

// TODO: move to Eclipse package when adapter factories are whiteboared
public interface MarkerApi {

	@Deprecated
	interface DocumentUri {
		String PARAM_TYPE = Properties.TYPE;		
	}

	interface Properties {
		String TYPE = "markerType";
		String URI = "markerUri";
		String ATTRIBUTES = "markerAttributes";
	}
	
	interface Base {
		String NAME = "us.coastalhacking.corvus.eclipse.base";
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
		String NAME = "us.coastalhacking.corvus.eclipse.entrypoint";
	}

	@Deprecated
	interface Marker {
		@Deprecated
		interface Base {
			@Deprecated
			String TYPE = MarkerApi.Base.NAME;
			@Deprecated
			String START_LINE = MarkerApi.Base.START_LINE;
			@Deprecated
			String END_LINE = MarkerApi.Base.END_LINE;
			@Deprecated
			String START_CHAR = MarkerApi.Base.START_CHAR;
			@Deprecated
			String END_CHAR = MarkerApi.Base.END_CHAR;
		}

		@Deprecated
		interface EntryPoint {
			@Deprecated
			String TYPE = MarkerApi.EntryPoint.NAME;
		}
	}

	@Deprecated
	interface Type {
		@Deprecated
		String BASE = Marker.Base.TYPE;

		@Deprecated
		String ENTRY_POINT = Marker.EntryPoint.TYPE;
	}

	@Deprecated
	interface Feature {
		@Deprecated
		interface IResource {
			// org.eclipse.core.resources.IResource#findMarkers(String, boolean, int)
			int IRESOURCE__IMARKERS = 0;
		}

		@Deprecated
		interface IMarker {
			// org.eclipse.core.resources.IMarker.getResource()
			int IMARKER__IRESOURCE = 0;
		}
	}
}

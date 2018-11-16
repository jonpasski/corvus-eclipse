package us.coastalhacking.corvus.eclipse;

import java.util.Optional;
import java.util.concurrent.Future;

import org.eclipse.core.resources.IResource;

public interface MarkerSupport {

	interface Coordinate {

		/**
		 * Zero-relative and inclusive
		 * 
		 * @see org.eclipse.core.resources.IMarker#CHAR_START IMarker.CHAR_START
		 */
		int charStart();

		/**
		 * Zero-relative and exclusive
		 * 
		 * @see org.eclipse.core.resources.IMarker#CHAR_END IMarker.CHAR_END
		 */
		int charEnd();

		/**
		 * The line number, 1-relative
		 * 
		 * @return For non-line terminating documents (i.e. binary), returns
		 *         <code>1</code>
		 * @see org.eclipse.core.resources.IMarker#LINE_NUMBER IMarker.LINE_NUMBER
		 */
		int lineNumber();
		
		/**
		 * The selected text
		 * 
		 * @return the selected text
		 */
		String selected();
	}

	Future<Void> createMarker(Coordinate coordinate, String resourceFullPath, String markerType);
	
	/**
	 * Obtain the resource via the active editor
	 * 
	 * @return A future which completes with either the resource or exceptionally
	 *  
	 * @throws Exception
	 */
	Future<IResource> getActiveEditorResource();

	Optional<Coordinate> getSelectedCoordinate() throws Exception;
}

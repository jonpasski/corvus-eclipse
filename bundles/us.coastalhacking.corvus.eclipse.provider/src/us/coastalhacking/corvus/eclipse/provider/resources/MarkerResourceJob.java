package us.coastalhacking.corvus.eclipse.provider.resources;

import java.util.concurrent.CompletableFuture;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import us.coastalhacking.corvus.eclipse.MarkerSupport.Coordinate;

public class MarkerResourceJob extends WorkspaceJob {

	final String jobId;
	final CompletableFuture<Void> future;
	final String markerId;
	final IResource resource;
	final Coordinate coordinate;

	public MarkerResourceJob(String jobId, CompletableFuture<Void> future, String markerId, IResource resource,
			Coordinate coordinate) {
		super(String.format("New marker '%s' on resource '%s' job", markerId, resource.getLocationURI()));
		this.jobId = jobId;
		this.resource = resource;
		this.markerId = markerId;
		this.future = future;
		this.coordinate = coordinate;
	}

	@Override
	public IStatus runInWorkspace(IProgressMonitor monitor) throws CoreException {
		IStatus result = null;
		try {
			IMarker marker = resource.createMarker(markerId);
			// Offset by 1
			int lineNo = coordinate.lineNumber() + 1;
			if (marker.isSubtypeOf(IMarker.TEXT)) {
				marker.setAttribute(IMarker.CHAR_START, coordinate.charStart());
				marker.setAttribute(IMarker.CHAR_END, coordinate.charEnd());
				marker.setAttribute(IMarker.LINE_NUMBER, lineNo);
			}

			if (marker.isSubtypeOf(IMarker.BOOKMARK)) {
				marker.setAttribute(IMarker.MESSAGE, coordinate.selected());
				marker.setAttribute(IMarker.LOCATION, String.format("line %s", lineNo));
			}
			future.complete(null);
			result = Status.OK_STATUS;
		} catch (Exception e) {
			future.completeExceptionally(e);
			result = new Status(IStatus.ERROR, jobId, e.getMessage(), e);
		}
		return result;
	}

}

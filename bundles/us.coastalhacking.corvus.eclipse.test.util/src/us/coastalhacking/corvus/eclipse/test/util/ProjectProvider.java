package us.coastalhacking.corvus.eclipse.test.util;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

@Component(immediate=true)
public class ProjectProvider {

	@Reference
	IWorkspace workspace;
	IProject project;
	IFile file;
	
	@Activate
	void activate(Map<String, Object> props) throws Exception {
		IWorkspaceRoot root = workspace.getRoot();
		project = root.getProject("aoeu");
		project.create(null);
		project.open(null);
		file = project.getFile("foo.txt");
		file.create(new ByteArrayInputStream("aoeu\n\n\naoeu\n".getBytes(StandardCharsets.UTF_8)), IResource.FORCE, null);
	}
	
	@Deactivate
	void deactivate() throws Exception {
		file.delete(true, null);
		project.delete(true, null);
	}
}

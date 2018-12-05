package us.coastalhacking.corvus.eclipse.provider.launch;

import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IStreamsProxy;
import org.osgi.service.component.ComponentInstance;

public class CorvusAppProcessBase implements IProcess {

	private final ILaunch launch;
	private final ComponentInstance appInstance;
	private AtomicBoolean disposed = new AtomicBoolean(false); 
	private final String label;
	
	public CorvusAppProcessBase(ILaunch launch, ComponentInstance appInstance, String label) {
		this.launch = launch;
		this.appInstance = appInstance;

		// Don't need to save the result
		appInstance.getInstance();
		this.label = label;
	}
	
	@Override
	public <T> T getAdapter(Class<T> adapter) {
		return null;
	}

	@Override
	public boolean canTerminate() {
		return !isTerminated();
	}

	@Override
	public boolean isTerminated() {
		return disposed.get();
	}

	@Override
	public void terminate() throws DebugException {
		if (disposed.getAndSet(true)) {
			return;
		}
		appInstance.dispose();
	}

	@Override
	public String getLabel() {
		return this.label;
	}
	
	@Override
	public ILaunch getLaunch() {
		return launch;
	}

	@Override
	public IStreamsProxy getStreamsProxy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAttribute(String key, String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getAttribute(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getExitValue() throws DebugException {
		// TODO Auto-generated method stub
		return 0;
	}

}

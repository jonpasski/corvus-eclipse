package us.coastalhacking.corvus.emf.provider;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

@Component(service=EventHandler.class)
public class CorvusEventHandler implements EventHandler {

	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
		
	}

}

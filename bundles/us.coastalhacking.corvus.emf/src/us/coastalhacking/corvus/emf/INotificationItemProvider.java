package us.coastalhacking.corvus.emf;

import java.util.Optional;

public interface INotificationItemProvider {

	Optional<Integer> getEventType(Object object);

	Object getOldValue(Object object);

	Object getNewValue(Object object);

}

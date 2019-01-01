package us.coastalhacking.corvus.emf;

import java.util.Optional;
import java.util.function.Function;

import org.osgi.service.event.Event;

public interface EventFunction<T> extends Function<T, Optional<Event>> {

}

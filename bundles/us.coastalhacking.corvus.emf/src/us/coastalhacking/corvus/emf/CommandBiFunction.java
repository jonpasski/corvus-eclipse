package us.coastalhacking.corvus.emf;

import java.util.Optional;
import java.util.function.BiFunction;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.osgi.service.event.Event;

public interface CommandBiFunction extends BiFunction<Event, EditingDomain, Optional<Command>> {

}

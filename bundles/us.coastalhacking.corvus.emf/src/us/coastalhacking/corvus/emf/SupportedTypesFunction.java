package us.coastalhacking.corvus.emf;

import java.util.Optional;
import java.util.function.Function;

import org.osgi.framework.Filter;
import org.osgi.framework.ServiceReference;

public interface SupportedTypesFunction extends Function<ServiceReference<?>, Optional<Filter>> {

}

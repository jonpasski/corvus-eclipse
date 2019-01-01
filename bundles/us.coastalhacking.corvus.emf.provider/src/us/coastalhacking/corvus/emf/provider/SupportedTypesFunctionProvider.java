package us.coastalhacking.corvus.emf.provider;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.osgi.framework.Filter;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceReference;
import org.osgi.service.component.annotations.Component;

import com.google.common.base.Strings;

import us.coastalhacking.corvus.emf.EventApi;
import us.coastalhacking.corvus.emf.SupportedTypesFunction;

@Component(service = SupportedTypesFunction.class, immediate=true)
public class SupportedTypesFunctionProvider implements SupportedTypesFunction {

	private static final String empty = "";
	private static final String prefix = "(&";
	private static final String suffix = ")";
	private static final String format = "(%s=%s)";

	@Override
	public Optional<Filter> apply(ServiceReference<?> sr) {
		Filter result = null;
		Object value = sr.getProperty(EventApi.Properties.SUPPORTED_TYPES);
		if (value != null) {
			// TODO: support collections
			String[] supportedTypes = value instanceof String[] ? (String[]) value : new String[] { value.toString() };
			// TODO: escape property values
			String filter = Stream.of(supportedTypes)
					.map(s -> String.format(format, EventApi.Properties.SUPPORTED_TYPES, s))
					.collect(Collectors.joining(empty, prefix, suffix));
			if (!Strings.isNullOrEmpty(filter)) {
				try {
					result = FrameworkUtil.createFilter(filter);
				} catch (InvalidSyntaxException e) {
					// TODO log
					e.printStackTrace();
				}
			}
		}
		return Optional.ofNullable(result);
	}

}

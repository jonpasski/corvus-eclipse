package us.coastalhacking.corvus.util.provider;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.owasp.prevent.ldap.LdapEscape;

import us.coastalhacking.corvus.util.ConfigurationAdminHelper;

@Component(service = ConfigurationAdminHelper.class) //, immediate=true)
public class ConfigurationAdminHelperProvider implements ConfigurationAdminHelper {

	@Reference
	ConfigurationAdmin configAdmin;

	@Override
	public void target(Map<String, Object> props, Stream<String> targets, String targetKey, String targetValue) {
		// "foo.target=(unescapedKey=escapedValue)"
		if (targets.isParallel()) {
			throw new RuntimeException("Unknown map type, parallel operations may be unsafe");
		}
		targets.forEach(target -> {
			props.put(target + ".target",
					String.format("(%s=%s)", targetKey, LdapEscape.escapeLDAPSearchFilter(targetValue)));
		});
	}

	@Override
	public String configure(String factoryPid, Map<String, Object> props, List<Configuration> configurations)
			throws Exception {
		// Cross-bundle configuration creation requires specifying a "location"
		// Using the special location "?"
		final Configuration configuration = configAdmin.createFactoryConfiguration(factoryPid, "?");
		configurations.add(configuration);
		configuration.update(new Hashtable<>(props));
		return configuration.getPid();
	}

	@Override
	public void deactivateConfigurations(List<Configuration> configurations) {
		configurations.forEach(c -> {
			try {
				c.delete();
			} catch (Exception e) {
				// TODO log
				e.printStackTrace();
			}
		});
	}

}

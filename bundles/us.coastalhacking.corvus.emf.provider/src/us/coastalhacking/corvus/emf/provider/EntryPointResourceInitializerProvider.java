package us.coastalhacking.corvus.emf.provider;

import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;

import us.coastalhacking.corvus.emf.BaseResourceInitializer;
import us.coastalhacking.corvus.emf.EmfApi;
import us.coastalhacking.corvus.emf.ResourceInitializer;
import us.coastalhacking.corvus.semiotics.SemioticsFactory;

@Component(service = ResourceInitializer.class, immediate = true, property = {
		EmfApi.ResourceInitializer.Properties.LOGICAL + "=" + EmfApi.ResourceInitializer.EntryPoint.Properties.LOGICAL,
		EmfApi.ResourceInitializer.Properties.PHYSICAL + "=" + EmfApi.ResourceInitializer.EntryPoint.Properties.PHYSICAL
		})
public class EntryPointResourceInitializerProvider extends BaseResourceInitializer {

	@Activate
	void activate(Map<String, Object> props) {
		baseActivate(props);
	}

	@Override
	public EObject getRoot() {
		return SemioticsFactory.eINSTANCE.createRoot();
	}
}

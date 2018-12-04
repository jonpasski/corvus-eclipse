package us.coastalhacking.corvus.emf;

import java.util.Map;

public abstract class BaseResourceInitializer implements ResourceInitializer {

	protected String logical;
	protected String physical;

	protected void baseActivate(Map<String, Object> props) {
		logical = (String) props.get(EmfApi.ResourceInitializer.Properties.LOGICAL);
		physical = (String) props.get(EmfApi.ResourceInitializer.Properties.PHYSICAL);
	}

	@Override
	public String getLogical() {
		return logical;
	}

	@Override
	public String getFilename() {
		return physical;
	}

}

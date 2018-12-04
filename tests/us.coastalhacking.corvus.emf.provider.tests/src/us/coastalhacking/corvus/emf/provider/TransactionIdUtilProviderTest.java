package us.coastalhacking.corvus.emf.provider;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import us.coastalhacking.corvus.emf.EmfApi;
import us.coastalhacking.corvus.emf.TransactionIdUtil;
import us.coastalhacking.corvus.test.util.AbstractProjectTest;

class TransactionIdUtilProviderTest extends AbstractProjectTest {

	public TransactionIdUtilProviderTest() throws Exception {
		super();
	}
	
	Map<String, Object> props;
	String expectedId;
	URI expectedUri;
	
	@BeforeEach
	protected void beforeEach() throws Exception {
		super.beforeEach();
		expectedId = project.getFullPath().toPortableString();
		expectedUri = URI.createPlatformResourceURI(expectedId, true);
		props = new HashMap<>();
		props.put(EmfApi.TransactionalEditingDomain.Properties.ID, expectedId);
	}

	@Test
	void shouldConfigure() throws Exception {
		TransactionIdUtil idUtil = serviceTrackerHelper(TransactionIdUtil.class);
		assert(idUtil instanceof TransactionIdUtilProvider);
	}

	@Test
	void shouldGetId() {
		TransactionIdUtilProvider provider = new TransactionIdUtilProvider();
		String actualId = provider.getId(props);
		assertEquals(expectedId, actualId);
	}

	@Test
	void shouldGetUriProps() {
		TransactionIdUtilProvider provider = new TransactionIdUtilProvider();
		URI actualUri = provider.getUri(props);
		assertEquals(expectedUri, actualUri);
	}
	
	@Test
	void shouldGetUriString() {
		TransactionIdUtilProvider provider = new TransactionIdUtilProvider();
		assertTrue(expectedId.startsWith("/"));
		URI actualUri = provider.getUri(expectedId);
		assertFalse(actualUri.toString().contains("%"));
		assertEquals(expectedUri, actualUri);
	}

	@Test
	void shouldGetIdIProject() {
		TransactionIdUtilProvider provider = new TransactionIdUtilProvider();
		String actualId = provider.getId(project);
		assertEquals(expectedId, actualId);
	}
	
	@Test
	void shouldPutId() {
		props.clear();
		TransactionIdUtilProvider provider = new TransactionIdUtilProvider();
		provider.putId(props, expectedId);
		assertTrue(props.containsKey(EmfApi.TransactionalEditingDomain.Properties.ID));
		assertEquals(expectedId, props.get(EmfApi.TransactionalEditingDomain.Properties.ID));
	}
}

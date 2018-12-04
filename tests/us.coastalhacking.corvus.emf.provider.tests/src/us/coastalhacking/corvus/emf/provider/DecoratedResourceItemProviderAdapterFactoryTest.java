package us.coastalhacking.corvus.emf.provider;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemProviderDecorator;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.junit.jupiter.api.Test;

import us.coastalhacking.corvus.test.util.AbstractProjectTest;

class DecoratedResourceItemProviderAdapterFactoryTest extends AbstractProjectTest {

	public DecoratedResourceItemProviderAdapterFactoryTest() throws Exception {
		super();
	}

	@Test
	void shouldProveResourceItemProviderAdapterFactory() {
		// Prep
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory();
		adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());

		// Execute
		Adapter resourceSetAdapter = adapterFactory.adapt(new ResourceSetImpl(), IEditingDomainItemProvider.class);
		Adapter resourceAdapter = adapterFactory.adapt(new ResourceImpl(), IEditingDomainItemProvider.class);

		// Verify
		assertNotNull(resourceSetAdapter);
		assertTrue(resourceSetAdapter.getTarget() instanceof ResourceSet);
		assertNotNull(resourceAdapter);
		assertTrue(resourceAdapter.getTarget() instanceof Resource);
	}

	@Test
	void shouldProveDecoratedResourceItemProviderAdapterFactory() {
		// Prep
		ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory();
		adapterFactory.addAdapterFactory(new DecoratedResourceItemProviderAdapterFactory());

		// Execute
		Adapter resourceSetAdapter = adapterFactory.adapt(new ResourceSetImpl(), IEditingDomainItemProvider.class);
		Adapter resourceAdapter = adapterFactory.adapt(new ResourceImpl(), IEditingDomainItemProvider.class);

		// Verify
		assertNotNull(resourceSetAdapter);
		assertTrue(resourceSetAdapter.getTarget() instanceof ResourceSet);
		assertNotNull(resourceAdapter);
		assertTrue(resourceAdapter.getTarget() instanceof Resource);
	}

	
	@Test
	void shouldCreateDecoratedResourceSetItemProvider() {
		DecoratedResourceItemProviderAdapterFactory provider = new DecoratedResourceItemProviderAdapterFactory();
		IItemProviderDecorator decorator = provider.createItemProviderDecorator(new ResourceSetImpl(),
				IEditingDomainItemProvider.class);
		assertTrue(decorator instanceof DecoratedResourceSetItemProvider);
	}

	@Test
	void shouldNotCreateResourceSetProviderWrongTarget() {
		DecoratedResourceItemProviderAdapterFactory provider = new DecoratedResourceItemProviderAdapterFactory();
		IItemProviderDecorator decorator = provider.createItemProviderDecorator(new XMIResourceImpl(),
				IEditingDomainItemProvider.class);
		assertFalse(decorator instanceof DecoratedResourceSetItemProvider);
	}

	@Test
	void shouldNotCreateResourceSetProviderWrongType() {
		DecoratedResourceItemProviderAdapterFactory provider = new DecoratedResourceItemProviderAdapterFactory();
		IItemProviderDecorator decorator = provider.createItemProviderDecorator(new ResourceSetImpl(),
				ITreeItemContentProvider.class);
		assertFalse(decorator instanceof DecoratedResourceSetItemProvider);
	}

	@Test
	void shouldCreateDecoratedResourceItemProvider() {
		DecoratedResourceItemProviderAdapterFactory provider = new DecoratedResourceItemProviderAdapterFactory();
		IItemProviderDecorator decorator = provider.createItemProviderDecorator(new ResourceImpl(),
				IEditingDomainItemProvider.class);
		assertTrue(decorator instanceof DecoratedResourceItemProvider);
	}

	@Test
	void shouldNotCreateResourceProviderWrongTarget() {
		DecoratedResourceItemProviderAdapterFactory provider = new DecoratedResourceItemProviderAdapterFactory();
		IItemProviderDecorator decorator = provider.createItemProviderDecorator(new ResourceSetImpl(),
				IEditingDomainItemProvider.class);
		assertFalse(decorator instanceof DecoratedResourceItemProvider);
	}

	@Test
	void shouldNotCreateResourceProviderWrongType() {
		DecoratedResourceItemProviderAdapterFactory provider = new DecoratedResourceItemProviderAdapterFactory();
		IItemProviderDecorator decorator = provider.createItemProviderDecorator(new ResourceImpl(),
				ITreeItemContentProvider.class);
		assertFalse(decorator instanceof DecoratedResourceItemProvider);
	}
}

package us.coastalhacking.corvus.emf.provider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

class ItemProviderAdapterFactoryBaseTest {

	// Mock
	//
	ItemProviderAdapterFactoryBase provider;
	ComposedAdapterFactory mockRoot;

	public interface DisposableAdapter extends IDisposable, Adapter {
	};

	DisposableAdapter mockDisposableAdapter;
	Notifier mockNotifier;
	EList<Adapter> mockAdapters;
	INotifyChangedListener mockChangeListener;
	IChangeNotifier mockChangeNotifier;
	Notification mockNotification;

	@SuppressWarnings("unchecked")
	@BeforeEach
	void subBeforeEach() throws Exception {
		// Mock
		//
		provider = new ItemProviderAdapterFactoryBase();

		mockRoot = mock(ComposedAdapterFactory.class);
		when(mockRoot.getRootAdapterFactory()).thenReturn(mockRoot);
		mockDisposableAdapter = mock(DisposableAdapter.class);
		mockAdapters = (EList<Adapter>) mock(EList.class);
		mockNotifier = mock(Notifier.class);
		when(mockNotifier.eAdapters()).thenReturn(mockAdapters);
		mockChangeListener = mock(INotifyChangedListener.class);
		mockChangeNotifier = mock(IChangeNotifier.class);
		provider.changeNotifier = mockChangeNotifier;
		mockNotification = mock(Notification.class);
	}

	@Test
	void shouldGetRootAdapterFactory() {
		// Prep
		provider.parentAdapterFactory = mockRoot;

		// Execute
		ComposeableAdapterFactory actual = provider.getRootAdapterFactory();

		// Verify
		assertEquals(mockRoot, actual);
	}

	@Test
	void shouldSetParentAdapterFactory() {
		// Execute
		provider.setParentAdapterFactory(mockRoot);

		// Verify
		assertEquals(mockRoot, provider.parentAdapterFactory);
	}

	@Test
	void shouldAddListener() {
		// Execute
		provider.addListener(mockChangeListener);

		// Verify
		verify(mockChangeNotifier, times(1)).addListener(eq(mockChangeListener));
	}

	@Test
	void shouldRemoveListener() {
		// Execute
		provider.removeListener(mockChangeListener);

		// Verify
		verify(mockChangeNotifier, times(1)).removeListener(eq(mockChangeListener));
	}

	@Test
	void shouldFireNotifyChanged() {
		// Prep
		provider.parentAdapterFactory = mockRoot;

		// Execute
		provider.fireNotifyChanged(mockNotification);

		// Verify
		verify(mockChangeNotifier, times(1)).fireNotifyChanged(eq(mockNotification));
		verify(mockRoot, times(1)).fireNotifyChanged(eq(mockNotification));
	}

	@Test
	void shouldAssociate() {
		// Prep
		ArgumentCaptor<Adapter> adapterCaptor = ArgumentCaptor.forClass(Adapter.class);

		// Execute
		provider.associate(mockDisposableAdapter, mockNotifier);

		// Verify
		assertTrue(provider.disposable.contains(mockDisposableAdapter));
		verify(mockAdapters, times(1)).add(adapterCaptor.capture());

		Adapter actual = adapterCaptor.getValue();
		assertEquals(mockDisposableAdapter, actual);
	}

	@Test
	void shouldDispose() {
		// Prep
		provider.disposable.add(mockDisposableAdapter);

		// Execute
		provider.dispose();

		// Verify
		assertFalse(provider.disposable.contains(mockDisposableAdapter));
	}
}

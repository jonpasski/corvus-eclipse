package us.coastalhacking.corvus.test.util;

import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

// TODO fix leaky service trackers
public class TestUtils {

	// TODO return trackers and let test cases manage those
	public static <T> ServiceTracker<T, T> getTracker(BundleContext context, Class<T> clazz) throws Exception {
		return getTracker(context, clazz, "");
	}

	public static <T> ServiceTracker<T, T> getTracker(BundleContext context, Class<T> clazz, String filter)
			throws Exception {
		ServiceTracker<T, T> st;
		if (filter != null && !"".equals(filter)) {
			st = new ServiceTracker<>(context, context.createFilter(filter), null);
		} else {
			st = new ServiceTracker<>(context, clazz, null);
		}
		st.open();
		return st;
	}

	public static <T> T getService(BundleContext context, Class<T> clazz, long timeout) throws Exception {
		return getService(context, clazz, timeout, "");
	}

	public static <T> T getService(BundleContext context, Class<T> clazz, long timeout, String filter)
			throws Exception {
		ServiceTracker<T, T> st;
		if (filter != null && !"".equals(filter)) {
			st = new ServiceTracker<>(context, context.createFilter(filter), null);
		} else {
			st = new ServiceTracker<>(context, clazz, null);
		}
		st.open();
		return st.waitForService(timeout);
	}
}

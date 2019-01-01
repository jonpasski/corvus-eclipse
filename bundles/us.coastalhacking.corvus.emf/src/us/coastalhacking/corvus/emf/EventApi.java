package us.coastalhacking.corvus.emf;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.lang.model.SourceVersion;


public interface EventApi {

	/**
	 * OSGi event topic prefix (excluding topic separator)
	 */
	String PREFIX = "corvus";
	
	interface Properties {
		String SUPPORTED_TYPES = "corvus.event.supportedTypes";
	}

	interface Reference {
		String EVENT_FUNCTION = "corvus.event.function";
	}
	
	/**
	 * Based on general and custom EMF Notification event types
	 */
	enum EventType {
		SET(1), UNSET(2), ADD(3), REMOVE(4), ADD_MANY(5), REMOVE_MANY(6), MOVE(7), TOUCH(10), STAR(11);

		private int value;

		private EventType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}	
	
	// TODO: Refactor below methods into EventUtil{,Provider}
	//

	int MAX_SPLITS = 100;

	String U2A = "*";
	String U2E = "\\.";
	String U2F = "/";

	static void validatePrefix(String prefix) {
		if (!SourceVersion.isIdentifier(prefix)) {
			throw new IllegalArgumentException("Invalid prefix value");
		}
	}

	static void validateName(String name) {
		if (!SourceVersion.isName(name)) {
			throw new IllegalArgumentException("Invalid name value");
		}
	}

	static void validateMember(String member) {
		if (!SourceVersion.isIdentifier(member)) {
			throw new IllegalArgumentException("Invalid member value");
		}
	}
	
	static void validateEventType(String member) {
		if (!SourceVersion.isIdentifier(member)) {
			throw new IllegalArgumentException("Invalid member value");
		}
	}

	/**
	 * Generate an event topic
	 * 
	 * @param name      a Java class name
	 * @param member    a real or pseudo Java member on the class name
	 * @param eventType an event type
	 * @return a string suitable for OSGi event topics
	 */
	static String topic(String name, String member, EventType eventType) {
		validateName(name);
		validateMember(member);
		String type = (eventType == EventType.STAR) ? U2A : eventType.name();
		return String.join(U2F, PREFIX, name.replaceAll(U2E, U2F), member, type);
	}

	static String prefix(String topic) {
		String prefix = topic.split(U2F, MAX_SPLITS)[0];
		validatePrefix(prefix);
		return prefix;
	}

	static String name(String topic) {
		String[] split = topic.split(U2F, MAX_SPLITS);
		String name = IntStream.range(1, split.length - 2).mapToObj(i -> split[i]).collect(Collectors.joining(U2F));
		validateName(name);
		return name;
	}

	static String member(String topic) {
		String[] split = topic.split(U2F, MAX_SPLITS);
		String member = split[split.length - 2];
		validateMember(member);
		return member;
	}

	static EventType eventType(String topic) {
		String[] split = topic.split(U2F, MAX_SPLITS);
		String eventType = split[split.length - 1];
		eventType = U2A.equals(eventType) ? EventType.STAR.name() : eventType;
		return EventType.valueOf(eventType);
	}
}
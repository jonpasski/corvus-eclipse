package us.coastalhacking.corvus.emf.provider;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import us.coastalhacking.corvus.emf.EventApi;
import us.coastalhacking.corvus.emf.EventApi.EventType;

// TODO: move to a different test package
class EventApiTest {

	@Test
	void shouldValidatePrefix() {
		EventApi.validatePrefix("foobar");
	}

	@Test
	void shouldNotValidatePrefix() {
		assertThrows(IllegalArgumentException.class, () -> EventApi.validatePrefix("foo*bar"));
		assertThrows(IllegalArgumentException.class, () -> EventApi.validatePrefix("foo/bar"));
		assertThrows(IllegalArgumentException.class, () -> EventApi.validatePrefix("foo.bar"));
	}

	@Test
	void shouldValidateName() {
		EventApi.validateName("foo.is.a.Class");
	}

	@Test
	void shouldNotValidateName() {
		assertThrows(IllegalArgumentException.class, () -> EventApi.validateName("foo*bar"));
		assertThrows(IllegalArgumentException.class, () -> EventApi.validateName("foo/bar"));
	}

	@Test
	void shouldValidateMember() {
		EventApi.validateMember("iamamember");
		EventApi.validateMember("i_am_a_member");
		EventApi.validateMember("iamamember1");
	}

	@Test
	void shouldNotValidateMember() {
		assertThrows(IllegalArgumentException.class, () -> EventApi.validateMember("foo*bar"));
		assertThrows(IllegalArgumentException.class, () -> EventApi.validateMember("foo/bar"));
		assertThrows(IllegalArgumentException.class, () -> EventApi.validateMember("foo.bar"));
	}

	@Test
	void shouldReturnTopic() {
		String className = "foo.bar.Biz";
		String member = "baz";
		String template = "corvus/foo/bar/Biz/baz/%s";
		assertEquals(String.format(template, "SET"), EventApi.topic(className, member, EventType.SET));
		assertEquals(String.format(template, "UNSET"), EventApi.topic(className, member, EventType.UNSET));
		assertEquals(String.format(template, "ADD"), EventApi.topic(className, member, EventType.ADD));
		assertEquals(String.format(template, "ADD_MANY"), EventApi.topic(className, member, EventType.ADD_MANY));
		assertEquals(String.format(template, "REMOVE"), EventApi.topic(className, member, EventType.REMOVE));
		assertEquals(String.format(template, "REMOVE_MANY"), EventApi.topic(className, member, EventType.REMOVE_MANY));
		assertEquals(String.format(template, "MOVE"), EventApi.topic(className, member, EventType.MOVE));
		assertEquals(String.format(template, "TOUCH"), EventApi.topic(className, member, EventType.TOUCH));
		assertEquals(String.format(template, "*"), EventApi.topic(className, member, EventType.STAR));
	}

	@Test
	void shouldNotReturnTopic() {
		assertThrows(IllegalArgumentException.class,
				() -> EventApi.topic("valid.clazz.Name", "invalid.member", EventType.STAR));
		assertThrows(IllegalArgumentException.class,
				() -> EventApi.topic("invalid.class.Name", "valid_member", EventType.STAR));
	}

	@Test
	void shouldPrefix() {
		String expectedName = "foobar";
		assertEquals(expectedName, EventApi.prefix("foobar/biz/*"));
		assertEquals(expectedName, EventApi.prefix("foobar/*"));
		assertEquals(expectedName, EventApi.prefix("foobar/"));
		assertEquals(expectedName, EventApi.prefix("foobar"));
	}

	@Test
	void shouldNotPrefix() {
		assertThrows(IllegalArgumentException.class, () -> EventApi.prefix("foo.bar"));
		assertThrows(IllegalArgumentException.class, () -> EventApi.prefix("foo*bar"));
		assertThrows(IllegalArgumentException.class, () -> EventApi.prefix("foo.bar/biz/*"));
		assertThrows(IllegalArgumentException.class, () -> EventApi.prefix("foo*bar/biz/*"));
	}

	@Test
	void shouldName() {
		assertEquals("foo.bar.Biz", EventApi.name("prefix/foo.bar.Biz/member/SET"));
		assertEquals("foo.bar.Biz$Baz", EventApi.name("prefix/foo.bar.Biz$Baz/member/SET"));
		assertEquals("foobarBiz", EventApi.name("prefix/foobarBiz/member/SET"));
	}

	@Test
	void shouldNotName() {
		assertThrows(IllegalArgumentException.class, () -> EventApi.name("prefix/foo.bar*Biz/member/SET"));
		assertThrows(IllegalArgumentException.class, () -> EventApi.name("prefix//member/SET"));
		assertThrows(IllegalArgumentException.class, () -> EventApi.name("prefix/member/SET"));
	}

	@Test
	void shouldMember() {
		assertEquals("member", EventApi.member("prefix/foo.bar.Biz/member/SET"));
		assertEquals("mem_ber", EventApi.member("prefix/foo.bar.Biz/mem_ber/SET"));
		assertEquals("member1", EventApi.member("prefix/foo.bar.Biz/member1/SET"));
	}

	@Test
	void shouldNotMember() {
		assertThrows(IllegalArgumentException.class, () -> EventApi.member("prefix/foo.bar.Biz//SET"));
		assertThrows(IllegalArgumentException.class, () -> EventApi.member("prefix/foo.bar.Biz/SET"));
		assertThrows(IllegalArgumentException.class, () -> EventApi.member("prefix/foo.bar.Biz/mem*ber/SET"));
		assertThrows(IllegalArgumentException.class, () -> EventApi.member("prefix/foo.bar.Biz/mem.ber/SET"));
	}

	@Test
	void shouldHaveValidInt() {
		assertEquals(1, EventType.SET.getValue());
		assertEquals(2, EventType.UNSET.getValue());
		assertEquals(3, EventType.ADD.getValue());
		assertEquals(5, EventType.ADD_MANY.getValue());
		assertEquals(4, EventType.REMOVE.getValue());
		assertEquals(6, EventType.REMOVE_MANY.getValue());
		assertEquals(7, EventType.MOVE.getValue());
		assertEquals(10, EventType.TOUCH.getValue());
		assertEquals(11, EventType.STAR.getValue());
	}

	@Test
	void shouldEventType() {
		assertEquals(EventType.SET, EventApi.eventType("prefix/foo.bar.Biz/member/SET"));
		assertEquals(EventType.UNSET, EventApi.eventType("prefix/foo.bar.Biz/member/UNSET"));
		assertEquals(EventType.ADD, EventApi.eventType("prefix/foo.bar.Biz/member/ADD"));
		assertEquals(EventType.REMOVE, EventApi.eventType("prefix/foo.bar.Biz/member/REMOVE"));
		assertEquals(EventType.ADD_MANY, EventApi.eventType("prefix/foo.bar.Biz/member/ADD_MANY"));
		assertEquals(EventType.REMOVE_MANY, EventApi.eventType("prefix/foo.bar.Biz/member/REMOVE_MANY"));
		assertEquals(EventType.TOUCH, EventApi.eventType("prefix/foo.bar.Biz/member/TOUCH"));
		assertEquals(EventType.STAR, EventApi.eventType("prefix/foo.bar.Biz/member/*"));
		assertEquals(EventType.STAR, EventApi.eventType("prefix/foo.bar.Biz/*"));
		assertEquals(EventType.STAR, EventApi.eventType("prefix/*"));
		assertEquals(EventType.STAR, EventApi.eventType("prefix//*"));
	}

	@Test
	void shouldNotEventType() {
		assertThrows(IllegalArgumentException.class, () -> EventApi.eventType("prefix/foo.bar.Biz/member/CREATE"));
		assertThrows(IllegalArgumentException.class,
				() -> EventApi.eventType("prefix/foo.bar.Biz/member/REMOVING_ADAPTER"));
		assertThrows(IllegalArgumentException.class, () -> EventApi.eventType("prefix/foo.bar.Biz/member/RESOLVE"));
		assertThrows(IllegalArgumentException.class, () -> EventApi.eventType("prefix/foo.bar.Biz/member/"));
		assertThrows(IllegalArgumentException.class, () -> EventApi.eventType("prefix/foo.bar.Biz/member"));
	}

}

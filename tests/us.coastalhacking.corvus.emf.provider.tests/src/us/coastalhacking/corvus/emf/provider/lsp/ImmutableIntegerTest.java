package us.coastalhacking.corvus.emf.provider.lsp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import us.coastalhacking.corvus.emf.lsp.Range;
import us.coastalhacking.corvus.emf.provider.lsp.ImmutableRange.ImmutableInteger;

class ImmutableIntegerTest {

	Integer startChar;
	Integer startLine;
	Integer endChar;
	Integer endLine;
	ImmutableRange<Integer> provider;

	@BeforeEach
	void subBeforeEach() {
		startLine = 3;
		startChar = 2;
		endChar = 1;
		endLine = 5;
		provider = new ImmutableInteger(startChar, startLine, endChar, endLine);
	}

	@Test
	void shouldGetStartCharacter() {
		// Verify
		assertEquals(startChar, provider.getStartCharacter());
	}

	@Test
	void shouldGetStartLine() {
		// Verify
		assertEquals(startLine, provider.getStartLine());
	}

	@Test
	void shouldGetEndCharacter() {
		// Verify
		assertEquals(endChar, provider.getEndCharacter());
	}

	@Test
	void shouldGetEndLine() {
		// Verify
		assertEquals(endLine, provider.getEndLine());
	}

	@Test
	void shouldEqual() {
		// Prep
		ImmutableInteger other = new ImmutableInteger(startChar, startLine, endChar, endLine);

		// Execute
		boolean actual = provider.equals(other);

		// Verify
		assertTrue(actual);

	}

	public abstract class TestNumber extends Number implements Comparable<TestNumber> {
		private static final long serialVersionUID = 1L;
	}

	@SuppressWarnings("unchecked")
	@Test
	void shouldConvert() {
		// Prep
		TestNumber mockNumber = mock(TestNumber.class);
		Integer expected = 42;
		when(mockNumber.intValue()).thenReturn(expected);
		Range<TestNumber> other = mock(Range.class);
		when(other.getStartCharacter()).thenReturn(mockNumber);
		when(other.getStartLine()).thenReturn(mockNumber);
		when(other.getEndCharacter()).thenReturn(mockNumber);
		when(other.getEndLine()).thenReturn(mockNumber);

		// Execute
		Range<Integer> actual = provider.convert(other);

		// Verify
		verify(mockNumber, times(4)).intValue();
		verify(other, times(1)).getStartCharacter();
		verify(other, times(1)).getStartLine();
		verify(other, times(1)).getEndCharacter();
		verify(other, times(1)).getEndLine();
		assertEquals(expected, actual.getStartCharacter());
		assertEquals(expected, actual.getStartLine());
		assertEquals(expected, actual.getEndCharacter());
		assertEquals(expected, actual.getEndLine());
		
	}
}

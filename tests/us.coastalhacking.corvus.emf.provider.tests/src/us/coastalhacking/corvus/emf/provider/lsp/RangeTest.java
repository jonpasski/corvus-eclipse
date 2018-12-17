package us.coastalhacking.corvus.emf.provider.lsp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import us.coastalhacking.corvus.emf.lsp.Range;

// TODO: move to the non-existent emf.tests bundle
class RangeTest {

	Range<Integer> thisRange;
	Range<Integer> otherRange;
	Integer thisEndLine;
	Integer otherEndLine;
	Integer thisEndChar;
	Integer otherEndChar;
	Integer thisStartLine;
	Integer otherStartLine;
	Integer thisStartChar;
	Integer otherStartChar;

	@SuppressWarnings("unchecked")
	@BeforeEach
	void beforeRange() {
		thisRange = mock(Range.class);
		otherRange = mock(Range.class);

		thisEndLine = otherEndLine = 6;
		thisEndChar = otherEndChar = 5;
		thisStartLine = otherStartLine = 4;
		thisStartChar = otherStartChar = 3;
		when(thisRange.compareTo(any())).thenCallRealMethod();
		when(thisRange.getEndLine()).thenReturn(thisEndLine);
		when(thisRange.getEndCharacter()).thenReturn(thisEndChar);
		when(thisRange.getStartLine()).thenReturn(thisStartLine);
		when(thisRange.getStartCharacter()).thenReturn(thisStartChar);

		when(otherRange.getEndLine()).thenReturn(otherEndLine);
		when(otherRange.getEndCharacter()).thenReturn(otherEndChar);
		when(otherRange.getStartLine()).thenReturn(otherStartLine);
		when(otherRange.getStartCharacter()).thenReturn(otherStartChar);

	}

	@Test
	void shouldCompareEquals() {
		// Execute
		int actual = thisRange.compareTo(thisRange);

		// Verify
		assertEquals(0, actual);
	}

	@Test
	void shouldCompareGreaterEndLine() {
		// Prep
		Integer expected = otherEndLine + 1;
		when(thisRange.getEndLine()).thenReturn(expected);

		// Execute
		int actual = thisRange.compareTo(otherRange);

		// Verify
		assertEquals(1, actual);
	}

	@Test
	void shouldCompareLessEndLine() {
		// Prep
		Integer expected = otherEndLine - 1;
		when(thisRange.getEndLine()).thenReturn(expected);

		// Execute
		int actual = thisRange.compareTo(otherRange);

		// Verify
		assertEquals(-1, actual);
	}

	@Test
	void shouldCompareGreaterEndChar() {
		// Prep
		Integer expected = otherEndChar + 1;
		when(thisRange.getEndCharacter()).thenReturn(expected);

		// Execute
		int actual = thisRange.compareTo(otherRange);

		// Verify
		assertEquals(1, actual);
	}

	@Test
	void shouldCompareLessEndChar() {
		// Prep
		Integer expected = otherEndChar - 1;
		when(thisRange.getEndCharacter()).thenReturn(expected);

		// Execute
		int actual = thisRange.compareTo(otherRange);

		// Verify
		assertEquals(-1, actual);
	}

	@Test
	void shouldCompareGreaterStartLine() {
		// Prep
		Integer expected = otherStartLine + 1;
		when(thisRange.getStartLine()).thenReturn(expected);

		// Execute
		int actual = thisRange.compareTo(otherRange);

		// Verify
		assertEquals(1, actual);
	}

	@Test
	void shouldCompareLessStartLine() {
		// Prep
		Integer expected = otherStartLine - 1;
		when(thisRange.getStartLine()).thenReturn(expected);

		// Execute
		int actual = thisRange.compareTo(otherRange);

		// Verify
		assertEquals(-1, actual);
	}

	@Test
	void shouldCompareGreaterStartChar() {
		// Prep
		Integer expected = otherStartChar + 1;
		when(thisRange.getStartCharacter()).thenReturn(expected);

		// Execute
		int actual = thisRange.compareTo(otherRange);

		// Verify
		assertEquals(1, actual);
	}

	@Test
	void shouldCompareLessStartChar() {
		// Prep
		Integer expected = otherStartChar - 1;
		when(thisRange.getStartCharacter()).thenReturn(expected);

		// Execute
		int actual = thisRange.compareTo(otherRange);

		// Verify
		assertEquals(-1, actual);
	}
}

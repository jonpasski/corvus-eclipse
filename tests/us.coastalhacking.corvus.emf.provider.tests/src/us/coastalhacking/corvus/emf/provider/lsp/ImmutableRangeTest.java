package us.coastalhacking.corvus.emf.provider.lsp;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import us.coastalhacking.corvus.emf.lsp.Range;

class ImmutableRangeTest {
	ImmutableRange<Integer> thisProvider;
	ImmutableRange<Integer> otherProvider;

	@BeforeEach
	void subBeforeEach() {
		thisProvider = mockRange(1, 2, 3, 4);
		otherProvider = mockRange(1, 2, 3, 4);
	}

	ImmutableRange<Integer> mockRange(Integer startChar, Integer startLine, Integer endChar, Integer endLine) {
		ImmutableRange<Integer> result = new ImmutableRange<Integer>(startChar, startLine, endChar, endLine) {

			@Override
			public Class<Integer> getRangeType() {
				return Integer.class;
			}

			@Override
			public Range<Integer> convert(Range<?> other) {
				// Not used
				return null;
			}
			
		};
		return result;
	}

	@Test
	void shouldEqual() {
		// Prep
		// Execute
		boolean actual = thisProvider.equals(otherProvider);

		// Verify
		assertTrue(actual);
	}

}

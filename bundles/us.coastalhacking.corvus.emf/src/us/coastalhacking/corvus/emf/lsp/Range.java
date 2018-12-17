package us.coastalhacking.corvus.emf.lsp;

public interface Range<N extends Number & Comparable<N>> extends Comparable<Range<N>> {
	// 0-relative, inclusive
	N getStartCharacter();

	// 0-relative, inclusive
	N getStartLine();

	// 0-relative, exclusive
	N getEndCharacter();

	// 0-relative, exclusive
	N getEndLine();
	
	Class<N> getRangeType();
	
	Range<N> convert (Range<?> other);
	
	@Override
	default int compareTo(Range<N> other) {
		Integer result = null;
		if (this.equals(other)) {
			result = 0;
		} else {
			int endLineCompare = this.getEndLine().compareTo(other.getEndLine());
			if (endLineCompare != 0) {
				result = endLineCompare;
			} else {
				int endCharResult = this.getEndCharacter().compareTo(other.getEndCharacter());
				if (endCharResult != 0) {
					result = endCharResult;
				} else {
					int startLineCompare = this.getStartLine().compareTo(other.getStartLine());
					if (startLineCompare != 0) {
						result = startLineCompare;
					} else {
						result = this.getStartCharacter().compareTo(other.getStartCharacter());
					}
				}
			}
		}
		return result;
	}
}

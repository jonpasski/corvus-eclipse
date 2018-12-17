package us.coastalhacking.corvus.emf.provider.lsp;

import us.coastalhacking.corvus.emf.lsp.Range;

public abstract class ImmutableRange<N extends Number & Comparable<N>> implements Range<N> {

	protected final N startCharacter;
	protected final N startLine;
	protected final N endCharacter;
	protected final N endLine;

	public ImmutableRange(N startChar, N startLine, N endChar, N endLine) {
		this.startCharacter = startChar;
		this.startLine = startLine;
		this.endCharacter = endChar;
		this.endLine = endLine;
	}

	public N getStartCharacter() {
		return startCharacter;
	}

	public N getStartLine() {
		return startLine;
	}

	public N getEndCharacter() {
		return endCharacter;
	}

	public N getEndLine() {
		return endLine;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endCharacter == null) ? 0 : endCharacter.hashCode());
		result = prime * result + ((endLine == null) ? 0 : endLine.hashCode());
		result = prime * result + ((startCharacter == null) ? 0 : startCharacter.hashCode());
		result = prime * result + ((startLine == null) ? 0 : startLine.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ImmutableRange<?> other = (ImmutableRange<?>) obj;
		if (endCharacter == null) {
			if (other.endCharacter != null) {
				return false;
			}
		} else if (!endCharacter.equals(other.endCharacter)) {
			return false;
		}
		if (endLine == null) {
			if (other.endLine != null) {
				return false;
			}
		} else if (!endLine.equals(other.endLine)) {
			return false;
		}
		if (startCharacter == null) {
			if (other.startCharacter != null) {
				return false;
			}
		} else if (!startCharacter.equals(other.startCharacter)) {
			return false;
		}
		if (startLine == null) {
			if (other.startLine != null) {
				return false;
			}
		} else if (!startLine.equals(other.startLine)) {
			return false;
		}
		return true;
	}

	public static class ImmutableInteger extends ImmutableRange<Integer>  {

		public ImmutableInteger(Integer startChar, Integer startLine, Integer endChar, Integer endLine) {
			super(startChar, startLine, endChar, endLine);
		}

		@Override
		public Class<Integer> getRangeType() {
			return Integer.class;
		}

		@Override
		public ImmutableRange<Integer> convert(Range<?> other) {
			return new ImmutableInteger(other.getStartCharacter().intValue(), other.getStartLine().intValue(), other.getEndCharacter().intValue(), other.getEndLine().intValue());
		}

	}
}

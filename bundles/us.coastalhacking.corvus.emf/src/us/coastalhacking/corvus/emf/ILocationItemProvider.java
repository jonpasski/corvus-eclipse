package us.coastalhacking.corvus.emf;

import java.util.Optional;

/**
 * 
 * @see <a href="https://microsoft.github.io/language-server-protocol/specification">Language Server Protocol specification</a>
 */
public interface ILocationItemProvider {

	// 0-relative, inclusive
	Optional<Integer> getStartCharacter(Object object);

	// 0-relative, inclusive
	Optional<Integer> getStartLine(Object object);

	// 0-relative, exclusive
	Optional<Integer> getEndCharacter(Object object);

	// 0-relative, exclusive
	Optional<Integer> getEndLine(Object object);

	Optional<String> getDocumentUri(Object object);
}

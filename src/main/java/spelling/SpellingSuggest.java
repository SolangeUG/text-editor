package spelling;

import java.util.List;

/**
 * Spelling suggestions interface
 * @author UC San Diego Intermediate MOOC team
 */
public interface SpellingSuggest {

	/**
	 * Return a list of spelling suggestions from a string
	 * @param word the string to provide suggestions for
	 * @param numSuggestions the number of suggestions requested
	 * @return a list of spelling suggestions for the word
	 */
	List<String> suggestions(String word, int numSuggestions);
	
}

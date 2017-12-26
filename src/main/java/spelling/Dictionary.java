package spelling;

/**
 * Dictionary interface, representing and old school word-lookup dictionary
 * @author Christine Alvarado
 *
 */
public interface Dictionary {

	/**
	 * Add this word to the dictionary.
	 * @param word The word to add
	 * @return true if the word was added to the dictionary 
	 * 		   (it wasn't already there).
	 */
	boolean addWord(String word);

	/**
	 * Is this a word according to this dictionary?
	 * @param s the word to test
	 * @return true if s is in this dictionary
	 * 		   false otherwise
	 */
	boolean isWord(String s);
	
	/**
	 * Return the number of words in the dictionary
	 * @return the size of this dictionary
	 */
	int size();
	
}

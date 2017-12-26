package spelling;

import java.util.TreeSet;

/**
 * TreeSet implementation of a dictionary
 * @author UC San Diego Intermediate MOOC team
 * @author Solange U. Gasengayire
 */
public class DictionaryBST implements Dictionary {

    private TreeSet<String> dict;

    /**
     * Constructor
     */
    public DictionaryBST() {
        this.dict = new TreeSet<>();
    }

    /**
     * Add this word to the dictionary.
     * Convert it to lowercase first for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     *         (it wasn't already there).
     */
    public boolean addWord(String word) {
    	// DONE: Implement this method
        boolean result = false;
        if (! this.isWord(word)) {
            result = dict.add(word.toLowerCase());
        }
        return result;
    }

    /**
     * Return the number of words in the dictionary
     * @return the size of this dictionary
     */
    public int size() {
    	// DONE: Implement this method
        return dict.size();
    }

    /**
     * Is this a word according to this dictionary?
     * @param s the word to check
     * @return true if this word is in the dictionary
     *         false otherwise
     */
    public boolean isWord(String s) {
    	// DONE: Implement this method
        boolean result = false;
        if (s != null && ! s.isEmpty()) {
            result = dict.contains(s.toLowerCase());
        }
        return result;
    }

}

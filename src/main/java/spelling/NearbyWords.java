package spelling;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;


/**
 * Spelling suggestions class
 * @author UC San Diego Intermediate MOOC team
 * @author Solange U. Gasengayire
 *
 */
public class NearbyWords implements SpellingSuggest {
	// THRESHOLD to determine how many words to look through when looking
	// for spelling suggestions (stops prohibitively long searching)
	// For use in the Optional Optimization in Part 2.
	private static final int THRESHOLD = 1000; 

	Dictionary dict;

	/**
	 * Constructor
	 * @param dict the dictionary to use for spelling suggestions
	 */
	public NearbyWords (Dictionary dict) {
		this.dict = dict;
	}

	/**
	 * Return the list of Strings that are one modification away from the input string.
	 * @param s The original String
	 * @param wordsOnly controls whether to return only words or any String
	 * @return list of Strings which are nearby the original string
	 */
	public List<String> distanceOne(String s, boolean wordsOnly )  {
		List<String> retList = new ArrayList<>();
		insertions(s, retList, wordsOnly);
		substitution(s, retList, wordsOnly);
		deletions(s, retList, wordsOnly);
		return retList;
	}

	
	/**
	 * Add to the currentList Strings that are one character mutation away from the input string.
	 * @param s The original String
	 * @param currentList is the list of words to append modified words 
	 * @param wordsOnly controls whether to return only words or any String
	 */
	public void substitution(String s, List<String> currentList, boolean wordsOnly) {
		// for each letter in the s and for all possible replacement characters
		for(int index = 0; index < s.length(); index++){
			for(int charCode = (int)'a'; charCode <= (int)'z'; charCode++) {
				// use StringBuilder for an easy interface to permuting the
				// letters in the String
				StringBuilder sb = new StringBuilder(s);
				sb.setCharAt(index, (char)charCode);

				// if the item isn't in the list, isn't the original string, and
				// (if wordsOnly is true) is a real word, add to the list
				if(!currentList.contains(sb.toString()) && 
						(!wordsOnly||dict.isWord(sb.toString())) &&
						!s.equals(sb.toString())) {
					currentList.add(sb.toString());
				}
			}
		}
	}
	
	/**
	 * Add to the currentList Strings that are one character insertion away from the input string.
	 * @param s The original String
	 * @param currentList is the list of words to append modified words 
	 * @param wordsOnly controls whether to return only words or any String
	 */
	public void insertions(String s, List<String> currentList, boolean wordsOnly ) {
		// DONE: Implement this method
		if (s != null && ! s.isEmpty()) {
			for (int index = 0; index <= s.length(); index++) {
				for (int charCode = (int)'a'; charCode <= (int)'z'; charCode++) {
					StringBuilder sb = new StringBuilder(s);
					sb.insert(index, (char)charCode);

					if (! currentList.contains(sb.toString())
							&& (! wordsOnly || dict.isWord(sb.toString()))
							&& (! s.equals(sb.toString()))) {
						currentList.add(sb.toString());
					}
				}
			}
		}
	}

	/**
	 * Add to the currentList Strings that are one character deletion away
	 * from the input string.  
	 * @param s The original String
	 * @param currentList is the list of words to append modified words 
	 * @param wordsOnly controls whether to return only words or any String
	 */
	public void deletions(String s, List<String> currentList, boolean wordsOnly ) {
		// DONE: Implement this method
		if (s != null && ! s.isEmpty()) {
			for (int index = 0; index < s.length(); index++) {
				StringBuilder sb = new StringBuilder(s);
				sb.deleteCharAt(index);
				if (! currentList.contains(sb.toString())
						&& (! wordsOnly || dict.isWord(sb.toString()))
						&& (! s.equals(sb.toString()))) {
					currentList.add(sb.toString());
				}
			}
		}
	}

	/**
	 * Add to the currentList Strings that are one character deletion away from the input string.
	 * @param word The misspelled word
	 * @param numSuggestions is the maximum number of suggestions to return 
	 * @return the list of spelling suggestions
	 */
	@Override
	public List<String> suggestions(String word, int numSuggestions) {
		// List of strings to explore
		List<String> queue = new LinkedList<>();

		// HashSet to avoid exploring the same string multiple times
		HashSet<String> visited = new HashSet<>();

		// List of words to return
		List<String> retList = new LinkedList<>();

		// Insert first node
		queue.add(word);
		visited.add(word);
		int i = 0;

		// Implement the remainder of this method, see assignment for algorithm
		while (! queue.isEmpty() && i < numSuggestions) {
			String head = queue.remove(0);
			List<String> neighbors = distanceOne(head, true);
			for (String neighbor: neighbors) {
				// Have we reached the maximum required number of suggestions ?
				if (i < numSuggestions && ! visited.contains(neighbor)) {
					visited.add(neighbor);
					queue.add(neighbor);
					// Is this neighbor a valid word in the dictionary?
					if (dict.isWord(neighbor)) {
						retList.add(neighbor);
						i++;
					}
				}
			}
		}

		return retList;

	}

	/**
	 * Test method
	 * @param args command-line arguments
	 */
    public static void main(String[] args) {
		// Basic testing code to get started
	    String word = "i";
	    // Pass NearbyWords any Dictionary implementation you prefer
	    Dictionary d = new DictionaryHashSet();

	    String filename = NearbyWords.class
							.getResource("/data/dict.txt")
			   				.getFile();

	    DictionaryLoader.loadDictionary(d, filename);
	    NearbyWords w = new NearbyWords(d);
	    List<String> l = w.distanceOne(word, true);
	    System.out.println("One away word Strings for for \""+word+"\" are:");
	    System.out.println(l+"\n");

	    word = "tailo";
	    List<String> suggest = w.suggestions(word, 10);
	    System.out.println("Spelling Suggestions for \""+word+"\" are:");
	    System.out.println(suggest);
    }

}

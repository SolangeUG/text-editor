package spelling;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author Solange U. Gasengayire
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;

	/**
	 * Constructor
	 */
	public AutoCompleteDictionaryTrie() {
		root = new TrieNode();
	}

	/**
	 * Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the 
	 * string to all lower case before you insert it. 
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie, as described outlined in the videos for this week.
	 * It should appropriately use existing nodes in the trie, only creating new
	 * nodes when necessary.
	 * E.g. If the word "no" is already in the trie, then adding the word "now"
	 * would add only one additional node (for the 'w').
	 *
	 * @param word the word to add to the dictionary
	 * @return true if the word was successfully added.
	 * 		   false if it already exists in the dictionary.
	 */
	public boolean addWord(String word) {
	    // DONE: Implement this method.
		boolean added = false;

		if (! isWord(word.toLowerCase())) {
			TrieNode current = root;
			char[] characters = word.toLowerCase().toCharArray();
			for (char c: characters) {
				TrieNode child = current.getChild(c);
				if (child == null) {
					child = current.insert(c);
				}
				current = child;
			}
			current.setEndsWord(true);
			this.size++;
			added = true;
		}
		return added;
	}
	
	/** 
	 * Return the number of words in the dictionary.
	 * This is NOT necessarily the same as the number of TrieNodes in the trie.
	 * @return the size of this dictionary.
	 */
	public int size() {
	    // DONE: Implement this method
	    return this.size;
	}
	
	
	/**
	 * Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week.
	 * @param s the word to check
	 * @return true if the word is in the dictionary
	 * 		   false otherwise
	 */
	@Override
	public boolean isWord(String s) {
	    // DONE: Implement this method
		boolean result = true;
		TrieNode current = root;

		char[] characters = s.toLowerCase().toCharArray();
		for (char c : characters) {
			TrieNode cNode = current.getChild(c);
			if (cNode == null) {
				result = false;
				break;
			}
			current = cNode;
		}

		if (result) {
			result = current != null && current.endsWord();
		}
		return result;
	}

	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */
	@Override
	public List<String> predictCompletions(String prefix, int numCompletions) {
		// DONE: Implement this method
    	// This method should implement the following algorithm:
    	// 1. Find the stem in the trie.  If the stem does not appear in the trie,
    	//    return an empty list
    	// 2. Once the stem is found, perform a breadth first search to generate completions
    	//    using the following algorithm:
    	//    Create a queue (LinkedList) and add the node that completes the stem to the back
    	//       of the list.
    	//    Create a list of completions to return (initially empty)
    	//    While the queue is not empty and you don't have enough completions:
    	//       remove the first Node from the queue
    	//       If it is a word, add it to the completions list
    	//       Add all of its child nodes to the back of the queue
    	// Return the list of completions

		List<String> completions = new ArrayList<>(numCompletions);
		TrieNode stem = getStem(prefix);

		if (stem != null) {
			Queue<TrieNode> queue = new LinkedList<>();
			queue.add(stem);
			int i = 0;
			while (! queue.isEmpty() && i < numCompletions) {
				TrieNode qNode = queue.remove();
				if (qNode.endsWord()) {
					completions.add(qNode.getText());
					i++;
				}
				queue.addAll(qNode.getChildren());
			}
		}

		return completions;
	}

	/**
	 * A pre-order traversal of this trie
	 */
 	public void printTree() {
 		printNode(root);
 	}
 	
 	/**
	 * Do a pre-order traversal from this node down
	 * @param curr the start node from which to print the trie
	 */
 	private void printNode(TrieNode curr) {
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}

	/**
	 * Return the node that corresponds to the given string
	 * @param prefix The string to look for
	 * @return The TrieNode for this string
	 * 		null if it doesn't exist in the trie
	 */
	private TrieNode getStem(String prefix) {
		TrieNode stem = null;
		TrieNode current = root;
		boolean found = true;

		char[] characters = prefix.toLowerCase().toCharArray();
		for (char c: characters) {
			TrieNode cNode = current.getChild(c);
			if (cNode == null) {
				found = false;
				break;
			}
			current = cNode;
		}

		if (found) {
			stem = current;
		}
		return stem;
	}
	
}
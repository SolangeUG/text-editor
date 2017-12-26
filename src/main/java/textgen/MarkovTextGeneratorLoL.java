package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team
 * @author Solange U. Gasengayire
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;

	/**
	 * Constructor
	 * @param generator initial generator
	 */
	public MarkovTextGeneratorLoL(Random generator) {
		wordList = new LinkedList<>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/**
	 * Train the generator by adding the sourceText
	 * @param sourceText initial source text
	 *
	 * The algorithm
	 * *************
	 * 		set "starter" to be the first word in the text
	 *		set "prevWord" to be starter
	 *		for each word "w" in the source text starting at the second word
	 *			check to see if "prevWord" is already a node in the list
	 * 				if "prevWord" is a node in the list
	 *					add "w" as a nextWord to the "prevWord" node
	 *				else
	 *					add a node to the list with "prevWord" as the node's word
	 *					add "w" as a nextWord to the "prevWord" node
	 *			set "prevWord" = "w"
	 *		add starter to be a next word for the last word in the source text.
	 */
	@Override
	public void train(String sourceText) {
		// DONE: Implement this method
		if (sourceText != null && ! sourceText.isEmpty()) {

			String[] words = sourceText.split("[ ]+");
			int size = words.length;
			starter = words[0];

			String prevWord = starter;
			for (int i = 1; i < size; i++) {
				ListNode wordNode = getNode(prevWord);
				wordNode.addNextWord(words[i]);
				prevWord = words[i];
			}

			// Add first word as the next word of the last word in the source text
			ListNode lastNode = getNode(words[size - 1]);
			lastNode.addNextWord(starter);
		}
	}
	
	/** 
	 * Generate the number of words requested.
	 * @param numWords size of the text to generate
	 * @return a string containing the exact number of words requested.
	 *
	 * The algorithm
	 * *************
	 * 		set "currWord" to be the starter word
	 *		set "output" to be ""
	 *		add "currWord" to output
	 *		while you need more words
	 *			find the "node" corresponding to "currWord" in the list
	 *			select a random word "w" from the "wordList" for "node"
	 *			add "w" to the "output"
	 *			set "currWord" to be "w"
	 *			increment number of words added to the list
	 */
	@Override
	public String generateText(int numWords) {
	    // DONE: Implement this method
		StringBuilder output = new StringBuilder();
		if (wordList.size() > 0 && numWords > 0) {

			String currWord = starter;
			output.append(currWord);

			int i = 1;
			while (i < numWords) {
				ListNode currNode = getNode(currWord);
				String randomWord = currNode.getRandomNextWord(rnGenerator);
				output.append(" ").append(randomWord);
				currWord = randomWord;
				i++;
			}

		}
		return output.toString();
	}

	/**
	 * Return a string representation of this class
	 * @return this class string representation
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (ListNode n : wordList) {
			builder.append(n.toString());
		}
		return builder.toString();
	}
	
	/**
	 * Retrain the generator from scratch on the source text
	 * @param sourceText the source text
	 */
	@Override
	public void retrain(String sourceText) {
		// DONE: Implement this method.
		starter = "";
		wordList.clear();
		train(sourceText);
	}

	/**
	 * From the wordList, get the node associated with a given word.
	 * If there's no node associated with the word, create one.
	 * @param word the word to be checked
	 * @return the corresponding node
	 */
	private ListNode getNode(String word) {
		ListNode wordNode = null;
		for (ListNode node: wordList) {
			if (node.getWord().equals(word)) {
				wordNode = node;
				break;
			}
		}
		if (wordNode == null) {
			wordNode = new ListNode(word);
			wordList.add(wordNode);
		}
		return wordNode;
	}
	
	/**
	 * This is a minimal set of tests.
	 * Note that it can be difficult to test methods/classes with randomized behavior.
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {

		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 =
				"You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/**
 * A class that links a word to the next words in the list.
 * You should use this class in your implementation.
 */
class ListNode {
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;

	/**
	 * Constructor
	 * @param word the word to link to the next words
	 */
	ListNode(String word) {
		this.word = word;
		nextWords = new LinkedList<>();
	}

	/**
	 * Get this list node word
	 * @return the word that links to the next words
	 */
	public String getWord() {
		return word;
	}

	/**
	 * Add a word to the list
	 * @param nextWord the word to add
	 */
	public void addNextWord(String nextWord) {
		nextWords.add(nextWord);
	}

	/**
	 * Return a random next word
	 * @param generator a random generator
	 * @return a computed random next word
	 */
	public String getRandomNextWord(Random generator) {
		// DONE: Implement this method
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
		int index = generator.nextInt(nextWords.size());
		return nextWords.get(index);
	}

	/**
	 * Return a string representation of this class
	 * @return a list node as a string
	 */
	public String toString() {
		StringBuilder listNodeStr = new StringBuilder(word + ": ");
		for (String s : nextWords) {
			listNodeStr.append(s).append("->");
		}
		listNodeStr.append("\n");
		return listNodeStr.toString();
	}
	
}



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
	 */
	@Override
	public void train(String sourceText) {
		// TODO: Implement this method
	}
	
	/** 
	 * Generate the number of words requested.
	 * @param numWords size of the text to generate
	 */
	@Override
	public String generateText(int numWords) {
	    // TODO: Implement this method
		return null;
	}

	/**
	 * Return a string representation of this class
	 * @return this class string representation
	 */
	@Override
	public String toString() {
		StringBuilder toReturn = new StringBuilder();
		for (ListNode n : wordList) {
			toReturn.append(n.toString());
		}
		return toReturn.toString();
	}
	
	/**
	 * Retrain the generator from scratch on the source text
	 * @param sourceText the source text
	 */
	@Override
	public void retrain(String sourceText) {
		// TODO: Implement this method.
	}
	
	// TODO: Add any private helper methods you need here.
	
	
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
		String textString2 = "You say yes, I say no, "+
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
		// TODO: Implement this method
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
	    return null;
	}

	/**
	 * Return a string representation of this class
	 * @return a list node as a string
	 */
	public String toString() {
		StringBuilder toReturn = new StringBuilder(word + ": ");
		for (String s : nextWords) {
			toReturn.append(s).append("->");
		}
		toReturn.append("\n");
		return toReturn.toString();
	}
	
}



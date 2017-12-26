package spelling;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * WPTree implements WordPath by dynamically creating a tree of words during a Breadth First
 * Search of Nearby words to create a path between two words. 
 * 
 * @author UC San Diego Intermediate MOOC team
 * @author Solange U. Gasengayire
 *
 */
public class WPTree implements WordPath {

	// this is the root node of the WPTree
	private WPTreeNode root;

	// used to search for nearby Words
	private NearbyWords nw; 

    /**
     * Return a new WPTree
     * NB: This constructor is used by the Text Editor Application
     *     You'll need to create your own NearbyWords object here.
     */
	public WPTree () {
		this.root = null;
		// DONE: initialize a NearbyWords object
		Dictionary d = new DictionaryHashSet();
        String filename = WPTree.class.getResource("/data/dict.txt").getFile();
		DictionaryLoader.loadDictionary(d, filename);
		this.nw = new NearbyWords(d);
	}

    /**
     * Return a new WPTree.
     * NB: This constructor will be used by the grader code
     * @param nw nearbyWords to initialize this WPTree with
     */
	public WPTree (NearbyWords nw) {
		this.root = null;
		this.nw = nw;
	}

    /**
     * Return a path from word1 to word2 through dictionary words with
     * the restriction that each step in the path can involve only a
     * single character mutation
     * @param word1 The first word
     * @param word2 The second word
     * @return list of Strings which are the path from word1 to word2
     *         including word1 and word2
     */
	public List<String> findPath(String word1, String word2) {
	    // DONE: Implement this method.
        List<String> path = new LinkedList<>();

        boolean precondition =
                word1 != null && ! word1.isEmpty()
                        && word2 != null && ! word2.isEmpty()
                        && nw.dict.isWord(word2);

        if (precondition) {

            List<WPTreeNode> nodes = new LinkedList<>();
            HashSet<String> visited = new HashSet<>();
            this.root = new WPTreeNode(word1, null);
            boolean found = false;

            nodes.add(this.root);
            visited.add(word1);

            while(! nodes.isEmpty() && ! found) {
                WPTreeNode current = nodes.remove(0);
                List<String> words = nw.distanceOne(current.getWord(), true);
                for (String word: words) {
                    if (! visited.contains(word)) {
                        WPTreeNode child = current.addChild(word);
                        visited.add(word);
                        nodes.add(child);
                        if (word.equals(word2)) {
                            path = child.buildPathToRoot();
                            found = true;
                            break;
                        }
                    }
                }
            }
        }

        return path;
	}

    /**
     * Return a String representation of a list of WPTreeNodes
     * @param list The input list of WPTreeNodes
     * @return The String representation of the input list
     */
	private String printQueue(List<WPTreeNode> list) {
		StringBuilder ret = new StringBuilder("[ ");
		for (WPTreeNode w : list) {
			ret.append(w.getWord()).append(", ");
		}
		ret.append("]");
		return ret.toString();
	}
	
}

/* Tree Node in a WordPath Tree. This is a standard tree with each
 * node having any number of possible children.
 * Each node should only contain a word in the dictionary and the
 * relationship between nodes is that a child is one character mutation
  *(deletion, insertion, or substitution) away from its parent.
*/
class WPTreeNode {
    
    private String word;
    private List<WPTreeNode> children;
    private WPTreeNode parent;
    
    /**
     * Construct a node with the word w and the parent p
     * (pass a null parent to construct the root)
	 * @param w The new node's word
	 * @param p The new node's parent
	 */
    WPTreeNode(String w, WPTreeNode p) {
        this.word = w;
        this.parent = p;
        this.children = new LinkedList<>();
    }
    
    /**
     * Add a child of a node containing the String s
     * Precondition: The word is not already a child of this node
     * @param s The child node's word
	 * @return The new WPTreeNode
	 */
    public WPTreeNode addChild(String s){
        WPTreeNode child = new WPTreeNode(s, this);
        this.children.add(child);
        return child;
    }
    
    /**
     * Get the list of children of the calling object
     * (pass a null parent to construct the root)
	 * @return List of WPTreeNode children
	 */
    public List<WPTreeNode> getChildren() {
        return this.children;
    }
   
    /**
     * Allows you to build a path from the root node to the calling object
     * @return The list of strings starting at the root and 
     *         ending at the calling object
	 */
    public List<String> buildPathToRoot() {
        WPTreeNode curr = this;
        List<String> path = new LinkedList<>();
        while(curr != null) {
            path.add(0,curr.getWord());
            curr = curr.parent; 
        }
        return path;
    }
    
    /**
     * Get the word for the calling object
	 * @return Getter for calling object's word
	 */
    public String getWord() {
        return this.word;
    }
    
    /**
     * toString method
	 * @return The string representation of a WPTreeNode
	 */
    public String toString() {
        StringBuilder ret = new StringBuilder("Word: " + word + ", parent = ");
        if(this.parent == null) {
           ret.append("null.\n");
        }
        else {
           ret.append(this.parent.getWord()).append("\n");
        }
        ret.append("[ ");
        for(WPTreeNode curr: children) {
            ret.append(curr.getWord()).append(", ");
        }
        ret.append(" ]\n");
        return ret.toString();
    }

}


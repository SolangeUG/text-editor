package spelling;

import java.io.PrintWriter;
import java.lang.StringBuilder;
import java.util.List;

/**
 * A grader class for the implementation of a dictionary as a trie
 * @author UC San Diego Intermediate MOOC team
 */
public class TrieGrader {

    private StringBuilder feedback;

    private TrieGrader() {
        feedback = new StringBuilder();
    }


    public static void main(String[] args) {
        TrieGrader g = new TrieGrader();

        PrintWriter out;
        try {
            out = new PrintWriter("output.out");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        try {
            AutoCompleteDictionaryTrie ac = new AutoCompleteDictionaryTrie();
            g.testAddWords(ac);
            g.testWordsInOut(ac);
            g.testPredictions(ac);
        } catch (Exception e) {
            out.println(g.getFeedback() + "Error during runtime: " + e);
            out.close();
            return;
        }

        StringBuilder feedback = g.getFeedback();
        out.println(feedback.toString());
        out.close();
    }


    private void testAddWords(AutoCompleteDictionaryTrie ac) {

        feedback.append( "//TESTING ADDING WORDS (addWord, insert)//");
        appendTestString(1, "Adding first word to dictionary...");
        feedback.append("addWord returned ").append(ac.addWord("dog")).append(".");

        appendTestString(2,"Adding two more words and testing size...");
        ac.addWord("downhill");
        ac.addWord("downhiller");

        feedback.append("Size is ").append(ac.size()).append(".");

        appendTestString(3, "Adding more words to dictionary trie (testing size after insertions)...");

        ac.addWord("doge");
        ac.addWord("dogg");
        ac.addWord("dawg");
        ac.addWord("dage");
        ac.addWord("doggo");
        ac.addWord("doggie");
        ac.addWord("doggos");
        ac.addWord("doggoes");
        ac.addWord("doggies");
        ac.addWord("test");
        ac.addWord("tester");
        ac.addWord("testing");
        ac.addWord("tested");
        ac.addWord("testin");
        ac.addWord("teston");
        ac.addWord("testone");
        ac.addWord("testine");
        ac.addWord("testell");
        ac.addWord("testcase");
        ac.addWord("testbase");
        ac.addWord("testcases");


        feedback.append("Dict size is ").append(ac.size()).append(".");

        // get current size before trying to add duplicate word

        appendTestString(4,"Adding duplicate word...");
        feedback.append("Adding duplicate word returned ").append(ac.addWord("dog")).append(".");

        appendTestString(5, "Checking size after try to add duplicate word...");
        feedback.append("Dict size is ").append(ac.size()).append(".");
    }

    private void testWordsInOut(AutoCompleteDictionaryTrie ac) {

        feedback.append("\n\n\n//TESTING FOR WORDS IN/OUT OF DICTIONARY (isWord)//");
        appendTestString(6,"Checking empty string...");
        // test empty string
        feedback.append("Empty string in dictionary: ").append(ac.isWord("")).append(".");

        appendTestString(7, "Checking for word in dictionary...");
        feedback.append("'doggoes' in dictionary: ").append(ac.isWord("doggoes")).append(".");

        // test word only missing last letter
        appendTestString(8, "Testing word only missing last letter...");
        feedback.append("'downhil' in dictionary: ").append(ac.isWord("downhil")).append(".");

        //test word with added letter
        appendTestString(9, "Testing word with one extra letter...");
        feedback.append("'downhille' in dictionary: ").append(ac.isWord("downhille")).append(".");

        appendTestString(10, "Testing for more words in dictionary...");
        feedback.append("'test' in dictionary: ").append(ac.isWord("test"))
                .append(". 'testcases' in dictionary: ").append(ac.isWord("testcases"))
                .append(". 'testone' in dictionary: ").append(ac.isWord("testone")).append(".");

        appendTestString(11, "Testing word with capital letters...");
        feedback.append("'TeSt' in dictionary: ").append(ac.isWord("TeSt")).append(".");

    }

    private void testPredictions(AutoCompleteDictionaryTrie ac) {

        feedback.append("\n\n\n//TESTING AUTO COMPLETE FUNCTIONALITY (predictCompletions)//");
        List<String> auto = ac.predictCompletions("dog", 3);

        appendTestString(12, "3 completions requested...");
        feedback.append("Autocomplete returned the following: ");
        for (String s : auto) {
            feedback.append(s).append(", ");
        }

        appendTestString(13,"Testing size of list...");
        feedback.append("predictCompletions returned ").append(auto.size()).append(" elements.");

        auto = ac.predictCompletions("soup", 6);
        appendTestString(14, "6 completions requested, 0 expected...");
        feedback.append("predictCompletions found ").append(auto.size()).append(" words.");

        auto = ac.predictCompletions("dogg", 10);
        appendTestString(15, "10 completions requested, 6 expected...");
        feedback.append("predictCompletions found ").append(auto.size()).append(" elements.");

        appendTestString(16, "Testing for correctness of 6 words...");
        feedback.append("Words returned by predictCompletions: ");
        for (String s : auto) {
            feedback.append(s).append(", ");
        }

        auto = ac.predictCompletions("test", 7);

        appendTestString(17, "7 completions requested (test for size)...");
        feedback.append("predictCompletions returned ").append(auto.size()).append(" elements.");

        appendTestString(18, "Testing if list is sorted from shortest to longest...");
        feedback.append("Check above output.");

        List<String> partialList = auto.subList(0, 5);

        appendTestString(19, "Testing if list contains correct shorter words...");
        feedback.append("Check above output.");


        appendTestString(20, "Testing for remaining words...");
        partialList = auto.subList(5, auto.size());

        int count = 0;

        count = partialList.contains("testone") ? ++count:count ;
        count = partialList.contains("testine") ? ++count:count;
        count = partialList.contains("testell") ? ++count:count;
        count = partialList.contains("testing") ? ++count:count;

        feedback.append("Out of 'testone', 'testine', 'testell', and 'testing', ")
                .append(count).append(" words were found.");

    }

    private void appendTestString(int num, String description) {
        feedback.append("\n\n** Test #").append(num).append(": ").append(description).append("\n");
    }

    private StringBuilder getFeedback() {
        return this.feedback;
    }


}


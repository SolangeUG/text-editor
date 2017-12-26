package spelling;

import java.util.List;
import java.util.ArrayList;
import java.io.PrintWriter;

/**
 * A grader class for NearbyWords implementation
 * @author UC San Diego Intermediate MOOC team
 */
public class NearbyWordsGraderOne {

    public static void main(String args[]) {

        StringBuilder feedback = new StringBuilder();
        PrintWriter out;

        try {
            out = new PrintWriter("grader_output/module5.part1.out");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        try {
            Dictionary d = new DictionaryHashSet();

            String fileName =
                    NearbyWordsGraderOne
                        .class
                        .getResource("/test_cases/dict.txt")
                        .getFile();

                    DictionaryLoader.loadDictionary(d, fileName);
            NearbyWords nw = new NearbyWords(d);

            List<String> d1 = nw.distanceOne("word", true);
            
            feedback.append("** Test 1: distanceOne list size... ");
            feedback.append("distanceOne returned ").append(d1.size()).append(" words.\n");

            feedback.append("** Test 2: distanceOne words returned... ");
            for (String i : d1) {
                feedback.append(i).append(", ");
            }

            feedback.append("\n** Test 3: distanceOne list size (allowing non-words)... ");
            d1 = nw.distanceOne("word", false);
            feedback.append("distanceOne with non-words returned ").append(d1.size()).append(" words.\n");
            
            d1 = new ArrayList<>();
            
            feedback.append("** Test 4: deletions list size... ");
            nw.deletions("makers", d1, true);
            feedback.append("deletions returned ").append(d1.size()).append(" words.\n");

            feedback.append("** Test 5: deletions words returned... ");
            feedback.append("deletions returned: ");
            for (String i : d1) {
                feedback.append(i).append(", ");
            }

            d1 = new ArrayList<>();

            feedback.append("\n** Test 6: insertions list size... ");
            nw.insertions("or", d1, true);
            feedback.append("insertions returned ").append(d1.size()).append(" words.\n");

            feedback.append("** Test 7: insertions words returned... ");
            feedback.append("insertions returned: ");
            for (String i : d1) {
                feedback.append(i).append(", ");
            }
            feedback.append("\n");
            
        } catch (Exception e) {
            out.println("Runtime error: " + e);
            return;
        }

        out.println(feedback + "Tests complete. Check that everything looks right.");
        out.close();
    }
}

package document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 * A grader class to test the EfficientDocument implementation
 * @author UC San Diego Intermediate Programming MOOC team
 * @author Solange U. Gasengayire
 */
public class EfficientDocumentGrader {

    public static void main(String[] args) {
        try {

            System.out.println("Sentences, words, and syllables:");

            String inputFile =
                    EfficientDocumentGrader.class
                            .getClass()
                            .getResource("/test_cases/mod2TestCases.txt")
                            .getFile();

            String line;
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            PrintWriter out = new PrintWriter("grader_output/module2.part1.out", "utf-8");

            while ((line = br.readLine()) != null) {
                EfficientDocument doc = new EfficientDocument(line);
                String result = doc.getNumSentences() + " " + doc.getNumWords() + " " + doc.getNumSyllables() + " ";
                System.out.print(result);
                out.print(result);
            }
            out.print("\n");
            out.close();
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

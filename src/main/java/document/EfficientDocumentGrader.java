package document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

public class EfficientDocumentGrader {
    public static void main(String[] args) {
        try
        {
            System.out.println("Sentences, words, and syllables:");

            String inputFile =
                    EfficientDocumentGrader.class
                            .getClass()
                            .getResource("/test_cases/mod2TestCases.txt")
                            .getFile();

            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            String line;
            PrintWriter out = new PrintWriter("grader_output/module2.part1.out", "utf-8");
            while ((line = br.readLine()) != null)
            {
                EfficientDocument doc = new EfficientDocument(line);
                String result = doc.getNumSentences() + " " + doc.getNumWords() + " " + doc.getNumSyllables() + " ";
                System.out.print(result);
                out.print(result);
            }
            out.print("\n");
            out.close();
            br.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

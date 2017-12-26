package document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

import document.BasicDocument;

public class BasicDocumentGrader {
    public static void main(String[] args) {
        try
        {
            System.out.println("Sentences, words, and syllables:");

            String inputFile =
                    BasicDocumentGrader
                            .class
                            .getResource("/test_cases/mod1TestCases.txt")
                            .getFile();

            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            String line;
            PrintWriter out = new PrintWriter("grader_output/module1.part1.out", "utf-8");
            while ((line = br.readLine()) != null)
            {
                BasicDocument doc = new BasicDocument(line);
                String result = doc.getNumSentences() + " " + doc.getNumWords() + " " + doc.getNumSyllables() + " ";
                System.out.print(result);
                out.print(result);
            }
            out.print("\n");
            out.close();
            System.out.println("\nFlesch scores:");
            br.close();

            br = new BufferedReader(new FileReader(inputFile));
            out = new PrintWriter("grader_output/module1.part2.out", "utf-8");
            while ((line = br.readLine()) != null)
            {
                BasicDocument doc = new BasicDocument(line);
                String result = doc.getFleschScore() + " ";
                System.out.print(result);
                out.print(result);
            }
            out.print("\n");
            out.close();
            System.out.print('\n');
            br.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

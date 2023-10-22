/**
 * Author: Keita Katsumi
 * Last update Oct 22. 2023
 * The program  is for super raters' verbatim detection check process.
 * It aims to reduce verbatim manual checking time and effort.
 * But it can check single row every time.
 *
 * The VerbatimCheck program do the three things
 *
 * 1. Read three text files: rewrite.txt, model.txt, and reference txt
 * All the text information come form raters google sheet manually.
 * Extract the text data as a String, then store each sentence to the ArrayList<String>
 *
 * 2. Compare raters' rewrite and model response.
 * The program check the number of consecutive letters in the rewrite are in the model response.
 * If it is detected, the program shows
 *   i. Consecutive letters,
 *   ii. Rewrite sentence which has the consecutive letters,
 *   iii. Model response sentence which has the consecutive letters.
 *
 *   3. Compare raters' rewrite and references.
 *   It takes the same process  of section 2.
 *
 *   If you would like to discuss the future application or any issues,
 *   please contact my google or GL email.
 *
 */



import UtilMethods.Tools;
import java.util.ArrayList;

public class Main {


    // Declaring the color for output to increase readability
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static void main(String[] args) {

//        //Test suite to check find over character method works properly
//        TestSuite.findOverlappingCharactersTest();

//        //Test suite to check whether the function can divide extracted data from the files
//        TestSuite.sliceJapaneseTextTest();

//        //Test suite to check the method detect consecutive letters and display the letters and rewrite and reference sentence.
//        TestSuite.displayDetectLettersAndSentencesTest();

        //Input process section 1
        //You can use your local device file path.
        String rewriteObj = Tools.readTextFromFile("/Users/keita-katsumi/Dropbox/Programing/GitHub/GL/VerbatimCheck/TestData/rewrite.txt");
        String modelObj = Tools.readTextFromFile("/Users/keita-katsumi/Dropbox/Programing/GitHub/GL/VerbatimCheck/TestData/model.txt");
        String refObj = Tools.readTextFromFile("/Users/keita-katsumi/Dropbox/Programing/GitHub/GL/VerbatimCheck/TestData/reference.txt");

        //Slice one long string  from the given files to a sentence string
        //Store each string sentence to the ArrayList to aggregate
        ArrayList<String> listOfRewrite = Tools.sliceJapaneseText(rewriteObj);
        ArrayList<String> listOfModel = Tools.sliceJapaneseText(modelObj);
        ArrayList<String> listOfreference= Tools.sliceJapaneseText(refObj);

        //Section 2
        //Comparing raters' rewrite and model and detect verbatim if there exist.
        System.out.println(ANSI_RED +  "\n\n - - - - - Compare your rewrite and model summary  - - - - - " + ANSI_RESET);
        Tools.displayDetectLettersAndSentences(listOfRewrite, listOfModel);

        //Section 3
        //Comparing raters' rewrite and reference and detect verbatim if there exist.
        System.out.println(ANSI_RED + "\n\n - - - - -  Compare your rewrite and reference - - - - - " + ANSI_RESET);
        Tools.displayDetectLettersAndSentences(listOfRewrite, listOfreference);


    }
}
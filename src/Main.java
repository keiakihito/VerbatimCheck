import UtilMethods.Tools;

import java.util.ArrayList;

public class Main {


    // Declaring the color
    // Custom declaration
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

        String rewriteObj = Tools.readTextFromFile("/Users/keita-katsumi/Dropbox/Programing/GitHub/GL/VerbatimCheck/TestData/rewrite.txt");
        String modelObj = Tools.readTextFromFile("/Users/keita-katsumi/Dropbox/Programing/GitHub/GL/VerbatimCheck/TestData/model.txt");
        String refObj = Tools.readTextFromFile("/Users/keita-katsumi/Dropbox/Programing/GitHub/GL/VerbatimCheck/TestData/reference.txt");

        //Slice one long string to a sentence string
        //Store each string sentence to the Array list to aggregate
        ArrayList<String> listOfRewrite = Tools.sliceJapaneseText(rewriteObj);
        ArrayList<String> listOfModel = Tools.sliceJapaneseText(modelObj);
        ArrayList<String> listOfreference= Tools.sliceJapaneseText(refObj);

        System.out.println(ANSI_RED +  "\n\n - - - - - Compare your rewrite and model summary  - - - - - " + ANSI_RESET);
        Tools.displayDetectLettersAndSentences(listOfRewrite, listOfModel);

        System.out.println(ANSI_RED + "\n\n - - - - -  Compare your rewrite and reference - - - - - " + ANSI_RESET);
        Tools.displayDetectLettersAndSentences(listOfRewrite, listOfreference);


    }
}
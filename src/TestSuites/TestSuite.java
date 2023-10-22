/*
* This test suites to ensure my sub methods.
* I  only use small amount of test case in this file,
* However, I used decent amount of actual case rewrites, modes, and reference from the task.
* I tend to amit them here to avoid leaking output source.
* */


package TestSuites;
import UtilMethods.*;

import java.util.ArrayList;

public class TestSuite {
    public static void  findOverlappingCharactersTest(){
        System.out.println("\n\n~~ findOverlappingCharactersTest ~~ \n\n");

        String strOfRewrite = "";
        String strOfModel = "";
        String verbatim = Tools.findOverlappingCharacters(strOfRewrite, strOfModel);


        strOfRewrite = "文章などの要点をとりまとめること。また、そのまとめたもの。「読んだ本の内容を―して話す」\n";
        strOfModel = "文章などの要点をとりまとめること。また、そのまとめたもの。「読んだ本の内容を―して話す」\n";
        verbatim = Tools.findOverlappingCharacters(strOfRewrite, strOfModel);

        System.out.println(verbatim);



        strOfRewrite = "また、そのまとめたもの。「読んだ本の内容を―して話す」";
        strOfModel = "文章などの要点をとりまとめること。また、そのまとめたもの。「読んだ本の内容を―して話す」";
        verbatim = Tools.findOverlappingCharacters(strOfRewrite, strOfModel);
        System.out.println(verbatim);


        strOfRewrite = "文章などの要点。そのまとめたもの";
        strOfModel = "文章などの要点をとりまとめること。また、そのまとめたもの。「読んだ本の内容を―して話す」";
        verbatim = Tools.findOverlappingCharacters(strOfRewrite, strOfModel);
        System.out.println(verbatim);


        strOfRewrite = "文章などの要点をとりまとめること。また、そのまとめたもの。「読んだ本の内容を―して話す」";
        strOfModel = "文章の要点をまとめたもの。";
        verbatim = Tools.findOverlappingCharacters(strOfRewrite, strOfModel);
        System.out.println(verbatim);



        strOfRewrite = "";
        strOfModel = "";
        verbatim = Tools.findOverlappingCharacters(strOfRewrite, strOfModel);
        System.out.println(verbatim);


        System.out.println("\n\n~~ findOverlappingCharactersTest passed ~~ \n\n");
    }

    public static void sliceJapaneseTextTest(){
        System.out.println("\n\n~~ sliceJapaneseTextTest ~~ \n\n");
        //Input
        //Extract file texts to the string
        String file1Content = Tools.readTextFromFile("/Users/keita-katsumi/Dropbox/GlobalLogic/Alchemist/VerbatimDetector/TestData/file1.txt");
        String file2Content = Tools.readTextFromFile("/Users/keita-katsumi/Dropbox/GlobalLogic/Alchemist/VerbatimDetector/TestData/file2.txt");

        //Slice one long string to a sentence string
        //Store each string sentence to the Array list to aggregate
        ArrayList<String> listOfRewrite = Tools.sliceJapaneseText(file1Content);
        ArrayList<String> listOfModel = Tools.sliceJapaneseText(file2Content);

        System.out.println("Extract data from file1 AKA rewrite: ");
        for (String sentence : listOfRewrite){
            System.out.println(sentence);
        }

        System.out.println("Extract data from file2 AKA rewrite: ");
        for (String sentence : listOfModel){
            System.out.println(sentence);
        }


        System.out.println("\n\n~~ sliceJapaneseTextTest passed  ~~ \n\n");


    }

    public static void displayDetectLettersAndSentencesTest(){
        System.out.println("\n\n~~ sliceJapaneseTextTest ~~ \n\n");
        //Input
        //Extract file texts to the string
        String rewriteObj = Tools.readTextFromFile("/Users/keita-katsumi/Dropbox/GlobalLogic/Alchemist/VerbatimDetector/TestData/rewrite.txt");
        String modelObj = Tools.readTextFromFile("/Users/keita-katsumi/Dropbox/GlobalLogic/Alchemist/VerbatimDetector/TestData/model.txt");
        String refObj = Tools.readTextFromFile("/Users/keita-katsumi/Dropbox/GlobalLogic/Alchemist/VerbatimDetector/TestData/model.txt");

        //Slice one long string to a sentence string
        //Store each string sentence to the Array list to aggregate
        ArrayList<String> listOfRewrite = Tools.sliceJapaneseText(rewriteObj);
        ArrayList<String> listOfModel = Tools.sliceJapaneseText(modelObj);
        ArrayList<String> listOfreference= Tools.sliceJapaneseText(refObj);

        System.out.println("\n\n - - - -  Compare your rewrite and model summary  - - - - - - ");
        Tools.displayDetectLettersAndSentences(listOfRewrite, listOfModel);

        System.out.println("\n\n - - - - 　Compare your rewrite and reference  - - - - -");
        Tools.displayDetectLettersAndSentences(listOfRewrite, listOfreference);

        System.out.println("\n\n~~ displayDetectLettersAndSentencesTest passed~~ \n\n");

    }


} // End of TestSuite

package UtilMethods;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {

    static final int NUMBER_OF_CONSECCUTIVE_LETTER = 15;
    // Declaring ANSI_RESET so that we can reset the color
    public static final String ANSI_RESET = "\u001B[0m";

    // Declaring the color
    // Custom declaration
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    /**
     *The method extract file text into one big String
     * @Param filePath, file path in the local device
     * @Return fileObj, extracted text source from the file.txt
     */
    public static String readTextFromFile(String filePath) {
        StringBuilder fileObj = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                fileObj.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return fileObj.toString();
    }

    /**
     * The method takes file source and  separte sentences, and sore each sentence to the ArrayList.
     * @Param String fileObj, it is text file data extracting rewrite or model source
     * @Return ArrayList<String> listOfSentences, the Array list aggregates sliced text
     */
    public static ArrayList<String> sliceJapaneseText(String fileObj) {
        ArrayList<String> listOfSentences = new ArrayList<>();

        // Define a regular expression pattern for the delimiters you mentioned (。, ], and \n.)
        Pattern pattern = Pattern.compile("。|\\]|\\]\n");

        // Use a Matcher to split the text
        Matcher matcher = pattern.matcher(fileObj);

        // Start index of the current sentence
        int start = 0;

        // Iterate through the text and split it into sentences
        while (matcher.find()) {
            int end = matcher.end();
            String sentence = fileObj.substring(start, end).trim();
            listOfSentences.add(sentence);
            start = end;
        }

        // Add the remaining text as the last sentence
        String lastSentence = fileObj.substring(start).trim();
        listOfSentences.add(lastSentence);

        return listOfSentences;
    }


    /**
     * @Param String strOfRewrite, one of the sentence in the list of rewrite
     *@Param String strOfModel, one of the sentence in the list of model
     * @Return String strOverlaps, consecutive characters in the rewrite sentence
     */
    public static String findOverlappingCharacters(String strOfRewrite, String strOfModel) {
        assert strOfRewrite.length() >= NUMBER_OF_CONSECCUTIVE_LETTER : "The rewrite sentence should greater than equal to the number of consecutive letters";
        assert strOfModel.length() >= NUMBER_OF_CONSECCUTIVE_LETTER : "The model sentence should greater than equal to the number of consecutive letters";

        String strOverlap = null;

        //Calculate the last required index of rewrite sentence
        int lstIdxStrOfRewrite = strOfRewrite.length() - NUMBER_OF_CONSECCUTIVE_LETTER;
        //Calculate the last required index of model sentence
        int lstIdxStrOfModel = strOfModel.length() - NUMBER_OF_CONSECCUTIVE_LETTER;

        assert lstIdxStrOfRewrite > -1 : "The last index of rewrite which is needs to be checked should more than 0";
        assert lstIdxStrOfModel > -1 : "The last index of model which is needs to be checked should more than 0";

        //The inner loop iterates the first index of rewrite sentence to the required last index
        //The outer loop iterates the first index of model sentence to the required last index
        // Let o_wk is outer loop walker, in_wk is inner loop walker
        for (int o_wk = 0; o_wk < lstIdxStrOfRewrite; o_wk++) {
            String subStrRewrite = strOfRewrite.substring(o_wk, o_wk + NUMBER_OF_CONSECCUTIVE_LETTER);
            for (int in_wk = 0; in_wk < lstIdxStrOfModel; in_wk++) {
                String subStrModel = strOfModel.substring(in_wk, in_wk + NUMBER_OF_CONSECCUTIVE_LETTER);
                if (subStrRewrite.equals(subStrModel)) {
                    strOverlap = subStrRewrite;
                    return strOverlap;
                } else {
                    continue;
                }
            } // end of inner loop
        } // end of outer loop

        //In case there is not consecutive letters in both sentence, return null as a flag.
        return strOverlap;
    } // end of findOverlappingCharacters

    /**
     * @Param ArrayList<String> listOfRewrite, list of rewrite sentence
     * @Param ArrayList<String> listOfModel, list of rewrite sentence
     * @Return void, display consecutive letters and rewrite sentence which has the consecutive letters , model sentence
     */
    public static void displayDetectLettersAndSentences(ArrayList<String> listOfRewrite, ArrayList<String> listOfModel){
        int counter = 1;

        for (int o_wk = 0; o_wk < listOfRewrite.size(); o_wk++){
            if (listOfRewrite.get(o_wk).length() < NUMBER_OF_CONSECCUTIVE_LETTER){ continue;}
            for (int in_wk = 0; in_wk < listOfModel.size(); in_wk++){
                if (listOfModel.get(in_wk).length() < NUMBER_OF_CONSECCUTIVE_LETTER){continue;}
                String verbatim = findOverlappingCharacters(listOfRewrite.get(o_wk), listOfModel.get(in_wk));
                    if (verbatim == null){
                        continue;
                    }else{
                        //Display the detection result
                        System.out.println( ANSI_GREEN + "\n\n" + counter + ": " + " !!!WARNING!!! ~~Detect Verbatim ~~" + ANSI_RESET);

                        System.out.println("Consecutive letters: \n" + verbatim);

                        System.out.println("\nYour rewrite sentence: ");
                        System.out.print(listOfRewrite.get(o_wk) + "\n");
                        System.out.println("\nCompare the model or reference: ");
                        System.out.println(listOfModel.get(in_wk) + "\n");
                        //Update next detection number
                        counter++;

                        // Exit the inner loop without break statement , to check the next model sentence
                        in_wk = listOfModel.size();


                    }
            } // end of inner loop
        } // end of outer loop

    }












} // End of Tools class

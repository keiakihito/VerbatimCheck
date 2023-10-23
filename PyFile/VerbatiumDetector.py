'''
/**
 * Author: Keita Katsumi
 * Last updated Oct 22. 2023
 * The program  is for super raters' verbatim detection check process.
 * It aims to reduce verbatim manual checking time and effort.
 * But it can check a single row every time.
 *
 * The VerbatimCheck program does the three things
 *
 * 1. Read three text files: rewrite.txt, model.txt, and reference txt
 * All the text information comes form the raters' google sheet manually.
 * Extract the text data as a String, then store each sentence in the ArrayList<String>
 *
 * 2. Compare raters' rewrite and model response.
 * The program checks the number of consecutive letters in the rewrite in the model response.
 * If it is detected, the program shows
 *   i. Consecutive letters,
 *   ii. Rewrite sentence which has the consecutive letters,
 *   iii. Model response sentence that has consecutive letters.
 *
 *   3. Compare the raters' rewrite and references.
 *   It takes the same process as section 2.
 *
 *   If you would like to discuss the future application or any issues,
 *   Please contact my Google or GL email.
 *
 */
'''

import re

NUMBER_OF_CONSECUTIVE_LETTERS = 15

def read_text_from_file(file_path):
    file_obj = ""
    try:
        with open(file_path, 'r', encoding='utf-8') as file:
            file_obj = file.read()
    except IOError as e:
        print("Error reading file:", e)
    return file_obj

def slice_japanese_text(file_obj):
    list_of_sentences = []
    # Define a regular expression pattern for the delimiters (。, ], and \n)
    pattern = re.compile(r'。|\]|\]\n')
    # Use re.split to split the text
    sentences = re.split(pattern, file_obj)
    for sentence in sentences:
        sentence = sentence.strip()
        if sentence:
            list_of_sentences.append(sentence)
    return list_of_sentences

def find_overlapping_characters(str_of_rewrite, str_of_model):
    assert len(str_of_rewrite) >= NUMBER_OF_CONSECUTIVE_LETTERS, "The rewrite sentence should be greater than or equal to the number of consecutive letters"
    assert len(str_of_model) >= NUMBER_OF_CONSECUTIVE_LETTERS, "The model sentence should be greater than or equal to the number of consecutive letters"

    str_overlap = None
    # Calculate the last required index of the rewrite sentence
    lst_idx_str_of_rewrite = len(str_of_rewrite) - NUMBER_OF_CONSECUTIVE_LETTERS
    # Calculate the last required index of the model sentence
    lst_idx_str_of_model = len(str_of_model) - NUMBER_OF_CONSECUTIVE_LETTERS

    assert lst_idx_str_of_rewrite > -1, "The last index of rewrite to be checked should be more than 0"
    assert lst_idx_str_of_model > -1, "The last index of model to be checked should be more than 0"

    # Iterate through both sentences to find overlapping characters
    for o_wk in range(lst_idx_str_of_rewrite + 1):
        sub_str_rewrite = str_of_rewrite[o_wk:o_wk + NUMBER_OF_CONSECUTIVE_LETTERS]
        for in_wk in range(lst_idx_str_of_model + 1):
            sub_str_model = str_of_model[in_wk:in_wk + NUMBER_OF_CONSECUTIVE_LETTERS]
            if sub_str_rewrite == sub_str_model:
                str_overlap = sub_str_rewrite
                return str_overlap
    return str_overlap

def display_detect_letters_and_sentences(list_of_rewrite, list_of_model):
    counter = 1
    for o_wk in range(len(list_of_rewrite)):
        if len(list_of_rewrite[o_wk]) < NUMBER_OF_CONSECUTIVE_LETTERS:
            continue
        for in_wk in range(len(list_of_model)):
            if len(list_of_model[in_wk]) < NUMBER_OF_CONSECUTIVE_LETTERS:
                continue
            verbatim = find_overlapping_characters(list_of_rewrite[o_wk], list_of_model[in_wk])
            if verbatim is None:
                continue
            else:
                # Display the detection result
                print(f"\n\n{counter}: !!!WARNING!!! ~~Detect Verbatim ~~")
                print("Consecutive letters: \n" + verbatim)
                print("\nYour rewrite sentence: ")
                print(list_of_rewrite[o_wk] + "\n")
                print("Compare the model: ")
                print(list_of_model[in_wk] + "\n")
                # Update the next detection number
                counter += 1






# main 
# Input process section 1
#The file path needs to be adjusted where you put rewrite.txt, mode.text, and reference.txt
rewrite_obj = read_text_from_file("rewrite.txt")
model_obj = read_text_from_file("model.txt")
reference_obj = read_text_from_file("reference.txt")

# Slice the long string from the given files into sentence strings
list_of_rewrite = slice_japanese_text(rewrite_obj)
list_of_model = slice_japanese_text(model_obj)
list_of_reference = slice_japanese_text(reference_obj)

#Process and output
# Comparing raters' rewrite and model and detect verbatim if there exist
print("\n\n - - - - - Compare your rewrite and model summary  - - - - - ")
display_detect_letters_and_sentences(list_of_rewrite, list_of_model)

#Process and output
# Comparing raters' rewrite and reference and detect verbatim if there exist
print("\n\n - - - - -  Compare your rewrite and reference - - - - - ")
display_detect_letters_and_sentences(list_of_rewrite, list_of_reference)






'''
Sample Run
 - - - - - Compare your rewrite and model summary  - - - - - 


1: !!!WARNING!!! ~~Detect Verbatim ~~
Consecutive letters: 
要約とは、文章などの要点をとり

Your rewrite sentence: 
要約とは、文章などの要点をとりまとめることです

Compare the model: 
要約とは、文章などの要点をとりまとめることです



2: !!!WARNING!!! ~~Detect Verbatim ~~
Consecutive letters: 
「読んだ本の内容を要約して話す

Your rewrite sentence: 
一例として：
＊「読んだ本の内容を要約して話す」

他の意味としては、約束を結ぶこと

Compare the model: 
一例として「読んだ本の内容を要約して話す」などがあります



3: !!!WARNING!!! ~~Detect Verbatim ~~
Consecutive letters: 
他の意味としては、約束を結ぶこ

Your rewrite sentence: 
一例として：
＊「読んだ本の内容を要約して話す」

他の意味としては、約束を結ぶこと

Compare the model: 
他の意味としては、約束を結ぶこと、また約束を指します



 - - - - -  Compare your rewrite and reference - - - - - 


1: !!!WARNING!!! ~~Detect Verbatim ~~
Consecutive letters: 
文章などの要点をとりまとめるこ

Your rewrite sentence: 
要約とは、文章などの要点をとりまとめることです

Compare the model: 
Refernce: goo辞書
 https://dictionary.goo.ne.jp/word/%E8%A6%81%E7%B4%84/
 
 よう‐やく〔エウ‐〕【要約】 の解説
［名］(スル)

    １ 文章などの要点をとりまとめること


'''

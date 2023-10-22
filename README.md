# VerbatimCheck
The program  is for super raters' verbatim detection check process. It aims to reduce verbatim manual checking time and effort. But it can check a single row every time. 
 
* The VerbatimCheck program does the three things
 
1. Read three text files: rewrite.txt, model.txt, and reference txt
<br>All the text information comes from the raters' Google sheet manually.
<br>Extract the text data as a String, then store each sentence in the ArrayList<String>
 
2. Compare raters' rewrite and model response.
<br>The program checks the number of consecutive letters in the rewrite in the model response.
<br>If it is detected, the program shows
<br> i. Consecutive letters,
<br> ii. Rewrite the sentence which has the consecutive letters,
<br> iii. Model response sentence which has consecutive letters.
 
3. Compare the raters' rewrite and references.
<br>It takes the same process  of section 2.
 
<br>If you would like to discuss the future application or any issues,
<br>please contact my google or GL email.






# Sample Run

<br> - - - - - Compare your rewrite and model summary  - - - - - 


1:  !!!WARNING!!! ~~Detect Verbatim ~~
<br> Consecutive letters: 
<br> 要約とは、文章などの要点をとり

<br> Your rewrite sentence: 
<br> 要約とは、文章などの要点をとりまとめることです。

<br> Compare the model or reference: 
<br> 要約とは、文章などの要点をとりまとめることです。



<br> 2:  !!!WARNING!!! ~~Detect Verbatim ~~
<br> Consecutive letters: 
<br> 「読んだ本の内容を要約して話す

<br> Your rewrite sentence: 
<br> 一例として：＊「読んだ本の内容を要約して話す」他の意味としては、約束を結ぶこと。

<br> Compare the model or reference: 
<br> 一例として「読んだ本の内容を要約して話す」などがあります。



 <br><br> - - - - -  Compare your rewrite and reference - - - - - 

1:  !!!WARNING!!! ~~Detect Verbatim ~~
<br> Consecutive letters: 
<br> 文章などの要点をとりまとめるこ

<br> Your rewrite sentence: 
<br> 要約とは、文章などの要点をとりまとめることです。

<br> Compare the model or reference: 
<br> Refernce: goo辞書 https://dictionary.goo.ne.jp/word/%E8%A6%81%E7%B4%84/  よう‐やく〔エウ‐〕【要約】 の解説［名］(スル)    １ 文章などの要点をとりまとめること。



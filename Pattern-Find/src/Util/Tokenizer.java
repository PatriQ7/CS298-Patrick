package Util;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * This class is in charge of tokenizing a string
 * @author Nikhil Kalantri
 * @version 1.0
 */
public class Tokenizer
{
// ........................ D A T A   F I E L D S ............................//
// ............. G L O B A L   P R I V A T E   C O N S T A N T S .............//

    /** hold average English words size */
    private static final int AVG_WORD_SIZE = 5;

// .............. G L O B A L   P U B L I C   C O N S T A N T S ..............//
// ................. G L O B A L   P R I V A T E   V A R S ...................//
//.............................. G E T T E R S ...............................//
//.............................. S E T T E R S ...............................//
// ...................... P R I V A T E   M E T H O D S ......................//


// ...................... P U B L I C   M E T H O D S ........................//

    /**
     * initialize matcher objects
     * @param patStrArr - the given array of pattern strings
     * @param tokenStr - the string that we are going to apply the tokenizer.
     */
    private static Matcher[] initMatchers(
        ArrayList<String> patStrArr, String tokenStr)
    {
        int arrSize = patStrArr.size();
        Pattern[] patternArr = new Pattern[arrSize];
        Matcher[] matcherArr = new Matcher[arrSize];


        for (int i = 0; i < arrSize; i++) {

            patternArr[i] = Pattern.compile(patStrArr.get(i));
            matcherArr[i] = patternArr[i].matcher(tokenStr);

        } //for

        return matcherArr;

    } //method


    private static void testinitMatchers()
    {
//        String[] patStrArr = {"^(email is)\\b", "^(url is)\\b"};
        String patternFN = "pattern.txt";
        char commentChar = '\'';
        ArrayList<String> patStrArr =
            FileUtil.fileToArrayListSkipCommentLines(patternFN, commentChar);
        //StringUtil.showArrayList(patStrArr);

        String tokenStr =
              "My email is a test nikhil@nikhilkalantri.com and url is this one ";

        Matcher[] matcherArr = initMatchers(patStrArr, tokenStr);

        for (int i = 0; i < matcherArr.length; i++)
            System.out.format("matcher %d = [%s] %n%n", i, matcherArr[i]);

    } //test method


    /**
     * adjust the matcher region in the target string based on the previous matches
     * @param matcherArr - the array of the matchers
     * @param start - the new start point of the region
     * @param strLength - the end of the region = string length
     */
    private static void adjustMatchersRegions(Matcher[] matcherArr,
        int start, int strLength)
    {
        for (int i = 0; i < matcherArr.length; i++) {
            matcherArr[i] = matcherArr[i].region(start, strLength);
        }

    } //method


    /**
     * check if a token is stop words or not
     * @param token - the given token
     * @param stopWordsHM - hash map of the stop words
     * @return true if the token is stop words and false otherwise.
     */
    private static boolean isTokenStopWords(
        String token, HashMap<String, Integer> stopWordsHM)
    {
        if (stopWordsHM.get(token.toLowerCase()) == null) return false;
        else                                              return true;

    } //method


    /**
     * advance the iterator to the next start token.
     * @param iter - the given iterator
     * @param start - number of tokens that it should advance.
     */
    private static void advanceIterator(BreakIterator iter, int start)
    {
        while(iter.next() < start) ; //note to the empty statement ";"
    } //method


    /**
     * chop a given string into tokens and return them as an array list.
     * @param tokenStr - the given string
     * @param locale - the locale of the string
     * @param regXArr - the array of regX containing the patterns that should be
     * checked to recognize specific tokens like US address, email and so forth.
     * @return the array list containing the tokens
     */
    public static ArrayList<String> tokenize(
        String tokenStr, Locale locale, ArrayList<String> regXArr,
        HashMap<String, Integer> stopWordsHM)
    {
        ArrayList<String> tokens = new ArrayList(); //for efficiency
        BreakIterator iter = BreakIterator.getWordInstance(locale);
        iter.setText(tokenStr);

        int start = iter.first();
        int end;
        int strLength  = tokenStr.length();

        Matcher[] matcherArr = initMatchers(regXArr, tokenStr);

        String token;
        boolean matched;

        while (start != BreakIterator.DONE) {
            matched = false;
            end   = iter.next();

            //check all regX's
            for (int i = 0; i < matcherArr.length; i++) {
                if (matcherArr[i].find()) {
                    token = tokenStr.substring(matcherArr[i].start(), matcherArr[i].end());

                    if (!isTokenStopWords(token, stopWordsHM))
                        tokens.add(token);

                    start = matcherArr[i].end();

                    //advance the iter to next token after the current position
                    advanceIterator(iter, start);

                    matched = true;
                    break;
                } //if
            } //for

            if (!matched) {
                if (end == BreakIterator.DONE) break;
                token = tokenStr.substring(start, end);
                if (Character.isLetterOrDigit(token.charAt(0)) &&
                    !isTokenStopWords(token, stopWordsHM))
                    tokens.add(token);

                start = end;

            } //if

            adjustMatchersRegions(matcherArr, start, strLength);

        } //while

        return tokens;

    } //method


    private static void testtokenize()
    {
        //Tested in TextAnalysis class

    } //test method


    /**
     * tokenize the given string and return an array list containing the tokens.
     * post-condition: the tokens cases are INTACT.
     *
     * @param inpStr - the given input string
     * @param locale - the locale of the language
     * @param unitTerminatorCoded - is true if it should add a terminator
     * (period) to the chopping unit.
     * @return the tokens in an array list
     */
    public static ArrayList<String> simpleTokenizer(
        String inpStr, Locale locale)
    {
        //int avgSize = inpStr.length() / AVG_WORD_SIZE;
        ArrayList<String> tokens = new ArrayList();

        BreakIterator iter = BreakIterator.getWordInstance(locale);
        iter.setText(inpStr);

        int start = iter.first();
        int end   = iter.next();

        while (end != BreakIterator.DONE) {

            String tkn = inpStr.substring(start, end);

            if (Character.isLetter(tkn.charAt(0)))
                tokens.add(tkn);

            start = end;
            end = iter.next();
        } //while

       return tokens;

    } //method


} //class


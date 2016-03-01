
package Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.BreakIterator;
import java.util.*;



/**
 * This class provides some string handling operations that Java
 *              does not support directly.
 *
 * Author:		Nikhil Kalantri
 *
 * Modified By: Date         	Who                   	Why
 * -------------------------------------------------------------------
 *
 **/
public class StringUtil
{
//...................... P U B L I C   M E T H O D S ........................//

    /**
     * create a query statement from the table name.
     * @param t1 - the table name.
     * @return the query statement.
     */
    public static String createQueryStatement(String t1)
    {
            return String.format("SELECT * FROM %s", t1.toUpperCase());

    } // method


    /**
     * copy a string array to another.
     * @param strArr - the string that should be copied.
     * @return the new born string.
     */
    public static String[] copyStringArr(String[] strArr)
    {
            int count = strArr.length;
            String[] tempStr = new String[count];
            System.arraycopy(strArr, 0, tempStr, 0, count);

            return tempStr;

    } // method


    /**
     * break a string into pieces based on the start and length values provided.
     * @param lineStr - the string that should be broken.
     * @param startLength - the array containing start and length of pieces.
     * @return - the array of pieces.
     */
    public static String[] breakStringToValues(String lineStr,
                    int[][] startLength)
    {
            int lineLeng = lineStr.length();
            int count = startLength.length;
            String[] values = new String[count];

            int start, end;

            for (int i = 0; i < count; i++)
            {
                    start = startLength[i][0];
                    end   = start + startLength[i][1];

                    if (start > lineLeng)
                            values[i] = "";

                    else if (end > lineLeng)
                            values[i] = lineStr.substring(start).trim();
                    else
                            values[i] = lineStr.substring(start, end).trim();
            } // for-loop

            return values;

    } // method


    private static void testBreakStringToValues()
    {
            int[][] startLengthArr = {{0,3}, {3,4}, {7,2}, {9,3}};
            String line = "A  B   C D  ";

            String[] valuesArr = breakStringToValues(line, startLengthArr);

            showStringArray(valuesArr);

    } // method


    /**
     * show the content of string array.
     * @param arr - the array
     */
    public static void showStringArray(String[] arr)
    {
            System.out.println("...........................................");

            for (int i = 0; i < arr.length; i++)
            {
                    System.out.println("Element #" + i + " = [" + arr[i] + "]");

            }// for-loop

    } // method


    /**
     * show the content of integer array.
     * @param arr - the array
     */
    public static void showIntArray(int[] arr)
    {
        System.out.println("...........................................");

        for (int i = 0; i < arr.length; i++)
        {
                System.out.println("Element #" + i + " = [" + arr[i] + "]");

        }// for-loop

    } // method


    /**
     * show the content of Integer array.
     * @param arr - the array
     */
    public static void showIntegerArray(Integer[] arr)
    {
        System.out.println("...........................................");

        for (int i = 0; i < arr.length; i++)
        {
                System.out.println("Element # " + i + " = [" + arr[i] + "]");

        }// for-loop

    } // method


    /**
     * show the content of string array.
     * @param arr - the array
     */
    public static void showShortArray(short[] arr)
    {
        System.out.println("...........................................");

        for (int i = 0; i < arr.length; i++)
        {
                System.out.println("Element # " + i + " = [" + arr[i] + "]");

        }// for-loop

    } // method


    public static void showByteArr(byte[] bArr)
    {
        System.out.print("[");
        for (int i = 0; i < bArr.length; i++)
        {
            System.out.print(bArr[i] + " ");
        }
        System.out.println("]");
    } // method


    /**
     * break a string to 'limit' parts and add new line at the each
     * part and concatenate all of them and return it.
     * @param inStr - the input string
     * @param limit - the length that the string should be broken.
     *
     * @return - the result string
     */
    public static String lineBreaker(String inStr, int limit)
    {
        String restStr = inStr;
        String result  = "";

        while (restStr.length() > limit)
        {
                result += restStr.substring(0, limit) + "\n";
                restStr = restStr.substring(limit);

        } // end of while-loop

        return result + restStr;

    } // method


    private static void testLineBreaker()
    {
        String inStr = "34567890123";
        int limit  = 5;

        //String testStr = modifyJCLLineLengthProblem(inFN, oFN, null);
        String testStr = lineBreaker(inStr, limit);

        System.out.println(testStr);


    } // method


    /**
     * remove the trimStr from the str.
     * @param str - the string that should be trimmed.
     * @param trimStr - the string that should be removed from the end of str
     **/
    public static String trimOccuranceFromBeg(String str, String trimStr)
    {
        if (str.startsWith(trimStr))
                return str.substring(trimStr.length());
        else
                return str;

    } // method


    private static void testtrimOccuranceFromBeg()
    {
        String str     = "l\nThis is me";
        String trimStr = "l";


        System.out.println("[" + trimOccuranceFromBeg(str, trimStr) + "]");

    //		System.out.println(ro.toString());


    } // method


    /**
     * remove the trimStr from the str.
     * @param str - the string that should be trimmed.
     * @param trimStr - the string that should be removed from the end of str
     **/
    public static String trimOccuranceFromEnd(String str, String trimStr)
    {
        if (str.endsWith(trimStr))
                return str.substring(0, str.length() - trimStr.length());
        else
                return str;

    } // method


    private static void testtrimOccuranceFromEnd()
    {
        String str     = "This is me\n;";
        String trimStr = "\n;";


        System.out.println("[" + trimOccuranceFromEnd(str, trimStr) + "]");

    //		System.out.println(ro.toString());


    } // method


    /**
     * remove the trimStr from the begining and the str.
     * @param str - the string that should be trimmed.
     * @param trimStr - the string that should be removed
     **/
    public static String trimOccuranceFromBegAndEnd(String str, String trimStr)
    {
        String tempStr = trimOccuranceFromEnd(str, trimStr);
        return trimOccuranceFromBeg(tempStr, trimStr);

    } // method


    private static void testtrimOccuranceFromBegAndEnd()
    {
        String str     = "(This is me);";
        String trimStr = "(";


        System.out.println("[" + trimOccuranceFromBegAndEnd(str, trimStr) + "]");

    //		System.out.println(ro.toString());


    } // method


    /**
     * make a string of all values of an array separated by comma.
     * @param valueArr - the input array.
     * @return - the string of the array values.
     */
    public static String createCommaSeparatedStr(String[] valueArr)
    {
        StringBuilder result = new StringBuilder(valueArr[0]);

        for (int i = 1; i < valueArr.length; i++)
                result.append(", ").append(valueArr[i]);

        return result.toString();

    } // method


    /**
     * make a string of all values of an array separated by comma. It looks at
     * the corresponding numOrStr and if it is 0 means that the value is a
     * String. So, it wrapped it in single quotes.
     * @param valueArr - the input array.
     * @return - the string of the array values.
     */
    public static String createCommaSeparatedStrWrapped(String[] valueArr,
                    int[] numOrStr)
    {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < valueArr.length; i++)
        {
                // remove \' from the char types
                valueArr[i] = valueArr[i].replaceAll("[\']", "");

                if (valueArr[i].equals("-"))
                        result.append(", NULL");
                else if (numOrStr[i] == 0)
                        result.append(", \'").append(valueArr[i]).append("\'");
                else if (numOrStr[i] == 2)
                        result.append(", CURRENT TIMESTAMP");
                else
                        result.append(", ").append(valueArr[i]);
        }

        return result.toString().substring(2);

    } // method


    /**
     * chops a string into smaller size and return them as an array.
     * @param str - the original string
     * @param size - the chopped strings sizes
     * @return an array of the strings
     */
    public static String[] stringChopper(String str, int size)
    {
        float temp = ((float) str.length() / (float) size) + (float) 0.5;
        int arrSize = Math.round(temp);

        String[] strArr = new String[arrSize];

        for (int i = 0; i < arrSize; i++)
        {
            if (str.length() <= size)
            {
                strArr[i] = str;
                break;
            }
            else
            {
                strArr[i] = str.substring(0, size);
                str = str.substring(size);
            }
        } // for-loop

        return strArr;

    } // constructor


    private static void teststringChopper()
    {
        String str;
        //String str = "123456789";

        try {
            try (Scanner scr = new Scanner(new File("bitString.txt"))) {
                str = scr.nextLine();
                //System.out.println("str = "+ str);
                String[] arr = stringChopper(str, 4096);

                showStringArray(arr);
            }

        } // end of try

        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    } // method

    /**
     * right pad a string with spaces
     * @param str - the original string
     * @param padedSize - the final string sizes
     * @return the final strings
     */
    public static String padRight(String str, int padedSize)
    {  return String.format("%1$-" + padedSize + "s", str);  } // method


    /**
     * left pad a string with a specific char
     * @param str - the original string
     * @param size - the final string sizes
     * @param padChar - the padding char
     * @return the final padded string
     */
    public static String padLeftWithChar(String str, int size, char padChar)
    {
        if (str.length() >= size) return str.substring(0, size);

        int i = 0;
        StringBuilder sb = new StringBuilder(size);
        int padSize = size - str.length();

        while (i < padSize)
        {
            sb.append(padChar);
            i++;
        }
        sb.append(str);

    return sb.toString();

    } // method


    private static void testpadLeftWithChar()
    {
        String tempStr = padLeftWithChar("hello!", 2, '0');
        System.out.println("[" +tempStr + "]");

    } // method


    /**
     * create a random bit string in the size given for testing only
     * @param size - the track string
     * @return the bit strng
     */
    public static String createRandomBitString(int size)
    {
        String bitStr = "";
        Random rnd = new Random();

        for (int i = 0; i < size; i++)
            bitStr += (rnd.nextBoolean() == true ? "1" : "0");

        //System.out.println("Bit string = " + bitStr + "\n");

        //RAccessFile.createAndWriteInFile("bitString.txt", bitStr);
        return bitStr;

    } // method


    /**
     * find the smallest number greater than b which is a multiple of a.
     * for example 5/2 = 2.5 then we round 2.5 upward to get 3 and 3 * 2 = 6 and
     * return 6
     * @param dividend - the dividend
     * @param divisor - the divisor
     * @return the smallest multiple of the divisor greater than dividend
     */
    public static int findSmallestMultiple(int dividend, int divisor)
    {
        float temp = ((float) dividend / (float) divisor) + (float) 0.5;
        return Math.round(temp) * divisor;

    } // method


    /**
     * right pad a string with a specific char
     * @param str - the original string
     * @param size - the final string sizes
     * @param padChar - the padding char
     * @return the final padded string
     */
    public static String padRightWithChar(String str, int size, char padChar)
    {
        if (str.length() >= size) return str;

        int i = 0;
        StringBuilder sb = new StringBuilder(size);
        sb.append(str);
        int padSize = size - str.length();

        while (i < padSize)
        {
            sb.append(padChar);
            i++;
        }

    return sb.toString();

    } // method


    private static void testpadRightWithChar()
    {
        String tempStr = padRightWithChar("hello!", 20, '0');
        System.out.println("[" +tempStr + "]");

    } // method


    /**
     * right pad an array of strings with a specific char
     * @param strArr - the original string array
     * @param size - the final string sizes
     * @param padChar - the padding char
     * @return the final padded string array
     */
    public static String[] padRightStrArrWithChar(String[] strArr, int size, char padChar)
    {
        if (strArr[0].length() >= size) return strArr;

        String msg = "\n";
        System.out.println(msg);

        for (int j = 0; j < strArr.length; j++) {
            strArr[j] = padRightWithChar(strArr[j], size, padChar);

            msg = "Right padding the string array# %d with '0'";
            System.out.println(String.format(msg, j));

        } //for


        return strArr;

    } // method


    private static void testpadRightStrArrWithChar()
    {
        String[] strArr = {"nikhil", "shivratan", "kalantri"};

        strArr = padRightStrArrWithChar(strArr, 10, '0');

        StringUtil.showStringArray(strArr);


    } // method


    /**
     * left pad a string with spaces
     * @param str - the original string
     * @param padedSize - the final string sizes
     * @return the final strings
     */
    public static String padLeft(String str, int padedSize)
    {
        return String.format("%1$#" + padedSize + "s", str);

    } // method


    private static void testpadLeft()
    {
        String tempStr = padLeft("Helo!", 20);
        System.out.println("[" +tempStr + "]");

    } // method


    /**
     * left pad a number with zero and return it as an string.
     * @param dec - the decimal number
     * @param padedSize - the final string sizes
     * @return the final strings
     */
    public static String padLeftZero(int dec, int padedSize)
    {
        return String.format("%0" + padedSize + "d", dec);

    } // method


    private static void testpadLeftZero()
    {
        String tempStr = padLeftZero(23, 20);
        System.out.println("[" +tempStr + "]");

    } // method


    /**
     * make a string of all values of an array separated by space.
     * @param valueArr - the input array.
     * @return - the string of the array values.
     */
    public static String createSpaceSeparatedStr(String[] valueArr, int start)
    {
        StringBuilder result = new StringBuilder(valueArr[start]);

        for (int i = start+1; i < valueArr.length; i++)
                result.append(" ").append(valueArr[i]);

        return result.toString();

    } // method


    private static void testcreateSpaceSeparatedStr()
    {
        String[] strArr = {"sql", "drop", "table", "item"};
        System.out.println("[" + createSpaceSeparatedStr(strArr, 1) + "]");

    } // method


    /**
     * make a string of all values of an array separated by comma pre-added with
     * another string with underscore.
     * @post-condition: it does NOT trim the last comma.
     * @param sb - the string builder
     * @param preAddedStr - the pre-added string
     * @param valueArr - the input array
     */
    public static void createCommaSeparatedPreAddedStr(StringBuilder sb,
            String preAddedStr, String[] valueArr)
    {
        for (int i = 0; i < valueArr.length; i++)
                sb.append(preAddedStr).append("_").append(valueArr[i]).append(", ");

    } // method


    private static void testcreateCommaSeparatedAddedStr()
    {
        StringBuilder sb = new StringBuilder();
        String preAddedStr = "V3";
        String[] valueArr = {"50", "70"};
        createCommaSeparatedPreAddedStr(sb, preAddedStr, valueArr);
        System.out.println("[" + sb.toString() + "]");

    } // method


    /**
     * convert an array of string to Hash Map
     * @param arr - the input array
     * @return the hash map
     */
    public static HashMap<String, Integer> convertArrayToHashMap(String[] arr)
    {
        HashMap<String, Integer> hm = new HashMap<>();

        for (int i = 0; i < arr.length; i++)
            hm.put(arr[i], i);

        return hm;

    } // method


    public static void testconvertArrayToHashMap()
    {
        String[] arr = {"A", "B", "C"};
        HashMap<String, Integer> hm = convertArrayToHashMap(arr);
        System.out.println(hm.toString());

    } // method


    /**
     * convert a string value to comma separated position bit string
     * @param val - the input value as a string
     * @param hm - the hash map containing the value position
     * @param sb - the string builder object
     */
    public static void convertValToBitStr(String val,
            HashMap<String, Integer> hm, StringBuilder sb)
    {
        int pos = hm.get(val) == null ? -1 : hm.get(val);

        for (int i = 0; i < hm.size(); i++) {
            if (i == pos) sb.append("1, ");
            else          sb.append("0, ");
        } // for

    } // method


    public static void testconvertValToBitStr()
    {
        HashMap<String, Integer> hm = new HashMap();
        hm.put("50", 0);
        hm.put("70", 1);
        System.out.println(hm.toString());

        StringBuilder sb = new StringBuilder();
        convertValToBitStr("70", hm, sb);
        convertValToBitStr("50", hm, sb);
        convertValToBitStr("40", hm, sb);

        System.out.println(sb.toString());
    } // method


    /**
     * get a string array and initialize them with "".
     * @param size - the size of the string array.
     * @return - the the string array.
     */
    public static String[] getStrArr(int size)
    {
        String[] tempStrArr = new String[size];

        for (int i = 0; i < size; i++)
            tempStrArr[i] = "";

        return tempStrArr;

    } // method


    /**
     * check if the char is in the given string.
     * @param c - the character
     * @param str - the string
     * @return true if the char exists in the string and false otherwise.
     */
    public static boolean isCharInStr(char c, String str)
    {
        if (str.indexOf(c) != -1) return true;

        return false;

    } // method


    private static void testisCharInStr()
    {
        String str = ".!?";
        char c = 'b';
        System.out.println(isCharInStr(c, str));

        c = '.';
        System.out.println(isCharInStr(c, str));

        c = '!';
        System.out.println(isCharInStr(c, str));

        c = ' ';
        System.out.println(isCharInStr(c, str));



    } // method


    /**
     * show the content of an array list.
     * @param arr - the array list
     */
    public static <E> void showArrayList(ArrayList<E> arr)
    {
            System.out.println("...........................................");

            for (int i = 0; i < arr.size(); i++)
            {
                System.out.format("Element #%d = [%s]%n", i, arr.get(i).toString());
            } //for

    } // method


    private static void testshowArrayList()
    {
//        ArrayList<Node> arr = new ArrayList();
//
//        Node n1 = new Node(null, 15, 9, 0);
//        Node n2  = new Node(n1,  3, 1, 1);
//
//        arr.add(n1);
//        arr.add(n2);
//
//        showArrayList(arr);

    } // method


    /**
     * create blank string whose size is 4 * size
     * @param size - the size of the string
     * @return the blank string
     */
    public static String space4Creator(int size) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < size; i++)
            sb.append("                 ");

        return sb.toString();
    } //method


    /**
     * Repeat the input string n times and return it.
     * @param size - the size of the string
     * @param str - the input string
     * @return the repeated string
     */
    public static String stringRepeater(int size, String str) {

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < size; i++)
            sb.append(str);

        return sb.toString();
    } //method


    /**
     * Divide a string into two (almost) halves and return an array list of
     * them and add the input string to the array list as well.
     * @param inStr - the given input string
     * @return the array list of the two halves strings and the whole inpStr
     */
    public static ArrayList<String> stringToTwoHalvesPlus(String inStr)
    {
        ArrayList<String> tempArr = stringToTwoHalves(inStr);
        tempArr.add(inStr);

        return tempArr;
    } //method


    /**
     * Divide a string into two (almost) halves and return an array list of
     * them.
     * @param inStr - the given input string
     * @return the array list of the two halves strings
     */
    public static ArrayList<String> stringToTwoHalves(String inStr)
    {
        int halfSize = inStr.length() / 2;
        int actualBreakPoint = inStr.indexOf('\r', halfSize) + 2; //to include \r\n
        ArrayList<String> tempArr = new ArrayList();

        tempArr.add(inStr.substring(0, actualBreakPoint));
        tempArr.add(inStr.substring(actualBreakPoint));

        return tempArr;
    } //method


    private static void testdivideFileIntoHalf()
    {
        String inFN = ".\\Text\\test.txt";

        String inStr =
            FileUtil.fileToStringSenseParagraphRemoveBlankLines(inFN);

        ArrayList<String> tempArr = stringToTwoHalves(inStr);

        GenUtil.showArrayList(tempArr);

    } //test method


    /**
     * Align center the given string with respect to the given size
     * @param str - the given string
     * @param size - the given size
     */
    public static String alignCenter(String str, int size) {

        int strSize = str.length();
        int blankSize = (size - strSize) / 2;
        str = StringUtil.padLeft(str, blankSize + strSize);
        return StringUtil.padRight(str, size);

    } //method

    private static void testalignCenter()
    {
        String str = "Hi";
        int size = 6;

        System.out.println("[" + alignCenter(str, size) + "]");

    } //test method


    /**
     * write an array list of strings to the given file.
     * @param arr - the given array list
     * @param outFN - the automata file name
     */
    public static String arrayListToString(ArrayList<String> arr) {

        StringBuilder sb = new StringBuilder();

        for (String line : arr)
            sb.append(line).append("\r\n");

        return sb.toString();

    } //method

    private static void testarrayListToFile()
    {
        String inFN = ".\\Text\\MOBY DICK.txt";
        String str = FileUtil.fileToString(inFN);

        //ArrayList<String> arr = StringUtil.s

        String outFN = ".\\Text\\MOBY DICK_O.txt";

        //arrayListToFile(arr, outFN);

    } //test method


    /**
     * chop a string into sentences and return it as an array list.
     * @param inpStr - the given input string
     * @param locale - the file locale
     * @return the chopped string into array list of the sentences
     */
    public static ArrayList<String> stringToSentenceArr(
        String inpStr, Locale locale, boolean unitTerminatorCoded,
        String terminatorStr) {

        BreakIterator boundary = BreakIterator.getSentenceInstance(locale);

        if (unitTerminatorCoded)
            return stringToUnitWithTerminator(boundary, inpStr, terminatorStr);
        else
            return stringToUnit(boundary, inpStr);

    } //method


    private static void teststringToSentenceArr() {

        String inFN  = ".\\SimilarSentence\\learn.txt";
        Locale locale = new Locale("en_US");
        boolean unitTerminatorCoded = true;
        String terminatorStr = "perioddoirep";

        String tempStr = FileUtil.fileToString(inFN);

        System.out.println("*****  after file to string ****");
        System.out.println(tempStr);


        ArrayList<String> sentArr =
            stringToSentenceArr(
            tempStr, locale, unitTerminatorCoded, terminatorStr);

        System.out.println("\n\n*****  after string to sentence array ****");
        StringUtil.showArrayList(sentArr);

    } //test method


    /**
     * Chop a string into units and return them as an array list.
     * @param boundary - the boundary of the chopping. It can be character,
     * word, sentence, and line.
     * @param inpStr - the given input string
     * @return the chopped string as an array list of the units
     */
    private static ArrayList<String> stringToUnit(
        BreakIterator boundary, String inpStr) {

        ArrayList<String> elementArr = new ArrayList();

        boundary.setText(inpStr);
        int start = boundary.first();

        for (int end = boundary.next();
            end != BreakIterator.DONE;
            start = end, end = boundary.next())
                elementArr.add(inpStr.substring(start,end));

        return elementArr;

    } //method


    private static void teststringToUnit() {

        String inFN  = ".\\SimilarSentence\\learn.txt";
        Locale locale = new Locale("en_US");

        String tempStr = FileUtil.fileToString(inFN);
        System.out.println("*****  after file to string ****");
        System.out.println(tempStr);

        BreakIterator boundary = BreakIterator.getSentenceInstance(locale);


        ArrayList<String> sentArr = stringToUnit(boundary, tempStr);

        System.out.println("\n\n*****  after string to sentence array ****");
        StringUtil.showArrayList(sentArr);

    } //test method


    /**
     * Chop a string into the given units, add the terminator and return as an
     * array list.
     * @param boundary - the boundary of the chopping. It can be character,
     * word, sentence, and line.
     * @param inpStr - the given input string
     * @param terminatorStr - the string representing the sentence terminator
     * @return the chopped string into array list of the units
     */
    private static ArrayList<String> stringToUnitWithTerminator(
        BreakIterator boundary, String inpStr, String terminatorStr) {

        ArrayList<String> elementArr = new ArrayList();

        boundary.setText(inpStr);
        int start = boundary.first();

        for (int end = boundary.next();
            end != BreakIterator.DONE;
            start = end, end = boundary.next())

                elementArr.add(inpStr.substring(start,end) + " " +
                    terminatorStr);

        return elementArr;

    } //method


    private static void teststringToUnitWithTerminator() {

        String inFN  = ".\\SimilarSentence\\learn.txt";
        Locale locale = new Locale("en_US");
        String terminatorStr = "perioddoirep";

        String tempStr = FileUtil.fileToString(inFN);

        System.out.println("*****  after file to string ****");
        System.out.println(tempStr);

        BreakIterator boundary = BreakIterator.getSentenceInstance(locale);


        ArrayList<String> sentArr =
            stringToUnitWithTerminator(boundary, tempStr, terminatorStr);

        System.out.println("\n\n*****  after string to sentence array ****");
        StringUtil.showArrayList(sentArr);

    } //test method


    /**
     * Chop a string into paragraphs and return them as an array list.
     * @param inpStr - the given input string
     * @param  locale - the string's locale
     * @return the chopped string into array list of the paragraphs
     */
    public static ArrayList<String> stringToParagraphArr(
        String inpStr, Locale locale,
        boolean unitTerminatorCoded, String terminatorStr) {

        String period = "\r\n\r\n";
        if (unitTerminatorCoded) period += " " + terminatorStr;

        ArrayList<String> sentArr = stringToSentenceArr(
            inpStr, locale, unitTerminatorCoded, terminatorStr);

        ArrayList<String> paragraphArr = new ArrayList();
        StringBuilder parBuilder = new StringBuilder();

        for (String sent : sentArr) {

            parBuilder.append(sent).append(" ");

            if (sent.endsWith(period)) {
                paragraphArr.add(parBuilder.toString());
//                paragraphArr.add("\r\n");
                parBuilder = new StringBuilder();
            }
        } //for

        if (parBuilder.length() != 0)
            paragraphArr.add(parBuilder.toString());

        return paragraphArr;

    } //method


    private static void teststringToParagraphArr()
    {
        String inFN  = ".\\SimilarSentence\\learn.txt";
        Locale locale = new Locale("en_US");
        boolean unitTerminatorCoded = true;
        String terminatorStr = "perioddoirep";

        String tempStr = FileUtil.fileToString(inFN);
        System.out.println("*****  after file to string **** \n[" + tempStr + "]");

        ArrayList<String> sentArr =
            stringToSentenceArr(
            tempStr, locale, unitTerminatorCoded, terminatorStr);
        System.out.println("\n\n*****  after string to sentence array ****");
        StringUtil.showArrayList(sentArr);

        ArrayList<String> parArr =
            stringToParagraphArr(
            tempStr, locale, unitTerminatorCoded, terminatorStr);

        System.out.println("\n\n*****  after string to paragraph array ****");
        StringUtil.showArrayList(parArr);

    } //test method


    /**
     */
//........................ M A I N   M E T H O D ............................//

	/**
	 * This main method is just for testing this class.
	 * @param args the arguments
	 */
	public static void main(String[] args)
	throws ClassNotFoundException, SQLException {

        teststringToParagraphArr();

	} // main method.


} // class



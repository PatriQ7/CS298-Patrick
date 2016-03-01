
package Util;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;

import Control.Config;

/*
 * This class contains all file utilities that this application needs.
 * @author Nikhil Kalantri
 * @version 1.0
 */
public class FileUtil
{
// ...................... P R I V A T E   M E T H O D S ......................//
// ...................... P U B L I C   M E T H O D S ........................//

    /**
     * read the given file and put them in an array list line by line.
     * @param inFN - the given file name
     * @return the array list containing the lines
     */
    public static ArrayList<String> fileToArrayList(String inFN)
    {
        /*.................... T A S K   M E S S A G E .......................*/
        //System.out.format("Reading from file %s %n", inFN);
        /*....................................................................*/

        ArrayList<String> strArr = new ArrayList();
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(new File(inFN)));

            while (reader.ready()) {
                String line = reader.readLine().trim();
                if (line.length() == 0) continue; //empty line
                strArr.add(line);
            } //while

            reader.close();

        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

        return strArr;

    } //method

    public static void testfileToArrayList()
    {
        String inFN = "testPattern.txt";
        ArrayList<String> strArr = fileToArrayList(inFN);

        System.out.println("Array = " + strArr);

    } //test method


    /**
     * read a given file and skip the comment lines and return it as an array list
     * @param inFN - the given file name
     * @param commentChar - the comment line starts with this char
     * @return the array list of strings containing each line
     */
    public static ArrayList<String> fileToArrayListSkipCommentLines(
        String inFN, char commentChar)
    {
        ArrayList<String> tempArr = new ArrayList();
        BufferedReader reader;
        String line;

        try {
            reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(inFN),
                Charset.forName("UTF-8")));

            while(reader.ready()){
                line = reader.readLine().trim();

                //skip blank lines
                if (line.isEmpty()) continue;

                //skip the comment lines
                if (line.charAt(0) != commentChar)
                    tempArr.add(line);

            } //while

            reader.close();

        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

        return tempArr;

    } //method


    private static void testreadPatterns()
    {
        String inFN = "pattern.txt";
        char commentChar = '\'';
        ArrayList<String> patStrArr =
            fileToArrayListSkipCommentLines(inFN, commentChar);
        GenUtil.showArrayList(patStrArr);

    } //method


    /**
     * read a given file and put each line in a hash map.
     * @param inFN - the given file name
     * @return the hash map containing the lines of the given file
     */
    public static HashMap<String, Integer> fileToHashMap(String inFN) {

        HashMap<String, Integer> tempHM = new HashMap<>();
        int i = 0;

        try {
            try (BufferedReader reader =
                new BufferedReader(new FileReader(new File(inFN)))) {

                while (reader.ready()) {
                    tempHM.put(reader.readLine().trim(), i++);
                }
            }
            return tempHM;

        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
            return null;
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            return null;
        }

    } //method


    /**
     * read a given file and put each line in a hash map.
     * the keys and values are separated by comma in the file.
     * @param inFN - the given file name
     * @return the hash map containing the lines of the given file
     */
    public static HashMap<String, Integer> fileKeyCommaValueToHashMap(String inFN) {

        HashMap<String, Integer> tempHM = new HashMap();

        try {
            try (BufferedReader reader =
                new BufferedReader(new FileReader(new File(inFN)))) {
                while (reader.ready()) {
                    String[] keyValueArr = reader.readLine().trim().toLowerCase().split("[,]");
                    tempHM.put(keyValueArr[0], Integer.parseInt(keyValueArr[1]));
                }
            }
            return tempHM;

        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
            return null;
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            return null;
        }

    } //method


    private static void testfileStringCodedToHashMap()
    {
        String functionWordsFN  = ".\\FunctionWords\\functionWords.txt";

        HashMap<String, Integer> funcWordHM =
            fileKeyCommaValueToHashMap(functionWordsFN);
        GenUtil.showMap(funcWordHM);

    } //test method


    private static void cleanUpFunctionWords()
    {
        String functionWordsFN  = ".\\FunctionWords\\Rachel\\functionWords.txt";
        ArrayList<String> arr = FileUtil.fileToArrayList(functionWordsFN);
        Collections.sort(arr);

        //GenUtil.showArrayList(arr);

        String outFN = ".\\FunctionWords\\Rachel\\functionWords2.txt";
        FileUtil.arrayListToFile(arr, outFN);


    } //test method


    /**
     * write an array list of strings to the given file.
     * @param arr - the given array list
     * @param outFN - the output file name
     */
    public static void arrayListToFile(ArrayList<String> arr, String outFN) {

        String str = StringUtil.arrayListToString(arr);
        FileUtil.stringToFile(str, outFN);

    } //method

    private static void testarrayListToFile()
    {
        String inFN = "testPattern.txt";
        ArrayList<String> arr = fileToArrayList(inFN);
        GenUtil.showArrayList(arr);

        String outFN = "testPatternOut.txt";

        arrayListToFile(arr, outFN);

    } //test method


    /**
     * read a folder files names + path from the given path.
     *
     * @param folderName - the given folder name including path
     * @return the array list of the  files names
     */
    public static ArrayList<String> getFolderFNPlusPathArr(
        String folderName) {

        File folder = new File(folderName);
        File[] filesArr = folder.listFiles();

        ArrayList<String> fileNameArr = new ArrayList();

        for (File file : filesArr) {
            fileNameArr.add(file.getPath());

        } //for

        return fileNameArr;

    } //method


    private static void testgetFolderFilesIncludePath()
    {
        String filesPath  = ".\\Text\\";

        ArrayList<String> filesArr = getFolderFNPlusPathArr(filesPath);
        GenUtil.showArrayList(filesArr);

//        ArrayList<String> filePathArr = getFolderFNArr(filesPath);
//        GenUtil.showArrayList(filePathArr);

    } //test method


    /**
     * read the given file and write the odd and even paragraphs into
     * separate files.
     * Pre-condition: the file is cleaned up and paragraphs are separated.
     * @param inpFN - the given input file name
     * @param locale - the input file locale
     * @param oddFN - the odd file name
     * @param evenFN - the even file name
     */
    public static void separateFileIntoOddEvenParagraph(
        String inpFN, Locale locale, String oddFN, String evenFN) {

        String inpStr = fileToString(inpFN);
        //Test: System.out.println("inpStr = " + inpStr);

//        ArrayList<String> sentArr =
//            StringUtil.stringToParagraphArr(inpStr, locale);
//
//        ArrayList<String> oddSentArr = new ArrayList();
//        ArrayList<String> evenSentArr = new ArrayList();
//
//        GenUtil.separateArrToOddEvenArr(sentArr, oddSentArr, evenSentArr);
//
//        FileUtil.arrayListToFile(oddSentArr, oddFN);
//        FileUtil.arrayListToFile(evenSentArr, evenFN);

    } //method


    private static void testseparateFileIntoOddEvenParagraph()
    {
        Locale locale = new Locale("en_US");

        String inpFN  = ".\\Test\\Mark Twain - Adventures of Huckleberry Finn.txt";
        String oddFN  = ".\\TestClean\\Mark Twain - Adventures of Huckleberry Finn_odd_PARAG.txt";
        String evenFN = ".\\TestClean\\Mark Twain - Adventures of Huckleberry Finn_even_PARAG.txt";

        separateFileIntoOddEvenParagraph(inpFN, locale, oddFN, evenFN);

    } //method


    /**
     * read the given file and write the odd and even sentences into separate files.
     * @param inpFN - the given input file name
     * @param locale - the input file locale
     * @param oddFN - the odd file name
     * @param evenFN - the even file name
     */
    public static void separateFileIntoOddEvenSentences(
        String inpFN, Locale locale, String oddFN, String evenFN) {

        String inpStr = fileToString(inpFN);
        //Test: System.out.println("inpStr = " + inpStr);

//        ArrayList<String> sentArr =
//            StringUtil.stringToSentenceArr(inpStr, locale);
//
//        ArrayList<String> oddSentArr = new ArrayList();
//        ArrayList<String> evenSentArr = new ArrayList();
//
//        GenUtil.separateArrToOddEvenArr(sentArr, oddSentArr, evenSentArr);
//
//        FileUtil.arrayListToFile(oddSentArr, oddFN);
//        FileUtil.arrayListToFile(evenSentArr, evenFN);

    } //method


    private static void testseparateFileIntoOddEvenSentences()
    {
        Locale locale = new Locale("en_US");

        String inpFN  = ".\\Test\\Mark Twain - Adventures of Huckleberry Finn.txt";
        String oddFN  = ".\\TestClean\\Mark Twain - Adventures of Huckleberry Finn_odd_SENT.txt";
        String evenFN = ".\\TestClean\\Mark Twain - Adventures of Huckleberry Finn_even_SENT.txt";

        separateFileIntoOddEvenSentences(inpFN, locale, oddFN, evenFN);

    } //method


    /**
     * Read the given file and convert it into a string but sense the
     * paragraphs and return the string.
     * @param inFN - the given input file name
     * @return the files as a string
     */
    public static String fileToStringSenseParagraphRemoveBlankLines(String inFN)
    {
        StringBuilder streamSB = new StringBuilder();
        BufferedReader reader;

        StringBuilder parSB = new StringBuilder();

        try {
            reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(inFN),
                Charset.forName("UTF-8")));

            while(reader.ready()){

                String line = reader.readLine().trim();

                if (line.length() == 0){
                    if (parSB.toString().length() == 0) {
                        continue;

                    } else {
                        parSB.append(line).append("\r\n");
                        streamSB.append(parSB.toString());
                        parSB = new StringBuilder();
                    }

                } else
                    parSB.append(line).append(" ");

            } //while

            if (parSB.toString().length() != 0)
                streamSB.append(parSB.toString()).append("\r\n");

            reader.close();

        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

        return streamSB.toString();

    } //method


    /**
     * Divide a file into two halves.
     * @param inFN - the given input file name
     * @return the number of chars of the file
     */
    public static void divideFileIntoHalf(
        String inFN, String outFN1, String outFN2)
    {
        String fileStr = fileToString(inFN);
        int half = fileStr.length() / 2;
        int breakPoint = fileStr.indexOf('\r', half) + 2; //to include \r\n
        String halfOne = fileStr.substring(0, breakPoint);
        String halfTwo = fileStr.substring(breakPoint);

        FileUtil.stringToFile(halfOne, outFN1);
        FileUtil.stringToFile(halfTwo, outFN2);

    } //method


    private static void testdivideFileIntoHalf()
    {
        String inFN = ".\\Text\\test.txt";
        String outFN1 = ".\\Text\\test_HALF_ONE.txt";
        String outFN2 = ".\\Text\\test_HALF_TWO.txt";

        FileUtil.divideFileIntoHalf(inFN, outFN1, outFN2);

    } //test method


    /**
     * Divide a file into two (almost) halves and return an array list of
     * them and add the input string to the array list as well.
     * @param inFN - the given input file name
     * @return the number of chars of the file
     */
    public static ArrayList<String> divideFileIntoTwoHalfStrPlus(String inFN)
    {
        String fileStr = fileToString(inFN);
        return StringUtil.stringToTwoHalvesPlus(fileStr);

    } //method


    /**
     * Divide a file into two (almost) halves and return an array list of
     * them and add the input string to the array list as well.
     * @param inFN - the given input file name
     * @return the number of chars of the file
     */
    public static ArrayList<String> divideFileIntoTwoHalfStr(String inFN)
    {
        String fileStr = fileToString(inFN);
        return StringUtil.stringToTwoHalves(fileStr);

    } //method


    /**
     * Read the given file and convert it into a string but remove the blank
     * lines.
     * @param inFN - the given input file name
     * @return the files as a string
     */
    public static String fileToStringRemoveBlankLines(String inFN)
    {
        StringBuilder streamSB = new StringBuilder();
        BufferedReader reader;

        try {
            reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(inFN),
                Charset.forName("UTF-8")));

            while(reader.ready()){

                String line = reader.readLine().trim();

                if (line.length() != 0)
                    streamSB.append(line).append("\r\n");

            } //while

            reader.close();

        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

        return streamSB.toString();

    } //method


    /**
     * Remove blank line of a given file.
     * @param inFN - the given input file name
     * @param outFN - the given output file name
     */
    public static void fileRemoveBlankLine(String inFN, String outFN) {

        String str = FileUtil.fileToStringRemoveBlankLines(inFN);
        FileUtil.stringToFile(str, outFN);

    } //method


    /**
     * Clean up all files of a given folder and write them in the given folder.
     * @param inFolder - the given input folder
     * @param outFolder - the given output folder
     * @return the files as string
     */
    public static void folderRemoveBlankLines(String inFolder,
        String outFolder) {

        ArrayList<String> filesArr = getFolderFNArr(inFolder);

        for (String fileName: filesArr) {

            System.out.println("Removing blank lines of file: " + fileName);

            FileUtil.fileRemoveBlankLine(
                inFolder + fileName, outFolder + fileName);
        } //for

    } //method


    private static void testfolderRemoveBlankLines()
    {
        String inFolder   = ".\\Zhang Books\\";
        String outFolder  = ".\\Zhang Books-CNB\\";

        folderRemoveBlankLines(inFolder, outFolder);

    } //method


    /**
     * Divide a file into two (almost) halves and return an array list of
     * them and add the input string to the array list as well.
     * @param inFN
     * @return the number of chars of the file
     */
    /**
     * Divide a file into two sections odds units and even units and return
     * them.
     * @param inFN - the given input file name
     * @param unit - the chopping unit
     * @param locale - the file locale
     * @param oddArr - the returning odd array
     * @param evenArr - the returning even array
     */
//    public static void divideFileIntoOddEvenArr(String inFN, TypeChop unit,
//        Locale locale, ArrayList<String> oddArr, ArrayList<String> evenArr) {
//
//        String fileStr = fileToString(inFN);
//        ArrayList<String> unitArr = new ArrayList();
//
//        /*...................................................................*/
////        if      (unit.equals(TypeChop.SENTENCE))
////            unitArr = StringUtil.stringToSentenceArr(fileStr, locale);
////        else if (unit.equals(TypeChop.PARAGRAPH))
////            unitArr = StringUtil.stringToParagraphArr(fileStr, locale);
//        /*...................................................................*/
//
//        GenUtil.separateArrToOddEvenArr(unitArr, oddArr, evenArr);
//
//    } //method


    /**
     * Read all files names of a given folder name. Note that the file name
     * does not contain the path.
     *
     * @param folderName - the given folder name including the path
     * @return the array list of the  files names
     */
    public static ArrayList<String> getFolderFNArr(String folderName) {

        File folder = new File(folderName);
        File[] filesArr = folder.listFiles();

        ArrayList<String> fileNameArr = new ArrayList();

        for (File file : filesArr) {
            fileNameArr.add(file.getName()); //no path

        } //for

        return fileNameArr;

    } //method

    /**
     * Create a folder with the given name.
     * Pre-condition: the folder name doesn't contain sub-folder.
     * Pre-condition: the folder exists and is really a folder.
     * @param folderName - the given folder name including the path
     */
    public static void createFolder(String folderName) {

        File dir = new File(folderName);
        dir.mkdir();

    } //method

    /**
     * Copy a file with the given name to somewhere else.
     * Pre-condition: the folder name doesn't contain sub-folder.
     * Pre-condition: the folder exists and is really a folder.
     * @param folderName - the given folder name including the path
     */
    public static void copyFile(String fileName, String folderName) {

        String fileStr = FileUtil.fileToString(fileName);
        FileUtil.stringToFile(fileStr, folderName);

    } //method

    /**
     * Delete a folder and its contents.
     * Pre-condition: the folder doesn't contain sub-folder.
     * @param folderName - the given folder name including the path
     * @return true if the operation is successful and false otherwise.
     */
    public static void deleteFolder(String folderName) {

        File folder = new File(folderName);
        if (!folder.exists() || !folder.isDirectory()) return;
        File[] filesArr = folder.listFiles();

        for (File file : filesArr)
            file.delete();

        folder.delete();

    } //method

    /**
     * Delete a the contents (files) of a folder.
     * Pre-condition: the folder doesn't contain sub-folder.
     * @param folderName - the given folder name including the path
     * @return true if the operation is successful and false otherwise.
     */
    public static void deleteFolderContent(String folderName) {

        File folder = new File(folderName);

        if (!folder.exists() || !folder.isDirectory()) return;
        File[] filesArr = folder.listFiles();

        for (File file : filesArr)
            file.delete();

    } //method

    private static void testgetFolderFiles()
    {
        String filesPath  = ".\\test\\";

        ArrayList<String> filePathArr = getFolderFNArr(filesPath);
        GenUtil.showArrayList(filePathArr);

    } //test method


    /**
     * write an array list of strings to the given file.
     * @param arr - the given array list
     * @param outFN - the automata file name
     */
    public static void stringToFile(String str, String outFN) {

        BufferedWriter writer;

        try {
            writer = new BufferedWriter(
                new OutputStreamWriter(
                new FileOutputStream(outFN),
                Charset.forName("UTF-8")));

            writer.write(str);
            writer.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    } //method

    private static void teststringToFile()
    {
        String inFN = ".\\Test\\MOBY DICK.txt";
        String str = FileUtil.fileToString(inFN);

        String outFN = ".\\Test\\MOBY DICK_O.txt";
        FileUtil.stringToFile(str, outFN);

    } //test method


    private static void test()
    {
        File testFN = new File(".\\Temp\\HP1.txt");

        String fileStr = FileUtil.fileToString(testFN.getPath());
        FileUtil.stringToFile(fileStr, ".\\MyTemp\\");

    } //test method


    /**
     * Clean up all files of a given folder and write them in the given folder.
     * @param inFolder - the given input folder
     * @param outFolder - the given output folder
     * @param removeBlankLine - removes the blank lines if this is true.
     */
    public static ArrayList<String> folderToArrayList(String inFolder) {

        ArrayList<String> unitArr = new ArrayList();
        ArrayList<String> filesArr = FileUtil.getFolderFNPlusPathArr(inFolder);

        for (String fileName : filesArr) {

            unitArr.add(FileUtil.fileToString(fileName));

        } //for

        return unitArr;

    } //method


    private static void testfolderToArrayList()
    {
        String inFolder   = ".\\SmallTest\\";

        ArrayList<String> unitArr = folderToArrayList(inFolder);
        GenUtil.showArrayList(unitArr);

    } //method


    /**
     * Read a given file and put each line in a hash map. The lines contain
     * the stop words, and the function word codes. This method ignore the given
     * code and put the line number as the value instead.
     * @param inFN - the given file name
     * @return the hash map containing the lines of the given file
     */
    public static HashMap<String, Integer> fileToHashMapLineNumberAsKey(
        String inFN) {

        HashMap<String, Integer> tempHM = new HashMap();

        try {
            try (BufferedReader reader =
                new BufferedReader(new FileReader(new File(inFN)))) {
                Integer val = 1;

                while (reader.ready()) {
                    String[] keyValueArr = reader.readLine().trim().toLowerCase().split("[,]");
                    tempHM.put(keyValueArr[0], val++);
                }
            }
            return tempHM;

        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
            return null;
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            return null;
        }

    } //method


    private static void testfileToHashMapLineNumberAsKey()
    {
        String functionWordsFN  = ".\\FunctionWords\\functionWords.txt";

        HashMap<String, Integer> funcWordHM =
            fileToHashMapLineNumberAsKey(functionWordsFN);
        GenUtil.showMap(funcWordHM);

    } //test method


    /**
     * read the given file and return it as a string.
     * Modified by Prashanth Sandela. Below commented is original function
     * @param inFN - the given input file name
     * @return the files as string
     */
    public static String fileToString(String inFN)
    {
        StringBuilder streamSB = new StringBuilder();
        StringBuilder tempSB = new StringBuilder();
        BufferedReader reader;
        //BufferedReader checkreader;

        try {
            reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(inFN),
                Charset.forName("UTF-8")));
            /*checkreader = new BufferedReader(
                new InputStreamReader(new FileInputStream(inFN),
                Charset.forName("UTF-8")));*/

            String line;
            //String checkStr = checkreader.readLine().trim().substring(0, 1);

            if(Config.getProperty("PROGRAM_TYPE").equals("DNA") && Config.getProperty("PROGRAM_SUBTYPE").equals("DNA"))
            //if (checkStr.equalsIgnoreCase("A"))
            {
                //System.out.println(Config.getProperty("PROGRAM_TYPE"));
            	//Get data and Chop for every 3 characters
                int j = 1;
            	int lineNumber = 0;
            	boolean isStart = false; 
            	while(reader.ready()){
            		String tempStr = reader.readLine().trim();
            		int size = tempStr.length();
            		lineNumber++;
            		
            		for(int i = 0; i < size; i++, j++)
                	{
            			tempSB.append(tempStr.charAt(i));
            			int mid_nodes_count = 0;
                		if( j % 3 == 0)
                		{
                			
                        	String s = tempSB.toString();
                        		
                        	//Recognize start and end of Sequence
                        	if(isStart)//end
                    		{
                    			if(s.equalsIgnoreCase("TGA") || s.equalsIgnoreCase("TAG") || s.equalsIgnoreCase("TAA"))
                    			{
//                    				streamSB.append(s + " ");
                    				if(mid_nodes_count == 0)
                    					streamSB.append(" ");
                    				streamSB.append("\r\n");
                    				mid_nodes_count++;
                    				isStart = false;
                    			} else 
                    			{
                    				streamSB.append(s + " ");
                    				isStart = true;
                    			}
                    		} else if(s.equalsIgnoreCase("ATG"))//|| s.equalsIgnoreCase("ATT") || s.equalsIgnoreCase("ATA")) //start
                    		{
//                    			streamSB.append(s + " ");
                    			mid_nodes_count = 0;
                    			isStart = true;
                    		} else 
                    		{
                    			isStart = false;
                    		}

                			tempSB = new StringBuilder();
                		}
                	}
            		
            	}
            	
            }
            
            if(Config.getProperty("PROGRAM_TYPE").equals("DNA") && Config.getProperty("PROGRAM_SUBTYPE").equals("STR"))
            //if (checkStr.equalsIgnoreCase("S"))
            {
            	//Get data and Chop for every 3 characters
            	int j = 1;
            	int lineNumber = 0;
            	boolean isStart = false; 
            	while(reader.ready()){
            		String tempStr = reader.readLine().trim();
            		int size = tempStr.length();
            		lineNumber++;
            		
            		for(int i = 0; i < size; i++, j++)
                	{
            			tempSB.append(tempStr.charAt(i));
            			int mid_nodes_count = 0;
                		if( j % 1 == 0)
                		{
                			
                        	String s = tempSB.toString();
                        		
                        	//Recognize start and end of Sequence
                        	if(isStart)//end
                    		{
                    			//if(s.equalsIgnoreCase("TGA") || s.equalsIgnoreCase("TAG") || s.equalsIgnoreCase("TAA"))
                                        if(s.equalsIgnoreCase("E"))
                    			{
//                    				streamSB.append(s + " ");
                    				if(mid_nodes_count == 0)
                    					streamSB.append(" ");
                    				streamSB.append("\r\n");
                    				mid_nodes_count++;
                    				isStart = false;
                    			} else 
                    			{
                    				streamSB.append(s + " ");
                    				isStart = true;
                    			}
                    		} else if(s.equalsIgnoreCase("S")) //start
                    		{
//                    			streamSB.append(s + " ");
                    			mid_nodes_count = 0;
                    			isStart = true;
                    		} else 
                    		{
                    			isStart = false;
                    		}

                			tempSB = new StringBuilder();
                		}
                	}
            		
            	}
            	
            } 
            else
            {
            	while(reader.ready()){

                    line = reader.readLine().trim();

                    if (line.length() == 0)
                        streamSB.append("\r\n");
                    else
                        streamSB.append(line).append("\r\n");

                } //while
            }
            
            reader.close();

        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

        return streamSB.toString();
    } //method
    
//    public static String fileToString(String inFN)
//    {
//        StringBuilder streamSB = new StringBuilder();
//        BufferedReader reader;
//
//        try {
//            reader = new BufferedReader(
//                new InputStreamReader(new FileInputStream(inFN),
//                Charset.forName("UTF-8")));
//
//            while(reader.ready()){
//
//                String line = reader.readLine().trim();
//
//                if (line.length() == 0)
//                    streamSB.append("\r\n");
//                else
//                    streamSB.append(line).append("\r\n");
//
//            } //while
//
//            reader.close();
//
//        } catch (FileNotFoundException ex) {
//            System.err.println(ex.getMessage());
//        } catch (IOException ex) {
//            System.err.println(ex.getMessage());
//        }
//
//        return streamSB.toString();
//
//    } //method


    private static void testfileToString()
    {
        String inFN  = ".\\SimilarSentence\\learn.txt";

        String tempStr = FileUtil.fileToString(inFN);
        System.out.println("*****  after file to string **** \n[" + tempStr + "]");

    } //test method


    /**
     */
// ........................ M A I N   M E T H O D ............................//

    /**
     * This main method is just for testing this class.
     * @param args - the arguments
     */
    public static void main(String[] args)
    {
        /* ....................................................*/

        testfileToString();

        /* ....................................................*/

    } //main method

} //class


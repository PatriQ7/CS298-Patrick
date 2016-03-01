
package Control;

import Util.FileUtil;
import Util.StringUtil;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Locale;

/*
 * This abstract class is in charge to provide facilitites for chopping the
 * doc string.
 * @author Nikhil Kalantri
 * @modified Prashanth Sandela
 * @version 1.0
 */
public abstract class Chopper {

// ...................... P R I V A T E   M E T H O D S ......................//

    /**
     * Modified by Prashanth Sandela
     * Chop a string into units and return them as an array list.
     * @param boundary - the boundary of the chopping. It can be character,
     * word, sentence, and line.
     * @param inpStr - the given input string
     * @return the chopped string as an array list of the units
     */
    static ArrayList<String> stringToUnit(
        BreakIterator boundary, String inpStr) {

        ArrayList<String> elementArr = new ArrayList();

        if(Config.getProperty("PROGRAM_TYPE").toUpperCase().equals("DNA"))
        {
        	String[] tempStr = inpStr.split("\n");
        	for(String s: tempStr)
        	{
        		s = s.trim();
        		if(s != null)
        		{
        			elementArr.add(s);
        		}
        	}
        }
        else 
        {
        	boundary.setText(inpStr);
            int start = boundary.first();

            for (int end = boundary.next();
                end != BreakIterator.DONE;
                start = end, end = boundary.next())
                    elementArr.add(inpStr.substring(start,end));

        }
        
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
    static ArrayList<String> stringToUnitWithTerminator(
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



// ...................... P U B L I C   M E T H O D S ........................//
    public abstract ArrayList<String> chop(
        String inpStr, Locale locale, boolean unitTerminatorCoded,
        String terminatorStr);
// ........................ M A I N   M E T H O D ............................//

} //class


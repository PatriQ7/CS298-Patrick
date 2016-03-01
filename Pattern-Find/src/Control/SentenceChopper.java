
package Control;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Locale;

/*
 * This class chops the string into sentences.
 * @author Nikhil Kalantri
 * @version 1.0
 */
public class SentenceChopper extends Chopper {

// ...................... P U B L I C   M E T H O D S ........................//

    @Override
    public ArrayList<String> chop(
        String inpStr, Locale locale, boolean unitTerminatorCoded,
        String terminatorStr) {

        BreakIterator boundary = BreakIterator.getSentenceInstance(locale);

        if (unitTerminatorCoded)
            return stringToUnitWithTerminator(boundary, inpStr, terminatorStr);
        else
            return stringToUnit(boundary, inpStr);

    } //method


    /**
     * return the name of the algorithm.
     */
    @Override
    public String toString() {

        return "Sentence";

    } //method

} //class


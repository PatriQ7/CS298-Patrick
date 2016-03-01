
package Beans;

import Util.Tokenizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Properties;

/**
 * 
 * Convert an array of string (strings can be sentences, paragraphs, or the
 * whole book) into a document object that contains the array of sybmols.
 */
public class Document
{
// ........................ D A T A   F I E L D S ............................//
// ............. G L O B A L   P R I V A T E   C O N S T A N T S .............//
// .............. G L O B A L   P U B L I C   C O N S T A N T S ..............//
// ................. G L O B A L   P R I V A T E   V A R S ...................//

    /** hold the empty string symbol */
    private String emptyStringSymbol;

    /** hold the input file name that is going to be converted to document. */
    private String fileName;

    /** hold the file locale. */
    private Locale locale = new Locale("en_US"); //default value

    /** hold function words hash map */
    private HashMap<String, Integer> functionWordsHM;

    /** hold the document as a array list */
    private ArrayList<String> symbolUnitArr;

// ........................ C O N S T R U C T O R S ..........................//

    /**
     * Construct an object of Document
     * @param config - the configuration file properties
     * @param functionWordsHM - the hash map of the function words
     * @param unit - the unit of measurement
     * @param unitArr - the given unit array. Unit can be sentence or paragraph.
     */
    public Document(Properties config, HashMap<String, Integer> functionWordsHM,
        ArrayList<String> unitArr) {
        /*Since we provided the file as a string, so the file name is not
         important anymore! */
        fileName = "Not Important!";
        locale = new Locale(config.getProperty("LOCALE"));
        emptyStringSymbol = config.getProperty("EMPTY_STRING_SYMBOL");
        this.functionWordsHM = functionWordsHM;
        symbolUnitArr = buildSymbolUnitArr(unitArr);

    } //constructor

    /**
     * Construct an object of Document
     * @param config - the configuration file properties
     * @param functionWordsHM - the hash map of the function words
     * @param unit - the unit of measurement
     * @param unitArr - the given unit array. Unit can be sentence or paragraph.
     */
    public Document(
        Properties config, HashMap<String, Integer> functionWordsHM,
        ArrayList<String> unitArr, String fileName) {

        this.fileName = fileName;
        locale = new Locale(config.getProperty("LOCALE"));
        emptyStringSymbol = config.getProperty("EMPTY_STRING_SYMBOL");
        this.functionWordsHM = functionWordsHM;
        symbolUnitArr = buildSymbolUnitArr(unitArr);

    } //constructor


//.............................. G E T T E R S ...............................//


    public ArrayList<String> getSymbolUnitArr() {
        return symbolUnitArr;
    }


    public String getEmptyStringSymbol() {
        return emptyStringSymbol;
    }


    public String getFileName() {
        return fileName;
    }

//.............................. S E T T E R S ...............................//

    public void setFunctionWordsHM(HashMap<String, Integer> functionWordsHM) {
        this.functionWordsHM = functionWordsHM;
    }


// ...................... P R I V A T E   M E T H O D S ......................//

    /**
     * Convert the given unit array to symbol array and return it.
     * @param unitArr - the given unit array
     * @return the array of converted strings into symbols.
     */
    private ArrayList<String> buildSymbolUnitArr(ArrayList<String> unitArr) {

        //GenUtil.showArrayList(unitArr);

        //the size is adjusted for efficiency
        ArrayList<String> tempSymbolUnitArr = new ArrayList(unitArr.size());

        for (String unitElement : unitArr) {

            String symbolStr =
                convertUnitElement2Symbol(unitElement, locale);
            tempSymbolUnitArr.add(symbolStr);

        } //for

        //GenUtil.showArrayList(tempSymbolUnitArr);

        return tempSymbolUnitArr;

    } //method
    
    
    /**
     * Convert the unit element to lower case, tokenize it and
     * remove the non-function words of a unit element and code the reset
     * of the given unit element into symbols of function words.
     * @param oneUnit - the given string
     * @param locale - the locale of the string
     * @return the coded string
     */
    private String convertUnitElement2Symbol(String oneUnit, Locale locale)
    {
        ArrayList<String> oneUnitTokenArr =
            Tokenizer.simpleTokenizer(oneUnit.toLowerCase(), locale);

        StringBuilder sb = new StringBuilder();

        for (String token : oneUnitTokenArr) {

            Integer symbol = functionWordsHM.get(token);

            if (symbol != null) // = funtion word
                sb.append(symbol).append(" ");

        } //for

        String tempStr = sb.toString().trim();

        if (tempStr.length() == 0)
            return emptyStringSymbol;

        return tempStr;

    } //method
    
    
    @Override
    public String toString() {
        
        StringBuilder sb = new StringBuilder();
        sb.append("\nFile Name = ").append(fileName);
        sb.append("\n[").append(symbolUnitArr).append("]\n\n\n");
        
        return sb.toString();
        
    } //method


    /**
     */
} //class

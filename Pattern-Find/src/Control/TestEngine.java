package Control;

import Beans.Document;
import Beans.TestCond;
import Beans.TypeCode;
import Test.TestBody;
import Test.TestMatrix;
import Util.AYLogger;
import Util.FileUtil;
import Util.GenUtil;
import Util.ReportUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Properties;

/**
 * this class is in charge to control all pattern checking stuffs.
 * Debug Code = 100
 */
public class TestEngine {

// ........................ D A T A   F I E L D S ............................//
// ............. G L O B A L   P R I V A T E   C O N S T A N T S .............//
    //private final static String TEMP_FOLDER_NAME = ".\\MyTemp";
// .............. G L O B A L   P U B L I C   C O N S T A N T S ..............//
// ................. G L O B A L   P R I V A T E   V A R S ...................//


    /** hold configuration object which contains all initialization parameters. */
    private Properties config;

    /** hold locale object. */
    private static Locale locale;

    /** Hold function words file name. */
    private String functionWordsFN;

    /** Hold an object of TestBody. */
    private TestBody testBody;

    /** Hold terminator string */
    private String terminatorStr;

    /** Hold an object of program's logger. */
    private AYLogger logger;

    /** hold stop words hash map <token, numeric symbol>
     * the number start from 1. The number 0 represent empty string.
     */
    private static HashMap<String, Integer> functionWordsHM;


// ........................ C O N S T R U C T O R S ..........................//

    public TestEngine(Properties config, AYLogger logger)
    {
        this.config = config;
        this.logger = logger;

        terminatorStr = config.getProperty("SENTENCE_TERMINATOR");
        functionWordsFN = config.getProperty("FUNCTION_WORDS_FILE");
        functionWordsHM = loadFunctionWords();
        locale = new Locale(config.getProperty("LOCALE"));
        logger.record("test engine started.");

    } //constructor

//.............................. G E T T E R S ...............................//

    public TestMatrix getTMatrix() {
        return testBody.gettMatrix();
    }

    public TestBody getTestBody() {
        return testBody;
    }


//.............................. S E T T E R S ...............................//

    public void setFunctionWords(String functionWordsFN) {

        this.functionWordsFN = functionWordsFN;
        functionWordsHM = loadFunctionWords();
    }

    public void switchToStopWords() {

        /*............. Logging Task ..............................*/
        String taskMsg = String.format(
            "switched to new function word %s.", functionWordsFN);
        logger.record(taskMsg);
        /*.........................................................*/

        functionWordsHM =
            FileUtil.fileToHashMapLineNumberAsKey(functionWordsFN);
    }


// ...................... P R I V A T E   M E T H O D S ......................//
// ...................... P U B L I C   M E T H O D S ........................//


    /**
     * Print the test case report.
     */
    public void printTestBody() {

         System.out.println(testBody.toString());
//         logger.record("\n\n" + testBody.toString());

    } //method


    /**
     * Read function words file and put them in a hash map.
     * Pre-condition: the file's line contain stop word as the key and comma
     * separated with the value which is the function word code.
     * @return the hash map containing the function words
     */
    private HashMap<String, Integer> loadFunctionWords() {

        /*............. Logging Task ..............................*/
        String taskMsg = String.format(
            "loading function words file %s\n", functionWordsFN);
        logger.record(taskMsg);
        /*.........................................................*/

        return FileUtil.fileKeyCommaValueToHashMap(functionWordsFN);
    } //method


    /**
     * Convert a folder of documents to an array list of Documents objects
     * @param docFolder - the folder containing the documents
     * @param unit - the chopping unit
     * @return an array list of the Document objects.
     */
    private ArrayList<Document> getDocArr(
        String docFolder, Chopper chopper, boolean unitTerminatorCoded) {

        ArrayList<Document> docArr = new ArrayList<Document>();

        ArrayList<String> filesNamesArr =
            FileUtil.getFolderFNPlusPathArr(docFolder);

        for (String fileName : filesNamesArr) {

        	System.out.println("Loading Test File: " + fileName);
            String docStr = FileUtil.fileToString(fileName);

            ArrayList<String> unitArr = chopper.chop(
                docStr, locale, unitTerminatorCoded, terminatorStr);

            System.out.println("Generating Documment ArrayList of Testing documents..");
            docArr.add(
                new Document(config, functionWordsHM, unitArr, fileName));

        } //for

        return docArr;

    } //method


    /**
     * Convert a folder of documents to an array list of its unit array list.
     * @param docFolder - the folder containing the documents
     * @param unit - the chopping unit
     * @return an array list of its unit array list
     */
    private ArrayList<ArrayList<String>> getUnitArrArr(
        String docFolder, Chopper chopper, boolean unitTerminatorCoded) {

        ArrayList<ArrayList<String>> unitArrArr = new ArrayList();

        ArrayList<String> filesNamesArr =
            FileUtil.getFolderFNPlusPathArr(docFolder);

        for (String fileName : filesNamesArr) {

            String docStr = FileUtil.fileToString(fileName);

            unitArrArr.add(chopper.chop(
                docStr, locale, unitTerminatorCoded, terminatorStr));

        } //for

        return unitArrArr;

    } //method


    /**
     * Do learning phase with half of a doc and testing phase with the
     * other half.
     *
     * @param tCond - the test condition object
     */
    public void doHalfBookTest(TestCond tCond) {

        if (tCond.getCoding().equals(TypeCode.STOP_WORD)) switchToStopWords();

        ArrayList<String> testFNArr =
            FileUtil.getFolderFNArr(tCond.getTestFolder());

        ArrayList<Document> learnDocArr = new ArrayList();
        ArrayList<Document> testDocArr = new ArrayList();

        prepareHalfBookTest(tCond, testDocArr, learnDocArr);

        //Note that testFNArr and learnFNArr are the same in this test.
        testBody = new TestBody(config, functionWordsHM,
            learnDocArr, testDocArr, testFNArr, testFNArr, tCond, logger);

    } //method


    /**
     * Prepare all data structures needed for half book test.
     * Pre-Condition: testDocArr and learnUnitArr are empty
     * @param docFolder - the folder containing the documents
     * @param unit - the chopping unit
     * @param testDocArr - an empty test doc array that would be filled
     * @param learnUnitArr - an empty learn unit array that would be filled
     * @return both testDocArr and learnUnitArr in the arguments.
     */
    private void prepareHalfBookTest(
        TestCond tCond,
        ArrayList<Document> testDocArr, ArrayList<Document> learnDocArr) {

        ArrayList<String> filesNamesArr =
            FileUtil.getFolderFNPlusPathArr(tCond.getTestFolder());

        for (String fileName : filesNamesArr) {

            ArrayList<String> docTwoHalfStrArr =
                FileUtil.divideFileIntoTwoHalfStr(fileName);

            ArrayList<String> learnUnitArr = tCond.getLearnChopper().chop(
            docTwoHalfStrArr.get(0), locale, tCond.isUnitTerminatorCoded(),
            terminatorStr);
            if (tCond.isDebugMode(101))
                ReportUtil.showDebugInfo(TestEngine.class, learnUnitArr, 101);

            ArrayList<String> testUnitArr = tCond.getLearnChopper().chop(
            docTwoHalfStrArr.get(1), locale, tCond.isUnitTerminatorCoded(),
            terminatorStr);
            if (tCond.isDebugMode(103))
                ReportUtil.showDebugInfo(TestEngine.class, testUnitArr, 103);

            learnDocArr.add(new Document(
                config, functionWordsHM, learnUnitArr, fileName + "_1st Half"));

            testDocArr.add(new Document(
                config, functionWordsHM, testUnitArr, fileName + "_2nd Half"));

        } //for

    } //method


    /**
     * Do learning phase with one doc and do testing phase with the documents
     * in a folder over a range of alpha.
     *
     * @param tCond - the test condition object
     */
     public void doRegularTests(TestCond tCond) {

        if (tCond.getCoding().equals(TypeCode.STOP_WORD)) switchToStopWords();

        String learnStr = FileUtil.fileToString(tCond.getLearnDescr());
        if (tCond.isDebugMode(100))
            ReportUtil.showDebugInfo(TestEngine.class, learnStr, 100);
     }
    public void doRegularTest(TestCond tCond) {

        if (tCond.getCoding().equals(TypeCode.STOP_WORD)) 
        	switchToStopWords();

        
        System.out.println("Converting Leaning file to String...");
        String learnStr = FileUtil.fileToString(tCond.getLearnDescr());
        //System.out.println(learnStr);
        if (tCond.isDebugMode(100));
            //ReportUtil.showDebugInfo(TestEngine.class, learnStr, 100);

        System.out.println("Learning Leaner Unit Array..");
        ArrayList<String> learnUnitArr =
            tCond.getLearnChopper().chop( 
            learnStr, locale, tCond.isUnitTerminatorCoded(), terminatorStr);
        //System.out.println(learnUnitArr.size());
        if (tCond.isDebugMode(101))
            ReportUtil.showDebugInfo(TestEngine.class, learnUnitArr, 101);

        System.out.println("Get Test folder elements..");
        ArrayList<String> testFNArr =
            FileUtil.getFolderFNArr(tCond.getTestFolder());
        if (tCond.isDebugMode(104))
            ReportUtil.showDebugInfo(TestEngine.class, testFNArr, 104);

        System.out.println("Getting Test Documents..");
        ArrayList<Document> testDocArr = getDocArr(
            tCond.getTestFolder(), tCond.getTestChopper(),
            tCond.isUnitTerminatorCoded());
//        if (tCond.isDebugMode(102))
//            ReportUtil.showDebugInfo(TestEngine.class, testDocArr, 102);

        System.out.println("Generating Test Body");
        testBody = new TestBody(
            config, functionWordsHM, learnUnitArr, testDocArr, testFNArr,
            tCond, logger);

    } //method


    /**
     * Prepare all data structures needed for scramble test.
     * Pre-Condition: testDocArr and learnUnitArr are empty
     *
     * @param tCond - the test condition object
     * @param testDocArr - an empty test doc array that would be filled
     * @param learnUnitArr - an empty learn unit array that would be filled
     * @return both testDocArr and learnDocArr.
     */
    private void prepareScrambleTest(
        TestCond tCond,
        ArrayList<Document> testDocArr, ArrayList<Document> learnDocArr) {

        ArrayList<String> filesNamesArr =
            FileUtil.getFolderFNPlusPathArr(tCond.getTestFolder());

        for (String fileName : filesNamesArr) {

            String learnStr = FileUtil.fileToString(fileName);
            if (tCond.isDebugMode(100))
                ReportUtil.showDebugInfo(TestEngine.class, learnStr, 100);

            ArrayList<String> learnUnitArr =
                tCond.getLearnChopper().chop(
                learnStr, locale, tCond.isUnitTerminatorCoded(), terminatorStr);
            if (tCond.isDebugMode(101))
                ReportUtil.showDebugInfo(TestEngine.class, learnUnitArr, 101);

            // testUnitArr is the scrambled of learnUnitArr.
            ArrayList<String> testUnitArr =
                GenUtil.scrambleArrayList(learnUnitArr);
            if (tCond.isDebugMode(103))
                ReportUtil.showDebugInfo(TestEngine.class, testUnitArr, 103);

            learnDocArr.add(new Document(
                config, functionWordsHM, learnUnitArr, fileName));

            testDocArr.add(new Document(
                config, functionWordsHM, testUnitArr, fileName + "_scrambled"));

        } //for

    } //method


    /**
     * Do learning phase with one doc and do testing phase with the documents
     * sentences scrambled in a folder over a range of alpha.
     * The goal of this test is to make sure that the automat really is
     * capturing the sentence.
     *
     * @param tCond - the test condition object
     */
    public void doScrambleTest(TestCond tCond) {

        if (tCond.getCoding().equals(TypeCode.STOP_WORD)) switchToStopWords();

        ArrayList<String> testFNArr =
            FileUtil.getFolderFNArr(tCond.getTestFolder());

        ArrayList<Document> testDocArr = new ArrayList();
        ArrayList<Document> learnDocArr = new ArrayList();

        prepareScrambleTest(tCond, testDocArr, learnDocArr);

        testBody = new TestBody(config, functionWordsHM,
            learnDocArr, testDocArr, testFNArr, testFNArr, tCond, logger);
        //Note that testFNArr and learnFNArr are the same in this test.

    } //method


    /**
     * Prepare all data structures needed for odd/even test.
     *
     * @param tCond - the test condition object
     * @param testDocArr - an empty test doc array that would be filled
     * @param learnUnitArr - an empty learn unit array that would be filled
     * @return both testDocArr and learnDocArr.
     */
    private void prepareOddEvenTest(TestCond tCond,
        ArrayList<Document> testDocArr, ArrayList<Document> learnDocArr) {

        ArrayList<String> filesNamesArr =
            FileUtil.getFolderFNPlusPathArr(tCond.getTestFolder());

        for (String fileName : filesNamesArr) {

            String learnStr = FileUtil.fileToString(fileName);
            if (tCond.isDebugMode(100))
                ReportUtil.showDebugInfo(TestEngine.class, learnStr, 100);

            ArrayList<String> unitArr =
                tCond.getLearnChopper().chop(
                learnStr, locale, tCond.isUnitTerminatorCoded(), terminatorStr);

            ArrayList<String> learnUnitArr = new ArrayList();
            ArrayList<String> testUnitArr  = new ArrayList();

            GenUtil.separateArrToOddEvenArr(unitArr, learnUnitArr, testUnitArr);

            if (tCond.isDebugMode(101))
                ReportUtil.showDebugInfo(TestEngine.class, learnUnitArr, 101);
            if (tCond.isDebugMode(103))
                ReportUtil.showDebugInfo(TestEngine.class, testUnitArr, 103);

            learnDocArr.add(new Document(
                config, functionWordsHM, learnUnitArr, fileName + "_odd"));

            testDocArr.add(new Document(
                config, functionWordsHM, testUnitArr, fileName + "_even"));
        } //for

    } //method


    /**
     * Do learning phase with odd units of a doc and do testing phase with the
     * even units of the same doc over a range of alpha.
     *
     * @param tCond - the test condition object
     */
    public void doOddEvenTest(TestCond tCond) {

        if (tCond.getCoding().equals(TypeCode.STOP_WORD)) switchToStopWords();

        ArrayList<String> testFNArr =
            FileUtil.getFolderFNArr(tCond.getTestFolder());

        ArrayList<Document> learnDocArr = new ArrayList();
        ArrayList<Document> testDocArr = new ArrayList();

        prepareOddEvenTest(tCond, testDocArr, learnDocArr);

        testBody = new TestBody(config, functionWordsHM,
            learnDocArr, testDocArr, testFNArr, testFNArr, tCond, logger);
        //Note that testFNArr and learnFNArr are the same in this test.

    } //method


    /**
     * Do learning phase with a folder of documents such that chopping unit is
     * the whole of a book, and do testing phase with one document
     * over a range of alpha.
     *
     * @param tCond - the test condition object
     */
    public void doWholeBookTest(TestCond tCond) {

        if (tCond.getCoding().equals(TypeCode.STOP_WORD)) switchToStopWords();

        ArrayList<String> testFNArr =
            FileUtil.getFolderFNArr(tCond.getTestFolder());

        ArrayList<Document> testDocArr =
            getDocArr(tCond.getTestFolder(), tCond.getTestChopper(),
            tCond.isUnitTerminatorCoded());

        ArrayList<String> learnUnitArr =
            FileUtil.folderToArrayList(tCond.getLearnFolder());

        testBody = new TestBody(config, functionWordsHM,
                learnUnitArr, testDocArr, testFNArr, tCond, logger);

        printTestBody();

    } //method


    /**
     */
} //class

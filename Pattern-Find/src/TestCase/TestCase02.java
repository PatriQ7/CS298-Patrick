
package TestCase;

import Beans.*;
import Control.*;
import Util.*;
import java.io.*;

/*
 * This class tests for writer's pattern for various amount of parameters
 *
 * @author Nikhil Kalantri
 * @version 1.0
 */
public class TestCase02
{
// ...................... P R I V A T E   M E T H O D S ......................//
// ...................... P U B L I C   M E T H O D S ........................//


    /**
     * Test
     */
    public static void test04(TestEngine testEngn) {

        /*................. T E S T   D E S C R I P T I O N ..................*/
        String title = "TEST CASE# 04: Scramble Test\n"
            + "Results of learning with one doc and testing with \n"
            + "the scrambled of the same doc.";
        System.out.format("%n%n" + title + "%n");
        /*....................................................................*/

        double startAlpha = 0.4;
        double incr = 0.1;
        double upLimit = 1.0;

        TypeCode coding = TypeCode.FUNCTION_WORD;
        boolean unitTerminatorCoded = true;
        boolean forceAutoReturn = true;

        /*....................................................................*/
        Chopper learnChopper = new SentenceChopper();
        Chopper testChopper = new SentenceChopper();
        MergeAlg mergeAlg = new AlgAlergiaBFS();
        int[] debugPoints = {100, 102, 200, 300};
        //MergeAlg mergeAlg = new AlgAlergiaBFS();

        int[] range = {0, 51, 76, 101};
        String learnDescr = "Each book is learned and are tested against its "
            + "scrambled version.";
        String testFolder = ".\\BooksMix\\";
        /*....................................................................*/
        TestCond tCond = new TestCond(
            startAlpha, incr, upLimit,
            learnDescr, null, testFolder, coding,
            unitTerminatorCoded, forceAutoReturn, debugPoints,
            learnChopper, testChopper, mergeAlg);

        /*....................................................................*/
        testEngn.doScrambleTest(tCond);
        StatUtil stat = new StatUtil(testEngn.getTMatrix(), range);

        testEngn.printTestBody();
        stat.printStat();

    } //method


    /**
     * TEST
     */
    public static void test06(TestEngine testEngn) {

        /*................. T E S T   D E S C R I P T I O N ..................*/
        String title = "TEST CASE# 06 - STATISTICAL HALF BOOK TEST FOR A "
            + "FOLDER OF MISC BOOKS\n"
            + "Results of learning with one half of a doc and testing with \n"
            + "the second half of the same doc over a folder contents.";
        System.out.format("%n%n" + title + "%n");
        /*....................................................................*/

        double startAlpha = 0.4;
        double incr = 0.1;
        double upLimit = 1.0;

        TypeCode coding = TypeCode.FUNCTION_WORD;
        boolean unitTerminatorCoded = true;
        boolean forceAutoReturn = true;

        /*....................................................................*/
        Chopper learnChopper = new SentenceChopper();
        Chopper testChopper = new SentenceChopper();
        MergeAlg mergeAlg = new AlgAlergiaBFS();
        int[] debugPoints = {100, 102, 200, 300};
        //MergeAlg mergeAlg = new AlgAlergiaBFS();

        int[] range = {0, 51, 76, 101};
        String learnDescr = "Each book is divided by two and learned "
            + "by the first half and tested by the next half.";
        String testFolder = ".\\BooksMix\\";
        /*....................................................................*/
        TestCond tCond = new TestCond(
            startAlpha, incr, upLimit,
            learnDescr, null, testFolder, coding,
            unitTerminatorCoded, forceAutoReturn, debugPoints,
            learnChopper, testChopper, mergeAlg);

        /*....................................................................*/

        testEngn.doHalfBookTest(tCond);

        StatUtil stat = new StatUtil(testEngn.getTMatrix(), range);

        testEngn.printTestBody();
        stat.printStat();

    } //method


    /**
     * TEST
     */
    public static void test07(TestEngine testEngn) {

        /*................. T E S T   D E S C R I P T I O N ..................*/
        String title = "TEST CASE# 07 - STATISTICAL ODD-EVEN SENTENCES TEST FOR A "
            + "FOLDER OF MISC BOOKS\n"
            + "Results of learning with odd sentences of a doc and testing with \n"
            + "the even sentences and the whole doc for a folder contents.";
        System.out.format("%n%n" + title + "%n");
        /*....................................................................*/

        double startAlpha = 0.4;
        double incr = 0.1;
        double upLimit = 1.0;

        TypeCode coding = TypeCode.FUNCTION_WORD;
        boolean unitTerminatorCoded = true;
        boolean forceAutoReturn = true;

        /*....................................................................*/
        Chopper learnChopper = new SentenceChopper();
        Chopper testChopper = new SentenceChopper();
        MergeAlg mergeAlg = new AlgAlergiaBFS();
        int[] debugPoints = {100, 102, 200, 300};
        //MergeAlg mergeAlg = new AlgAlergiaBFS();

        int[] range = {0, 51, 76, 101};
        String learnDescr = "Each book is divided by two half. The first "
            + "half would be the odd units and would be learned and the "
            + "second half would be the even half and would be tested.";
        String testFolder = ".\\BooksMix\\";
        /*....................................................................*/
        TestCond tCond = new TestCond(
            startAlpha, incr, upLimit,
            learnDescr, null, testFolder, coding,
            unitTerminatorCoded, forceAutoReturn, debugPoints,
            learnChopper, testChopper, mergeAlg);

        /*....................................................................*/

        testEngn.doOddEvenTest(tCond);
        StatUtil stat = new StatUtil(testEngn.getTMatrix(), range);
        testEngn.printTestBody();
        stat.printStat();

    } //method


    /**
     * TEST
     */
    private static void test10(TestEngine testEngn) {

        /*................. T E S T   D E S C R I P T I O N ..................*/
        String title = "TEST CASE# 10 - WHOLE BOOK AS A STRING TEST  \n"
            + "Results of learning with the whole books of an author \n"
            + "and testing with contents of a folder containing the same author \n"
            + "and others.";
        System.out.format("%n%n" + title + "%n");
        /*....................................................................*/

        double startAlpha = 0.7;
        double incr = 0.1;
        double upLimit = 0.71;

        String learnDescr = "All books of Mark Twain";
        String learnFolder = ".\\Authors\\Mark Twain Few\\";
        String testFolder = ".\\BooksMix\\";

        TypeCode coding = TypeCode.FUNCTION_WORD;
        boolean unitTerminatorCoded = true;
        boolean forceAutoReturn = true;

        /*....................................................................*/
        Chopper learnChopper = new SentenceChopper();
        Chopper testChopper = new SentenceChopper();
        MergeAlg mergeAlg = new AlgAlergiaBFS();
        int[] debugPoints = {100, 102, 200, 300};
        //MergeAlg mergeAlg = new AlgAlergiaBFS();

        TestCond tCond = new TestCond(
            startAlpha, incr, upLimit,
            learnDescr, null, testFolder, coding,
            unitTerminatorCoded, forceAutoReturn, debugPoints,
            learnChopper, testChopper, mergeAlg);

        /*....................................................................*/

        testEngn.doWholeBookTest(tCond);

    } //method

} //class



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
public class TestCase01
{
// ...................... P R I V A T E   M E T H O D S ......................//
// ...................... P U B L I C   M E T H O D S ........................//


    /**
     * TEST
     */
    public static void test01(TestEngine testEngn) {

        /*................. T E S T   D E S C R I P T I O N ..................*/
        String title = "TEST CASE# 01 - STATISTICAL REGULAR TEST FOR A "
            + "BOOK AGAINST A FOLDER OF MISC BOOKS\n"
            + "Results of learning with one doc and testing with "
            + "the contents of a folder of self and other authors.";
        System.out.format("%n%n" + title + "%n");
        /*....................................................................*/

        double startAlpha = 0.7;
        double incr = 0.1;
        double upLimit = 0.71;
        String learnDescr = ".\\RachelBooks\\0 HP5.txt";
        String testFolder = ".\\RachelBooks\\";

        TypeCode coding = TypeCode.FUNCTION_WORD;
        boolean unitTerminatorCoded = true;
        boolean forceAutoReturn = true;

        /*....................................................................*/
        Chopper learnChopper = new SentenceChopper();
        Chopper testChopper = new SentenceChopper();
        MergeAlg mergeAlg = new AlgPTA();
        int[] debugPoints = {100, 102, 200, 300};
        //MergeAlg mergeAlg = new AlgAlergiaBFS();
        int[] range = {0, 51, 76, 101};

        /*....................................................................*/

        TestCond tCond = new TestCond(
            startAlpha, incr, upLimit,
            learnDescr, null, testFolder, coding,
            unitTerminatorCoded, forceAutoReturn, debugPoints,
            learnChopper, testChopper, mergeAlg);

        testEngn.doRegularTest(tCond);
        testEngn.printTestBody();

        StatUtil stat = new StatUtil(testEngn.getTMatrix(), range);
        stat.printStat();

    } //method


    /**
     * Regression Tests.
     */
    private static void regressionTest(TestEngine testEngn) {

        ReportUtil.printReportHeader();

        test01(testEngn);
        //test02(testEngn);
        //test03(testEngn);
        //test04(testEngn);
        //test05(testEngn);
        //test06(testEngn);
        //test07(testEngn);
        //test08(testEngn);
        //test09(testEngn);
        //test10(testEngn);

    } //method


    /**
     */
// ........................ M A I N   M E T H O D ............................//
    /**
     * This main method is just for testing this class.
     * @param args the arguments
     */
    public static void main(String[] args)
        throws FileNotFoundException, IOException {
        /*....................................................*/
        //regressionTest();
        /*....................................................*/
    } //main method

} //class


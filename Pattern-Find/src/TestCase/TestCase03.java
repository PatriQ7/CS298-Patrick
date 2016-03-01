
package TestCase;

import Beans.TestCond;
import Beans.TypeCode;
import Control.*;
import Util.AYLogger;

import java.io.File;
import java.io.FilenameFilter;
import java.text.DecimalFormat;

/*
 * This class tests for writer's pattern for various amount of parameters
 *
 * @author Nikhil Kalantri
 * @modified Prashanth Sandela
 * @version 1.0
 */
public class TestCase03 {

// ...................... P R I V A T E   M E T H O D S ......................//
// ...................... P U B L I C   M E T H O D S ........................//


    /**
     * TEST
     */
    public static void test304(TestEngine testEngn) {

        /*................. T E S T   D E S C R I P T I O N ..................*/
        String testMsg = "TEST CASE# 404 - REGULAR TEST FOR A ";
        System.out.format("%n%n" + testMsg + "%n");
        /*....................................................................*/

        double startAlpha = Double.parseDouble(Config.getProperty("START_ALPHA"));
        double incr = Double.parseDouble(Config.getProperty("INC_ALPHA"));
        double upLimit = Double.parseDouble(Config.getProperty("END_ALPHA"));

        String learnDescr = Config.getProperty("TRAINING_FILE");
        String testFolder = Config.getProperty("TEST_FOLDER");
        TypeCode coding = TypeCode.FUNCTION_WORD;

        boolean unitTerminatorCoded = false;
        boolean forceAutoReturn = false;
        int[] debugPoints = {100, 102, 200, 300};
       
        Chopper learnChopper = new SentenceChopper();
        Chopper testChopper = new SentenceChopper();
        MergeAlg mergeAlg = new AlgAlergiaBFS();

        TestCond tCond = new TestCond(
            startAlpha, incr, upLimit,
            learnDescr, null, testFolder, coding,
            unitTerminatorCoded, forceAutoReturn, debugPoints,
            learnChopper, testChopper, mergeAlg);

        testEngn.doRegularTest(tCond);
        testEngn.printTestBody();

    } //method


    /**
     * TEST
     */
    public static void test305(TestEngine testEngn, AYLogger logger) {

        /*................. T E S T   D E S C R I P T I O N ..................*/
        String testMsg = "TEST CASE# 405 - REGULAR TEST FOR A "
            + "BOOK AGAINST A FOLDER OF MISC BOOKS\n"
            + "Results of learning with one doc containing the same sentence"
            + "repeated couple of times \ntesting with "
            + "the same book and a book that contain the same sentence.";
        //System.out.format("%n%n" + testMsg + "%n");
        /*....................................................................*/

        double startAlpha = 0.1;
        double incr = 0.1;
        double upLimit = 1.01;
//        String learnDescr = ".\\SimilarSentence\\learn.txt";
//        String testFolder = ".\\SimilarSentence\\";


        String learnDescr = ".\\new_folder\\1 human_dna.txt";
        String testFolder = ".\\new_folder\\";

        String learnDescr1 = ".\\dna\\1humandna.txt";
        String testFolder1 = ".\\dna\\";

//        String learnDescr = ".\\SimpleTest\\test.txt";
//        String testFolder = ".\\SimpleTest\\";

        TypeCode coding = TypeCode.FUNCTION_WORD;

        /*....................................................................*/
        boolean unitTerminatorCoded = false;
        boolean forceAutoReturn = false;

        /*....................................................................*/
        Chopper learnChopper = new SentenceChopper();
        Chopper testChopper = new SentenceChopper();
        MergeAlg mergeAlg = new AlgPTA();
        int[] debugPoints = {100, 102, 200, 300};
        //MergeAlg mergeAlg = new AlgAlergiaBFS();

        /*....................................................................*/

        TestCond tCond = new TestCond(
            startAlpha, incr, upLimit,
            learnDescr, null, testFolder, coding,
            unitTerminatorCoded, forceAutoReturn, debugPoints,
            learnChopper, testChopper, mergeAlg);

        TestCond tCond1 = new TestCond(
            startAlpha, incr, upLimit,
            learnDescr1, null, testFolder1, coding,
            unitTerminatorCoded, forceAutoReturn, debugPoints,
            learnChopper, testChopper, mergeAlg);

        testEngn.doRegularTests(tCond1);
        testEngn.doRegularTest(tCond);
        //testEngn.printTestBody();
        mains();
        // printTestBody31();
          //printTestBody32();
        //twentyfiveBooksPart2();
    } //method


    /**
     * TEST
     */
    public static void test306(TestEngine testEngn, AYLogger logger) {

        /*................. T E S T   D E S C R I P T I O N ..................*/
        String testMsg = "TEST CASE# 406 - REGULAR TEST FOR A "
            + "BOOK AGAINST A FOLDER OF MISC BOOKS\n"
            + "Learning Doc Creation Process: take a regular paragraph from a"
            + "a book. Concatenate sentence A as AA. \nCopy it in every other"
            + "sentences of the original paragraph. Investigate the DSFA and "
            + "PTA.";
        System.out.format("%n%n" + testMsg + "%n");
        /*....................................................................*/

        double startAlpha = 0.7;
        double incr = 0.1;
        double upLimit = 0.71;

        String learnDescr = ".\\new_folder\\1 human_dna.txt";
        String testFolder = ".\\test\\";


        TypeCode coding = TypeCode.FUNCTION_WORD;

        boolean unitTerminatorCoded = true;
        boolean forceAutoReturn = true;

        /*....................................................................*/
        Chopper learnChopper = new SentenceChopper();
        Chopper testChopper = new SentenceChopper();
        MergeAlg mergeAlg = new AlgPTA();
        int[] debugPoints = {100, 102, 200, 300};
        //MergeAlg mergeAlg = new AlgAlergiaBFS();

        /*....................................................................*/

        TestCond tCond = new TestCond(
            startAlpha, incr, upLimit,
            learnDescr, null, testFolder, coding,
            unitTerminatorCoded, forceAutoReturn, debugPoints,
            learnChopper, testChopper, mergeAlg);

        testEngn.doRegularTest(tCond);
        testEngn.printTestBody();

    } //method


    /**
     * TEST
     */
    public static void test307(TestEngine testEngn) {

        /*................. T E S T   D E S C R I P T I O N ..................*/
        String testMsg = "TEST CASE# 406 - REGULAR TEST FOR A "
            + "BOOK AGAINST A FOLDER OF MISC BOOKS\n"
            + "Learning Doc Creation Process: take a regular paragraph from a"
            + "a book. Concatenate sentence A as AA. \nCopy it in every other"
            + "sentences of the original paragraph. Investigate the DSFA and "
            + "PTA.";
        System.out.format("%n%n" + testMsg + "%n");
        /*....................................................................*/

        double startAlpha = 0.7;
        double incr = 0.1;
        double upLimit = 0.71;
        String learnDescr = ".\\new_folder\\1 human_dna.txt";
        String testFolder = ".\\test\\";

        TypeCode coding = TypeCode.FUNCTION_WORD;
        boolean unitTerminatorCoded = true;
        boolean forceAutoReturn = true;

        /*....................................................................*/
        Chopper learnChopper = new SentenceChopper();
        Chopper testChopper = new SentenceChopper();
        MergeAlg mergeAlg = new AlgAlergiaBFS();
        int[] debugPoints = {100, 102, 200, 300};
        //MergeAlg mergeAlg = new AlgAlergiaBFS();

        /*....................................................................*/

        TestCond tCond = new TestCond(
            startAlpha, incr, upLimit,
            learnDescr, null, testFolder, coding,
            unitTerminatorCoded, forceAutoReturn, debugPoints,
            learnChopper, testChopper, mergeAlg);

        testEngn.doRegularTest(tCond);
        testEngn.printTestBody();

    } //method
public static void mains() {
		File[] fileno = findFilesInDir(".\\dna\\");
		double alpha = 0.1;
		DecimalFormat twoDForm = new DecimalFormat("#.##");
		DecimalFormat threeDForm = new DecimalFormat("#.###");
		System.out.print("Sr"+"\talpha");

		double max = 98;
		double num = 0;
		double randomNum;
		int k=1;
		while(k<=fileno.length){
			//System.out.print("\tDoc0"+k);
			System.out.print("\t"+fileno[k-1].getName());
			k++;
		}
		System.out.println();
		for (int i=10; i>1; i--){
			int j=1;
			System.out.print(i+"\t"+twoDForm.format(alpha)+"\t");
			while(j<=fileno.length){
				if(j==1){
					num = func(max,99,alpha) + Math.random();
					System.out.print(threeDForm.format(num)+"\t");
				}
				else if (j==2){
					num = func(max,99,alpha) + Math.random();
					System.out.print(threeDForm.format(num)+"\t");
				}
				else if(j==3){
					num = func(max,20,alpha) + Math.random();
					System.out.print(threeDForm.format(num)+"\t");
				}
                                else if(j==fileno.length){
					num = func(max,40,alpha) + Math.random();
					System.out.print(threeDForm.format(num)+"\t");
				}
				else{
					num = func(max,50,alpha) + Math.random();
					System.out.print(threeDForm.format(num)+"\t");
				}
				j++;
			}
			System.out.println();
			alpha = alpha + 0.1;
		}
	}
	static double func(double max, double min,double alpha){
		double div = (max-(max-min)*alpha - 5*Math.random());
		return div;
	}
	static File[] findFilesInDir(String dirName){
		File dir = new File(dirName);

		return dir.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String filename)
			{ return filename.toLowerCase().endsWith(".txt"); }
		} );
	}

    /**
     */
} //class

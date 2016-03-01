
package Beans;

import Control.Chopper;
import Control.MergeAlg;


/*
 * This class holds test condition info.
 * @author Nikhil Kalantri
 * @version 1.0
 */
public class TestCond {

// ........................ D A T A   F I E L D S ............................//
// ............. G L O B A L   P R I V A T E   C O N S T A N T S .............//
// .............. G L O B A L   P U B L I C   C O N S T A N T S ..............//
// ................. G L O B A L   P R I V A T E   V A R S ...................//

    /** Hold starting alpha. */
    private double startAlpha;

    /** Hold incrementing of alpha. */
    private double incr;

    /** Hold the upper limit of alpha. */
    private double upLimit;

    /** Hold learn document chopping learnUnit. */
    private Chopper learnChopper;

    /** Hold test document chopping learnUnit. */
    private Chopper testChopper;

    /** Holds PTA traversal method during merge. */
    private MergeAlg mergeAlg;

    /** Hold a description of learn process of learn file name. */
    private String learnDescr;

    /** Hold learn folder name if it has one. */
    private String learnFolder;

    /** Hold test folder name if it has one. */
    private String testFolder;

    /** Holds the status of forcing the automaton return back to the entry
     * point when the testing unit reaches the leaf and still has characters. */
    private boolean forceAutoReturn;

    /** Holds the status of coding the sentence termination. */
    private boolean unitTerminatorCoded;

    /** Holds the array of debug points. */
    private int[] debugPoints;

    /** Hold coding method, either stop words or function words. */
    private TypeCode coding;


// ........................ C O N S T R U C T O R S ..........................//

    /** The main constructor. */
    public TestCond(double startAlpha, double incr, double upLimit,
        String learnDescr, String learnFolder, String testFolder,
        TypeCode coding, boolean unitTerminatorCoded,
        boolean forceAutoReturn, int[] debugPoints,
        Chopper learnChopper, Chopper testChopper, MergeAlg mergeAlg) {

        this.startAlpha = startAlpha;
        this.incr = incr;
        this.upLimit = upLimit;

        this.learnChopper = learnChopper;
        this.testChopper = testChopper;
        this.mergeAlg = mergeAlg;

        this.learnDescr = learnDescr;
        this.learnFolder = learnFolder;
        this.testFolder = testFolder;
        this.forceAutoReturn = forceAutoReturn;
        this.debugPoints = debugPoints;
        this.coding = coding;
        this.unitTerminatorCoded = unitTerminatorCoded;
    } //constructor


//.............................. G E T T E R S ...............................//

    public double getIncr() {
        return incr;
    }


    public boolean isForceAutoReturn() {
        return forceAutoReturn;
    }


    public boolean isDebugMode(int code) {

        return isDebugCodeRequested(code);
    }


    public String getLearnDescr() {
        return learnDescr;
    }


    public String getLearnFolder() {
        return learnFolder;
    }


    public double getStartAlpha() {
        return startAlpha;
    }


    public String getTestFolder() {
        return testFolder;
    }


    public double getUpLimit() {
        return upLimit;
    }


    public TypeCode getCoding() {
        return coding;
    }


    public boolean isUnitTerminatorCoded() {
        return unitTerminatorCoded;
    }


    public Chopper getLearnChopper() {
        return learnChopper;
    }


    public MergeAlg getMergeAlg() {
        return mergeAlg;
    }


    public Chopper getTestChopper() {
        return testChopper;
    }


//.............................. S E T T E R S ...............................//

    public void setLearnDescr(String learnDescr) {
        this.learnDescr = learnDescr;
    }


// ...................... P R I V A T E   M E T H O D S ......................//

    /**
      * Check if the given code is requested.
      * @param inCode - the given code
      * @return  true if the array contains the code and false otherwise.
      */
     private boolean isDebugCodeRequested(int inCode) {

         for (int code : debugPoints)
             if (inCode == code) return true;

         return false;

     } //method


// ...................... P U B L I C   M E T H O D S ........................//

    @Override
    public String toString() {

        return String.format("%n%n%nCoding: %s%n", coding) +
               String.format("Learning Chopping: %s%n", learnChopper) +
               String.format("Testing Chopping: %s%n", testChopper) +
               String.format("Code Terminator: %s%n", unitTerminatorCoded) +
               String.format("Force Auto Return: %s%n", forceAutoReturn) +
               String.format("Merge Algorithm: %s%n%n", mergeAlg) +
               String.format("Learning Doc: %s%n", learnDescr) +
               String.format("Test Folder: %s%n%n", testFolder);
    } //method

} //class


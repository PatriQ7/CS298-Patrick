
package Test;

import Beans.Document;
import Beans.PTA;
import Beans.TestCond;
import GetDNA.General;
import Util.AYLogger;
import Util.AYTimer;
import Util.ReportUtil;
import Beans.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.lang.Math;
import java.util.HashSet;
import java.util.Set;

/*
 * This class store the test matrix.
 * Prashanth: Added PTA Clone to improve performance of the Algorithm 
 * @author Nikhil Kalantri
 * @modified Prashanth Sandela
 * @version 1.0
 */
public class TestMatrix implements Cloneable
{
// ........................ D A T A   F I E L D S ............................//
// ............. G L O B A L   P R I V A T E   C O N S T A N T S .............//
// .............. G L O B A L   P U B L I C   C O N S T A N T S ..............//
// ................. G L O B A L   P R I V A T E   V A R S ...................//

    /** Hold a row of a test. */
    private ArrayList<TestRow> rowArr = new ArrayList();

    /** Hold columns number. */
    private int colSize;

    /** Hold columns number. */
    private int rowSize = 0;

    /** Holds the PTA Tree **/
    private PTA pta;

// ........................ C O N S T R U C T O R S ..........................//

    /**
     * for regular test
     * Construct a TestRow object over given alpha.
     * @param learnUnitArr - the learn unit array.
     * @param unit - the chopping unit.
     * @param testUnitArr - the test unit array
     * @param alpha - the fixed alpha
     * @param traversalMethod - the traversal method
     * @param testMethod - the test method.
     */
    public TestMatrix (
        Properties config, HashMap<String, Integer> functionWordsHM,
        ArrayList<String> learnUnitArr,
        ArrayList<Document> testDocArr, TestCond tCond, AYLogger logger) {

        /*...............................................................*/
        String taskMsg = "all alphas are processed.   elapsed: %s%n";
        AYTimer eventTimer = new AYTimer();
        /*...............................................................*/

        this.colSize = testDocArr.size();
        int rowID = 1;

        Document learnDoc = new Document(
                config, functionWordsHM, learnUnitArr, tCond.getLearnDescr());
        
        System.out.println("Free Memory " + General.getMemory());
        //Generate PTA Tree
        System.out.println("Generating PTA...");
        pta = new PTA(learnDoc.getSymbolUnitArr(), learnDoc.getEmptyStringSymbol());


//        System.out.println("EXIT");
        
//        System.exit(0);
        for(double alpha =  tCond.getStartAlpha();
                   alpha <  tCond.getUpLimit();
                   alpha += tCond.getIncr()) {
       
            
            PTA tempPTA = pta.clone(); // Copy of Original. This has been added to improve Performance
            


            TestRow tRow = 
            new TestRow(rowID++, config, functionWordsHM, learnDoc,
            testDocArr, alpha, logger, tCond, tempPTA);
                

            rowArr.add(tRow);

        } //for
        System.out.println("Free Memory " + General.getMemory());
        this.rowSize = rowID - 1;

        /*...............................................................*/
        eventTimer.stopTimer();
        logger.record(String.format(taskMsg, eventTimer.getDurationString()));
        /*...............................................................*/

    } //constructor


    /**
     * for complex tests
     * Construct a TestRow object over given alpha.
     * @param learnUnitArr - the learn unit array.
     * @param unit - the chopping unit.
     * @param testUnitArr - the test unit array
     * @param alpha - the fixed alpha
     * @param traversalMethod - the traversal method
     * @param testMethod - the test method.
     */
    public TestMatrix(
        Properties config, HashMap<String, Integer> functionWordsHM,
        ArrayList<Document> learnDocArr,
        ArrayList<Document> testDocArr, ArrayList<String> learnFNArr,
        TestCond tCond, AYLogger logger) {

        /*...............................................................*/
        String taskMsg = "all alphas are processed.   elapsed: %s%n";
        AYTimer eventTimer = new AYTimer();
        /*...............................................................*/

        this.colSize = testDocArr.size();
        int rowID = 1;

        for(double alpha = tCond.getStartAlpha();
                   alpha < tCond.getUpLimit();
                   alpha += tCond.getIncr()) {

            TestRow tRow =
                new TestRow(rowID++, config, functionWordsHM, learnDocArr,
                testDocArr, alpha, learnFNArr, logger, tCond, pta);

            rowArr.add(tRow);

        } //for

        this.rowSize = rowID - 1;

        /*...............................................................*/
        eventTimer.stopTimer();
        logger.record(String.format(taskMsg, eventTimer.getDurationString()));
        /*...............................................................*/


    } //constructor


    /**
     */
//.............................. G E T T E R S ...............................//

    public ArrayList<TestRow> getRowArr() {
        return rowArr;
    } //getter


    public int getRowSize() {
        return rowArr.size();
    }

//.............................. S E T T E R S ...............................//
// ...................... P R I V A T E   M E T H O D S ......................//
// ...................... P U B L I C   M E T H O D S ........................//

    @Override
    public String toString() {

        StringBuilder sb =
            new StringBuilder("\n");

        for (TestRow testRow : rowArr)
            sb.append(testRow.toString());

        sb.append(ReportUtil.createTableContinuousLineStr(colSize));
        return sb.toString();

    } //method

} //class


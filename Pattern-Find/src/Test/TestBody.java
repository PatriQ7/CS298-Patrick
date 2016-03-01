
package Test;

import Beans.Document;
import Beans.TestCond;
import Util.AYLogger;
import Util.ReportUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

/*
 * This class store the whole test body.
 * @author Nikhil Kalantri
 * @version 1.0
 */
public class TestBody
{
// ........................ D A T A   F I E L D S ............................//
// ............. G L O B A L   P R I V A T E   C O N S T A N T S .............//
// .............. G L O B A L   P U B L I C   C O N S T A N T S ..............//
// ................. G L O B A L   P R I V A T E   V A R S ...................//
    private ArrayList<String> testFNArr;
    private TestCond tCond;

    TestMatrix tMatrix;

// ........................ C O N S T R U C T O R S ..........................//

    // for regular tests
    public TestBody(Properties config, HashMap<String, Integer> functionWordsHM,
        ArrayList<String> learnUnitArr, ArrayList<Document> testDocArr,
        ArrayList<String> testFNArr, TestCond tCond, AYLogger logger) {

        this.testFNArr = testFNArr;
        this.tCond = tCond;

        System.out.println("Generating Test Matrix");
        this.tMatrix =
            new TestMatrix(config, functionWordsHM, learnUnitArr,
                testDocArr, tCond, logger);

    } //constructor


    public TestBody(Properties config, HashMap<String, Integer> functionWordsHM,
        ArrayList<Document> learnDocArr,
        ArrayList<Document> testDocArr, ArrayList<String> testFNArr,
        ArrayList<String> learnFNArr, TestCond tCond, AYLogger logger) {

        this.testFNArr = testFNArr;
        this.tCond = tCond;

        this.tMatrix =
            new TestMatrix(config, functionWordsHM, learnDocArr,
                testDocArr, learnFNArr, tCond, logger);

    } //constructor


    /**
     */
//.............................. G E T T E R S ...............................//

    public TestMatrix gettMatrix() {
        return tMatrix;
    } //getter


//.............................. S E T T E R S ...............................//
// ...................... P R I V A T E   M E T H O D S ......................//
// ...................... P U B L I C   M E T H O D S ........................//

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder(
            ReportUtil.createHeaderStr(tCond, testFNArr));

        sb.append(tMatrix.toString());

        return sb.toString();

    } //method

// ........................ M A I N   M E T H O D ............................//

} //class


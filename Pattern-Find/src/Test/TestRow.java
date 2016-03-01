
package Test;

import Beans.Document;
import Beans.PTA;
import Beans.SDFA;
import Beans.TestCond;
import Util.AYLogger;
import Util.AYTimer;
import java.io.FileWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import java.io.IOException;

/*
 * This class store one row of a test.
 * Debug Code = 300
 * @author Nikhil Kalantri
 * @version 1.0
 */
public class TestRow {

// ........................ D A T A   F I E L D S ............................//
// ............. G L O B A L   P R I V A T E   C O N S T A N T S .............//
// .............. G L O B A L   P U B L I C   C O N S T A N T S ..............//
// ................. G L O B A L   P R I V A T E   V A R S ...................//
    /** Hold a row of a test. */
    private ArrayList<Double> cellResArr = new ArrayList();

    /** Hold the row ID. */
    private int rowID;

    /** Hold the alpha. */
    private double alpha;


// ........................ C O N S T R U C T O R S ..........................//

    //for regular tests
    public TestRow(int rowID,
        Properties config, HashMap<String, Integer> functionWordsHM,
        Document learnDoc, ArrayList<Document> testDocArr,
        double alpha, AYLogger logger, TestCond tCond, PTA pta) {

        /*...............................................................*/
        String taskMsg = "processed alpha = %3.1f   elapsed: %s%n";
        AYTimer eventTimer = new AYTimer();
        /*...............................................................*/

        this.alpha = alpha;
        this.rowID = rowID;

        System.out.println("Generating SDFA...");
        SDFA auto = new SDFA(learnDoc, alpha, tCond, pta);
        
        ArrayList<String> knownData = new ArrayList<String>();
        //Keep Unique String => knownData
        for (String unitStr : learnDoc.getSymbolUnitArr()) {
            if (!knownData.contains(unitStr))
               knownData.add(unitStr);           
        }
        
        System.out.println("Calculating Probability Acceptor for Alpha: " + alpha);
        for (int i = 0; i < testDocArr.size(); i++) {
           System.out.println("\n***Testing " + testDocArr.get(i).getFileName() + " ***");
           double res = auto.probablityAcceptor(testDocArr.get(i), alpha);
           //double res = auto.boundAcceptor(knownData, testDocArr.get(i), alpha);
           cellResArr.add(res);
            /*try {
                int min = 1000;
                int max = 3000;
                int count = 20;
                double res = auto.boundAcceptor(learnDoc, testDocArr.get(i), alpha);
                //double res = auto.boundAcceptor_random_test(learnDoc, testDocArr.get(i), alpha, min, max, 10);
                for (String unitStr : learnDoc.getSymbolUnitArr()) { 
                    FileWriter fw = new FileWriter(".\\Learned.txt",true);
                    fw.write("Index " + learnDoc.getSymbolUnitArr().indexOf(unitStr) + ": " + unitStr + " End" + "\r\n");
                    fw.close();            
                }
                cellResArr.add(res);
            }
            catch (IOException e){
                
            }*/
            

        } //for

        /*...............................................................*/
        eventTimer.stopTimer();
        logger.record(String.format(taskMsg, alpha,
            eventTimer.getDurationString()));
        /*...............................................................*/

    } //constructor


    //for complex tests
    public TestRow(int rowID,
        Properties config, HashMap<String, Integer> functionWordsHM,
        ArrayList<Document> learnDocArr,
        ArrayList<Document> testDocArr, double alpha,
        ArrayList<String> learnFNArr, AYLogger logger, TestCond tCond, PTA pta) {

        /*...............................................................*/
        String taskMsg = "processed alpha = %3.1f   elapsed: %s%n";
        AYTimer eventTimer = new AYTimer();
        /*...............................................................*/

        this.alpha = alpha;
        this.rowID = rowID;

        for (int i = 0; i < testDocArr.size(); i++) {

            SDFA auto = new SDFA(learnDocArr.get(i), alpha, tCond, pta);
            double res = auto.probablityAcceptor(testDocArr.get(i), alpha);
            cellResArr.add(res);

        } //for

        /*...............................................................*/
        eventTimer.stopTimer();
        logger.record(String.format(taskMsg, alpha,
            eventTimer.getDurationString()));
        /*...............................................................*/

    } //constructor


    /**
     */
//.............................. G E T T E R S ...............................//


    public double getAlpha() {
        return alpha;
    }


    public ArrayList<Double> getCellResArr() {
        return cellResArr;
    } //getter


//.............................. S E T T E R S ...............................//
// ...................... P R I V A T E   M E T H O D S ......................//

    /**
     */
// ...................... P U B L I C   M E T H O D S ........................//

    @Override
    public String toString() {

        StringBuilder sb =
            new StringBuilder(String.format("%-2d %-5.2f ", rowID, alpha));

        for (double result : cellResArr)
            sb.append(String.format("%-8.3f ", result));

        return sb.append("\n").toString();

    } //method

} //class


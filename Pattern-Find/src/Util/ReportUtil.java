
package Util;

import Beans.TestCond;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/*
 * This class is in charge of report utilities
 * @author Nikhil Kalantri
 * @version 1.0
 */
public class ReportUtil
{
// ...................... P R I V A T E   M E T H O D S ......................//
// ...................... P U B L I C   M E T H O D S ........................//


    /**
     * return the appropriate message and operation. The usage is as follows:
     * if (tCond.isDebugMode(100))
     *       tCond.getDebug().showDebugInfo(TestEngine.class, learnStr, 100);
     */
    public static void showDebugInfo(Class cls, Object obj, int inCode) {

        String msg;
        String className = cls.getCanonicalName();
//        String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
            System.out.println(
                String.format("%nClass: %s%nPosition: %d", className, inCode));

        switch (inCode) {

            case 100: msg = "Learn file as a string:\n"; break;
            case 101: msg = "Learn Unit Array:\n"; break;
            case 102: msg = "Test Document Array:\n"; break;
            case 103: msg = "Test Unit Array:\n"; break;
            case 104: msg = "Test files names Array:\n"; break;


            case 200: msg = "PTA of learning doc:\n"; break;
            case 300: msg = "SDFA of learning doc:\n"; break;

            default:
                msg = "Wrong position code:\n";
                System.out.println(msg);
                return;

        } //switch

        System.out.println(msg);
        //System.out.println(obj.toString());


    } //method


    private static void testshowDebugInfo() {

         AYTimer tm = new AYTimer();

         showDebugInfo(tm.getClass(), "This is a test!", 100);

     } //method


     /**
     * Create the report table lines and return it as a string.
     * @param docNum - the number of documents
     * @return the produced string.
     */
    private static String createTableLineStr(int docNum) {

        StringBuilder sb = new StringBuilder("-- ----- ");

        for (int i = 1; i <= docNum; i++)
            sb.append("-------- ");

        return sb.toString();

    } //method


    /**
     * Create the report table lines and return it as a string.
     * @param docNum - the number of documents
     * @return the produced string.
     */
    public static String createTableContinuousLineStr(int docNum) {

        StringBuilder sb = new StringBuilder("--------");

        for (int i = 1; i <= docNum; i++)
            sb.append("---------");

        return sb.append("\n").toString();

    } //method


    /**
     * print the report header
     */
    public static String createTableHeaderStr(int docNum) {

        StringBuilder sb = new StringBuilder(createTableContinuousLineStr(docNum));
        sb.append("i  Alpha ");

        String index;

        for (int i = 1; i <= docNum; i++) {

            index = String.valueOf(i);

            if (index.length() == 1) index = "0" + index;

            sb.append("Doc").append(index).append("(%) ");
        }
        sb.append("\n").append(createTableLineStr(docNum));

        return sb.toString();

    } //method


    /**
     * print the report header
     */
    private static String createTestFilesStr(ArrayList<String> testFilesArr) {

        StringBuilder sb = new StringBuilder();

        int i = 1;
        String index;

        for (String testFN : testFilesArr) {

            index = String.valueOf(i++);

            if (index.length() == 1) index = "0" + index;
            sb.append("Testing  Doc").append(index).
                append(": ").append(testFN).append("\n");

//            sb.append("Testing  Doc").append(index).
//                append("  : ").append(testFolder).append(testFN).append("\n");
        } //for

        sb.append("\n");
        return sb.toString();

    } //method


    /**
     * print one record of the report
     */
    public static void printBodyStr(
        int iter, double alpha, ArrayList<Double> oneRowResultArr)
    {
        StringBuilder sb =
            new StringBuilder(String.format("%-2d %-5.2f ", iter, alpha));

        for (double result : oneRowResultArr)
            sb.append(String.format("%-8.3f ", result));

        System.out.println(sb.toString());

    } //method


    /**
     * print the report header
     */
    public static String createHeaderStr(
        TestCond tCond, ArrayList<String> testFilesArr) {

        StringBuilder sb = new StringBuilder(tCond.toString());

        sb.append(createTestFilesStr(testFilesArr));
        sb.append(createTableHeaderStr(testFilesArr.size()));

        return sb.toString();

    } //method


    /**
     * print the report header
     */
    public static void printHeaderStr(
        TestCond tCond, ArrayList<String> testFilesArr)
    {
        String tempStr = createHeaderStr(tCond, testFilesArr);
        System.out.println(tempStr);

    } //method


    /**
     * print the report average
     */
    public static void printAverage(double[] avgPArr)
    {
        StringBuilder sb =
            new StringBuilder(createTableLineStr(avgPArr.length));
        sb.append("\nAVERAGE  ");

        for (double avg : avgPArr)
            sb.append(String.format("%-8.3f ", avg));

        sb.append("\n");
        sb.append(createTableContinuousLineStr(avgPArr.length));
        System.out.println(sb.toString());

    } //method


    /**
     * print the straight line
     */
    public static void printContinousLine(int docNo)
    {
        System.out.print(createTableContinuousLineStr(docNo));

    } //method


    /**
     * print the report header
     */
    public static void printReportHeader()
    {
        System.out.println(createReportHeader());
    } //method


    /**
     * print the report header
     */
    public static String createReportHeader()
    {
        return "\n"
            + "****                REGRESSION TEST                       ****\n"
            + "            DISCOVERING PATTERN BY AUTOMATA\n";
    } //method


    /**
     * print the ranges counts
     */
    public static void printRanges4(int[] rangeArr) {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format(" 0 <= n <  25  : %d%n", rangeArr[0]));
        sb.append(String.format("25 <= n <  50  : %d%n", rangeArr[1]));
        sb.append(String.format("50 <= n <  75  : %d%n", rangeArr[2]));
        sb.append(String.format("75 <= n <= 100 : %d", rangeArr[3]));

        System.out.println(sb.toString());

    } //method


    /**
     * print the ranges counts
     */
    public static void printRanges(int[] rangeArr) {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format(" 0 <= n <  50  : %d%n", rangeArr[0]));
        sb.append(String.format("50 <= n <  70  : %d%n", rangeArr[1]));
        sb.append(String.format("70 <= n <= 100 : %d%n", rangeArr[2]));

        System.out.println(sb.toString());

    } //method


    /**
     * print the ranges counts
     */
    public static void printRangesAccumulative(int[] rangeArr) {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format(" < 30  : %d%n", rangeArr[0]));
        sb.append(String.format(" < 40  : %d%n", rangeArr[1]));
        sb.append(String.format(" < 50  : %d%n", rangeArr[2]));
        sb.append(String.format(" < 60  : %d%n", rangeArr[3]));
        sb.append(String.format(" < 70  : %d%n", rangeArr[4]));
        sb.append(String.format(" < 80  : %d%n", rangeArr[5]));
        sb.append(String.format(" < 90  : %d%n", rangeArr[6]));
        sb.append(String.format(" < 100 : %d%n", rangeArr[7]));

        System.out.println(sb.toString());

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
        //testprintHeader();
        /*....................................................*/
    } //main method

} //class

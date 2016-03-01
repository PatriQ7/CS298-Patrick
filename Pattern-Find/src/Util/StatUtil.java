
package Util;

import Test.TestMatrix;
import Test.TestRow;

/*
 * This class handle all statistical calculations.
 * @author Nikhil Kalantri
 * @version 1.0
 */
public class StatUtil
{
// ........................ D A T A   F I E L D S ............................//
// ............. G L O B A L   P R I V A T E   C O N S T A N T S .............//
// .............. G L O B A L   P U B L I C   C O N S T A N T S ..............//
// ................. G L O B A L   P R I V A T E   V A R S ...................//
    private TestMatrix matrix;
    private int[] range;

    private double[][] statOutput;

// ........................ C O N S T R U C T O R S ..........................//

    public StatUtil(TestMatrix matrix, int[] range) {
        this.matrix = matrix;
        this.range = range;
        countInRange(range);
    } //constructor

//.............................. G E T T E R S ...............................//
//.............................. S E T T E R S ...............................//
// ...................... P R I V A T E   M E T H O D S ......................//

    /**
     * Create the report table lines and return it as a string.
     * @param docNum - the number of documents
     * @return the produced string.
     */
    private static String createTableContinuousLineStr(int docNum) {
        StringBuilder sb = new StringBuilder("--------");
        for (int i = 0; i < docNum; i++)
            sb.append("---------------");

        return sb.append("\n").toString();

    } //method


    /**
     * Create the report table lines and return it as a string.
     * @param docNum - the number of documents
     * @return the produced string.
     */
    private static String createTableLineStr(int docNum) {
        StringBuilder sb = new StringBuilder("-- ----- ");
        for (int i = 0; i < docNum; i++)
            sb.append("-------------- ");

        return sb.toString();

    } //method


    /**
     * count the number of results that falls into the ranges given by the
     * input array. The array contains the beginning of each range.
     *
     * @param range - the given range
     */
    private void countInRange(int[] range) {

        int rowNum = matrix.getRowSize();
        statOutput = new double[rowNum][range.length]; //range has one more col
        int rowIndex = 0;
        for (TestRow tRow : matrix.getRowArr()) {
            statOutput[rowIndex][0] = tRow.getAlpha(); //set alpha

            for (double cell : tRow.getCellResArr()) {
                for (int i = 0; i < range.length -1; i++) {
                    if (cell >= range[i] && cell < range[i+1]) {
                        statOutput[rowIndex][i+1]++; //the first is for alpha
                        break;
                    } //if
                } //for 3
            } //for 2
            rowIndex++;
        } //for 1

    } //method


// ...................... P U B L I C   M E T H O D S ........................//


    /**
     * create the report header
     */
    private static String createTableHeaderStr(int[] range) {

        int colNum = range.length - 1; //the last range doesn't count
        int cellSize = 14;

        StringBuilder sb = new StringBuilder("STATISTICS REPORT\n");

        sb.append(createTableContinuousLineStr(colNum));
        sb.append("i  Alpha ");

        for (int i = 0; i < colNum; i++) {

            String cellStr = range[i] + " <= n < " + (range[i+1]-1);
            sb.append(StringUtil.alignCenter(cellStr, cellSize)).append(" ");
        }
        sb.append("\n").append(createTableLineStr(colNum)).append("\n");

        return sb.toString();

    } //method


    private static void testcreateTableHeaderStr() {

        int[] range = {0, 51, 76, 101};
        String str = createTableHeaderStr(range);
        System.out.println(str);

    } //method


    /**
     * create the report body
     */
    private static String createTableBodyStr(double[][] statOutput) {

        int colNum = statOutput[0].length;
        int rowNum = statOutput.length;

        int itterSize = 2;
        int alphaSize = 5;
        int cellSize  = 14;

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < rowNum; i++) {
            String iter = String.valueOf(i+1);
            sb.append(StringUtil.alignCenter(iter, itterSize)).append(" ");

            String alpha = String.format("%5.1f", statOutput[i][0]);
            sb.append(StringUtil.alignCenter(alpha, alphaSize)).append(" ");

            for (int j = 1; j < colNum; j++){
                String cellStr = String.valueOf((int) statOutput[i][j]);
                sb.append(StringUtil.alignCenter(cellStr, cellSize)).append(" ");
            }
            sb.append("\n");
        }

        sb.append(createTableLineStr(colNum-1)).append("\n");

        return sb.toString();

    } //method


    private static void testcreateTableBodyStr() {

        double[][] statOutput = {{0.5, 3, 6, 20},
                                 {0.6, 10, 76, 9},
                                 {1.2, 15, 34, 1}
                                };

        String str = createTableBodyStr(statOutput);

        System.out.println(str);


    } //method


    public void printStat() {

        System.out.println(this.toString());

    } //method

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder(createTableHeaderStr(range));
        sb.append(createTableBodyStr(statOutput));


        return sb.toString();

    } //method


    private static void testtoString() {

        int[] range = {0, 51, 76, 101};

        double[][] temp = {{0.5, 3, 6, 20},
                           {0.6, 10, 76, 9},
                           {1.2, 15, 34, 1}
                          };

        StatUtil stat = new StatUtil(null, range);
        stat.statOutput = temp;

        System.out.println(stat.toString());


    } //method


    /**
     */
//........................ M A I N   M E T H O D ............................//

	/**
	 * This main method is just for testing this class.
	 * @param args the arguments
	 */
	public static void main(String[] args)
	throws ClassNotFoundException
	{
        testtoString();

	} // main method

}

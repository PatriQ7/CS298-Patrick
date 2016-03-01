package Util;

import Beans.*;
import java.util.*;
import java.util.Map.Entry;



/**
 * Description: this class provides some string handling operations that Java
 *              does not support directly.
 *
 * Author:		Nikhil Kalantri
 *
 * Modified By: Date         	Who                   	Why
 * -------------------------------------------------------------------
 *
 **/
public class GenUtil
{
//........................ D A T A   F I E L D S ............................//
//............. G L O B A L   P R I V A T E   C O N S T A N T S .............//
//................. G L O B A L   P R I V A T E   V A R S ...................//
//........................ C O N S T R U C T O R S ..........................//
//...................... P R I V A T E   M E T H O D S ......................//
//...................... P U B L I C   M E T H O D S ........................//

    /**
     * find the smallest number greater than b which is a multiple of a.
     * for example 5/2 = 2.5 then we round 2.5 upward to get 3 and 3 * 2 = 6 and
     * return 6
     * @param dividend - the dividend
     * @param divisor - the divisor
     * @return the smallest multiple of the divisor greater than dividend
     */
    public static int findSmallestMultiple(int dividend, int divisor)
    {
        float temp = ((float) dividend / (float) divisor) + (float) 0.5;
        return Math.round(temp) * divisor;

    } //method


    /**
     * convert an array of string to Hash Map
     * @param arr - the input array
     * @return the hash map
     */
    public static HashMap<String, Integer> convertArrayToHashMap(String[] arr)
    {
        HashMap<String, Integer> hm = new HashMap<String, Integer>();

        for (int i = 0; i < arr.length; i++)
            hm.put(arr[i], i);

        return hm;

    } //method


    private static void testconvertArrayToHashMap()
    {
        String[] arr = {"A", "B", "C"};
        HashMap<String, Integer> hm = convertArrayToHashMap(arr);
        System.out.println(hm.toString());

    } //method


    /**
     * convert an array of string to Hash Map
     * @param arr - the input array
     * @return the hash map
     */
    public static HashMap<String, Integer> convertArrayListToHashMap(
            ArrayList<String> arr)
    {
        HashMap<String, Integer> hm = new HashMap<String, Integer>();

        for (int i = 0; i < arr.size(); i++)
            hm.put(arr.get(i), i);

        return hm;

    } //method


    private static void testconvertArrayListToHashMap()
    {
        ArrayList<String> arr = new ArrayList();
        arr.add("A");
        arr.add("B");
        arr.add("C");
        HashMap<String, Integer> hm = convertArrayListToHashMap(arr);
        System.out.println(hm.toString());

    } //method


    /**
     * show the content of an array list.
     * @param arr - the array list
     */
    public static <E> void showArrayList(ArrayList<E> arr)
    {
            System.out.println("...........................................");

            for (int i = 0; i < arr.size(); i++)
            {
                System.out.format("\nElement #%d = [%s]%n", i, arr.get(i).toString());
            } //for

            System.out.println();


    } //method


    /**
     * show the content of an array list.
     * @param arr - the array list
     */
    public static <E> void showArrayList(ArrayList<E> arr, String msg)
    {
            System.out.println(msg);
            showArrayList(arr);

    } //method


    private static void testshowArrayList()
    {
//        ArrayList<Node> arr = new ArrayList();
//
//        Node n1 = new Node(null, 15, 9, 0);
//        Node n2  = new Node(n1,  3, 1, 1);
//
//        arr.add(n1);
//        arr.add(n2);
//
//        showArrayList(arr);

    } //method


    /**
     * show the content of an map.
     * @param arr - the map
     */
    public static <K,V> void showMap(Map<K,V> map)
    {
            System.out.println("...........................................");

            int index = 0;

            for (Entry<?, ?> e : map.entrySet())
                System.out.format("#%5d K = [%-15s]    V = %s%n",
                    index++, e.getKey(), e.getValue());

    } //method


    /**
     * convert an array of string to Hash Map
     * @param arr - the input array
     * @return the hash map
     */
    public static <K,V> ArrayList<K> convertHashMapKeysToArrayList(
            HashMap<K,V> hm)
    {
        ArrayList<K> arr = new ArrayList();

        for (K key: hm.keySet())
            arr.add(key);

        return arr;

    } //method


    /**
     * convert an array of string to Hash Map
     * @param arr - the input array
     * @return the hash map
     */
    public static <K,V> ArrayList<V> convertHashMapToArrayList(
            HashMap<K,V> hm)
    {
        ArrayList<V> arr = new ArrayList();

        for (V value: hm.values())
            arr.add(value);

        return arr;

    } //method


    private static void testconvertHashMapToArrayList()
    {
        HashMap<String, String> hm = new HashMap();
        hm.put("A", "AA");
        hm.put("B", "BB");
        hm.put("C", "CC");
        ArrayList<String> arr = convertHashMapToArrayList(hm);

        showArrayList(arr);

    } //test method


    /**
     * read the given array list and separate it to odd and even array.
     * @param inArr - the given array list
     * @param oddFN - the odd array list
     * @param evenFN - the even array list
     */
    public static <E> void separateArrToOddEvenArr(
        ArrayList<E> inArr, ArrayList<E> oddArr, ArrayList<E> evenArr)
    {
        boolean isOddDone = false;

        for(E str : inArr) {
            if (!isOddDone) {
                oddArr.add(str);
                isOddDone = true;
            } else {
                evenArr.add(str);
                isOddDone = false;
            }
        } //for

    } //method


    /**
     * show the content of an array list.
     * @param arr - the array list
     */
    public static void showNodeQueue(AYQueue<Node> q)
    {
            for (int i = 0; i < q.size(); i++)
            {
                Node node = q.dequeue();
                System.out.format("Element #%d = [%s]%n", i, node.getNodeID());
                q.enqueue(node);
            } //for

    } // method


    private static void testshowNodeQueue()
    {
//        AYQueue<Node> q = new AYQueue();
//        Node n1 = new Node(null, 15, 9, 0);
//        n1.setNodeID(1);
//        Node n2  = new Node(n1,  3, 1, 1);
//        n2.setNodeID(2);
//
//
//        q.enqueue(n1);
//        q.enqueue(n2);
//
//        showNodeQueue(q);
//        showNodeQueue(q);

    } // method


    /**
     * show the content of an array list.
     * @param arr - the array list
     */
    public static void showNodeArrayList(ArrayList<Node> arr)
    {
            for (int i = 0; i < arr.size(); i++)
            {
                Node node = arr.get(i);
                System.out.format("Element #%d = [%s]%n", i, node.getNodeID());
            } //for

    } // method


    private static void testshowNodeArrayList()
    {
//        ArrayList<Node> arr = new ArrayList();
//        Node n1 = new Node(null, 15, 9, 0);
//        n1.setNodeID(1);
//        Node n2  = new Node(n1,  3, 1, 1);
//        n2.setNodeID(2);
//
//
//        arr.add(n1);
//        arr.add(n2);
//
//        showNodeArrayList(arr);
//        showNodeArrayList(arr);

    } // method


    /**
     * accumulate the content of the array list with new array list content.
     */
    public static void accumulateResults(double[] avgPArr,
        ArrayList<Double> oneRowResultArr) {

        int index = 0;

        for (double res : oneRowResultArr)
            avgPArr[index++] += res;

    } //method


    /**
     * accumulate the content of the array list with new array list content.
     */
    public static void calculateAvg(double[] avgPArr, int itemNum) {

        for (int i = 0; i < avgPArr.length; i++)
            avgPArr[i] /= itemNum;

    } //method


    /**
     * Scramble the given array and return it.
     * post-condition: the given array is intact.
     * @param inArr - the given array list
     * @return the scrambled array.
     */
    public static <E> ArrayList<E> scrambleArrayList(ArrayList<E> inArr)
    {
        ArrayList<E> scrambleArr = new ArrayList();
        scrambleArr.addAll(inArr);
        Random rndVar = new Random();
        int size = inArr.size();
        E tempStr;

        for(int k = 0; k < size; k++) {
            int i = rndVar.nextInt(size);
            tempStr = scrambleArr.get(i);
            scrambleArr.remove(i);
            scrambleArr.add(tempStr);

        } //for

        return scrambleArr;

    } //method


    private static void testscrambleArrayList() {

        ArrayList<String> inArr = new ArrayList();
        inArr.add("A");
        inArr.add("B");
        inArr.add("C");
        inArr.add("D");
        inArr.add("E");
        inArr.add("F");

        ArrayList<String> scrambleArr = scrambleArrayList(inArr);

        showArrayList(inArr);

        showArrayList(scrambleArr);

//        for (int i = 0; i < 5; i++) {
//            for (int j = 0; j < 3; j++)
//                if (j == 1) break;
//
//            System.out.println("Hello");
//        }


    } // method


    /**
     * Show the content of an array.
     * @param arr - the array
     */
    public static void showStringArray(String[] arr)
    {
        System.out.println("...........................................");

        for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
        }
        System.out.println();

    } //method


    /**
     * show the content of an array list.
     * @param arr - the array list
     */
    public static void showDoubleArray(double[][] arr)
    {
        System.out.println("...........................................");

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] + "    ");
            }

            System.out.println();
        }

    } //method


    private static void testshowDoubleArray()
    {
       double[][] arr = {{0.5, 3, 6, 20},
                         {0.6, 10, 76, 9},
                         {1.2, 15, 34, 1}
                        };
       showDoubleArray(arr);
    } //method


    /**
     * Divide the given array list into two half and return them in the given
     * array lists.
     * @param inArr - the given array list
     * @param HalfOneArr - the first half array list
     * @param HalfTwoArr - the second half array list
     */
    public static <E> void divideArrIntoHalfArr(
        ArrayList<E> inArr, ArrayList<E> halfOneArr, ArrayList<E> halfTwoArr) {

        int size = inArr.size() / 2;

        for (int i = 0; i < size; i++)
            halfOneArr.add(inArr.get(i));

        for (int i = size; i < inArr.size(); i++)
            halfTwoArr.add(inArr.get(i));


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
        testshowDoubleArray();

	} // main method


} // class



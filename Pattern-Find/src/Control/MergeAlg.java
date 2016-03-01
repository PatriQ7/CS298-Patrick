package Control;

import Beans.Node;
import Beans.PTA;
import Util.GenUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * This is an abstract class representing the merge algorithms.
 *
 */
public abstract class MergeAlg implements Serializable {
    
// ........................ D A T A   F I E L D S ............................//
// ............. G L O B A L   P R I V A T E   C O N S T A N T S .............//
// .............. G L O B A L   P U B L I C   C O N S T A N T S ..............//
// ................. G L O B A L   P R I V A T E   V A R S ...................//

    /** Holds the number of merged nodes. */
    private static int mergedNodesNum = 1;

//.............................. G E T T E R S ...............................//

    public int getMergedNodesNum() {
        return mergedNodesNum;
    }
    
// ...................... P R I V A T E   M E T H O D S ......................//

    /**
     * calculate Hoeffding bound for two nodes and return true if the inequality
     * holds and false otherwise.
     * The larger the gama, the less the merge and vice versa.
     * @param n1 - total number of strings of node1
     * @param f1 - frequency of a specific symbol of node 1
     * @param n2 - total number of strings of node1
     * @param f2 - frequency of a specific symbol of node 2
     * @param gama  = Math.sqrt(0.5 * Math.log(2/alpha)) this is calculated once
     * and will pass to this method for efficiency purpose
     * @return true if they differ and false otherwise
     */
    private static boolean isDifferent(
        double n1, double f1, double n2, double f2, double gama) {

        double d1 = Math.abs(f1 / n1 - f2 / n2);
        double d2 = gama * (1.0 / Math.sqrt(n1) + 1.0 / Math.sqrt(n2));
        return d1 > d2;
//        return true;

    } //method


    /**
     * update numReached, numAccepted, and the children's numOut after merging
     * @param nodeI - the first node
     * @param nodeJ - the second node
     */
    private static void fold(Node nodeI, Node nodeJ) {

//    	System.out.println("\n\n**********************"
//    			+ "Before Folding Nodes"
//    			+ "**********************\n"
//    			+ "From: \n" + nodeI.toString() + "\n"
//    			+ "To: \n" + nodeJ.toString() + "\n"
//    			+ "********************************************\n");
    	
        // update reached and accepted frequency on nodeI
        nodeI.addNumReached(nodeJ.getNumReached());
        nodeI.addNumAccepted(nodeJ.getNumAccepted());

        // update numOut info
        HashMap<Integer, Integer> iNumOut = nodeI.getNumOut();
        HashMap<Integer, Integer> jNumOut = nodeJ.getNumOut();

        /**this array list is used to prevent the concurrent modification error. */
        ArrayList<Integer> jChildArr =
            GenUtil.convertHashMapKeysToArrayList(jNumOut);

        for (Integer jKey : jChildArr) {

            Integer jValue = jNumOut.get(jKey);
            Integer iValue = iNumOut.get(jKey);

            if (iValue != null) { //node I contains the key
                iNumOut.put(jKey, jValue + iValue);

            } else { 
                iNumOut.put(jKey, jValue);
                
            } //else

//            System.out.println("\n\n**********************"
//        			+ "After Folding Nodes"
//        			+ "**********************\n"
//        			+ "Node I: \n" + nodeI.toString() + "\n"
//        			+ "********************************************\n");
            
        } //for

    } //method


    /**
     * redirect the parent of nodeJ to nodeI
     * @param nodeI - the node I
     * @param nodeJ - the node J
     */
    static void merge2SingleNodes(Node nodeI, Node nodeJ) {

        Node jParent = nodeJ.getParent();
        int  jSymbol = nodeJ.getSymbol();

//        System.out.println("Creating New Nodes for: " + jParent.toString());
        jParent.setChild(jSymbol, nodeI);

    } //method

    /**
     * Folds the two nodes and recursively continue for all their children
     * @param nodeI - the first node
     * @param nodeJ - the second node
     */
    static void determinize(Node nodeI, Node nodeJ) {

        //transfer the j's numOut info to i
        fold(nodeI, nodeJ);
        mergedNodesNum++;

        // fold successors
        HashMap<Integer, Node> iChildren = nodeI.getChildren();
        HashMap<Integer, Node> jChildren = nodeJ.getChildren();

        ArrayList<Integer> jChildArr =
            GenUtil.convertHashMapKeysToArrayList(jChildren);

        for (Integer jKey : jChildArr) {

            Node iChild = iChildren.get(jKey);
            Node jChild = jChildren.get(jKey);
            
            if (iChild != null) { // iNode contains the jKey
                // recursively determinize the childern
//            	System.out.println("\n\n**********************"
//            			+ "Start Determinize"
//            			+ "**********************\n"
//            			+ "From: \n" + iChild.toString() + "\n"
//            			+ "To: \n" + jChild.toString() + "\n"
//            			+ "********************************************\n");
                determinize(iChild, jChild);
//                System.out.println("\n\n**********************"
//            			+ "End Determinize"
//            			+ "**********************\n"
//            			+ "From: \n" + iChild.toString() + "\n"
//            			+ "To: \n" + jChild.toString() + "\n"
//            			+ "********************************************\n");
//                System.out.println("\n\nEnd  of Det Merge for: " + iChild.toString() + " **** " + jChild.toString());

            } else { //added per my algorithm modification
                //add the jChild to iNode
//            	System.out.println("\n\n**********************\n"
//            			+ "Creating new Parent for " + jChild.toString() + "**** "
//            			+ "\nTo node" + nodeI.toString() );
                iChildren.put(jKey, jChild);
                jChild.setParent(nodeI);

            } //else

        } //while

    } //method


    /**
     * check if two nodes are compatible in f(symbol). = is equal f(a)
     * @param nodeI - the first node
     * @param nodeJ - the second node
     * @param gama - the parameter gama = Math.sqrt(0.5 * Math.log(2/alpha))
     * @return true if they are compatible and false otherwise
     */
    private static boolean isDifferentChildernOutgoingProb(
        Node nodeI, Node nodeJ, double gama, Set<Integer> nodesSymbolSet,
        double n1, double n2) {

        for (int symbol : nodesSymbolSet) {

            //outgoing probability test
            //if the node doesn't send out a specific symbol, f(sysmbol) = 0
            if (isDifferent(n1, nodeI.getNumOut(symbol),
                            n2, nodeJ.getNumOut(symbol), gama))
                
                return true;
            
        } //for

        return false;

    } //method


    /**
     * check if two nodes are compatible.
     * @param nodeI - the first node
     * @param nodeJ - the second node
     * @param gama - the parameter gama = Math.sqrt(0.5 * Math.log(2/alpha))
     * @return true if they are compatible and false otherwise
     */
    static boolean isCompatible(Node nodeI, Node nodeJ, double gama) {

    	double n1 = nodeI.getNumReached();
        double n2 = nodeJ.getNumReached();
        

        // termination test (n,f(#))
        if (isDifferent(n1, nodeI.getNumAccepted(),
                        n2, nodeJ.getNumAccepted(), gama))
            return false;
        
        // symbol numOut test (n,f(a))
        Set<Integer> nodesSymbolSet = Node.getSymbolsOutSet(nodeI, nodeJ);

        if (isDifferentChildernOutgoingProb(
            nodeI, nodeJ, gama, nodesSymbolSet, n1, n2)) {
            return false;
        }

        for (int symbol : nodesSymbolSet) {

            // recursive test for successors
            Node iChild = nodeI.getChild(symbol);
            Node jChild = nodeJ.getChild(symbol);

            if (iChild == null || jChild == null)  continue;

            if (!isCompatible(iChild, jChild, gama)) {
                return false; // at least one pair of children are not compatible
            }


        } //for

        return true;

    } //method
    

    private static void testAll2()
    {
        double alpha = 0.8;
        double gama = Math.sqrt(0.5 * Math.log(2 / alpha));

//        Node n1 = new Node(null, 15, 9, 0);
//        Node n2  = new Node(n1,  3, 1, 1);
//        Node n3  = new Node(n1,  3, 0, 2);
//        Node n4  = new Node(n2,  2, 2, 1);
//        Node n5  = new Node(n3,  2, 0, 1);
//        Node n6  = new Node(n3,  1, 0, 2);
//        Node n7  = new Node(n5,  1, 1, 1);
//        Node n8  = new Node(n5,  1, 0, 2);
//        Node n9  = new Node(n6,  1, 1, 1);
//        Node n10 = new Node(n8,  1, 0, 2);
//        Node n11 = new Node(n10, 1, 1, 1);

//        n1.setChild(1, n2, 3);
//        n1.setChild(2, n3, 3);
//        n2.setChild(1, n4, 2);
//        n3.setChild(1, n5, 2);
//        n3.setChild(2, n6, 1);
//        n5.setChild(1, n7, 1);
//        n5.setChild(2, n8, 1);
//        n6.setChild(1, n9, 1);
//        n8.setChild(2, n10, 1);
//        n10.setChild(1, n11, 1);

//        System.out.println("\n\n");
//        System.out.println("\n\nresult:\n" + isCompatible(n1, n2, gama));

//        System.out.println("\n\nMerging Node1 and Node2\n");
//        merge2SingleNodes(n1, n2);
//        determinize(n1, n2);
//
//        System.out.println("\n\n");
//        System.out.println("n1 = "  + n1.toString());
//        System.out.println("n2 = "  + n2.toString());
//        System.out.println("n3 = "  + n3.toString());
//        System.out.println("n4 = "  + n4.toString());
//        System.out.println("n5 = "  + n5.toString());
//        System.out.println("n6 = "  + n6.toString());
//        System.out.println("n7 = "  + n7.toString());
//        System.out.println("n8 = "  + n8.toString());
//        System.out.println("n9 = "  + n9.toString());
//        System.out.println("n10 = " + n10.toString());
//        System.out.println("n11 = " + n11.toString());
//
//        System.out.println("\n\n***** isCompatible Node1 and Node3 *****");
//        System.out.println("\n\nresult:\n" + isCompatible(n1, n3, gama));
//
//        System.out.println("\n\nMerging Node1 and Node5\n");
//        merge2SingleNodes(n1, n5);
//        determinize(n1, n5);
//
//        System.out.println("\n\n");
//        System.out.println("n1 = "  + n1.toString());
//        System.out.println("n3 = "  + n3.toString());
//        System.out.println("n6 = "  + n6.toString());
//        System.out.println("n9 = "  + n9.toString());
//
//        System.out.println("\n\n***** isCompatible Node1 and Node6 *****");
//        System.out.println("\n\nresult:\n" + isCompatible(n1, n6, gama));

    } //method


    /**
     * Apply ALERGIA algorithm with pre-order search
     * merge the compatible nodes of a PTA and fold them and their
     * appropriate successors.
     */
    static double calculateGama(double alpha) {

        if (alpha == 0.0) return Math.sqrt(0.5 * Double.MAX_VALUE) ;
        if (alpha >= 2.0) return Double.MIN_VALUE;
        return Math.sqrt(0.5 * Math.log(2 / alpha));

    } //method


    /**
     * Apply merging algorithm. This method will be implemented by the
     * subclasses. It merges the compatible nodes of a PTA and fold them and
     * their appropriate successors.
     * @param tree - the PTA tree
     * @param alpha - the alpha
     * @return the tree with merged nodes using the given algorithm.
     */
    public abstract PTA applyAlgorithm(PTA tree, double alpha);


    /**
     */

} //class


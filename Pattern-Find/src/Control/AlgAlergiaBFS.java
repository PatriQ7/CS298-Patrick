
package Control;

import Beans.AYQueue;
import Beans.Node;
import Beans.PTA;
import java.util.ArrayList;

/**
 * Apply BFS strategy for traversing the PTA tree in Alergia algorithm.
 *
 */
public class AlgAlergiaBFS extends MergeAlg {

// ...................... P U B L I C   M E T H O D S ........................//

    /**
     * Apply ALERGIA algorithm with pre-order search
     * merge the compatible nodes of a PTA and fold them and their
     * appropriate successors.
     */
    @Override
    public PTA applyAlgorithm(PTA tree, double alpha) {
        
        //final double gama = Math.sqrt(0.5 * Math.log(2 / alpha));
        final double gama = calculateGama(alpha);

        Node nodeI0 = tree.getRoot();
        boolean isMerged;

        AYQueue<Node> jQ = new AYQueue(); // J nodes
        nodeI0.enqueueChildren(jQ);

        ArrayList<Node> iArr = new ArrayList(); // I nodes
        iArr.add(nodeI0);

        while (jQ.hasItems()) { //over the J's

            Node nodeJ = jQ.dequeue();
            isMerged = false;

            for(Node nodeI : iArr) { //over the I's
                if (isCompatible(nodeI, nodeJ, gama)) {
                    merge2SingleNodes(nodeI, nodeJ);
                    determinize(nodeI, nodeJ);
                    isMerged = true;
                    break;
                } //if

            } //for

            if (!isMerged) {
                nodeJ.enqueueChildren(jQ);
                iArr.add(nodeJ);
            }

        } //while
        //System.out.println("PTA Tree After Merging ......");
        //System.out.println(tree.toString_Rec());
        return tree;

    } //method


    /**
     * return the name of the algorithm.
     */
    @Override
    public String toString() {

        return "Alergia BFS";

    } //method


    /**
     */

} //class

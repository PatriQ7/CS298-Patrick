package Control;

import Beans.Node;
import Beans.PTA;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Apply DFS strategy for traversing the PTA tree in Alergia algorithm.
 *
 */
public class AlgAlergiaDFS extends MergeAlg {
    
// ...................... P U B L I C   M E T H O D S ........................//

    /**
     * Apply ALERGIA algorithm with pre-order search
     * merge the compatible nodes of a PTA and fold them and their
     * appropriate successors.
     */
    @Override
    public PTA applyAlgorithm(PTA tree, double alpha) {

        final double gama = calculateGama(alpha);

        Node nodeI0 = tree.getRoot();
        boolean isMerged;

        Stack<Node> jQ = new Stack(); // J nodes

        nodeI0.pushChildren(jQ);

        ArrayList<Node> iArr = new ArrayList(); // I nodes
        iArr.add(nodeI0);

        while (!jQ.empty()) { //over the J's

            Node nodeJ = jQ.pop();
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
                nodeJ.pushChildren(jQ);
                iArr.add(nodeJ);
            }

        } //while

        return tree;

    } //method


    /**
     * return the name of the algorithm.
     */
    @Override
    public String toString() {

        return "Alergia DFS";

    } //method


    /**
     */

} 

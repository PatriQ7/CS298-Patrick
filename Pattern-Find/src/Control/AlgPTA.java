
package Control;

import Beans.PTA;

/**
 * Apply NO strategy for traversing the PTA tree and return the PTA.
 *
 */
public class AlgPTA extends MergeAlg {

// ...................... P U B L I C   M E T H O D S ........................//


    /**
     * Apply NO algorithm to the given PTA and just return it.
     */
    @Override
    public PTA applyAlgorithm(PTA pta, double alpha) {

        return pta;

    } //method


    /**
     * return the name of the algorithm.
     */
    @Override
    public String toString() {

        return "No Merge Algorithm (PTA)";

    } //method


    /**
     */

} //class


package Beans;

import Util.GenUtil;
import java.io.Serializable;
import java.util.*;

/**
 * represent an element of a node object in PTA. Modification History: Date Who
 * Ver. Why -------- ----------------- ----
 * --------------------------------------------- Rachel Yu 1.0 is born 10/23/11
 * Nikhil Kalantri 2.0 Object oriented design, increasing the sample words to
 * @Modified by Prashanth Sandela - To reduce the storage space. 
 * 174
 */
public class Node implements Serializable {
	// ........................ D A T A F I E L D S
	// ............................//
	// ............. G L O B A L P R I V A T E C O N S T A N T S .............//
	// .............. G L O B A L P U B L I C C O N S T A N T S ..............//
	// ................. G L O B A L P R I V A T E V A R S ...................//

	/** hold a pointer to the parent of this node. */
	private Node parent;

	/** hold the symbol of this node. 
	 * Value Type changes to byte from int - Prashanth Sandela
	 * */
	private byte symbol;

	/** how many Strings pass this node. */
	private int numReached;

	/** hold node ID for tracing purpose. */
	private int nodeID;

	/**
	 * Holds the status of visited or not. This is particularly used by SDFA
	 * toString method.
	 */
	private boolean isVisited;

	/** hold node level for tracing purpose. */
	private int nodeLevel;

	/** hold how many strings ends (accepted) at this node. */
	private int numAccepted;

	/** <transition symbol, number of outgoing symbol> */
	private HashMap<Integer, Integer> numOut;

	/** <transition symbol, the child Node> */
	private HashMap<Integer, Node> children;

	// ........................ C O N S T R U C T O R S
	// ..........................//

	public Node(int nodeID, Node parent, byte symbol, int nodeLevel) {
		this.parent = parent;
		this.symbol = symbol;
//		this.symbol = symbol;
		this.nodeID = nodeID;
		this.nodeLevel = nodeLevel;
		this.isVisited = false;
		this.numAccepted = 0;
		this.numReached = 0;
		this.children = new HashMap();
		this.numOut = new HashMap();

	} // constructor

	public Node() {
		this.parent = null;
		this.symbol = 0;
		this.numAccepted = 0;
		this.numReached = 0;
		this.nodeID = 1;
		this.nodeLevel = 0;
		this.isVisited = false;
		this.children = new HashMap();
		this.numOut = new HashMap();

	} // constructor

	// public Node(Node parent, int numReached, int numAccepted, int symbol)
	// {
	// this.parent = parent;
	// this.symbol = symbol;
	// this.nodeID = 0;
	// this.nodeLevel = 0;
	// this.isVisited = false;
	// this.numReached = numReached;
	// this.numAccepted = numAccepted;
	// this.children = new HashMap();
	// this.numOut = new HashMap();
	//
	// } //constructor

	// .............................. G E T T E R S
	// ...............................//

	public HashMap<Integer, Node> getChildren() {
		return children;
	}

	public int getChildrenSize() {
		return children.size();
	}

	public int getNodeLevel() {
		return nodeLevel;
	}

	public boolean isLeaf() {

		if (children.isEmpty())
			return true;
		else
			return false;
	}

	public int getNumAccepted() {
		return numAccepted;
	}

	public HashMap<Integer, Integer> getNumOut() {
		return numOut;
	}

	public int getNumOut(int symbol) {

		Integer val = numOut.get(symbol);

		if (val == null)
			return 0;
		else
			return val;
	}

	public Node getParentRelavantChild() {

		return parent.getChild(symbol);
	}

	public Node getParent() {

		return parent;
	}

	public int getSymbol() {

		return symbol;
	}

	public Node getChild(int symbol) {
		return children.get(symbol);

	}

	public int getNumReached() {
		return numReached;
	}

	public boolean isVisited() {
		return isVisited;
	}

	public int getNodeID() {
		return nodeID;
	}

	// .............................. S E T T E R S
	// ...............................//

	public void incNumAccepted() {
		this.numAccepted++;
	}

	public void setIsVisited(boolean isVisited) {
		this.isVisited = isVisited;
	}

	public void incNumReached() {
		this.numReached++;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public void setChild(int symbol, Node child) {
		this.children.put(symbol, child);
	}

	public void setChild(int symbol, Node child, int numO) {
		this.children.put(symbol, child);
		this.numOut.put(symbol, numO);
	}

	public void incChildNumOut(int symbol) {
		this.numOut.put(symbol, numOut.get(symbol) + 1);
	}

	public void setNumAccepted(int numAccepted) {
		this.numAccepted = numAccepted;
	}

	public void setNumReached(int numReached) {
		this.numReached = numReached;
	}

	// ...................... P R I V A T E M E T H O D S
	// ......................//
	// ...................... P U B L I C M E T H O D S
	// ........................//

	/**
	 * add the given value to the numAccepted value
	 * 
	 * @param numAccepted
	 *            - the addendum
	 */
	public void addNumAccepted(int numAccepted) {
		this.numAccepted += numAccepted;
	}

	/**
	 * add the given value to the numReached value
	 * 
	 * @param numReached
	 *            - the addendum
	 */
	public void addNumReached(int numReached) {
		this.numReached += numReached;
	}

	/**
	 * check if the given child exists.
	 * 
	 * @param symbole
	 *            - the symbol of the child
	 * @return true if the child exists and false otherwise
	 */
	public boolean containChild(int symbol) {
		return children.containsKey(symbol);

	} // method

	// @Override
	// /**
	// * convert this object to string.
	// * @return the string of this object
	// */
	// public String toString()
	// {
	// String initStr = this.toStringParent() +
	// "{" + nodeID + "[" + numReached + ":" + numAccepted + "] --> ";
	//
	// StringBuilder sb = new StringBuilder(initStr);
	//
	// for (Map.Entry<Integer, Integer> en : numOut.entrySet()) {
	//
	// sb.append(en.getKey()).append("[").append(en.getValue()).append("]::");
	// } //for
	//
	// String oStr = sb.toString();
	// oStr = StringUtil.trimOccuranceFromEnd(oStr, "::");
	//
	// return oStr + "}";
	//
	// } //method

	/**
	 * This method is particularly used for PTA toString method. Represent this
	 * object's information as a string.
	 * 
	 * @return the representation of this object as a string
	 */
	public String toStringForPTA() {
		StringBuilder sb = new StringBuilder();

		sb.append("{(").append(nodeID).append(")-").append(symbol).append("[")
				.append(numReached).append(":").append(numAccepted)
				.append("]}");

		if (!this.isLeaf())
			sb.append(" --> ");
		else
			sb.append(" --> Leaf");

		return sb.toString();
	} // method

	/**
	 * This method is particularly used for PTA toString method. Represent this
	 * object's information as a string.
	 * 
	 * @return the representation of this object as a string
	 */
	public String toStringForSDFA() {
		StringBuilder sb = new StringBuilder();

		sb.append("{(").append(nodeID).append(")-").append(symbol).append("[")
				.append(numReached).append(":").append(numAccepted)
				.append("]}");

		if (!this.isVisited)
			sb.append(" --> ");
		// else sb.append(toStringParent());

		return sb.toString();
	} // method

	/**
	 * convert this object to string.
	 * 
	 * @return the string of this object
	 */
	public String toStringParent() {
		if (parent != null)
			return "{" + parent.getNodeID() + "} <-- ";

		return "{null} <-- ";
	} // method

	/**
	 * push all children of this node into the given stack
	 * 
	 * @param s
	 *            - the given stack
	 * @return the number of children
	 */
	public int pushChildren(Stack s) {

		int numOfChildren = 0;

		for (Node child : children.values()) {
			s.push(child);
			numOfChildren++;

		} // for

		return numOfChildren;

	} // method

	/**
	 * push unvisited children of this node into the given stack
	 * 
	 * @param s
	 *            - the given stack
	 * @return the number of children
	 */
	public int pushUnVisitedChildren(Stack s) {

		int numOfChildren = 0;

		for (Node child : children.values()) {
			if (!child.isVisited) {
				s.push(child);
				numOfChildren++;
			}

		} // for

		return numOfChildren;

	} // method

	/**
	 * enqueue the children of this node
	 * 
	 * @param q
	 *            - the given queue
	 */
	public void enqueueChildren(AYQueue q) {

		for (Node child : children.values())
			q.enqueue(child);

	} // method

	private static void testenqueueChildren() {

		String[] symbolsStrArr = { "1 1 0", "-", "-", "-", "0", "-", "0 0",
				"0 0", "-", "-", "-", "1 0 1 1 0", "-", "-", "1 0 0" };
		String emptyStringSymbol = "-";
		ArrayList<String> arr = new ArrayList(Arrays.asList(symbolsStrArr));

		PTA tree = new PTA(arr, emptyStringSymbol);

		System.out.println("the tree:\n" + tree.toString());

		Node node = tree.getRoot();
		AYQueue<Node> q = new AYQueue();

		node.enqueueChildren(q);

		System.out.println("Q before loop: ");
		GenUtil.showNodeQueue(q);

	} // test method

	/**
	 * get all symbols out from two nodes in a set
	 * 
	 * @param nodeI
	 *            - the first node
	 * @param nodeJ
	 *            - the second node
	 * @return the set
	 */
	public static Set<Integer> getSymbolsOutSet(Node nodeI, Node nodeJ) {

		HashMap<Integer, Node> iChildren = nodeI.getChildren();
		HashMap<Integer, Node> jChildren = nodeJ.getChildren();

		Set<Integer> ijNodeSet = new HashSet<>();

		for (Integer iKey : iChildren.keySet())
			ijNodeSet.add(iKey);

		for (Integer jKey : jChildren.keySet())
			ijNodeSet.add(jKey);

		return ijNodeSet;

	} // method

	private static void testgetSymbolsOutSet() {
		String[] symbolsStrArr = { "1 1 0", "-", "-", "-", "0", "-", "0 0",
				"0 0", "-", "-", "-", "1 0 1 1 0", "-", "-", "1 0 0" };

		String emptyStringSymbol = "-";

		ArrayList<String> arr = new ArrayList(Arrays.asList(symbolsStrArr));
		PTA tree = new PTA(arr, emptyStringSymbol);
		System.out.println("the tree2:\n" + tree.toString());

		Node nodeI = tree.getRoot();
		Node nodeJ = tree.getRoot().getChild(1);

		System.out.println("result:\n" + getSymbolsOutSet(nodeI, nodeJ));

	} // test method

/**
 * Converts the node value and returns NodeID, Number Reached, Number Accepted
 */
	public String toString()
	{
//		StringBuilder sb = new StringBuilder();
//		sb.append("Node ID: " + this.nodeID + "\n");
//		sb.append("Symbol: " + this.symbol + "\n");
//		sb.append("Number Reached: " + this.numReached + "\n");
//		sb.append("Number Accepted: " + this.numAccepted + "\n");
//		
//		return sb.toString();
		return "";
		
	}
	
} // class

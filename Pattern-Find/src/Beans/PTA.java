package Beans;

import Control.AlgAlergiaBFS;
import Control.Config;
import Util.StringUtil;
import dfamini.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Stack;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * represent an Prefix Tree Acceptor (PTA).
 *
 * Modification History: Date Who Ver Why -------- ----------------- ----
 * --------------------------------------------- 10/23/11 Nikhil Kalantri 1.0
 * this class is born.
 */
public class PTA implements Serializable {
// ........................ D A T A   F I E L D S ............................//
// ............. G L O B A L   P R I V A T E   C O N S T A N T S .............//
// .............. G L O B A L   P U B L I C   C O N S T A N T S ..............//
// ................. G L O B A L   P R I V A T E   V A R S ...................//
    /**
     * hold the root of this tree.
     */
    private Node root;
    private int start_node_id = 1;

    /**
     * hold the node id.
     */
    private int nodeID;

// ........................ C O N S T R U C T O R S ..........................//

    public PTA(Node node) {
        this.root = node;
    } //constructor


    public PTA(ArrayList<String> symbolsStrArr, String emptyStringSymbol) {

        root = new Node();
        nodeID = start_node_id;
        createPTA(symbolsStrArr, emptyStringSymbol);

    } //constructor


//.............................. G E T T E R S ...............................//
    public Node getRoot() {
        return root;
    }


    /**
     * Return total number of nodes.
     *
     * @return total number of nodes.
     */
    public int getTotNodes() {
        return nodeID; // one for root
    }

//.............................. S E T T E R S ...............................//
// ...................... P R I V A T E   M E T H O D S ......................//
// ...................... P U B L I C   M E T H O D S ........................//

    /**
     * get a node as input and insert one symbol into a new child if it does not
     * already exists or its child and return the child. The caller will
     * increment numAccepted and numReached of the child appropriately.
     *
     * @param curNode - the current node
     * @param symbol - the symbol
     * @return the node that the symbol is inserted.
     */
    private Node insertSymbol(Node curNode, byte symbol) {

        if (!curNode.containChild(symbol)) {

            Node newNode = new Node(++nodeID, curNode, symbol,
                curNode.getNodeLevel() + 1);
            curNode.setChild(symbol, newNode, 1); // 1 = num out
            curNode = newNode;

        } else {
            curNode.incChildNumOut(symbol);
            curNode = curNode.getChild(symbol);
        }

        return curNode;

    } //method


    private static void testinsertSymbol() {
//        Node n2 = tree.insertSymbol(tree.root, 1);
//        System.out.println("Insert 1:\n" + tree.toString());
//
//        Node n3 = tree.insertSymbol(tree.root, 2);
//        System.out.println("Insert 2:\n" + tree.toString());
//
//        Node n4 = tree.insertSymbol(n2, 1);
//        System.out.println("Insert 2:\n" + tree.toString());
//
//        Node n5 = tree.insertSymbol(n3, 1);
//        Node n6 = tree.insertSymbol(n3, 2);
//        System.out.println("Insert 2:\n" + tree.toString());
    } //test method


    /**
     * insert a string of symbols into this PTA.
     *
     * @param symbolsStr - the symbol string
     */
    Node curNode;
    private void insertString(String symbolsStr, String emptyStringSymbol) {

    	String[] symbolArr = symbolsStr.split("\\p{javaWhitespace}+");
        curNode = root; //insertion always starts from the root

        //empty string condition
        if (symbolArr.length == 1 && symbolArr[0].equals(emptyStringSymbol)) {

            curNode.incNumAccepted();
            curNode.incNumReached();
            return;
        } //if

        for (int i = 0; i < symbolArr.length; i++) {

            curNode.incNumReached();
            //the curNode will change to the child
            curNode = insertSymbol(curNode, Byte.parseByte(symbolArr[i]));

        } //for

        curNode.incNumAccepted();
        curNode.incNumReached();

    } //method


    /**
     * create the given PTA from the given strings of symbols.
     *
     * @param symbolsStrArr - the array of symbols strings.
     * @param emptyStringSymbol - the empty string symbol (usually "0")
     */
    private void createPTA(
        ArrayList<String> symbolsStrArr, String emptyStringSymbol) {
        for (String symbolStr : symbolsStrArr) {
            insertString(symbolStr, emptyStringSymbol);
        } //for

    } //method


    /**
     * Returns a copy of this object, or null if the object cannot be
     * serialized.
     */
    @Override
    public PTA clone() {

        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try (ObjectOutputStream out = new ObjectOutputStream(bos)) {
                out.writeObject(this);
                out.flush();
            }
            PTA pta;
            try (ObjectInputStream in = new ObjectInputStream(
                    new ByteArrayInputStream(bos.toByteArray()))) {


                pta = (PTA) in.readObject();

            }

            return pta;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    } //method


    private static void testclone() {
        //ArrayList<Node> arr = new ArrayList();

        String[] symbolsStrArr = {
            "1 1 0", "-", "-", "-", "0", "-", "0 0", "0 0", "-", "-", "-",
            "1 0 1 1 0", "-", "-", "1 0 0"
        };

        String emptyStringSymbol = "-";

        ArrayList<String> arr = new ArrayList(Arrays.asList(symbolsStrArr));

        PTA tree = new PTA(arr, emptyStringSymbol);
        //System.out.println("the tree2:\n" + tree.toString());


        PTA pta = tree.clone();

        pta.insertString("0 1", emptyStringSymbol);
        System.out.println("the tree:\n" + tree.toString());
        
        //Apply AlergiaBFS
        AlgAlergiaBFS bfs = new AlgAlergiaBFS();
        bfs.applyAlgorithm(pta, 0.1);
        
//        System.out.println("the tree2:\n" + pta.toString());

    } //test method

    /**
     * Traverses the tree based on pre-order and builds a string of the nodes.
     * @param node - the entry point of the tree
     * @param depth - the depth of the tree
     * @param sb  - the string builder which will contains the final string
     */
    

    private void preOrderTraverse(Node node, int depth, StringBuilder sb, int node_id) {
        sb.append(StringUtil.space4Creator(depth));

        if (node == null) {
            sb.append("null\n");
        } else {
            sb.append(node.toStringForPTA());
            
            for (Node child : node.getChildren().values()) {
                
                if(node_id != child.getNodeID()){
            		sb.append("\n");
            		preOrderTraverse(child, depth + 1, sb, child.getNodeID());
            	}

            	else
            	{
            		sb.append(StringUtil.space4Creator(depth));
            		sb.append("::::Rec_Over_Node: " + child.getNodeID() + "\tSymbol: " + child.getSymbol() + "\t Key: " +"\n");
            	}
            } //for
            sb.append("\n");
        }

    } //mehtod

    private ArrayList nodearr;
    int nodenum = 1;
    int key_tmp;
    private void preOrderTraverse_Rec(Node node, int depth, StringBuilder sb, int node_id) {    	  	
        nodearr.add(node_id);

        //System.out.println(nodearr.toString());
        sb.append(StringUtil.space4Creator(depth));

        if (node == null) {
            nodenum = 0;
            sb.append("null\n");
        } else {
        	if (node.getParent() != null) {
        		for (Entry<Integer, Node> entry : node.getParent().getChildren().entrySet()) {
        			if (entry.getValue().equals(node)) 
        				key_tmp = entry.getKey();                
                }
        		//Symbol: 0	Num Out:414	Rec_B
        		//                           e
        		sb.append("Symbol: " + Integer.toString(key_tmp-1) + "\tNum Out:" +node.getParent().getNumOut(key_tmp)+ "\t" +node.toStringForPTA());
        	}
        	else
        		sb.append(node.toStringForPTA());
            ArrayList<Integer> NodeAL = new ArrayList<>();
            HashMap<Integer, Node> Children = node.getChildren();
            for (Integer iKey : Children.keySet()) {
                NodeAL.add(iKey);
            }
            //for (Node child : node.getChildren().values()) {
            for (Integer sym : NodeAL) {
                Node child = node.getChild(sym);
                if(nodearr.contains(child.getNodeID())){                
                    sb.append("\n");
                    sb.append(StringUtil.space4Creator(depth+1));
                    //sb.append("Rec_Back_To_Node:" + child.getNodeID() + "\tSymbol: " + child.getSymbol() + "\n");
                    sb.append("Symbol: " + Integer.toString(sym-1) + "\tNum Out:" + node.getNumOut(sym) + "\tRec_Back_To_Node:" + child.getNodeID() +  "\n");
                    continue;
            	}
                
/*                else if(node_id == child.getNodeID()) {
                    sb.append(StringUtil.space4Creator(depth));
                    sb.append("::::Rec_Over_Node: " + child.getNodeID() + "\tSymbol: " + child.getSymbol() + "\n");
            	}
                */
                else if(node_id != child.getNodeID() && !(nodearr.contains(child.getNodeID()))){
                    sb.append(StringUtil.space4Creator(depth));
                    sb.append("\n");
                    nodenum++;
                    preOrderTraverse_Rec(child, depth + 1, sb, child.getNodeID());

            } //for
            sb.append("\n");
        } 
        }
        
    }

    

    /**
     * Traverses the tree based on post order and builds a string of the nodes.
     * @param sb - the input string builder.
     * @param root - the entry point of the tree.
     */
    private static void postOrderTreeTraverse(StringBuilder sb, Node root) {

        Stack<Node> levelStack = new Stack();
        levelStack.push(root);

        int oldLevel = 0;

        while (!levelStack.empty()) {

            Node curNode = levelStack.pop();

            if (curNode.getNodeLevel() - oldLevel == 1) { //consecutive nodes
                sb.append(curNode.toStringForPTA());
            } else {
                sb.append("\n");
                sb.append(StringUtil.stringRepeater(
                    curNode.getNodeLevel(), "                 "));
                sb.append(curNode.toStringForPTA());
            }
            oldLevel = curNode.getNodeLevel();
            curNode.pushChildren(levelStack);

        } //while

    } //method


    /**
     * Creates a string from all nodes of this tree.
     *
     * @return the string made of nodes key by pre order traverse.
     */
    @Override
    public String toString() {

       StringBuilder sb = new StringBuilder("\n*****   PTA  ******\n");
       sb.append("Total # of nodes = ").append(getTotNodes()).append("\n");

     //  postOrderTreeTraverse(sb, root);
//
      preOrderTraverse(this.root, 0, sb, start_node_id);
      return sb.toString();
//    	return null;

    } //method
    
    public String toString_Rec() {
       nodearr = new ArrayList();
       StringBuilder sb = new StringBuilder("\n*****   PTA  ******\n");
       sb.append("Total # of nodes = ").append(getTotNodes()).append("\n");

     //  postOrderTreeTraverse(sb, root);
//
      preOrderTraverse_Rec(this.root, 0, sb, start_node_id);
      //System.out.println("Total Node number: " + nodenum);
      return sb.toString();
//    	return null;

    } //method
    
    public void make_dfa_mini () {
    	ArrayList<Node> node_list_PTA = new ArrayList<>();
    	ArrayList<node> node_list = new ArrayList<>();
    	ArrayList<String> Final_Set = new ArrayList<>();
    	ArrayList<String> cins = new ArrayList<String>(Arrays.asList("0","1"));
    	ArrayList nodearr = new ArrayList<>();
    	
    	Node cur_node = this.root;
    	node tmp_node = new node(Integer.toString(cur_node.getNodeID()));
    	node_list_PTA.add(cur_node);
    	node_list.add(tmp_node);
    	nodearr.add(cur_node.getNodeID());
    	int index = 0;
    	while (index < node_list_PTA.size()) {
    		System.out.print(index + ": " + cur_node.getNodeID() + "-- ");
    		for (Integer key : cur_node.getChildren().keySet()) {
    			Node child = cur_node.getChild(key);
    			node tmp_child = new node(Integer.toString(child.getNodeID()));
    			if (!nodearr.contains(child.getNodeID())) {
    				node_list_PTA.add(child);
    				nodearr.add(child.getNodeID());
    				node_list.add(tmp_child);
    				node_list.get(index).children.put(Integer.toString(key-1), node_list.get(node_list.indexOf(tmp_child)));
    				System.out.print(key + "--" + tmp_child.node_ID + " ");
    			}
    			else {
    				node_list.get(index).children.put(Integer.toString(key-1), node_list.get(node_list_PTA.indexOf(child)));
    				System.out.print(key + "--" + tmp_child.node_ID + " ");
    			}
    	    }
    		index++;
    		System.out.println();
    		//System.out.println("Round:" + index);
    		if (index == node_list_PTA.size())
    			break;
    		else
    			cur_node = node_list_PTA.get(index);
    	}
    	
    	// Get Final Set
    	for (Node tmp : node_list_PTA) {
    		if (tmp.getNumAccepted() > 0) {
    			Final_Set.add((node_list.get(node_list_PTA.indexOf(tmp)).node_ID));
    		}
    	}

        
    	System.out.println("STARTING MAKING DFA MINI");
    	
    	//Write jff file
    	try {

			String content = "This is the content to write into file +1";

			File file = new File("./filename.txt");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("<structure>\n  <type>fa</type>\n  <automaton>\n");
			for (node tmpnode : node_list) {
				bw.write("    <state id=\"" + tmpnode.node_ID + "\"" + " name=\"" + tmpnode.node_ID + "\"" + ">\n      <x>100.0</x>\n      <y>100.0</y>\n");
				if (node_list.indexOf(tmpnode) == 0)
					bw.write("      <initial/>\n");
				if (Final_Set.contains(tmpnode.node_ID))
					bw.write("      <final/>\n");
				bw.write("    </state>\n");
			}
			for (node tmpnode : node_list) {
				for (String cin : cins) {
					bw.write("    <transition>\n      <from>" + tmpnode.node_ID + "</from>\n      <to>" + tmpnode.children.get(cin).node_ID + "</to>\n      <read>" + cin + "</read>\n    </transition>\n");
				}
			}
			bw.write("  </automaton>\n</structure>");
			
			
			
			bw.close();

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
        DFAmini new_case = new DFAmini();
        new_case.make_equiv_class(new_case.dfa_mini(new_case.load_info(node_list), node_list, Final_Set, cins), node_list, cins);
    	
    }


    private static void testtoString() {

        String emptyStringSymbol = "0";

        ArrayList<String> symbolsStrArr = new ArrayList();
        symbolsStrArr.add("3 6 4 2 5 2 3 3 6 4 2 5 2 3 3 6 4 2 5 2 3 7");
        symbolsStrArr.add("3 6 4 2 5 2 3 3 6 4 2 5 2 3 3 6 4 2 5 2 3 7");
        symbolsStrArr.add("3 6 4 2 5 2 3 3 6 4 2 5 2 3 3 6 4 2 5 2 3 7");
        symbolsStrArr.add("3 6 4 2 5 2 3 3 6 4 2 5 2 3 3 6 4 2 5 2 3 7");
        symbolsStrArr.add("3 6 4 2 5 2 3 3 6 4 2 5 2 3 3 6 4 2 5 2 3 7");


        PTA pta = new PTA(symbolsStrArr, emptyStringSymbol);

        System.out.println(pta.toString());


    } //test method


    /**
     */
// ........................ M A I N   M E T H O D ............................//
    /**
     * This main method is just for testing this class.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {

        testclone();

    } //main method
} //class


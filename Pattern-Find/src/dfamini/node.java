package dfamini;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class node {
	public String node_ID;
	public equivclass e_class = null;
	public boolean is_Fset;
	
	//private ArrayList<Map<String, node>> children = new ArrayList<>();
	public Map<String, node> children = new HashMap<String, node>();
	
	//public node(String node_ID, Map<String, node> children) {
	public node(String node_ID) {
		this.node_ID = node_ID;
		//this.children = children;
	}
	
	
}

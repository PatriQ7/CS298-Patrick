package dfamini;

public class table_node {
	
	public node x;
	public node y;
	public table_node entry;
	public boolean value;
	
	public table_node(node x, node y, boolean value) {
		this.x = x;
		this.y = y;
		this.value = value;
	}
}

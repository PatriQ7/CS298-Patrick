package dfamini;

import java.util.ArrayList;
import java.util.Arrays;

public class DFAmini {
	 
	public static ArrayList<ArrayList<table_node>> x_coor = new ArrayList<>();
	public static ArrayList<node> node_list = new ArrayList<>();
	
	//public static int finish_flag = 0;
	
	public static void main(String[] args) {
		System.out.println("Hello DFA-mini!");
		ArrayList<String> cins = new ArrayList<String>(Arrays.asList("0","1"));
		
		/*
		node A = new node("A");
		node B = new node("B");
		node C = new node("C");
		node D = new node("D");
		node E = new node("E");
		node F = new node("F");
		node G = new node("G");
		node H = new node("H");
		
		A.children.put("0", B);
		A.children.put("1", F);
		
		B.children.put("0", G);
		B.children.put("1", C);
		
		C.children.put("0", A);
		C.children.put("1", C);
		
		D.children.put("0", C);
		D.children.put("1", G);
		
		E.children.put("0", H);
		E.children.put("1", F);
		
		F.children.put("0", C);
		F.children.put("1", G);
		
		G.children.put("0", G);
		G.children.put("1", E);
		
		H.children.put("0", G);
		H.children.put("1", C);
		
		ArrayList <String> Final_Set = new ArrayList<>(new ArrayList(Arrays.asList("C")));
		
		*/
		
		node A = new node("A");
		node B = new node("B");
		node C = new node("C");
		node D = new node("D");
		node E = new node("E");
		node F = new node("F");
		node G = new node("G");
		node H = new node("H");
		
		A.children.put("0", H);
		A.children.put("1", B);
		
		B.children.put("0", H);
		B.children.put("1", A);
		
		C.children.put("0", E);
		C.children.put("1", F);
		
		D.children.put("0", E);
		D.children.put("1", F);
		
		E.children.put("0", F);
		E.children.put("1", G);
		
		F.children.put("0", F);
		F.children.put("1", F);
		
		G.children.put("0", G);
		G.children.put("1", F);
		
		H.children.put("0", C);
		H.children.put("1", C);
		
		ArrayList <String> Final_Set = new ArrayList<>(new ArrayList(Arrays.asList("F","G")));
		
		
		node_list.add(A);
		node_list.add(B);
		node_list.add(C);
		node_list.add(D);
		node_list.add(E);
		node_list.add(F);
		node_list.add(G);
		node_list.add(H);
		
		
		dfa_mini(load_info (node_list), Final_Set, cins);
		
		/*
		 ****** Print Loaded Info ******
		 */

		System.out.print("  ");

		for (int j = node_list.size()-1; j>0; j--)
			System.out.print(node_list.get(j).node_ID + " ");
		System.out.println("");
		for (ArrayList <table_node> tmp : x_coor) {
			System.out.print(tmp.get(0).x.node_ID + " ");
			for (table_node tmp_node : tmp) {
				if (tmp_node.value == true)
					System.out.print("x ");
				else
					System.out.print("e ");
			}
			System.out.println("");
		}
		
		make_equiv_class(x_coor);
		
	}
	
	
	private static ArrayList<ArrayList<table_node>> load_info (ArrayList<node> node_list) {	
		for ( int i = 0; i < (node_list.size()-1); i++) {
			ArrayList <table_node> tmp_list = new ArrayList<table_node> ();
			for (int j = node_list.size()-1; j>i; j--){
				tmp_list.add(new table_node(node_list.get(i), node_list.get(j), false));
			}
			x_coor.add(tmp_list);
		}		
		return x_coor;
	}
	
	
	private static void dfa_mini(ArrayList<ArrayList<table_node>> x_coor, ArrayList<String> Final_Set, ArrayList<String> cins) {
		int finish_flag = 0;
		/* Check Final State */
		for (ArrayList <table_node> tmp : x_coor) {
			for (table_node tmp_node : tmp) {
				boolean x = Final_Set.contains(tmp_node.x.node_ID);
				boolean y = Final_Set.contains(tmp_node.y.node_ID);
				if (x && !y || !x && y)
					tmp_node.value = true;
			}
			System.out.println("");
		}		
		
		/* Check Equivalence */
		int change_flag;
		while (finish_flag == 0){
			change_flag = 0;
			finish_flag = 1;
			node n_x = null;
			node n_y = null;
			for ( int i = 0; i < (node_list.size()-1); i++) {				
				for (int j = node_list.size()-i-2; j >= 0; j--) {
					if (x_coor.get(i).get(j).value == false) {
						for (String cin : cins) {
							n_x = x_coor.get(i).get(j).x.children.get(cin);
							n_y = x_coor.get(i).get(j).y.children.get(cin);
							
							if (n_x == null || n_y == null)
								continue;
							
							int entry_x = node_list.indexOf(n_x);
							int entry_y = node_list.indexOf(n_y);

							if (entry_x > entry_y) {
								int tmp = entry_y;
								entry_y = entry_x;
								entry_x = tmp;
							}
							if (entry_x > (node_list.size()-2))
								continue;
							if (entry_y <= entry_x)
								continue;
							
							x_coor.get(i).get(j).entry = x_coor.get(entry_x).get(node_list.size()-entry_y-1);
							
							if (x_coor.get(i).get(j).entry.value == true) {
								x_coor.get(i).get(j).value = true;
								change_flag = 1;
								break;
							}
						}
					}
				}
			}
			
			
			if (change_flag == 1) {
				finish_flag = 0;
			}
			else
				break;

		}
	}
	
	private void check_useless() {
		/*
		 * TBD
		 */
	}
	
	private static ArrayList<equivclass> make_equiv_class (ArrayList<ArrayList<table_node>> x_coor) {
		ArrayList<equivclass> equiv_class = new ArrayList<>();
		//equiv_class.sor;
		
		return equiv_class;
	}
	
}

package Beans;

import java.io.Serializable;
import java.util.LinkedList;

/*
 * simulate a generic queue by means of Linked List data structure
 *
 * Modification History:
 * Date     Who               Ver              	Why
 * -------- ----------------- ---- ---------------------------------------------
 * 10/28/11 Nikhil Kalantri  1.0  born.
 */
@SuppressWarnings("serial")
public class AYQueue<E> implements Serializable {

// ........................ D A T A   F I E L D S ............................//
// ............. G L O B A L   P R I V A T E   C O N S T A N T S .............//
// .............. G L O B A L   P U B L I C   C O N S T A N T S ..............//
// ................. G L O B A L   P R I V A T E   V A R S ...................//

    /** hold an object of the linked list. */
    private LinkedList<E> list;

// ........................ C O N S T R U C T O R S ..........................//

    public AYQueue() {
        list = new LinkedList<>();
    } //constructor


//.............................. G E T T E R S ...............................//
//.............................. S E T T E R S ...............................//
// ...................... P R I V A T E   M E T H O D S ......................//
// ...................... P U B L I C   M E T H O D S ........................//

    public void enqueue(E item) {
        list.addLast(item);
    }


    public E dequeue() {
        return list.poll();
    }


    public boolean hasItems() {
        return !list.isEmpty();
    }


    public int size() {
        return list.size();
    }


    public void addItems(AYQueue<? extends E> q) {
        while (q.hasItems()) {
            list.addLast(q.dequeue());
        }
    }


    /**
     * creates a string from all nodes of this tree.
     * Postcondition: this is a destructive method
     * @return the string of this object.
     */
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        while (this.hasItems()) {
            sb.append(this.dequeue().toString()).append("\n");
        }
        return sb.toString();

    } //method

    @SuppressWarnings("unused")
	private static void testtoString() {

//        AYQueue<Node> q = new AYQueue();
//
//        Node n1 = new Node(null, 15, 9, 0);
//        Node n2 = new Node(n1, 3, 1, 1);
//        Node n3 = new Node(n1, 3, 0, 2);
//        Node n4 = new Node(n2, 2, 2, 1);
//        Node n5 = new Node(n3, 2, 0, 1);
//        Node n6 = new Node(n3, 1, 0, 2);
//
//        q.enqueue(n1);
//        q.enqueue(n2);
//        q.enqueue(n3);
//        q.enqueue(n4);
//        q.enqueue(n5);
//        q.enqueue(n6);
//
//        System.out.println(q.toString());

    } //test method


    /**
     */

} //class


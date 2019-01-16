package heaps; // ******* <---  DO NOT ERASE THIS LINE!!!! *******

/* *****************************************************************************************
 * THE FOLLOWING IMPORT IS NECESSARY FOR THE ITERATOR() METHOD'S SIGNATURE. FOR THIS
 * REASON, YOU SHOULD NOT ERASE IT! YOUR CODE WILL BE UNCOMPILABLE IF YOU DO!
 * ********************************************************************************** */

import java.util.Iterator;

import lists.DoublyLinkedList;
import lists.EmptyListException;
import lists.IllegalListAccessException;
import lists.LinkedList;
import trees.EmptyTreeException;
import trees.linkedMinHeapTree;

/**
 * <p>A <tt>LinkedMinHeap</tt> is a tree (specifically, a <b>complete</b> binary tree) where every node is
 * smaller than or equal to its descendants (as defined by the <tt>compareTo() </tt>overridings of the type T).
 * Percolation is employed when the root is deleted, and insertions guarantee are performed in a way that guarantees
 * that the heap property is maintained. </p>
 *
 * <p>You <b>must</b> edit this class! To receive <b>any</b> credit for the unit tests related to this class,
 * your implementation <b>must</b> be a <i>"linked"</i>, <b>non-contiguous storage</b> (or, at least, not <i>necessarily</i>
 * contiguous storage) implementation based on a binary tree of nodes and references! </p>
 *
 * <p>Your background from CMSC132 as well as the implementation and testing framework of {@link trees.LinkedBinarySearchTree}
 * could be a help here. </p>
 * 
 * @author --- YOUR NAME HERE ---
 *
 * @param <T> The {@link Comparable} type of object held by the <tt>LinkedMinHeap</tt>.
 *
 * @see trees.LinkedBinarySearchTree
 * @see MinHeap
 * @see ArrayMinHeap
 */
public class LinkedMinHeap<T extends Comparable<T>> implements MinHeap<T> { // *** <-- DO NOT CHANGE THIS LINE!!! ***

	private static final RuntimeException UNIMPL_METHOD = new RuntimeException("Implement this method!");

	/* *********************************************
	 * PLACE YOUR PRIVATE AND PROTECTED FIELDS HERE!
	 * YOU MIGHT ALSO WANT TO PUT PRIVATE METHODS AND/OR CLASSES HERE!
	 * THE DESIGN CHOICE IS YOURS ENTIRELY.
	 * ******************************************** */

	private linkedMinHeapTree<T> ll;
	 

	/* ***********************************************************************************
	 * YOU SHOULD IMPLEMENT THE FOLLOWING METHODS. BESIDES THE INTERFACE METHODS,
	 * THOSE INCLUDE CONSTRUCTORS (DEFAULT, NON-DEFAULT, COPY) AS WELL AS EQUALS().
	 * PLEASE MAKE SURE YOU RECALL HOW ONE SHOULD MAKE A CLASS-SAFE EQUALS() FROM EARLIER
	 * JAVA COURSES!
	 *
	 * YOU SHOULD NOT CHANGE *ANY* METHOD SIGNATURES! IF YOU DO, YOUR CODE WILL NOT RUN
	 * AGAINST OUR TESTS!
	 * ********************************************************************************** */


	/**
	 *  Default constructor.
	 */
	public LinkedMinHeap(){
		/* FILL THIS IN WITH YOUR IMPLEMENTATION OF A DEFAULT CONSTRUCTOR, IF ANY. */
		ll=new linkedMinHeapTree<T>();
	}

	/**
	 *  Second, non-default constructor.
	 *  @param rootElement the element to create the root with.
	 */
	public LinkedMinHeap(T rootElement){
		ll=new linkedMinHeapTree<T>();
		ll.add(rootElement);
	}

	/**
	 * Copy constructor initializes the current MinHeap as a carbon
	 * copy of the parameter.
	 *
	 * @param other The MinHeap to copy the elements from.
	 */
	public LinkedMinHeap(MinHeap<T> other){
		ll=new linkedMinHeapTree<T>();
		Iterator<T> itr=other.iterator();
		while(itr.hasNext()) {
			ll.add(itr.next());
		}
	}

	/**
	 * Standard equals() method.
	 *
	 * @return true If the parameter Object and the current MinHeap
	 * are identical Objects.
	 */
	@Override
	public boolean equals(Object other){
		return ll.equals(other);
	}

	@Override
	public boolean isEmpty() {
		return ll.isEmpty();
	}

	@Override
	public int size() {

		return ll.size();
	}

	@Override
	public void clear() {

		ll.clear();
	}

	@Override
	public void insert(T element) {
		ll.add(element);
		//System.out.println("we are working on: "+element);

	} 

	@Override
	public T getMin() throws EmptyHeapException {
		T rv=null;
		try {
			rv=ll.getMin();
		} catch (EmptyTreeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rv;
	}

	@Override
	public T deleteMin() throws EmptyHeapException {
		return ll.deleteMin();
	}	
	
	
	@Override
	public Iterator<T> iterator() {
		return ll.iterator();
	}

}




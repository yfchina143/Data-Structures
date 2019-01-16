package priorityqueues; // ******* <---  DO NOT ERASE THIS LINE!!!! *******

/* *****************************************************************************************
 * THE FOLLOWING IMPORTS ARE HERE ONLY TO MAKE THE JAVADOC AND iterator() method signature
 * "SEE" THE RELEVANT CLASSES. SOME OF THOSE IMPORTS MIGHT *NOT* BE NEEDED BY YOUR OWN
 * IMPLEMENTATION, AND IT IS COMPLETELY FINE TO ERASE THEM. THE CHOICE IS YOURS.
 * ********************************************************************************** */
import exceptions.InvalidPriorityException;
import exceptions.InvalidCapacityException;
import fifoqueues.FIFOQueue;
import lists.EmptyListException;
import lists.IllegalListAccessException;
import lists.PriorityLinkedList;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * <p><tt>LinearPriorityQueue</tt> is a priority queue implemented as a linear {@link java.util.Collection}
 * of common {@link FIFOQueue}s, where the {@link FIFOQueue}s themselves hold objects
 * with the same priority (in the order they were inserted).</p>
 *
 * <p>You  <b>must</b> implement the methods in this file! To receive <b>any credit</b> for the unit tests related to this class, your implementation <b>must</b>
 * use <b>whichever</b> linear {@link java.util.Collection} you want (e.g {@link ArrayList}, {@link java.util.LinkedList},
 * {@link java.util.Queue}), or even the various {@link lists.List} and {@link FIFOQueue} implementations that we
 * provide for you. It is also possible to use <b>raw</b> arrays.</p>
 *
 * @param <T> The type held by the container.
 * 
 * @author  ---- YOUR NAME HERE ----
 *
 * @see MinHeapPriorityQueue
 *
 */
public class LinearPriorityQueue<T> implements PriorityQueue<T> { // *** <-- DO NOT CHANGE THIS LINE!!! ***

	private static final RuntimeException UNIMPL_METHOD = new RuntimeException("Implement this method!");

	/* *********************************************
	 * PLACE YOUR PRIVATE AND PROTECTED FIELDS HERE!
	 * YOU MIGHT ALSO WANT TO PUT PRIVATE METHODS AND/OR CLASSES HERE!
	 * THE DESIGN CHOICE IS YOURS ENTIRELY.
	 * ******************************************** */

	private PriorityLinkedList<T> pll; 
	private int size=0;

	/* ***********************************************************************************
	 * YOU SHOULD IMPLEMENT THE FOLLOWING METHODS. BESIDES THE INTERFACE METHODS,
	 * THOSE INCLUDE TWO CONSTRUCTORS. FOR THE PRIORITY QUEUE CLASSES, YOU
	 * WILL *NOT* NEED TO IMPLEMENT COPY CONSTRUCTORS AND EQUALS(), BUT IF YOU WOULD LIKE TO
	 * IMPLEMENT THOSE TO FACILITATE YOUR OWN TESTS AND CLIENT CODE, PLEASE FEEL FREE TO DO SO!
	 * THE CHOICE IS YOURS.
	 *
	 * YOU SHOULD NOT CHANGE *ANY* METHOD SIGNATURES! IF YOU DO, YOUR CODE WILL NOT RUN
	 * AGAINST OUR TESTS!
	 * ********************************************************************************** */

	/**
	 * Default constructor initializes the data structure with
	 * a default capacity. This default capacity will be the default capacity of the
	 * underlying data structure that you will choose to use to implement this class.
	 */
	public LinearPriorityQueue(){
		/* FILL THIS IN WITH YOUR IMPLEMENTATION OF A DEFAULT CONSTRUCTOR, IF ANY. */
		pll=new PriorityLinkedList<T>(); 
	}

	/**
	 * Non-default constructor initializes the data structure with 
	 * the provided capacity. This provided capacity will need to be passed to the default capacity
	 * of the underlying data structure that you will choose to use to implement this class.
	 * @see #LinearPriorityQueue()
	 * @param capacity The initial capacity to endow your inner implementation with.
	 * @throws InvalidCapacityException if the capacity provided is negative.
	 */
	public LinearPriorityQueue(int capacity) throws InvalidCapacityException{
		if(capacity<0) {
			throw new InvalidCapacityException("capacity wrong: "+capacity);
		}
		pll=new PriorityLinkedList<T>(); 
		size=0;
	}

	@Override
	public void enqueue(T element, int priority) throws InvalidPriorityException{
		pll.add(element, priority);
		size++;
	}

	@Override
	public T dequeue() throws EmptyPriorityQueueException {
		T temp=null;
		try {
			temp=pll.get(0);
			pll.delete(0);
			size--;
		} catch (IllegalListAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (EmptyListException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}

	@Override
	public T getFirst() throws EmptyPriorityQueueException {
		try {
			return pll.getFirst();
		} catch (EmptyListException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		if(size==0) {
			return true;
		}
		return false;
	}

	@Override
	public void clear() {
		pll.clear();
		size=0;
	}

	@Override
	public Iterator<T> iterator() {
		return pll.iterator();
	}

}
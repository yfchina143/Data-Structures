package heaps; // ******* <---  DO NOT ERASE THIS LINE!!!! *******

import java.util.ArrayList;

/* *****************************************************************************************
 * THE FOLLOWING IMPORT IS NECESSARY FOR THE ITERATOR() METHOD'S SIGNATURE. FOR THIS
 * REASON, YOU SHOULD NOT ERASE IT! YOUR CODE WILL BE UNCOMPILABLE IF YOU DO!
 * ********************************************************************************** */

import java.util.Iterator;

import lists.EmptyListException;
import lists.IllegalListAccessException;
import lists.StaticArrayList;
/**
 * <p><tt>ArrayMinHeap</tt> is a {@link MinHeap} implemented using an internal array. Since heaps are <b>complete</b>
 * binary trees, using contiguous storage to store them is an excellent idea, since with such storage we avoid
 * wasting bytes per <tt>null</tt> pointer in a linked implementation.</p>
 *
 * <p>You <b>must</b> edit this class! To receive <b>any</b> credit for the unit tests related to this class,
 * your implementation <b>must</b> be a <b>contiguous storage</b> implementation based on a linear {@link java.util.Collection}
 * or a raw Java array.</p>
 *
 * @author -- YOUR NAME HERE ---
 *
 * @see MinHeap
 * @see ArrayMinHeap
  */
public class ArrayMinHeap<T extends Comparable<T>> implements MinHeap<T> { // *** <-- DO NOT CHANGE THIS LINE!!! ***

	private static final RuntimeException UNIMPL_METHOD = new RuntimeException("Implement this method!");

	/* *********************************************
	 * PLACE YOUR PRIVATE AND PROTECTED FIELDS HERE!
	 * YOU MIGHT ALSO WANT TO PUT PRIVATE METHODS AND/OR CLASSES HERE!
	 * THE DESIGN CHOICE IS YOURS ENTIRELY.
	 * ******************************************** */
	private StaticArrayList<T> array;
	private int size=0;

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
	public ArrayMinHeap(){
		/* FILL THIS IN WITH YOUR IMPLEMENTATION OF A DEFAULT CONSTRUCTOR, IF ANY. */
		//System.out.println("we got contrcutor");
		array=new StaticArrayList<T>();
		size=0;
	}

	/**
	 *  Second, non-default constructor.
	 *  @param rootElement the element to create the root with.
	 */
	public ArrayMinHeap(T rootElement){
		array=new StaticArrayList<T>();
		size++;
		array.pushFront(rootElement);
	}

	/**
	 * Copy constructor initializes the current MinHeap as a carbon
	 * copy of the parameter.
	 *
	 * @param other The MinHeap to copy the elements from.
	 */
	public ArrayMinHeap(MinHeap<T> other){
		Iterator<T> itr=other.iterator();
		while(itr.hasNext()) {
			array.pushBack(itr.next());
			size++;
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
		return array.equals(other);
	}


	@Override
	public boolean isEmpty() {
		if(size==0) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public void clear() {
		array.clear();
		size=0;
	}



	@Override
	public void insert(T element) {
		array.pushBack(element);
		int location=size;
		int index=location;
		
		//System.out.println("we are working on: "+element);
		while(index>1){
				int Cindex=((index)/2);
				//System.out.println("the cindex is: "+Cindex);
				try {
					if(array.get(Cindex-1).compareTo(array.get(index-1))>0) {
						T tempC=array.get(Cindex-1);
						T temp=array.get(index-1);
						array.set(index-1,tempC);
						array.set(Cindex-1,temp);	
						//System.out.println("we have swapped: "+tempC+" and "+temp);
						
					}
				} catch (EmptyListException e) {
					// TODO Auto-generated catch block
					
					e.printStackTrace();
					break;
				} catch (IllegalListAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					break;
				}
				index=Cindex;
				
		}
		size++;
	}

	@Override
	public T getMin() throws EmptyHeapException {
		if(array.isEmpty()) {
			throw new EmptyHeapException("empty");
		}
		else {
			try {
				return array.get(0);
			} catch (EmptyListException | IllegalListAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public T deleteMin() throws EmptyHeapException {
		//System.out.println("in delete min");
		if(isEmpty()) {
			throw new EmptyHeapException("empty");
		}
		else if(size()==1) {
			T deleteValue=null;
			try {
				
				try {
					deleteValue=array.get(0);
				} catch (EmptyListException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				array.delete(0);
				size--;
			} catch (IllegalListAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return deleteValue;
		}
		else {
			T deleteValue=null;
			try {
				deleteValue=array.get(0);
				//System.out.println("in check size before "+array.size());
				array.delete(0);
				//System.out.println("in check size after "+array.size());
				T replaceValue;
				replaceValue = array.get(array.size()-1);
				//array.delete(array.size()-1);
				
				

				size--;
				array.pushFront(replaceValue);
				siftDown(0);
				array.delete(array.size()-1);
				
			} catch (EmptyListException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalListAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//System.out.println("we have deleted"+replaceValue);
			return deleteValue;
		}
	}
	/**
	 * recursive function to replace the value to its current location. MinHeap Style!
	 * @param StartIndex the index of current recursion 
	 * @throws IllegalListAccessException 
	 * @throws EmptyListException 
	 */
	public void siftDown(int startIndex) throws EmptyListException, IllegalListAccessException {
		//System.out.println("---sift start"+startIndex+"---");
		int left=2*startIndex+1;
		int right=2*startIndex+2;
		int minIndex;
		if(right>=array.size()) {
			if(left>=array.size()) {
				//System.out.println("---sift over"+startIndex+"---");
				return;
			}
			else {
				minIndex=left;
			}
		}
		else {
			if(array.get(right).compareTo(array.get(left))>=0) {
				minIndex=left;
			}
			else {
				minIndex=right;
			}
		}
		if(array.get(startIndex).compareTo(array.get(minIndex))>0) {
			T startValue=array.get(startIndex);
			T swapValue=array.get(minIndex);
			array.set(startIndex,swapValue);
			array.set(minIndex,startValue);
			//System.out.println("start index is: "+startIndex+"---start value is: "+startValue);
			//System.out.println("min index is: "+minIndex+"---min value is: "+swapValue);
			//System.out.println("---sift over"+startIndex+"---");
			siftDown(minIndex);
		}
		
	}



	@Override
	public Iterator<T> iterator() {
		return array.iterator(); /* ERASE THIS LINE AFTER IMPLEMENTING THE METHOD. */
	}
}

package trees;

/**
 * <p>A <tt>BinarySearchTree</tt> is a {@link Tree} with a maximum of 2 children nodes per node
 * which allows for adding and removing nodes in a sorted manner. As such, it will extend
 * the {@link Tree} interface but it will also restrict the objects it stores to Comparable ones.
 * There are only going to be two new methods in this interface: insert(T) and delete(T).</p>
 * 
 * <p>Following classic semantics, for every given node, elements in the left subtree of the node
 * are smaller (as defined by their <tt>compareTo()</tt> definitions) than the element at the node, which is
 * subsequently smaller than or equal to the nodes in the right subtree.</p>
 * 
 * <p>Note that this interface does not allow for methods that balance it. For BinarySearchTrees
 * that allow for auto-balancing, AVL trees or Red-Black trees should be used.</p>
 *
 * <p>You should <b>not</b> edit this class! It is given to you as a resource for your project.</p>
 *
 * @author  <a href="mailto:jasonfil@cs.umd.edu">Jason Filippou</a>
 *
 * @param <T> The Comparable type held by the <tt>BinarySearchTree</tt>.
 *
 */
public interface BinarySearchTree<T extends Comparable<T>> extends Tree<T>{
	
	/**
	 * Adds an element to the tree. <tt>BinarySearchTree</tt>s impose criteria for insertion
	 * (see description of the class), whereas general Trees do not.
	 * @param element The element to insert.
	 */
	public void add(T element);
	
	/**
	 * Removes the specified element from the tree and returns it.
	 * @param element The element to be removed.
	 * @throws EmptyTreeException If the tree is empty.
	 * @return the removed element or null if the element is not there.
	 */
	public T delete(T element) throws EmptyTreeException;
	
	/**
	 * Returns the height of the tree.
	 * @return an int representing the height of the tree.
	 */
	public int height();
	
	/**
	 * Returns the minimum element of the tree.
	 * @return The minimum element of the tree.
	 * @throws EmptyTreeException If the tree is empty.
	 */
	public T getMin() throws EmptyTreeException;
	
	/**
	 * Returns the maximum element of the tree.
	 * @return The maximum element of the tree.
	 * @throws EmptyTreeException If the tree is empty.
	 */
	public T getMax() throws EmptyTreeException;
}

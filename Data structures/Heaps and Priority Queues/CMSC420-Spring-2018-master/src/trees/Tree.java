package trees;
import java.util.Iterator;

/**
 * <p>A standard interface for a tree-like data structure. No assumption is made about
 * whether the tree is <i>n</i>-ary with a particular choice of non-negative integer <i>n</i>, whether it is balanced,
 * complete, full, etc. Classes implementing this interface will specify
 * the specific functionality and state of the Tree as well as its internal data storage
 * mechanisms. </p>
 *
 * <p>You should <b>not</b> edit this interface! It is given to you as a resource for your project.</p>
 *
 * @author  <a href="mailto:jasonfil@cs.umd.edu">Jason Filippou</a>
 *
 * @param <T> The type of Object held by the Tree data structure.
 *
 */
public interface Tree<T> extends Iterable<T> {
	
	/**
	 * Return the element at the root of the tree.
	 * @return The element at the root of the tree.
	 * @throws EmptyTreeException if the tree is empty.
	 */
	public T getRoot() throws EmptyTreeException;
	
	/**
	 * Queries the tree for emptiness.
	 * @return <tt>true</tt> if the tree contains no elements, <tt>false</tt> otherwise.
	 */
	public boolean isEmpty();
	
	
	/**
	 * Query the tree for its size (number of elements)
	 * @return The number of elements in the tree.
	 */
	public int size();
		
	/**
	 * Find the element in the tree. Throws an exception if it doesn't find it.
	 * @param element The element to be found
	 * @return A reference to the element in the tree and <tt>null</tt> if it's not there.
	 * @throws EmptyTreeException if the tree is empty.
	 */
	public T find(T element) throws EmptyTreeException;
	
	/**
	 * Generates a preorder traversal for the tree.
	 * @return An iterator which traverses the tree in preorder
	 * fashion. (left subtree-&gt; node -&gt; right subtree).
	 * @throws EmptyTreeException if the tree is empty.
	 */
	public Iterator<T> preorder() throws EmptyTreeException;
	
	/**
	 * Generates an inorder traversal for the tree.
	 * @return An iterator which traverses the tree in inorder
	 * fashion. (left subtree-&gt;node-&gt;right subtree).
	 * @throws EmptyTreeException if the tree is empty.
	 */
	public Iterator<T> inOrder() throws EmptyTreeException;
	
	/**
	 * Generates a postorder traversal for the tree.
	 * @return An iterator which traverses the tree in postorder
	 * fashion. (left subtree -&gt; right subtree -&gt; node).
	 * @throws EmptyTreeException if the tree is empty.
	 */
	public Iterator<T> postOrder() throws EmptyTreeException;
	
	/**
	 * Generates a breadth-getFirst search (BFS) of the tree (level-order
	 * traversal).
	 * @return An iterator which traverses the tree breadth-getFirst.
	 * @throws EmptyTreeException if the tree is empty.
	 */
	public Iterator<T> levelOrder() throws EmptyTreeException; 
	
	/**
	 * Clears the tree of all its elements.
	 */
	public void clear();
}

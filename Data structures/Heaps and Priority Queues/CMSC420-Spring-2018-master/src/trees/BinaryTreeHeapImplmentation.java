package trees;

import java.util.ArrayList;
import java.util.Iterator;

import fifoqueues.EmptyFIFOQueueException;
import fifoqueues.LinkedFIFOQueue;
import heaps.EmptyHeapException;


public class BinaryTreeHeapImplmentation<T> {
	private static final RuntimeException UNIMPL_METHOD = new RuntimeException("Implement this method!");
	protected Node<T> root;
	protected int count; // To facilitate size queries.
	protected Node<T> pos;
	protected char position;
	
	/**
	 * Default constructor initializes data structure.
	 */
	public BinaryTreeHeapImplmentation(){
		root = null;
		count = 0;
	}

	/**
	 * ADT-level copy constructor initializes the tree with 
	 * all the elements of the provided tree.
	 * @param other The root of the tree to copy elements from.
	 */
//	public BinaryTreeHeapImplmentation(BinarySearchTree<T> other){
//		if(other == null)
//			throw new RuntimeException("Cannot copy-construct from a null reference");
//		for(T el: other) {
//			add(el);
//			count++;
//		}
//	}
	
	/**
	 * Standard equals() method. Returns true if the parameter object
	 * is a carbon copy of the current object.
	 * 
	 * @param other the Object to compare with <tt>this</tt>.
	 * 
	 * @return <tt>true</tt> if <tt>other</tt> and <tt>this</tt>
	 * are carbon copies of one another.
	 */
	@Override
	public boolean equals(Object other){
		if(other == null)
			return false;
		if(other.getClass() != getClass())
			return false;
		BinaryTreeHeapImplmentation<T> ocasted = null;
		try {
			ocasted = (BinaryTreeHeapImplmentation<T>)other;
		}catch(ClassCastException exc){
			return false;
		}
		if(ocasted.size() != size())
			return false;
		Iterator<T> currEls = iterator(), otherEls = ocasted.iterator();
		while(otherEls.hasNext())
			if(!otherEls.next().equals(currEls.next()))
				return false;
		return true;
	}
	
	
	
	public T getRoot() throws EmptyTreeException{
		if(isEmpty())
			throw new EmptyTreeException("getRoot(): Tree is empty.");
		return root.getElement();
	}


	public boolean isEmpty() {
		return count == 0;
	}


	
	public int size() {
		return count;
	}


	public T find(int index) throws EmptyTreeException {
		throw UNIMPL_METHOD;
	}

	public Iterator<T> preorder() throws EmptyTreeException{
		if(root == null)
			throw new EmptyTreeException("preorder(): Tree was empty.");
		ArrayList<T> elementList = new ArrayList<T>();
		root.preorderTraversal(elementList);
		return elementList.iterator();
	}

	
	public Iterator<T> inOrder() throws EmptyTreeException{
		if(root == null)
			throw new EmptyTreeException("inorder(): Tree was empty.");
		ArrayList<T> elementList = new ArrayList<T>();
		root.inorderTraversal(elementList);
		return elementList.iterator();
	}


	public Iterator<T> postOrder() throws EmptyTreeException{
		if(root == null)
			throw new EmptyTreeException("postorder(): Tree was empty.");
		ArrayList<T> elementList = new ArrayList<T>();
		root.postorderTraversal(elementList);
		return elementList.iterator();
	}


	public Iterator<T> levelOrder() throws EmptyTreeException{
		if(root == null)
			throw new EmptyTreeException("levelorder(): Tree was empty.");
		ArrayList<T> elementList = new ArrayList<T>();
		try {

			LinkedFIFOQueue<Node<T>> nodeQueue = new LinkedFIFOQueue<Node<T>>();
			nodeQueue.enqueue(root);
			while(!nodeQueue.isEmpty()){
				Node<T> current = nodeQueue.dequeue();
				elementList.add(current.getElement());
				if(current.left != null)
					nodeQueue.enqueue(current.left);
				if(current.right != null)
					nodeQueue.enqueue(current.right);
			}
		} catch(EmptyFIFOQueueException exc){
			// Dummy catchblock because dequeue() is complaining.
		}
		return elementList.iterator();
	} 


	public T getMin() throws EmptyTreeException{
		return root.getElement();
	}




	
	public void clear() {
		root = null;
		count = 0;
		System.gc();
	}


	public Iterator<T> iterator() {
		Iterator<T> retVal = null;
		try {
			retVal = levelOrder();
		} catch(EmptyTreeException exc){
			return null;
		}
		return retVal;
	}

	
	public void add(T element,int pro) {
		Node<T> current =new Node<T>(pro,element);
		if(root==null) {
			root=current;
			count++;
			
			return;
		}
		else {
			if(root.left==null) {
				root.setLeft(current);
				root.left.setPrev(root);
				count++;
				siftup();
				//System.out.println("i got to root left");
				return;
			}
			else if(root.right==null) {
				//System.out.println("i got to root right");
				root.setRight(current);
				count++;
				root.right.setPrev(root);
				siftup();
				return;
			}
			else {
				//System.out.println("adding element: "+element);
				root.add(pro, element,Integer.toBinaryString(size()+1),root);
				count++;
				siftup();
				//siftup();
				return;
			}
		}
		
	}

	
	/* Removal of a node consists of two things: First, finding it and returning it
	 * to the caller, and, second, the actual removal of it. The second part will be 
	 * attained by Node.delete.
	 */
	public void siftup() {
		Node<T> temp=getLast();
		//System.out.println("this is pro data: "+temp.priority+"prev pro is:"+temp.prev.priority);
		if(temp==root) {
			return;
		}
		else {
			temp.siftup();
		}
	}
	
	
	
	
	
	public Node<T> getLast(){
		
		return root.getLast(Integer.toBinaryString(size()));
	}
	public Node<T> removeLast() {
		//System.out.println("bs for size 11 is "+Integer.toBinaryString(size()));
		Node<T> temp=root.removeLast(Integer.toBinaryString(size()));
		//System.out.println("i removed: "+temp);
		return temp;
	}
	public int height(){
		return root.height();
	}
	
	public T deleteMin() throws EmptyHeapException {
		
		if(isEmpty()) {
			throw new EmptyHeapException("empty");
		}else if(size()==1) {
			count--;
			T i=root.data;
			root=null;
			return i;
		}
		else {
			Node<T> min=removeLast();
			//System.out.println("this is in method delete min: "+min.data+"root data"+root.data);
			T i=root.data;
			root.data=min.data; 
			root.priority=min.priority;
			siftdown(root);
			count--;
			return i;
		}
	}
	
	public void siftdown(Node<T> current) {
		if(current!=null) {
		if(current.right!=null&&current.left!=null) {
			if(current.left.priority<=current.right.priority) {
				if(current.priority>=current.left.priority) {
				int pr=current.priority;
				T info=current.data;
				
				current.priority=current.left.priority;
				current.data=current.left.data;
				
				current.left.priority=pr;
				current.left.data=info;
//				System.out.println("current.left info is: "+current.left.data+
//						"coming from"+current.data);
				siftdown(current.left);
				}
			}
			else {
				if(current.priority>=current.right.priority) {
				int pr=current.priority;
				T info=current.data;
				
				current.priority=current.right.priority;
				current.data=current.right.data;
				
				current.right.priority=pr;
				current.right.data=info;
//				System.out.println("current.right info is: "+current.right.data+
//						"coming from"+current.data);
				siftdown(current.right);
				}
			}
		}else if(current.right==null&&current.left!=null) {
			
				if(current.priority>=current.left.priority) {
				int pr=current.priority;
				T info=current.data;
				
				current.priority=current.left.priority;
				current.data=current.left.data;
				
				current.left.priority=pr;
				current.left.data=info;
//				//System.out.println("end of recurrent current.left info is: "+current.left.data+
//						"coming from"+current.data);
				}
			}
		
		}
	}
	
	/* The book complicates things unnecessarily here, because it also assumes the
	 * BinaryTree class which has a Node type of its own, declared protected and extended
	 * by the node type of this class. We ditchthe idea of a non-search BinaryTree altogether 
	 * because we find it unrealistic in practice (it does not allow for a well-defined way to 
	 * insert or delete elements,rather it depends on combining "singleton" trees).
	 * 
	 * All methods that require some sort of depth-getFirst traversal will be implemented as methods
	 * of the Node<T> class.
	 */
	public class Node<Type>{
		public Type data;
		public Node<Type> left, right;
		public Node<Type> prev;
		public int priority;
		
		public Node(int priority,Type element){
			data = element;
			left = right = null;
			prev=null;
			this.priority=priority;
		}

		

		public void siftup() {
			//System.out.println("the 0 check");
			if(prev!=null) {
				//System.out.println("the first check");
				if(prev.priority>priority) {
					//System.out.println("the second check");
					int prev1=prev.priority;
					Type prev2=prev.data;
					prev.priority=priority;
					prev.data=data;
					priority=prev1;
					data=prev2;
					prev.siftup();
					
				}
				
			}
			return;
			
		}


		
		public Node<Type> getLast(String binaryString ) {
			if(binaryString.length()==2) {
				if(binaryString.charAt(1)=='0') {
					//Type temp=left.data;
					//left=null;
					//System.out.println("going  taking left"+data);
					return left;
				}
				else {
					//Type temp=right.data;
					//right=null;
					//System.out.println("going  taking right");
					return right;
				}
			}
			else {
				if(binaryString.charAt(1)=='0') {
					//System.out.println("going  rec left");
					return left.getLast(binaryString.substring(1, binaryString.length()));
				}
				else {
					//System.out.println("going  rec right");
					return right.getLast(binaryString.substring(1, binaryString.length()));
				}
			}
		}



		public Node<Type> removeLast(String binaryString) {
			//System.out.println("removelast binaryString is: "+ binaryString);
			if(binaryString.length()==1) {
				//System.out.println("we hit here");
				return null;
			}
			if(binaryString.length()==2) {
				//System.out.println("we hit here1");
				if(binaryString.charAt(1)=='0') {
					//System.out.println("we hit here1.5");
					Node<Type> temp=left;
					left=null;
					return temp;
				}
				else {
					//System.out.println("we hit here2");
					Node<Type> temp=right;
					right=null;
					return temp;
				}
			}
			else {
				if(binaryString.charAt(1)=='0') {
					return left.removeLast(binaryString.substring(1, binaryString.length()));
				}
				else {
					return right.removeLast(binaryString.substring(1, binaryString.length()));
				}
			}
			
			// TODO Auto-generated method stub
			
		}



		public Node<Type> getLeft(){
			return left;
		}
		
		public int getPriority(){
			return priority;
		}
		public Node<Type> getRight(){
			return right;
		}
		
		public Node<Type> getPrev(){
			return prev;
		}
		
		public void setPriority(int pro){
			this.priority = pro;
		}
		
		public void setLeft(Node<Type> left){
			this.left = left;
		}
		
		public void setPrev(Node<Type> root) {
			this.prev=root;
		}
		
		public void setRight(Node<Type> right){
			this.right = right;
		}

		public Type getElement(){
			return data;
		}

		public int getCount(){
			int count = 1;
			if(left != null)
				count += left.getCount();
			if(right != null)
				count += right.getCount();
			return count;
		}
		private char position;
		public void setPosition(char position) {
			this.position=position;
		}
		public char getPosition() {
			return position;
		}
		public Type find(int index){
			// Preorder traversal makes most sense when wanting to find an element.
//			Type retVal = null;
//			if(data.compareTo(element) == 0)
//				retVal = data;
//			else // Check recursively over elements. Could be null, eventually.
//				if(element.compareTo(data) < 0 && left != null)
//					retVal=left.find(element);
//				else if(element.compareTo(data) > 0 && right != null)
//					retVal=right.find(element);
//			return retVal;
			throw UNIMPL_METHOD;
		}
		
		/* Binary search trees allow us to insert nodes at specific places. The
		 * new node inserted will always be a leaf node.*/
		public void add(int pro,Type element, String size,Node<Type> root){
			Node<Type> temp= new Node<Type>(pro,element);
			
		
				addAlx(temp,size.substring(1, size.length()),root);
				
		
			
		}
		
		public void addAlx(Node<Type> value,String info,Node<Type> current) {
			if(info.length()==1) {
				if(info.charAt(0)=='0') {
					//System.out.println("i will add left:"+ info);
					setLeft(value);
					value.prev=current;
				}
				else {
					//System.out.println("i will add right:"+ info);
					setRight(value);
					value.prev=current;
				}
			}else {
				if(info.charAt(0)=='0') {
					//System.out.println("i will go to left:"+ info);
					left.addAlx(value,info.substring(1, info.length()),current.left);
				}
				else {
					//System.out.println("i will go to right:"+ info);
					right.addAlx(value,info.substring(1, info.length()),current.right);
				}
			}
		}
		
		

		/* The following method removes a specific node from the tree
		 * and returns the node that replaces it. It is recursive in nature.
		 */
		public Node<Type> remove(Type element){
//			Node<Type> retVal = this;
//			if(element.compareTo(data) == 0){// Current node should be removed
//
//				// Case #1: Node is a leaf: return null.
//				if(left == null && right == null)
//					retVal = null;
//
//				// Case #2: Node is a pre-leaf with left child null: return the right child.
//				else if(left == null && right != null)
//					retVal = right;
//
//				// Case #3: Node is a pre-leaf with right child null: return the left child.
//				else if(left != null && right == null)
//					retVal = left;
//
//				// Case #4: Node is an inner node with rooted subtrees. Find its inorder successor
//				//		and replace the node with it. Set the left child to whatever was left before
//				//		and the right child to whatever was right before.
//				else {
//					retVal = getInorderSuccessor(); // Guaranteed to exist, because "this" is an inner node.
//					retVal.left = left;
//					retVal.right = right;
//				}
//			} else if(element.compareTo(data) < 0){// Element on the left sub-tree
//				if(left != null) // if left is null, then the element is simply not in the tree.
//					left = left.remove(element);
//			} else{ // Element on the right sub-tree
//				if(right != null) // same
//					right = right.remove(element);
//			}
//			return retVal;
			throw UNIMPL_METHOD;
		}

		// The inorder successor of an inner node is the leftmost node of its right subtree.
		private Node<Type> getInorderSuccessor(){
			Node<Type> current = right;
			while(current.left != null)
				current = current.left;
			right.remove(current.getElement()); // The successor is a leaf node, so it boils down to a previous case.
			return current;
			
		}

		/* Preorder, inorder and postorder traversal methods. */

		public void preorderTraversal(ArrayList<Type> elements){
			elements.add(data);
			if(left != null)
				left.preorderTraversal(elements);
			if(right != null)
				right.preorderTraversal(elements);
		}

		public void inorderTraversal(ArrayList<Type> elements){
			if(left != null)
				left.inorderTraversal(elements);
			elements.add(data);
			if(right != null)
				right.inorderTraversal(elements);
		}

		public void postorderTraversal(ArrayList<Type> elements){
			if(left != null)
				left.postorderTraversal(elements);
			if(right != null)
				right.postorderTraversal(elements);
			elements.add(data);
		}
		
		public int height(){
			int leftHeight = 1, rightHeight = 1;
			if(left != null)
				leftHeight = left.height() + 1;
			if(right != null)
				rightHeight = right.height() + 1;
			// return the maximum of heights
			return (leftHeight > rightHeight) ? leftHeight : rightHeight;
		}
	}
}

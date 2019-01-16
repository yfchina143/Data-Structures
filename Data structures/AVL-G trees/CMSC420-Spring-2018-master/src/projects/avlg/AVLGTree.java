/* DO NOT ERASE THESE THREE LINES OR YOUR CODE WON'T COMPILE! */
package projects.avlg;
import projects.avlg.exceptions.EmptyTreeException;
import projects.avlg.exceptions.InvalidBalanceException;


/** <p>An <tt>AVL-G Tree</tt> is an AVL Tree with a relaxed balance condition. Its constructor receives a strictly
 * positive parameter which controls the <b>maximum</b> imbalance allowed on any subtree of the tree which
 * it creates. So, for example:</p>
 *  <ul>
 *      <li>An AVL-1 tree is a classic AVL tree, which only allows for perfectly balanced binary
 *      subtrees (imbalance of 0 everywhere), or subtrees with a maximum imbalance of 1 (somewhere). </li>
 *      <li>An AVL-2 tree relaxes the criteria of AVL-1 trees, by also allowing for subtrees
 *      that have an imbalance of 2.</li>
 *      <li>AVL-3 trees allow an imbalance of 3.</li>
 *      <li>...</li>
 *  </ul>
 *
 *  <p>The idea behind AVL-G trees is that rotations cost time, so maybe we would be willing to
 *  accept bad search performance now and then if it would mean less rotations.</p>
 *
 * <p>You <b>must</b> implement this class! You should <b>not</b> move this class' file to
 * a different position in the code tree. You <b>are</b> allowed to add any classes, enums, interfaces,
 * abstract classes, packages, etc. that you think you might need.</p>
 *
 * @author YOUR NAME HERE!
 */
public class AVLGTree<T extends Comparable<T>> {

    private static final RuntimeException UNIMPL_METHOD =
            new RuntimeException("Implement this method!");

    /* *************************************************************************
     ************** PLACE YOUR PRIVATE METHODS AND FIELDS HERE: ****************
     ***************************************************************************/
    public node<T> root;
    private int allowedHeight;
    private T deletedKey;


    /* *********************************************************************
     ************************* PUBLIC (INTERFACE) METHODS *******************
     **********************************************************************/
    
    protected class node<T> {

    	protected T t;
    	protected int height;
    	protected node<T> left;
    	protected node<T> right;

        private node(T t) {
            this.t = t;
            height = 1;
            left=null;
            right=null;
        }
    }
    /**
     * The class constructor provides the tree with its maximum imbalance allowed.
     * @param maxImbalance The maximum imbalance allowed by the AVL-G Tree.
     * @throws InvalidBalanceException if <tt>maxImbalance</tt> is a value smaller than 1.
     */
    public AVLGTree(int maxImbalance) throws InvalidBalanceException {
        if(maxImbalance<=0) {
        	throw new InvalidBalanceException("height "+maxImbalance+" is wrong");
        }
        else {
        	allowedHeight=maxImbalance+1;
        }
    }

    /**
     * Insert <tt>key</tt> in the tree.
     * @param key The key to insert in the tree.
     */
    public void insert(T key) {
    	//System.out.println("----------------------we are doing key:" +key );
        root=insert(key,root);
       //System.out.println("we are doing key:" +key+" getting out --------------" );
    }
    
    private node<T> insert(T key, node<T> curr){
    
    	if(curr==null) {
    		//System.out.println("inserted "+key);
    		return new node<T>(key);
    	}
    	else {
    		if(key.compareTo(curr.t)<0) {
    			//left case
	
    			curr.left=insert(key,curr.left);

    			if(getHeight(curr.left)-getHeight(curr.right)>=allowedHeight) {
    			
    				if(key.compareTo(curr.left.t)<0) {
    					//System.out.println("on  left case: R"+ curr.t);
    					return rightRotate(curr);
    					}
    				else {
    					//System.out.println("on  left case: LR , curr left is: "+ curr.left.t+" curr is: "+curr.t);
    					curr.left=leftRotate(curr.left);
    					return rightRotate(curr);
    				}
    			}
    			//System.out.println("getting out of "+ curr.t+" we just added"+ curr.left.t);
    			//System.out.println("no rotation was made");		
    			return curr;
    		}
    		else {
    			//right case
    			//System.out.println("on right case of "+curr.t+" L-R "+(getHeight(curr.left)-getHeight(curr.right)));
    			
    			curr.right=insert(key,curr.right);
    			
    			int temph=-1*allowedHeight;
    			
    			//System.out.println("on right case of "+curr.t+" after insert L-R "+(getHeight(curr.left)-getHeight(curr.right)));
    			
    			if(getHeight(curr.left)-getHeight(curr.right)<=temph) {
    				//System.out.println("curr t is: "+curr.t+" curr.right.t is: " +curr.right.t);
    				if(key.compareTo(curr.right.t)<0) {
    					//System.out.println("on  right case: RL "+ curr.t);
    					curr.right=rightRotate(curr.right);
    					return leftRotate(curr);
    					
    				}
    				else {
    					//System.out.println("on  right case: L "+ curr.right.t+" curr is: "+curr.t);
    					
    					return leftRotate(curr);
    				}
    			}
    			
    		//System.out.println("no rotation was made");		
    		return curr;
    		}
    	}
    }
    
    private node<T> leftRotate(node<T> curr){
    	node<T> temp=curr.right;
    	//System.out.println("this is left: "+temp.left.t);
    	curr.right=temp.left;
    	temp.left=curr;
    	temp.height=getHeight(temp);
    	curr.height=getHeight(curr);
    	return temp;
    }
    
    private node<T> rightRotate(node<T> curr){
    	node<T> temp=curr.left;
    	//System.out.println("this is left: "+temp.left.t);
    	curr.left=temp.right;
    	temp.right=curr;
    	temp.height=getHeight(temp);
    	curr.height=getHeight(curr);
    	return temp;
    }
    
    /**
     * Delete the key from the data structure and return it to the caller.
     * @param key The key to delete from the structure.
     * @return The key that was removed, or <tt>null</tt> if the key was not found.
     * @throws EmptyTreeException if the tree is empty.
     */
	public T delete(T key) throws EmptyTreeException {
		if (isEmpty()) {
			throw new EmptyTreeException("tree is empty at deletion");
		} else {
			//System.out.println("we got here-2");
			if (search(key, root) != null) {
				//System.out.println("we got here-1");
				// System.out.println("-0------"+search(key,root));
				root = delete(key, root);
				// System.out.println("-0------"+search(key,root));
				//System.out.println("we got here1");
				if (isAVLGBalanced(root) && isBST() && search(key, root) == null) {
					//System.out.println("we got here2");
					return key;
				} else {
					//System.out.println("we got here3");
					return null;
					}
			} else {
				return null;
			}
		}
	}
    
    private node<T> delete(T key,node<T> curr){
    	if(curr==null) {
    		
    		return null;
    	}
    	
    	//basic bst delete
    	if(key.compareTo(curr.t)>0){
    		//go right
    		curr.right=delete(key,curr.right);
    	}
    	else if(key.compareTo(curr.t)<0){
    		//go left
    		curr.left=delete(key,curr.left);
    	}
    	else {
    		
    		if(curr.left!=null&&curr.right!=null) {
    			//System.out.println("we doing left and right on: "+curr.t);
    			T temp=MostLeft(curr.right);
    			//System.out.println("temp is: "+temp);
    			curr.t=temp;
    			
    			curr.right=delete(temp,curr.right);
    			
			} else if (curr.left != null) {

				//curr = curr.left;
				//System.out.println("we doing left on: "+curr.t);
				T temp = MostRight(curr.left);
				//System.out.println("temp is: "+temp);
				curr.t = temp;

				curr.left = delete(temp, curr.left);

			} else if (curr.right != null) {
				//System.out.println("we doing right on: "+curr.t);
				T temp = MostLeft(curr.right);
				//System.out.println("temp is: "+temp);
				curr.t = temp;

				curr.right = delete(temp, curr.right);

			} else {
				//System.out.println("we hit null");
    			curr=null;
    		}
    	}
    	
    	if(curr==null) {
    		//System.out.println("we did null");
    		return null;
    	}
    	
    	curr.height=getHeight(curr);
    	
    	if(getHeight(curr.left)-getHeight(curr.right)>=allowedHeight) {
    	//left case	
    		if(getHeight(curr.left.left)-getHeight(curr.left.right)<0) {
    			curr.left=leftRotate(curr.left);
    			return rightRotate(curr);
    		}
    		else {
    			return rightRotate(curr);
    		}
    	}
    	else if(getHeight(curr.left)-getHeight(curr.right)<=(allowedHeight*-1)) {
    	//right case
    		
    		if(getHeight(curr.right.left)-getHeight(curr.right.right)>0) {
    			curr.right=rightRotate(curr.right);
    			return leftRotate(curr);
    		}
    		else {
    			return leftRotate(curr);
    		}
    		
    			
    	}
    	else {
    		//good condition
    		return curr;
    	}
    	
   
    }
    
    private T MostRight(node<T> curr) {
    	if(curr.right!=null) {
    		return MostRight(curr.right);
    	}
    	else {
    		return curr.t;
    	}

    } 
    
    private T MostLeft(node<T> curr) {
    	if(curr.left!=null) {
    		return MostLeft(curr.left);
    	}
    	else {
    		return curr.t;
    	}

    } 
    
    /**
     * <p>Search for <tt>key</tt> in the tree. Return a reference to it if it's in there,
     * or <tt>null</tt> otherwise.</p>
     * @param key The key to search for.
     * @return <tt>key</tt> if <tt>key</tt> is in the tree, or <tt>null</tt> otherwise.
     * @throws EmptyTreeException if the tree is empty.
     */
    public T search(T key) throws EmptyTreeException {
       if(isEmpty()) {
    	   throw new EmptyTreeException(" search tree is empty");
       }
       else {
    	   return search(key,root);
       }
    }
    
    private T search(T key, node<T> curr) {
    	if(curr==null) {
    		return null;
    	}
    	else {
    		if(key.compareTo(curr.t)>0) {
    			return search(key,curr.right);
    		}
    		else if(key.compareTo(curr.t)<0){
    			return search(key,curr.left);
    		}
    		else {
    			return curr.t;
    		}
    	}
    }

    /**
     * Retrieves the maximum imbalance parameter.
     * @return The maximum imbalance parameter provided as a constructor parameter.
     */
    public int getMaxImbalance(){
        return allowedHeight-1;
    }


    /**
     * <p>Return the height of the tree. The height of the tree is defined as the length of the
     * longest path between the root and the leaf level. By definition of path length, a
     * stub tree has a height of 0, and we define an empty tree to have a height of -1.</p>
     * @return The height of the tree.
     */
    public int getHeight() {
           if(isEmpty()) {
        	   return -1;
           }
           else {
        	   return getHeight(root)-1;
           }
    }

    public int getHeight(AVLGTree<T>.node<T> curr) {

		if(curr==null) {
			return 0;
		}
		
		int leftHeight=0;
		int rightHeight=0;
		
		if(curr.left!=null) {
			leftHeight=getHeight(curr.left);
		}
		if(curr.right!=null) {
			rightHeight=getHeight(curr.right);
		}
		
		if(leftHeight>rightHeight) {
			return leftHeight+1;
		}
		else {
			return rightHeight+1;
		}
		
	}

	/**
     * Query the tree for emptiness. A tree is empty iff it has zero keys stored.
     * @return <tt>true</tt> if the tree is empty, <tt>false</tt> otherwise.
     */
    public boolean isEmpty() {
       if(root==null) {
    	   return true;
       }
       else {
    	   return false;
       }
    }

    /**
     * <p>Return the key at the tree's root node if it exists, or throws an
     * <tt>EmptyTreeException</tt> otherwise.</p>
     * @throws EmptyTreeException if the tree is empty.
     * @return The key at the tree's root node.
     */
    public T getRoot() throws EmptyTreeException{
       if(root==null) {
    	   throw new EmptyTreeException("tree is empty");
       }
       else {
    	   return root.t;
       }
    }


    /**
     * <p>Establishes whether the AVL-G tree <em>globally</em> satisfies the BST condition.
     * This method is <b>terrifically useful for testing!</b></p>
     * @return <tt>true</tt> if the tree satisfies the Binary Search Tree property,
     * <tt>false</tt> otherwise.
     */
    public boolean isBST() {
        if(root==null) {
        	return true;
        }
        else if(getCount()==1) {
        	return true;
        }
        else {
        	return isBST(root);
        }
    }
    
    public boolean isBST(node<T> curr) {
    	if(curr.left!=null&&curr.right!=null) {
    		if(curr.left.t.compareTo(curr.t)>0||curr.right.t.compareTo(curr.t)<0) {
    			return false;
    		}
    		return isBST(curr.left)&&isBST(curr.right);
    	}
    	else if(curr.left!=null) {
    		if(curr.left.t.compareTo(curr.t)>0) {
    			return false;
    		}
    		return isBST(curr.left);
    	}
    	else if(curr.right!=null){
    		if(curr.right.t.compareTo(curr.t)<0) {
    			return false;
    		}
    		return isBST(curr.right);
    	}
    	else {
    		return true;
    	}
    	
    }
    
    
    /**
     * <p>Establishes whether the AVL-G tree <em>globally</em> satisfies the AVL-G condition.
     * This method is <b>terrifically useful for testing!</b></p>
     * @return <tt>true</tt> if the tree satisfies the Binary Search Tree property,
     * <tt>false</tt> otherwise.
     */
    public boolean isAVLGBalanced() {
        if(root==null) {
        	return true;
        }
        else {
        	return isAVLGBalanced(root)&&isBST();
        }
    }
    
    public boolean isAVLGBalanced(node<T> curr) {
    	if(curr==null) {
    		return true;
    	}
    	if(Math.abs(getHeight(curr.left)-getHeight(curr.right))<=allowedHeight-1&&isAVLGBalanced(curr.left)&&isAVLGBalanced(curr.right)) {
    		return true;
    	}
    	return false;
    }

    void printLevelOrder()
    {
        int h = getHeight(root);
        int i;
        for (i=1; i<=h; i++) {
            System.out.println("level:"+i);
        	printGivenLevel(root, i);
        	 System.out.println("");
        }
    }
    
    void printGivenLevel (node<T> root ,int level)
    {
        if (root == null)
            return;
        if (level == 1)
            System.out.print(root.t + " ");
        else if (level > 1)
        {
            printGivenLevel(root.left, level-1);
            printGivenLevel(root.right, level-1);
        }
    }


    /**
     * <p>Empties the <tt>AVLGTree</tt> of all its elements. After a call to this method, the
     * tree should have <b>0</b> elements.</p>
     */
    public void clear(){
        root=null;
    }

    int count;
    /**
     * <p>Return the number of elements in the tree.</p>
     * @return  The number of elements in the tree.
     */
    public int getCount(){
    	count=0;
        if(root==null) {
        	count=0;
        	return count;
        }
        else {
        	counting(root);
        	return count;
        }
    }
    
    public void counting(node<T> curr) {
    	if(curr!=null) {
    		counting(curr.left);
    		counting(curr.right);
    		count+=1;
    	}
    	
    	
    }
}

package projects.bpt;
import java.util.Iterator;
import java.util.*;
public class BinaryPatriciaTrie implements Iterable<String> {

    private static RuntimeException UNIMPL_METHOD = new RuntimeException("Implement this method!");

    /* *************************************************************************
     ************** PLACE YOUR PRIVATE METHODS AND FIELDS HERE: ****************
     ***************************************************************************/
    Node root;
    int size=0;

    /**
     * Simple constructor that will initialize the internals of <tt>this</tt>.
     */
    BinaryPatriciaTrie() {

        root = new Node();
    }
    /* *********************************************************************
     ************************* PUBLIC (INTERFACE) METHODS *******************
     **********************************************************************/

    /**
     * Searches the trie for a given <tt>key</tt>.
     *
     * @param key The input String key.
     * @return true if and only if key is in the trie, false otherwise.
     */
    public boolean search(String key) {
        boolean debug=false;

        Node curr = root;

        String currS = key;
        if (key == ""||key==null) {
            return false;
        }

        if (key.charAt(0) == '1') {
            curr = curr.right;
            if(debug) {
                System.out.println("going right");
            }
        } else {
            if(debug) {
                System.out.println("going left");
            }
            curr = curr.left;
        }
        while (currS.isEmpty() == false) {
            if (curr == null) {
                if(debug) {
                    System.out.println("curr = null");
                }
                return false;
            }
            if (curr.length > currS.length()) {
                if(debug) {
                    System.out.println("case 0 ");
                }
                return false;
            }
            else if(curr.length < currS.length()){

                int index = findSimilarIndex(currS, curr.keyRef);

                if(debug) {
                    System.out.println("case 1");
                    System.out.println("CurrS:"+currS+ " keyRef:"+curr.keyRef+" index:"+index);
                    System.out.println("substring: "+currS.substring(0,index));
                }
                if(currS.substring(0,index).equals(curr.keyRef)) {
                    if(debug) {
                        System.out.println("case 1.1");
                    }
                    if (currS.charAt(index) == '1') {
                        if (debug) {
                            System.out.println("going right\n");
                        }
                        currS = currS.substring(index);
                        curr = curr.right;
                    } else {
                        if (debug) {
                            System.out.println("going left\n");
                        }
                        currS = currS.substring(index);
                        curr = curr.left;
                    }
                }
                else{
                    if(debug) {
                        System.out.println("1.2");
                    }
                    return false;
                }
            }
            else {
                if(debug) {
                    System.out.println("case 2");
                }
                if (curr.length == currS.length()) {
                    if(debug) {
                        System.out.println("case 2.1");
                    }
                    if (curr.isKey == true) {

                        if (currS.equals(curr.keyRef)) {
                            if(debug) {
                                System.out.println("case 2.1.1");
                            }
                            return true;
                        } else {
                            if(debug) {
                                System.out.println("case 2.1.2");
                            }
                            return false;
                        }
                    } else {
                        return false;
                    }

                } else {
                    if(debug) {
                        System.out.println("case 2.2");
                    }
                  return false;
                }
            }
        }

        return false;
    }

    /**
     * return matching index location +1. key always have to be bigger than node;
     *
     * @param key
     * @param Node
     * @return
     */
    public int findSimilarIndex(String key, String Node) {
        int index = -1;
        for (int i = 0; i < Node.length(); i++) {
            //System.out.println("we hit here");
            if (key.charAt(i) != Node.charAt(i)) {
                break;
            }
            index++;
        }
        return index + 1;
    }

    /**
     * Inserts <tt>key</tt> into the trie.
     *
     * @param key The input String key.
     * @return true if and only if the key was not already in the trie, false otherwise.
     */
    public boolean insert(String key) {
        if (key == null || key.trim().equals("")) {
            return false;
        }

        if(search(key)){
           // System.out.println(key+" exist");
            return false;
        }

        if (key.charAt(0) == '1') {
            if (root.right == null) {
                root.right = new Node(key, true, key.length());
                size++;
            } else {
                root.right = insert(root, root.right, key);
                size++;
            }
        } else {
            if (root.left == null) {
                size++;
                root.left = new Node(key, true, key.length());
            } else {
                root.left = insert(root, root.left, key);
                size++;
            }
        }
        return true;


    }

    /**
     * recessive insert method
     *
     * @param curr
     * @param key
     * @return
     */
    public Node insert(Node prev, Node curr, String key) {
        boolean debug=false;
        if (curr == null) {
            return new Node(key, true, key.length());
        } else if (curr.keyRef.equals(key)) {
            curr.isKey = true;
            return curr;
        } else if (key.length() > curr.length) {

            if(debug) {
                System.out.println("case 0 key:"+key+" currRef:"+curr.keyRef);
            }
            int index = findSimilarIndex(key, curr.keyRef);
            if (index == curr.length) {
                if(debug) {
                    System.out.println("case 0.1");
                }
                if (key.charAt(index) == '1') {
                    if(debug) {
                        System.out.println("case 0.1.1 -" + key.substring(index));
                    }
                    curr.right = insert(curr, curr.right, key.substring(index));
                    return curr;
                } else {
                    if(debug) {
                        System.out.println("case 0.1.2 -" + key.substring(index));
                    }
                    curr.left = insert(curr, curr.left, key.substring(index));
                    return curr;
                }
            } else {

                String common=curr.keyRef.substring(0,index);
                int length=curr.length-index;
                if(debug) {
                      System.out.println("case 0.2 common:"+common+" new:"+curr.keyRef.substring(index)+" length:"+length+" keyat:"+key.charAt(index));
                }
                Node master=new Node(common,false,index);
                curr.keyRef=curr.keyRef.substring(index);
                curr.length=length;
                if(key.charAt(index)=='1'){
                    if(debug) {
                            System.out.println("case 0.2.1");
                    }
                    master.left=curr;
                    master.right=insert(master,null,key.substring(index));

                    return master;
                }
                else{
                    if(debug) {
                         System.out.println("case 0.2.2 substring:"+key.substring(index));
                    }
                    master.right=curr;
                    master.left=insert(master,null,key.substring(index));

                    return master;
                }
            }
        } else if (key.length() <= curr.length) {
            if(debug) {
                 System.out.println("case 1");
            }
            int index = findSimilarIndex(curr.keyRef, key);
            if(debug) {
                System.out.println("check1 index:"+ index);
            }
            if (key.equals(curr.keyRef.substring(0, key.length()))) {
                if(debug) {
                        System.out.println("case 1.1");

                }
                String common = curr.keyRef.substring(0, index);
                String difference1 = curr.keyRef.substring(index);
                Node master = new Node(common, true, common.length());
                if (difference1.charAt(0) == '1') {
                    if(debug) {

                     System.out.println("case 1.1.1");
                    }
                    curr.keyRef = difference1;
                    curr.length=curr.length-index;
                    master.right = curr;

                } else {
                    if(debug) {

                        System.out.println("case 1.1.2");
                    }
                    curr.keyRef = difference1;
                    curr.length=curr.length-index;
                    master.left = curr;
                }
                return master;
            } else {
                if(debug) {
                      System.out.println("case 1.2");

                }
                if (index == -1) {
                    if(debug) {
                         System.out.println("case 1.22");
                        //root case
                    }
              //      System.out.println("case 1.2.1");
                    if (key.charAt(0) == '1') {
                        if(debug) {
                            System.out.println("case 1.2.1");
                        }
                        Node temp = new Node();
                        temp.right = insert(curr, curr.right, key);
                        temp.left = temp;
                        return temp;
                    } else {
                        if(debug) {
                            System.out.println("case 1.2.2");
                        }
                        Node temp = new Node();
                        temp.left = insert(curr, curr.left, key);
                        temp.right = temp;
                        return temp;
                    }
                } else {
                    if(debug) {
                           System.out.println("case 2.2");
                    }
                    String common = curr.keyRef.substring(0, index);
                    if(debug) {
                        System.out.println("coomon:" + common);
                    }
                    String commonDifference = curr.keyRef.substring(index);
                    if(debug) {
                        System.out.println("CD:" + commonDifference);
                    }
                    String keyDifference = key.substring(index);
                    if(debug) {
                        System.out.println("KD:" + keyDifference);
                    }
                    if (keyDifference.charAt(0) == '1') {
                        if(debug) {
                            System.out.println("2.2.1");
                        }
                        Node master = new Node(common, false, common.length());
                        curr.keyRef = commonDifference;
                        curr.length = commonDifference.length();
                        master.left = curr;
                        master.right = new Node(keyDifference, true, keyDifference.length());
                        return master;
                    } else {
                        if(debug) {
                            System.out.println("2.2.2");
                        }
                        Node master = new Node(common, false, common.length());
                        curr.keyRef = commonDifference;
                        curr.length = commonDifference.length();
                        master.right = curr;
                        master.left = new Node(keyDifference, true, keyDifference.length());
                        return master;
                    }

                }

            }
        } else {
           // System.out.println("case 3");
            return null;
        }

    }



//    public Node delete(Node prev,Node curr, String key) {
//        boolean debug=false;
//        if(curr==null){
//            return null;
//        }
//        else if(curr.length == key.length()){
//            if(debug) {
//                  System.out.println("case 1");
//            }
//            int index = findSimilarIndex(key, curr.keyRef);
//            if (curr.isKey == true) {
//                if(debug) {
//                    System.out.println("case 1.1");
//                }
//                if (curr.keyRef.equals(key)) {
//                    if(debug) {
//                        System.out.println("case 1.1.1");
//                    }
//                    if (curr.left != null && curr.right != null) {
//                        curr.isKey = false;
//                        return curr;
//                    } else if (curr.left == null && curr.right == null) {
//                        return null;
//                    } else if (curr.right == null) {
//                        String common = curr.keyRef;
//                        int cl = curr.length;
//                        curr = curr.left;
//                        curr.length += cl;
//                        curr.keyRef = common + curr.keyRef;
//                        return curr;
//                    } else {
//                        String common = curr.keyRef;
//                        int cl = curr.length;
//                        curr = curr.right;
//                        curr.length += cl;
//                        curr.keyRef = common + curr.keyRef;
//                        return curr;
//                    }
//                } else {
//                    if(debug) {
//                        System.out.println("case 1.1.2");
//                    }
//                    if (index == -1) {
//                        return curr;
//                    } else {
//                        char dir = key.charAt(index);
//                        if (dir == '1') {
//                            Node master=prev;
//                            master.right = delete(curr,curr.right, key.substring(index));
//                            if(master.right==null){
//                                master.length+=master.left.length;
//                                master.keyRef=master.keyRef+master.left.keyRef;
//                                master.left=null;
//                            }
//
//                            return master;
//                        } else {
//                            Node master=prev;
//                            master.left = delete(curr,curr.left, key.substring(index));
//                            if(master.right==null){
//                                master.length+=master.right.length;
//                                master.keyRef=master.keyRef+master.right.keyRef;
//                                master.right=null;
//                            }
//
//                            return master;
//                        }
//
//                    }
//
//                }
//            } else {
//                if(debug) {
//                    System.out.println("case 1.2");
//                }
//                return curr;
//            }
//        }
//        else if(curr.length < key.length()){
//            if(debug) {
//                System.out.println("case 3");
//            }
//            int index = findSimilarIndex(key, curr.keyRef);
//            if (index == -1) {
//                if(debug) {
//                    System.out.println("case 3.1");
//                }
//                return curr;
//            } else {
//                if(debug) {
//                    System.out.println("case 3.2");
//                }
//                if (key.charAt(index) == '1') {
//                    if(debug) {
//                        System.out.println("case 3.2.1");
//                    }
//                    Node master=prev;
//                    master.right = delete(curr,curr.right, key.substring(index));
//                    if(master.right==null){
//                        master.length+=master.left.length;
//                        master.keyRef=master.keyRef+master.left.keyRef;
//                        master.left=null;
//                    }
//                    return master;
//                } else {
//                    if(debug) {
//                        System.out.println("case 3.2.2 - key:"+key+" length:"+curr.length);
//                    }
//                    Node master=prev;
//                    master.left = delete(curr,curr.left, key.substring(index));
//                    if(master.left==null){
//                        master.length+=master.right.length;
//                        master.keyRef=master.keyRef+master.right.keyRef;
//                        master.right=null;
//                    }
//                    return master;
//                }
//            }
//        }
//        else{
//            if(debug) {
//                System.out.println("case 2");
//            }
//            return curr;
//        }
//
//    }

    /**
     * Deletes <tt>key</tt> from the trie.
     *
     * @param key The String key to be deleted.
     * @return True if and only if key was contained by the trie before we attempted deletion, false otherwise.
     */
    public boolean delete(String key) {
        boolean debug=false;
        if(debug) {
            System.out.println("--delete method---");
            System.out.println("-search start-\n");
        }

        boolean contain=search(key);
        if(debug) {
            System.out.println("\n-search over-\n");
        }
        if(contain==false){
            System.out.println("key don't exist");
            return false;
        }
        else{
            if(debug) {
                System.out.println("we are in else");
            }
            if (key.charAt(0) == '1') {
                //System.out.println("going right");
                root.right = delete(root,root.right, key);
                size--;
                return true;
            } else {
               // System.out.println("going left");
                root.left = delete(root,root.left, key);
                size--;
                return true;
            }

        }
    }

    public Node delete(Node prev,Node curr, String key) {
        boolean debug=false;
        if (curr == null) {
            if(debug) {
                System.out.println("case 0");
            }
            return null;
        } else if (curr.length == key.length()) {
            if(debug) {
                  System.out.println("case 1");
            }
            int index = findSimilarIndex(key, curr.keyRef);
            if (curr.isKey == true) {
                if(debug) {
                    System.out.println("case 1.1");
                }
                if (curr.keyRef.equals(key)) {
                    if(debug) {
                        System.out.println("case 1.1.1");
                    }
                    if (curr.left != null && curr.right != null) {
                        if(debug) {
                            System.out.println("case 1.1.2");
                        }
                        curr.isKey = false;
                        return curr;
                    } else if (curr.left == null && curr.right == null) {
                        if(debug) {
                            System.out.println("case 1.1.3");
                        }
                        return null;
                    } else if (curr.right == null) {

                        String common = curr.keyRef;
                        int cl = curr.length;
                        curr = curr.left;
                        curr.length += cl;
                        curr.keyRef = common + curr.keyRef;
                        curr.isKey=true;
                        if(debug) {
                            System.out.println("case 1.1.4");
                        }
                        return curr;
                    } else {
                        String common = curr.keyRef;
                        int cl = curr.length;
                        curr = curr.right;
                        curr.length += cl;
                        curr.keyRef = common + curr.keyRef;
                        curr.isKey=true;
                        if(debug) {
                            System.out.println("case 1.1.5");
                        }
                        return curr;
                    }
                } else {
//                    if(debug) {
//                        System.out.println("case 1.1.2");
//                    }
//                    if (index == -1) {
//                        return curr;
//                    } else {
//                        char dir = key.charAt(index);
//                        if (dir == '1') {
//                            Node master=curr;
//                            master = delete(curr,curr.right, key.substring(index));
//
//                            return master;
//                        } else {
//                            Node master=curr;
//                            master= delete(curr,curr.left, key.substring(index));
//
//                            return master;
//                        }
//
//                    }
                    //System.out.println("**!!!***we should never be here***!!!**");
                    return curr;

                }
            } else {
                if(debug) {
                    System.out.println("case 1.2");
                }
                return curr;
            }
        } else if (key.length() < curr.keyRef.length()) {
            if(debug) {
                System.out.println("case 2");
            }
            return curr;
        } else if (key.length() > curr.keyRef.length()) {
            if(debug) {
                System.out.println("case 3");
            }
            int index = findSimilarIndex(key, curr.keyRef);
            if (index == -1) {
                if(debug) {
                    System.out.println("case 3.1");
                }
                return curr;
            } else {
                if(debug) {
                    System.out.println("case 3.2");
                }
                if (key.charAt(index) == '1') {
                    if(debug) {
                        System.out.println("case 3.2.1");
                    }
                    Node master=curr;
                    Node temp=delete(curr,curr.right, key.substring(index));

                        if (temp == null) {
                            if(debug) {
                                System.out.println("temp = null");
                            }
                            if (master.isKey == false ) {
                                if(debug) {
                                    System.out.println("key is false");
                                }
                                master.length += master.left.length;
                                master.keyRef = master.keyRef + master.left.keyRef;
                                master.isKey=true;
                                Node left=master.left.left;
                                Node right=master.left.right;
                                master.right=right;
                                master.left=left;

                            }
                            else{
                                master.right=null;
                            }

                        }


                    return master;
                } else {
                    if(debug) {
                        System.out.println("case 3.2.2 - key:"+key+" length:"+curr.length);
                    }
                    Node master=curr;
                    Node temp= delete(curr,curr.left, key.substring(index));

                        if (temp == null) {
                            if(debug) {
                                System.out.println("temp = null ");
                            }
                            if (master.isKey == false) {
                                if(debug) {
                                    System.out.println("key is false");
                                }
                                master.length += master.right.length;
                                master.keyRef = master.keyRef + master.right.keyRef;
                                master.isKey=true;
                                Node left=master.right.left;
                                Node right=master.right.right;
                                master.right=right;
                                master.left=left;

                            }
                            else{
                                master.left=null;
                            }

                        }

                    return master;
                }
            }
        } else {

            if(debug) {
                System.out.println("case 4, never be here");
                System.out.println("case 4 - key:"+key+" curr: length:"+curr.keyRef +" "+curr.length);
            }
            return curr;
        }
    }

    /**
     * Queries the trie for emptiness.
     *
     * @return true if and only if {@link #getSize()} == 0, false otherwise.
     */
    public boolean isEmpty() {
        if(getSize()!=0){
            return false;
        }
        return true;
    }

    /**
     * Returns the number of keys in the tree.
     *
     * @return The number of keys in the tree.
     */
    public int getSize() {

        //return getSize(root);
        return size;
    }

    public int getSize(Node curr){
        if(curr==null){
            return 0;
        }
        int count=0;
        if(curr.isKey==true){
            count=1;
        }
        return getSize(curr.left)+count+getSize(curr.right);
    }

    /**
     * <p>Performs an <i>inorder (symmetric) traversal</i> of the Binary Patricia Trie. Remember from lecture that inorder
     * traversal in tries is NOT sorted traversal, unless all the stored keys have the same length. This
     * is of course not required by your implementation, so you should make sure that in your tests you
     * are not expecting this method to return keys in lexicographic order. We put this method in the
     * interface because it helps us test your submission thoroughly and it helps you debug your code! </p>
     *
     * <p>We <b>neither require nor test </b> whether the {@link Iterator} returned by this method is fail-safe or fail-fast.
     * This means that you  do <b>not</b> need to test for thrown {@link java.util.ConcurrentModificationException}s and we do
     * <b>not</b> test your code for the possible occurrence of concurrent modifications.</p>
     *
     * @return An {@link Iterator} over the {@link String} keys stored in the trie, exposing the elements in <i>symmetric
     * order</i>.
     */
    public Iterator<String> inorderTraversal() {
        return iterator();
    }

    public Iterator<String> iterator() {
        return new inOrderIterator(root);
    }

     class inOrderIterator implements Iterator<String>{

        ArrayDeque<String> qe;
        Node curr;
        public inOrderIterator(Node curr){
            qe=new ArrayDeque<String>();
            this.curr=curr;
            populateStack(curr,"");
        }

        public void populateStack(Node curr,String prev){
            if(curr==null){
                return;
            }
            populateStack(curr.left,prev+curr.keyRef);

            if(curr.isKey){
                if(curr.keyRef==null){
                    //System.out.println("pushed: "+prev+"");
                    qe.add(prev+"");
                }
                else {
                    //System.out.println("pushed: "+prev + curr.keyRef);
                    qe.add(prev + curr.keyRef);

                }
            }
            populateStack(curr.right,prev+curr.keyRef);

        }
        @Override
        public boolean hasNext() {
            return !qe.isEmpty();
        }

        @Override
        public String next() {
            String temp=qe.poll();
            if(temp.contains("null")){
                temp=temp.substring(4);
            }
            return temp;
        }
    }


    /**
     * Finds the longest {@link String} stored in the Binary Patricia Trie.
     *
     * @return <p>The longest {@link String} stored in this. If the trie is empty, the empty string "" should be
     * returned. Careful: the empty string "" is <b>not</b> the same string as " "; the latter is a string
     * consisting of a single <b>space character</b>! It is also <b>not</b> the same as a <tt>null</tt> reference.</p>
     * <p>Ties should be broken in terms of <b>value</b> of the bit string. For example, if our trie contained
     * only the binary strings 01 and 11, <b>11</b> would be the longest string. If our trie contained
     * only 001 and 010, <b>010</b> would be the longest string.</p>
     */
    public String getLongest() {
        String temp="";
        for(String i:this){
            if(temp.equals("")){
                temp=i;
            }
            else{
                if(i.length()>=temp.length()){
                    temp=i;
                }
            }
        }

        return temp;
    }

    public void IOTPrint(){
        IOTPrint(root,0);
    }
    public void IOTPrint(Node curr,int level) {

        if (curr == null) {
            return;
        }
        IOTPrint(curr.left,level+1);

        System.out.print("k:"+curr.keyRef + "-" + curr.isKey + "-"+ "level:"+level+" | ");

        IOTPrint(curr.right,level+1);
    }

    public class Node {
        public Node left;
        public Node right;
        public String keyRef;
        public boolean isKey;
        public int length;

        public Node(String key, boolean isKey) {
            left = new Node();
            right = new Node();
            keyRef = key;
            this.isKey = isKey;
        }

        public Node(String key, boolean isKey, int l) {
            keyRef = key;
            this.isKey = isKey;
            length = l;

        }

        public Node() {

        }

        public String printAll() {
            return "keyRef:" + keyRef + " isKey:" + isKey + " length:" + length;
        }


    }
}


package projects.avlg;

import static org.junit.Assert.*;

import org.junit.Test;

import projects.avlg.exceptions.EmptyTreeException;
import projects.avlg.exceptions.InvalidBalanceException;

public class AVLGTest {
//
//	@Test
//	public void test1() throws EmptyTreeException {
//		System.out.println("-------------------------test1--------------------------------");
//		AVLGTree avl;
//		try {
//			avl=new AVLGTree(1);
//
//			avl.insert(1);
//			avl.insert(2);
//			avl.insert(3);
//			avl.insert(4);
//			avl.insert(5);
//			avl.insert(6);
//			avl.insert(7);
//			avl.insert(8);
//			avl.insert(9);
//			avl.insert(10);
//	
//			
//			System.out.println("printing on level -------------");
//			avl.printLevelOrder();
//			int value=4;
//			System.out.println("");
//			System.out.println("searching "+value+" this returns: "+avl.search(value));
//			
//			
//			System.out.println("checking if its bst: "+	avl.isBST());
//			System.out.println("checking if its bst: "+	avl.isAVLGBalanced());
//
//			
//		} catch (InvalidBalanceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		
//	}
//	
//	@Test
//	public void test2() throws EmptyTreeException {
//		System.out.println("-------------------------test2--------------------------------");
//		AVLGTree avl;
//		try {
//			avl=new AVLGTree(1);
//
//			int temp=avl.getHeight();
//			
//			System.out.println("this is empty tree height value:"+ temp);
//			avl.insert(1);
//			avl.insert(2);
//			avl.insert(3);
//			avl.insert(4);
//			avl.insert(5);
//			avl.insert(6);
//			avl.insert(7);
//			avl.insert(8);
//			avl.insert(9);
//			avl.insert(10);
//			
//			int temp1=avl.getHeight();
//			
//			System.out.println("this is empty tree height value:"+ temp1);
//			
//		} catch (InvalidBalanceException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//	}
	@Test
	public void deletionTest() throws EmptyTreeException {
		
		AVLGTree avl;
		try {
			int differce=1;
			avl=new AVLGTree(differce);

			int temp=avl.getHeight();
			System.out.println("-------------------------deletionTest G: "+differce+"--------------------------------");
			
			
			avl.insert(1);
			
			avl.insert(2);
			
		avl.insert(3);
			
		avl.insert(4);
			avl.insert(5);
			avl.insert(6);
			avl.insert(7);
			avl.insert(8);
			avl.insert(9);
			
			avl.insert(10);
			
			avl.delete(1);
			
			avl.delete(2);
			
		avl.delete(3);
			
		avl.delete(4);
			avl.delete(5);
			avl.delete(6);
			avl.delete(7);
			avl.delete(8);
			avl.delete(9);
			
			avl.delete(10);
		
			
			System.out.println("this is count  "+ avl.getCount());
			//System.out.println("we got here");
			
			

			int i2=avl.getHeight(avl.root);
			//int i2=(Integer) avl.delete(5);
			
			
		//	System.out.println("print deleted value: "+avl.delete(8));
		//	System.out.println("print deleted height: "+(i2));
		//	System.out.println("this is count after delete: "+ avl.getCount());
			
			//System.out.println("print deleted value: "+i1+","+i2+",");
			
			System.out.println("++printing on level++");
			avl.printLevelOrder();
			System.out.println("");
			System.out.println("--printing on level--");
			
			
			
		} catch (InvalidBalanceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	

}

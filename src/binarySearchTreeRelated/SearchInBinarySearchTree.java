/*
 * Created Date: June 9, 2018
 * 
 * Question - Search In Binary Search Tree (easy) :
 *   Find the target key K in the given binary search tree, 
 *   return the node that contains the key if K is found, otherwise return null.
 *   
 *   Assumption:
 *    There are no duplicate keys in the binary search tree 
 * 
 */

package binarySearchTreeRelated;

public class SearchInBinarySearchTree {
	
	// Time Complexity: O( log(height) )
	// Space Complexity: O(1)
	public static TreeNode search(TreeNode root, int key) {	   
		TreeNode curr = root;
	    while (curr != null) {
	    	    if (curr.value == key) {
	    	    	    return curr;
	    	    } else if (curr.value < key) {
	    	    	    curr = curr.right;
	    	    } else {
	    	    	    curr = curr.left;
	    	    }	    		
	    }
	    return null;
	}
	
	// how to do it recursively ?
//	public TreeNode recursiveSearch(TreeNode root, int key) {	
//		
//
//	}
	

	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		TreeNode root0 = null;
		root0 = search(root0, 1);
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}

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
	
	public TreeNode search(TreeNode root, int key) {	   
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
}

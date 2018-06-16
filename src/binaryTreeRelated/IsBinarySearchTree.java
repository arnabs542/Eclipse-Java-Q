/*
 * Created Date: May 24, 2018
 * 
 * Question - Is Binary Search Tree Or Not:
 *   Determine if a given binary tree is binary search tree
 *   
 *  Assumptions:
 *    There should no be duplicate keys in binary search tree.
 *    You can assume the keys stored in the binary search tree can not be Integer.MIN_VALUE or Integer.MAX_VALUE.
 *    
 *  Corner Cases:
 *    What if the binary tree is null? Return true in this case.
 *   
 * 
 */

package binaryTreeRelated;

public class IsBinarySearchTree {
	
	/* -------< Method 2, better method >------- */
	// Time Complexity: O(n)
	// Space Complexity: O(height), worst case: O(n)
	
	int lastSeen = Integer.MIN_VALUE;
	// Mistake: put this variable declaration in function.
	//	It must be a global variable, or it will be overridden
	
	public boolean isBST_Method2(TreeNode root) {
		if (root == null) return true; // corner case	
		lastSeen = Integer.MIN_VALUE;		
		return inOrderTraversal(root);
	}
	
	private boolean inOrderTraversal(TreeNode node) {
		if (node == null) {
			return true;
		}		
		if (inOrderTraversal(node.left) == false) {
			return false;
		}		
		if (node.value <= lastSeen) {
			return false;
		}
		lastSeen = node.value;		
		if (inOrderTraversal(node.right) == false) {
			return false;
		}		
		return true;
	}
	
	/* -------< Method 1 >------- */
	
	// Time Complexity: O(n)
	// Space Complexity: O(height), worst case: O(n)
	public boolean isBST_Method1(TreeNode root) {	
		if(root == null) return true; // corner case	
		return isBST_Method1(root, Integer.MIN_VALUE, Integer.MAX_VALUE); // initialize
	}
	
	private boolean isBST_Method1(TreeNode node, int min, int max) {		
		if(node == null) return true; // base case	
		if(node.value <= min || node.value >= max) return false; // no node with the same value		
		return isBST_Method1(node.left, min, node.value) && isBST_Method1(node.right, node.value, max);		
	}
	
	/* -------< Wrong approach, didn't understand the concept of BinarySearchTree!!! >------- */
	
	public boolean isBSTmyWrongSul(TreeNode root) {
		
	    if(root == null) return true;
		if(root.left == null && root.right == null) return true;
	    
		if(root.left == null) {
		if(root.right.value > root.value) return isBSTmyWrongSul(root.right);
			else return false;	
		}
	        
		if(root.right == null) {
	      if(root.left.value < root.value) return isBSTmyWrongSul(root.left);
	      else return false;
	    } 
	    
	    if(root.right.value < root.value || root.left.value > root.value) return false;
	    
		return isBSTmyWrongSul(root.left) && isBSTmyWrongSul(root.right);
	}

}

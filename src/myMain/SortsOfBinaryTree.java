// Created Date: May 24, 2018
// Job_Q:
// isSymmetricBT() - Check if a given binary tree is symmetric.
// isTweakedIdentical() 
// isBSTmyWrongSul()

package myMain;

//class TreeNode {
//	public int value;
//	public TreeNode left;
//	public TreeNode right;
//	
//	public TreeNode(int v) {
//		value = v;
//		left = null;
//		right = null;
//	}
//}

public class SortsOfBinaryTree {
		
	/* Question - Determine if a given binary tree is binary search tree. */
	
	int lastSeen = Integer.MIN_VALUE;
	// Mistake: put this variable declaration in function.
	//	It must be a global variable, or it will be overridden
	
	public boolean isBST_Method2(TreeNode root) {
		if(root == null) return true; // corner case	
		lastSeen = Integer.MIN_VALUE;		
		return inOrderTraversal(root);
	}
	
	private boolean inOrderTraversal(TreeNode node) {
		if(node == null) return true;
		
		if(inOrderTraversal(node.left) == false) return false;
		
		if(node.value <= lastSeen) return false;
		lastSeen = node.value;
		
		if(inOrderTraversal(node.right) == false) return false;
		
		return true;
	}
	
	
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
	
	/* Didn't understand the concept of BinarySearchTree!!! */
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
	
	/* Question: Check if a given binary tree is symmetric. */
	public boolean isSymmetricBT(TreeNode root) {
		if(root == null) return true; // corner case
		
		// if a tree is symmetric to itself, it's a Symmetric Binary Tree
		return isSymmetricHelper(root, root); 
	}
	
	// Time Complexity: O(n)
	// Space Complexity: O(height), worst case: O(n)
	private boolean isSymmetricHelper(TreeNode left, TreeNode right) {
		
		if(left == null && right == null) return true;
		if(left == null || right == null) return false;
		if(left.value == right.value) return true;
			
		boolean first = isSymmetricHelper(left.left, right.right);
		boolean second = isSymmetricHelper(left.right, right.left);
		return first && second;
	}
	
	/*  Question: Determine whether two given binary trees are identical  
	 *		assuming any number of ‘tweak’s are allowed. */
	// Time Complexity: O(n ^ 2)
	// Space Complexity: O(height), worst case: O(n)
	public boolean isTweakedIdentical(TreeNode one, TreeNode two) {
		
		if(one == null && two == null) return true;
		if(one == null || two == null) return false;
		
		if(one.value != two.value) return false;
			
		// one.left, two.left && one.right, two.right || one.left, two.right && one.right, two.left		
		return isTweakedIdentical(one.left, two.left) && isTweakedIdentical(one.right, two.right)
				|| isTweakedIdentical(one.left, two.right) && isTweakedIdentical(one.right, two.left);
		
	}

}

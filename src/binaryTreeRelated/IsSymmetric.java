/*
 * Created Date: May 24, 2018
 * 
 * Question - Symmetric Binary Tree: 
 *   Check if a given binary tree is symmetric.
 *   
 *   Example: 
 *   
 *         5
 *         
 *      /      \
 *      
 *     3        3
 *     
 *   /   \    /   \
 *   
 *  1     4  4     1
 *  
 *  is symmetric.
 *  
 *  -------------------
 *  
 *          5
 *
 *       /    \
 *
 *     3        3
 *
 *   /   \     /   \
 *
 *  1      4  1      4
 *  
 *  is not symmetric.
 *  
 *  Corner Cases:
 *    What if the binary tree is null? Return true in this case.
 *   
 *   Follow up:
 *     Tweaked Identical Binary Trees
 *   
 * Updated:
 * 
 */

package binaryTreeRelated;

public class IsSymmetric {
	
	public boolean isSymmetricBT(TreeNode root) {
		if (root == null) { // corner case
			return true;
		} 
		
		// if a tree is symmetric to itself, it's a Symmetric Binary Tree
		return isSymmetricHelper(root, root); 
	}

	private boolean isSymmetricHelper(TreeNode left, TreeNode right) {
		
		if(left == null && right == null) return true;
		if(left == null || right == null) return false;
		if(left.value == right.value) return true;
			
		boolean first = isSymmetricHelper(left.left, right.right);
		boolean second = isSymmetricHelper(left.right, right.left);
		return first && second;
	}
	
	// Time Complexity: O(n)
	// Space Complexity: O(height), worst case: O(n)
}

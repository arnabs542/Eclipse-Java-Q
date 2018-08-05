/*
 * Created Date: August 5, 2018
 * 
 * Question - Lowest Common Ancestor I (medium):
 *   Given two nodes in a binary tree, find their lowest common ancestor.
 *   
 *   Example: 
 *   
 *           5          
 *         /   \
 *       9      12
 *     /  \       \
 *    2    3       14
 *   
 *  The lowest common ancestor of 2 and 14 is 5
 *  The lowest common ancestor of 2 and 9 is 9
 * 
 */


package binaryTreeRelated;

public class LowestCommonAncestorI {
	
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode one, TreeNode two) {
		// base case
		if (root == null) {
			return null;
		}
		if (root == one || root == two) {
			return one;
		}
		
	    // step1: 
	    TreeNode left = lowestCommonAncestor(root.left, one, two);
	    TreeNode right = lowestCommonAncestor(root.right, one, two);
	    
	    // step2 + step 3
	    if (left != null && right != null) {
	      return root;
	    } else {
	      return left == null ? right : left;
	    }	
	}

}

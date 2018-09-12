/*
 * == Created Date == August 5, 2018
 * 
 * == Question - Lowest Common Ancestor I (medium) ==
 * Given two nodes in a binary tree, find their lowest common ancestor.
 *   
 * == Example ==  
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
 *  == Note == 
 *  Sept.11, 2018: Review
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
			return root;
		}
		
	    // step1: 
	    TreeNode left = lowestCommonAncestor(root.left, one, two);
	    TreeNode right = lowestCommonAncestor(root.right, one, two);
	    
	    // step2 + step 3
	    if (left != null && right != null) {
	      return root;
	    } 
	    return left == null ? right : left;
	}
	
// 	  mistakes:
//    if (left == p && right == q || left == q && right == p) {
//        return root;
//    } 

}

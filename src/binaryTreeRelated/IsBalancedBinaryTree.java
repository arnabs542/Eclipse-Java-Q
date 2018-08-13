/*
 * Created Date: May 24, 2018
 * 
 * Question - Check If Binary Tree Is Balanced:
 *   Check if a given binary tree is balanced. A balanced binary tree is 
 *   one in which the depths of every node’s left and right subtree differ by at most 1.
 *   
 *   Corner Cases:
 *   	What if the binary tree is null? Return true in this case.  
 *   
 * Updated: 
 *   June 15, 2018: Review
 *   August 13, 2018: Review, Strengthen_3 
 * 
 */

package binaryTreeRelated;

public class IsBalancedBinaryTree {
	
	public static boolean isBalanced(TreeNode root) {
		if (root == null) {
			return true;
		}
		if (helper(root) != -1) {
			return true;
		} else {
			return false;
		}
	}
	
	private static int helper(TreeNode root) {
		if (root == null) {
			return 0;
		}	
		
		// step 1: what do you expect from left or right child
		int leftHeight = helper(root.left);
		int rightHeight = helper(root.right); 
		
		// step 2: what do you want to do in the current layer
		// post-order traversal
		if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
			return -1;
		} 
		
		// step 3: what do you report to your parent (should be same as step 1)
		return Math.max(leftHeight, rightHeight) + 1;
	}
	// Mistake: didn't consider all possible situation: Math.abs(leftHeight - rightHeight) > 1!!!!!
	
	// Time Complexity: O(n), total numbers of the tree
	// Space Complexity: O(height), worst case: O(logn)
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		int[] arr1 = {1, 2, 3, 4, 5};
		TreeNode root1 = TreeNode.genBst(arr1);
		TreeNode.printLayerByLayer(root1);
		System.out.println(isBalanced(root1));
			
		/* Test Case 2 */
		System.out.println("\n---< Test Case 2 >---");
		
		int[] arr2 = {1, 2, 3, 4, 5};
		TreeNode root2 = TreeNode.genBalancedBst(arr2);		
		TreeNode.printLayerByLayer(root2);
		System.out.println(isBalanced(root2));
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");		
	}
}

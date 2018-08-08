/*
 * Created Date: August 7, 2018
 * 
 * Question - Maximum Path Sum Binary Tree I (medium):
 *   Given a binary tree in which each node contains an integer number. 
 *   Find the maximum possible sum from one leaf node to another leaf node. 
 *   If there is no such path available, return Integer.MIN_VALUE(Java)/INT_MIN (C++).
 *   
 *   Example: 
 *       -15
 *      /    \
 *     2      11 
 *   /   \
 *  6     14
 *  The maximum path sum is 6 + 11 + 14 = 31.
 *  
 *  Notes: MidTerm 2
 * 
 */

package binaryTreeRelated;

public class MaximumPathSumBinaryTreeI {
	public int maxPathSum(TreeNode root) {
		int[] max = new int[] {Integer.MIN_VALUE}; // wrap a global variable into an array 
		helper(root, max);
		return max[0];		
	}
	
	private int helper(TreeNode root, int[] max) {
		if (root == null) {
			return 0;
		}
		
		int left = helper(root.left, max);
		int right = helper(root.right, max);
		
		// only update the max path sum for the node that 
		// has both left and right child
		if (root.left != null && root.right != null) {
			max[0] = Math.max(max[0], left + right + root.value);
			return Math.max(left, right) + root.value;
		} else {
			return root.left == null ? right + root.value : left + root.value;
		}		
	}
}

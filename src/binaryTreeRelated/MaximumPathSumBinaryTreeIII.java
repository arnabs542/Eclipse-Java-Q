/*
 * Created Date: August 13, 2018
 * 
 * Question - Maximum Path Sum Binary Tree III, from leaf node to root:
 *   Given a binary tree in which each node contains an integer number. 
 *   Find the maximum possible subpath sum
 *   (both the starting and ending node of the subpath should be 
 *   on the same path from root to one of the leaf nodes, 
 *   and the subpath is allowed to contain only one node).
 *      
 *   Example: 
 *   
 *      -5
 *    /    \
 *   2      11
 *        /    \
 *       6     14
 *            /
 *          -3
 *  The maximum path sum is 11 + 14 = 25
 * 
 */

package binaryTreeRelated;

public class MaximumPathSumBinaryTreeIII {
	public int maxPathSum(TreeNode root) {
		int[] globalMax = new int[] {Integer.MIN_VALUE};
		int SumPrefix = 0;
		helper(root, SumPrefix, globalMax);
		return globalMax[0];
	}
	
	private void helper(TreeNode root, int SumPrefix, int[]globalMax) {
		if (root == null) {
			return;
		}
		SumPrefix += root.value;
		if (root.left == null && root.right == null) {
			globalMax[0] = Math.max(globalMax[0], SumPrefix);
		}
		
		helper(root.left, SumPrefix, globalMax);
		helper(root.right, SumPrefix, globalMax);	
	}
	
	// -----------------------------
	public int maxPath(TreeNode root) {

		return maxPath(root, 0);
	}
	
	private int maxPath(TreeNode root, int SumPrefix) {
		if (root == null) {
			return 0;
		}
		SumPrefix += root.value;
		if (root.left == null && root.right == null) {
			return SumPrefix;
		} else if (root.left == null) {
			return SumPrefix + maxPath(root.right, SumPrefix);
		} else if (root.right == null) {
			return SumPrefix + maxPath(root.left, SumPrefix);
		}
	
		return Math.max(maxPath(root.right, SumPrefix), maxPath(root.left, SumPrefix));
	}
}

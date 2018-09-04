/*
 * == Created Date == 
 * August 13, 2018
 * 
 * == Question - Maximum Path Sum Binary Tree II, from any node to any node ==
 * Given a binary tree in which each node contains an integer number. 
 * Find the maximum possible sum from any node to any node (the start node and the end node can be the same). 
 *   
 *   
 * == Example ==
 *   
 *       -1
 *     /    \
 *    2      11
 *         /    \
 *        6    -14
 * 
 *   one example of paths could be -14 -> 11 -> -1 -> 2
 *   another example could be the node 11 itself
 *   The maximum path sum in the above binary tree is 6 + 11 + (-1) + 2 = 18
 * 
 * == Update ==
 * September 4, 2018: Update to fix a fail test case in LeetCode, missing test case in LaiCode
 * 
 */

package binaryTreeRelated;

public class MaximumPathSumBinaryTreeII {
	
	public int maxPathSum(TreeNode root) {	
		int[] max = new int[] {Integer.MIN_VALUE};
		helper(root, max);
		return max[0]; 		
	}
	
	private int helper(TreeNode root, int[]max) {
		if (root == null) {
			return 0;
		}
		// step 1: what do you expect from left or right child?
		//   - single maximum path of my left/right subtree
		//   - discard if they are negative
		int left = helper(root.left, max);
		int right = helper(root.right, max);
		
		int cur = root.value;
		if (left > 0) {
			cur += left;
		} 
		if (right > 0) {
			cur += right;
		}
		
		// step 2: what do you want to do in the current layer?
		//   - update global max 
		max[0] = Math.max(max[0], cur);
		
		// step 3: what do you report to your parent (should be same as step 1)
		int maxChild = Math.max(left, right);		
		return maxChild > 0 ? maxChild + root.value : root.value;
	}
}

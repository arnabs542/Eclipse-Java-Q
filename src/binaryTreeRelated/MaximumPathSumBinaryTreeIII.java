/*
 * == Created Date ==
 * August 13, 2018
 * 
 * == Question - Maximum Path Sum Binary Tree III, root-to-leaf sub-path ==
 * Given a binary tree in which each node contains an integer number. 
 * Find the maximum possible subpath sum
 *   (both the starting and ending node of the subpath should be 
 *   on the same path from root to one of the leaf nodes, 
 *   and the subpath is allowed to contain only one node).
 *      
 * == Example == 
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

import java.util.ArrayList;
import java.util.List;

public class MaximumPathSumBinaryTreeIII {
	
	// ------------ all paths from leaf to root -----------
	public int maxPathSum1(TreeNode root) {
		int[] globalMax = new int[] {Integer.MIN_VALUE};
		int SumPrefix = 0;
		helper1(root, SumPrefix, globalMax);
		return globalMax[0];
	}
	
	private void helper1(TreeNode root, int SumPrefix, int[]globalMax) {
		if (root == null) {
			return;
		}
		SumPrefix += root.value;
		if (root.left == null && root.right == null) {
			globalMax[0] = Math.max(globalMax[0], SumPrefix);
		}
		
		helper1(root.left, SumPrefix, globalMax);
		helper1(root.right, SumPrefix, globalMax);	
	}
	
	
	// ------------------------------------------------
	public int maxPathSum(TreeNode root) {
		int[] globalMax = new int[] {Integer.MIN_VALUE};		
		List<Integer> prefix = new ArrayList<>();				
		helper(root, prefix, globalMax);
		return globalMax[0];
	}
	
	private void helper(TreeNode root, List<Integer> prefix, int[]globalMax) {
		if (root == null) {
			prefix.add(null);
			return;
		}
		
		prefix.add(root.value);
		int prefixSum = 0;
		for (int i = prefix.size() - 1; i >= 0; i--) {
			prefixSum += prefix.get(i);
			globalMax[0] = Math.max(globalMax[0], prefixSum);			
		}
			
		helper(root.left, prefix, globalMax);
		prefix.remove(prefix.size() - 1);
		
		helper(root.right, prefix, globalMax);
		prefix.remove(prefix.size() - 1);
	}
	
	// Time Complexity: O(n * height), worst case = O(n ^ 2)
	//   pre-order traverse all the nodes --- O(n);
	//   every time check prefix -- O(height)
	
	// Space Complexity: O(height);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		MaximumPathSumBinaryTreeIII testObj = new MaximumPathSumBinaryTreeIII();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		TreeNode root1 = new TreeNode(6);
		root1.left = new TreeNode(-2);
		root1.right = new TreeNode(-6);
		
		root1.left.left = new TreeNode(-3);
		root1.left.right = new TreeNode(4);
		
		root1.left.left.left = new TreeNode(5);
		root1.left.right.left = new TreeNode(-5);
		
		//		     6
		//	      /     \
		//	    -2      -6
		//	  /    \
		//  -3      4   
		//  /      /
		// 5      -5
				
		int result1 = testObj.maxPathSum(root1);
		System.out.println(result1);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");		
	}
}

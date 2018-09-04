/*
 * == Created Date ==
 * August 14, 2018
 * 
 * == Question - Binary Tree Path Sum To Target III, node to its descendants ==
 * Given a binary tree in which each node contains an integer number. 
 * Determine if there exists a path 
 * (the path can only be from one node to itself or to any of its descendants), 
 * the sum of the numbers on the path is the given target number.
 *     
 * == Example ==
 *       5
 *     /    \
 *    2      11
 *         /    \
 *        6     14
 *       /
 *      3
 *      
 *   If target = 17, There exists a path 11 + 6, the sum of the path is target.
 *   If target = 20, There exists a path 11 + 6 + 3, the sum of the path is target.
 *   If target = 10, There does not exist any paths sum of which is target.
 *   If target = 11, There exists a path only containing the node 11.
 * 
 */


package binaryTreeRelated;

import java.util.ArrayList;
import java.util.List;

public class PathSumToTargetIII {
	
	public boolean exist(TreeNode root, int target)  {
		List<Integer> prefix = new ArrayList<>();	
		boolean[] flag = new boolean[] {false};
		helper(root, prefix, target, flag);
		return flag[0];
	}

	private void helper(TreeNode root, List<Integer> prefix, int target, boolean[] flag) {
		if (root == null) {
			prefix.add(null);
			return;
		}
		
		prefix.add(root.value);
		int prefixSum = 0;
		for (int i = prefix.size() - 1; i >= 0; i--) {
			prefixSum += prefix.get(i);
			if (prefixSum == target) {
				flag[0] = true;
			}		
		}
			
		helper(root.left, prefix, target, flag);
		prefix.remove(prefix.size() - 1);
		
		helper(root.right, prefix, target, flag);
		prefix.remove(prefix.size() - 1);
	}
	
	// Time Complexity: O(n * height), worst case = O(n ^ 2)
	//   pre-order traverse all the nodes --- O(n);
	//   every time check prefix -- O(height)
	
	// Space Complexity: O(height), , worst case = O(n)

}

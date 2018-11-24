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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	//TODO O(n) method using prefixSum
    public int pathSum(TreeNode root, int sum) {        
        Map<Integer, Integer> prefixSumMap = new HashMap<>(); // key: prefixSum, value: frequency
        prefixSumMap.put(0, 1);
        
        int[] pathCount = {0};
        preOrder(root, sum, 0, prefixSumMap, pathCount);
        return pathCount[0];
    }
    
    private void preOrder(TreeNode root, int target, int curSum, Map<Integer, Integer> prefixSumMap, int[] pathCount) {
        if (root == null) {
            return;
        }
        
        curSum += root.value;
                       
        if (prefixSumMap.containsKey(curSum - target) && prefixSumMap.get(curSum - target) > 0) {
            pathCount[0] += prefixSumMap.get(curSum - target);
        }
        
        prefixSumMap.put(curSum, prefixSumMap.getOrDefault(curSum, 0) + 1);
        
        if (root.left != null) {
            preOrder(root.left, target, curSum, prefixSumMap, pathCount);
            prefixSumMap.put(curSum + root.left.value, prefixSumMap.get(curSum + root.left.value) - 1);
        }

        if (root.right != null) {
            preOrder(root.right, target, curSum, prefixSumMap, pathCount);
            prefixSumMap.put(curSum + root.right.value, prefixSumMap.get(curSum + root.right.value) - 1);
        }
    }
	// Time Complexity: O(?);
	// Space Complexity: O(?);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		PathSumToTargetIII testObj = new PathSumToTargetIII();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(-2);
		root.right = new TreeNode(-3);
		
		System.out.println(testObj.pathSum(root, -1)); // 1
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		TreeNode root1 = new TreeNode(0);
		root1.left = new TreeNode(1);
		root1.right = new TreeNode(1);
		System.out.println(testObj.pathSum(root1, 1)); // 4
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}

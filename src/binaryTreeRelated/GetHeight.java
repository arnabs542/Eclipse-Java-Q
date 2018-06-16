/*
 * Created Date: May 24, 2018
 * 
 * Question - Height of Binary Tree:
 * 
 */

package binaryTreeRelated;

import binarySearchTreeRelated.TreeNode;

public class GetHeight {
	
	public static int getHeight(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
	}
	
	// Time Complexity: O(n), total numbers of the tree
	// Space Complexity: O(height), worst case: O(n)

	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("\n ---< Test Case 1 >---");
		
		int[] arr1 = {1, 2, 3, 4, 5};
		TreeNode root1 = TreeNode.genBst(arr1);
		TreeNode.printLayerByLayer(root1);
		System.out.print("height = " + getHeight(root1)); // expected 5
		
		/* Test Case 2 */
		System.out.println("\n ---< Test Case 2 >---");
		int[] arr2 = {1, 2, 3, 4, 5};
		TreeNode root2 = TreeNode.genBalancedBst(arr2);
		TreeNode.printLayerByLayer(root2);
		System.out.print("height = " + getHeight(root2)); // expected 3
		
		/* Test Case 3 */
		System.out.println("\n ---< Test Case 3 >---");		
	}
}

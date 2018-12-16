/*
 * == Created Date == 
 * August 26, 2018
 * 
 * == Question - Cousins In Binary Tree ==
 * Given a binary Tree and the two keys, determine whether the two nodes are cousins of each other or not. 
 * Two nodes are cousins of each other if they are at the same level and have different parents.
 *     
 * == Example ==
 *   
 *        6
 *      /   \
 *     3     5
 *    / \   / \
 *   7   8 1   13
 *   
 *   7 and 1, result is true.
 *   3 and 5, result is false.
 *   7 and 5, result is false.
 *   
 * == Notes ==
 * Lai Course - Final problem 2
 * 
 */

package binaryTreeRelated;

import java.util.LinkedList;
import java.util.Queue;

public class AreCousinNodes {

	// ----------------------< Method 1 >-----------------------//
	public boolean isCousin(TreeNode root, int a, int b) {
		if (root == null) {
			return false;
		}
		if (!checkParent(root, a, b) 
				&& getLevel(root, a, 0) == getLevel(root, b, 0) 
				&& getLevel(root, a, 0) != -1 
				&& getLevel(root, b, 0) != -1) {
			return true;
		}
		return false;
	}
	
	private boolean checkParent(TreeNode root, int a, int b) {
		if (root == null || root.right == null & root.left == null) {
			return false;
		}

		if (root.right == null) {
			return checkParent(root.left, a, b);
		} 
		if (root.left == null) {
			return checkParent(root.right, a, b);
		}
		
		if (root.right.value == a && root.left.value == b || 
			 root.right.value == b && root.left.value == a) {
			return true;
		}		
		return checkParent(root.left, a, b) || checkParent(root.right, a, b);
	}
	
	private int getLevel(TreeNode root, int target, int level) {
		if (root == null) {
			return -1;
		}
		
		if (root.value == target) {
			return level;
		}
		
		int left = getLevel(root.left, target, level + 1);
		int right = getLevel(root.right, target, level + 1);
		
		return left == -1 ? right : left;
	}
	
	// Time Complexity: O(n);
	// Space Complexity: O(height), worst case O(n);
	
	// ----------------------< Method 2 >-----------------------//
	
	public boolean isCousinMeth2(TreeNode root, int a, int b) {
		if (root == null) {
			return false;
		}		
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			boolean flagA = false;
			boolean flagB = false;
			for (int i = 0; i < size; i++) {
				TreeNode cur = queue.poll();
				int left = Integer.MAX_VALUE;
				int right = Integer.MAX_VALUE;
				
				if (cur.left != null) {
					queue.offer(cur.left);
					left = cur.left.value;
				}
				if (cur.right != null) {
					queue.offer(cur.right);
					right = cur.right.value;
				}
				
				// check if two nodes has the same parent
				if (left == a && right == b 
						|| left == b && right == a) {
					return false;
				}				
				if (!flagA) {
					flagA = (left == a || right == a);
				}
				if (!flagB) {
					flagB = (left == b || right == b);
				}
			}
			
			// check if two nodes are in the same level
			if (flagA && flagB) {
				return true;
			} else if (flagA || flagB) {
				return false;
			}
		}
		return false;
	}
	
	// Time Complexity: O(n);
	// Space Complexity: O(number of nodes of the layer with the largest number of nodes), worst case O(n/2) = O(n);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		AreCousinNodes testObj = new AreCousinNodes();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		/*
		 *        6
		 *      /   \
		 *     3     5
		 *    / \   / \
		 *   7   8 1   13
		 *   
		 *   7 and 1, result is true.
		 *   3 and 5, result is false.
		 *   7 and 5, result is false.
		 *   
		 */
		
		TreeNode root = new TreeNode(6);
		root.left = new TreeNode(3);
		root.left.left = new TreeNode(7);
		root.left.right = new TreeNode(8);
		root.right = new TreeNode(5);
		root.right.left = new TreeNode(1);
		root.right.right = new TreeNode(13);
		
//		int level = testObj.getLevel(root, 13, 0);
//		System.out.println(level);
		

		System.out.println(testObj.isCousin(root, 7, 1)); // expected: true
		System.out.println(testObj.isCousinMeth2(root, 7, 1) + "\n"); // expected: true
		
		System.out.println(testObj.isCousin(root, 3, 5)); // expected: false
		System.out.println(testObj.isCousinMeth2(root, 3, 5) + "\n"); // expected: false
		
		System.out.println(testObj.isCousin(root, 7, 5)); // expected: false
		System.out.println(testObj.isCousinMeth2(root, 7, 5)); // expected: false
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		
		/*
		 *          4
		 *        /   
		 *       2     
		 *    /     \    
		 *   1       3    
		 *    \     /
		 *     8   7
		 *   
		 *   1 and 3, result is false.
		 *   
		 */
		
		TreeNode root2 = new TreeNode(4);
		root2.left = new TreeNode(2);
		root2.left.left = new TreeNode(1);
		root2.left.left.right = new TreeNode(8);
		root2.left.right = new TreeNode(3);
		root2.left.right.left = new TreeNode(7);

//		int level2 = testObj.getLevel(root2, 3, 0);
//		System.out.println(level2);
		
		System.out.println(testObj.isCousin(root2, 1, 3)); // expected: false
		System.out.println(testObj.isCousinMeth2(root2, 1, 3)); // expected: false
				
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");		
		
		/*
		 *             1
		 *          /     \
		 *        3        6 
		 *      /   \    /   \
		 *     2     4  5     0   
		 *   
		 *  Targets nodes don't exist in the binary tree
		 *  11 and -2, result is false.
		 *   
		 */
		
		TreeNode root3 = new TreeNode(1);
		root3.left = new TreeNode(3);
		root3.left.left = new TreeNode(2);
		root3.left.right = new TreeNode(4);
		root3.right = new TreeNode(6);
		root3.right.left = new TreeNode(5);
		root3.right.right = new TreeNode(0);

//		int level3 = testObj.getLevel(root3, 11, 0);
//		System.out.println(level3);
		
		System.out.println(testObj.isCousin(root3, 11, -2)); // expected: false
		System.out.println(testObj.isCousinMeth2(root3, 11, -2)); // expected: false
	}
}

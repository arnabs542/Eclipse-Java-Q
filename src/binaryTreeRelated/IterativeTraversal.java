/*
 * Created Date: May 20, 2018
 * 
 * Question - Binary Search Tree Traversal Iteratively  
 *   
 * Updated:
 *   June 10, 2018: Review, Practice Class 7 
 * 
 */

package binaryTreeRelated;

import java.util.Deque;
import java.util.LinkedList;

public class IterativeTraversal {

	// Pre-Order Traversal using iteration
	// Time Complexity: O(n) 
	// Space Complexity: O(height + n) = O(n)
	public static void preOrderIterative(TreeNode root) {
		if (root == null) {
			return;
		}
		Deque<TreeNode> stack = new LinkedList<TreeNode>();
		stack.offerFirst(root);
		TreeNode curr = root;
		while (!stack.isEmpty()) {			
			if (curr != null) {
				System.out.print(curr.value + " ");
				if (curr.right != null) stack.offerFirst(curr.right);
				curr = curr.left;
			}
			else {
				curr = stack.pollFirst();
			}			
		}			
	}
			
	// Time Complexity: O(n) 
	// Space Complexity: O(height + n) = O(n)	
	public static void inOrderIterative(TreeNode root) {
		if(root == null) {
			return;
		}
		Deque<TreeNode> stack = new LinkedList<TreeNode>();
		TreeNode curr = root;
		while (curr!= null || !stack.isEmpty()) {
			if (curr != null) {
				stack.offerFirst(curr);
				curr = curr.left;
			} else {
				curr = stack.pollFirst();
				System.out.print(curr.value + " ");
				curr = curr.right;
			}			
		}		
	}	
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		/* Test Case
		 *             10
		 *          /       \
		 *         5         15
		 *       /   \      /  \
		 *      2     7    13   20
		 *     /
		 *    1
		 *     
		 */
		
		TreeNode root = new TreeNode(10);
		root.left = new TreeNode(5);
		root.left.left = new TreeNode(2);
		root.left.left.left = new TreeNode(1);
		root.left.right = new TreeNode(7);
		root.right = new TreeNode(15);
		root.right.left = new TreeNode(13);
		root.right.right = new TreeNode(20);	
				
		preOrderIterative(root);
		System.out.print("\n");
		
		inOrderIterative(root);
		System.out.print("\n");
		
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}

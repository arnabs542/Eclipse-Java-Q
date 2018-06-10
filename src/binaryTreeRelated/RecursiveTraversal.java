/*
 * Created Date: May 18, 2018
 * 
 * Question - Binary Search Tree Traversal Recursively
 *      
 *   Follow up:
 *     Binary Search Tree Traversal Iteratively
 * 
 */

package binaryTreeRelated;

public class RecursiveTraversal {
	
	// Pre-Order Traversal using recursion
	// Time Complexity: O(n)
	// Space Complexity: O(height), worst case: O(n), balanced BST: O(logn)
	public static void preOrder(TreeNode node) {
		if(node == null) return;
		
		System.out.print(node.value + " ");
		preOrder(node.left);
		preOrder(node.right);	
	}
	
	// In-Order Traversal using recursion
	public static void inOrder(TreeNode node) {
		if(node == null) return;
			
		inOrder(node.left);
		System.out.print(node.value + " ");
		inOrder(node.right);	
	}
	
	// Post-Order Traversal using recursion
	public static void postOrder(TreeNode node) {
		if(node == null) return;
		
		postOrder(node.left);
		postOrder(node.right);
		System.out.print(node.value + " ");	
	}
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		TreeNode root = new TreeNode(10);
		root.left = new TreeNode(5);
		root.left.left = new TreeNode(2);
		root.left.left.left = new TreeNode(1);
		root.left.right = new TreeNode(7);
		root.right = new TreeNode(15);
		root.right.left = new TreeNode(12);
		root.right.right = new TreeNode(20);	
				
		preOrder(root);
		System.out.print("\n");
		
		inOrder(root);
		System.out.print("\n");
		
		postOrder(root);
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

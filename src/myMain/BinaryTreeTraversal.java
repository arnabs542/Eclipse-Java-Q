// Created Date: May 18, 2018
// Application: Practice Binary Search Tree Traversal

// Updated: May 20, 2018
// inOrderIterative(), preOrderIterative() - Binary Search Tree Traversal without recursion

package myMain;

import java.util.Deque;
import java.util.LinkedList;

class TreeNode {
	public int value;
	public TreeNode left;
	public TreeNode right;
	
	public TreeNode(int v) {
		value = v;
		left = null;
		right = null;
	}
}

public class BinaryTreeTraversal {
	
	// Time Complexity: O(n) 
	// Space Complexity: O(height), worst case: O(n), balanced BST: O(logn)
	public static boolean checkBalanced2(TreeNode node) {
		if(node == null) return true; // corner case
		if(helper(node) == -1) return false;
		else return true;
	}

	static int helper(TreeNode node) {
		if(node == null) return 0; 
		
		int leftHeight = helper(node.left);
		int rightHeight = helper(node.right);
		
		if(leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
			return -1;
		} // post-order traversal
		return Math.max(leftHeight, rightHeight) + 1;
	}
	
	// Time Complexity: O(n)
	// Space Complexity: O(height)
	public static int getHeight(TreeNode node) {		
		if(node == null) return 0;
		
		int leftHeight = getHeight(node.left);
		int rightHeight = getHeight(node.right);
		return Math.max(leftHeight, rightHeight) + 1;	// post-order traversal
	}
	
	// Pre-Order Traversal using iteration
	// Time Complexity: O(n) 
	// Space Complexity: O(height + n) = O(n)
	public static void preOrderIterative(TreeNode root) {
		if(root == null) return;
		Deque<TreeNode> stack = new LinkedList<TreeNode>();
		TreeNode curr = root;
		while(curr != null || !stack.isEmpty()) {			
			if(curr != null) {
				System.out.print(curr.value + " ");
				if(curr.right != null) stack.push(curr.right);
				curr = curr.left;
			}
			else {
				curr = stack.pop();
			}			
		}			
	}
		
	// Pre-Order Traversal using recursion
	// Time Complexity: O(n)
	// Space Complexity: O(height), worst case: O(n), balanced BST: O(logn)
	public static void preOrder(TreeNode node) {
		if(node == null) return;
		
		System.out.print(node.value + " ");
		preOrder(node.left);
		preOrder(node.right);	
	}

	// Time Complexity: O(n) 
	// Space Complexity: O(height + n) = O(n)	
	public static void inOrderIterative(TreeNode root) {
		if(root == null) return;
		Deque<TreeNode> stack = new LinkedList<TreeNode>();
		TreeNode curr = root;
		while(curr != null || !stack.isEmpty()) {
			if(curr != null) {
				stack.push(curr);
				curr = curr.left;
			} else {
				curr = stack.pop();
				System.out.print(curr.value + " ");
				curr = curr.right;
			}			
		}		
	}	
	
	// In-Order Traversal using recursion
	public static void inOrder(TreeNode node) {
		if(node == null) return;
			
		inOrder(node.left);
		System.out.print(node.value + " ");
		inOrder(node.right);	
	}
	
	// if there's parent pointer? how to do traversal
	
	// Post-Order Traversal using recursion
	public static void postOrder(TreeNode node) {
		if(node == null) return;
		
		postOrder(node.left);
		postOrder(node.right);
		System.out.print(node.value + " ");	
	}
	
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
		
		preOrderIterative(root);
		System.out.print("\n");
		
		inOrder(root);
		System.out.print("\n");
		
		inOrderIterative(root);
		System.out.print("\n");	
		
		postOrder(root);	
		System.out.print("\n");	
		
		int h = getHeight(root);
		System.out.print(h + "\n");	
		
		boolean rst = checkBalanced2(root);
		System.out.print(rst);	
	}
}

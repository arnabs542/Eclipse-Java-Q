/*
 * Created Date: June 9, 2018
 * 
 */

package binarySearchTreeRelated;

import java.util.Deque;
import java.util.LinkedList;

public class TreeNode {
	
	public int value;
	public TreeNode left;
	public TreeNode right;
	
	public TreeNode(int v) {
		value = v;
		left = null;
		right = null;
	}

	/* -----------< generate a BST >---------------*/
	public static TreeNode genBst(int[] arr) {
		if (arr == null) {
			return null;
		}
		TreeNode root = new TreeNode(arr[0]);
		for (int i = 1; i < arr.length; i++) {
			root = insert(root, arr[i]);
		}
		return root;
	}
	
	private static TreeNode insert(TreeNode root, int key) {		
		TreeNode curr = root;
		while (curr != null) {
			if (curr.value < key) {
				if (curr.right != null) {
					curr = curr.right;
				} else {
					curr.right = new TreeNode(key);
					return root;
				}
			} else if (curr.value > key) {
				if (curr.left != null) {
					curr = curr.left;
				} else {
					curr.left = new TreeNode(key);
					return root;
				}				
			} else {
		        return root;
		    }
		}
		return new TreeNode(key);
	}
	
	/* -----------< print BST >---------------*/
	public static void printInOrder(TreeNode root) {
		Deque<TreeNode> stack = new LinkedList<>();
		TreeNode curr = root;
		while (!stack.isEmpty() || curr != null) {
			if (curr == null) {
				curr = stack.pop();
				System.out.print(curr.value + " ");
				curr = curr.right;
			} else {
				stack.push(curr);
				curr = curr.left;
			}
		}
		System.out.print("\n");
	}
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		int[] arr1 = {3, 1, 2};
		TreeNode root1 = TreeNode.genBst(arr1);
		TreeNode.printInOrder(root1);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}

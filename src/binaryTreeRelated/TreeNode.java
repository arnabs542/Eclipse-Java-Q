/*
 * Created Date: June 9, 2018
 * 
 * Updated:
 *   June 11, 2018: genBst(); printInOrder()
 *   
 * 
 */

package binaryTreeRelated;

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
	
	/* -----------< print BST in-order >---------------*/
	public static void printInOrder(TreeNode root) {
		if (root == null) {
			System.out.print("null");
		}
		Deque<TreeNode> stack = new LinkedList<>();		
		while (root!= null || !stack.isEmpty()) {
			if (root == null) {
				root = stack.pop();
				System.out.print(root.value + " ");
				root = root.right;
			} else {
				stack.push(root);
				root = root.left;
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
		
		int[] arr1 = {1, 2, 3, 4, 5};
		TreeNode root1 = TreeNode.genBst(arr1);
		TreeNode.printInOrder(root1);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}



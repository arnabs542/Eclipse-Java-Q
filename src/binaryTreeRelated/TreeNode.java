package binaryTreeRelated;

/*
 * Created Date: June 9, 2018
 * 
 * Updated:
 *   June 11, 2018: genBst(); printInOrder()   
 *   June 14, 2018: genBalancedBst(), printLayerByLayer()
 * 
 */

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeNode {
	
	public int value;
	public TreeNode left;
	public TreeNode right;
	
	public TreeNode(Integer v) {
		value = v;
		left = null;
		right = null;
	}
	
	/* -----------< generate a height balanced BST >---------------*/	
	public static TreeNode genBalancedBst(int[] arr) {
		if (arr == null) {
			return null;
		}
        return createTree(arr, 0, arr.length - 1);
	}

	private static TreeNode createTree(int[] arr, int left, int right) {
		// base case
		if (left > right) {
			return null;
		}
		int mid = left + (right - left) / 2;
		TreeNode root = new TreeNode(arr[mid]);
		root.left = createTree(arr, left, mid - 1);
		root.right = createTree(arr, mid + 1, right);
		return root;
	}

	/* -----------< generate a BST, with the first element as root node >---------------*/	
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
	
	/* -----------< print BST Layer By Layer >---------------*/
	public static void printLayerByLayer(TreeNode root) {		
		if (root == null) { // corner case
			return;
		}
		Queue<TreeNode> queue = new LinkedList<>();	
		queue.offer(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			List<Integer> list = new LinkedList<>();
			for (int i = 0; i < size; i++) {
				TreeNode curr = queue.poll();	
				
				list.add(curr.value);
				System.out.print(curr.value + " "); 
				
				if (curr.left != null) {
					queue.offer(curr.left);				
				} 
				if (curr.right != null) {
					queue.offer(curr.right);
				} 
			}
			System.out.print(" "); 
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
		printLayerByLayer(root1);
		
		/* Test Case 2 */
		System.out.println("\n---< Test Case 2 >---");
		
		int[] arr2 = {1, 2, 3, 4, 5};
		TreeNode root2 = TreeNode.genBalancedBst(arr2);
		TreeNode.printInOrder(root2);
		printLayerByLayer(root2);
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}


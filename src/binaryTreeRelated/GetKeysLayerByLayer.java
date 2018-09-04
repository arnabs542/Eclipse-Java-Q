/*
 * == Created Date == 
 * June 12, 2018
 * 
 * == Question - Get Keys In Binary Tree Layer By Layer ==
 * Get the list of list of keys in a given binary tree layer by layer. 
 * Each layer is represented by a list of keys and the keys are traversed from left to right.
 *   
 * == Examples ==
 *   
 *         5
 * 
 *       /    \
 *
 *     3        8
 *
 *   /   \        \
 *
 *  1     4        11
 * 
 *  the result is [ [5], [3, 8], [1, 4, 11] ]
 * 
 * == Corner Cases == 
 * What if the binary tree is null? Return an empty list of list in this case.
 * 
 * == Updates ==
 * September 3, 2018: DSF way
 * 
 */

package binaryTreeRelated;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GetKeysLayerByLayer {
	
	/* ----------------------< Iterative & BFS Method >-------------------------*/
	public List<List<Integer>> getKeysLayerByLayer(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();		
		if (root == null) { // corner case
			return result;
		}
		Queue<TreeNode> queue = new LinkedList<>();	
		queue.offer(root);
		while (!queue.isEmpty()) {
			int size = queue.size();
			List<Integer> list = new LinkedList<>();
			for (int i = 0; i < size; i++) {
				TreeNode curr = queue.poll();	
				
				list.add(curr.value);				
				if (curr.left != null) {
					queue.offer(curr.left);				
				} 
				if (curr.right != null) {
					queue.offer(curr.right);
				}
			}
			result.add(list);
		}
		return result;
	}
	
	// Time Complexity: O(n);
	// Space Complexity: O(number of nodes of the layer with the largest number of nodes), worst case O(n/2) = O(n);
	
	/* ----------------------< Recursive & DFS Method >-------------------------*/
	public List<List<Integer>> leverOrderRecursion(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();	
		DFS(root, result, 0);
		return result;
	}
	
	private void DFS(TreeNode root, List<List<Integer>> result, int level) {
		if (root == null) {
			return;
		}
		if (result.size() == level) {
			result.add(new ArrayList<Integer>());
		}
		result.get(level).add(root.value);
		DFS(root.left, result, level + 1);
		DFS(root.right, result, level + 1);
	}
	
	// Time Complexity: O(n);
	// Space Complexity: O(height), worst case O(n)
		
	/* ----------------------< test stub >-------------------------*/
	private static void print(List<List<Integer>> result) {
		System.out.print("\n");
		for (List<Integer> list : result) {
			for (int item : list) {
				System.out.print(item + " ");
			}
			System.out.print("\n");
		}
	}

	public static void main(String[] args) {
		
		GetKeysLayerByLayer testObj = new GetKeysLayerByLayer();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("\n---< Test Case 1 >---");
		
		int[] arr1 = {10, 5, 15, 3, 6, 13, 20};
		TreeNode root1 = TreeNode.genBst(arr1);
		TreeNode.printInOrder(root1);
		
		List<List<Integer>> result1a = testObj.getKeysLayerByLayer(root1);
		print(result1a);
		
		List<List<Integer>> result1b = testObj.leverOrderRecursion(root1);
		print(result1b);
		
		/* Test Case 2 */
		System.out.println("\n---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
	
}

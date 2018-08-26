/*
 * Created Date: June 12, 2018
 * 
 * Question - Get Keys In Binary Tree Layer By Layer:
 *   Get the list of list of keys in a given binary tree layer by layer. 
 *   Each layer is represented by a list of keys and the keys are traversed from left to right.
 * 
 *   Example: 
 *   
 *   Examples
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
 * Corner Cases:
 *   What if the binary tree is null? Return an empty list of list in this case.
 * 
 */

package binaryTreeRelated;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GetKeysLayerByLayer {
	
	public static List<List<Integer>> getKeysLayerByLayer(TreeNode root) {
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
				System.out.print(curr.value + " "); 
				
				if (curr.left != null) {
					queue.offer(curr.left);				
				} 
				if (curr.right != null) {
					queue.offer(curr.right);
				}
			}
			System.out.print("\n"); 
			result.add(list);
		}
		return result;
	}
	
	// Time Complexity: O(n);
	// Space Complexity: O(number of nodes of the layer with the largest number of nodes), worst case O(n/2) = O(n);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("\n---< Test Case 1 >---");
		
		int[] arr1 = {10, 5, 15, 3, 6, 13, 20};
		TreeNode root1 = TreeNode.genBst(arr1);
		TreeNode.printInOrder(root1);
		
		getKeysLayerByLayer(root1);
		
		/* Test Case 2 */
		System.out.println("\n---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
	
}

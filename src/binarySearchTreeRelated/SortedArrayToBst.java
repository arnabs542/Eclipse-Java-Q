/*
 * Created Date: June 11, 2018
 * 
 * Question - Sorted Array To Binary Search Tree (medium)
 *   Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 *      
 *   Example: 
 *      Given ascending order array: [1, 3, 4, 5, 8, 11], return Binary Search Tree is:
 *      
 *            4
 *            
 *       /        \
 *       
 *       1          8
 *       
 *        \      /     \
 *        
 *         3     5      11
 *  
 * 
 */

package binarySearchTreeRelated;

public class SortedArrayToBst {
	

	public static TreeNode sortedArrayToBST(int[] arr) {
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
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		/* Test Case 0 */
		System.out.println("\n---< Test Case 0 >---");
		
		TreeNode root0 = sortedArrayToBST(null);
		TreeNode.printInOrder(root0);
		
		/* Test Case 1 */
		System.out.println("\n---< Test Case 1 >---");
		
		/*
		 *       2
		 *     /   \
		 *    1     3
		 * 
		 * */
		
		int[] arr1 = {1, 2, 3};
		
		TreeNode root1 = sortedArrayToBST(arr1);
		TreeNode.printInOrder(root1);
		
		/* Test Case 2 */
		System.out.println("\n---< Test Case 2 >---");
		
		int[] arr2 = {1, 3, 4, 5, 8, 11};
		
		/*
		 *             4           
		 *          /    \
		 *         1       8     
		 *          \     /  \    
		 *           3   5   11
		 */
		
		TreeNode root2 = sortedArrayToBST(arr2);
		TreeNode.printInOrder(root2);
		
		/* Test Case 3 */
		System.out.println("\n---< Test Case 3 >---");
		
        int[] arr3 = {1};
		
		TreeNode root3 = sortedArrayToBST(arr3);
		TreeNode.printInOrder(root3);
	}
}

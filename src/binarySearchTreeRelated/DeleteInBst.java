/*
 * Created Date: June 10, 2018
 * 
 * Question - Delete In Binary Search Tree
 *   Delete the target key K in the given binary search tree, 
 *   if the binary search tree contains K. 
 *   Return the root of the binary search tree.
 *   
 *   Find your own way to delete the node from the binary search tree, 
 *   after deletion the binary search tree's property should be maintained.
 * 
 * Updated:
 *   June 10, 2018: only come up with very cumbersome solution, too much cases, gave up...
 *   June 11, 2018: practice class 7
 *   Need to Review in one week!!!
 */

package binarySearchTreeRelated;

public class DeleteInBst {
	
	/* 
	 * delete 10:
	 * 
	 * case 1 - node to be deleted has no children:
	 * 
	 *     5                  5
	 *   /   \       -->    /   
	 *  3    10            3
	 *  
	 * case 2 - no left child: 
	 * 
	 *     5                  5
	 *   /   \       -->    /   \
	 *  3    10            3    15
	 *         \
	 *         15                   
	 *                            
	 * case 3 - no right child: 
	 * 
	 *     5                  5
	 *   /   \       -->    /   \
	 *  3    10            3     8
	 *       / 
	 *      8        
	 * 
	 * case 4.1 - has children, and the right child is the smallest node of right subtree
	 * 
	 * 	   5                  5
	 *   /   \       -->    /   \
	 *  3    10            3     15
	 *      /  \                /  \
	 *     8   15              8   20
	 *           \
	 *           20
	 *           
	 * case 4.2 
	 * 
	 * 	   5                  5
	 *   /   \       -->    /   \
	 *  3    10            3     13
	 *      /  \                /  \
	 *     8   15              8    15
	 *        /   \                /  \ 
	 *       13   20              14   20 
	 *        \
	 *         14
	 */
	
	public static TreeNode delete(TreeNode root, int key) {
		// corner case
		if (root == null) {
		    return null;
		}
		// base case
		if (root.value == key) { 
			if (root.left == null) { // case 2, no left child
				return root.right;
			} else if (root.right == null) { // case 3, no right child, case 2 & 3 is equal to case 1
				return root.left;
			} else if (root.right.left == null) { // case 4.1, right child is the smallest node of right subtree
				root.right.left = root.left;
				return root.right;
			} else { // case 4.2
				TreeNode successor = deleteSmallest(root, key);
				successor.left = root.left;
				successor.right = root.right;
				return successor;
			}		  
		} else if (root.value < key) {
			root.right = delete(root.right, key);
			return root; 
		} else {
			root.left = delete(root.left, key);
			return root; 
		}      
	}
	
	private static TreeNode deleteSmallest(TreeNode root, int key) {
		TreeNode pre = root;
		while (root.left != null) {
			pre = root;
			root = root.left;			
		}
		pre.left = root.right;
		return root;
	}
	

	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		/* Test Case 0 */
		System.out.println("\n---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("\n---< Test Case 1 >---");
		
		/*
		 *       3
		 *     /   \
		 *    1     2
		 * 
		 * */
		
		int[] arr1 = {3, 1, 2};
		TreeNode root1 = TreeNode.genBst(arr1);
		TreeNode.printInOrder(root1);
		
		root1 = delete(root1, 3);
		TreeNode.printInOrder(root1);
		
		/* Test Case 2 */
		System.out.println("\n---< Test Case 2 >---");
		
		/*
		 *  	   5                  5
		 *   /   \       -->    /   \
		 *  3    10            3     13
		 *      /  \                /  \
		 *     8   15              8    15
		 *        /   \                /  \ 
		 *       13   20              14   20 
		 *        \
		 *         14
		 */  
		
		int[] arr2 = {5, 3, 10, 8, 15, 13, 20, 14};
		TreeNode root2 = TreeNode.genBst(arr2);
		TreeNode.printInOrder(root2);
		
		root2 = delete(root2, 10);
		TreeNode.printInOrder(root2);
		
		/* Test Case 3 */
		System.out.println("\n---< Test Case 3 >---");
		
		int[] arr3 = {3, 1, 2};
		TreeNode root3 = TreeNode.genBst(arr3);
		TreeNode.printInOrder(root3);
		
		root3 = delete(root3, 4);
		TreeNode.printInOrder(root3);	
	}
}

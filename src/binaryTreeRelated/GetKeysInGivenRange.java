/*
 * Created Date: June 8, 2018
 * 
 * Question - Get Keys In Binary Search Tree In Given Range:
 *   Get the list of keys in a given binary search tree in a given range[min, max] in ascending order, 
 *   both min and max are inclusive.
 *    
 *   Example: 
 *   
 *          5
 *        /    \
 *      3        8
 *    /   \        \
 *   1     4        11
 *
 * get the keys in [2, 5] in ascending order, result is  [3, 4, 5]
 *   
 */

package binaryTreeRelated;

public class GetKeysInGivenRange {
	
	public static void getVal(TreeNode root, int min, int max) {	
		if (root == null) {
			return;
		}	
		if (root.value > min) {
			getVal(root.left, min, max);
		} 	
		if (root.value >= min && root.value <= max) {
			System.out.print(root.value + " ");
		}		
		if (root.value < max) {
			getVal(root.right, min, max);
		}
	}
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("\n---< Test Case 1 >---");
		
		int[] arr1 = {1, 2, 3, 4, 5};
		TreeNode root1 = TreeNode.genBst(arr1);
		TreeNode.printInOrder(root1);
		
		getVal(root1, 2, 5);
		
		/* Test Case 2 */
		System.out.println("\n---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}

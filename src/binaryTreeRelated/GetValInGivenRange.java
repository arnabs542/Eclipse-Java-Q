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

public class GetValInGivenRange {
	
	public void getVal(TreeNode root, int min, int max) {	
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
}

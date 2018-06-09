/*
 * Created Date: May 24, 2018
 * 
 * Question - Tweaked Identical Binary Trees:
 *   Determine whether two given binary trees are identical assuming any number of ‘tweak’s are allowed. 
 *   A tweak is defined as a swap of the children of one node in the tree.
 *    
 *   Example: 
 *   
 *     5
 *   
 *   /    \
 *   
 *  3      8
 *      
 *        /   \
 *       1      4
 *       
 *       and
 *       
 *       5
 *       
 *    /    \
 *    
 *  8        3
 *  
 *          /   \
 *           
 *         1     4
 * 
 *  the two binary trees are tweaked identical.
 *  
 * Updated:
 *   June 9: Time Complexity Analysis
 * 
 */

package binaryTreeRelated;

public class IsTweakedIdentical {
	
	public boolean isTweakedIdentical(TreeNode one, TreeNode two) {
		
		if(one == null && two == null) return true;
		if(one == null || two == null) return false;
		
		if(one.value != two.value) return false;
			
		// one.left, two.left && one.right, two.right || one.left, two.right && one.right, two.left		
		return isTweakedIdentical(one.left, two.left) && isTweakedIdentical(one.right, two.right)
				|| isTweakedIdentical(one.left, two.right) && isTweakedIdentical(one.right, two.left);
	}
	
	/*	
	                                 (one, two)                 
	     	       
	 	/                       |                        |                   \
	 
   (one.left, two.left)  (one.right, two.right)  (one.left, two.right)  (one.right, two.left)    4 
   
   / | | \                     / | | \                 / | | \                / | | \            4 ^ 2
    ...
    
                                                                                                 4 ^ 3
                                                                                                 4 ^ height = 4 ^ logn (worst case)
   
	Time Complexity: O( 4^log_2(n) ) = O( 2^log_2(n^2) ) = O( n^2)
	Space Complexity: O(height), worst case: O(n)

	*/

}

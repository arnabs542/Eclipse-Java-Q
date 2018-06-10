/*
 * Created Date: June 9, 2018
 * 
 * Question - Insert In Binary Search Tree
 *   Insert a key in a binary search tree 
 *   if the binary search tree does not already contain the key. 
 *   Return the root of the binary search tree.
 *   
 *   Example: 
 *   
 *         5
 *         
 *       /    \
 *             
 *      3        8
 *             
 *    /   \
 *             
 *    1     4
 *    
 *    insert 11, the tree becomes
 *    
 *          5
 *     
 *        /    \
 *         
 *      3        8
 *            
 *    /   \        \
 *            
 *   1     4        11
 * 
 */


package binarySearchTreeRelated;

public class InsertInBinarySearchTree {
	
	public TreeNode insert(TreeNode root, int key) {		
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
	
	// how to do it recursively ?
}

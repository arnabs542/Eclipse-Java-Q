/*
 * == Created Date == 
 * Jan 26, 2019
 * 
 * == Question - Merge Two Binary Trees == 
 *   
 * == Notes ==
 * LeetCode 617(E)
 */

package binaryTreeRelated;

public class MergeTwoBinaryTrees {

    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null;
        }
            
        if (t1 == null) {
            TreeNode root = new TreeNode(t2.value);
            root.left = mergeTrees(null, t2.left);
            root.right = mergeTrees(null, t2.right);
            return root;
        }
        
        if (t2 == null) {
            TreeNode root = new TreeNode(t1.value);
            root.left = mergeTrees(t1.left, null);
            root.right = mergeTrees(t1.right, null);
            return root;
        }
        
        TreeNode root = new TreeNode(t1.value + t2.value);
        root.left = mergeTrees(t1.left, t2.left);
        root.right = mergeTrees(t1.right, t2.right);
        return root;
    }
    
	/* ----- < Solution - Optimized from above > -----
	 * Time Complexity: O(N + M);
	 * Space Complexity: O(log(Height1) + log(Height2)); 
	 * 
	 * */
    public TreeNode mergeTreesII(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null;
        }
            
        if (t1 == null) {
            return t2;
        }
        
        if (t2 == null) {
            return t1;
        }
        
        TreeNode root = new TreeNode(t1.value + t2.value);
        root.left = mergeTrees(t1.left, t2.left);
        root.right = mergeTrees(t1.right, t2.right);
        return root;
    }
	
}

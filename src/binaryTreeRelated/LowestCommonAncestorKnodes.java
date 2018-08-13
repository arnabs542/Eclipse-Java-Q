/*
 * Created Date: August 13, 2018
 * 
 * Question - Lowest Common Ancestor IV:
 *   Given K nodes in a binary tree, find their lowest common ancestor.
 *   
 *   
 *   Example: 
 * 
 */

package binaryTreeRelated;

import java.util.List;

public class LowestCommonAncestorKnodes {
	
	public TreeNode lowestCommonAncestor(TreeNode root, List<TreeNode> nodes)  {
		if (root == null || nodes.contains(root)) {
			return root;
		}
		TreeNode left = lowestCommonAncestor(root.left, nodes);
		TreeNode right = lowestCommonAncestor(root.right, nodes);
		
		if (left != null && right != null) {
			return root;
		} 
		return left == null ? right : left;
	}	
}

/*
 * == Created Date ==
 * September 7, 2018
 * 
 * == Question - Construct Binary Tree from Inorder and Postorder Traversal ==
 * Given inorder and postorder traversal of a tree, construct the binary tree.  
 *  
 * == Similar Question == 
 * Construct Binary Tree from In-order and Pre-order Traversal
 * Construct Binary Tree from In-order and Level-order Traversal
 * 
 */

package binaryTreeRelated;

import java.util.HashMap;
import java.util.Map;

public class ConstructFromInorderAndPostorderTraversal {
	
    /*
	    5 
	  /   \
	 3     8
	/ \      \
	1   4     10
	
	index:    0       1      2       3       4       5
	
	postOrder: 1      4      3       10      8       5
	                                            postRight
	         
	inOrder:  1       3      4       5       8       10
	         inLeft               inMid          inRight
     */

	public TreeNode buildTree(int[] inorder, int[] postorder) {
	    Map<Integer, Integer> indexMap = getIndexMap(inorder);
	    return buildTree(0, inorder.length - 1, postorder, postorder.length - 1, indexMap);
	}

	private TreeNode buildTree(int inLeft, int inRight,
	                           int[] post, int postRight, Map<Integer, Integer> indexMap) {
	    if (inLeft > inRight || postRight > post.length - 1) {
	        return null;
	    }
	    TreeNode root = new TreeNode(post[postRight]);
	    int inMid = indexMap.get(root.value);
	    int rightHalfSize = inRight - inMid;
	    root.left = buildTree(inLeft, inMid - 1,
	                          post, postRight - rightHalfSize - 1, indexMap);
	    
	    root.right = buildTree(inMid + 1, inRight,
	                           post, postRight - 1, indexMap);
	    return root;
	}
	
	private Map<Integer, Integer> getIndexMap(int[] inOrder) {
	    Map<Integer, Integer> indexMap = new HashMap<>();
	    for (int i = 0; i < inOrder.length; i++) {
	        indexMap.put(inOrder[i], i);
	    }
	    return indexMap;
	}

}

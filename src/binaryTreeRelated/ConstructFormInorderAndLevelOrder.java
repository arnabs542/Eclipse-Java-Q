/*
 * == Created Date ==
 * September 7, 2018
 * 
 * == Question - Construct Binary Tree from In-order and Level-order Traversal ==
 * Given the level-order and in-order traversal sequence of a binary tree, reconstruct the original tree. 
 *   
 * == Example == 
 * level-order traversal = {5, 3, 8, 1, 4, 11}
 * in-order traversal = {1, 3, 4, 5, 8, 11}
 * The corresponding binary search tree is
 * 
 *        5       
 *     /    \
 *    3       8
 *  /   \       \
 * 1      4      11
 *  
 * == Similar Question == 
 * Reconstruct Binary Search Tree With Pre-order Traversal
 * 
 */

package binaryTreeRelated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConstructFormInorderAndLevelOrder {
	
    /*
		    5 
		  /   \
		 3     8
		/ \      \
	   1   4     10
		
		index:         0       1      2       3       4       5
		
		levelOrder: 	   5       3      8       1       4       10
		              root     ->                                   
		         
		inOrder:    	   1       3      4       5       8       10
		                                     root
    */
	
	public TreeNode reconstruct(int[] inOrder, int[] levelOrder) {
	    Map<Integer, Integer> indexMap = getIndexMap(inOrder);
	    List<Integer> level = new ArrayList<>();
	    for (int item : levelOrder) {
	    		level.add(item);
	    }
	    return buildTree(level, indexMap);
	}
	
	private TreeNode buildTree(List<Integer> level, Map<Integer, Integer> indexMap) {
		if (level.isEmpty()) {
			return null;
		}
		TreeNode root = new TreeNode(level.remove(0)); 
		
		List<Integer> left = new ArrayList<>();
		List<Integer> right = new ArrayList<>();
		
		int rootIndex = indexMap.get(root.value);
		for (int ele : level) {
			if (indexMap.get(ele) < rootIndex) {
				left.add(ele);
			} else {
				right.add(ele);
			}
		}
		
	    root.left = buildTree(left, indexMap);
		root.right = buildTree(right, indexMap);
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

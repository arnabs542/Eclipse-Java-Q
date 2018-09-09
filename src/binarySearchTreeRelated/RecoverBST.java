/*
 * == Created Date ==
 * September 7, 2018
 * 
 * == Question - Recover Binary Search Tree ==
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * Recover the tree without changing its structure.
 *   
 * == Example == 
 * Input: [1,3,null,null,2]
 * 
 *    1
 *   /
 *  3
 *   \
 *     2
 *     
 * Output: [3,1,null,null,2]
 * 
 *    3
 *   /
 *  1
 *   \
 *    2
 *   
 * == Notes == 
 * Solution1, Time Complexity O(nlogn)
 * Do a in-order traversal, record the in-order node array and value array.
 * Sort the value array and then change the value of every node using the sorted array.
 * 
 * There is a better solution in the Internet of O(n) time.
 * 
 */

package binarySearchTreeRelated;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import binaryTreeRelated.TreeNode;

public class RecoverBST {
	
	/* ----------------------< Solution1, TC = O(nlogn) >-------------------------*/
	
    public void recoverTree(TreeNode root) {
        List<TreeNode> nodeList = new ArrayList<>();
        List<Integer> valList = new ArrayList<>(); 
        inOrder(root, nodeList, valList);
        Collections.sort(valList); 
        for (int i = 0; i < nodeList.size(); i++) {
            nodeList.get(i).value = valList.get(i);
        }
    }
    
    private void inOrder(TreeNode root, List<TreeNode> nodeList, List<Integer> valList) {
        if (root == null) {
            return;
        }
        inOrder(root.left, nodeList, valList);
        nodeList.add(root);
        valList.add(root.value);
        inOrder(root.right, nodeList, valList);
    }	
    
	// Time Complexity: O(nlogn);
	// Space Complexity: O(n);
}

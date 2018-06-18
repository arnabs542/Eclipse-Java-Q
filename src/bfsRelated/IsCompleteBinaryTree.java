/*
 * Created Date: June 17, 2018
 * 
 * Question - Check If Binary Tree Is Completed:
 *   Check if a given binary tree is completed. 
 *   A complete binary tree is one in which every level of the binary tree is completely filled 
 *     except possibly the last level.
 *   Furthermore, all nodes are as far left as possible.
 *   
 *  Corner Cases:
 *    What if the binary tree is null? Return true in this case.
 *     
 * 
 */

package bfsRelated;
import java.util.LinkedList;
import java.util.Queue;

import binaryTreeRelated.TreeNode;

public class IsCompleteBinaryTree {
	
	public boolean checkCompleted(TreeNode root) {
		if (root == null) { // corner case
			return true;
		}
		Queue<TreeNode> queue = new LinkedList<>();
		boolean flag = false; // if flag is set true, we should not see any node with child
		queue.offer(root);
		while (!queue.isEmpty()) {
			TreeNode cur = queue.poll(); // expand a node from queue
			
			if (cur.left == null) { // if a node has only one child, set the flag to true
				flag = true;
			} else if (flag) { // if the flag is set, and we still see a node with child
				return false;
			} else { // the flag is not set and the left child present, generate its left child
				queue.offer(cur.left);
			}
			
			// same logic applied to the right child
			if (cur.right == null) {
				flag = true;
			} else if (flag) {
				return false;
			} else {
				queue.offer(cur.right);
			}
		}
		return true;
	}

}

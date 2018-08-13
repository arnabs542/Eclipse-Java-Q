/*
 * Created Date: August 11, 2018
 * 
 * Question - Get Keys In Binary Tree Layer By Layer Zig-Zag Order:
 *   Get the list of keys in a given binary tree layer by layer in zig-zag order.
 *    
 *   Example: 
 *   
 *           5
 *        /    \
 *      3        8
 *    /   \        \   
 *   1     4        11 
 *   
 *   The result is [5, 3, 8, 11, 4, 1]
 * 
 */

package binaryTreeRelated;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class GetKeysLayerByLayerZigZagOrder {
	
//	 *           1
//	 *        /    \
//	 *      2        3
//	 *    /   \     /  \   
//	 *   7     6   5    4 
//      /     /  \     /  \
//     8     9    10  11  12	
	
	// 1. poll last, expand first right, left
	// 2 3 || 1
	
	// 2. poll first, expand last left, right
	// 2 3 || 7 6 5 4 
	
	// 3. poll last, expand first right, left
	// 8 9 10 11 12 || 7 6 5 4 
		
	public List<Integer> zigZag(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		Deque<TreeNode> deque = new LinkedList<>();
		deque.addFirst(root);
		boolean flag = true;
		while (!deque.isEmpty()) {
			for (int i = deque.size(); i > 0; i--) {
				if (flag) {
					TreeNode cur = deque.pollLast();
					res.add(cur.value);
					if (cur.right != null) {
						deque.addFirst(cur.right);
					}
					if (cur.left != null) {
						deque.addFirst(cur.left);
					}
				} else {
					TreeNode cur = deque.pollFirst();
					res.add(cur.value);	
					if (cur.left != null) {
						deque.addLast(cur.left);
					}
					if (cur.right != null) {
						deque.addLast(cur.right);
					}		
				}				
			}
			flag = !flag;			
		}
		return res;
	}  
}

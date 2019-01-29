/*
 * == Created Date ==
 * Nov 12, 2018
 * 
 * == Question - ==
 * Populating Next Right Pointers in Each Node
 * 
 * == Notes == 
 * LeeCode 116 
 * 
 */

package binaryTreeRelated;

import java.util.ArrayDeque;
import java.util.Deque;

class TreeLinkNode {
     int val;
     TreeLinkNode left, right, next;
     TreeLinkNode(int x) { val = x; }
}

public class PopulatingNextRightPointers {
	
    public void connectI(TreeLinkNode root) {
        Deque<TreeLinkNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (root != null && !queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeLinkNode cur = queue.poll();               
                if (queue.isEmpty()) {
                    cur.next = null;
                } else {
                    cur.next = queue.peek();
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
    }
    
	// Time Complexity: O(n);
	// Space Complexity: O(n);
	
    public void connectII(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        // keep the dummy as the start of a linekd list to linked next for children level for the 
        TreeLinkNode dummy = new TreeLinkNode(0);
        TreeLinkNode cur = dummy; 
        
        while (root != null) {
            if (root.left != null) { // linked the left child 
                cur.next = root.left;
                cur = cur.next;
            }
            if (root.right != null) { // linked the right child 
                cur.next = root.right;
                cur = cur.next;
            }
            root = root.next; // update root to its next node
            if (root == null) { 
                // if root doesn't have a next node, we finishing linking the child node for this level
                // use the head of the linked list as the new root, and delink the dummy
                root = dummy.next;
                dummy.next = null;
                cur = dummy;
            }
        }
    }
    
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		System.out.print(1);
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}

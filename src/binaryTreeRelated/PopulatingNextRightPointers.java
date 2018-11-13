/*
 * == Created Date ==
 * Nov ?, 2018
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
    public void connect(TreeLinkNode root) {
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
    
	// Time Complexity: O(?);
	// Space Complexity: O(?);
	
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

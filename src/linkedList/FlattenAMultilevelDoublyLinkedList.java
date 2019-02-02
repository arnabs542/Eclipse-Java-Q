/*
 * == Created Date == 
 * Jan 26, 2019
 * 
 * == Question - Flatten A Multilevel Doubly Linked List == 
 * You are given a doubly linked list which in addition to the next and previous pointers, 
 *   it could have a child pointer, 
 *   which may or may not point to a separate doubly linked list. 
 * 
 * These child lists may have one or more children of their own, and so on, 
 *   to produce a multilevel data structure, as shown in the example below.
 * 
 * Flatten the list so that all the nodes appear in a single-level, 
 *   doubly linked list. You are given the head of the first level of the list.
 *   
 * == Example ==
 * Input:
 * 
 * 1---2---3---4---5---6--NULL
 *         |
 *         7---8---9---10--NULL
 *             |
 *             11--12--NULL
 *
 * Output:
 * 1-2-3-7-8-11-12-9-10-4-5-6-NULL  
 * 
 * == Notes ==
 * LeetCode 430(M)
 */

package linkedList;

import java.util.ArrayDeque;
import java.util.Deque;

public class FlattenAMultilevelDoublyLinkedList {

    class Node {
	    public int val;
	    public Node prev;
	    public Node next;
	    public Node child;

	    public Node() {}

	    public Node(int _val,Node _prev,Node _next,Node _child) {
	        val = _val;
	        prev = _prev;
	        next = _next;
	        child = _child;
	    }
	};
	
	/* ----- < Solution 1 - Recursion > -----
	 * Time Complexity: O(N), N is the number total nodes 
	 * Space Complexity: O(L); L is the deepest children level
	 * 
	 * */
    public Node flatten(Node head) {
        flattenTail(head);
        return head;
    }
    
    private Node flattenTail(Node head) {
        if (head == null) { // base case
            return head;
        }
        if (head.child == null && head.next == null) { // reach a tail, base case
            return head;
        }
        Node next = head.next;
        if (head.child != null) {
            Node childTail = flattenTail(head.child);
            
            head.next = head.child;        
            head.child.prev = head;
            
            head.child = null;
            
            if (next != null) {
                childTail.next = next;
                next.prev = childTail;
            }
        }
        return flattenTail(next);
    }

    
	/* ----- < Solution 2 - Iterative using stack > -----
	 * Time Complexity: O(N), N is the number total nodes 
	 * Space Complexity: O(N); 
	 * 
	 * Similar to the post order tree traversal
	 * */
     public Node flattenII(Node head) {
         if (head == null) {
             return head;
         }
         Node dummy = new Node();
         Node cur = dummy;
        
         Deque<Node> stack = new ArrayDeque<>();
         stack.offer(head);
        
         while (!stack.isEmpty()) {
             Node top = stack.pop();
             cur.next = new Node(top.val, cur, null, null); // public Node(int _val, Node _prev, Node _next, Node _child) 
             cur = cur.next;
            
             if (top.next != null) {
                 stack.push(top.next);
             }
            
             if (top.child != null) {
                 stack.push(top.child);
             }
         }
         dummy.next.prev = null; // set the prev of new head node as null
         return dummy.next;
     }
}

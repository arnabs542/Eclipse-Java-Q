/*
 * Created Date: May 14, 2018
 * 
 * Question - Reverse a singly-linked list iteratively and recursively
 * 
 * Examples:
 *   L = null, return null
 *   L = 1 -> null, return 1 -> null
 *   L = 1 -> 2 -> 3 -> null, return 3 -> 2 -> 1 -> null
 * 
 * Updated:
 *  June 5, 2018: Review
 * 	
 */

package myLinkedList;

public class ReverseLinkedList {
	
	public static ListNode iterativeReverse(ListNode head) {
		ListNode pre = null;
		ListNode curr = head;
		
		while (curr != null) {
			ListNode next = curr.next;
			curr.next = pre;
			pre = curr;
			curr = next;
		}
		return pre;
	}
	// Time Complexity: O(n);
	// Space Complexity: O(1);
	
	public static ListNode recursiveReverse(ListNode head) {
		if(head == null || head.next == null) return head;
		ListNode newHead = recursiveReverse(head.next);
				
		head.next.next = head;
		head.next = null;
		return newHead;
	}
	// Time Complexity: O(n);
	// Space Complexity: O(1);
	
	public static void main(String[] args) {	
		
		ListNode head = new ListNode(1);
		head.next = new ListNode(3);
		head.next.next = new ListNode(5);
		head.next.next.next = new ListNode(7);
		head.next.next.next.next = new ListNode(9);
		head.printList(head);
		
		ListNode newHead = iterativeReverse(head);
		newHead.printList(newHead);	
		
		newHead = recursiveReverse(newHead);
		newHead.printList(newHead);	
	}	
}

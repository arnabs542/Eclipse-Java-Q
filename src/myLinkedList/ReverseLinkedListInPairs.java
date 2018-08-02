/*
 * Created Date: August 2, 2018
 * 
 * Question - Reverse Linked List In Pairs (medium):
 *   Reverse pairs of elements in a singly-linked list.
 *   
 *   Example: 
 *     L = null, after reverse is null
 *     L = 1 -> null, after reverse is 1 -> null
 *     L = 1 -> 2 -> null, after reverse is 2 -> 1 -> null
 *     L = 1 -> 2 -> 3 -> null, after reverse is 2 -> 1 -> 3 -> null
 *     
 * Follow up:
 *   Reverse in 3 or more nodes in pairs
 * 
 */

package myLinkedList;

public class ReverseLinkedListInPairs {
	
	public ListNode reverseInPairs(ListNode head) {
		if (head == null || head.next == null) {
	      return head;
	    }
	    ListNode nextNode = head.next;
	    head.next = reverseInPairs(head.next.next);
	    nextNode.next = head;
	    return nextNode;
	}
}

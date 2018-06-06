/*
 * Created Date: May 21,2018
 * 
 * Question - ReOrder Linked List (hard): 
 *  Reorder the given singly-linked list N1 -> N2 -> N3 -> N4 -> … -> Nn -> null 
 *  to be N1- > Nn -> N2 -> Nn-1 -> N3 -> Nn-2 -> … -> null
 * 
 * 	Example: L = null, is reordered to null
 *		     L = 1 -> null, is reordered to 1 -> null
 *			 L = 1 -> 2 -> 3 -> 4 -> null, is reordered to 1 -> 4 -> 2 -> 3 -> null
 *			 L = 1 -> 2 -> 3 -> null, is reordred to 1 -> 3 -> 2 -> null
 *
 * Updated:
 *   June 6, 2018: Review
 * 
 */

package myLinkedList;

public class ReOrderLinkedList {
	
	public static ListNode reorderLinkedList(ListNode head) {
		// corner case
		if (head == null) {
			return head;
		}
			
		// find middle node
		ListNode mid = findMidNode(head);
		
		// delink the first part from the second part
		ListNode two = mid.next;
		mid.next = null; 
		
		// reverse the second part 
		two = reverse(two);
		
		// merge the two parts
		return merge(head, two);
	}
	
	private static ListNode merge(ListNode one, ListNode two) {
		ListNode dummyHead = new ListNode(0);
		ListNode curr = dummyHead;
		while(one != null || two != null) {
			if(one == null) {
				curr.next = two;
				break; 
			} else if (two == null) {
				curr.next = one;
				break;
			} else {
				curr.next = one;
				one = one.next;
				curr = curr.next;
				
				curr.next = two;
				two = two.next;
				curr = curr.next;
			}
		}
		return dummyHead.next;
	}
	
	private static ListNode reverse(ListNode head) {
		ListNode pre = null;
		while (head != null) {
			ListNode next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		return pre;		
	}
	
	private static ListNode findMidNode(ListNode head) {
		ListNode fast = head;
		ListNode slow = head;
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	} 
	
	public static void main(String[] args) {
		
		int[] a = {1, 2, 3, 4, 5, 6, 7, 8};
		ListNode head = ListNode.genLinkedList(a);
		head.printList(head);

		reorderLinkedList(head);
		head.printList(head);
	}	
}

// Mistake - in function merge(), Change value of ptrTwo but didn't realize: 
//	curr.next = two; 
//	curr = curr.next;				
//	curr.next = one;
//	curr = curr.next;
//	two = two.next;
//	one = one.next;

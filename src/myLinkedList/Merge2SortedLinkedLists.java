/*
 * Created Date: May 17, 2018
 * 
 * Question - Merge Two Sorted Linked Lists (easy):
 *   Merge two sorted lists into one large sorted list.
 *   
 *   Examples:
 *   L1 = 1 -> 4 -> 6 -> null, L2 = 2 -> 5 -> null, merge L1 and L2 to 1 -> 2 -> 4 -> 5 -> 6 -> null
 *   L1 = null, L2 = 1 -> 2 -> null, merge L1 and L2 to 1 -> 2 -> null
 *   L1 = null, L2 = null, merge L1 and L2 to null
 *   
 * Updated:
 *   June 6, 2018: Review
 * 
 */

package myLinkedList;

public class Merge2SortedLinkedLists {
	
	public static ListNode merge(ListNode one, ListNode two) {
		
		ListNode dummyHead = new ListNode(0);
		ListNode curr = dummyHead;
		
		while (one != null || two != null) {
			if (one == null) {
				curr.next = two;
				break;
			} else if(two == null) {
				curr.next = one;
				break;
			} else {
				if (one.value <= two.value) {
					curr.next = one;
					one = one.next;
					curr = curr.next;
				} else {
					curr.next = two;
					two = two.next;
					curr = curr.next;
				}
			}
		}
		return dummyHead.next;
	}
	
	public static void main(String[] args) {	
		
		ListNode printHelper = new ListNode(0);
		
		/* Test Case 0 */	  
		ListNode one1 = null;
		ListNode two1 = null;
 
		ListNode newhead1 = merge(one1, two1);
		printHelper.printList(newhead1);
		
		/* Test Case 2 */	 
		ListNode head = new ListNode(1);
		head.next = new ListNode(3);
		head.next.next = new ListNode(5);
		head.next.next.next = new ListNode(7);
		head.next.next.next.next = new ListNode(9);

		
		ListNode head2 = new ListNode(2);;
		head2.next = new ListNode(4);
		head2.next.next = new ListNode(6);
		head2.next.next.next = new ListNode(8);
		head2.next.next.next.next = new ListNode(10);
		
		ListNode newhead = merge(head, head2);
		newhead.printList(newhead);	
	}	
}

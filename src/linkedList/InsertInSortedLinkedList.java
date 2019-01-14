/*
 * Created Date: May 17, 2018
 * 
 * Question - Insert In Sorted Linked List:
 *   Insert a value in a sorted linked list.
 *     
 *   Example: 
 *     L = null, insert 1, return 1 -> null
 *     L = 1 -> 3 -> 5 -> null, insert 2, return 1 -> 2 -> 3 -> 5 -> null
 *     L = 1 -> 3 -> 5 -> null, insert 3, return 1 -> 3 -> 3 -> 5 -> null
 *   
 *   Mirror Question:
 *     Linked List Insert At Index
 *   
 * Updated:
 *   June 9, 2018: Use dummy head
 * 
 */

package linkedList;

public class InsertInSortedLinkedList {
	
	public static ListNode insert(ListNode head, int val) {
		ListNode dummyHead = new ListNode(0);
		ListNode curr = dummyHead;
		while (head != null) {
			if (val <= head.value) {
				curr.next = new ListNode(val);
				curr.next.next = head;
				return dummyHead.next;
			} else {
				curr.next = head;
				head = head.next;
				curr = curr.next;
			}
		}
		curr.next = new ListNode(val);
		return dummyHead.next;
	}
	
	// Time Complexity: O(n);
	// Space Complexity: O(1);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		ListNode head0 = insert(null, 1);
		head0.printList(head0);
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		int[] arr1 = {2, 3, 4, 5};
		ListNode head1 = ListNode.genLinkedList(arr1);
		head1.printList(head1);
		
		head1 = insert(head1, 1);
		head1.printList(head1);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
	}
}

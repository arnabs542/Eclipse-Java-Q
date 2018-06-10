/*
 * Created Date: May 31, 2018
 * 
 * Question - Linked List Insert At Index:
 *   Insert a new element at a specific index in the given linked list. 
 *   The index is 0 based, and if the index is out of the list's scope, 
 *   you do not need to do anything.
 *     
 *   Example: 
 *     1 -> 2 -> 3 -> null, insert 4 at index 3, --> 1 -> 2 -> 3 -> 4 -> null
 *     1 -> 2 -> null, insert 4 at index 0, --> 4 -> 1 -> 2 -> null
 *   
 *   Mirror Question:
 *     Insert In Sorted Linked List
 *   
 * Updated:
 *   June 9, 2018: Review
 * 
 */

package myLinkedList;

public class InsertAtIndex {
	
	public static ListNode insert(ListNode head, int index, int value) {
		// assume index belongs to set: [0, size of linked list + 1]
		ListNode dummyHead = new ListNode(0);
		ListNode curr = dummyHead;
		while (head != null && index > 0){
			curr.next = head;
			head = head.next;
			curr = curr.next;
			index--;
		}
		// index is out of the list's scope
		if (index > 0) {
			return dummyHead.next;
		}		
		curr.next = new ListNode(value);
		curr.next.next = head;
		return dummyHead.next;
	}
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		ListNode head0 = insert(null, 0, 1);
		head0.printList(head0); // expected: 1
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		int[] arr1 = {2, 3, 4, 5};
		ListNode head1 = ListNode.genLinkedList(arr1);
		head1.printList(head1);
		
		head1 = insert(head1, 0, 1);
		head1.printList(head1); // expected: 1 2 3 4 5
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		int[] arr2 = {2, 3, 4, 5};
		ListNode head2 = ListNode.genLinkedList(arr2);
		head1.printList(head2);
		
		head1 = insert(head2, 5, 1); // index is out of list's scope
		head1.printList(head1); // expected: 2 3 4 5
	}
}

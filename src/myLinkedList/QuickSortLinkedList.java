/*
 * Created Date: August 29, 2018
 * 
 * Question - Quick Sort Linked List:
 *   Given a singly-linked list, where each node contains an integer value, 
 *   sort it in ascending order. 
 *   The quick sort algorithm should be used to solve this problem.
 * 
 */

package myLinkedList;

public class QuickSortLinkedList {
	
	public ListNode quickSort(ListNode head) {
		if (head == null) {
			return head;
		}
		ListNode tail = head;
		while (tail.next != null) {
			tail = tail.next;
		}
		return quickSort(head, tail);
	}
	
	private ListNode quickSort(ListNode head, ListNode tail) {
		if (head == tail) {
			return head;
		}
		
		ListNode leftDummyHead = new ListNode(0);
		ListNode leftCur = leftDummyHead;
		
		ListNode rightCur = tail;
		
		int pivotVal = tail.value; // choose the last node as the pivot
		while (head != null && head != tail)  {
			if (head.value <= pivotVal) {
				leftCur.next = head;
				leftCur = leftCur.next;
			} else {
				rightCur.next = head;
				rightCur = rightCur.next;
			}
			head = head.next;
		}
		
		ListNode leftTail = leftCur;
		ListNode rightTail = rightCur;
		rightTail.next  = null;
		
		if (leftDummyHead.next != null) {
			leftTail.next = tail;
			ListNode newHead = quickSort(leftDummyHead.next, leftTail);
			ListNode rightHead = quickSort(tail.next, rightTail);

			tail.next = rightHead;
			return newHead;
		} else {
			return quickSort(tail, rightTail);
		}
	}
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		QuickSortLinkedList myList = new QuickSortLinkedList();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		ListNode head0 = new ListNode(0);
		
		head0 = myList.quickSort(null);
		System.out.println(head0);
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		int[] arr1 = {3, 2, 1};
		ListNode head1 = new ListNode(0);
		head1 = ListNode.genLinkedList(arr1);
		head1.printList(head1);
		
		head1 = myList.quickSort(head1);
		head1.printList(head1);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		int[] arr2 = {6, 3, 5, 4, 2, 1};
		ListNode head2 = new ListNode(0);
		head2 = ListNode.genLinkedList(arr2);
		head2.printList(head2);
		
		head2 = myList.quickSort(head2);
		head2.printList(head2);		
	}

}

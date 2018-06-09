/*
 * Created Date: June 9, 2018
 * 
 * Question - Merge Sort Linked List:
 *   Given a singly-linked list, where each node contains an integer value, 
 *   sort it in ascending order. 
 *   The merge sort algorithm should be used to solve this problem.
 * 
 */

package myLinkedList;

public class MergeSortLinkedList {
	
	public ListNode mergeSort(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		
		ListNode mid = findMidNode(head);
		ListNode two = mid.next;
		mid.next = null;
		
		ListNode one = mergeSort(head);
		two = mergeSort(two);
		return merge(one, two);
	}
	
	private ListNode merge(ListNode one, ListNode two) {
		ListNode dummyHead = new ListNode(0);
		ListNode curr = dummyHead;
		while (one != null || two != null) {
			if (one == null) {
				curr.next = two;
				break;
			} else if (two == null) {
				curr.next = one;
				break;
			} else if (one.value <= two.value) {
				curr.next = one;
				one = one.next;
				curr = curr.next;
			} else {
				curr.next = two;
				two = two.next;
				curr = curr.next;
			}
		}
		return dummyHead.next;
	}
	
	private ListNode findMidNode(ListNode head) {
		if (head == null) {
			return head;
		}
		ListNode slow = head;
		ListNode fast = head;
		while (fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
		}
		return slow;
	}
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		MergeSortLinkedList myList = new MergeSortLinkedList();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		ListNode head0 = new ListNode(0);
		
		head0 = myList.mergeSort(null);
		System.out.println(head0);
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		int[] arr1 = {3, 2, 1};
		ListNode head1 = new ListNode(0);
		head1 = ListNode.genLinkedList(arr1);
		head1.printList(head1);
		
		head1 = myList.mergeSort(head1);
		head1.printList(head1);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		int[] arr2 = {20, 18, 6, 9, 6, 32};
		ListNode head2 = new ListNode(0);
		head2 = ListNode.genLinkedList(arr2);
		head2.printList(head2);
		
		head2 = myList.mergeSort(head2);
		head2.printList(head2);
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}

}

/*
 * Created Date: May 18, 2018
 * 
 * Question - Middle Node Of Linked List:
 *   Find the middle node of a given linked list.
 *   
 *   Example: L = null, return null
 *     L = 1 -> null, return 1
 *     L = 1 -> 2 -> null, return 1
 *     L = 1 -> 2 -> 3 -> null, return 2
 *     L = 1 -> 2 -> 3 -> 4 -> null, return 2
 *   
 * Updated:
 *   June 5, 2018: Review
 * 
 */

package linkedList;

public class FindMidNode {
	
	public static ListNode findMidNode(ListNode head) {		
		// corner head
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
	
	public static void main(String[] args) {
		
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		head.next.next.next = new ListNode(4);
		head.next.next.next.next = new ListNode(5);
		head.printList(head);
		
		ListNode rst = findMidNode(head);
		System.out.println(rst.value);
		
		head.next.next.next.next.next = new ListNode(6);
		head.printList(head);
		rst = findMidNode(head);
		System.out.println(rst.value);
	}

}

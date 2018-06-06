/*
 * Created Date: May 29, 2018
 * 
 * Question - Partition Linked List (Medium) :
 *   Given a linked list and a target value T, partition it such that 
 *   all nodes less than T are listed before the nodes larger than or equal to target value T. 
 *   The original relative order of the nodes in each of the two partitions should be preserved.
 *   
 *   Example: 
 *     L = 2 -> 4 -> 3 -> 5 -> 1 -> null, T = 3, is partitioned to 2 -> 1 -> 4 -> 3 -> 5 -> null
 *   
 * Updated:
 *   June 5, 2018: Review
 * 
 */

package myLinkedList;

public class PartitionLinkedList {
	
	// Time Complexity: O(n)
	// Space Complexity: O(1)
	public static ListNode partitionLL(ListNode head, int target) {
	    ListNode curr = head;
	    ListNode dummyH1 = new ListNode(0);
	    ListNode curr1 = dummyH1;
	    ListNode dummyH2 = new ListNode(0);
	    ListNode curr2 = dummyH2;
	   
	    while(curr != null) {
	    	if(curr.value < target) {
	        curr1.next = curr;
	        curr1 = curr1.next;
	        curr = curr.next;       
	      } else {
	        curr2.next = curr;
	        curr2 = curr2.next;
	        curr = curr.next;
	      }     
	    }
	    curr2.next = null;
	    curr1.next = dummyH2.next;
	    return dummyH1.next;
	}
	
	
	public static void main(String[] args) {
		ListNode head = new ListNode(5);
		head.next = new ListNode(5);
		head.next.next = new ListNode(5);
		head.next.next.next = new ListNode(5);
		head.next.next.next.next = new ListNode(4);
		head.printList(head);		
		
		head = partitionLL(head, 5);
		head.printList(head);

		ListNode head1 = new ListNode(2);
		head1.next = new ListNode(4);
		head1.next.next = new ListNode(3);
		head1.next.next.next = new ListNode(5);
		head1.next.next.next.next = new ListNode(1);
		head1.printList(head1);		
		
		head1 = partitionLL(head1, 3);
		head1.printList(head1); // expected: 2 -> 1 -> 4 -> 3 -> 5 -> null		
	}
}


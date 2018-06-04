// Created Date: May 29,2018
// Question: Partition Linked List (Medium)
// Given a linked list and a target value T, partition it such that 
//		all nodes less than T are listed before the nodes larger than or equal to target value T. 
// 		The original relative order of the nodes in each of the two partitions should be preserved.
// 	eg: L = 2 -> 4 -> 3 -> 5 -> 1 -> null, T = 3, is partitioned to 2 -> 1 -> 4 -> 3 -> 5 -> null

package myMain;

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
	
	private static void printList(ListNode head) {
		if(head == null) {
			System.out.print("null \n");
			return;
		}
		
		ListNode curr = head;
		
		while(curr != null) {
			System.out.print(curr.value +" ");
			curr = curr.next;
		}		
		System.out.print("\n");
	}
	
	public static void main(String[] args) {
		ListNode head = new ListNode(5);
		head.next = new ListNode(5);
		head.next.next = new ListNode(5);
		head.next.next.next = new ListNode(5);
		head.next.next.next.next = new ListNode(4);
		printList(head);		
		
		head = partitionLL(head, 5);
		printList(head);		
	}
}

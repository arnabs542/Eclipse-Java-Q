/*
 * Created Date: May 18, 2018
 * 
 * Question - Check If Linked List Has A Cycle:
 * 	 Check if a given linked list has a cycle. 
 *   Return true if it does, otherwise return false.
 *   
 * Updated:
 *   June 5, 2018: Review
 * 
 */

package myLinkedList;

public class HasCycle {
	
	// Question: Check if a given linked list has a cycle. 
    //		 Return true if it does, otherwise return false.
	public static boolean hasCycle(ListNode head) {
		
		if(head == null) return false;
		
		ListNode fast = head;
		ListNode slow = head;
		
		while(fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;
			if(fast == slow) return true;
		}		
		return false;
	}
	
	public static void main(String[] args) {
		
		ListNode head = new ListNode(1);
		head.next = new ListNode(2);
		head.next.next = new ListNode(3);
		ListNode node1 = new ListNode(4);
		head.next.next.next = node1;
		node1.next = new ListNode(5);
		node1.next.next = new ListNode(6);
		
		// 1 -> 2 -> 3 -> 4 -> 5 -> 6
		boolean ret = hasCycle(head);
		System.out.println(ret);
						
		// 1 -> 2 -> 3 -> 4 -> 5 -> 6
        //                ^---------|	
		node1.next.next.next = node1;
		ret = hasCycle(head);
		System.out.println(ret);
	}

}

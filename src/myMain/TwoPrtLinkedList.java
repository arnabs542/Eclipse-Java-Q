// Created Date: May 18, 2018

package myMain;

public class TwoPrtLinkedList {
	
	//Question: Find the middle node of a given linked list.
	//Time Complexity: ? O(n/2);
	//Space Complexity: O(1);
	public static ListNode FindMiddleNode (ListNode head) {
		if(head == null) return null;
		
		ListNode fast = head;
		ListNode slow = head;
		
		while(fast.next != null && fast.next.next != null) {
			slow = slow.next;
			fast = fast.next.next;			
		}
		return slow;
	}
	
	//Question: Check if a given linked list has a cycle. 
	// 	Return true if it does, otherwise return false.
	public static boolean hasCycle(ListNode head) {
		
		if(head == null) return false;
		
		ListNode fast = head;
		ListNode slow = head;
		
		while(fast.next != null && fast.next.next != null) {
			if(fast == slow) return true;
			else {
				fast = fast.next.next;
				slow = slow.next;
			}
		}		
		return false;
	}
	
}

// Created Date: May 21, 2018
// Question: Reorder the given singly-linked list N1 -> N2 -> N3 -> N4 -> … -> Nn -> null 
//		to be N1- > Nn -> N2 -> Nn-1 -> N3 -> Nn-2 -> … -> null
// 		eg: L = 1 -> 2 -> 3 -> 4 -> null, is reordered to 1 -> 4 -> 2 -> 3 -> null

package myMain;

import java.util.Random;

public class ReorderedLinkedList {
	
	public ListNode reorder(ListNode head) {
		if(head == null || head.next == null) return head;
		
		ListNode one = head;
		
		// find the middle node of the l
		ListNode mid = findMiddle(head);
		ListNode two = mid.next;
		
		// delink the second half from the list
		mid.next = null;
		
		// reverse the second half
		two = reverse(two);
		
		// merge the two halves
		return merge(one, two);
	}
	// Mistake, didn't delink the second half from the list, causing merge problem
	
	private ListNode findMiddle(ListNode head) {
		ListNode fast = head;
		ListNode slow = head;
		
		while(fast.next != null && fast.next.next != null) {
			fast = fast.next.next;
			slow = slow.next;		
		}
		return slow;
	}
	// Warning, following caused NPE error:
	// while(fast.next != null || fast.next.next != null) ..
	
	public ListNode reverse(ListNode head) {
		if(head == null || head.next == null) return head;	
		ListNode pre = null;
		
		while(head != null) {
			ListNode next = head.next;
			head.next = pre;
			pre = head;
			head = next;
		}
		return pre;				
	}	
	
	private ListNode merge(ListNode one, ListNode two) {
		ListNode curr = one;
		ListNode ptrOne = one.next;
		ListNode ptrTwo = two;
		
		while(ptrOne != null || ptrTwo != null) {
			if(ptrOne == null) {
				curr.next = ptrTwo;
				break;
			} else if(ptrTwo == null) {
				curr.next = ptrOne;
				break;
			}		
			curr.next = ptrTwo;
			ptrTwo = ptrTwo.next;
			
			curr = curr.next;			
			curr.next = ptrOne;
			curr = curr.next;
			
			ptrOne = ptrOne.next;						
		}		
		return one;
	}
	// Mistake - Change value of ptrTwo but didn't realize: 
	//	curr.next = ptrTwo; 
	//	curr = curr.next;				
	//	curr.next = ptrOne;
	//	curr = curr.next;
	//	ptrTwo = ptrTwo.next;
	//	ptrOne = ptrOne.next;
	
	public static ListNode genRandLinkedList(int count) {
		Random rand = new Random();		
		ListNode head = null;
		
		while(count > 0) {
			ListNode node = new ListNode(rand.nextInt(10));
			node.next = head;
			head = node;
			count--;
		}		
		
		return head;
	}
	
	public static ListNode genLinkedList(int[] a) {
		ListNode head = null;	
		for(int i = a.length - 1; i >= 0 ; i--) {
			ListNode node = new ListNode(a[i]);
			node.next = head;
			head = node;
		}		
		return head;
	}
	
	public static void printLinkedList(ListNode head) {
		ListNode curr = head;
		while(curr != null) {
			System.out.print(" " + curr.value);
			curr = curr.next;
		}
		System.out.print("\n");
	}
	
	public static void main(String[] args) {
		int[] a = {1, 2, 3, 4, 5, 6, 7, 8};
		ListNode head = genLinkedList(a);
		printLinkedList(head);
					
		ReorderedLinkedList testObj = new ReorderedLinkedList();
		head = testObj.reorder(head);
		printLinkedList(head);
	}
}

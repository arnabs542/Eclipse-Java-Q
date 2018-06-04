// Created Date: May 14, 2018
// Application: Practice reversing LinkedList

// Updated: May 17, 2018: 
// merge() - merge two sorted LinkedList
// insert() - Insert In Sorted Linked List

// Updated: May 18, 2018:
// FindMiddleNode() - Find the middle node of a given linked list.

// Updated: May 18, 2018:
// printListFromTailToHead()

package myMain;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

class ListNode {	
	int value;
	ListNode next = null;
	
	public ListNode(int v) {
		this.value = v;
		this.next = null;
	}
}

public class myLinkedList {
		
	// not sure if it's the best solution
    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        
    		ArrayList<Integer> al = new ArrayList<Integer>();
    		Deque<Integer> stack = new LinkedList<>();
    		
        ListNode curr = listNode;
        
        while(curr != null) {        	
        		stack.push(curr.value);
        		curr = curr.next;
        }
         
        int length = stack.size();
        for(int i = 0; i < length; i++) {
        		System.out.print("stack.size():" + stack.size() + "\n");
    			al.add(stack.pop());
    		}
        
        for(int i = 0; i < al.size(); i++) {
        		System.out.print((Integer)al.get(i));
        }
        System.out.print("\n");
        
        return al;        
    }
	
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
	
	// Time Complexity: ? O(n/2);
	// Space Complexity: O(1);
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
	
	// Time Complexity: O(n);
	// Space Complexity: O(1);
	public static ListNode myInsert(ListNode head, int value) {
		
		ListNode newNode = new ListNode(value);
		ListNode curr = head;
		
		if(curr == null) {
			head = newNode;
			return head;
		}
		else if(curr.value >= value) {
			newNode.next = head;
			head = newNode;
			return head;
		}
		else {		
			while(curr != null) {
				if(curr.next == null || curr.next.value >= value) {
					ListNode next = curr.next;
					curr.next = newNode;
					newNode.next = next;
					return head;
				}
				curr = curr.next;
			}
			return head;
		}			
	}
		
	// Time Complexity: ? O(size1+size2)
	// Space Complexity: O(1);
	public static ListNode merge(ListNode one, ListNode two) {
		
		if(one == null && two == null) return null;
		
		ListNode dummyHead = new ListNode(-1);
		ListNode curr = dummyHead;
		
		ListNode ptrOne = one;
		ListNode ptrTwo = two;
		
		while(ptrOne != null || ptrTwo != null) {
					
			if(ptrOne == null) {
				curr.next = ptrTwo;
				break;
			}
			else if(ptrTwo == null) {
				curr.next = ptrOne;
				break;
			}
			else if(ptrOne.value <= ptrTwo.value) {
				curr.next = ptrOne;
				ptrOne = ptrOne.next;
			}
			else {
				curr.next = ptrTwo;
				ptrTwo = ptrTwo.next;
			}
			curr = curr.next;
		}
					
		return dummyHead.next;
	}
		
	// Time Complexity: O(n);
	// Space Complexity: O(1);
	public static ListNode IterativeReverse(ListNode head) {
				
		ListNode prev = null;
		ListNode curr = head;
		ListNode next = null;
		
		while(curr != null) {			
			
			next = curr.next;
			curr.next = prev;		
			
			prev = curr;	
			curr = next;						
		}			
		return prev;
	}
	
	// Time Complexity: O(n);
	// Space Complexity: O(1);
	public static ListNode RecursiveReverse(ListNode head) {
		
		if(head == null || head.next == null) return head;
		
		ListNode newHead = RecursiveReverse(head.next);
		
		ListNode node = head.next;
		node.next = head;
		head.next = null;
				
		return newHead;
	}
	
	// Time Complexity: O(n);
	// Space Complexity: O(1);
	public static void printList(ListNode head) {
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
		
		ListNode newhead;
		ListNode rst;
		
		ListNode head = new ListNode(1);
		head.next = new ListNode(3);
		head.next.next = new ListNode(5);
		head.next.next.next = new ListNode(7);
		head.next.next.next.next = new ListNode(9);
		printList(head);
		//printListFromTailToHead(head);
		
		//System.out.println(getLength(head));
		
		newhead  = RecursiveReverse(head);
		printList(newhead);
		
		newhead = IterativeReverse(newhead);
		printList(newhead);
		
		ListNode head2 = new ListNode(2);;
		head2.next = new ListNode(4);
		head2.next.next = new ListNode(6);
		head2.next.next.next = new ListNode(8);
		head2.next.next.next.next = new ListNode(10);
		printList(head2);
		
		newhead = merge(head, head2);
		printList(newhead);
				
		newhead = myInsert(newhead, 20);
		printList(newhead);
		
		newhead = myInsert(newhead, 0);
		printList(newhead);
		
		newhead = myInsert(newhead, 6);
		printList(newhead);
		
		rst = FindMiddleNode(newhead);
		System.out.println(rst.value);
		
		ListNode head3 = new ListNode(1);
		head3.next = new ListNode(2);
		head3.next.next = new ListNode(3);
		ListNode node1 = new ListNode(4);
		head3.next.next.next = node1;
		node1.next = new ListNode(5);
		node1.next.next = new ListNode(6);
		
		boolean ret = hasCycle(head3);
		System.out.println(ret);
		printList(head3);
		
		node1.next.next.next = node1;
		ret = hasCycle(head3);
		System.out.println(ret);
	}	
}

/* Alt Test Case 0:
 * 
 * ListNode head = null;
 * printList(head);
 * ListNode head2 = null;
 * printList(head2);
 * 
 * ListNode newhead = merge(head, head2);
 * printList(newhead);
 * 
 * */

/* Alt Test Case 1:
 * 
 * ListNode head = new ListNode(0);
 * printList(head);
 * ListNode head2 = null;
 * printList(head2);
 * 
 * ListNode newhead = merge(head, head2);
 * printList(newhead);
 * 
 * */



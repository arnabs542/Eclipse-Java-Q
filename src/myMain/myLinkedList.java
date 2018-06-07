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
        
        while (curr != null) {        	
        		stack.push(curr.value);
        		curr = curr.next;
        }
        
        while (!stack.isEmpty()) {
    			al.add(stack.pop());
    		}
        
        for (int i = 0; i < al.size(); i++) {
        		System.out.print((Integer)al.get(i) + " ");
        }
        System.out.print("\n");
        
        return al;        
    }
	// Time Complexity: O(n);
	// Space Complexity: O(n);
	
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
		
	public static void main(String[] args) {	
		
		ListNode newhead;
		
		ListNode head = new ListNode(1);
		head.next = new ListNode(3);
		head.next.next = new ListNode(5);
		head.next.next.next = new ListNode(7);
		head.next.next.next.next = new ListNode(9);
		printListFromTailToHead(head);
		
		//System.out.println(getLength(head));
			
		ListNode head2 = new ListNode(2);;
		head2.next = new ListNode(4);
		head2.next.next = new ListNode(6);
		head2.next.next.next = new ListNode(8);
		head2.next.next.next.next = new ListNode(10);

		newhead = myInsert(head, 20);
		
		newhead = myInsert(newhead, 0);
		
		newhead = myInsert(newhead, 6);
		
	}	
}





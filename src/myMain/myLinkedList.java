

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
	
		
	public static void main(String[] args) {	
		
		ListNode head = new ListNode(1);
		head.next = new ListNode(3);
		head.next.next = new ListNode(5);
		head.next.next.next = new ListNode(7);
		head.next.next.next.next = new ListNode(9);
		printListFromTailToHead(head);		
	}	
}





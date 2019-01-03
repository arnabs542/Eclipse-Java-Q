/*
 * == Created Date ==
 * Jan 1, 2019
 * 
 * == Question - Number of Islands ==
 * 
 * == Notes == 
 * LeetCode 200 (M) 
 * 
 */

package myLinkedList;

import java.util.Deque;
import java.util.LinkedList;

public class PalindromeLinkedList {
	
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        Deque<Integer> stack = new LinkedList<>();
        
        while (fast != null && fast.next != null) { // push the value of the first half nodes to the stack
            stack.push(slow.value);
            slow = slow.next;
            fast = fast.next.next;
        }
        
        if (fast != null) { // skip the middle node if number of nodes is odd
            slow = slow.next;
        }
        
        while (!stack.isEmpty()) {
            if (stack.pop() != slow.value) {
                return false;
            }
            slow = slow.next;
        }
        return true;
    }
    
}

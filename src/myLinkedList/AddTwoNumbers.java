/*
 * Created Date: August 30, 2018
 * 
 * Question - Add Two Numbers (Medium):
 *   You are given two non-empty linked lists representing two non-negative integers. 
 *   The digits are stored in reverse order and each of their nodes contain a single digit. 
 *   Add the two numbers and return it as a linked list.
 *   
 *   You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *   
 *   Example: 
 *     Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 *     Output: 7 -> 0 -> 8
 *     Explanation: 342 + 465 = 807.
 * 
 */

package myLinkedList;

public class AddTwoNumbers {
	
	/* -------< Partial Solution, can not pass case [[9], [1,9,9,9,9,9,9,9,9,9]] >------------*/
	public ListNode addTwoNumbersOverFlow(ListNode l1, ListNode l2) {
		long sum = 0;
		int digit = 1;
		while (l1 != null) {
			System.out.println(sum);
			sum += l1.value * digit;
			digit *= 10;
			l1 = l1.next;
		}
		System.out.println(sum);
		System.out.println(l2.value);
		digit = 1;
		while (l2 != null) {
			sum += l2.value * digit;
			System.out.println(sum);
			digit *= 10;
			l2 = l2.next;
		}

		ListNode dummyHead = new ListNode(0); 
		ListNode cur = dummyHead; 
		while (sum > 0) {
			int val = (int) sum % 10;
			cur.next = new ListNode(val);
			sum /= 10;
			cur = cur.next;
		}	
		return dummyHead.next;
	}
	
	// Time Complexity: O(number of nodes of max(l1, l2));
	// Space Complexity: O(1);
	
	/* ----------------< Better Solution>-------------*/
	
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        int sum = 0;
        while (l1 != null || l2 != null) {
            sum /= 10;
            if (l1 != null) {
                sum += l1.value;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.value;
                l2 = l2.next;
            }
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
        }
        if (sum / 10 == 1) {
        		cur.next = new ListNode(1);
        }          
        return dummyHead.next;
	}
	
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		AddTwoNumbers testObj = new AddTwoNumbers();
			
		int a = 1000000000;
//		double b = 10000000000; // error
		
		System.out.println(a + "\n" + 0 / 10);
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		ListNode one1 = new ListNode(2);
		one1.next = new ListNode(4);
		one1.next.next = new ListNode(3);
		
		ListNode two1 = new ListNode(5);
		two1.next = new ListNode(6);
		two1.next.next = new ListNode(4);
		ListNode result1 = testObj.addTwoNumbers(one1, two1);
		
		result1.printList(result1);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		int[] arr2 = {1,9,9,9,9,9,9,9,9,9};
		ListNode one2 = ListNode.genLinkedList(arr2);
		
		ListNode two2 = new ListNode(9);
		ListNode result2 = testObj.addTwoNumbers(one2, two2);
		result2.printList(result2);
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}

/*
 * Created Date: August 7, 2018
 * 
 * Question - Deep Copy LinkedList With Random Pointer (medium):
 *   Each of the nodes in the linked list has another pointer pointing to a random node in the list or null. 
 *   Make a deep copy of the original list.
 *   
 *  Notes: 
 *    - don't forget to use dummy head
 * 
 */

package linkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * class RandomListNode {
 *   public int value;
 *   public RandomListNode next;
 *   public RandomListNode random;
 *   
 *   public RandomListNode(int value) {
 *     this.value = value;
 *   }
 * }
 */

public class DeepCopyLinkedListWithRandomPointer {
	
	public RandomListNode copy(RandomListNode head) {
		RandomListNode dummy = new RandomListNode(0);
		RandomListNode cur = dummy;
		
		// Use a hash table to maintain the node in the original list 
		// and its corresponding node in the new list		
		Map<RandomListNode, RandomListNode> map = new HashMap<>();
		
		while (head != null) {
			// copy the current node if necessary
			if (!map.containsKey(head)) {
				map.put(head, new RandomListNode(head.value));
			}
			cur.next = map.get(head);
			
			// copy the random node if necessary
			if (head.random != null) {
				if (!map.containsKey(head.random)) {
					map.put(head.random, new RandomListNode(head.random.value));
				}
				cur.next.random = map.get(head.random); // !!! not cur.random = ....
			}
			
			cur = cur.next;
			head = head.next;			
		}
		return dummy.next;		
	}
}

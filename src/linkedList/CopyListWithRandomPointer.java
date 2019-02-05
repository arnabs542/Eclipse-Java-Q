/*
 * == Created Date == 
 * August 7, 2018
 * 
 * == Question - Copy List with Random Pointer ==
 * Each of the nodes in the linked list has another pointer pointing to a random node in the list or null. 
 * Make a deep copy of the original list.   
 *   
 * == Notes ==
 * LeetCode 138 (M)
 * 
 */

package linkedList;

import java.util.HashMap;
import java.util.Map;


class RandomListNode {
	int label;
	public RandomListNode next;
	public RandomListNode random; 
	
	public RandomListNode(int v) {
		this.label = v;
		this.next = null;
		this.random = null;
	}
}

public class CopyListWithRandomPointer {
	
	/* ----- < Solution 1 - Recurtion with Hash table > -----
	 * Time Complexity: O(N);
	 * Space Complexity: O(N);
	 * 
	 * */
    public RandomListNode copyRandomListI(RandomListNode head) {
        Map<RandomListNode, RandomListNode> map = new HashMap<>(); // key-value ：old node and its corresponding copy node
        return copy(head, map);
    }
    
    private RandomListNode copy(RandomListNode head, Map<RandomListNode, RandomListNode> map) {
        if (head == null) {
            return null;
        }
        
        // If we have already processed the current node, then we simply return the cloned version of it.
        if (map.containsKey(head)) {
            return map.get(head);
        }
        
        // Create a new node with the label same as old node. (i.e. copy the node)
        RandomListNode newHead = new RandomListNode(head.label);
        map.put(head, newHead); // Save this old and new node pair to the map
        
        // Recursively copy the remaining linked list starting once from the next pointer and then from the random pointer.
        // Finally update the next and random pointers for the new node created.
        newHead.next = copy(head.next, map);
        newHead.random = copy(head.random, map);
        
        return newHead;
    }
    
	/* ----- < Solution 2 - Iteration with Hash table > -----
	 * Time Complexity: O(N);
	 * Space Complexity: O(N);
	 * 
	 * */
	public RandomListNode copyRandomListII(RandomListNode head) {
		// Use dummy node to reduce corner cases, like copy the first node
		RandomListNode dummy = new RandomListNode(0);
		RandomListNode cur = dummy;
		
		// Use a hash table to maintain mappinng between the node in the original list 
		// and the corresponding node in the new list		
		Map<RandomListNode, RandomListNode> map = new HashMap<>();
		
		while (head != null) {
			// copy the current node if necessary
			if (!map.containsKey(head)) {
				map.put(head, new RandomListNode(head.label));
			}
			// connect the copied node to the deep copy list
			cur.next = map.get(head);
			
			// copy the random node if necessary
			if (head.random != null) {
				if (!map.containsKey(head.random)) {
					map.put(head.random, new RandomListNode(head.random.label));
				}
				// connect the copied node to the random pointer
				cur.next.random = map.get(head.random); // !!! not cur.random = ....
			}
			
			cur = cur.next;
			head = head.next;			
		}
		return dummy.next;		
	}
	
	/* ----- < Solution 3 - Three pass using constance space > -----
	 * Time Complexity: O(N);
	 * Space Complexity: O(1);
	 * 
	 * */
	public RandomListNode copyRandomListIII(RandomListNode head) {
		if (head == null) {
			return null;
		}
		
		// First pass: For each node in the original list, insert a copied node between the node and the node.next, 
		//             creating a new weaved list of original and copied nodes.
		// A -> B -> C
		// A -> A' -> B -> B' -> C -> C'
		RandomListNode cur = head;
		while (cur != null) {
			// Make a copy of cur node, insert it to the middle of cur and cur.next
			RandomListNode copy = new RandomListNode(cur.label);
			copy.next = cur.next;
			cur.next = copy;
			cur = cur.next.next;
		}
		
		// Second pass: Link the random pointer for the copied node
		// 
		//          
		//  A -> B ->  C
        //  |          ^
		//  |__________|
		
		// A -> A' -> B -> B' -> C -> C'
        //      |                     ^
		//      |_____________________|
		
		while (cur != null) {
			if (cur.random != null) {
				cur.next.random = cur.random.next;
			}
			cur = cur.next.next;
		}
		
		// Third pass: Unweave the linked list to get back the original linked list and the cloned list.
		// A -> A' -> B -> B' -> C -> C'
		
		//  A -> B ->  C
		//  A' -> B' ->  C'
		
		cur = head;
		RandomListNode dummy = new RandomListNode(0);
		RandomListNode newListCur = dummy;
		while (cur != null) {
			newListCur.next = cur.next;
			cur.next = cur.next.next;
			
			newListCur = newListCur.next;
			cur = cur.next;
		}
		return dummy.next;
	}
}

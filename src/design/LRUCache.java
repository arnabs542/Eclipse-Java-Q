/*
 * == Created Date ==
 * Jan 15, 2019
 * 
 * == Question - LRU Cache ==
 * Design and implement a data structure for Least Recently Used (LRU) cache. 
 * It should support the following operations: get and put.
 * 
 * - get(key)
 *   Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * 
 * - put(key, value) 
 *   Set or insert the value if the key is not already present. 
 *   When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 *   
 * == Example == 
 * LRUCache cache = new LRUCache(2);
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);       // returns 1
 * cache.put(3, 3);    // evicts key 2
 * cache.get(2);       // returns -1 (not found)
 * cache.put(4, 4);    // evicts key 1
 * cache.get(1);       // returns -1 (not found)
 * cache.get(3);       // returns 3
 * cache.get(4);       // returns 4
 * 
 * == Follow up ==
 * Could you do both operations in O(1) time complexity?
 *   
 * == Notes == 
 * LeetCode 146(H)
 * 
 */

package design;

import java.util.HashMap;
import java.util.Map;

/* Use cases;
 * 
 * 1. Return the corresponding response of a request (hash table)
 * 2. Append a new entry
 * 3. Remove the oldest entry
 * 4. Adjust the position of some entry
 * 
 * 
 * 
 * */

public class LRUCache {

	class Node {
		private int key;
		private int value;
		private Node next;
		private Node previous;
		
		Node(int key, int value) {
			this.key = key;
			this.value = value;
		}
	}

	private final int capacity;
	private Map<Integer, Node> map;
	Node head;
	Node tail;
	
    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) {
        		return -1;
        }
        Node node = map.get(key);
        
        // remove the node to the head of the list
        remove(node); 
        append(node);
        return node.value;
    }
    
    public void put(int key, int value) {
    		// case1 - If the key is alreay in the cache, update its value and move it to the head
        if (map.containsKey(key)) {
        		map.get(key).value = value;
        		Node node = map.get(key);
            remove(node); 
            append(node);
        } else if (map.size() < capacity) {
        		// case2 - If the key is not in the cache and we still hava space, append it as the new head
        		Node node = new Node(key, value);
        		append(node);
        } else {
        		// case3. If the key is node in the cache and we don't have space, evict the tail and reuse the node
        		remove(tail);
        		Node node = new Node(key, value);
        		append(node);
        }
    }
	
	// remove the node from the double linked list
	private void remove(Node node) {
		map.remove(node.key);
		if (node == head) {
			head = node.next;
		} else if (node == tail) {
			tail = node.previous;
			tail.next = null;
		} else {
			node.previous.next = node.next;
			node.next.previous = node.previous;
		}
	}
	
	// append the node to the head of the double linked list
	private void append(Node node) {
		map.put(node.key, node);
		if (head == null) {
			head = node;
			tail = node;
		} else {
			head.previous = node;
			node.next = head;
			head = node;
		}
	}
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		 LRUCache cache = new LRUCache(2);
		 cache.put(1, 1);
		 cache.put(2, 2);
		 System.out.println(cache.get(1));       // returns 1
		 
		 cache.put(3, 3);    // evicts key 2
		 System.out.println(cache.get(2)); // returns -1 (not found)
		 
		 cache.put(4, 4);    // evicts key 1
		 System.out.println(cache.get(1)); // returns -1 (not found)
		 System.out.println(cache.get(3));       // returns 3
		 System.out.println(cache.get(4));       // returns 4
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}

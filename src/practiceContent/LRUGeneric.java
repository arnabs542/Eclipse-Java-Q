/*
 * == Created Date ==
 * Jan 15, 2019
 * 
 * == Application == 
 * A generic LRU Cache
 * 
 */

package practiceContent;

import java.util.HashMap;
import java.util.Map;


public class LRUGeneric<K, V> {

	// each node contains the key, value pair and it is also a double linked list node
	private final int capacity;
	private Node<K, V> head;
	private Node<K, V> tail;
	
	// maintains the relationship of key and its corresponding node
	private Map<K, Node<K, V>> map;
	
    public LRUGeneric(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<K, Node<K, V>>();
    }
    
    public V get(K key) {
    		Node<K, V> node = map.get(key);
    		if (node == null) { // If not exists, return -1
    			return null;
    		}
    		// Otherwise, return the value and move the node to the head
    		remove(node);
    		append(node);
    		return node.value;
    }
    
    public void put(K key, V value) {
        Node<K, V> node = null;
        // 1. If the key alreay in the cache, update its value and move it to the head
        if (map.containsKey(key)) {
        		node = map.get(key);
        		node.value = value;
        		remove(node);
        } else if (map.size() < this.capacity) {
        		// 2. If the key is not in the cache and we still hava space, append it as the new head
        		node = new Node<K, V>(key, value);
        } else {
        		// 3. If the key is node is not in the cache and we don't have space, evict the tail and reuse the node
        		node = tail;
        		remove(node);
        		node.update(key, value);
        }
        	append(node);
    }
	
    private Node<K, V> remove(Node<K, V> node) {
    		map.remove(node);
    		if (node.previous != null) {
    			node.previous.next = node.next;
    		}
    		if (node.next != null) {
    			node.next.previous = node.previous;
    		}
    		if (node == this.head) {
    			this.head = node.next;
    		}
    		if (node == this.tail) {
    			this.tail = node.previous;
    		}
    		node.next = node.previous = null;
    		return node;
    }
    
    private Node<K, V> append(Node<K, V> node) { 
    		map.put(node.key, node);
    		if (this.head == null) {
    			this.head = node;
    			this.tail = node;
    		} else {
    			node.next = this.head;
    			this.head.previous = node;
    			this.head = node;
    		}
    		return node;
    }
    
	static class Node<K, V> {
		Node<K, V> next;
		Node<K, V> previous;
		K key;
		V value;
		
		Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		void update(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}
}

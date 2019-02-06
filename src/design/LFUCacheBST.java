/*
 * == Created Date ==
 * Feb 5, 2019
 * 
 * == Question - LFU Cache ==
 * 
 *   
 * == Example == 
 *   
 * == Notes == 
 * LeetCode 460(H)
 * 
 */
package design;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class LFUCacheBST {

	/* ----- < Solution 1 - Balanced binary search tree + Hash Map > -----
	 * Time Complexity: O(logk), k is the capacity of the tree
	 * Space Complexity: O(logk);
	 * 
	 * */
    class Node {
        int key;
		int value;
		int freq;
		int tick; // serve as a time stamp
	    public Node (int key, int value, int freq, int tick) {
            this.key = key;
	        this.value = value;
	        this.freq = freq;
	        this.tick = tick;
	    }
	}
    
    private Map<Integer, Node> keyNodeMap;
	private TreeSet<Node> treeSet; // balanced binary search tree
	private int tick;
	private int capacity;
    
    public LFUCacheBST(int capacity) {
		this.capacity = capacity;
		tick = 0;
		keyNodeMap = new HashMap<>();
		treeSet = new TreeSet<>((a, b) -> a.freq == b.freq ? a.tick - b.tick : a.freq - b.freq);  
    }
    
    public int get(int key) {
        if (!keyNodeMap.containsKey(key)) {
        	return -1;
        }
        Node oldNode = keyNodeMap.get(key);
        int value = oldNode.value;
        
        Node newNode = new Node(key, oldNode.value, oldNode.freq + 1, ++tick);
        
        treeSet.remove(oldNode);  // log(capacity)    
        treeSet.add(newNode);  // log(capacity)    
        keyNodeMap.put(key, newNode);
        
        return value;
    }
    
    public void put(int key, int value) {
        if (capacity == 0) {
        	return;
        }
        
        // If the key exist, update the value, freq and tick
        if (keyNodeMap.containsKey(key)) {
            Node oldNode = keyNodeMap.get(key);
            Node newNode = new Node(key, value, oldNode.freq + 1, ++tick);
            
            treeSet.remove(oldNode);  // log(capacity)    
            treeSet.add(newNode);  // log(capacity)    
            keyNodeMap.put(key, newNode);
            return;
        }
        
        // If the cache is full, remove the least freqently used node (first node in the treeSet)
        if (keyNodeMap.size() == capacity) {
            keyNodeMap.remove(treeSet.pollFirst().key);
        }
        
        // Add new node to the treeSet
        Node newNode = new Node(key, value, 1, ++tick);
        treeSet.add(newNode);  // log(capacity)    
        keyNodeMap.put(key, newNode);     
    }
}

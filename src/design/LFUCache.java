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

public class LFUCache {

	/* ----- < Solution 2 - Double linked list + Hash Map  > -----
	 * Time Complexity: O(logk), k is the capacity of the tree
	 * Space Complexity: O(logk);
	 * 
	 * */
    class Node {
        int key;
		int value;
		int freq;
	}
}

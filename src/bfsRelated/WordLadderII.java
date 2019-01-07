/*
 * == Created Date ==
 * Jan 5, 2019
 * 
 * == Question - Word Ladder II ==
 * Given two words (beginWord and endWord), and a dictionary's word list, 
 * find all shortest transformation sequence(s) from beginWord to endWord, such that:
 *  - Only one letter can be changed at a time.
 *  - Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 *  
 * Note:
 * - Return 0 if there is no such transformation sequence.
 * - All words have the same length.
 * - All words contain only lowercase alphabetic characters.
 * - You may assume no duplicates in the word list.
 * - You may assume beginWord and endWord are non-empty and are not the same.
 *   
 * == Example == 
 * Input:
 *   beginWord = "hit",
 *   endWord = "cog",
 *   wordList = ["hot","dot","dog","lot","log","cog"]
 * 
 * Output:
 * [
 *   ["hit","hot","dot","dog","cog"],
 *   ["hit","hot","lot","log","cog"]
 * ]
 * 
 * == Notes == 
 * LeetCode 126(H)
 * 
 */
package bfsRelated;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WordLadderII {
	
	/* ----- < Method 1 - BFS > -----
	 * 
	 * == Data Strucuture ==
	 * FIFO queue
	 * A Set to record all the words in the word lists
	 * 
	 * ["hot","dot","dog","lot","log","cog"]
	 *                 
	 *                     hit                         ["hot","dot","dog","lot","log","cog"]
	 *                    /
	 *                  hot                            ["dot","dog","lot","log","cog"]
	 *                 /  \
	 *              dot    lot                         ["dog","log","cog"]
	 *             /        /
	 *           dog       log                         ["cog"]
	 *           /         /
	 *         cog        cog
	 *  
	 * == Init ==
	 * Enque the beginWord
	 * 
	 * == For each step ==
	 * Expand: dequeue
	 * 
	 * Generate: generate its neighbor nodes 
	 * case 1: enqueue the words that being changed one character and exist in dictionary
	 * case 2: neighbor node is the endWord, return distance + 1
	 *  
	 * == Termination ==
	 * Queue is empty or return in the process
	 * 
	 * Time Complexity: O(N * M), where N is size of the dictionary and M is length of the word.
	 * Space Complexity: O(N);
	 */

	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> dictionary = new HashSet<>(wordList);
        if (!dictionary.contains(endWord)) {
        		return new ArrayList<>();
        }
        
        List<List<String>> ladders = new ArrayList<>();
        
        // Incomplete
        
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord); 
        return ladders;
    }
}

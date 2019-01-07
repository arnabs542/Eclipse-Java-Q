/*
 * == Created Date ==
 * Jan 5, 2019
 * 
 * == Question - Word Ladder ==
 * Given two words (beginWord and endWord), and a dictionary's word list, 
 * find the length of shortest transformation sequence from beginWord to endWord, such that:
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
 * "hot"
 * "dog"
 * ["hot","dog","dot"]
  
 * == Notes == 
 * LeetCode 127(M)
 * 
 */

package bfsRelated;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class WordLadder {
	
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
	 *           /
	 *         cog
	 *  
	 * == Init ==
	 * Enque the beginWord
	 * 
	 * == For each step ==
	 * Expansion: dequeue
	 * 
	 * Generation: generate its neighbor nodes 
	 * case 1: enqueue the words that being changed one character and exist in dictionary
	 * case 2: neighbor node is the endWord, return distance + 1
	 *  
	 * == Termination ==
	 * Queue is empty or return in the process
	 * 
	 * Time Complexity: O(N * 26^L), where N is size of the dictionary and L is length of the word.
	 * Space Complexity: O(N);
	 * 
	 * */
	
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dictionary = new HashSet<>(wordList);
        if (!dictionary.contains(endWord)) {
            return 0;
        }
        
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord); 
        int distance = 0;
        while (!queue.isEmpty()) { // traverse the tree via BFS - O(N)
            distance++;
            for (int size = queue.size(); size > 0; size--) {
            		// expand one node from queue
            		String word = queue.poll(); 
            		char[] chars = word.toCharArray();
            		
            		// generate its neighbor nodes - O(26 ^ L), L is the len of one word
            		// (words that being changed one character and exist in dictionary)
                for (int i = 0; i < chars.length; i++) {
                		char temp = chars[i]; 
                		for (char j = 'a'; j <= 'z'; j++) {
                			chars[i] = j;
                			String newWord = new String(chars);
                			if (newWord.equals(endWord)) {
                             return distance + 1;
                			}
                			if (dictionary.contains(newWord)) { 
                				queue.offer(newWord);
    	        					dictionary.remove(newWord); // deduplication
                			}
                    }
                    chars[i] = temp;
                }
            }
        }
        return 0;
    }
    
    /* -- < Lessons Learned > --
     * 
     * char[] chars = word.toCharArray();
     * 
     * new String(chars) 
     * VS.
     * chars.toString(), this will return the address, don't use this to convert char array to string
     * 
     * */

	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		WordLadder testObj = new WordLadder();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		List<String> wordList = new ArrayList<>();
		String[] list = {"hot","dot","dog","lot","log","cog"};
		for (String str : list) {
			wordList.add(str);
		}
		
		int resule = testObj.ladderLength("hit", "cog", wordList);
		System.out.println(resule);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		List<String> wordList2 = new ArrayList<>();
		String[] list2 = {"a","c"};
		for (String str : list2) {
			wordList2.add(str);
		}
		resule= testObj.ladderLength("a", "c", wordList2);
		System.out.println(resule);

		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		List<String> wordList3 = new ArrayList<>();
		String[] list3 = {"hot","dog","dot"};
		for (String str : list3) {
			wordList3.add(str);
		}
		resule = testObj.ladderLength("hot", "dog", wordList3);
		System.out.println(resule);
	}
}

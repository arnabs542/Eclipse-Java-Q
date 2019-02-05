/*
 * == Created Date ==
 * Feb 5, 2019
 * 
 * == Question - Word Break II ==
 * 
 * == Notes == 
 * LeetCode 140(H)
 * 
 */

package dynamicProgramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordBreakII {
	
	/* ----- < Solution - Memorized Recursion > -----
	 * Time Complexity: O(2^n);
	 * Space Complexity: O(2^n);
	 * 
	 *                        pineapplepenapple
	 *                     /
	 *             pineapplepen [apple]
	 *             /          \
	 *    pine [applepen]     pineapple [pen]    
	 *                          /                
	 *                     pine apple
	 * 
	 * ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
	 * map:
	 * 
	 * <pine, [pine]>
	 * <pineapple, ["pineapple", "pine apple"]>
	 * <pineapplepen, ["pine apple pen", "pine applepen", "pineapple pen"]>
	 * <pineapplepenapple, ["pineapple pen apple", "pine apple pen apple", "pine applepen apple"]>
	 * 
	 * */
    public List<String> wordBreak(String s, List<String> wordDict) {
    		// create a hash set of words for fast query
    		Set<String> dict = new HashSet<>(wordDict); 
    		
    		// key : string, value : corresponding result list
    		Map<String, List<String>> memo = new HashMap<>();
    		
    		return find(s, memo, dict);
    }

    private List<String> find(String s, Map<String, List<String>> memo, Set<String> dict) {
    		// Base case1: we have already check s, return the result list in the memo
    		if (memo.containsKey(s)) {
    			return memo.get(s);
    		}
    		
    		List<String> curList = new ArrayList<>();
    		
    		// Base case2: s in the dict, add it to the curResultList
    		if (dict.contains(s)) {
    			curList.add(s);
    		}
    		
    		// try each break point positon for s
    		for (int pos = 1; pos < s.length(); pos++) {
    			// check whether right part is a word
    			String right = s.substring(pos);
    			if (!dict.contains(right)) {
    				continue;
    			}
    			
    			// get the result list for left part
    			String left = s.substring(0, pos);
    			
    			List<String> tempList = find(left, memo, dict);
    			List<String> leftList = append(tempList, right);
    			
    			for (String element : leftList) {
    				curList.add(element);
    			}
    		}
    		memo.put(s, curList);
    		return memo.get(s);
    }
    
    private List<String> append(List<String> list, String word) {
    		List<String> newList = new ArrayList<>();
    		for (int i = 0; i < list.size(); i++) {
    			newList.add(list.get(i) + " " + word); 
    		}
    		return newList;
    }
    
	/* ----------------------< test stub >-------------------------*/
    private static void printList(List<String> list) {
		for (String s : list) {
			System.out.println(s);
		}
		System.out.println();
		
    }	
	public static void main(String[] args) {
		
		WordBreakII testObj = new WordBreakII();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		List<String> wordList1 = new ArrayList<>();
		wordList1.add("cat");
		wordList1.add("cats");
		wordList1.add("and");
		wordList1.add("sand");
		
		printList(testObj.wordBreak("catsand", wordList1));
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		List<String> wordList2 = new ArrayList<>();
		wordList2.add("apple");
		wordList2.add("pen");
		wordList2.add("applepen");
		wordList2.add("pine");
		wordList2.add("pineapple");
		
		printList(testObj.wordBreak("pineapplepenapple", wordList2));
		
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}

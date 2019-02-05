/*
 * == Created Date ==
 * July 8, 2018
 * 
 * == Question - Word Break ==
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, 
 *   determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 *   
 * == Assumptions ==
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 *     
 * == Examples 1 == 
 * Dictionary: {“bob”, “cat”, “rob”}
 * Word: “robob” return false
 * Word: “robcatbob” return true since it can be composed by "rob", "cat", "bob"
 * 
 * == Examples 2 == 
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 *              Note that you are allowed to reuse a dictionary word.
 *              
 * == Note == 
 * LeetCode 139 (M) - Word Break
 *                            
 */

package dynamicProgramming;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordBreak {
	
	/* ----- < Solution 1 - Dynamic Programing > -----
	 * Time Complexity: O(n^3)  // two nested loop * substring() -- O(n)
	 * Space Complexity: O(n);
	 * 
	 * */
	public boolean wordBreakI(String input, String[] dict) {
		Set<String> dictionary = new HashSet<>();
		for (String s : dict) {
			dictionary.add(s);
		}
		
		int size = input.length();
		boolean[] M = new boolean[size + 1];
		M[0] = true;
		for (int i = 2; i <= size; i++) {
			for (int k = 0; k < i; k++) {
				if (M[k] && dictionary.contains(input.substring(k, i))) {
					M[i] = true;
					break;
				}
			}
		}
		return M[size];
	}

	// different signature
    public boolean wordBreakI(String s, List<String> wordDict) {
    		// create a hash set of words for fast query
    		Set<String> dictionary = new HashSet<>(wordDict);
    		
        // memo[i] stores whether the substring [0, i) of s can be break into words or not
        boolean[] memo = new boolean[s.length() + 1];
        memo[0] = true;
        
        // start from substring size 1
        for (int size = 1; size <= s.length(); size++) {
            // for the current subtring, try each break point
            for (int i = 0; i < size; i++) {
                if (memo[i] && dictionary.contains(s.substring(i, size))) {
                    memo[size] = true;
                    break;
                }
            }
            // can not find a valid break point, initially memo[size] = false;
        }
        return memo[s.length()];
    }
    
	/* ----- < Solution 2 - Recursion + Memorization > -----
	 * Time Complexity: O(?);
	 * Space Complexity: O(?);
	 * 
	 *                                catsanddog
	 *                  
	 *       /           |           |             |            |           |
	 *  c atsand      ca tsand   cat sanddog    cats anddog    ...     catsand dog
	 *                             |              |                      |
	 *                          [true]          [true]                cat sand
	 *             
	 *  map:
	 *  <c, false>
	 *  <ca, false>
	 *  <cat, true>
	 *  <cats, true>
	 *  <catsa, false>a
	 *  <catsan, false>       
	 * */
    public boolean wordBreakIII(String s, List<String> wordDict) {
    		Set<String> dict = new HashSet<>(wordDict); // create a hash set of words for fast query
    		Map<String, Boolean> memo = new HashMap<>();
    		
    		// query the answer of the original string
    		return canBreak(s, memo, dict);
    }
    
    private boolean canBreak(String s, Map<String, Boolean> memo, Set<String> dict) {
    		// base case1 : The whole string is a word, return true
    		if (dict.contains(s)) {
    			memo.put(s, true);
    			return true;
    		}
    		
    		// base case2: We have checked this string before, return the result in memo 
    		if (memo.containsKey(s)) {
    			return memo.get(s);
    		}
    		
    		// Try every break point
    		for (int i = 1; i < s.length(); i++) {
    			String left = s.substring(0, i);
    			String right = s.substring(i);
    			// if right part is a word, recursivly check the left part
    			if (canBreak(left, memo, dict) && dict.contains(right)) { 
    				// we find a valid break point for s, record it in the memo and return true
    				memo.put(s, true);
    				return true;
    			}
    		}
    		
    		// no break point for s, record the result in memo and return
    		memo.put(s, false);
    		return false;
    }
    
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		WordBreak testObj = new WordBreak();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		String testString = "0123456";
		System.out.println(testString.substring(0, 1)); // not "01", but "0"!!!, 
		  //the second parameter is the size of substring, not end index
		
		System.out.println(testString.substring(0, 3));
		System.out.println(testString.substring(1));
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		String[] dict1 = {"rob", "bob", "cat"};
		boolean result1 = testObj.wordBreakI("robob", dict1);
		System.out.println(result1);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		boolean result2 = testObj.wordBreakI("robcatbob", dict1); 
		System.out.println(result2);
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		String[] dict3 = {"leet","code"};
		boolean result3 = testObj.wordBreakI("leetcode", dict3); 
		System.out.println(result3);
		
	}

}

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

import java.util.HashSet;
import java.util.Set;

public class DictionaryWordI {
	
	public boolean canBreak(String input, String[] dict) {
		
		Set<String> dictionary = new HashSet<>();
		for (String s : dict) {
			dictionary.add(s);
		}
		
		int size = input.length();
		boolean[] M = new boolean[size + 1];
		M[0] = true;
		M[1] = false;
		for (int i = 2; i <= size; i++) {
			boolean curRst = false;
			for (int k = 0; k < i; k++) {
				if (M[k] && dictionary.contains(input.substring(k, i))) {
					curRst = true;
					break;
				}
			}
			M[i] = curRst;
			System.out.println("M[" + i + "] = " + M[i]);
		}
		return M[size];
	}
	
	// Time Complexity: O(n^3)  // two nested loop * substring() -- O(n)
	// Space Complexity: O(n);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		DictionaryWordI testObj = new DictionaryWordI();
		
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
		boolean result1 = testObj.canBreak("robob", dict1);
		System.out.println(result1);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		boolean result2 = testObj.canBreak("robcatbob", dict1); 
		System.out.println(result2);
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		String[] dict3 = {"leet","code"};
		boolean result3 = testObj.canBreak("leetcode", dict3); 
		System.out.println(result3);
		
	}

}

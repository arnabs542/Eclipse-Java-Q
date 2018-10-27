/*
 * == Created Date ==
 * October 24, 2018
 * 
 * == Question - Minimum Window Substring (H) ==
 * Given a string S and a string T, find the minimum window in S 
 *   which will contain all the characters in T in complexity O(n). 
 * 
 * == Note ==
 * LeetCode 76
 * 
 */

package arrayRelated;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
	
	public String minWindow(String s, String t) {
		Map<Character, Integer> charMap = getCharDic(t); // key: char; value: frequency
        
        int left = 0; 
        int matchCount = 0;
        
        int minLen = Integer.MAX_VALUE;
        int index = 0;
        
        for (int right = 0; right < s.length(); right++){       
            char rightVal = s.charAt(right);
            if (charMap.containsKey(rightVal)) {
                if (charMap.get(rightVal) == 1) { // 1 -> 0
                    matchCount++;
                }
                charMap.put(rightVal, charMap.get(rightVal) - 1);
                
                while (matchCount == charMap.size()) {  // find a valid substring
                    if (right - left < minLen) {
                        minLen = right - left;
                        index = left;
                    }
                    char leftVal = s.charAt(left);
                    if (charMap.containsKey(leftVal)) {
                        int freq = charMap.get(leftVal);
                        if (freq == 0) { // 0 -> 1
                            matchCount--;
                        }
                        charMap.put(leftVal, freq + 1);     
                    } 
                    left++;
                }
            } 
        }
        if (minLen == Integer.MAX_VALUE) {
        		return "";
        }
        return new String(s.substring(index, index + minLen + 1));
    }
	
	private Map<Character, Integer> getCharDic(String t) {
		Map<Character, Integer> charDic = new HashMap<>(); // key: char; value: frequency
		for (int i = 0; i < t.length(); i++) {
			char cur = t.charAt(i);
			charDic.put(cur, charDic.getOrDefault(cur, 0) + 1);
		}
		return charDic;
	}
	// Time Complexity: O(len of S);
	// Space Complexity: O(len of T);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		MinimumWindowSubstring testObj = new MinimumWindowSubstring();
		
		String test = "ABANC";
		System.out.println(test.substring(1,5));
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		System.out.println(testObj.minWindow("AECDBACAC", "ABFC"));
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		System.out.println(testObj.minWindow("AECDBACAC", "ABCC"));
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		System.out.println(testObj.minWindow("ABANC", "ABC"));

		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		System.out.println(testObj.minWindow("cabwefgewcwaefgcf", "cae"));
		
	}
}

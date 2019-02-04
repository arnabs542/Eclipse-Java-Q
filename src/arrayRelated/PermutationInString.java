/*
 * == Created Date ==
 * Feb 3, 2019
 * 
 * == Question - Permutation in String ==
 * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. 
 * In other words, one of the first string's permutations is the substring of the second string.
 *   
 * The input strings only contain lower case letters.
 * The length of both given strings is in range [1, 10,000].
 * 
 * == Example 1 == 
 * Input:s1 = "ab" s2 = "eidbaooo"
 * Output:True
 * Explanation: s2 contains one permutation of s1 ("ba").  
 * 
 * == Example 2 == 
 * Input:s1= "ab" s2 = "eidboaoo"
 * Output: False
 * 
 * == Notes == 
 * LeetCode 567 (M)
 * 
 */

package arrayRelated;

import java.util.HashMap;
import java.util.Map;

public class PermutationInString {
	
	/* ----- < Solution 1 - Slinding Window, one HashMap + Two pointer > -----
	 * Time Complexity: O(n);
	 * Space Complexity: O(n);
	 * 
	 * */
    public boolean checkInclusionI(String s1, String s2) {
        Map<Character, Integer> countMap = new HashMap<>(); // record the characters and their count in s1
        for (int i = 0; i < s1.length(); i++) {
            countMap.put(s1.charAt(i), countMap.getOrDefault(s1.charAt(i), 0) + 1);
        }
        
        int slow = 0;
        int fast = 0;
        int matchCount = countMap.size(); // the number of characters that needs to match
        
        while (fast < s2.length()) {
    			char fastVal = s2.charAt(fast);
            if (countMap.containsKey(fastVal)) {
                countMap.put(fastVal, countMap.get(fastVal) - 1);
                if (countMap.get(fastVal) == 0) { // 1 -> 0, one character finished matching
                    matchCount--;
                    if (matchCount == 0) { 
                        return true; // if find an anagram of p, return true
                    }
                }
            }
            fast++;
            
            // if the size of the sliding window is equal or larger than the size of s1, move slow pointer
            if (fast - slow == s1.length()) {
            		char slowVal = s2.charAt(slow);
                if (countMap.containsKey(slowVal)) {
                    countMap.put(slowVal, countMap.get(slowVal) + 1);
                    if (countMap.get(slowVal) == 1) { // 0 -> 1, one character needs to match again
                        matchCount++;
                    }
                }
                slow++;
            }
        }
        return false;
    }
    
	/* ----- < Solution 2 - Slinding Window, two hash tables > -----
	 * Time Complexity: O( l1 + 26 * (l2 - l1) );
	 * Space Complexity: O(1);
	 * 
	 * */
    public boolean checkInclusionII(String s1, String s2) {
        if (s1.length() > s2.length()) {
        		return false;
        }
            
        int[] s1map = new int[26];
        int[] s2map = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            s1map[s1.charAt(i) - 'a']++;
            s2map[s2.charAt(i) - 'a']++;
        }
        
        for (int i = 0; i < s2.length() - s1.length(); i++) {
            if (matches(s1map, s2map)) {
            		return true;
            }
            s2map[s2.charAt(i + s1.length()) - 'a']++;
            s2map[s2.charAt(i) - 'a']--;
        }
        return matches(s1map, s2map);
    }
    
    public boolean matches(int[] s1map, int[] s2map) {
        for (int i = 0; i < 26; i++) {
            if (s1map[i] != s2map[i]) {
            		return false;
            }
        }
        return true;
    }
    
	/* ----- < Solution 3 - Slinding Window, one hash tables > -----
	 * Time Complexity: O( l1 + 26 * (l2 - l1) );
	 * Space Complexity: O(1);
	 * 
	 * */
    public boolean checkInclusionIII(String s1, String s2) {        
        int[] count = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            count[s1.charAt(i) - 'a']++;
        }
        
        int right = 0;
        int left = 0;
        while (right < s2.length()) {
            count[s2.charAt(right) - 'a']--;
            right++;
            
            if (right - left == s1.length()) {
                if (allZero(count)) {
            			return true;
                }
            		count[s2.charAt(left) - 'a']++;
            		left++;
            }
        }
        return false;
    }
    
    private boolean allZero(int[] count) {
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) {
            		return false;
            }
        }
        return true;
    }
    
	/* ----- < same algorithm as above with hash map > -----
	 * 
	 * */
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> countMap = new HashMap<>(); // record the characters and their count in s1
        for (int i = 0; i < s1.length(); i++) {
            countMap.put(s1.charAt(i), countMap.getOrDefault(s1.charAt(i), 0) + 1);
        }
        
        int right = 0;
        int left = 0;
        while (right < s2.length()) {
            char rightVal = s2.charAt(right);
            countMap.put(rightVal, countMap.getOrDefault(rightVal, 0) - 1);
            right++;
            
            if (right - left == s1.length()) {
                if (allZero(countMap)) {
                    return true;
                }
                char leftVal = s2.charAt(left);
                countMap.put(leftVal, countMap.getOrDefault(leftVal, 0) + 1);
                left++;
            }
        }
        return false;
    }
    
    private boolean allZero (Map<Character, Integer> countMap) {
        for (Character c : countMap.keySet()) {
            if (countMap.get(c) != 0) {
                return false;
            }
        }
        return true;
    }

}

/*
 * == Created Date ==
 * Jan 6, 2019
 * 
 * == Question - Palindrome Pairs ==
 * Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, 
 *   so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
 *   
 * == Example == 
 * Input: ["abcd","dcba","lls","s","sssll"]
 * 
 * Output: [[0,1],[1,0],[3,2],[2,4]] 
 * 
 * Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
 * 
 * == Notes == 
 * LeetCode 336 (H)
 * 
 */

package hashTableRelated;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PalindromePairs {
	
	/* == Brute Force Solution == 
	 * Time Complexity: O(N ^ 2 * L)
	 * 
	 * */
	
	/* ------------------- < Solution 1: Hash Table >------------------
	 * Time Complexity: O(N * L^2)
	 * Space Complexity: O(N)
	 * 
	 * There are several cases to be considered that isPalindrome(s1 + s2):
	 * Case 1: If s1 is a blank string, then for any string that is palindrome s2: 
	 * 		   s1: "", s2: "aba"
	 *         then, s1+s2 and s2+s1 are palindrome.
	 *      
	 * Case 2: If s2 is the reversing string of s1, 
	 * 		   s1: "abba", s2: "abba"
	 *         then, then s1+s2 and s2+s1 are palindrome.
	 *      
	 * Case 3: If s1[0:cut] is palindrome and there exists s2 is the reversing string of s1[cut+1:]
	 *         s1: "google", s2: "el"
	 *         s2+s1: elgoogle
	 *         then, s2+s1 is palindrome.
	 *       
	 * Case 4: Similiar to case3. If s1[cut+1: ] is palindrome and there exists s2 is the reversing string of s1[0:cut]
	 *         s1: "airbnb", s2: "ria"
	 *         s1+s2: airbnbria
	 *         then, s1+s2 is palindrome.
	 *         
	 * To make the search faster, build a HashMap to store the String-idx pairs.
	 * 
	 * */
	
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (words == null || words.length == 0) {
            return res;
        }
        // build the map to save the key-val pairs: word-index - O(N)
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }

        // Case1 - If s1 is a blank string, then for any string that is palindrome s2, 
        // s1+s2 and s2+s1 are palindrome. - O(N * L), where N is the number of words, L is average length of each word
        if (map.containsKey("")) {
        		int blankIdx = map.get("");
            for (int i = 0; i < words.length; i++) {
            		if (isPalindrome(words[i])) {
            			if (i == blankIdx) { // skip itself
            				continue;
            			}
            			res.add(Arrays.asList(blankIdx, i));
            			res.add(Arrays.asList(i, blankIdx));
                }
            }
        }

        // Case2 - If s2 is the reversing string of s1, then, 
        // then s1+s2 and s2+s1 are palindrome. - O(N * L)
        for (int i = 0; i < words.length; i++) {
            String cur_r = reverseStr(words[i]);
            if (map.containsKey(cur_r)){
                int found = map.get(cur_r);
                if (found == i) {
                		continue;
                }
                res.add(Arrays.asList(i, found));
            }
        }

        // O(N * L * L)
        for (int i = 0; i < words.length; i++) {
            String current = words[i];
            for (int cut = 1; cut < current.length(); cut++) {
                // Case 3: If s1[0:cut] is palindrome and there exists s2 is the reversing string of s1[cut+1:]
                // then, s2+s1 is palindrome.
                if (isPalindrome(current.substring(0, cut))) {
                    String cut_r = reverseStr(current.substring(cut));
                    if (map.containsKey(cut_r)) {
                        int found = map.get(cut_r);
                        if (found == i) {
                        		continue;
                        }
                        res.add(Arrays.asList(found, i));
                    }
                }
                
                // Case 4: If s1[cut+1: ] is palindrome and there exists s2 is the reversing string of s1[0:cut]
                // then, s2+s1 is palindrome.
                if (isPalindrome(current.substring(cut))) {
                    String cut_r = reverseStr(current.substring(0, cut));
                    if (map.containsKey(cut_r)) {
                        int found = map.get(cut_r);
                        if (found == i) {
                            continue;
                        }
                        res.add(Arrays.asList(i, found));
                    }
                }
            }
        }
        return res;
    }

    public String reverseStr(String str) {
        char[] reverseStr = new char[str.length()];
        for (int i = str.length() - 1; i >= 0; i--) {
            reverseStr[str.length() - 1 - i] = str.charAt(i);
        }
        return new String(reverseStr);
    }
    
    public String reverseStrI(String str) {
        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString();
    }
    
    public boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i <= j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}

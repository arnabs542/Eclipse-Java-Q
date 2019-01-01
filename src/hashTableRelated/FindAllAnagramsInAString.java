/*
 * == Created Date ==
 * Dec 31, 2018
 * 
 * == Question - Find All Anagrams in a String ==
 *   
 * == Notes == 
 * LeetCode 438(E)
 *   
 */

package hashTableRelated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindAllAnagramsInAString {
	
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        
        // Use map to record each distinct character and its occurrence in string p
        Map<Character, Integer> countMap = new HashMap<>(); 
        for (int i = 0; i < p.length(); i++) {
            countMap.put(p.charAt(i), countMap.getOrDefault(p.charAt(i), 0) + 1);
        }
        
        int slow = 0;
        int fast = 0;
        int matchCount = countMap.size(); // the number of distinct characters that needs to match
        
        while (fast < s.length()) {
            // if the size of the sliding window is less or equal than size of p, move fast pointer
            if (fast - slow < p.length()) {
                if (countMap.containsKey(s.charAt(fast))) {
                    countMap.put(s.charAt(fast), countMap.get(s.charAt(fast)) - 1);
                    if (countMap.get(s.charAt(fast)) == 0) { // 1 -> 0, one character finished matching
                        matchCount--;
                        // if find an anagram of p, add its starting index to the result list
                        if (matchCount == 0 && fast - slow == p.length() - 1) { 
                            result.add(slow);
                        }
                    } 
                }
                fast++;
            } else { // if the size of the sliding window is larger than size of p, move slow pointer
                if (countMap.containsKey(s.charAt(slow))) {
                    countMap.put(s.charAt(slow), countMap.get(s.charAt(slow)) + 1);
                    if (countMap.get(s.charAt(slow)) == 1) { // 0 -> 1, one character needs to match again
                        matchCount++;
                    }
                }
                slow++;
            }
        }
        return result;
    }
}

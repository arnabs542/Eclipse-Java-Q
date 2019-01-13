/*
 * == Created Date ==
 * Jan 12, 2019
 * 
 * == Question - Word Pattern ==
 * 
 * == Notes == 
 * LeetCode 290 (E) - Word Pattern
 * 
 */

package hashTableRelated;

import java.util.HashMap;
import java.util.Map;

public class WordPattern {
	
    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if (words.length != pattern.length()) {
            return false;
        }
        Map<Character, String> matchMap = new HashMap<>();
        for (int i = 0; i < pattern.length(); i++) {
            char curChar = pattern.charAt(i);
            if (!matchMap.containsKey(curChar)) {
                if (matchMap.containsValue(words[i])) {
                    return false;
                }
                matchMap.put(curChar, words[i]);
            } else if (!matchMap.get(curChar).equals(words[i])) {
                return false;
            }
        }
        return true;
    }
    
    /* -- < Lessons Learned > --
     * 
     * The first time I know java.util.HashMap.containsValue() Method
     * 
     * */
}

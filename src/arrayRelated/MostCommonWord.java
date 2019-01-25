/*
 * == Created Date ==
 * Jan 24, 2019
 * 
 * == Question - Most Common Word ==
 *   
 * == Smilar Question == 
 * LeetCode 819 (E), Most Common Word
 * Amazon OA2, fail 3/20 hidden test cases
 * 
 */

package arrayRelated;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class MostCommonWord {
    // METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    List<String> retrieveMostFrequentlyUsedWords(String literatureText, 
                                                 List<String> wordsToExclude) {
        // WRITE YOUR CODE HER
        if (literatureText == null || wordsToExclude == null) {
            return new ArrayList<>();
        }
        
        // Step1: Put all the wordsToExclude into hash table 
        Set<String> excludedWordSet = new HashSet<>();
        for (String word : wordsToExclude) {
            excludedWordSet.add(word);
        }
        
        // Step2: Store all the words in the literatureText to a list
        String[] wordList = literatureText.replaceAll("\\W+" , " ").toLowerCase().split("\\s+"); 
        
        // Step3: Use a hash map to record the frequency of each word in the String
        //        except the wordsToExclude
        Map<String, Integer> frequencyMap = new HashMap<>(); // key-value : word-frequency
        int highestFrequency = 0;
        for (String word : wordList) {
            if (!excludedWordSet.contains(word)) { // skip the excluded word
                frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1); 
                
                // update the highestFrequency
                highestFrequency = Math.max(highestFrequency, frequencyMap.get(word));
            }
        }
        
        // Step4: Find the words with highest frequency
        List<String> result = new ArrayList<>();
        for (String word : frequencyMap.keySet()) { 
            if (frequencyMap.get(word) == highestFrequency) {
                result.add(word);
            }
        }
        return result;
    }
    // METHOD SIGNATURE ENDS
    
    private List<String> splitWord(String str) {
        String text = str.toLowerCase();
        List<String> wordList = new ArrayList<>();
        int left = 0;
        while (left < text.length()) {
            while (left < text.length() && !Character.isAlphabetic(text.charAt(left))) {
                left++;
            }
            int right = left;
            
            while (right < text.length() && Character.isAlphabetic(text.charAt(right))) {
                right++;
            }
            
            if (left != right || left < text.length() && Character.isAlphabetic(text.charAt(left))) {
                wordList.add(text.substring(left, right));
            }
            
            left = right;
        }
        
        for (String word : wordList) {
        		System.out.println(word);
        }
        return wordList;
    }
    
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		MostCommonWord testObj = new MostCommonWord();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		testObj.splitWord(" ");
		
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}

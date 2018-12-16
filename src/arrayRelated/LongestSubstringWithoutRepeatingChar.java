/*
 * == Created Date == 
 * July 19, 2018
 * 
 * == Question - Longest Substring Without Repeating Characters (medium) == 
 * Given a string, find the longest substring without any repeating characters and return the length of it. 
 * The input string is guaranteed to be not null.   
 *   
 * == Example ==
 * for "bcdfbd", return 4
 * 
 * == Note ==
 * LeetCode 3
 * 
 * Dec 16, 2018: Review for BB phone interivew
 * 
 */

package arrayRelated;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingChar {
	
	public int longest(String input)  {
		if (input.length() <= 1) {
			return input.length();		
		}
		Set<Character> distinct = new HashSet<>();
		int slow = 0;
		int fast = 0;
		int longest = 0;
		while (fast < input.length()) {		
			if (distinct.contains(input.charAt(fast))) { 
				// if encounter a repeated character, move the slow pointer to make a new sliding window
				// [slow, fast] containing all unique characters
				distinct.remove(input.charAt(slow));
				slow++;
			} else { 
				// if encounter non-repeated character, move the fast pointer to make a new sliding window
				distinct.add(input.charAt(fast));
				fast++;
				longest = Math.max(longest, fast - slow);
			}
		}
		return longest;
	}
	
	// Time Complexity: O(n);
	// Space Complexity: O(n);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		LongestSubstringWithoutRepeatingChar testObj = new LongestSubstringWithoutRepeatingChar();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		int result1 = testObj.longest("bcdfbd");
		System.out.println(result1);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}

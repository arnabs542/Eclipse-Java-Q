/*
 * Created Date: July 3, 2018
 * 
 * Question - All Unique Characters I:
 *   Determine if the characters of a given string are all unique.
 *   
 *   Assumptions:
 *     The only set of possible characters used in the string are 'a' - 'z', the 26 lower case letters.
 *     The given string is not null
 *   
 *   Examples:
 *     the characters used in "abcd" are unique
 *     the characters used in "aba" are not unique
 * 
 * Follow up: All Unique Characters II 
 * 
 * Need to Review!!!
 */

package bitMinipulation;

import java.util.HashSet;
import java.util.Set;

public class AllUniqueCharactersI {
	
	public boolean allUnique1(String word) {
		Set<Character> occurredChars = new HashSet<>();
		char[] input = word.toCharArray();
		for (char c : input) {     
			if (occurredChars.contains(c)) {
				return false;
			}
			occurredChars.add(c);
		}
		return true;
	} 	
	// Time Complexity: O(n);
	// Space Complexity: O(n);
	
	public boolean allUnique2(String word) {
		char[] input = word.toCharArray();
		boolean[] occurredChars = new boolean[26];
		for (char c : input) {     
			if (!occurredChars[c - 'a']) {
				occurredChars[c - 'a'] = true;
			} else {
				return false;
			}
		}
		return true;
	}
	// Time Complexity: O(26);
	// Space Complexity: O(26);
	
	public boolean allUnique3(String word) {
		char[] input = word.toCharArray();
		int occurredChars = 0;
		for (char c : input) {  
			int index = c - 'a';
			if (((occurredChars >>> index) & 1) == 1) { // bit tester
				return false;
			} else {
				occurredChars |= (1 << index); // bit setter
			}
		}
		return true;
	}
	// Time Complexity: O(26);
	// Space Complexity: O(1);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		AllUniqueCharactersI testObj = new AllUniqueCharactersI();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
	
		boolean result1A = testObj.allUnique1("bcdkowa");
		System.out.println(result1A);
		
		boolean result1B = testObj.allUnique2("bcdkowa");
		System.out.println(result1B);
		
		boolean result1C = testObj.allUnique3("bcdkowa");
		System.out.println(result1C);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		boolean result2A = testObj.allUnique1("xxyzaasdf");
		System.out.println(result2A);
		
		boolean result2B = testObj.allUnique2("xxyzaasdf");
		System.out.println(result2B);
		
		boolean result2C = testObj.allUnique3("xxyzaasdf");
		System.out.println(result2C);
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");		
	}
}

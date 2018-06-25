/*
 * Created Date: June 26, 2018
 * 
 * Question - Remove Certain Characters:
 *   Remove given characters in input string, the relative order of other characters should be remained. 
 *   Return the new string after deletion.
 *   
 *   Example: 
 *     input = "abcd", t = "ab", delete all instances of 'a' and 'b', the result is "cd".
 *   
 *   Follow up:
 *   
 *   Mirror Question:
 *   
 * Updated:
 * 
 */

package arrayRelated;

import java.util.HashSet;
import java.util.Set;

public class RemoveCertainCharacters {
	
	public String remove(String input, String t) {
		// Assumption: input and t are not null
		char[] in = input.toCharArray();
		Set<Character> set = new HashSet<>(); 
		for (int i = 0; i < t.length(); i++) {
			set.add(t.charAt(i));
		}
		int end = 0;
		for (char item : in) {
			if (!set.contains(item)) {
				in[end++] = item;
			} 		
		}
		return new String(in, 0, end);
	}
	
	// Time Complexity: O(input + t);
	// Space Complexity: O(t.size();
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		RemoveCertainCharacters testObj = new RemoveCertainCharacters();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		String result1 = testObj.remove("abcd", "cbd");
		System.out.println(result1);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		String result2 = testObj.remove("student", "un");
		System.out.println(result2);
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}

}

/*
 * == Created Date == 
 * June 27, 2018
 * 
 * == Question - Remove Adjacent Repeated Characters IV == 
 * Repeatedly remove all adjacent, repeated characters in a given string from left to right.
 * No adjacent characters should be identified in the final string.
 *   
 * == Example ==
 * "abbbaaccz" → "aaaccz" → "ccz" → "z"
 * "aabccdc" → "bccdc" → "bdc"
 * 
 */

package arrayRelated;

import java.util.Deque;
import java.util.LinkedList;

public class RemoveAdjacentRepeatedCharactersIV {
	
	public String deDup(String input) {
		if (input == null || input.length() == 0) {
	      return input;
	    }
	    char[] in = input.toCharArray();
	    
	    Deque<Character> stack = new LinkedList<>();
	    char pre = ' ';
	    for (int curr = 0; curr < in.length; curr++) {
	        if (stack.isEmpty() && in[curr] != pre) {
		        stack.push(in[curr]);
		    } else if (!stack.isEmpty() && in[curr] != stack.peek() && in[curr] != pre) {
	            stack.push(in[curr]);
	        } else if (!stack.isEmpty() && in[curr] == stack.peek()) {
		        pre = in[curr];		
		        stack.pop();
		    }
		}
	    
	    int size = stack.size();
	    for (int i = size - 1; i >= 0; i--) { // mistake: i > 0 !!!!!!!!!!
	      in[i] = stack.pop();
	    }
	    return new String(in, 0, size);
	 }
	
	// Time Complexity: O(?);
	// Space Complexity: O(?);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		RemoveAdjacentRepeatedCharactersIV testObj = new RemoveAdjacentRepeatedCharactersIV();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		String result1 = testObj.deDup("aabbbc");
		System.out.println(result1);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}

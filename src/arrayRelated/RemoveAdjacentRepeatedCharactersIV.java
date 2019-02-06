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
	
	/* ----- < Solution 1 - Use Stack > -----
	 * Time Complexity: O(n);
	 * Space Complexity: O(n);
	 * 
	 * */	
	public String deDup(String s) {
	    if (s == null || s.length() <= 1) { // corner case
   	   		return s; 
	    }
	    
	    Deque<Character> stack = new LinkedList<>();
	    char pre = ' ';
	    for (int i = 0; i < s.length(); i++) {
	    		char cur = s.charAt(i);
	        if (stack.isEmpty() && cur != pre) {
		        stack.push(cur);
		    } else if (!stack.isEmpty() && cur != stack.peek() && cur != pre) {
	            stack.push(cur);
	        } else if (!stack.isEmpty() && cur == stack.peek()) {
		        pre = cur;		
		        stack.pop();
		    }
	    }
	    
	    int size = stack.size();
	    char[] result = new char[size];
	    for (int i = size - 1; i >= 0; i--) { // !!!! not ``` i > 0 ``` 
	    		result[i] = stack.pop();
	    }
	    return new String(result);
	}

	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		RemoveAdjacentRepeatedCharactersIV testObj = new RemoveAdjacentRepeatedCharactersIV();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		System.out.println(testObj.deDup(""));
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		System.out.println(testObj.deDup("aabbbc"));
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		System.out.println(testObj.deDup("azxxzy"));
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}

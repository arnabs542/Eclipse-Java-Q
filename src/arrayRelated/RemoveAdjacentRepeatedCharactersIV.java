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
	 * Time Complexity: O(?);
	 * Space Complexity: O(n);
	 * 
	 * */
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

	
	
	/* ----- < Solution 2 - > -----
	 * Time Complexity: O(?);
	 * Space Complexity: O(?);
	 * 
	 * */
    public static String removeUtil(String str, char last_removed) 
    { 
       // If length of string is 1 or 0  
       if (str.length() == 0 || str.length() == 1) 
           return str; 

       // Remove leftmost same characters and recur for remaining   
       // string  
       if (str.charAt(0) == str.charAt(1)) 
       { 
           last_removed = str.charAt(0); 
           while (str.length() > 1 && str.charAt(0) == str.charAt(1)) 
                 str = str.substring(1, str.length()); 
           str = str.substring(1, str.length()); 
           return removeUtil(str, last_removed);  
       } 

       // At this point, the first character is definiotely different   
       // from its adjacent. Ignore first character and recursively   
       // remove characters from remaining string  
       String rem_str = removeUtil(str.substring(1,str.length()), last_removed); 

       // Check if the first character of the rem_string matches with   
       // the first character of the original string 
       if (rem_str.length() != 0 && rem_str.charAt(0) == str.charAt(0)) 
       { 
          last_removed = str.charAt(0); 
          return rem_str.substring(1,rem_str.length()); // Remove first character 
       }  


       // If remaining string becomes empty and last removed character  
       // is same as first character of original string. This is needed  
       // for a string like "acbbcddc"  
       if (rem_str.length() == 0 && last_removed == str.charAt(0)) 
           return rem_str; 

       // If the two first characters of str and rem_str don't match,   
       // append first character of str before the first character of  
       // rem_str 
       return (str.charAt(0) + rem_str); 
    } 
 
    static String remove(String str)   
    { 
           char last_removed = '\0'; 
           return removeUtil(str, last_removed);        
    } 

	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		RemoveAdjacentRepeatedCharactersIV testObj = new RemoveAdjacentRepeatedCharactersIV();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
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

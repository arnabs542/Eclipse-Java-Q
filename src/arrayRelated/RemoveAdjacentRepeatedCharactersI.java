/*
 * Created Date: June 27, 2018
 * 
 * Question - Remove Adjacent Repeated Characters I (easy):
 *   Remove adjacent, repeated characters in a given string, 
 *   leaving only one character for each group of such characters.
 *   Try to do it in place.
 *    
 *   Example: 
 *     “aaaabbbc” is transferred to “abc”  
 * 
 */

package arrayRelated;

public class RemoveAdjacentRepeatedCharactersI {
	
	public String deDup(String input) {
		if (input == null || input.length() == 0) {
		      return input;
		}
		char[] in = input.toCharArray();
		int slow = 1;
		int fast = 1;
		while (fast < in.length) {
			if (in[fast] != in[slow - 1]) {
				in[slow++] = in[fast++];
		     } else {
		        fast++;
		     }
		 }
		return new String(in, 0, slow); 
	}
}

/*
 * Created Date: June 26, 2018
 * 
 * Question - Remove Spaces:
 *   Given a string, remove all leading/trailing/duplicated empty spaces.
 *   
 *   Example: 
 *     “  a” --> “a”
 *     “   I     love MTV ” --> “I love MTV”
 *  
 *  Updated:
 *    Need to review!!! Consider all the situations carefully!!!
 *    Be sure the exact position of each pointer after every step!!! 
 *    June 30, 2018: Review, Mid term exam problem 4
 * 
 */

package arrayRelated;

public class RemoveSpaces {
	public String removeSpaces(String input) {
		if (input.isEmpty()) {
			return input;
		} 
		char[] in = input.toCharArray();
		int slow = 0;
		int fast = 0;
		while (fast < in.length) {
			if (in[fast] != ' ' || 
			   (in[fast] == ' ' && slow != 0 && in[slow - 1] != ' ')) {
				in[slow++] = in[fast];
			} 
	        fast++;
	    }
		if (slow != 0 && in[slow - 1] == ' ') { // processing the trailing spcae
			return new String(in, 0, slow - 1);
		}
	    return new String(in, 0, slow);
	}
	
	// Time Complexity: O(n);
	// Space Complexity: O(n);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		RemoveSpaces testObj = new RemoveSpaces();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		String result0 = testObj.removeSpaces("");
		System.out.println("[" + result0 + "]"); // expected "[]"
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		String result1 = testObj.removeSpaces(" ");
		System.out.println("[" + result1 + "]"); // expected "[]"
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		String result2 = testObj.removeSpaces("   I love   coding!   ");
		System.out.println("[" + result2 + "]"); // expected "[I love   coding!]"
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");		
	}
}

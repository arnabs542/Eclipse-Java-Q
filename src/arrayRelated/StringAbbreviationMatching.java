/*
 * Created Date: August 4, 2018
 * 
 * Question - String Abbreviation Matching (medium):
 *   Word “book” can be abbreviated to 4, b3, b2k, etc. 
 *   Given a string and an abbreviation, return if the string matches the abbreviation.
 *   
 *   Assumptions:
 *     The original string only contains alphabetic characters.
 *     Both input and pattern are not null.
 *   
 *   Examples:
 *     pattern “s11d” matches input “sophisticated” since “11” matches eleven chars “ophisticate”.
 * 
 */

package arrayRelated;

public class StringAbbreviationMatching {
	
	/* ----------------------< Method 1: Recursion >-------------------------*/
	public boolean match(String input, String pattern) {
		return helper(input, pattern, 0, 0);
	}
	
	private boolean helper(String input, String pattern, int indexOfinput, int indexOfpattern) {
		if (indexOfinput == input.length() && indexOfpattern == pattern.length()) { // base case 1
			return true;
		}
		if (indexOfinput >= input.length() || indexOfpattern >= pattern.length()) { // base case 2
			return false;
		}

		if (pattern.charAt(indexOfpattern) > '9') { // if the current character is not a digit 
			if (pattern.charAt(indexOfpattern++) == input.charAt(indexOfinput++)) {
				return helper(input, pattern, indexOfinput, indexOfpattern);
			} else {
				return false;
			}
		} else { // if the current character is a digit 
			int count = 0;
			while (indexOfpattern < pattern.length() && pattern.charAt(indexOfpattern) >= '0' && pattern.charAt(indexOfpattern) <= '9') {
				count = 10 * count + (pattern.charAt(indexOfpattern++) - '0');
			}
			return helper(input, pattern, indexOfinput + count, indexOfpattern);
		}
	}
	
	/* ----------------------< Method 2: Iteration >-------------------------*/
	
	public boolean matchIterative(String input, String pattern) {
		int curI = 0;
		int curP = 0;
		while (curI < input.length() && curP < pattern.length()) {
			if (pattern.charAt(curP) > '9') {
				if (pattern.charAt(curP) == input.charAt(curI)) {
					curP++;
					curI++;
				} else {
					return false;
				}
			} else {
				int count = 0;
				while (curP < pattern.length() && pattern.charAt(curP) >= '0' && pattern.charAt(curP) <= '9') {
					count = count * 10 + (pattern.charAt(curP) - '0');
					curP++;
				}
				curI += count;
			}	
			if (curP == pattern.length() && curI == input.length()) {
				return true;
			} else if (curP == pattern.length() || curI == input.length()) {
				return false;
			}
		}
		
		// corner case
		return curP == pattern.length() && curI == input.length();	
	}

	// Time Complexity: O(n);
	// Space Complexity: O(1);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		StringAbbreviationMatching testObj = new StringAbbreviationMatching();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		// How to determine if a char of a string is digit or character
		System.out.println((int)'0');
		System.out.println((int)'1');
		System.out.println((int)'2');
		System.out.println((int)'9');
		System.out.println((int)'a');
		System.out.println((int)'b');
		System.out.println((int)'A');	
		
		// How to change a number character to Integer number
		System.out.println('0' - '0');
		System.out.println('1' - '0');
		System.out.println('2' - '0');
		
		/* Test Case 1 */
		System.out.println("\n---< Test Case 1 >---");
		
		boolean res1a = testObj.match("book", "b2k");
		System.out.println(res1a + " // expected: true");
		
		boolean res1b = testObj.match("book", "b4"); 
		System.out.println(res1b + " // expected: false");
		
		boolean res1c = testObj.matchIterative("book", "b2k");
		System.out.println(res1c + " // expected: true");
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		System.out.println(testObj.match("sophisticated", "s11d")); // expected: true
		System.out.println(testObj.match("sophisticated", "s12d")); // expected: false
		
		System.out.println(testObj.matchIterative("sophisticated", "s12d")); // expected: false
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
		System.out.println(testObj.matchIterative("", "b2k"));  // expected: false
		System.out.println(testObj.matchIterative("book", ""));  // expected: false		
	}
}

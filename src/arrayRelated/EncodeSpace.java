/*
 * Created Date: August 24, 2018
 * 
 * Question - :
 *   In URL encoding, whenever we see an space " ", we need to replace it with "20%". 
 *   Provide a method that performs this encoding for a given string.  
 *   
 *   Example: 
 *     "google/q?flo wer market" → "google/q?flo20%wer20%market"
 * 
 */

package arrayRelated;

public class EncodeSpace {
	
	public String encode(String input) {
		if (input == null || input.length() == 0) {
			return input;
		}
		
		int spaceCount = 0;
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == ' ') {
				spaceCount++;
			}
		}
		
		char[] arr = new char[input.length() + 2 * spaceCount];
		int ai = 0;
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) != ' ') {
				arr[ai++] = input.charAt(i);
			} else {
				arr[ai++] = '2';
				arr[ai++] = '0';
				arr[ai++] = '%';
			}
		}
		return new String(arr);
	}
	// Mistake: assign too much space for new array
	//   char[] arr = new char[input.length() + 3 * spaceCount];
	
	// Time Complexity: O(n);
	// Space Complexity: O(n);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		EncodeSpace testObj = new EncodeSpace();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		String result1 = testObj.encode("google/q?flo wer market ");
		System.out.println(result1);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		String result2 = testObj.encode("google ");
		System.out.println(result2);
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}

}

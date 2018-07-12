/*
 * Created Date: July 12, 2018
 * 
 * Question - Decompress String II：
 *   Given a string in compressed form, decompress it to the original string. 
 *   The adjacent repeated characters in the original string are compressed 
 *     to have the character followed by the number of repeated occurrences.
 *  
 *  Assumptions：
 *    The string is not null
 *    The characters used in the original string are guaranteed to be ‘a’ - ‘z’
 *    There are no adjacent repeated characters with length > 9
 *   
 *  Example:
 *    “a1c0b2c4” → “abbcccc” 
 *   
 * Updated:
 *   In-place method is missing!!!!
 * 
 */

package arrayRelated;

public class DecompressStringII {
	
	public String decompressSb(String input) {
		StringBuilder sb = new StringBuilder();
		char[] in = input.toCharArray();
		for (int i = 0; i < in.length; i += 2) {
			for (int j = 1; j <= in[i + 1] - '0'; j++) {
				sb.append(in[i]);
			}
		}
		return sb.toString();
	}
	
	// Time Complexity: O(?);
	// Space Complexity: O(n);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		DecompressStringII testObj = new DecompressStringII();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		String string0 = "123456";
		char[] charArr = string0.toCharArray();
		int intChar0 = '0';
		System.out.println(intChar0);
		
		int intCharArrIndex0 = charArr[0];
		System.out.println(intCharArrIndex0);

		System.out.println(charArr[0] - '0');
		System.out.println(charArr[1] - '0');
		System.out.println(charArr[2] - '0');
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		String result1A = testObj.decompressSb("a1c0b2c4");
		System.out.println(result1A);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		String result2A = testObj.decompressSb("a1");
		System.out.println(result2A);
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}

}

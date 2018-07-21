/*
 * Created Date: July ?21 2018
 * 
 * Question - Hexadecimal Representation (easy):
 *   Generate the hexadecimal representation for a given non-negative integer number as a string. 
 *   The hex representation should start with "0x".
 *   There should not be extra zeros on the left side.
 *  
 *  Examples:
 *    0's hex representation is "0x0"
 *    255's hex representation is "0xFF" 
 * 
 */

package bitMinipulation;

public class HexadecimalRepresentation {
	
	public String hex(int number) {
		String prefix = "0x";	
	    if (number <= 0) { // corner case
	        return prefix + 0;
	    }
	    
		char[] set = {'A', 'B', 'C', 'D', 'E', 'F'};
		StringBuilder sb = new StringBuilder();
	    while (number > 0) {
	        int rem = number % 16;
	        number /= 16;
	        if (rem < 10) {
	          sb.append(rem);
	        } else {
	          sb.append(set[rem - 10]); // or: sb.append((char)(rem - 10 + 'A'));
	        }
	    }
	    return prefix + sb.reverse().toString(); // remember to reverse!!!    
	}
	
	// Time Complexity: O(n);
	// Space Complexity: O(n);

	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		HexadecimalRepresentation testObj = new HexadecimalRepresentation();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		System.out.println(0 + 'a'); // 97
		System.out.println('a' - 'A'); // 32
		System.out.println((char)('B' + 'a' - 'A'));
		System.out.println(0 + 'A'); // 65
		System.out.println(1 + 'A'); // 66
		System.out.println((char)(1 + 'A')); // B
		
		String result0 = testObj.hex(0);
		System.out.println(result0);
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		String result1 = testObj.hex(255);
		System.out.println(result1);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}

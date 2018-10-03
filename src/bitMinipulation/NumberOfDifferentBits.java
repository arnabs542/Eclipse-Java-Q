/*
 * Created Date: July 3, 2018
 * 
 * Question - Number Of Different Bits:
 *   Determine the number of bits that are different for two given integers.
 *   
 *   Examples:
 *     5 (“0101”) and 8 (“1000”) has 3 different bits
 *   
 */

package bitMinipulation;

//import java.util.HashSet;
//import java.util.Set;

public class NumberOfDifferentBits {
	
	public int diffBits(int a, int b) {
		int c = a ^ b;
		int count = 0;
		while (c != 0) {
			if ((c & 1) == 1) { // bit tester. remember to add parenthesis
				count++;
			}
			c >>>= 1; // >>> logical right shift, add 0 to left side
		}
		return count;
	}
	
	public int diffBitsPrettyStyle(int a, int b) {
		a ^= b;
		int count = 0;
		while (a != 0) {
			count += (a & 1);
			a >>>= 1;
		}
		return count;
	}	
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		NumberOfDifferentBits testObj = new NumberOfDifferentBits();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
	    System.out.println(Integer.toBinaryString(-1));
	    // prints "11111111111111111111111111111111"
	    System.out.println(Integer.toBinaryString(-1 >> 16)); // arithmetic shift right, sign-extension
	    // prints "11111111111111111111111111111111"
	    System.out.println(Integer.toBinaryString(-1 >>> 16)); // unsigned/logical right shift, zero-extension
	    // prints "1111111111111111"
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		int result1 = testObj.diffBits(5, 8); // expected 3
		System.out.println(result1);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		int result2 = testObj.diffBits(-1, 2147483647); 
		System.out.println(result2);
		
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}

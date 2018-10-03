/*
 * == Created Date ==
 * July 3, 2018
 * 
 * == Question - Power Of Two == 
 * Determine if a given integer is power of 2.
 *     
 * == Example ==
 * 16 is power of 2 (2 ^ 4)
 * 3 is not
 * 0 is not
 * -1 is not
 * 
 * == Note ==
 * Octocber 3, 2018: Review
 * 
 */

package bitMinipulation;

public class PowerOfTwo {
	
	/* --< Method 1: Count Ones >-- 
	 * Count how many bits are 1. If only one bit is one, return true;
	 * 2^0 = Ob00000001
	 * 2^1 = Ob00000010
	 * 2^2 = Ob00000100
	 * 2^3 = Ob00001000
	 * 
	 * Time Complexity: O(# of bits);
	 * Space Complexity: O(1); 
	 * 
	 */ 
	
	public boolean isPowerOfTwoMeth1(int number) {
		int count = 0;		
		while (number > 0) {
			count += (number & 1);
			number >>= 1;
		}
		return count == 1 ? true : false;
	}
	
    /* --< Mehod 2 : AND with X - 1 >--
     * If x is power of 2, all the bits of x will be different then all the bits of (x - 1).
     * So if x AND (x - 1) == 0, x is a power of 2; else not.    
     * 
     * 2^4     = Ob 00010000
	 * 2^4 - 1 = Ob 00001111
	 * ------------------------
	 *           0b 00000000
	 *           
	 * Time Complexity: O(1);
	 * Space Complexity: O(1); 
	 *         
	 */
	
	public boolean isPowerOfTwoMeth2(int number) {
		return ((number - 1) & number) == 0 && number > 0;
	}
		
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		PowerOfTwo testObj = new PowerOfTwo();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		System.out.println(testObj.isPowerOfTwoMeth1(3));
		System.out.println(testObj.isPowerOfTwoMeth2(3));
		
		System.out.println(testObj.isPowerOfTwoMeth1(8));
		System.out.println(testObj.isPowerOfTwoMeth2(8));
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");	
	}
}

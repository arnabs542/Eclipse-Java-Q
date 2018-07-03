/*
 * Created Date: July 3, 2018
 * 
 * Question - Power Of Two:
 *   Determine if a given integer is power of 2.
 *     
 *   Example: 
 *   16 is power of 2 (2 ^ 4)
 *   3 is not
 *   0 is not
 *   -1 is not
 * 
 */

package bitMinipulation;

public class PowerOfTwo {
	
	/*
	 * 2^0 = Ob00000001
	 * 2^1 = Ob00000010
	 * 2^2 = Ob00000100
	 * 2^3 = Ob00001000
	 * 
	 * 2^4     = Ob 00010000
	 * 2^4 - 1 = Ob 00001111
	 * ------------------------
	 *           0b 00011111
	 *           
	 * */
	public boolean isPowerOfTwo(int number) {
		return ((number - 1) & number) == 1 && number > 0;
	}
}

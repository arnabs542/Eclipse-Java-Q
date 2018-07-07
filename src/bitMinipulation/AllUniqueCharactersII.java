/*
 * Created Date: July 3, 2018
 * 
 * Question - All Unique Characters II:
 *   We are using ASCII charset, the value of valid characters are from 0 to 255
 *   The given string is not null
 * 
 * Examples
 *   all the characters in "abA+\8" are unique
 *   "abA+\a88" contains duplicate characters
 *   
 * Updated:
 *  Need to Review as soon as possible!!!
 * 
 */

package bitMinipulation;

public class AllUniqueCharactersII {
	
	/*
	 * vec[0] = xxxxxxxx xxxxxxxx xxxxxxxx xxxxx101
	 * vec[1] = xxxxxxxx xxxxxxxx xxxxxxxx xxxxx101
	 * vec[2] = xxxxxxxx xxxxxxxx xxxxxxxx xxxxx101
	 * ...
	 * vec[7] = xxxxxxxx xxxxxxxx xxxxxxxx xxxxx101
	 * 
	 * */
	
	public boolean allUnique(String word) {
		char[] input = word.toCharArray();
		int[] vec = new int[8];
		for (char c : input) {  
			int row = c / 32;
			int col = c % 32;
			if (((vec[row] >>> col) & 1) == 1) { // bit tester
				return false;
			} else {
				vec[row] |= (1 << col); // bit setter
			}
		}
		return true;
	}
	
	// Time Complexity: O(256);
	// Space Complexity: O(1);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		AllUniqueCharactersII testObj = new AllUniqueCharactersII();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
	
		boolean result1 = testObj.allUnique("bcdkowa");
		System.out.println(result1);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		boolean result2 = testObj.allUnique("abA+\\a88");
		System.out.println(result2);
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");		
	}

}
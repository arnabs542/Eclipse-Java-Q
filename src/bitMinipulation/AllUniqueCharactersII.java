/*
 * == Created Date == 
 * July 3, 2018
 * 
 * == Question - All Unique Characters II ==
 * We are using ASCII charset, the value of valid characters are from 0 to 255
 * The given string is not null
 * 
 * == Examples == 
 * all the characters in "abA+\8" are unique
 * "abA+\a88" contains duplicate characters
 *   
 * == Updated ==
 * July 21, 2018: Review. Need to Review Again as soon as possible!!!
 * October 24, 2018: Fall class review
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
		int[] vec = new int[8]; // use 8 * 32 bits to represent 256 ASCII characters
		for (int i = 0; i < word.length(); i++) {  
			int row = word.charAt(i) / 32;
			int col = word.charAt(i) % 32;
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
		boolean result3 = testObj.allUnique("abA+>8");
		System.out.println(result3);
	}

}

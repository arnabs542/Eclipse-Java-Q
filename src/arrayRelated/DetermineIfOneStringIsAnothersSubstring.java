/*
 * == Created Date: August 14, 2018 == 
 * 
 * == Question - Determine If One String Is Another's Substring ==
 * Determine if a small string is a substring of another large string.
 * Return the index of the first occurrence of the small string in the large string.
 * Return -1 if the small string is not a substring of the large string.
 *   
 * == Example ==
 * “ab” is a substring of “bcabc”, return 2
 * “bcd” is not a substring of “bcabc”, return -1
 * "" is substring of "abc", return 0
 * 
 * == Note ==
 * Finished in October 29... 
 */

package arrayRelated;

public class DetermineIfOneStringIsAnothersSubstring {
	
	/* ---------------------< Naive Solution >----------------------------- 
	 * Time Complexity: O(N * M) // N: len of large, M: len of small
	 * Space Complexity: O(1)
	 * 
	 * */
	public int strStrI(String large, String small) {
		if (large.length() < small.length()) {
			return -1;
		}
		if (small.length() == 0) {
			return 0;
		}
		for (int i = 0; i <= large.length() - small.length(); i++) {
			if (equals(large, i, small)) {
				return i;
			}
		}
		return -1;
	}
	
	// O(n)
	private boolean equals(String large, int index, String small) {
		for (int i = 0; i < small.length(); i++) {
			if (large.charAt(index + i) != small.charAt(i)) {
				return false;
			}
		}
		return true;
	}
	
	// O(2n)
	private boolean equalsWithEquals(String large, int index, String small) {
		if (large.substring(index, index + small.length()).equals(small)) {
			return true;
		}
		return true;
	}
	
	
	/* ---------------------< Solution with Rabin Karp >----------------------------- 
	 * Time Complexity: O(N) // N: len of large, M: len of small
	 * Space Complexity: O(1)
	 * 
	 * */
	public int strstr(String large, String small) {		
		if (large.length() < small.length()) {
			return -1;
		}
		if (small.length() == 0) {
			return 0;
		}
		
		int largePrime = 101;
		int prime = 31;
		int seed = 1;
		
		int targetHash = small.charAt(0) % largePrime;		
		for (int i = 1; i < small.length(); i++) {
			seed = moduleHash(seed, 0, prime, largePrime);
			targetHash = moduleHash(targetHash, small.charAt(i), prime, largePrime);
		}
		
		int hash = 0;
		for (int i = 1; i < small.length(); i++) {
			hash = moduleHash(hash, large.charAt(i), prime, largePrime);
		}
		
		if (hash == targetHash && equals(large, 0, small)) {
			return 0;
		}
		
		for (int i = 1; i <= large.length() - small.length(); i++) {
			hash = nonNegative(hash - seed * large.charAt(i - 1) % largePrime, largePrime);
			hash = moduleHash(hash,  large.charAt(i + small.length() - 1), prime, largePrime);
			if (hash == targetHash && equals(large, i, small)) {
				return i;
			}
		}		
		return -1;
	}
	
	private int moduleHash(int hash, int addition, int prime, int largePrime) {
		return (hash * prime % largePrime + addition) % largePrime;
	}
	
	private int nonNegative(int hash, int largePrime) {
		if (hash < 0) {
			hash += largePrime;
		}
		return hash;
	}
	
	// Time Complexity: O(?);
	// Space Complexity: O(?);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		DetermineIfOneStringIsAnothersSubstring testObj = new DetermineIfOneStringIsAnothersSubstring();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		System.out.println(testObj.strStrI("abcdedg", "dg"));
		System.out.println(testObj.strstr("abcdedg", "dg"));
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}

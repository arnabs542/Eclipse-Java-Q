/*
 * == Created Date ==
 * August 18, 2018
 * 
 * == Question - Minimum Cuts For Palindromes (medium) ==
 * Given a string, a partitioning of the string is a palindrome partitioning if every substring of the partition is a palindrome. 
 * Determine the fewest cuts needed for a palindrome partitioning of a given string.
 *   
 * == Example ==
 * “a | babbbab | bab | aba” is a palindrome partitioning of “ababbbabbababa”.
 * The minimum number of cuts needed is 3.
 * 
 */

package dynamicProgramming;

public class MinimumCutsForPalindromes {
	
	public int minCuts(String input) {
		if (input == null || input.length() <= 1) {
	        return 0;
	    }
		char[] in = input.toCharArray();
		int[] dp = new int[in.length + 1]; // dp[i] record the minimum cuts needed for sub-array [0, i - 1] 
		dp[1] = 0;
		for (int size = 2; size <= in.length; size++) {		
			if (inPanlin(in, 0, size)) { // no cut
				dp[size] = 0;	
				continue;
			} 
			dp[size] = size;
			for (int cut = 1; cut < size; cut++) { // iterate all the possible cutting positions
				if (inPanlin(in, cut, size - cut)) {
					dp[size] = Math.min(dp[size], dp[cut] + 1);				
				}
			}
		}
		return dp[in.length];
	}
	
	private boolean inPanlin(char[] in, int start, int size) {
		int end = start + size - 1;
		while (start < end) {
			if (in[start++] != in[end--]) {
				return false;
			}
		}
		return true;
	}
	
	// Time Complexity: O(n^3);
	// Space Complexity: O(n);

	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		int[] testUnintializeArr = new int[1];		
		System.out.println(testUnintializeArr[0]);
		
		MinimumCutsForPalindromes testObj = new MinimumCutsForPalindromes();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		testObj.minCuts("aba");
		
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}

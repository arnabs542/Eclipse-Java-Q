/*
 * Created Date: August 26, 2018
 *  
 * Question - Minimum Cut For Square Value:
 *   How to cut/split the number into a minimum number of items such that each item is equal to an integer's square value.
 *   
 *   Example: 
 *     4 can be split to 1+1+1+1 (4 items) or 2^2 (1 item, which is the solution), Return 1
 *     10 can be split to two items 3^2 + 1^2 (solution) or four items 2^2 + 2^2 + 1^2 +1^2, Return 2
 *     
 *   Notes: 
 *     Final problem 3
 * 
 */

package dynamicProgramming;

public class MinimumCutForSquareValue {
	
	public int cut(int n) {
		int[] dp = new int[n + 1]; // dp[i] record the least number of squares up to i 
		dp[0] = 0;
		for (int size = 1; size <= n; size++) {
			dp[size] = size;
			for (int i = 1; i <= (int) Math.sqrt(size); i++) {
				dp[size] = Math.min(dp[size], dp[size - i * i] + 1);
				System.out.println("size: " + size + " = dp[" + (size - i * i) + "] + " + i + "^2");
			}
			System.out.println("dp[" + size + "] = " + dp[size] + "\n");
		}
		return dp[n];
	}
	
	// Time Complexity: O(n^2);
	// Space Complexity: O(n);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		MinimumCutForSquareValue testObj = new MinimumCutForSquareValue();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		testObj.cut(4);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		testObj.cut(10);
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		testObj.cut(18);
	}
}

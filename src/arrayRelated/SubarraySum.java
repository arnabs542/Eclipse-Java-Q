/*
 * == Created Date ==
 * Jan 9, 2018
 * 
 * == Question - Subarray Sum Equals K ==
 * Given an integer array ‘arr[]’ of size n, find sum of all sub-arrays of given array.
 *   
 * == Example == 
 * 
 * Input   : arr[] = {1, 2, 3}
 * Output  : 20
 * Explanation : {1} + {2} + {3} + {2 + 3} + 
 *               {1 + 2} + {1 + 2 + 3} = 20
 *               
 * Input  : arr[] = {1, 2, 3, 4}
 * Output : 50
 * 
 * == Notes == 
 * 
 * 
 */

package arrayRelated;

public class SubarraySum {
	
	/* ----- < Method 1 -  Brute Force > -----
	 * Time Complexity: O(n^3);
	 * Space Complexity: O(1);
	 * 
	 * */
	public static long SubArraySum(int arr[]) {
		long result = 0;
		for (int left = 0; left < arr.length; left++) {
			for (int len = 0; len < arr.length; len++) {
				for (int i = left; i < left + len; i++) {
					result += arr[i];
				}
			}
		}
		return result;
	}
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		// Class testObj = new Class();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}

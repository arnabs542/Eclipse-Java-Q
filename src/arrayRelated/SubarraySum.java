/*
 * == Created Date ==
 * Jan 9, 2018
 * 
 * == Question - Subarray Sum ==
 * Given an integer array ‘arr[]’ of size n, find sum of all sub-arrays of given array.
 *   
 * == Example == 
 * 
 * Input   : arr[] = {1, 2, 3}
 * Output  : 20
 * Explanation : {1} + {2} + {3} + {2 + 3} + {1 + 2} + {1 + 2 + 3} = 20
 *               
 * Input  : arr[] = {1, 2, 3, 4}
 * Output : 50
 * 
 */

package arrayRelated;

public class SubarraySum {
	
	/* ----- < Method 1 - Brute Force > -----
	 * Iterate over all pairs of (start, stop), then iterate
	 * over all the elements in the subarray defined by that range. 
	 *  
	 * Time Complexity: O(n^3);
	 * Space Complexity: O(1);
	 * 
	 * */
	public long SubArraySum(int arr[]) {
		long result = 0;
		for (int left = 0; left < arr.length; left++) {
			for (int right = left; right < arr.length; right++) {
				for (int i = left; i <= right; i++) {
					System.out.print(arr[i] + " ");
					result += arr[i];
				}
				System.out.println();
			}
		}
		return result;
	}
	
	/* ----- < Method 2 - Optimized Subarray Enumeration > -----
	 * If we know the sum of the subarray [i, j], then the sum of the subarray [i, j + 1] 
	 * can be formed by taking the sum of the original subarray, then adding arr[j + 1] into the total.
	 *  
	 * Time Complexity: O(n^2);
	 * Space Complexity: O(1);
	 * 
	 * */
	public long SubArraySumII(int arr[]) {
		long result = 0;
		for (int left = 0; left < arr.length; left++) {
			long sum = 0;
			for (int right = left; right < arr.length; right++) {
				System.out.print(arr[right] + " ");
				sum += arr[right];
				result += sum;
			}
			System.out.println();
		}
		return result;
	}
	
	/* ----- < Method 2 - Optimized Subarray Enumeration > -----/
	 * 
	 * 	[1] [2] [3] [4]
	 *  [1, 2]  [2, 3]  [3, 4]
	 *  [1, 2, 3]  [2, 3, 4]
	 *  [1, 2, 3, 4]
	 *  
	 * The first element of the array will appear in n different subarrays– each of them starts at the first position. 
	 * The second element of the array will appear in n - ­1 subarrays that begin at its position, 
	 *   plus n - ­1 subarrays from the previous position 
	 *   (there are n total intervals, of which one has length one and therefore won't reach the second element). 
	 * The third element of the array will appear in n­ - 2 subarrays that begin in its position, 
	 *   plus n­2 subarrays beginning at the first element 
	 *   (all n arrays, minus the one of length two and the one of length one) 
	 *   and n­2 subarrays beginning at the second element (all n­1 of them except for the one of length one). 
	 * 
	 * More generally, the ith element will open n – i new intervals (one for each length stretching out to the end) and, 
	 * for each preceding element, will overlap n – i of the intervals starting there. 
	 * 
	 * This means that the total number of intervals overlapping element i is given by
	 * (n – i)i + (n – i) = (n – i)(i + 1)
	 * 
	 */
	public long SubArraySumIII(int arr[]) {
		long result = 0;
		for (int i = 0; i < arr.length; i++) {
			result += arr[i] * (i + 1) * (arr.length - i);
		}
		return result;
	}

	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		SubarraySum testObj = new SubarraySum();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		int[] arr = new int[] {1, 2, 3};
		System.out.println("sum = " + testObj.SubArraySum(arr));
		
		System.out.println("sum = " + testObj.SubArraySumII(arr));
		
		System.out.println("sum = " + testObj.SubArraySumIII(arr));
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}

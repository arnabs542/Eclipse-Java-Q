/*
 * == Created Date ==
 * September 4, 2018
 * 
 * == Question - Largest SubArray Sum ==
 * Given an unsorted integer array, 
 * find the contiguous subarray (containing at least one number) which has the largest sum 
 * and return its sum.
 *   
 * == Example == 
 * {2, -1, 4, -2, 1}, the largest subarray sum is 2 + (-1) + 4 = 5
 * 
 * {-2, -1, -3}, the largest subarray sum is -1 
 *  
 * 
 */

package dynamicProgramming;

public class LargestSubArraySum {
	
	public int largestSum(int[] array) {
		int max = array[0];
		int preSum = 0;
		for (int element : array) {
			if (preSum >= 0) {
				preSum += element;
			} else {
				preSum = element;
			}
			max = Math.max(max, preSum);
		}
		return max;
	}
}

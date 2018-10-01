/*
 * == Created Date ==
 * September 29, 2018
 * 
 * == Question - Minimum Size Subarray Sum (M) ==
 * Given an array of n positive integers and a positive integer s, 
 *   find the minimal length of a contiguous subarray of which the sum ≥ s. 
 * If there isn't one, return 0 instead.  
 *   
 * == Example == 
 * Input: s = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: the subarray [4,3] has the minimal length under the problem constraint.
 * 
 * == Notes == 
 *  
 * 
 */

package arrayRelated;

public class MinimumSizeSubarraySum {
	
   public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) { // corner case 
            return 0;
        }
        int start = 0;
        int end = 0;
        int subSum = nums[0];
        int minSize = 0;
        while (end < nums.length) {
            if (subSum < s) {
                if (end == nums.length - 1) {
                    break;
                }
                end++;
                subSum += nums[end];
            } else {
                if (minSize == 0) {
                    minSize = end - start + 1;
                } else {
                    minSize = Math.min(minSize, end - start + 1);
                }
                subSum -= nums[start];
                start++;
            }
        }
        return minSize;
    }
   
	// Time Complexity: O(n);
	// Space Complexity: O(n);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		MinimumSizeSubarraySum testObj = new MinimumSizeSubarraySum();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		int result1 = testObj.minSubArrayLen(3, new int[] {1, 1});
		System.out.println(result1);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}

}

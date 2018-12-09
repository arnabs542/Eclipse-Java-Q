/*
 * == Created Date ==
 * Dec 9, 2018
 * 
 * == Question - Increasing Triplet Subsequence ==
 * 
 * == Notes == 
 * LeetCode 334
 * 
 */

package arrayRelated;

public class IncreasingTripletSubsequence {

	/* ----------------------< Solution: DFS >-------------------------
	 * 
	 */
    private boolean res = false;
    public boolean increasingTriplet(int[] nums) {
        for (int i = 0 ; i < nums.length - 2; i++) {
			dfs(nums, i, 0);
		}	
        return res;
    }

	private void dfs(int[] nums, int index, int count) {
		if (count == 2) {
            res = true;
			return;
		}
		if (index >= nums.length) {
			return;
		}
        
		for (int i = index + 1; i < nums.length; i++) {
			if (nums[i] > nums[index]) {
                dfs(nums, i, count + 1);
			}
		}
        return;
	}
	
	/* ----------------------< Better Solution >-------------------------
	 * 
	 */
}

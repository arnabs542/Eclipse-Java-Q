/*
 * == Created Date ==
 * Nov 13, 2018
 * 
 * == Question - ==
 * 
 *   
 * == Example == 
 *   
 * == Notes == 
 *  
 * 
 */

package BinarySearchRelated;

public class FindFirstAndLastPosition {
    public int[] searchRange(int[] nums, int target) {
    		int[] res = new int[]{-1, -1};
        if (nums == null || nums.length == 0) {
            return res;
        }    		
        int left = 0; 
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (nums[left] == target || nums[right] == target) {
            int first = nums[left] == target ? left : right;
            res[0] = first;
            while (first < nums.length && nums[first] == target){
                first++;
            }
            res[1] = first - 1;
        } else {
        	res[0] = -1;
        	res[1] = -1;
        }
        return res;
    }
}

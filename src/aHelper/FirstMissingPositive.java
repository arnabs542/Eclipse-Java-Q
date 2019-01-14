/*
 * == Created Date ==
 * Jan 12, 2019
 * 
 * == Question - First Missing Positive ==
 * 
 * == Notes == 
 * LeetCode 41 (H)
 * 
 */

package aHelper;

import java.util.HashSet;
import java.util.Set;

public class FirstMissingPositive {
	
	/* ----- < Method 0a - Use Extra Space : Hash Set > -----
	 * Time Complexity: O(n);
	 * Space Complexity: O(n);
	 * 
	 * */
    public int firstMissingPositiveI(int[] nums) {
        Set<Integer> distinctSet = new HashSet<>();
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num > 0 && distinctSet.add(num)) {
                min = Math.min(min, num);
            }
        }
        
        if (min > 1) { // if the minimum Integer is larger than 1, return 1
            return 1;
        }

        while (distinctSet.contains(min)) {
            min++;
        }
        return min;
    }
    
	/* ----- < Method 0b - Use Extra Space : Boolean Array > -----
	 * Time Complexity: O(n);
	 * Space Complexity: O(n);
	 * 
	 *  [3, 4, -1, 1]
	 *   max = 4
	 *   
	 *  0 1 2 3 
	 *  t f t t 
	 *    i
	 *    return i + 1 = 2
	 *          
	 * */
    
    public int firstMissingPositiveII(int[] nums) {
        int max = 1; // not Integer.MAX_VALUE, consider nums is [0] or []
        for (int num : nums) {
            if (num > 0) {
                max = Math.max(max, num);
            }
        }
        boolean[] exist = new boolean[max];
        
        for (int num : nums) {
            if (num > 0) {
            		exist[num - 1] = true;
            }
        }
        
        for (int i = 0; i < exist.length; i++) {
            if (!exist[i]) {
            		return i + 1;
            }
        }
        return max + 1;
    }
    
	/* ----- < Method 1 - Use negative value as record > -----
	 * Time Complexity: O(n);
	 * Space Complexity: O(1);
	 * 
	 *          0  1  2    3
	 *         [3, 4, max, 1]
	 *         -3         -1
	 *         i
	 *         return 1 + 1 = 2
	 *         
    	 * */
    public int firstMissingPositiveIII(int[] nums) {
        // fill the element that less than 1 to Integer.MAX_VALUE
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0) {
                nums[i] = Integer.MAX_VALUE;
            }
        }
        
        // turn num[ abs(num[i]) ] to negative value
        for (int i = 0; i < nums.length; i++) {
            int num = Math.abs(nums[i]);
            if (num - 1 < nums.length) {
                nums[num - 1] = - Math.abs(nums[num - 1]);
            }
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
            	return i + 1;
            }
        }
        return nums.length + 1;
    }
    
	/* ----- < Method 2 - Swap > -----
	 * Time Complexity: O(n);
	 * Space Complexity: O(1);
	 * 
	 *          0  1  2    3
	 *         [3, 4, -1, 1]
	 *          1         3
	 *             i       
	 *             return 1 + 1 = 2
	 *         
	 *          0  1  2
	 *         [1, 0, 2]
	 *             2  0 
	 *             
    	 * */
    public int firstMissingPositiveIV(int[] nums) {
    		if (nums == null || nums.length == 0) {
    			return 1;
    		}
    		for (int i = 0; i < nums.length; i++) {
    	        // If the current value is in the range of (0,length) and it's not at its correct position, 
    	        // swap it to its correct position.
        		while (nums[i] > 0 && nums[i] - 1 < nums.length && nums[nums[i] - 1] != nums[i]) {
    	    			swap(nums, i, nums[i] - 1);
    	    		} 
        	}	
    		
    		for (int i = 0; i < nums.length; i++) {
    			if (nums[i] != i + 1) {
    				return i + 1;
    			}
    		}
    		return nums.length + 1;
    	}
    
    private void swap(int[] nums, int i, int j) {
    		int temp = nums[i];
    		nums[i] = nums[j];
    		nums[j] = temp;
    }
    
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		FirstMissingPositive testObj = new FirstMissingPositive();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		testObj.firstMissingPositiveII(new int[] {3,4,-1,1});
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		System.out.println(testObj.firstMissingPositiveIII(new int[] {1}));
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
	
}

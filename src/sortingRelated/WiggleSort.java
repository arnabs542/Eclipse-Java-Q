/*
 * == Created Date ==
 * Jan 7, 2019
 * 
 * == Question - Wiggle Sort II ==
 * LeetCode 324 (M)
 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 * 
 * == Question - Wiggle Sort ==
 * LeetCode 280 (M)
 * Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
 *   
 */

package sortingRelated;

import java.util.PriorityQueue;

public class WiggleSort {

	/*  0  1  2  3  4  5
	 * 	1, 5, 1, 1, 6, 4
	 * 
	 * [ 1, 6, 1, 5, 1, 4]
	 * 
	 * 	1  5  1  6  1  4
	 * 
	 *  6  5  4  3  2   1
	 * 
	 *  5  6  3  4  1  2
	 *  ----------------------
	 *  
	 *  2  1  3  1  4  1
	 *  
	 *  1  3  1  4  1  2
	 * 
	 * */
	
	/*  == Question - Wiggle Sort ==
	 * LeetCode 280 (M)
	 * Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
	 * 
	 * Time complexity : O(n)
	 * Space complexity : O(1)
	 * 
	 * */
    public void wiggleSortI(int[] nums) {
    		if (nums == null || nums.length == 1) {
    			return;
    		}
        for (int i = 0; i < nums.length; i += 2) {  // Traverse all even elements 
            // If current even element is larger than previous 
            if (i > 0 && nums[i] > nums[i - 1]) {
            		swap(nums, i - 1, i); 
            }
  
            // If current even element is larger than next 
            if (i < nums.length - 1 && nums[i] > nums[i + 1] ) {
            		swap(nums, i, i + 1); 
            }
        } 
    }
    
    // A utility method to swap two numbers. 
    private void swap(int arr[], int a, int b) { 
        int temp = arr[a]; 
        arr[a] = arr[b]; 
        arr[b] = temp; 
    } 
    
	/*  == Question - Wiggle Sort II ==
	 * LeetCode 324 (M)
	 * Given an unsorted array nums, reorder it in-place such that nums[0] < nums[1] > nums[2] < nums[3]....
	 * 
	 * The above solution for Wiggle Sort I cannot pass cases like: [1,2,1,2,1,2,1,1,1,2,2,2]
	 * 
	 * Time complexity : O(n)
	 * Space complexity : O(1)
	 * 
	 * 
	 * Original index: 0  1  2  3  4  5
	 * 	               M     S     S
	 *                    L     L     M
	 *                    
	 * Mapped index:   1  3  5  0  2  4

	 *                    
	 * Array:         13  6  5  5  4  2
	 * 
	 * */
    public void wiggleSortII(int[] nums) {
    		int median = findKthLargest(nums, (nums.length + 1) / 2); 
        int n = nums.length;

        int left = 0;
        int i = 0;
        int right = n - 1;

        while (i <= right) {
            if (nums[newIndex(i,n)] > median) {
                swap(nums, newIndex(left++,n), newIndex(i++,n));
            }
            else if (nums[newIndex(i,n)] < median) {
                swap(nums, newIndex(right--,n), newIndex(i,n));
            }
            else {
                i++;
            }
        }
    }
    
    /* 
     * 
	 * Original index: 0  1  2  3  4  5
	 * 
	 * Mapped index:   1  3  5  0  2  4
	 * 
     * 0 => (1 + 2 * 0) % 6 = 1 % 6 = 1
     * 1 => (1 + 2 * 1) % 6 = 3 % 6 = 3
     * 2 => (1 + 2 * 2) % 6 = 5 % 6 = 5
     * 
     * 0 => (1 + 2 * 0) % (6 | 1) = 1 % 7 = 1
     * 1 => (1 + 2 * 1) % (6 | 1) = 3 % 7 = 3
     * 2 => (1 + 2 * 2) % (6 | 1) = 5 % 7 = 5
     * 
     * 3 => (1 + 2 * 3) % (6 | 1) = 7 % 7 = 0
     * 4 => (1 + 2 * 4) % (6 | 1) = 9 % 7 = 2
     * 5 => (1 + 2 * 5) % (6 | 1) = 11 % 7 = 4
     * 
     * if n = 6, (n | 1) = 7 // for even number, last bit is 0, OR 1 will let even -> odd
     * if n = 7, (n | 1) = 7 
     * 
     */
    private int newIndex(int index, int n) {
        return (1 + 2 * index) % (n | 1);
    }
    
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(k + 1);
        for (int i = 0; i < nums.length; i++) {
            minHeap.offer(nums[i]);
            if (minHeap.size() > k) {
                minHeap.poll();
            } 
        }
        return minHeap.peek();
    }
}

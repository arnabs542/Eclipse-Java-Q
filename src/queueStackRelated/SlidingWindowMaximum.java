/*
 * == Created Date ==
 * Jan 13, 2019
 * 
 * == Question - Sliding Window Maximum ==
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. 
 * You can only see the k numbers in the window. 
 * Each time the sliding window moves right by one position. 
 * Return the max sliding window.
 * 
 * == Example == 
 * Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
 * Output: [3,3,5,5,6,7] 
 * 
 * Explanation: 
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1  [3  -1  -3] 5  3  6  7       3
 * 1   3 [-1  -3  5] 3  6  7       5
 * 1   3  -1 [-3  5  3] 6  7       5
 * 1   3  -1  -3 [5  3  6] 7       6
 * 1   3  -1  -3  5 [3  6  7]      7
 * 
 * == Notes == 
 * LeetCode 239 (H) 
 * 
 */

package queueStackRelated;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class SlidingWindowMaximum {
	
	public int[] maxSlidingWindowI(int[] nums, int k) {
		List<Integer> list = new ArrayList<>();
		MonotonicQueue monotonicQ = new MonotonicQueue();
		int fast = 0; 
		int slow = 0; 
		while (fast < nums.length) {
            monotonicQ.offer(nums[fast]);
            fast++;
            if (fast - slow == k) { 
            		// if we form a slinding window of size k, add the max of queue to result list
            		// move slow pointer and update the queue accordingly
                list.add(monotonicQ.max());
                if (nums[slow] == monotonicQ.max()) {
                    monotonicQ.poll();
                }
                slow++;
            }
		}
		
		int[] result = new int[list.size()];
		for (int i = 1; i < result.length; i++) {
			result[i] = list.get(i);
		}
		return result;	
	}
	
	/* ----- < Solution - Monotonic Queue > -----
	 * Time Complexity: O(n);
	 * Space Complexity: O(k);
	 * 
	 * Window position                   Monotonic Queue 	   
	 * ---------------                   ---------------    
	 * 0   1    2   3   4   5   6   7
	 * 1,  3,  -1, -3,  5,  3,  6,  7       [6]          
	 *                      s
	 *                                 f
	 *              
	 * result list : 3 3 5 5 6 7
	 */
	class MonotonicQueue {
		private Deque<Integer> queue = new ArrayDeque<>();
		
		// push an element into the queue, remove all the elements that is smaller that it - O (1) (amortized)
		public void offer(int e) {
			while (!queue.isEmpty() && queue.peekLast() < e) {
				queue.pollLast();
			}
			queue.offerLast(e);
		}
		
		public void poll() {
			if (queue.isEmpty()) {
				return;
			}
			queue.pollFirst();
		}
		
		public Integer max() {
			if (queue.isEmpty()) {
				return null;
			}
			return queue.peekFirst();
		}
	}
	
	/* ----- < Sliding Window Miniimum - Monotonic Queue > -----
	 * 
	 * Window position                   Monotonic Queue 	   
	 * ---------------                   ---------------    
	 * 0   1    2   3   4   5    6   7
	 * 1,  3,  -1, -3,  5,  3,   4,  7       [3 4 7]          
	 *                      s
	 *                                  f
	 *              
	 * result list : -1 -3 -3 -3 3 3
	 */
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		SlidingWindowMaximum testObj = new SlidingWindowMaximum();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		int[] nums = new int[] {1,3,-1,-3,5,3,6,7};
		testObj.maxSlidingWindowI(nums, 3);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
	
}

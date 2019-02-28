/*
 * == Created Date ==
 * Feb 27, 2019
 * 
 * == Question - KthLargest Element in a Stream ==
 *   
 * == Notes == 
 * LeetCode 703(E)
 * 
 */

package heapRelated;

import java.util.PriorityQueue;

public class KthLargestElementInAStream {
	
    PriorityQueue<Integer> minHeap;
    int size;
    
    public KthLargestElementInAStream(int k, int[] nums) {
        minHeap = new PriorityQueue<Integer>(k + 1);
        size = k;
        for (int i = 0; i < nums.length; i++) {
            minHeap.offer(nums[i]);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
    }
    
    public int add(int val) {
        if (minHeap.size() < size) {
            minHeap.offer(val);
        } else if (val >= minHeap.peek()) {
            minHeap.offer(val);
            minHeap.poll();
        }
        return minHeap.peek();
    }

}

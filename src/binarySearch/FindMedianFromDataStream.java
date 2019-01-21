/*
 * == Created Date ==
 * Jan 20, 2019
 * 
 * == Question - Find Median From Data Stream ==
 * Median is the middle value in an ordered integer list. 
 * If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
 * For example:
 *   [2,3,4], the median is 3
 *   [2,3], the median is (2 + 3) / 2 = 2.5
 *   
 * Design a data structure that supports the following two operations:
 *   void addNum(int num) - Add a integer number from the data stream to the data structure.
 *   double findMedian() - Return the median of all elements so far.
 *   
 * == Notes == 
 * LeetCode 295(H)
 * 
 */
package binarySearch;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Random;

public class FindMedianFromDataStream {
    
	
	/* ----- < Solution - Heap > -----
	 * Time Complexity: O(N * log N);
	 * Space Complexity: O(N);
	 * 
	 * */
    private PriorityQueue<Integer> smallerHalf; // max heap tp store the smaller half of all the numbers read from the stream so far 
    private PriorityQueue<Integer> largerHalf; // min heap to store the larger half ...

    /** initialize your data structure here. */
    public FindMedianFromDataStream() {
        largerHalf = new PriorityQueue<>();
        smallerHalf = new PriorityQueue<>(Collections.reverseOrder());        
    }
    
    public void addNum(int num) { // O(log N);
    		// Step1 - Compare
        if (smallerHalf.isEmpty() || num <= smallerHalf.peek()) {
            smallerHalf.offer(num); 
        } else {
            largerHalf.offer(num);
        }
    
        // Step2 - Adjust the size
        if (smallerHalf.size() - largerHalf.size() >= 2) {
        		// case1: size(smallerHalf) > size(largerHalf) + 1, move one number from smallerHalf to largerHalf
        		largerHalf.offer(smallerHalf.poll());
        } else if (largerHalf.size() > smallerHalf.size()) {
        		// case2: size(smallerHalf) < size(largerHalf), move one number from largerHalf to smallerHalf
        		smallerHalf.offer(largerHalf.poll());
        }
        // case3: if the size(smallerHalf) == size(largerHalf) or size(smallerHalf) == size(largerHalf) + 1, no need to adjust
    }
    
    public double findMedian() {
        int size = smallerHalf.size() + largerHalf.size();
        
        if (size == 0) { 
        		return 0;
        } else if (size % 2 != 0) { 
        		// if the size is odd, return max(smaller half)
        		return (double)(smallerHalf.peek());
        } else {
        		// if the size is even, return (max(smaller half) + min(larger half)) / 2
        		return (smallerHalf.peek() + largerHalf.peek()) / 2.0;
        }        
    }
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		FindMedianFromDataStream Finder = new FindMedianFromDataStream();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
        int[] numsCompare = new int[100];
        Random rand = new Random();
        for (int i = 0; i < 100; i++) {
            int num = rand.nextInt(100);
            numsCompare[i] = num;
            Finder.addNum(num);
        }
        
        Arrays.sort(numsCompare);
        for (int i = 0; i < 100; i++) {
            System.out.print(numsCompare[i] + ",");
        }
        System.out.println();
        
        System.out.println((numsCompare[49] + numsCompare[50]) / 2.0);
        System.out.println(Finder.findMedian());
        
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}

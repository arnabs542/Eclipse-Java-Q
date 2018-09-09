/*
 * == Created Date == 
 * August 6, 2018
 * 
 * == Question - Largest And Second Largest ==
 * Use the least number of comparisons to get the largest and 2nd largest number in the given integer array. 
 * Return the largest number and 2nd largest number.
 *     
 * == Example ==
 * {2, 1, 5, 4, 3}, the largest number is 5 and 2nd largest number is 4.
 *     
 * == Note ==
 * September 8, 2018
 * 
 */

package sortingRelated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LargestAndSecondLargest {
	
	/* 
	 *          2 1     10 6     5 4       7   
	 *           2[1]   10[6]     5[4]     7   tempArr[2, 10, 5, 7]
	 *                10[6, 2]        7[5]     tempArr[10, 7]
	 *                         10[6, 2, 7]     
	 *                         
	 *                  max = 10
	 *                  find second largest in [6, 2, 7] = 7
	 */
	
	// Tournament Sort, n + logn comparisons
	public int[] largestAndSecond(int[] array) {
		
		Map<Integer, List<Integer>> map = new HashMap<>(); 
		// key: current elements; value: elements smaller than the current element through comparison
		
		List<Integer> arr = new ArrayList<>();	
		for (int i = 0; i < array.length; i++) {
			arr.add(array[i]);
		}
		
		List<Integer> tempWinners = new ArrayList<>(); 
		
		while (arr.size() > 1) {		
			tempWinners.clear();
			for (int i = 0; i < arr.size(); i += 2) {
				if (i + 1 < arr.size()) {				
					int tempMax = Math.max(arr.get(i), arr.get(i + 1));
					int tempMin = Math.min(arr.get(i), arr.get(i + 1));				
					tempWinners.add(tempMax);		
					
					
					if (!map.containsKey(tempMax)) {
						List<Integer> beats = new ArrayList<>();	
						beats.add(tempMin);
						map.put(tempMax, beats);
					} else {
						map.get(tempMax).add(tempMin);
					}
				} else { // deal with the last one if the number of array is odd
					tempWinners.add(arr.get(i));
				}
			}	
			arr.clear();
			arr.addAll(tempWinners);			
		}
		
		int largest = arr.get(0);
		
		int second = Integer.MIN_VALUE;		
		for (int item : map.get(largest)) {
			second = Math.max(second, item);
		}
		
		System.out.println("largest = " + largest);
		System.out.println("second = " + second + "\n");
		return new int[] {largest, second};	
	}
	
	// Time Complexity: O(?);
	// Space Complexity: O(?);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		LargestAndSecondLargest testObj = new LargestAndSecondLargest();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		int a = 3;
		int b = 3;		
		System.out.println(Math.max(a, b));
		System.out.println(Math.min(a, b));
			
		List<Integer> arr = new ArrayList<>();	
		List<Integer> tempArr = new ArrayList<>();
		
		tempArr.add(1);
		tempArr.add(2);
		tempArr.add(3);
		
		arr.addAll(tempArr);			
		for (int item : arr) {
			System.out.print(item + " ");
		}
		
		/* Test Case 1 */
		System.out.println("\n---< Test Case 1 >---");
		
		testObj.largestAndSecond(new int[] {2,1});
		testObj.largestAndSecond(new int[] {5,4,3,2,1});
		testObj.largestAndSecond(new int[] {1,2,3,5,1,10});
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
  
}

/*
 * Created Date: August 6, 2018
 * 
 * Question - Largest And Second Largest:
 *   Use the least number of comparisons to get the largest and 2nd largest number in the given integer array. 
 *   Return the largest number and 2nd largest number.
 *     
 *   Example: 
 *     {2, 1, 5, 4, 3}, the largest number is 5 and 2nd largest number is 4.
 * 
 */

package sortingRelated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LargestAndSecondLargest {
	
	// Tournament Sort， n + logn comparisons
	public int[] largestAndSecond(int[] array) {
		Map<Integer, List<Integer>> map = new HashMap<>();
		
		List<Integer> arr = new ArrayList<>();	
		for (int i = 0; i < array.length; i++) {
			arr.add(array[i]);
		}
		
		List<Integer> tempArr = new ArrayList<>();
		
		while (arr.size() > 1) {		
			tempArr.clear();
			for (int i = 0; i < arr.size(); i += 2) {
				if (i + 1 < arr.size()) {				
					int tempMax = Math.max(arr.get(i), arr.get(i + 1));
					int tempMin = Math.min(arr.get(i), arr.get(i + 1));				
					tempArr.add(tempMax);		
					
					// record tempMin in tempMax's record of map
					if (!map.containsKey(tempMax)) {
						List<Integer> beats = new ArrayList<>();	
						beats.add(tempMin);
						map.put(tempMax, beats);
					} else {
						map.get(tempMax).add(tempMin);
					}
				} else { // deal with the last one if the number of array is odd
					tempArr.add(arr.get(i));
				}
			}	
			arr.clear();
			arr.addAll(tempArr);			
		}
		
		int largest = arr.get(0);
		int second = Integer.MIN_VALUE;
		// get the second largest
		for (int i = 0; i < map.get(largest).size(); i++) {
			second = Math.max(second, map.get(largest).get(i));
		}
		
		System.out.println(largest);
		System.out.println(second);
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

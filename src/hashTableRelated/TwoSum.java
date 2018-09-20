/*
 * == Created Date ==
 * September 20, 2018
 * 
 * == Question - Three Problem related to 2 Sum ==
 *   
 * == Example == 
 * 
 * A = {1, 3, 2, 4}, target = 5, return [[0, 3], [1, 2]]
 * A = {1, 2, 2, 4}, target = 6, return [[1, 3], [2, 3]]
 *   
 * 
 */

package hashTableRelated;

import java.util.*;

public class TwoSum {
	
	/* == Question 1 - 2 Sum == 
	 * Find all pairs of elements in a given array that sum to the given target number. 
	 * Return all the pairs of indices.
	 */
	
	/* Method 1: sort the array first, then use two pointers */
	
	public boolean twoSumMethI(int[] array, int target) {
		Arrays.sort(array);
		int left = 0;
		int right = array.length - 1;
		while (left < right) {
			if (array[left] + array[right] == target) {
				return true;
			} else if (array[left] + array[right] < target) {
				left++;
			} else {
				right--;
			}
		}
		return false;
	}
	
	/* == Question 2 - 2 Sum All Pair I == 
	 * Find all pairs of elements in a given array that sum to the given target number. 
	 * Return all the pairs of indices.
	 * 
	 * 	target: 5
	 * 
	 *  0  1  2  3  4  5
	 *  ------------------ 
	 *  	1, 3, 2, 4, 2, 3
	 *  
	 * 	Map<Integer, List<Integer>>
	 * key: number, value: list of all possible indices
	 * 1 [0]
	 * 3 [1, 5]
	 * 2 [2, 4]
	 * 4 [3]
	 * 	
	 * result
	 * [1, 2]
	 * [0, 3]
	 * [1, 4]
	 * [2, 5]
	 * [4, 5]
	 * 		
	 * */
	
	public List<List<Integer>> allPairsI(int[] array, int target) {
		List<List<Integer>> result = new ArrayList<>();
		Map<Integer, List<Integer>> map = new HashMap<>();
		for (int i = 0; i < array.length; i++) {
			List<Integer> indices = map.get(target - array[i]);
			if (indices != null) {
				for (int j : indices) {
					result.add(Arrays.asList(j, i));
				}
			}
			if (!map.containsKey(array[i])) {
				map.put(array[i], new ArrayList<>());
			}
			map.get(array[i]).add(i);
		}
		return result;
	}
	
	/* == Question 3 - 2 Sum All Pair II, distinct pairs of values == 
	 * Find all pairs of elements in a given array that sum to the pair the given target number. 
	 * Return all the distinct pairs of values
	 * 
	 * A = {2, 1, 3, 2, 4, 3, 4, 2}, target = 4
	 * map
	 * 2 true
	 * 1 true
	 * 3 true
	 * 4 false
	 * 
	 * result
	 * [1 3]
	 * [2 2]
	 * 
	 * */
	
	public List<List<Integer>> allPairs(int[] array, int target) {
		List<List<Integer>> result = new ArrayList<>();
		Map<Integer, Boolean> map = new HashMap<>(); // key: number, value: visited or not
		for (int i = 0; i < array.length; i++) {
			if (map.containsKey(target - array[i]) && !map.get(target - array[i])) {
				result.add(Arrays.asList(target - array[i], array[i]));
				map.put(target - array[i], true);
				map.put(array[i], true);
			} else if (!map.containsKey(array[i])) {
				map.put(array[i], false);
			}
		}
		return result;
	}
}

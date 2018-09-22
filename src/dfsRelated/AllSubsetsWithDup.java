/*
 * == Created Date ==
 * September 21, 2018
 * 
 * == Question - All Subsets II ==
 * Given a collection of integers that might contain duplicates, nums, 
 * return all possible subsets (the power set).
 *   
 * == Example ==
 * Input: [1,2,2]
 * Output:
 * [ 
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   [
 * ]
 * 
 */

package dfsRelated;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AllSubsetsWithDup {
	
	 /* == Example ==
	  * Input: [1,2,2]
	  *                                     [ ]
	  *                            /                   \
	  * index = 0               1                        [ ]
	  *                      /     \                   /    \
	  *         1          1 2       1                2      [ ]
	  *                  /    \       \              / \       \
	  *         2    [1 2 2]   [1 2]   [1]       [2, 2] [2]     [ ]
	*/
	
	public List<String> subSets(String set) {
		List<String> result = new LinkedList<>();
		if (set == null) {
			return result;
		}
		char[] arraySet =  set.toCharArray();
		Arrays.sort(arraySet); // See sort() to character array for the first time 
		StringBuilder sb = new StringBuilder();
		subSets(arraySet, sb, 0, result);
		return result;
	}
	
	private void subSets(char[] set, StringBuilder sb, int index, List<String> result) {
		if (index == set.length) {
			result.add(sb.toString());
			return;
		}
		subSets(set, sb.append(set[index]), index + 1, result);
		sb.deleteCharAt(sb.length() - 1);
		// skip all the consecutive and duplicate elements
		while (index < set.length - 1 && set[index] == set[index + 1]) {
			index++;
		}
		subSets(set, sb, index + 1, result);
	}
}

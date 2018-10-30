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
	  * Input: [a,b,b]
	  *                                     		         [ ]
	  *                     		          /                                 \
	  * index = 0 (for a)                a                                   [ ]
	  *                     			 /      \                             /     \
	  *         1 (for b1)          a b1       a                         b1       [ ]
	  *                 			 /      \       \ skip b2             /    \       \
	  *         2 (for b2)   [a b1 b2]   [a b1]   [a]              [b1, b2] [b1]     [ ]
	  *         
	  *         
	  *         
	  */
	
	public List<String> subSets(String set) {
		List<String> result = new LinkedList<>();
		if (set == null) {
			return result;
		}
		char[] arraySet =  set.toCharArray();
		Arrays.sort(arraySet); // See sort() to character array for the first time 
		StringBuilder sb = new StringBuilder();
		dfs(arraySet, sb, 0, result);
		return result;
	}
	
	private void dfs(char[] set, StringBuilder sb, int index, List<String> result) {
		if (index == set.length) { // base case
			result.add(sb.toString());
			return;
		}
		// Case1: Add set[index]
		dfs(set, sb.append(set[index]), index + 1, result);
		sb.deleteCharAt(sb.length() - 1);
		
		// skip all the consecutive and duplicate elements
		while (index < set.length - 1 && set[index] == set[index + 1]) {
			index++;
		}
		
		// Case2: Do not add set[index]
		dfs(set, sb, index + 1, result);
	}
}

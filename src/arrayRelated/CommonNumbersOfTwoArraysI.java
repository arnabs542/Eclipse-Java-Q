/*
 * Created Date: August 15, 2018
 * 
 * Question - Common Numbers Of Two Arrays I:
 *   Find all numbers that appear in both of the two unsorted arrays, 
 *     return the common numbers in increasing order.
 *   
 *   Example: 
 * 
 */

package arrayRelated;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CommonNumbersOfTwoArraysI {

	public List<Integer> common(List<Integer> a, List<Integer> b)  {
		Set<Integer> set = new HashSet<>(a);
		List<Integer> res = new ArrayList<>();
		for (int item : b) {
			if (set.contains(item)) {
				res.add(item);
			}
		}		
		Collections.sort(res);		
		return res;
	}	
}

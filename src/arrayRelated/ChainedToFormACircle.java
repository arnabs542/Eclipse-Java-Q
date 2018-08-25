/*
 * Created Date: August 19, 2018
 * 
 * Question - :
 *   
 *   
 *   Example: 
 * 
 */

package arrayRelated;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChainedToFormACircle {
	public boolean checkChained(List<String> list) {
		
		// use a map to record visited string. value = 1: only visited the start char; 2 only visited end char; 3 both visited
		Map<String, Integer> visited = new HashMap<>(); 
		
		for (int i = 0; i < list.size(); i++) {
			int startChar = list.get(i).charAt(0);
			int endChar = list.get(i).charAt(list.get(i).length() - 1);
			
			for (int k = i + 1; k < list.size(); k++) {
				String compare = list.get(k);
				
				// find if any other string contains a end char that matches the startChar
				if (!visited.containsKey(compare) && compare.charAt(compare.length() - 1) == startChar) {
					visited.put(compare, 2);
				} else if (visited.containsKey(compare) && visited.get(compare) == 1 && compare.charAt(compare.length() - 1) == startChar) {
					visited.put(compare, 3);
				} else {
					return false;
				}
				
				// find if any other string contains a start char that matches the endChar
				if (!visited.containsKey(compare) && compare.charAt(0) == endChar) {
					visited.put(compare, 1);
				} else if (visited.containsKey(compare) && visited.get(compare) == 2 && compare.charAt(0) == endChar) {
					visited.put(compare, 3);
				} else {
					return false;
				}
			}
		}
		return true;
	}

}

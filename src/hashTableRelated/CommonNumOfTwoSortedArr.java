/*
 * Created Date: June 25, 2018
 * 
 * Question - Common Numbers Of Two Sorted Array
 *   Find all numbers that appear in both of two sorted arrays
 *    (the two arrays are all sorted in ascending order). 
 *    
 *   Example:
 *     A = {1, 1, 2, 2, 3}, B = {1, 1, 2, 5, 6}, common numbers are [1, 1, 2] 
 *   
 *   Follow up:
 *   
 *   Mirror Question:
 *   
 * Updated:
 * 
 */

package hashTableRelated;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CommonNumOfTwoSortedArr {
	
  public List<Integer> common(List<Integer> A, List<Integer> B) {
	    List<Integer> result = new LinkedList<>();
	    Map<Integer, Integer> mapA = new HashMap<>();
	    for (int item : A) {
	      if (!mapA.containsKey(item)){
	        mapA.put(item, 0);
	      } else {
	        int freq = mapA.get(item);
	        freq++;
	        mapA.put(item, freq);
	      }
	    }   
	    for (int item : B) {  
	      if (mapA.containsKey(item) && mapA.get(item) != 0) {
	        result.add(item);
	        int freq = mapA.get(item);
	        freq--;
	        mapA.put(item, freq);
	      }
	    }
	    return result;
	  }

}

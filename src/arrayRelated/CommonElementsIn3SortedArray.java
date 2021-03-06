/*
 * Created Date: August 18, 2018
 * 
 * Question - Common Elements In Three Sorted Array:
 *   Find all common elements in 3 sorted arrays.
 *   
 *   Example: 
 *     A = {1, 2, 2, 3}, B = {2, 2, 3, 5}, C = {2, 2, 4}, the common elements are [2, 2]
 * 
 */

package arrayRelated;

import java.util.ArrayList;
import java.util.List;

public class CommonElementsIn3SortedArray {
	
	public List<Integer> common(int[] a, int[] b, int[] c) {
	    List<Integer> res = new ArrayList<>();
	    int ai = 0;
	    int bi = 0;
	    int ci = 0;
	    while (ai != a.length && bi != b.length && ci != c.length) {
	      if (a[ai] == b[bi] && b[bi] == c[ci]) {
	        res.add(a[ai]);
	        ai++;
	        bi++;
	        ci++;
	      } else if (a[ai] <= b[bi] && a[ai] <= c[ci]) {
	        ai++;
	      } else if (b[bi] <= a[ai] && b[bi] <= c[ci]) {
	        bi++;
	      } else {
	        ci++;
	      }
	    }
	    return res;
	}
}

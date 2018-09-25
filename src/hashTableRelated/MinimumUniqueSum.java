/*
 * == Created Date ==
 * September 25, 2018
 * 
 * == Question - Minimum Unique Sum ==
 *   
 * == Notes == 
 * LinkedIn OA 
 * 
 */
package hashTableRelated;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinimumUniqueSum {
	
	static int getMinimumUniqueSum(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> dup = new ArrayList<>();       
        int sum = 0;
        for (int ele : arr) {
            sum += ele;
            if (map.containsKey(ele)) {
                if (map.get(ele) == 1) { 
                	    dup.add(ele);
                }
                map.put(ele, map.get(ele) + 1);
            } else {
                map.put(ele, 1);
            }
        }        
        Collections.sort(dup, Collections.reverseOrder());
        for (int cur : dup) {
            int count = map.get(cur);
            for (int j = 0; j < count - 1; j++) {
                int base = 1;
                while (true) {
                    if (!map.containsKey(cur + base)){
                        map.put(cur + base, 1);
                        sum += base;
                        break;
                    } 
                    base++;
                }         
            }
        }
        System.out.println(sum);
        return sum;
    }
	
	/* Mistakes:
	   forgot Collections.sort(), use this in oa:
	    
	  	```
	  	Integer[] dupArray = new Integer[dup.size()];
	  	dup.toArray(dupArray);
	  	Arrays.sort(dupArray, Collections.reverseOrder());
	  	``` 
	 */
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
				
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		int[] arr = {3, 2, 1, 2, 2, 7};
		MinimumUniqueSum.getMinimumUniqueSum(arr);
		
		/* Test Case 2 */
		System.out.println("\n---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
				
	}

}

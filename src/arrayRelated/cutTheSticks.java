/*
 * == Created Date ==
 * September 25, 2018
 * 
 * == Question - Cut The Sticks (easy) ==
 *   
 * == Notes == 
 * LinkedIn OA 
 * 
 */

package arrayRelated;

import java.util.*;

public class cutTheSticks {
	
    public static List<Integer> cutSticks(List<Integer> lengths) {
        List<Integer> result = new ArrayList<>();
        while (!lengths.isEmpty()) {
            result.add(lengths.size());
            int min = findMin(lengths);
            for (int i = 0; i < lengths.size(); i++) {
            		lengths.set(i, lengths.get(i) - min);
                if (lengths.get(i) == 0) {                   
                    lengths.remove(i);
                } 
            }
        }
		for (int a : result) {
			System.out.print(a + " ");
		}
		
        return result;
     }
    
    private static Integer findMin (List<Integer> lengths) {
        int tempMin = Integer.MAX_VALUE;
        for (int ele : lengths) {
            tempMin = Math.min(tempMin, ele);
        }
        return tempMin;
    }
    
    // -- < Same problem, different signature > -- //
    static int[] cutSticks(int[] arr) {
        int zeroNum = 0;
        List<Integer> resList = new ArrayList<>();
        while (zeroNum != arr.length) {
            resList.add(arr.length - zeroNum);
            int min = findMin(arr);
            // == Mistake == Use for (int ele : arr) {} for the below loop
            // change ele won't affect the elements in the original array
            for (int i = 0; i < arr.length; i++) { 
                if (arr[i] != 0) {
                    arr[i] -= min;
                    if (arr[i] == 0) {
                        zeroNum++;
                    }
                }
            }
        }
        int[] res = new int[resList.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = resList.get(i);
        }
        return res;
    }
    
    static private int findMin(int[] arr) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                min = Math.min(min, arr[i]);
            }
        }
        return min;
    }
    
	// Time Complexity: O(n ^ 2);
	// Space Complexity: O(n);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
				
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		int[] arr = {3, 2, 1, 2, 7};
		int[] arr1 = cutTheSticks.cutSticks(arr);
		for (int a : arr1) {
			System.out.print(a + " ");
		}
		
		/* Test Case 2 */
		System.out.println("\n---< Test Case 2 >---");
		
		List<Integer> lengths = new ArrayList<>();
		lengths.add(5);
		lengths.add(4);
		lengths.add(4);
		lengths.add(2);
		lengths.add(2);
		lengths.add(8);
		cutTheSticks.cutSticks(lengths);
	}
}

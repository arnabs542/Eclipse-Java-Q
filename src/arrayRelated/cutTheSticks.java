package arrayRelated;

import java.util.ArrayList;
import java.util.List;

public class cutTheSticks {
    static int[] cutTheSticks(int[] arr) {
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
    
	// Time Complexity: O(?);
	// Space Complexity: O(?);
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
				
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		int[] arr = {5, 4, 4, 2, 2, 8};
		int[] arr1 = cutTheSticks.cutTheSticks(arr);
		for (int a : arr1) {
			System.out.print(a + " ");
		}
		
		/* Test Case 2 */
		System.out.println("\n---< Test Case 2 >---");
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
}

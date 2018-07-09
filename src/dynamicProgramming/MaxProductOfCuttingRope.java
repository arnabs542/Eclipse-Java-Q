/*
 * Created Date: July 7, 2018
 * 
 * Question - Max Product Of Cutting Rope:
 *   Given a rope with positive integer-length n, 
 *    how to cut the rope into m integer-length parts with length p[0], p[1], ...,p[m-1], 
 *    in order to get the maximal product of p[0]p[1] ... p[m-1]? 
 *   m is determined by you and must be greater than 0 (at least one cut must be made). 
 *   Return the max product you can have.
 *   
 *  Assumptions:
 *    n >= 2
 *     
 *  Examples
 *    n = 12, the max product is 3 3 3 3 = 81
 *     (cut the rope into 4 pieces with length of each is 3).
 *     
 * Updated:
 *   July 8, 2018: Method 3
 * 
 */

package dynamicProgramming;

public class MaxProductOfCuttingRope {
	
	/* -----< Method 1: Recursion / DFS >----------
	 * 
	 * define MP(n): largest product if at least one cut must be made
	 * 
	 *                                   MP(5)
	 *                             
	 *       /                     |                  |               \
	 *       
	 *  _ _ _ _ | _             _ _ _ | _ _         _ _ | _ _ _       _ | _ _ _ _
	 *        4 * 1                 3 * 2             2 * 3           1 * 4   
	 *     
	 * max{4, MP(4)} * 1      max{3, MP(3)} * 2    max{2, MP(2)} * 3   max{1, MP(1)} * 4 
	 *    no cut, cut   
	 *       /  |  \
	 * ....
	 * 
	 * 
	 * Time Complexity: O(n!) = O(2^n)
	 * Space Complexity: O(n)
	 * 
	 **/
	
	public int maxProductMeth1(int len) {
		if (len <= 1) { // base case, invalid situation actually
			return 0; 
		}
		int result = 0;
		for (int i = 1; i < len; i++) {
			int tempMax = Math.max(len - i, maxProductMeth1(len - i));
			result = Math.max(result, tempMax * i);
		}
		return result;
	}
	
	
	/* -----< Method 2: DPA - big left and big right >----------
	 * 
	 * Time Complexity: O(n^2) 
	 * Space Complexity: O(n)
	 * 
	 * size = 1
	 *   M[1] = invalid
	 * 
	 * size = 2: 
	 *   case:  _ | _       
	 *     M[2] = max{1, M[1]} * max{1, M[1]} = 1
	 *       big left part , big right part
	 *       
	 * size = 3:
	 *   case 1: _ | _ _ 
	 *     tempMax: max{1, M[1]} * max{2, M[2]} = 2
	 *     
	 *   case 2: _ _ | _
	 *     tempMax: max{2, M[2]} * max{1, M[1]} = 2
	 *     
	 *     M[3] = curMax = max{case1, case2} = max(2, 2) = 2
	 * 
	 * size = 4 ...
	 * 
	 */
	
	public int maxProductMeth2(int len) {
		int[] M = new int[len + 1];
		M[0] = 0;
		M[1] = 0;
		for (int i = 1; i <= len; i++) {
			int curMax = 0;
			for (int k = 1; k <= i / 2; k++) {
				int tempMax = Math.max(k, M[k]) * Math.max(i - k, M[i - k]);
				curMax = Math.max(curMax, tempMax);
			}
			M[i] = curMax;
		}
		return M[len];	
	}
		
	/* -----< Method 3: DPB - big left and small right >----------
	 * 
	 * size = 1
	 *   M[1] = 0 (invalid)
	 * 
	 * size = 2: 
	 *   case:   _ | _    
	 *        M[1] * 1   
	 *     M[2] = max{1, M[1]} * 1} = 1
	 *       big left part , small right part
	 *       
	 * size = 3:
	 *   case 1:   _ | _ _ 
	 *          M[1] * 2
	 *     tempMax: max{1, M[1]} * 2]} = 2
	 *     
	 *   case 2: _ _ | _
	 *         M[2]} * 1
	 *     tempMax: max{2, M[2]} * 1} = 2
	 *     
	 *     M[3] = curMax = max{case1, case2} = max(2, 2) = 2
	 *     
	 * size = 4:
	 *   case 1: _ | _ _ _
	 *     tempMax: max{1, M[1]} * 3} = 3
	 *     
	 *   case 2: _ _ | _ _
	 *     tempMax: max{2, M[2]} * 2} = 4
	 *     
	 *   case 3: _ _ _ | _
	 *     tempMax: max{3, M[3]} * 1} = 3
	 *     
	 *     M[4] = curMax = max{case 1, case 2, case 3} = max(3, 4, 3) = 4
	 *     
	 * size = 5:
	 * ....
	 */
	
	public int maxProductMeth3(int len) {
		int[] M = new int[len + 1];
		M[0] = 0;
		M[1] = 0;
		for (int i = 1; i <= len; i++) {
			int curMax = 0;
			for (int k = 1; k < i; k++) {
				int tempMax = Math.max(k, M[k]) * (i - k);
				curMax = Math.max(curMax, tempMax);
			}
			M[i] = curMax;
		}
		return M[len];	
	}
	
	
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		MaxProductOfCuttingRope testObj = new MaxProductOfCuttingRope();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		
		int result1A = testObj.maxProductMeth1(12);
		int result1B = testObj.maxProductMeth2(12);
		int result1C = testObj.maxProductMeth3(12);
		System.out.println(result1A); // expected: 81
		System.out.println(result1B); 
		System.out.println(result1C);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		
		int result2A = testObj.maxProductMeth1(5);
		int result2B = testObj.maxProductMeth2(5);
		int result2C = testObj.maxProductMeth3(5);
		System.out.println(result2A); // expected: 6
		System.out.println(result2B); 
		System.out.println(result2C); 
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}
	
}

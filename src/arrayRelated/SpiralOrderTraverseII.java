/*
 * == Created Date ==
 * August 4, 2018
 * 
 * == Question - Spiral Order Traverse II ==
 * Traverse an M  N 2D array in spiral order clock-wise starting from the top left corner. 
 * Return the list of traversal sequence.
 *    
 * == Example ==
 * { 
 *   {1,  2,  3,  4},
 *   {5,  6,  7,  8},
 *   {9, 10, 11, 12} 
 * }
 * 
 * The traversal sequence is [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]
 * 
 * == Note ==
 * October 10, 2018: Add iterative solution 
 * 
 */

package arrayRelated;

import java.util.ArrayList;
import java.util.List;

public class SpiralOrderTraverseII {
	
	/* ------------------------< Solution - Recuriosn >----------------- */ 
	
	public List<Integer> spiralMeth1(int[][] matrix) {
		List<Integer> result = new ArrayList<>();
		helper(matrix, matrix.length, matrix[0].length, 0, result);
		return result;
	}
	
	private void helper(int[][]matrix, int rowRem, int colRem, int offset, List<Integer> res) {
		if (rowRem <= 0 || colRem <= 0) {
			return;
		}
		
		int rowEnd = matrix.length - 1;
		int colEnd = matrix[0].length - 1;
				
		if (rowRem == 1) { // corner case, such as spiralGenerate(1, 3);
			for (int i = offset; i <= colEnd - offset; i++) {
				res.add(matrix[offset][i]);
			}
			return;
		} else if (colRem == 1) { // corner case, such as spiralGenerate(3, 1);
			for (int i = offset; i <= rowEnd - offset; i++) {
				res.add(matrix[i][0]);
			}
			return;
		} 
		
		// up row 
		for (int i = offset; i < colEnd - offset; i++) {
			res.add(matrix[offset][i]);
		}
			
		// right column
		for (int i = offset; i < rowEnd - offset; i++) {
			res.add(matrix[i][colEnd - offset]);
		}	

		// bottom row
		for (int i = colEnd - offset; i > offset; i--) {
			res.add(matrix[rowEnd - offset][i]);
		}
		
		// left column		
		for (int i = rowEnd - offset; i > offset; i--) {
			res.add(matrix[i][offset]);
		}
		
		helper(matrix, rowRem - 2, colRem - 2, offset + 1, res);
	}
	
	/* ------------------------< Solution - Iteration >----------------- 
	 *
	 * {  left         right
	 *     {1,  2,  3,  4},  up
	 *     {5,  6,  7,  8},
	 *     {9, 10, 11, 12}   down
	 * }
	 * 
	 */
    public List<Integer> spiralMeth2(int[][] matrix) {
    		List<Integer> result = new ArrayList<>(); 
    		if (matrix.length == 0) {
    			return result;
    		}
    		
    		int left = 0;
    		int right = matrix[0].length - 1;
    		int up = 0;
    		int down = matrix.length - 1;

        while (left <= right && up <= down) {
            for (int col = left; col <= right; col++) { // up row
            		result.add(matrix[up][col]);
            }
            for (int row = up + 1; row <= down; row++) { // right col
        			result.add(matrix[row][right]);
            }
            
            if (up < down && left < right) {
                for (int col = right - 1; col > left; col--) { // down row
            			result.add(matrix[down][col]);
                }

                for (int row = down; row > up; row--) { // left col
            			result.add(matrix[row][left]);
                }            
            }
            up++;
            right--;
            down--;
            left++;
        }
        return result;
    }
	
	/* ----------------------< test stub >-------------------------*/
	private static void print(List<Integer> list) {
		for (int item : list) {
			System.out.print(item + " ");
		} 
		System.out.println();
	}
	
	public static void main(String[] args) {
		
		SpiralOrderTraverseII testObj = new SpiralOrderTraverseII();
		
		/* -----------------------< Test Case 0 >--------------------------------*/
		System.out.println("---< Test Case 0 >---");
		
		int[][] m0 = {{1},{2},{3}};	
		
		List<Integer> result = testObj.spiralMeth1(m0);	
		print(result); // expected: 1, 2, 3
		
		result = testObj.spiralMeth2(m0);	
		print(result); // expected: 1, 2, 3
		
		/* -----------------------< Test Case 1 >--------------------------------*/
		System.out.println("\n---< Test Case 1 >---");
		
		int[][] m1 = {
				      {1,2,3}, 
				      {4,5,6}, 
				      {7,8,9}
				     };
		
		result = testObj.spiralMeth1(m1);
		print(result); // expected: 1, 2, 3, 6, 9, 8, 7, 4, 5
		
		result = testObj.spiralMeth2(m1);
		print(result); // expected: 1, 2, 3, 6, 9, 8, 7, 4, 5
		
		/* -----------------------< Test Case 2 >--------------------------------*/
		System.out.println("\n---< Test Case 2 >---");
		
		int[][] m2 = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}};	
		
		result = testObj.spiralMeth1(m2);	
		print(result);// expected: 1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7
		
		result = testObj.spiralMeth2(m2);	
		print(result);// expected: 1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7
		
		/* -----------------------< Test Case 3 >--------------------------------*/
		System.out.println("\n---< Test Case 3 >---");
		
		int[][] m3 = {{1,2,3,4}};		
		result = testObj.spiralMeth1(m3);		
		print(result); // expected: 1, 2, 3, 4
		
		result = testObj.spiralMeth2(m3);		
		print(result); // expected: 1, 2, 3, 4
		
		/* -----------------------< Test Case 4 >--------------------------------*/
		System.out.println("\n---< Test Case 4 >---");
		
		int[][] m4 = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15}};		
		List<Integer> l4 = testObj.spiralMeth1(m4);
		
		print(l4);// expected: 1, 2, 3, 4, 5, 10, 15, 14, 13, 12, 11, 6, 7, 8, 9
		
	}

}

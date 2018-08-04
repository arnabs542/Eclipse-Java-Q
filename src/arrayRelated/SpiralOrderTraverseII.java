/*
 * Created Date: August 4, 2018
 * 
 * Question - Spiral Order Traverse II:
 *   Traverse an M  N 2D array in spiral order clock-wise starting from the top left corner. 
 *   Return the list of traversal sequence.
 *    
 *   Example: 
 *     { {1,  2,  3,  4},
 *       {5,  6,  7,  8},
 *       {9, 10, 11, 12} }
 *     the traversal sequence is [1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]
 * 
 */

package arrayRelated;

import java.util.ArrayList;
import java.util.List;

public class SpiralOrderTraverseII {
	
	public List<Integer> spiral(int[][] matrix) {
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
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		SpiralOrderTraverseII testObj = new SpiralOrderTraverseII();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		int[][] m0 = {{1},{2},{3}};	
		List<Integer> l0 = testObj.spiral(m0);
		
		for(int item : l0) {
			System.out.print(item + " ");
		} // expected: 1, 2, 3
		
		/* Test Case 1 */
		System.out.println("\n---< Test Case 1 >---");
		
		int[][] m1 = {{1,2,3}, {4,5,6}, {7,8,9}};
		List<Integer> l1 = testObj.spiral(m1);
		
		for(int item : l1) {
			System.out.print(item + " ");
		} // expected: 1, 2, 3, 6, 9, 8, 7, 4, 5
		
		/* Test Case 2 */
		System.out.println("\n---< Test Case 2 >---");
		
		int[][] m2 = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}};		
		List<Integer> l2 = testObj.spiral(m2);
		
		for(int item : l2) {
			System.out.print(item + " ");
		} // expected: 1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7
		
		/* Test Case 3 */
		System.out.println("\n---< Test Case 3 >---");
		
		int[][] m3 = {{1,2,3,4}};		
		List<Integer> l3 = testObj.spiral(m3);
		
		for(int item : l3) {
			System.out.print(item + " ");
		} // expected: 1, 2, 3, 4
		
		/* Test Case 4 */
		System.out.println("\n---< Test Case 4 >---");
		
		int[][] m4 = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15}};		
		List<Integer> l4 = testObj.spiral(m4);
		
		for(int item : l4) {
			System.out.print(item + " ");
		} // expected: 1, 2, 3, 4, 5, 10, 15, 14, 13, 12, 11, 6, 7, 8, 9
		
	}

}

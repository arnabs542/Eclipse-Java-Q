/*
 * Created Date: July 30, 2018
 * 
 * Question - Spiral Order Generate II (medium):
 *   Generate an M  N 2D array in spiral order clock-wise starting from the top left corner, 
 *   using the numbers of 1, 2, 3, …, M  N in increasing order.
 *   
 *   Example: 
 *     M = 3, N = 4, the generated matrix is
 *      { {1,  2,  3,  4}
 *        {10, 11, 12, 5},
 *        {9,  8,  7,  6} }
 *  
 *  Updated:
 *    August 4, 2018: Review
 */

package arrayRelated;

public class SpiralOrderGenerateII {
	
	public void spiralGenerate(int m, int n) {
		int[][] matrix = new int[m][n];
		helper(matrix, m, n, 0, 1);
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.print("\n");
		}
	}

	private void helper(int[][]matrix, int rowRem, int colRem, int offset, int num) {
		if (rowRem <= 0 || colRem <= 0) {
			return;
		}
		
		int rowEnd = matrix.length - 1;
		int colEnd = matrix[0].length - 1;	
		
		if (rowRem == 1) { // corner case, such as spiralGenerate(1, 3); or only one row remains
			for (int i = offset; i <= colEnd - offset; i++) {
				matrix[offset][i] = num++;
			}
			return;
		} else if (colRem == 1) { // corner case, such as spiralGenerate(3, 1);
			for (int i = offset; i <= rowEnd - offset; i++) {
				matrix[i][0] = num++;
			}
			return;
		} 
		
		// up row 
		for (int i = offset; i < colEnd - offset; i++) {
			matrix[offset][i] = num++;
		}
			
		// right column
		for (int i = offset; i < rowEnd - offset; i++) {
			matrix[i][colEnd - offset] = num++;
		}

		// bottom row
		for (int i = colEnd - offset; i > offset; i--) {
			matrix[rowEnd - offset][i] = num++;
		}
		
		// left column		
		for (int i = rowEnd - offset; i > offset; i--) {
			matrix[i][offset] = num++;
		}
		
		helper(matrix, rowRem - 2, colRem - 2, offset + 1, num);
	}
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		SpiralOrderGenerateII testObj = new SpiralOrderGenerateII();
		
		/* Test Case 0 */ System.out.println("---< Test Case 0 >---");
		testObj.spiralGenerate(3, 1);
		
		/* Test Case 1 */ System.out.println("---< Test Case 1 >---");
		testObj.spiralGenerate(1, 3);
		
		/* Test Case 2 */ System.out.println("---< Test Case 2 >---");
		testObj.spiralGenerate(3, 2);
		
		/* Test Case 3 */ System.out.println("---< Test Case 3 >---");	
		testObj.spiralGenerate(3, 5);
		
		/* Test Case 4 */ System.out.println("---< Test Case 4 >---");	
		testObj.spiralGenerate(3, 3);
	}
}

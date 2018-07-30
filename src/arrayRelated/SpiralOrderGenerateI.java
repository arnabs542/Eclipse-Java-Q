/*
 * Created Date: July 30, 2018
 * 
 * Question - Spiral Order Generate I (medium):
 *   Generate an N N 2D array in spiral order clock-wise starting from the top left corner, 
 *   using the numbers of 1, 2, 3, …, N N in increasing order.
 *   
 *   Example: 
 *   N = 3, the generated matrix is
 *   { {1,  2,  3}
 *     {8,  9,  4},
 *     {7,  6,  5} }
 *     
 *   Follow up: II
 *   
 *   Mirror Question:
 *     Spiral Order Traverse I (medium)
 * 
 */

package arrayRelated;

public class SpiralOrderGenerateI {
	
	public void Generate(int n) {
		int[][] matrix = new int[n][n];
		helper(matrix, n, 0, 0);	
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.print("\n");
		}
	}
	
	private void helper(int[][]m, int size, int offset, int num) {
		if (size <= 1) {
			if (size == 1) {
				m[offset][offset] = num++;
			}
			return;
		}
		int rowEnd = m.length - 1;
		int colEnd = m.length - 1;
		
		for (int i = offset; i < colEnd - offset; i++) {
			m[offset][i] = num++;
		}
		
		for (int i = offset; i < rowEnd - offset; i++) {
			m[i][colEnd - offset] = num++;
		}
		
		for (int i = colEnd - offset; i > offset; i--) {
			m[rowEnd - offset][i] = num++;
		}
		
		for (int i = rowEnd - offset; i > offset; i--) {
			m[i][offset] = num++;
		}
		
		helper(m, size - 2, offset + 1, num);
	}
	
	/* ----------------------< test stub >-------------------------*/
	public static void main(String[] args) {
		
		SpiralOrderGenerateI testObj = new SpiralOrderGenerateI();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");
		testObj.Generate(3);
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		testObj.Generate(4);
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");	
	}
}

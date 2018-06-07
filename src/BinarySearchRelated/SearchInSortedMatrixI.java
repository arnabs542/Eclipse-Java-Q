/*
 * Created Date: June 7,2018
 * 
 * Question - Search In Sorted Matrix I:
 * 	Given a 2D matrix that contains integers only, which each row is sorted in an ascending order. 
 *  The first element of next row is larger than (or equal to) the last element of previous row.
 *  Given a target number, returning the position that the target locates within the matrix. 
 *  If the target number does not exist in the matrix, return {-1, -1}.
 *  
 *  Assumption: 
 * 		The given matrix is not null, and has size of N * M, where N >= 0 and M >= 0.
 * 
 * 	Examples:
 * 			matrix = { {1, 2, 3}, {4, 5, 7}, {8, 9, 10} },
 * 			target = 7, return {1, 2}
 * 			target = 6, return {-1, -1} to represent the target number does not exist in the matrix.
 * 
 */


package BinarySearchRelated;

public class SearchInSortedMatrixI {
	
	public static int[] search(int[][] matrix, int target) {
		// corner case omit...
	    
	    int leftBound = 0;
	    int rightBound = matrix.length * matrix[0].length - 1;	
	    int col = matrix[0].length;
	    int[] result = {-1, -1};
	    
	    while (leftBound <= rightBound) {
	      int mid = leftBound + (rightBound - leftBound) / 2;
	      int m = mid / col;
	      int n = mid % col;
	      
	      if (matrix[m][n] == target) {
	        result[0] = m;
	        result[1] = n;
	        return result;
	      } else if (matrix[m][n] < target) {
	        leftBound = mid + 1;
	      } else {
	        rightBound = mid - 1;
	      }
	    }
	    return result;
    }
	
	public static void main(String[] args) {
		
		/* Test Case 1*/
		int[][] matrix1 = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
		int[] result1 = search(matrix1, 2);	
		System.out.println(result1[0] + ", " + result1[1]); // expected: 0, 1
		
		/* Test Case 2*/
		int[][] matrix2 = { {1,2,3,3,4}, {4,5,6,7,10}, {12,14,14,17,19}, {22,22,22,24,25}};
		int[] result2 = search(matrix2, 19);	
		System.out.println(result2[0] + ", " + result2[1]); // expected: 2, 4
	}
}

/*
 * == Created Date ==
 * Jan 27, 2019
 * 
 * == Question - Rotate Image ==
 * You are given an n x n 2D matrix representing an image.
 * Rotate the image by 90 degrees (clockwise).
 * You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. 
 * DO NOT allocate another 2D matrix and do the rotation.
 *    
 * Given input matrix =
 * [
 *   [ 5, 1, 9,11],
 *   [ 2, 4, 8,10],
 *   [13, 3, 6, 7],
 *   [15,14,12,16]
 * ], 
 *
 * Rotate the input matrix in-place such that it becomes:
 * [
 *   [15,13, 2, 5],
 *   [14, 3, 4, 1],
 *   [12, 6, 8, 9],
 *   [16, 7,10,11]
 * ]
 * 
 * == Note ==
 * LeetCode 48(M) - Rotate Image
 * 
 */

package arrayRelated;

public class RotateImage {

    public void rotate(int[][] matrix) {
        int left = 0;
        int right = matrix[0].length - 1;
        int up = 0;
        int down = matrix.length - 1;

        int[] tempCol = new int[matrix.length];
        int[] tempRow = new int[matrix[0].length];
        
        while (left <= right && up <= down) {
            
            // Step1: Process up row
            for (int col = left, i = 0, row = down; 
            		col < right; 
            		col++, i++, row--) { 
            	
                tempRow[i] = matrix[up][col]; // store up row to tempRow
                matrix[up][col] = matrix[row][left]; // replace up row with left col    
            }
            
            // Step2 : Process right col
            for (int row = up, i = 0; row < down; row++, i++) { 
        			tempCol[i] = matrix[row][right]; // store right col to tempCol  
                matrix[row][right] = tempRow[i]; // replace right col with up row
            }
            
            // Step3 : Process down row
            for (int col = right, i = 0; col > left; col--, i++) { 
        			tempRow[i] = matrix[down][col]; // store down row to tempCol
                matrix[down][col] = tempCol[i]; // replace down row with right col
            }

            // Step4: process left col
            for (int row = down, i = 0; row > up; row--, i++) { 
                matrix[row][left] = tempRow[i]; // replace left col with down row
            }
            
            up++;
            right--;
            down--;
            left++;
        }
    }
}

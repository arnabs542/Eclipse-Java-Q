/*
 * == Created Date ==
 * October 6, 2018
 * 
 * == Question - Valid Sudoku (medium) ==
 * Determine if a 9x9 Sudoku board is valid. 
 * Only the filled cells need to be validated according to the following rules:
 * 	Each row must contain the digits 1-9 without repetition.
 * 	Each column must contain the digits 1-9 without repetition.
 *  Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.  
 *   
 * == Example == 
 * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 * 
 * Input:
 *	[
 *	  ["5","3",".",".","7",".",".",".","."],
 *	  ["6",".",".","1","9","5",".",".","."],
 *	  [".","9","8",".",".",".",".","6","."],
 *	  ["8",".",".",".","6",".",".",".","3"],
 *	  ["4",".",".","8",".","3",".",".","1"],
 *	  ["7",".",".",".","2",".",".",".","6"],
 *	  [".","6",".",".",".",".","2","8","."],
 *	  [".",".",".","4","1","9",".",".","5"],
 *	  [".",".",".",".","8",".",".","7","9"]
 *	]
 *	Output: true
 *  
 * == Notes == 
 *  
 * 
 */

package hashTableRelated;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ValidSudoku {
	
	/* -- < Syntax 1, use a list of HashSet to represent each row, col and cube >--- */
	
    public boolean isValidSudokuMeth1(char[][] board) {
        List<Set<Character>> rowMap = new ArrayList<>();
        List<Set<Character>> colMap = new ArrayList<>();
        List<Set<Character>> squareMap = new ArrayList<>();
        
        for (int i = 0; i < 9; i++) {
        		rowMap.add(new HashSet<>());
        		colMap.add(new HashSet<>());
        		squareMap.add(new HashSet<>());
        }
        
        for (int row = 0; row < 9; row++) {
        		for (int col = 0; col < 9; col++) {
        			if (board[row][col] != '.') {
        				char val = board[row][col];
                    int index = (row / 3) * 3 + col / 3; 
                    // if a same element exists in the set, set.add() will return false
                    if (!rowMap.get(row).add(val) || !colMap.get(col).add(val) || !squareMap.get(index).add(val)) {
                    		return false;
                    }
        			}
        		}
        }
        return true;
    }
    
    /* -- < Syntax 2, use 2D array to represent each row, col and cube >--- 
     * 
     * int[][] row
     * 
     * row[0] = {0,0,0,0,0,0,0,1} means the first row contains a '9'
     * row[0] = {1,0,0,0,0,0,0,0} means the first row contains a '1'
     * row[0] = {1,0,0,0,0,0,1,0} means the first row contains a '1' and '8'
     * 
     * */
    
    public boolean isValidSudokuMeth2(char[][] board) {
        int[][] rows = new int[9][9];
        int[][] cols = new int[9][9];
        int[][] boxs = new int[9][9];

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] != '.') {
                    int val = board[row][col] - '0' - 1;
                    int indexOfBox = (row / 3) * 3 + col / 3;
                    if (rows[row][val] == 1 || cols[col][val] == 1 || boxs[indexOfBox][val] == 1) {
                        return false;
                    }                       
                    rows[row][val] = 1;
                    cols[col][val] = 1;
                    boxs[indexOfBox][val] = 1;
                }  
            }        
        }
        return true;
    }
    
    /* -- < Syntax 3, use 1D array and bit minipution >-- */
    public boolean isValidSudokuMeth3(char[][] board) {
        
        int[] rows = new int[9];
        int[] cols = new int[9];
        int[] cubes = new int[9];
        
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] != '.') {
                    
                    int val = (int) (1 << (board[row][col] - '1'));
                    // '1' - '1' = 0  after shifting: 000000001
                    // '2' - '1' = 1  after shifting: 000000010
                    
                    int indexOfCube = (row / 3) * 3 + col / 3;
                    if ((val & rows[row]) > 0 || (val & cols[col]) > 0 || (val & cubes[indexOfCube]) > 0) {
                        return false;
                    }    
                    
                    // update states in three maps for the current element 
                    rows[row] |= val;
                    cols[col] |= val;
                    cubes[indexOfCube] |= val;
                }
            }
        }
        return true;
    }
    
}

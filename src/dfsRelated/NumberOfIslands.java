/*
 * == Created Date ==
 * Jan 1, 2019
 * 
 * == Question - Number of Islands ==
 * 
 * == Notes == 
 * LeetCode 200 (M) 
 * 
 */


package dfsRelated;

public class NumberOfIslands {
	
    /*
     * High level : 2D matrix -> graph
     * Graph problem : find the number of connected components
     * 
     */
	
	public int numIslands(char[][] grid) {
	    int num = 0;
	    for (int row = 0; row < grid.length; row++) {
	        for (int col = 0; col < grid[0].length; col++) {
	            if (grid[row][col] == '1') {
	                num++;
	                explore(grid, row, col);
	            }
	        }
	    }
	    return num; 
	}
	
	private void explore(char[][] grid, int row, int col) {
	    if (row < 0 || row >= grid.length || 
	        col < 0 || col >= grid[0].length || 
	        grid[row][col] != '1') {
	        return;
	    }
	    
	    grid[row][col] = '2'; // if visited, marked it as '2'
	    
	    explore(grid, row + 1, col);
	    explore(grid, row - 1, col); 
	    explore(grid, row, col + 1);
	    explore(grid, row, col - 1);
	}

}

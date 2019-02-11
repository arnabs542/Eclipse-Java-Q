/*
 * == Created Date ==
 * Feb 10, 2019
 * 
 * == Question - Design Tic-Tac-Toe ==
 * 
 * == Notes == 
 * LeetCode 348(M) - Design Tic-Tac-Toe
 * 
 */
package design;

public class DesignTicTacToe {
    int[] rows;
    int[] cols;
    int diagonal;
    int antiDiagonal;
    int targetNum;

    /** Initialize your data structure here. */
    public DesignTicTacToe(int n) {
        rows = new int[n];
        cols = new int[n];
        diagonal = 0;
        antiDiagonal = 0;
        targetNum = n;
    }
    
    /** Player {player} makes a move at ({row}, {col}).
        @param row The row of the board.
        @param col The column of the board.
        @param player The player, can be either 1 or 2.
        @return The current winning condition, can be either:
                0: No one wins.
                1: Player 1 wins.
                2: Player 2 wins. */
    public int move(int row, int col, int player) {
        int sign = player == 1 ? 1 : -1;
        
        rows[row] += sign;
        cols[col] += sign;
        
        if (row == col) {
            diagonal += sign;
        }
        if (row + col == targetNum - 1) {
            antiDiagonal += sign;
        }
        
        if (Math.abs(rows[row]) == targetNum || 
            Math.abs(cols[col]) == targetNum || 
            Math.abs(diagonal) == targetNum || 
            Math.abs(antiDiagonal) == targetNum) {
            return player;
        }
        return 0;
    }
    
    /**
     * Your TicTacToe object will be instantiated and called as such:
     * TicTacToe obj = new TicTacToe(n);
     * int param_1 = obj.move(row,col,player);
     */
}

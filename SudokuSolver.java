/*
1. Make main class w/ test
    a. Print board beforehand
    b. Call method to solve board
    c. Print solved/unsolved board
2. Implement printBoard method
    a. Split board into 3x3 boxes
3. Implement 3 helper methods
    a. Check if number is in row
    b. Check if number is in column
    c. Check if number is in 3x3 box
4. Implement method that checks if all 3 helper methods return false
5. Implement method that solves board, backtracks if attempt doesn't work
 */

public class SudokuSolver {
    private static final int boardSize = 9;
    public static void main(String[] args) {
        int[][] board = {
        {7, 0, 2, 0, 5, 0, 6, 0, 0},
        {0, 0, 0, 0, 0, 3, 0, 0, 0},
        {1, 0, 0, 0, 0, 9, 5, 0, 0},
        {8, 0, 0, 0, 0, 0, 0, 9, 0},
        {0, 4, 3, 0, 0, 0, 7, 5, 0},
        {0, 9, 0, 0, 0, 0, 0, 0, 8},
        {0, 0, 9, 7, 0, 0, 0, 0, 5},
        {0, 0, 0, 2, 0, 0, 0, 0, 0},
        {0, 0, 7, 0, 4, 0, 2, 0, 3}
        };

        printBoard(board);
    }

    private static void printBoard(int[][] board) {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
}
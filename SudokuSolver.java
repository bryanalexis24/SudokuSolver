/*
 * 1. Make main class w/ test
 *    a. Print board beforehand
 *    b. Call method to solve board
 *    c. Print solved/unsolved board
 * 2. Implement printBoard method
 *    a. Split board into 3x3 boxes
 * 3. Implement 3 helper methods
 *    a. Check if number is in row
 *    b. Check if number is in column
 *    c. Check if number is in 3x3 box
 * 4. Implement method that checks if all 3 helper methods return false
 * 5. Implement method that solves board, backtracks if attempt doesn't work
 */

public class SudokuSolver {
    // Fixed integer initialized since the dimension is frequently used
    private static final int boardSize = 9;
    public static void main(String[] args) {
        /* TESTS */
        // Unsolvable Sudoku Board
        // int[][] board = {
        // {5, 1, 6, 8, 4, 9, 7, 3, 2},
        // {3, 0, 7, 6, 0, 5, 0, 0, 0},
        // {8, 0, 9, 7, 0, 0, 0, 6, 5},
        // {1, 3, 5, 0, 6, 0, 9, 0, 7},
        // {4, 7, 2, 5, 9, 1, 0, 0, 6},
        // {9, 6, 8, 3, 7, 0, 0, 5, 0},
        // {2, 5, 3, 1, 8, 6, 0, 7, 4},
        // {6, 8, 4, 2, 0, 7, 5, 0, 0},
        // {7, 9, 1, 0, 5, 0, 6, 0, 8}
        // };

        // Solvable Sudoku Board
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

        System.out.println("Solve the Sudoku:");
        printBoard(board);
        System.out.println();
        if (solveBoard(board))
            System.out.println("Sudoku solved!");
        else
            System.out.println("Unsolvable Sudoku...");
        printBoard(board);
    }
/* ------------------------------------------------------------------------- */
    /* 
     * Method prints the board.
     */
    private static void printBoard(int[][] board) {
        for (int i = 0; i < boardSize; i++) {
            // Split board horizontally to make 3x3 boxes
            if (i % 3 == 0 && i != 0)
                System.out.println("-----------"); //
            for (int j = 0; j < boardSize; j++) {
                // Split board vertically
                if (j % 3 == 0 && j != 0)
                    System.out.print("|");
                // Print each cell
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
/* ------------------------------------------------------------------------- */
    /* 
     * Check if the number is already present in the same row.
     */
    private static boolean inRow(int[][] board, int num, int row) {
        for (int i = 0; i < boardSize; i++) {
            if (board[row][i] == num)
                return true;
        }
        return false;
    }
/* ------------------------------------------------------------------------- */
    /*
     * Check if the number is already present in the same column.
     */
    private static boolean inCol(int[][] board, int num, int col) {
        for (int i = 0; i < boardSize; i++) {
            if (board[i][col] == num)
                return true;
        }
        return false;
    }
/* ------------------------------------------------------------------------- */
    /* 
     * Check if the number is already present in the same 3x3 box.
     */
    private static boolean inBox(int[][] board, int num, int row, int col) {
        // Find the row and column indeces for the top left cell of the 3x3 box
        int boxStartRow = row - row % 3;
        int boxStartCol = col - col % 3;
        // Begin iteration at the top left cell of the box, end once bottom 
        // right cell is reached
        for (row = boxStartRow; row < boxStartRow + 3; row++) {
            for (col = boxStartCol; col < boxStartCol + 3; col++) {
                if (board[row][col] == num)
                    return true;
            } 
        }
        return false;
    }
/* ------------------------------------------------------------------------- */
    /* 
     * Checks if placed number isn't already present in the same row, column, 
     * or 3x3 box. 
     */
    private static boolean isValidNum(int[][] board, int num, int row, 
        int col) {
        // All have to be false for the attempted placement to be valid
        return !inRow(board, num, row) && !inCol(board, num, col)
            && !inBox(board, num, row, col);
    }
/* ------------------------------------------------------------------------- */
    /*
     * Recursively solves the Sudoku board using a backtracking algorithm.
     * The board is represented by a 2D array of integers, where each cell is
     * initialized with a number (0 for empty cells).
     * Method modifies the input board, filling in the empty cells with
     * the correct values.
     * Method returns true if successfully solved, flase if it's unsolvable.
     */
    private static boolean solveBoard(int[][] board) {
        // Iterate through entire 9x9 board
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                // Check if cell is empty before deciding to place a number
                if (board[i][j] == 0) {
                    // Begin attempts, from 1-9 inclusive
                    for (int attemptNum = 1; attemptNum <= 9; attemptNum++) {
                        // Check if attempt is valid, continue iteration if not
                        if (isValidNum(board, attemptNum, i, j)) {
                            // Temporarily place number before recursive call
                            board[i][j] = attemptNum;
                            // Recursively call method w/ updated board to
                            // continue solving
                            if (solveBoard(board))
                                return true; // Return back to previous call
                            else
                            // Backtrack by restting to 0 if it didn't lead to
                            // a valid solution, and attempt new number
                                board[i][j] = 0;
                        }
                    }
                    // Return false if all numbers have been attempted in empty
                    // cell and there still isn't a valid solution. Sudoku is
                    // therefore unsolvable 
                    return false;
                }
            }
        }
        // Successfully solved
        return true;
    }
}
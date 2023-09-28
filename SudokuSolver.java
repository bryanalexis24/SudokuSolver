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
        // TESTS
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

    private static void printBoard(int[][] board) {
        for (int i = 0; i < boardSize; i++) {
            if (i % 3 == 0 && i != 0)
                System.out.println("-----------");
            for (int j = 0; j < boardSize; j++) {
                if (j % 3 == 0 && j != 0)
                    System.out.print("|");
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    private static boolean inRow(int[][] board, int num, int row) {
        for (int i = 0; i < boardSize; i++) {
            if (board[row][i] == num)
                return true;
        }
        return false;
    }

    private static boolean inCol(int[][] board, int num, int col) {
        for (int i = 0; i < boardSize; i++) {
            if (board[i][col] == num)
                return true;
        }
        return false;
    }

    private static boolean inBox(int[][] board, int num, int row, int col) {
        int boxStartRow = row - row % 3;
        int boxStartCol = col - col % 3;
        for (row = boxStartRow; row < boxStartRow + 3; row++) {
            for (col = boxStartCol; col < boxStartCol + 3; col++) {
                if (board[row][col] == num)
                    return true;
            } 
        }
        return false;
    }

    private static boolean isValidNum(int[][] board, int num, int row, int col) {
        return !inRow(board, num, row) && !inCol(board, num, col)
            && !inBox(board, num, row, col);
    }

    private static boolean solveBoard(int[][] board) {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (board[i][j] == 0) {
                    for (int attemptNum = 1; attemptNum <= 9; attemptNum++) {
                        if (isValidNum(board, attemptNum, i, j)) {
                            board[i][j] = attemptNum;

                            if (solveBoard(board))
                                return true;
                            else
                                board[i][j] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
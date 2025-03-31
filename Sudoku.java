

public class Sudoku {

    public static void main(String[] args) {
        // Example unsolved Sudoku puzzle (0 represents empty cells)
        int[][] board = {
            {5, 3, 0, 0, 7, 0, 0, 0, 0},
            {6, 0, 0, 1, 9, 5, 0, 0, 0},
            {0, 9, 8, 0, 0, 0, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 0, 3},
            {4, 0, 0, 8, 0, 3, 0, 0, 1},
            {7, 0, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 0, 0, 0, 2, 8, 0},
            {0, 0, 0, 4, 1, 9, 0, 0, 5},
            {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        if (solveSudoku(board)) {
            printBoard(board);
        } else {
            System.out.println("No solution exists.");
        }
    }

    // Function to solve the Sudoku board
    public static boolean solveSudoku(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col] == 0) { // Find an empty cell
                    for (int num = 1; num <= 9; num++) { // Try all numbers from 1 to 9
                        if (isSafe(board, row, col, num)) {
                            board[row][col] = num; // Try this number
                            if (solveSudoku(board)) {
                                return true; // Recursively try to solve the board
                            }
                            board[row][col] = 0; // Backtrack if no solution found
                        }
                    }
                    return false; // If no valid number can be placed, return false
                }
            }
        }
        return true; // All cells are filled correctly
    }

    // Function to check if it's safe to place a number in a cell
    public static boolean isSafe(int[][] board, int row, int col, int num) {
        // Check if the number is already in the row
        for (int x = 0; x < 9; x++) {
            if (board[row][x] == num) {
                return false;
            }
        }

        // Check if the number is already in the column
        for (int x = 0; x < 9; x++) {
            if (board[x][col] == num) {
                return false;
            }
        }

        // Check if the number is already in the 3x3 subgrid
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }

        return true; // If no conflicts, it's safe
    }

    // Function to print the Sudoku board
    public static void printBoard(int[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }
}

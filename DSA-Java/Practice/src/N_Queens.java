import java.util.Scanner;

public class N_Queens {
    public static boolean nqueensolver(boolean[][] board, int row) {
        if (row == board.length) {
            display(board);
            System.out.println();
            return true;
        }
        boolean ans = false;
        for (int col = 0; col < board.length; col++) {
            if (issafe(board, row, col)) {
                board[row][col] = true;
                ans = nqueensolver(board, row + 1) || ans;
                board[row][col] = false;
            }
        }
        return ans;
    }

    public static boolean issafe(boolean[][] board, int row, int col) {
//        vertical check
        for (int i = 0; i < row; i++) {
            if (board[i][col]) {
                return false;
            }
        }
//        upper left diagonal
        int left = Math.min(row, col);
        for (int i = 1; i <= left; i++) {
            if (board[row - i][col - i]) {
                return false;
            }
        }
//       upper right diagonal
        int right = Math.min(row, board.length - col - 1);
        for (int i = 1; i <= right; i++) {
            if (board[row - i][col + i]) {
                return false;
            }
        }
        return true;

    }

    public static void display(boolean[][] board) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j]) {
                    System.out.print("Q");
                } else {
                    System.out.print("X");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int t = input.nextInt();
        while (t-- > 0) {
            int n = input.nextInt();
            boolean[][] board = new boolean[n][n];
            boolean res = nqueensolver(board, 0);
            if (!res) {
                System.out.println("Solution doesn't exists");
            }
        }
    }
}

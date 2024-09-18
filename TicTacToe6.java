package ArraysAndAlgorithms;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TicTacToe6 {

    public static void main(String[] args) {
        String[][] board = {
            {"", "", ""},
            {"", "", ""},
            {"", "", ""}
        };
        
        printBoard(board);
        
        // Add user input
        Scanner scan = new Scanner(System.in);
        char[] players = {'X', 'O'};
        char player;
        int row, col;
        int count = 0;
        
       do { 
        // Determine whose turn it is
        if(count % 2 == 0) {
             // Prompt for user input (Row)
        System.out.print("Enter your desired ROW (0 - 2): ");
        row = scan.nextInt();
        // Prompt for user input (Col)
        System.out.print("Enter your desired COLUMN (0 - 2): ");
        col = scan.nextInt();
            player = 'X';
            board = updateBoard(board, player, row, col);
        } else {
            player = 'O';
            board = AIMove(board, player);   
        }
        printBoard(board);
        
        // Check for victory
        if(isVictory(board, player)) {
            System.out.println("Player " + player + " has won!");
            System.exit(0);
        }
        count++;
       }while(count < 9);
       
        System.out.println("Game Over!");
       
    }
    
    public static void printBoard(String[][] board) {
        // Rows
        for(int r = 0; r < board.length; r++) {
            // Cols
            for (int c = 0; c < board[r].length; c++) {
                // Print the grid
                if(board[r][c].equals("")) {
                    System.out.print("[ ]"); 
                } else {
                    System.out.print(board[r][c]);
                }
            }
            System.out.println("");
        }
        System.out.println("=========");
    }
    
    public static String[][] updateBoard(String[][] board, char player, int row, int col) {
        Scanner scan = new Scanner(System.in);
        if(board[row][col].equals("")) {
            board[row][col] = "[" + player + "]";
        } else {
          System.err.println("Someone was already placed there. Please try again.");
          // Prompt for user input (Row)
          System.out.print("Enter your desired ROW (0 - 2): ");
          row = scan.nextInt();
          // Prompt for user input (Col)
          System.out.print("Enter your desired COLUMN (0 - 2): ");
          col = scan.nextInt();
          // RECURSION
          board = updateBoard(board, player, row, col);
        }
       return board; 
    }
    
    public static String[][] AIMove(String[][] board, char player){
        try {
            // Create slight delay for the AI to play
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(TicTacToe6.class.getName()).log(Level.SEVERE, null, ex);
        }
        int row = (int)(Math.random() * 3);
        int col = (int)(Math.random() * 3);
        
        System.out.println("Attempting to place player at row " + row + " column " 
        + col);
        if(board[row][col].equals("")) {
            board[row][col] = "[" + player + "]";
        } else {
            AIMove(board, player);
            System.out.println("RECURSION!!!");
        }
        return board;
    }
    
    public static boolean isVictory(String[][] board, char player) {
        String p = "[" + player + "]";
        if( (board[0][0].equals("[X]") && board[0][1].equals("[X]") && board[0][2].equals("[X]")) ||
                (board[0][0].equals("[O]") && board[0][1].equals("[O]") && board[0][2].equals("[O]"))) {
            return true;
        } 
        // Second Row
        else if( (board[1][0].equals(p) && board[1][1].equals(p) && board[1][2].equals(p))) {
            return true;
        }
        // Third Row
        else if( (board[2][0].equals(p) && board[2][1].equals(p) && board[2][2].equals(p))) {
            return true;
        }
        // Diag top left down
        else if( (board[0][0].equals(p) && board[1][1].equals(p) && board[2][2].equals(p))) {
            return true;
        }
        // Diag Right
        else if( (board[0][2].equals(p) && board[1][1].equals(p) && board[2][0].equals(p))) {
            return true;
        }
         // First col down
        else if( (board[0][0].equals(p) && board[1][0].equals(p) && board[2][0].equals(p))) {
            return true;
        }
        // Second col down
        else if( (board[0][1].equals(p) && board[1][1].equals(p) && board[2][1].equals(p))) {
            return true;
        }
        // Third col down
        else if( (board[0][2].equals(p) && board[1][2].equals(p) && board[2][2].equals(p))) {
            return true;
        }
    return false;
}
    
    }

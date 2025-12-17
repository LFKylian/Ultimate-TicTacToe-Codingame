import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class TicTacToe {

    static final int PLAYER = 1;
    static final int OPPONENT = 2;

    public static boolean isFinished(int[][] grid) {
        for (int[] row : grid) {
            for (int c : row) {
                if (c == 0) {
                    return false;
                }
            }
        }

        return true;
    }

    public static List<Integer[]> getValidActionCount(int[][] grid) {
        List<Integer[]> res = new ArrayList<Integer[]>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    Integer[] temp = new Integer[2];
                    temp[0] = Integer.valueOf(i);
                    temp[1] = Integer.valueOf(j);
                    res.add(temp);
                }
            }
        }

        return res;
    }

    public static int evaluate(int[][] grid) {
        
        if ((grid[0][0] == OPPONENT && grid[0][1] == OPPONENT && grid[0][2] == OPPONENT)
            || (grid[1][0] == OPPONENT && grid[1][1] == OPPONENT && grid[1][2] == OPPONENT) 
            || (grid[2][0] == OPPONENT && grid[2][1] == OPPONENT && grid[2][2] == OPPONENT)) {
            return -100;
        }
        
        //////////////////////////////////////

        if ((grid[0][0] == OPPONENT && grid[1][0] == OPPONENT && grid[2][0] == OPPONENT)
            || (grid[0][1] == OPPONENT && grid[1][1] == OPPONENT && grid[2][1] == OPPONENT) 
            || (grid[0][2] == OPPONENT && grid[1][2] == OPPONENT && grid[2][2] == OPPONENT)) {
            return -100;
        }

        /////////////////////////////////////

        if ((grid[0][0] == OPPONENT && grid[1][1] == OPPONENT && grid[2][2] == OPPONENT) 
            || (grid[0][2] == OPPONENT && grid[1][1] == OPPONENT && grid[2][0] == OPPONENT)) {
            return -100;
        }

        // ----------------------------------
        // ----------------------------------

        if ((grid[0][0] == PLAYER && grid[0][1] == PLAYER && grid[0][2] == PLAYER)
            || (grid[1][0] == PLAYER && grid[1][1] == PLAYER && grid[1][2] == PLAYER) 
            || (grid[2][0] == PLAYER && grid[2][1] == PLAYER && grid[2][2] == PLAYER)) {
            return 100;
        }
        
        //////////////////////////////////////

        if ((grid[0][0] == PLAYER && grid[1][0] == PLAYER && grid[2][0] == PLAYER)
            || (grid[0][1] == PLAYER && grid[1][1] == PLAYER && grid[2][1] == PLAYER) 
            || (grid[0][2] == PLAYER && grid[1][2] == PLAYER && grid[2][2] == PLAYER)) {
            return 100;
        }

        /////////////////////////////////////

        if ((grid[0][0] == PLAYER && grid[1][1] == PLAYER && grid[2][2] == PLAYER) 
            || (grid[0][2] == PLAYER && grid[1][1] == PLAYER && grid[2][0] == PLAYER)) {
            return 100;
        }

        return 0;
    }

    public static int minMaxAlphaBeta(int[][] grid, boolean isPlayer, int alpha, int beta) {
        int gridValue = evaluate(grid);
        
        if (gridValue != 0 || isFinished(grid)) {
            return gridValue;
        }

        List<Integer[]> validActionCount = getValidActionCount(grid);

        if (isPlayer) {

            int row, col, score;
            int maxScore = Integer.MIN_VALUE;
            for (Integer[] validAction : validActionCount) {
                row = validAction[0];
                col = validAction[1];
                grid[row][col] = PLAYER;
                score = minMaxAlphaBeta(grid, false, alpha, beta);
                maxScore = Math.max(maxScore, score);
                alpha = Math.max(alpha, score);

                if (maxScore == 100 || beta <= alpha) {
                    break;
                }

                grid[row][col] = 0;
            }

            return maxScore;

        } else {

            int row, col, score;
            int minScore = Integer.MAX_VALUE;
            for (Integer[] validAction : validActionCount) {
                row = validAction[0];
                col = validAction[1];
                grid[row][col] = OPPONENT;
                score = minMaxAlphaBeta(grid, true, alpha, beta);
                minScore = Math.min(minScore, score);
                beta = Math.min(beta, score);

                if (minScore == -100 || beta <= alpha) {
                    break;
                }

                grid[row][col] = 0;
            }

            return minScore;

        }
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        
        int[][] grid = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 },
                { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 },
                { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };

        // game loop
        while (true) {
            int opponentRow = in.nextInt();
            int opponentCol = in.nextInt();
            
            if (opponentRow != -1) {
                grid[opponentRow][opponentCol] = OPPONENT;
            }

            int bestRow = -1;
            int bestCol = -1;
            int score;
            int bestScore = -101;

            int validActionCount = in.nextInt();
            for (int i = 0; i < validActionCount; i++) {
                int row = in.nextInt();
                int col = in.nextInt();
                grid[row][col] = PLAYER;
                score = minMaxAlphaBeta(grid, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
                
                if (bestScore < score) {
                    bestScore = score;
                    bestRow = row;
                    bestCol = col;
                }

                grid[row][col] = 0;
            }

            grid[bestRow][bestCol] = PLAYER;
            
            // Write an action using System.out.println()
            // To debug: System.err.println("Debug messages...");

            System.out.println(String.format("%d %d", bestRow, bestCol));
        }
    }
}
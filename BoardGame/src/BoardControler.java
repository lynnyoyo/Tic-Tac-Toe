/**
 * Created by yuanlin on 19/3/6.
**/

import java.util.Scanner;

public class BoardControler {
    // initialize board size and the game rule
    public static int[] setBoardRule() {
        Scanner scan = new Scanner(System.in);
        int[] initialRule = new int[4]; // 0 : rol, 1 : column, 2 : win Number, 3 : first player

        System.out.print("input the board row：");
        if (scan.hasNextInt()) {
            initialRule[0] = scan.nextInt();
            if (initialRule[0] < 3) {
                System.out.println("the input number must be bigger than 3！");
            }
        } else {
            System.out.println("the input number must be Integer！");
        }
        System.out.print("input the board column：");
        if (scan.hasNextInt()) {
            initialRule[1] = scan.nextInt();
            if (initialRule[1] < 3) {
                System.out.println("the input number must be bigger than 3！");
            }
        } else {
            System.out.println("the input number must be Integer！");
        }
        System.out.print("input the board win Number：");
        if (scan.hasNextInt()) {
            initialRule[2] = scan.nextInt();
            if (initialRule[2] > initialRule[0] || initialRule[2] > initialRule[1]) {
                System.out.println("the input number must be smaller than the board length！");
            }
        } else {
            System.out.println("the input number must be Integer！");
        }
        System.out.print("input -1 or 1 to decide who do first (-1 for black or 1 for white)：");
        if (scan.hasNextInt()) {
            initialRule[3] = scan.nextInt();
            if (initialRule[3] != -1 && initialRule[3] != 1) {
                System.out.println("the input number must be -1 or 1！");
            }
        } else {
            System.out.println("the input number must be Integer！");
        }

        return initialRule;

    }

    // play the game until find the winner or the board is full
    public static int findWinner (Board board) {
        int winner = board.getWinner();
        int[] newStep; // [0] : row, [1]: column.
        while(BoardControler.hasEmpty(board)){
            newStep = inputNewStep(board);
            if(winner == 0 ){
                int curPlayer = board.getCurrentPlayer();
                gotNewStep(board, newStep);
                board.showCurrentBoard();
                if (ifThisStepWin(board, newStep, curPlayer)) {
                    winner = curPlayer;
                    board.setWinner(curPlayer);
                    return winner;
                }
            } else {
                break;
            }
        }
        return winner;
    }

    private static int[] inputNewStep (Board board) {
        int row = board.getBoard().length;
        int col = board.getBoard()[0].length;
        int[] newStep = new int[3];
        Scanner scan = new Scanner(System.in);
        System.out.print("input the board row：");
        if (scan.hasNextInt()) {
            newStep[0] = scan.nextInt();
            if (newStep[0] < 0 || newStep[0] >= row) {
                System.out.println("the input row must be in the board！");
            }
        } else {
            System.out.println("the input number must be Integer！");
        }
        System.out.print("input the board column：");
        if (scan.hasNextInt()) {
            newStep[1] = scan.nextInt();
            if (newStep[1] < 0 || newStep[1] >= col) {
                System.out.println("the input column must be in the board！");
            }
        } else {
            System.out.println("the input number must be Integer！");
        }
        return newStep;
    }

    // whether the board is already full.
    private static boolean hasEmpty (Board board) {
        boolean hasEmpty = false;
        int[][] curBoard = board.getBoard();
        // whether the board is full
        for (int i = 0; i < curBoard.length; i++) {
            for (int j = 0; j < curBoard.length; j++) {
                if (curBoard[i][j] == 0) {
                    hasEmpty = true;
                    break;
                }
            }
        }
        return hasEmpty;
    }

    private static void gotNewStep (Board board, int[] nextPosition) {
        board.setBoard(nextPosition[0], nextPosition[1]);
        board.updatePlayer(); // next player will be the opposite player;
    }

    private static boolean CheckInBoard(Board board, int x, int y){
        if(x >= 0 && y >= 0 && x < board.getBoard().length && y < board.getBoard()[0].length){
            return true;
        }
        return false;
    }

    private static int  CountConnectedPieces(Board board, int [] Step, int [] dir, int curPlayer) {
        int cnt = 1;
        int [][] chessBoard = board.getBoard();
        for (int reverse = 1; reverse >= -1; reverse -= 2) { 
            int dx = dir[0] * reverse, dy = dir[1] * reverse;
            int tx = Step[0] + dx, ty = Step[1] + dy;
            while(CheckInBoard(board, tx, ty) && chessBoard[tx][ty] == curPlayer){
                tx += dx;
                ty += dy;
                cnt ++;
            }
        }
        return cnt;
    }

    private static boolean ifThisStepWin (Board board, int[] newStep, int me) {
        int winRule = board.getWinRule();
        int curConnetedPieces = 1;
        int curPlayer = me;
        int [][] dirs = {{1,0},{0,1},{1,1},{1,-1}};
        for(int i = 0; i < dirs.length; i ++){
            curConnetedPieces = CountConnectedPieces(board, newStep, dirs[i], curPlayer);
            if(curConnetedPieces == winRule){
                return true;
            }
        }
        return false;
    }

    public static void showGameResult (int winner) {
        if (winner == 0) {
            System.out.println("DRAW！");
        } else if (winner == 1) {
            System.out.println("White player win!");
        } else if (winner == -1) {
            System.out.println("Black player win!");
        }
    }




}

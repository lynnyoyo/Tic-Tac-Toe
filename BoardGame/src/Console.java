/**
 * Created by yuanlin on 19/3/6.
 */
import java.util.Scanner;
import java.util.*;

public class Console {
    public static void main(String[] args) {

        // initialize the board size and win rule;
        int[] initialRule = new int[4];
        // position 0 : row, position 1 : column, position 2: winNumber, position 3 : who do first
        initialRule = BoardControler.setBoardRule();
        Board board = new Board(initialRule);


        //reset a new Game
        boolean resetGame = false;
        if (resetGame == true) {
            board.resetGame();
        }

        //show current game board
        boolean showCurrentBoard = false;
        if (showCurrentBoard == true) {
            board.showCurrentBoard();
        }

        //play game until one actor win or the board is full
        int winner = 0; // winner = 0 means nobody wins, winner = -1 means black actor wins, winner = 1 means white actor wins
        winner = BoardControler.findWinner(board);

        // show game result
        BoardControler.showGameResult(winner);

    }
}

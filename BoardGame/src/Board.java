/**
 * Created by yuanlin on 19/3/6.
 */
public class Board {
    private int[][] board;     // in this game board, 0 represent empty square; 1 represent black chess pieces; -1 represent white chess pieces.
    private int currentPlayer = -1; //-1 represent the black player, 1 represent the white player. Default player is black.
    private int winner; // winner = 0 means nobody wins currently, winner = -1 means black player wins, winner = 1 means white player wins
    private int winRule; // how many pieces in a line can win the game

    private int[] moveRecord=new int[20];
    private double heuristicValue;
    private double alpha;
    private double beta;

    //initialize the board rule
    public Board(int[] initialRule){
        board = new int[initialRule[0]][initialRule[1]];
        winRule = initialRule[2];
        currentPlayer = initialRule[3];
        winner = 0; // represent no winner in the beginning

    }

    // reset the Game rule
    public void resetGame() {
        BoardControler.setBoardRule();
    }

    // show current game board
    public void showCurrentBoard() {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                System.out.printf("%3d", board[i][j]);
            }
            System.out.println();
        }

    }

    public void setWinner(int currentWinner) {
        winner = currentWinner;
    }

    public int getWinner() {
        return winner;
    }

    public void updatePlayer() {
        currentPlayer = currentPlayer * (-1);
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public void setBoard(int i, int j){
            board[i][j] = currentPlayer;
    }

    public int getWinRule() {
        return winRule;
    }

    public int[][] getBoard(){
        return board;
    }





// blow funcetions are not used
/**
    public double getalpha() {
        return alpha;
    }
    public double getbeta() {
        return beta;
    }
    public int[] getMoveRecord() {
        return moveRecord;
    }
    public double getHValue() {
        return heuristicValue;
    }

    public void setAlpha(double a) {
        alpha=a;
    }
    public void setbeta(double b) {
        beta=b;
    }
    public void setMoveRecord(int No,int position) {
        moveRecord[No]=position;
    }
    public void setHValue(double value) {
        heuristicValue = value;
    }
    public void setWholeBoard(int[][] B) {
        for(int i = 0; i < B.length;i++){
            for(int j = 0; j < B[0].length;j++)
                board[i][j] = B[i][j];
        }
    }
**/

}

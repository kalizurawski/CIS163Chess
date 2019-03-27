package ChessGame;

import java.util.Random;
import java.util.Stack;

public class ChessModel implements IChessModel {
    private IChessPiece[][] board;
    private Player player;
    private boolean checkMate = false;
    Stack<Integer> stack = new Stack<>();

    // declare other instance variables as needed

    /*************************************************************
     *
     *
     *
     ************************************************************/
    public ChessModel() {
        board = new IChessPiece[8][8];

        board[7][0] = new Rook(Player.WHITE);
        board[7][1] = new Knight(Player.WHITE);
        board[7][2] = new Bishop(Player.WHITE);
        board[7][3] = new Queen(Player.WHITE);
        board[7][4] = new King(Player.WHITE);
        board[7][5] = new Bishop(Player.WHITE);
        board[7][6] = new Knight(Player.WHITE);
        board[7][7] = new Rook(Player.WHITE);
        for (int i = 0; i < 8; i++) {
            board[6][i] = new Pawn(Player.WHITE);
        }

        board[0][0] = new Rook(Player.BLACK);
        board[0][1] = new Knight(Player.BLACK);
        board[0][2] = new Bishop(Player.BLACK);
        board[0][3] = new Queen(Player.BLACK);
        board[0][4] = new King(Player.BLACK);
        board[0][5] = new Bishop(Player.BLACK);
        board[0][6] = new Knight(Player.BLACK);
        board[0][7] = new Rook(Player.BLACK);
        for (int i = 0; i < 8; i++) {
            board[1][i] = new Pawn(Player.BLACK);
        }
        player = Player.WHITE;
    }
    /*************************************************************
     *
     *
     *
     ************************************************************/
    public boolean isComplete() {
        boolean valid = false;
        if (checkMate == true) valid = true;
        return valid;
    }
    /*************************************************************
     *
     *
     *
     ************************************************************/
    public boolean isValidMove(Move move) {
        boolean valid = false;
            if (currentPlayer() != board[move.fromRow][move.fromColumn].player())
                return valid;
            else if (board[move.fromRow][move.fromColumn] != null)
                if (board[move.fromRow][move.fromColumn].isValidMove(move, board) == true)
                    valid = true;

        return valid;
    }
    /*************************************************************
     *
     *
     *
     ************************************************************/
    public void move(Move move) {

        if(board[move.toRow][move.toColumn] != null){
            if(board[move.toRow][move.toColumn] instanceof Pawn){
                stack.push(1);
                System.out.println("you just took a pawn");
            }else if(board[move.toRow][move.toColumn] instanceof Knight){
                stack.push(2);
            }else if(board[move.toRow][move.toColumn] instanceof Bishop){
                stack.push(3);
            }else if(board[move.toRow][move.toColumn] instanceof Rook){
                stack.push(4);
            }else if(board[move.toRow][move.toColumn] instanceof Queen){
                stack.push(5);
            }
        }else{
            stack.push(0);
        }

        board[move.toRow][move.toColumn] = board[move.fromRow][move.fromColumn];

        stack.push(move.fromRow);
        stack.push(move.fromColumn);
        stack.push(move.toRow);
        stack.push(move.toColumn);


        board[move.fromRow][move.fromColumn] = null;
    }
    /*************************************************************
     *
     *
     *
     ************************************************************/
    public boolean inCheck(Player p) {
        // add in code here to scan and check if the current player is in check
        boolean valid = false;

        return valid;
    }

    /*************************************************************
     *
     *
     *
     ************************************************************/
    public Player currentPlayer() {
        return player;
    }
    /*************************************************************
     *
     *
     *
     ************************************************************/
    public int numRows() {
        return 8;
    }
    /*************************************************************
     *
     *
     *
     ************************************************************/
    public int numColumns() {
        return 8;
    }
    /*************************************************************
     *
     ************************************************************/
    public IChessPiece pieceAt(int row, int column) {
        return board[row][column];
    }
    /*************************************************************
     *
     ************************************************************/
    public void setNextPlayer() {
        player = player.next();
    }
    /*************************************************************
     *
     ************************************************************/
    public void setPiece(int row, int column, IChessPiece piece) {
        board[row][column] = piece;
    }

    public void undo(){
        Integer top = stack.peek();
        if(top == null ){
            return;
        }
        else{
            System.out.println(stack);


            int fromCol = stack.pop();
            int fromRow = stack.pop();
            int toCol = stack.pop();
            int toRow = stack.pop();
            int pieceTaken = stack.pop();

            System.out.println("from: " + fromRow + " , " + fromCol + " To: " + toRow + " , " + toCol);
            setPiece(toRow, toCol, pieceAt(fromRow, fromCol));
            if(pieceTaken == 1) board[fromRow][fromCol] = new Pawn(currentPlayer());
            else if(pieceTaken == 2)board[fromRow][fromCol] = new Knight(currentPlayer());
            else if(pieceTaken == 3)board[fromRow][fromCol] = new Bishop(currentPlayer());
            else if(pieceTaken == 4)board[fromRow][fromCol] = new Rook(currentPlayer());
            else if(pieceTaken == 5)board[fromRow][fromCol] = new Queen(currentPlayer());
            else board[fromRow][fromCol] = null;
        }
        setNextPlayer();
    }

    public boolean canMakeMove(int x, int y){
        boolean valid = false;
                if (board[x][y] instanceof Pawn){

                }
                else if (board[x][y] instanceof Knight){

                }
                else if (board[x][y] instanceof Rook){

                }
                else if (board[x][y] instanceof Bishop){

                }
                else if (board[x][y] instanceof Queen){

                }
                else if (board[x][y] instanceof King){

                }
        return valid;
    }

    /*************************************************************
     *
     ************************************************************/
    public void AI() {
        /*
         * Write a simple AI set of rules in the following order.
         * a. Check to see if you are in check.
         *      i. If so, get out of check by moving the king or placing a piece to block the check
         *
         * b. Attempt to put opponent into check (or checkmate).
         *      i. Attempt to put opponent into check without losing your piece
         *      ii. Perhaps you have won the game.
         *
         *c. Determine if any of your pieces are in danger,
         *      i. Move them if you can.
         *      ii. Attempt to protect that piece.
         *
         *d. Move a piece (pawns first) forward toward opponent king
         *      i. check to see if that piece is in danger of being removed, if so, move a different piece.
         */
        int posX = 10;
        int posY = 10;
        Random rn = new Random();

            if(currentPlayer() == Player.BLACK){
                // add in a function of randomly moving valid pieces to spots where they cannot be taken/ can take the king.
                if (inCheck(Player.BLACK)){
                    // try to move king
                }

            }


        // if a piece can be taken, try to move it (potentially add in a priority for more important pieces, or just go for first one scanned)

        //if everything is fine from above, randomly move a piece forward.

    }
}

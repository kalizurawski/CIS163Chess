package ChessGame;

import java.util.Random;
import java.util.Stack;

public class ChessModel implements IChessModel {
    private IChessPiece[][] board;
    private Player player;
    private boolean checkMate = false;
    Stack<Integer> stack = new Stack<>();
    int movex, movey;

    // declare other instance variables as needed

    /*************************************************************
     *ChessModel (constructor)
     *
     * Description
     ************************************************************/
    public ChessModel() {
        board = new IChessPiece[8][8];// create the board

        board[7][0] = new Rook(Player.WHITE); // add in all White peices
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

        board[0][0] = new Rook(Player.BLACK); // add in all black pieces
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
     *isComplete
     *
     *Description:
     ************************************************************/
    public boolean isComplete() {
        boolean valid = false; // create boolean valid
        if (checkMate == true) valid = true; // check if in checkmate
        return valid;//return if the game is over
    }

    /*************************************************************
     *isValidMove
     *
     *Description:
     *
     *
     ************************************************************/
    public boolean isValidMove(Move move) {
        boolean valid = false;// create boolean to check if valid
        if (currentPlayer() != board[move.fromRow][move.fromColumn].player()) // if the current player doesnt own a piece, dont move it
            return valid;
        else if (board[move.fromRow][move.fromColumn] != null) // otherwise check if a valid move is being made and make it.
            if (board[move.fromRow][move.fromColumn].isValidMove(move, board) == true)
                valid = true;

        return valid;
    }

    /*************************************************************
     *move
     *
     *Description
     *
     *
     ************************************************************/
    public void move(Move move) {

        if (board[move.toRow][move.toColumn] != null) { //check to make sure that there is a piece being taken
            if (board[move.toRow][move.toColumn] instanceof Pawn) { // store info for the last move made, used for the undo button
                stack.push(1);
            } else if (board[move.toRow][move.toColumn] instanceof Knight) {
                stack.push(2);
            } else if (board[move.toRow][move.toColumn] instanceof Bishop) {
                stack.push(3);
            } else if (board[move.toRow][move.toColumn] instanceof Rook) {
                stack.push(4);
            } else if (board[move.toRow][move.toColumn] instanceof Queen) {
                stack.push(5);
            }
        } else {
            stack.push(0); // if no piece is taken, show that it is just an empty space being moved to.
        }

        board[move.toRow][move.toColumn] = board[move.fromRow][move.fromColumn];// move the piece

        stack.push(move.fromRow); // store more information on the last move made for the undo button
        stack.push(move.fromColumn);
        stack.push(move.toRow);
        stack.push(move.toColumn);


        board[move.fromRow][move.fromColumn] = null; // remove the peices from its last position.
    }

    /*************************************************************
     *inCheck
     *
     *Description:
     *
     *
     ************************************************************/
    public boolean inCheck(Player p) {
        // add in code here to scan and check if the current player is in check
        boolean valid = false;

        return valid;
    }

    /*************************************************************
     *currentPlayer
     *
     *Description: returns the current player.
     ************************************************************/
    public Player currentPlayer() {
        return player;
    }

    /*************************************************************
     *numRows
     *
     *Description: returns the number of chess squares on a board on one side
     ************************************************************/
    public int numRows() {
        return 8;
    }

    /*************************************************************
     *numColumns
     *
     *Description: returns the number of chess squares on a board on one side
     ************************************************************/
    public int numColumns() {
        return 8;
    }

    /*************************************************************
     *pieceAt
     *
     *Description: returns the peice at a given location
     ************************************************************/
    public IChessPiece pieceAt(int row, int column) {
        return board[row][column];
    }

    /*************************************************************
     *setNextPlayer
     *
     *Description: switches players between white and black.
     ************************************************************/
    public void setNextPlayer() {
        player = player.next();
    }

    /*************************************************************
     *setPiece
     *
     *Description: moves a piece from on location to the one provided whithout using the move functionz
     *
     *
     ************************************************************/
    public void setPiece(int row, int column, IChessPiece piece) {
        board[row][column] = piece;
    }


    /*************************************************************
     *undo
     *
     *Description: Undoes the last move made and returns the pieces to the positions they were at previously
     ************************************************************/
    public void undo() {
        Integer top = stack.peek(); // check if any moves have been made yet
        if (top == null) {
            return; // if no then end here.
        } else { // if yes, keep going
            //System.out.println(stack); // print function used for debugging purposes

            int fromCol = stack.pop();// retrieve last move made from the stack
            int fromRow = stack.pop();
            int toCol = stack.pop();
            int toRow = stack.pop();
            int pieceTaken = stack.pop(); // retrieve if there was a piece at the location

            //System.out.println("from: " + fromRow + " , " + fromCol + " To: " + toRow + " , " + toCol); // print statement used for debugging
            setPiece(toRow, toCol, pieceAt(fromRow, fromCol)); // move the last piece back
            if (pieceTaken == 1)
                board[fromRow][fromCol] = new Pawn(currentPlayer()); // return a piece if it was taken last time
            else if (pieceTaken == 2) board[fromRow][fromCol] = new Knight(currentPlayer());
            else if (pieceTaken == 3) board[fromRow][fromCol] = new Bishop(currentPlayer());
            else if (pieceTaken == 4) board[fromRow][fromCol] = new Rook(currentPlayer());
            else if (pieceTaken == 5) board[fromRow][fromCol] = new Queen(currentPlayer());
            else board[fromRow][fromCol] = null;
        }
        setNextPlayer(); // set the next player in order to keep playing and give back the proper pieces
    }

    public boolean canMakeMoveAI(int x, int y) {
        boolean valid = false;
        if (board[x][y] instanceof Pawn) {
            if (board[x + 1][y] == null && x < 7) {
                movex = 1;
                return true;
            }
        } else if (board[x][y] instanceof Knight) {
            if (board[x + 2][y + 1] == null && y < 7 && x < 6) {
                movex = 1;
                movey = 1;
                return true;
            } else if (board[x + 2][y - 1] == null && y > 0 && x < 6) {
                movex = 2;
                movey = -1;
                return true;
            } else if (board[x - 2][y + 1] == null && y < 7 && x > 1) {
                movex = -2;
                movey = 1;
                return true;
            } else if (board[x - 2][y - 1] == null && y > 0 && x > 1) {
                movex = -2;
                movey = -1;
                return true;
            } else if (board[x + 1][y + 2] == null && y < 6 && x < 7) {
                movex = 1;
                movey = 2;
                return true;
            } else if (board[x - 1][y + 2] == null && y < 6 && x > 0) {
                movex = -1;
                movey = 2;
                return true;
            } else if (board[x - 1][y - 2] == null && y > 1 && x > 0) {
                movex = -1;
                movey = -2;
                return true;
            } else if (board[x + 1][y - 2] == null && y > 1 && x < 7) {
                movex = 1;
                movey = -2;
                return true;
            }
        } else if (board[x][y] instanceof Rook) {
            if (board[x + 1][y] == null && x < 7) {
                movex = 1;
                movey = 0;
                return true;
            } else if (board[x][y + 1] == null && y < 7) {
                movex = 0;
                movey = 1;
                return true;
            } else if (board[x - 1][y] == null && x > 0) {
                movex = -1;
                movey = 0;
                return true;
            } else if (board[x][y - 1] == null && y > 0) {
                movex = 0;
                movey = -1;
                return true;
            }
        } else if (board[x][y] instanceof Bishop) {
            if (board[x + 1][y + 1] == null && y < 7 && x < 7) {
                movex = 1;
                movey = 1;
                return true;
            } else if (board[x - 1][y + 1] == null && y < 7 && x > 0) {
                movex = -1;
                movey = 1;
                return true;
            } else if (board[x - 1][y - 1] == null && y > 0 && x > 0) {
                movex = -1;
                movey = -1;
                return true;
            } else if (board[x + 1][y - 1] == null && y > 1 && x < 7) {
                movex = 1;
                movey = -1;
                return true;
            }
        } else if (board[x][y] instanceof Queen) {
            if (board[x + 1][y + 1] == null && y < 7 && x < 7) {
                movex = 1;
                movey = 1;
                return true;
            } else if (board[x - 1][y + 1] == null && y < 7 && x > 0) {
                movex = -1;
                movey = 1;
                return true;
            } else if (board[x - 1][y - 1] == null && y > 0 && x > 0) {
                movex = -1;
                movey = -1;
                return true;
            } else if (board[x + 1][y - 1] == null && y > 1 && x < 7) {
                movex = 1;
                movey = -1;
                return true;
            }else if (board[x + 1][y] == null && x < 7) {
                movex = 1;
                movey = 0;
                return true;
            } else if (board[x][y + 1] == null && y < 7) {
                movex = 0;
                movey = 1;
                return true;
            } else if (board[x - 1][y] == null && x > 0) {
                movex = -1;
                movey = 0;
                return true;
            } else if (board[x][y - 1] == null && y > 0) {
                movex = 0;
                movey = -1;
                return true;
            }

        } else if (board[x][y] instanceof King) {
            if (board[x + 1][y + 1] == null && y < 7 && x < 7) {
                movex = 1;
                movey = 1;
                return true;
            } else if (board[x - 1][y + 1] == null && y < 7 && x > 0) {
                movex = -1;
                movey = 1;
                return true;
            } else if (board[x - 1][y - 1] == null && y > 0 && x > 0) {
                movex = -1;
                movey = -1;
                return true;
            } else if (board[x + 1][y - 1] == null && y > 1 && x < 7) {
                movex = 1;
                movey = -1;
                return true;
            }
        } else if (board[x][y] instanceof Queen) {
            if (board[x + 1][y + 1] == null && y < 7 && x < 7) {
                movex = 1;
                movey = 1;
                return true;
            } else if (board[x - 1][y + 1] == null && y < 7 && x > 0) {
                movex = -1;
                movey = 1;
                return true;
            } else if (board[x - 1][y - 1] == null && y > 0 && x > 0) {
                movex = -1;
                movey = -1;
                return true;
            } else if (board[x + 1][y - 1] == null && y > 1 && x < 7) {
                movex = 1;
                movey = -1;
                return true;
            }else if (board[x + 1][y] == null && x < 7) {
                movex = 1;
                movey = 0;
                return true;
            } else if (board[x][y + 1] == null && y < 7) {
                movex = 0;
                movey = 1;
                return true;
            } else if (board[x - 1][y] == null && x > 0) {
                movex = -1;
                movey = 0;
                return true;
            } else if (board[x][y - 1] == null && y > 0) {
                movex = 0;
                movey = -1;
                return true;
            }

        }
        return valid;
    }

    /*************************************************************
     *AI
     *
     *Description: An AI function that can control the black player and make moves based on certain parameters.
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


        int posX = 7;
        int posY = 7;
        int x = 7;
        int y = 7;
        Random rn = new Random();
        Move m = new Move();
        boolean moved = false;

        if (currentPlayer() == Player.BLACK) {
            // add in a function of randomly moving valid pieces to spots where they cannot be taken/ can take the king.

            while (board[posX][posY].player() != currentPlayer() && !canMakeMoveAI(posX, posY)) {
                posX = rn.nextInt(7);
                posY = rn.nextInt(7);
                System.out.println("found!");
            }
                setPiece(posX+movex, posY+movey, pieceAt(posX, posY) );
                board[posX][posY] = null;

            }

        }


        // if a piece can be taken, try to move it (potentially add in a priority for more important pieces, or just go for first one scanned)

        //if everything is fine from above, randomly move a piece forward.

    }


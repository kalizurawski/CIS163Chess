package ChessGame;

import java.util.EmptyStackException;
import java.util.Random;
import java.util.Stack;

public class ChessModel implements IChessModel {
    private IChessPiece[][] board;
    private Player player;
    private Move m;

    // for AI
    private int fromSpaceX;
    private int fromSpaceY;
    private int toSpaceX;
    private int toSpaceY;

    Stack<Integer> stack = new Stack<>();

    /*************************************************************
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
     ************************************************************/
    public boolean isComplete() {
        int king1ATX = 0;        // king x position
        int king1ATY = 0;        // king y position
        int king2ATX = 0;        // king x position
        int king2ATY = 0;        // king y position
        int[] spaceList = new int[16];    //list for spaces around king
        int counter = 0;

        // scan board for king 1
        for (int i = 0; i < 8 ; i++){
            for (int j = 0; j < 8 ; j++) {
                if (board[i][j] instanceof King) {
                    king1ATX = j;
                    king1ATY = i;
                }
            }
        }

        // scan board for king 2
        for (int i = 0; i < 8 ; i++){
            for (int j = 0; j < 8 ; j++) {
                if ((board[i][j] instanceof King) && ((i != king1ATY) && (j != king1ATX))) {
                    king2ATX = j;
                    king2ATY = i;
                }
            }
        }

        // check end condition of first king
        spaceList = checkValidKingMoves(king1ATX, king1ATY);
        for(int i = 0; i < spaceList.length; i = i + 2) {
            if (spaceList[i] >= 0) {            // make sure there is an entry there
                // scan every row and every column for opposing player piece
                for (int x = 0; x < 8; x++) {
                    for(int y = 0; y < 8; y++) {
                        if ((board[x][y] != null) && (board[x][y].player() != board[king1ATY][king1ATX].player())) {   // if it is the enemy player
                            m = new Move(x, y, spaceList[i], spaceList[i + 1]);
                            if (isValidMove(m))                          // increment if it can get to the king
                                counter = counter + 1;
                        }
                    }
                }
            }
        }

        if (counter == (spaceList.length/2))
            return true;
        else
            counter = 0;

        // repeat for the other king
        spaceList = checkValidKingMoves(king2ATX, king2ATY);
        for(int i = 0; i < spaceList.length; i = i + 2) {
            if (spaceList[i] >= 0) {    // make sure there is an entry there
                // scan every row and every column for opposing player piece
                for (int x = 0; x < 8; x++) {
                    for(int y = 0; y < 8; y++) {
                        try {
                            if (board[x][y].player() != board[king2ATY][king2ATX].player()) {   // if it is the enemy player
                                m = new Move(x, y, spaceList[i], spaceList[i + 1]);
                                if (isValidMove(m))                          // increment if it can get to the king
                                    counter = counter + 1;
                            }
                        }catch(NullPointerException e){
                            // skip
                        }
                    }
                }
            }
        }

        if (counter == (spaceList.length/2))
            return true;

        return false;
    }

    private int[] checkValidKingMoves(int kingATX, int kingATY) {
        int a = 0;
        int[] spaceList = new int[16];
        // 8 possible spaces around king
        // check left
        if (kingATX - 1 >= 0) {
            m = new Move(kingATY, kingATX, kingATY, kingATX - 1);
            if (isValidMove(m)) {
                spaceList[a] = kingATY;
                spaceList[a + 1] = kingATX - 1;
                a = a + 2;
            }
        }
        // check right
        if (kingATX + 1 < 8) {
            m = new Move(kingATY, kingATX, kingATY, kingATX + 1);
            if (isValidMove(m)) {
                spaceList[a] = kingATY;
                spaceList[a + 1] = kingATX;
                a = a + 2;
            }
        }
        // check up
        if (kingATY + 1 < 8) {
            m = new Move(kingATY, kingATX, kingATY + 1, kingATX);
            if (isValidMove(m)) {
                spaceList[a] = kingATY + 1;
                spaceList[a + 1] = kingATX;
                a = a + 2;
            }
        }
        // check down
        if (kingATY - 1 >= 0) {
            m = new Move(kingATY, kingATX, kingATY - 1, kingATX);
            if (isValidMove(m)) {
                spaceList[a] = kingATY;
                spaceList[a + 1] = kingATX;
                a = a + 2;
            }
        }
        // check top left
        if ((kingATY + 1 < 8) && (kingATX - 1 >= 0)) {
            m = new Move(kingATY, kingATX, kingATY + 1, kingATX - 1);
            if (isValidMove(m)) {
                spaceList[a] = kingATY + 1;
                spaceList[a + 1] = kingATX - 1;
                a = a + 2;
            }
        }
        // check top right
        if ((kingATY + 1 < 8) && (kingATX + 1 < 8)) {
            m = new Move(kingATY, kingATX, kingATY + 1, kingATX + 1);
            if (isValidMove(m)) {
                spaceList[a] = kingATY + 1;
                spaceList[a + 1] = kingATX + 1;
                a = a + 2;
            }
        }
        // check down left
        if ((kingATY - 1 >= 0) && (kingATX - 1 >= 0)) {
            m = new Move(kingATY, kingATX, kingATY - 1, kingATX - 1);
            if (isValidMove(m)) {
                spaceList[a] = kingATY - 1;
                spaceList[a + 1] = kingATX - 1;
                a = a + 2;
            }
        }
        // check down right
        if ((kingATY - 1 >= 0) && (kingATX + 1 < 8)) {
            m = new Move(kingATY, kingATX, kingATY - 1, kingATX + 1);
            if (isValidMove(m)) {
                spaceList[a] = kingATY - 1;
                spaceList[a + 1] = kingATX + 1;
            }
        }

        return spaceList;
    }
    /*************************************************************
     *
     ************************************************************/
    public boolean isValidMove(Move move) {
        boolean valid = false;
            if (board[move.fromRow][move.fromColumn] != null) {
                if (currentPlayer() != board[move.fromRow][move.fromColumn].player())
                    return valid;
                else if (board[move.fromRow][move.fromColumn].isValidMove(move, board) == true)
                    valid = true;
            }

        return valid;
    }
    /*************************************************************
     *
     ************************************************************/
    public void move(Move move) {

        if (board[move.toRow][move.toColumn] != null) { //check to make sure that there is a piece being taken
            if (board[move.toRow][move.toColumn] instanceof Pawn && board[move.toRow][move.toColumn].player() == Player.WHITE) { // store info for the last move made, used for the undo button
                stack.push(1);
            } else if (board[move.toRow][move.toColumn] instanceof Knight && board[move.toRow][move.toColumn].player() == Player.WHITE) {
                stack.push(2);
            } else if (board[move.toRow][move.toColumn] instanceof Bishop && board[move.toRow][move.toColumn].player() == Player.WHITE) {
                stack.push(3);
            } else if (board[move.toRow][move.toColumn] instanceof Rook && board[move.toRow][move.toColumn].player() == Player.WHITE) {
                stack.push(4);
            } else if (board[move.toRow][move.toColumn] instanceof Queen && board[move.toRow][move.toColumn].player() == Player.WHITE) {
                stack.push(5);
            }else if (board[move.toRow][move.toColumn] instanceof Knight && board[move.toRow][move.toColumn].player() == Player.BLACK) {
                stack.push(6);
            } else if (board[move.toRow][move.toColumn] instanceof Bishop && board[move.toRow][move.toColumn].player() == Player.BLACK) {
                stack.push(7);
            } else if (board[move.toRow][move.toColumn] instanceof Rook && board[move.toRow][move.toColumn].player() == Player.BLACK) {
                stack.push(8);
            } else if (board[move.toRow][move.toColumn] instanceof Queen && board[move.toRow][move.toColumn].player() == Player.BLACK) {
                stack.push(9);
            }else if (board[move.toRow][move.toColumn] instanceof Pawn && board[move.toRow][move.toColumn].player() == Player.BLACK) {
                stack.push(10);
            }
        } else {
            stack.push(0); // if no piece is taken, show that it is just an empty space being moved to.
        }

        board[move.toRow][move.toColumn] = board[move.fromRow][move.fromColumn];// move the piece
        stack.push(move.fromRow); // store more information on the last move made for the undo button
        stack.push(move.fromColumn);
        stack.push(move.toRow);
        stack.push(move.toColumn);
        board[move.fromRow][move.fromColumn] = null; // remove the pieces from its last position.
    }
    /*************************************************************
     *
     ************************************************************/
    public boolean inCheck(Player p) {
        boolean valid = true;
        int kingATX = 0;        // king x position
        int kingATY = 0;        // king y position

        // scan board for king
        for (int i = 0; i < 8 ; i++){
            for (int j = 0; j < 8 ; j++) {
                if (board[i][j] instanceof King) {
                    kingATY = i;
                    kingATX = j;
                }
            }
        }

        // scan every row and every column for opposing player piece
        for (int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if ((board[i][j] != null) && (board[i][j].player() != p)) {   // if it is the enemy player
                    m = new Move(i, j, kingATY, kingATX);
                    if (isValidMove(m))                          // in check if it can get to the king
                        return valid;
                }
            }
        }
        return false;
    }

    /*************************************************************
     *
     ************************************************************/
    public Player currentPlayer() {
        return player;
    }
    /*************************************************************
     *
     ************************************************************/
    public int numRows() {
        return 8;
    }
    /*************************************************************
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

    /*************************************************************
     *
     ************************************************************/
    public void undo() {
        try {
            int fromCol = stack.pop();// retrieve last move made from the stack
            int fromRow = stack.pop();
            int toCol = stack.pop();
            int toRow = stack.pop();
            int pieceTaken = stack.pop(); // retrieve if there was a piece at the location

            setPiece(toRow, toCol, pieceAt(fromRow, fromCol)); // move the last piece back
            if (pieceTaken == 1)
                board[fromRow][fromCol] = new Pawn(Player.WHITE); // return a piece if it was taken last time
            else if (pieceTaken == 2) board[fromRow][fromCol] = new Knight(Player.WHITE);
            else if (pieceTaken == 3) board[fromRow][fromCol] = new Bishop(Player.WHITE);
            else if (pieceTaken == 4) board[fromRow][fromCol] = new Rook(Player.WHITE);
            else if (pieceTaken == 5) board[fromRow][fromCol] = new Queen(Player.WHITE);
            else if (pieceTaken == 6)
                board[fromRow][fromCol] = new Knight(Player.BLACK); // return a piece if it was taken last time
            else if (pieceTaken == 7) board[fromRow][fromCol] = new Bishop(Player.BLACK);
            else if (pieceTaken == 8) board[fromRow][fromCol] = new Rook(Player.BLACK);
            else if (pieceTaken == 9) board[fromRow][fromCol] = new Queen(Player.BLACK);
            else if (pieceTaken == 10) board[fromRow][fromCol] = new Pawn(Player.BLACK);
            else board[fromRow][fromCol] = null;

            setNextPlayer(); // set the next player in order to keep playing and give back the proper pieces
        }catch(EmptyStackException e){
            // do nothing
        }
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

        int posX = 7;
        int posY = 7;
        Random rn = new Random();


        if (currentPlayer() == Player.BLACK) {
            // if in check get out of check

            // if you can put the other person in check, do it

            // if you can take one of their pieces, do it
            if (checkToTake()) {
                m = new Move(fromSpaceX, fromSpaceY, toSpaceX, toSpaceY);
                move(m);
            }
            else {
                // otherwise make a random move
                while (!randomMove(posX, posY)) {
                    posX = rn.nextInt(8);
                    posY = rn.nextInt(8);
                    System.out.println("found!");
                }
                m = new Move(fromSpaceX, fromSpaceY, toSpaceX, toSpaceY);
                move(m);
            }

            setNextPlayer();

        }

    }
    private boolean checkToTake() {

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                // scan for a white piece to attack
                if (board[i][j] != null && board[i][j].player() == Player.WHITE) {

                    // scan for black pieces
                    for (int x = 0; x < 7; x++) {
                        for (int y = 0; y < 7; y++) {
                            if (board[x][y] != null && board[x][y].player() == Player.BLACK) {
                                m = new Move(x, y, i, j);
                                if (isValidMove(m)) {
                                    fromSpaceX = x;
                                    fromSpaceY = y;
                                    toSpaceX = i;
                                    toSpaceY = j;
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    public boolean randomMove(int x, int y) {
        boolean valid = false;
        if (board[x][y] instanceof Pawn && board[x][y].player() == Player.BLACK) {
            if (x < 7) {
                m = new Move(x, y, x + 1, y);
                if (isValidMove(m)) {
                    fromSpaceX = x;
                    fromSpaceY = y;
                    toSpaceX = x + 1;
                    toSpaceY = y;
                    return true;
                }
            }
        } else if (board[x][y] instanceof Knight && board[x][y].player() == Player.BLACK) {
            if (y < 7 && x < 6) {
                m = new Move(x, y, x + 2, y + 1);
                if (isValidMove(m)) {
                    fromSpaceX = x;
                    fromSpaceY = y;
                    toSpaceX = x + 2;
                    toSpaceY = y + 1;
                    return true;
                }
            }
            if (y > 0 && x < 6) {
                m = new Move(x, y, x + 2, y - 1);
                if (isValidMove(m)) {
                    fromSpaceX = x;
                    fromSpaceY = y;
                    toSpaceX = x + 2;
                    toSpaceY = y - 1;
                    return true;
                }
            }
            if (y < 7 && x > 1) {
                m = new Move(x, y, x - 2, y + 1);
                if (isValidMove(m)) {
                    fromSpaceX = x;
                    fromSpaceY = y;
                    toSpaceX = x - 2;
                    toSpaceY = y + 1;
                    return true;
                }
            }
            if (y > 0 && x > 1) {
                m = new Move(x, y, x - 2, y - 1);
                if (isValidMove(m)) {
                    fromSpaceX = x;
                    fromSpaceY = y;
                    toSpaceX = x - 2;
                    toSpaceY = y - 1;
                    return true;
                }
            }
            if (y < 6 && x < 7) {
                m = new Move(x, y, x + 1, y + 2);
                if (isValidMove(m)) {
                    fromSpaceX = x;
                    fromSpaceY = y;
                    toSpaceX = x + 1;
                    toSpaceY = y + 2;
                    return true;
                }
            }
            if (y < 6 && x > 0) {
                m = new Move(x, y, x - 1, y + 2);
                if (isValidMove(m)) {
                    fromSpaceX = x;
                    fromSpaceY = y;
                    toSpaceX = x - 1;
                    toSpaceY = y + 2;
                    return true;
                }
            }
            if (y > 1 && x > 0) {
                m = new Move(x, y, x - 1, y - 2);
                if (isValidMove(m)) {
                    fromSpaceX = x;
                    fromSpaceY = y;
                    toSpaceX = x - 1;
                    toSpaceY = y - 2;
                    return true;
                }
            }
            if (y > 1 && x < 7) {
                m = new Move(x, y, x + 1, y - 2);
                if (isValidMove(m)) {
                    fromSpaceX = x;
                    fromSpaceY = y;
                    toSpaceX = x + 1;
                    toSpaceY = y - 2;
                    return true;
                }
            }
        } else if (board[x][y] instanceof Rook && board[x][y].player() == Player.BLACK) {
            if (x < 7) {
                m = new Move(x, y, x + 1, y);
                if (isValidMove(m)) {
                    fromSpaceX = x;
                    fromSpaceY = y;
                    toSpaceX = x + 1;
                    toSpaceY = y;
                    return true;
                }
            }
            if (y < 7) {
                m = new Move(x, y, x, y + 1);
                if (isValidMove(m)) {
                    fromSpaceX = x;
                    fromSpaceY = y;
                    toSpaceX = x;
                    toSpaceY = y + 1;
                    return true;
                }
            }
            if (x > 0) {
                m = new Move(x, y, x - 1, y);
                if (isValidMove(m)) {
                    fromSpaceX = x;
                    fromSpaceY = y;
                    toSpaceX = x - 1;
                    toSpaceY = y;
                    return true;
                }
            }
            if (y > 0) {
                m = new Move(x, y, x, y - 1);
                if (isValidMove(m)) {
                    fromSpaceX = x;
                    fromSpaceY = y;
                    toSpaceX = x;
                    toSpaceY = y - 1;
                    return true;
                }
            }
        } else if (board[x][y] instanceof Bishop && board[x][y].player() == Player.BLACK) {
            if (y < 7 && x < 7) {
                m = new Move(x, y, x + 1, y + 1);
                if (isValidMove(m)) {
                    fromSpaceX = x;
                    fromSpaceY = y;
                    toSpaceX = x + 1;
                    toSpaceY = y + 1;
                    return true;
                }
            }
            if (y < 7 && x > 0) {
                m = new Move(x, y, x - 1, y + 1);
                if (isValidMove(m)) {
                    fromSpaceX = x;
                    fromSpaceY = y;
                    toSpaceX = x - 1;
                    toSpaceY = y + 1;
                    return true;
                }
            }
        }
        if (y > 0 && x > 0) {
            m = new Move(x, y, x - 1, y - 1);
            if (isValidMove(m)) {
                fromSpaceX = x;
                fromSpaceY = y;
                toSpaceX = x - 1;
                toSpaceY = y - 1;
                return true;
            }
        }
        if (y > 1 && x < 7) {
            m = new Move(x, y, x + 1, y - 1);
            if (isValidMove(m)) {
                fromSpaceX = x;
                fromSpaceY = y;
                toSpaceX = x + 1;
                toSpaceY = y - 1;
                return true;
            }
        }
        else if (board[x][y] instanceof Queen && board[x][y].player() == Player.BLACK) {
            if (y < 7 && x < 7) {
                m = new Move(x, y, x + 1, y + 1);
                if (isValidMove(m)) {
                    fromSpaceX = x;
                    fromSpaceY = y;
                    toSpaceX = x + 1;
                    toSpaceY = y + 1;
                    return true;
                }
            }
            if (y < 7 && x > 0) {
                m = new Move(x, y, x - 1, y + 1);
                if (isValidMove(m)) {
                    fromSpaceX = x;
                    fromSpaceY = y;
                    toSpaceX = x - 1;
                    toSpaceY = y + 1;
                    return true;
                }
            }
            if (y > 0 && x > 0) {
                m = new Move(x, y, x - 1, y - 1);
                if (isValidMove(m)) {
                    fromSpaceX = x;
                    fromSpaceY = y;
                    toSpaceX = x - 1;
                    toSpaceY = y - 1;
                    return true;
                }
            }
            if (y > 1 && x < 7) {
                m = new Move(x, y, x + 1, y - 1);
                if (isValidMove(m)) {
                    fromSpaceX = x;
                    fromSpaceY = y;
                    toSpaceX = x + 1;
                    toSpaceY = y - 1;
                    return true;
                }
            }

            if (x < 7) {
                m = new Move(x, y, x + 1, y);
                if (isValidMove(m)) {
                    fromSpaceX = x;
                    fromSpaceY = y;
                    toSpaceX = x + 1;
                    toSpaceY = y;
                    return true;
                }
            }
            if (y < 7) {
                m = new Move(x, y, x, y + 1);
                if (isValidMove(m)) {
                    fromSpaceX = x;
                    fromSpaceY = y;
                    toSpaceX = x;
                    toSpaceY = y + 1;
                    return true;
                }
            }
            if (x > 0) {
                m = new Move(x, y, x - 1, y);
                if (isValidMove(m)) {
                    fromSpaceX = x;
                    fromSpaceY = y;
                    toSpaceX = x - 1;
                    toSpaceY = y;
                    return true;
                }
            }
            if (y > 0) {
                m = new Move(x, y, x, y - 1);
                if (isValidMove(m)) {
                    fromSpaceX = x;
                    fromSpaceY = y;
                    toSpaceX = x;
                    toSpaceY = y - 1;
                    return true;
                }
            }
        } else if (board[x][y] instanceof King && board[x][y].player() == Player.BLACK) {
            if (y < 7 && x < 7) {
                m = new Move(x, y, x + 1, y + 1);
                if (isValidMove(m)) {
                    fromSpaceX = x;
                    fromSpaceY = y;
                    toSpaceX = x + 1;
                    toSpaceY = y + 1;
                    return true;
                }
            }
            if (y < 7 && x > 0) {
                m = new Move(x, y, x - 1, y + 1);
                if (isValidMove(m)) {
                    fromSpaceX = x;
                    fromSpaceY = y;
                    toSpaceX = x - 1;
                    toSpaceY = y + 1;
                    return true;
                }
            }
            if (y > 0 && x > 0) {
                m = new Move(x, y, x - 1, y - 1);
                if (isValidMove(m)) {
                    fromSpaceX = x;
                    fromSpaceY = y;
                    toSpaceX = x - 1;
                    toSpaceY = y - 1;
                    return true;
                }
            }
            if (y > 1 && x < 7) {
                m = new Move(x, y, x + 1, y - 1);
                if (isValidMove(m)) {
                    fromSpaceX = x;
                    fromSpaceY = y;
                    toSpaceX = x + 1;
                    toSpaceY = y - 1;
                    return true;
                }
            }

            if (x < 7) {
                m = new Move(x, y, x + 1, y);
                if (isValidMove(m)) {
                    fromSpaceX = x;
                    fromSpaceY = y;
                    toSpaceX = x + 1;
                    toSpaceY = y;
                    return true;
                }
            }
            if (y < 7) {
                m = new Move(x, y, x, y + 1);
                if (isValidMove(m)) {
                    fromSpaceX = x;
                    fromSpaceY = y;
                    toSpaceX = x;
                    toSpaceY = y + 1;
                    return true;
                }
            }
            if (x > 0) {
                m = new Move(x, y, x - 1, y);
                if (isValidMove(m)) {
                    fromSpaceX = x;
                    fromSpaceY = y;
                    toSpaceX = x + 1;
                    toSpaceY = y;
                    return true;
                }
            }
            if (y > 0) {
                m = new Move(x, y, x, y - 1);
                if (isValidMove(m)) {
                    fromSpaceX = x;
                    fromSpaceY = y;
                    toSpaceX = x;
                    toSpaceY = y - 1;
                    return true;
                }
            }

        }
        return valid;
    }

    public boolean checkEmpty(int r, int c){
        return board[r][c] == null;
    }

    public boolean checkPlayer(int r, int c){
        return player == board[r][c].player();
    }
}

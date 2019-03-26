package ChessGame;

public class ChessModel implements IChessModel {
    private IChessPiece[][] board;
    private Player player;
    private Move m;

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
            if (spaceList[i] >= 0) {    // make sure there is an entry there
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
                        if ((board[x][y] != null) && board[x][y].player() != board[king2ATY][king2ATX].player()) {   // if it is the enemy player
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
            if (currentPlayer() != board[move.fromRow][move.fromColumn].player())
                return valid;
            else if (board[move.fromRow][move.fromColumn] != null)
                if (board[move.fromRow][move.fromColumn].isValidMove(move, board) == true)
                    valid = true;

        return valid;
    }
    /*************************************************************
     *
     ************************************************************/
    public void move(Move move) {
        board[move.toRow][move.toColumn] = board[move.fromRow][move.fromColumn];
        board[move.fromRow][move.fromColumn] = null;
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
        if (inCheck(player)) {
            // make it so valid move is only moves that will protect the king

        }

        // add in a function of randomly moving valid pieces to spots where they cannot be taken/ can take the king.

        // if a piece can be taken, try to move it (potentially add in a priority for more important pieces, or just go for first one scanned)

        //if everything is fine from above, randomly move a piece forward.

    }

    public boolean checkEmpty(int r, int c){
        return board[r][c] == null;
    }

    public boolean checkPlayer(int r, int c){
        return player == board[r][c].player();
    }
}

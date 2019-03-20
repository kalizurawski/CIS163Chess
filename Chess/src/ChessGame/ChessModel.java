package ChessGame;

public class ChessModel implements IChessModel {
    private IChessPiece[][] board;
    private Player player;
    private boolean checkMate = false;

    // declare other instance variables as needed

    public ChessModel() {
        board = new IChessPiece[8][8];
        player = Player.WHITE;

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
        player = Player.BLACK;

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
    }

    public boolean isComplete() {
        boolean valid = false;
        if (checkMate = true) valid = true;
        return valid;
    }

    public boolean isValidMove(Move move) {
        // add in the ability to take other pieces.
        boolean valid = false;

            if (board[move.fromRow][move.fromColumn] != null)
                if (board[move.fromRow][move.fromColumn].isValidMove(move, board) == true)
                    valid = true;

        return valid;
    }

    public void move(Move move) {
        // finished
        if (isValidMove(move)) {
            board[move.toRow][move.toColumn] = board[move.fromRow][move.fromColumn];
            board[move.fromRow][move.fromColumn] = null;
        }
    }

    public boolean inCheck(Player p) {
        // add in code here to scan and check if the current player is in check
        boolean valid = false;

        return valid;
    }


    public Player currentPlayer() {
        return player;
    }

    public int numRows() {
        return 8;
    }

    public int numColumns() {
        return 8;
    }

    public IChessPiece pieceAt(int row, int column) {
        return board[row][column];
    }

    public void setNextPlayer() {
        player = player.next();
    }

    public void setPiece(int row, int column, IChessPiece piece) {
        board[row][column] = piece;
    }

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
}

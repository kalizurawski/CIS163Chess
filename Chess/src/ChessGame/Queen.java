package ChessGame;

public class Queen extends ChessPiece {
    /**************************************
     * instantiates the piece based on player
     * @param player color of player
     */
    public Queen(Player player) {
        super(player);

    }

    /**********************************
     * returns string of piece
     * @return string "Queen"
     */
    public String type() {
        return "Queen";

    }
    /***************************************
     * returns if the given move is valid using Bishop and Rook Classes
     * @param move the move, fromRow,fromCol,toRow,toCol
     * @param board the boards current state
     * @return boolean of if it is valid
     */
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        boolean valid = false;
        Bishop move1 = new Bishop(board[move.fromRow][move.fromColumn].player());
        Rook move2 = new Rook(board[move.fromRow][move.fromColumn].player());

        // check parent class if the move is valid
        if (!super.isValidMove(move, board))
            return valid;

        return (move1.isValidMove(move, board) || move2.isValidMove(move, board));
    }
}


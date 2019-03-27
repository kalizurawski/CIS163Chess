package ChessGame;

public abstract class ChessPiece implements IChessPiece {

    private Player owner;

    /******************************
     * Instantiates the chess piece class with owner
     * @param player the color of the player
     */
    protected ChessPiece(Player player) {
        this.owner = player;
    }

    /**************************
     * returns the type of piece
     * @return  the type of piece
     */
    public abstract String type();

    /********************************
     * returns the owner of the piece
     * @return the color of the owner
     */
    public Player player() {
        return owner;
    }

    /***********************************
     * checks if the move is legal and valid
     * @param move  a {@link W18project3.Move} object describing the move to be made.
     * @param board the {@link W18project3.IChessBoard} in which this piece resides.
     * @return boolean of if the move is valid
     */
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        boolean valid = false;

        //Verify start and finish are different
        if ((move.fromRow == move.toRow) && (move.fromColumn == move.toColumn)) {
            return valid;
        }

        //Verify that you aren't attacking your own piece
        if(board[move.toRow][move.toColumn] != null) {
            if (this.owner == board[move.toRow][move.toColumn].player()) {
                return valid;
            }
        }

        return true;
    }
}


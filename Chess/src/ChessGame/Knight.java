package ChessGame;

public class Knight extends ChessPiece {
    /*************************
     * instantiates the piece by player
     * @param player the player color
     */
    public Knight(Player player) {
        super(player);
    }

    /**************************
     * returns the string of piece type
     * @return string "Knight"
     */
    public String type() {
        return "Knight";
    }
    /***************************************
     * returns if the given move is valid
     * @param move the move, fromRow,fromCol,toRow,toCol
     * @param board the boards current state
     * @return boolean of if it is valid
     */
    public boolean isValidMove(Move move, IChessPiece[][] board){
        boolean valid = false;
        int delRow = move.toRow -  move.fromRow;
        int delCol = move.toColumn - move.fromColumn;

        // check parent class if the move is valid
        if (!super.isValidMove(move, board))
            return valid;

        // only allow to move in an L shape
        if (!(delRow == 1 && delCol == 2 || delRow == 1 && delCol == -2 ||
                delRow == -1 && delCol == 2 || delRow == -1 && delCol == -2 ||
                delRow == 2 && delCol == 1 || delRow == 2 && delCol == -1 ||
                delRow == -2 && delCol == 1 || delRow == -2 && delCol == -1)){
            return valid;
        }

        valid = true;
        return valid;
    }

}


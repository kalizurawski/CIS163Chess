package ChessGame;

public class King extends ChessPiece {
    /*******************************
     * instantiates the piece by player
     * @param player color of the owner
     */
    public King(Player player) {
        super(player);
    }

    /********************************
     * returns the string of the type of piece
     * @return string "King"
     */
    public String type() {
        return "King";
    }
    /***************************************
     * returns if the given move is valid
     * @param move the move, fromRow,fromCol,toRow,toCol
     * @param board the boards current state
     * @return boolean of if it is valid
     */
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        boolean valid = false;

        //check for castling
//        if(board[move.toRow][move.toColumn].type() == "Rook") {
//            if(move.toRow != move.fromRow || (move.fromRow != 0 || move.fromRow != 7)){
//                  return valid;
//            }
//            if (move.toColumn > move.fromColumn) {        //to the right
//                if
//            } else if (move.toColumn < move.fromColumn) { //to the left
//
//            } else {
//                return valid;
//            }
//        }

        if(move.toRow - move.fromRow > 1 || move.toColumn - move.fromColumn > 1 ||
                move.toRow - move.fromRow < -1 || move.toColumn - move.fromColumn < -1)
            return valid;
        return true;
    }
}


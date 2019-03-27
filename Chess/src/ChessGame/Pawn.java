package ChessGame;

public class Pawn extends ChessPiece {
    /*******************************
     * instantiates the pawn class based on player
     * @param player color of owner
     */
    public Pawn(Player player) {
        super(player);
    }

    /************************************
     * returns the string of piece type
     * @return string "Pawn"
     */
    public String type() {
        return "Pawn";
    }

    /***************************************
     * returns if the given move is valid
     * @param move the move, fromRow,fromCol,toRow,toCol
     * @param board the boards current state
     * @return boolean of if it is valid
     */
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        Move prevMove = new Move();
        boolean valid = false;

        // check parent class if the move is valid
        if (!super.isValidMove(move, board))
            return valid;

        if (player() == Player.WHITE) {                                       //WHITE to < from
            //en passant check here

            if (move.toRow > move.fromRow) {
                return valid;
            }
            if (move.fromColumn == move.toColumn) {
                if(board[move.toRow][move.toColumn] != null) {
                    return valid;
                }
                if (move.fromRow - move.toRow > 2) {
                    return valid;
                }
                if (move.fromRow - move.toRow == 2 && move.fromRow != 6) {
                    return valid;
                }
            } else if (move.toColumn - move.fromColumn > 1 || move.toColumn - move.fromColumn < -1) {
                return valid;
            } else {                                                           //moved one space left or right
                if (move.fromRow - move.toRow != 0 && board[move.toRow][move.toColumn] == null) {
                    return valid;
                }
            }
            valid = true;
        } else {                                                              //BLACK to > from
            //en pasant check here
            if (move.toRow < move.fromRow) {
                return valid;
            }
            if (move.fromColumn == move.toColumn) {
                if(board[move.toRow][move.toColumn] != null) {
                    return valid;
                }
                if (move.fromRow - move.toRow < -2) {
                    return valid;
                }
                if (move.fromRow - move.toRow == -2 && move.fromRow != 1) {
                    return valid;
                }
            } else if (move.toColumn - move.fromColumn > 1 || move.toColumn - move.fromColumn < -1) {
                return valid;
            } else {                                                           //moved one space left or right
                if (move.fromRow - move.toRow != -1 || board[move.toRow][move.toColumn] != null) {
                    return valid;
                }
            }
            valid = true;
        }
        return valid;
    }
}

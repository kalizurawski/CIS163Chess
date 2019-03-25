package ChessGame;

public class Pawn extends ChessPiece {

    public Pawn(Player player) {
        super(player);
    }

    public String type() {
        return "Pawn";
    }

    // determines if the move is valid for a pawn piece
    public boolean isValidMove(Move move, IChessPiece[][] board) {
        Move prevMove = new Move();
        boolean valid = false;

        // check parent class if the move is valid
        if (!super.isValidMove(move, board))
            return valid;

        if (player() == Player.WHITE) {                                       //WHITE to < from
//            //en pasant check require Move to have get piece
//            if(prevMove.toRow - prevMove.fromRow == 2 && prevMove.type() == "Pawn" &&
//            && (move.toColumn - move.fromColumn == 1 || move.toColumn - move.fromColumn == -1) &&
//            && (prevMove.toColumn - move.fromColumn == 1 || prevMove.toColumn - move.fromColumn == -1) &&
//            && move.toColumn == prevMove.fromColumn && move.toRow - prevMove.toRow == 1){
//                return true;
//            }
            if (move.toRow > move.fromRow) {
                return valid;
            }
            if (move.fromColumn == move.toColumn) {
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
            //en pasant check
            if (move.toRow < move.fromRow) {
                return valid;
            }
            if (move.fromColumn == move.toColumn) {
                if (move.fromRow - move.toRow < -2) {
                    return valid;
                }
                if (move.fromRow - move.toRow == -2 && move.fromRow != 1) {
                    return valid;
                }
            } else if (move.toColumn - move.fromColumn > 1 || move.toColumn - move.fromColumn < -1) {
                return valid;
            } else {                                                           //moved one space left or right
                if (move.fromRow - move.toRow != -1 || board[move.toRow][move.toColumn] == null) {
                    return valid;
                }
            }
            valid = true;
        }
        return valid;
    }
}


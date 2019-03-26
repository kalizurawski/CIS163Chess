package ChessGame;

public class King extends ChessPiece {

    public King(Player player) {
        super(player);
    }

    public String type() {
        return "King";
    }

    public boolean isValidMove(Move move, IChessPiece[][] board) {
        boolean valid = false;

        //check for castling
//        if(board[move.toRow][move.toColumn].type() == "Rook") {
//            if(move.toRow != move.fromRow && (move.fromRow != 0 || move.fromRow != 7)){
//
//            }
//            if (move.toColumn > move.fromColumn) {
//                if
//            } else if (move.toColumn < move.fromColumn) {
//
//            } else {
//                return valid;
//            }
//        }
        // allowed to move in any direction but only one spot
        if(move.toRow - move.fromRow > 1 || move.toColumn - move.fromColumn > 1 ||
                move.toRow - move.fromRow < -1 || move.toColumn - move.fromColumn < -1)
            return valid;
        return true;
    }
}


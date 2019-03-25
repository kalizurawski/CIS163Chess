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

        // check parent class if the move is valid
        if (!super.isValidMove(move, board))
            return valid;

        // allowed to move in any direction but only one spot
        if(move.toRow - move.fromRow > 1 || move.toColumn - move.fromColumn > 1 ||
                move.toRow - move.fromRow < -1 || move.toColumn - move.fromColumn < -1)
            return valid;
        return true;
    }
}


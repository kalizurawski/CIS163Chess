package Project_3.IChess;

public class King extends ChessPiece {

    public King(Player player) {
        super(player);
    }

    public String type() {
        return "King";
    }

    public boolean isValidMove(Move move, IChessPiece[][] board) {
        boolean valid = false;

        if(move.toRow - move.fromRow > 1 || move.toColumn - move.fromColumn > 1 ||
                move.toRow - move.fromRow < -1 || move.toColumn - move.fromColumn < -1)
            return valid;
        // More code is needed
        return true;
    }
}


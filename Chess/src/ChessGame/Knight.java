package ChessGame;

public class Knight extends ChessPiece {

    public Knight(Player player) {
        super(player);
    }

    public String type() {
        return "Knight";
    }

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


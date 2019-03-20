//package p3;

public class Knight extends ChessPiece {

    public Knight(Player player) {
        super(player);
    }

    public String type() {
        return "Knight";
    }

    public boolean isValidMove(Move move, IChessPiece[][] board){

        boolean superValid = super.isValidMove(move, board);
        boolean valid = false;
        int delRow = move.toRow -  move.fromRow;
        int delCol = move.toColumn - move.fromColumn;

        if (!(delRow == 1 && delCol == 2 || delRow == 1 && delCol == -2 ||
                delRow == -1 && delCol == 2 || delRow == -1 && delCol == -2 ||
                delRow == 2 && delCol == 1 || delRow == 2 && delCol == -1 ||
                delRow == -2 && delCol == 1 || delRow == -2 && delCol == -1)){
            return valid;
        }

        valid = true;
        // More code is needed
        return valid;
    }

}


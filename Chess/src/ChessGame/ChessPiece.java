package ChessGame;

public abstract class ChessPiece implements IChessPiece {

    private Player owner;

    protected ChessPiece(Player player) {
        this.owner = player;
    }

    public abstract String type();

    public Player player() {
        return owner;
    }

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


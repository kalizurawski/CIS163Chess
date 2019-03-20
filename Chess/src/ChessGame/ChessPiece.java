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
        if ((move.fromRow == move.toRow) && (move.fromColumn == move.toColumn))
            return valid;

        //Verify the piece is at the location being clicked
                //Make an equals method?
            //This only checks type
        if(this.type() != board[move.fromRow][move.fromColumn].type())
            return valid;

        //Verify that you aren't attacking your own piece
        if(board[move.toRow][move.toColumn] != null)
        if(this.owner == board[move.toRow][move.toColumn].player())
            return valid;

        //opt out of next if statement when no piece at move to
        //Verify finish is not where team piece is located
        if(board[move.toRow][move.toColumn] == null)
            return valid;
        else if(board[move.toRow][move.toColumn].player() == board[move.fromRow][move.fromColumn].player())
            return valid;

        return true;
    }
}


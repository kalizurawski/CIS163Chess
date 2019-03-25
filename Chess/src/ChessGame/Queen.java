package ChessGame;

public class Queen extends ChessPiece {

    public Queen(Player player) {
        super(player);

    }

    public String type() {
        return "Queen";

    }

    public boolean isValidMove(Move move, IChessPiece[][] board) {
        boolean valid = false;
        Bishop move1 = new Bishop(board[move.fromRow][move.fromColumn].player());
        Rook move2 = new Rook(board[move.fromRow][move.fromColumn].player());

        // check parent class if the move is valid
        if (!super.isValidMove(move, board))
            return valid;

        return (move1.isValidMove(move, board) || move2.isValidMove(move, board));
    }
}


package ChessGame;

public class Bishop extends ChessPiece {

    public Bishop(Player player) {
        super(player);
    }

    public String type() {
        return "Bishop";
    }

    public boolean isValidMove(Move move, IChessPiece[][] board) {

        //boolean superValid = super.isValidMove(move, board);
        boolean valid = false;
        String direction = "";      //Finds direction of movement

        for(int n = 0; (move.fromRow + n < 8) && move.fromColumn + n < 8; n++){
            if((move.fromRow + n == move.toRow) && (move.fromColumn + n == move.toColumn))
                direction = "NE";}
        for(int n = 0; (move.fromRow + n < 8) && move.fromColumn - n >= 0; n++){
            if((move.fromRow + n == move.toRow) && (move.fromColumn - n == move.toColumn))
                direction = "NW";}
        for(int n = 0; (move.fromRow - n >= 0) && move.fromColumn + n < 8; n++){
            if((move.fromRow - n == move.toRow) && (move.fromColumn + n == move.toColumn))
                direction = "SE";}
        for(int n = 0; (move.fromRow - n >= 0) && move.fromColumn - n >= 0; n++){
            if((move.fromRow - n == move.toRow) && (move.fromColumn - n == move.toColumn))
                direction = "SW";}
        if(direction == ""){
            direction = "Not in path";
            return valid;}
        System.out.println(direction);

        switch (direction){
            case "NE":
                for(int n = 1; move.fromRow + n < 8 && move.fromColumn + n < 8 ; n++){
                    if (move.fromRow + n == move.toRow && move.fromColumn + n == move.toColumn)
                        valid = true;
                    else if(board[move.fromRow + n][move.fromColumn + n] != null)
                        return valid;
                }
                break;
            case "NW":
                for(int n = 1; move.fromRow + n < 8 && move.fromColumn - n >= 0; n++){
                    if (move.fromRow + n == move.toRow && move.fromColumn - n == move.toColumn)
                        valid = true;
                    else if(board[move.fromRow + n][move.fromColumn - n] != null)
                        return valid;
                }
                break;
            case "SE":
                for(int n = 1; move.fromRow - n >= 0 && move.fromColumn + n < 8 ; n++){
                    if (move.fromRow - n == move.toRow && move.fromColumn + n == move.toColumn)
                        valid = true;
                    else if(board[move.fromRow - n][move.fromColumn + n] != null)
                        return valid;
                }
                break;
            case "SW":
                for(int n = 1; move.fromRow - n >= 0 && move.fromColumn - n >= 0 ; n++){
                    if (move.fromRow - n == move.toRow && move.fromColumn - n == move.toColumn)
                        valid = true;
                    else if(board[move.fromRow - n][move.fromColumn - n] != null)
                        return valid;
                }
                break;
        }//switch end

        return valid;
        // More code is needed

    }
}


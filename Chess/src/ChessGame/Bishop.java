package ChessGame;

public class Bishop extends ChessPiece {

    /***********************************
     * Instantiates the Bishop based on the player
     * @param player
     */
    public Bishop(Player player) {
        super(player);
    }

    /***********************************
     * Returns the object type "Bishop"
     * @return string "Bishop"
     */
    public String type() {
        return "Bishop";
    }

    /***************************************
     * returns if the given move is valid
     * @param move the move, fromRow,fromCol,toRow,toCol
     * @param board the boards current state
     * @return boolean of if it is valid
     */
    public boolean isValidMove(Move move, IChessPiece[][] board) {

        boolean valid = false;
        // check parent class if the move is valid
        if (!super.isValidMove(move, board))
            return valid;

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

            //checks the movement based on the direction of travel
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
        }

        return valid;

    }
}


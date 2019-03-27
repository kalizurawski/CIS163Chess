package ChessGame;

public class Rook extends ChessPiece {
    /***********************************
     * instantiates a piece based on player
     * @param player player color
     */
    public Rook(Player player) {
        super(player);
    }

    /**********************************
     * returns the type of piece
     * @return string "Rook"
     */
    public String type() {
        return "Rook";
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

        //if in-line
        //Pick Direction
        //if Line-of-Sight
        if(move.fromRow == move.toRow){
            if(move.toColumn > move.fromColumn){ // to the right
                for(int n = 1; move.fromColumn + n != move.toColumn; n++){          //increment from from to to
                    if(board[move.fromRow][move.fromColumn + n] != null){           //if to the right is not empty
                        return valid;
                    }
                }
                valid = true;
            }
            if(move.toColumn < move.fromColumn){ // to the left
                for(int n = 1; move.fromColumn - n != move.toColumn; n++){
                    if(board[move.fromRow][move.fromColumn - n] != null){           //if to the left is not empty
                        return valid;
                    }
                }
                valid = true;
            }
        }if (move.fromColumn == move.toColumn){
            if(move.toRow > move.fromRow){ // DOWN
                for(int n = 1; move.fromRow + n != move.toRow; n++){
                    if(board[move.fromRow + n][move.fromColumn] != null){           //if down is not empty
                        return valid;
                    }
                }
                valid = true;
            }
            if(move.toRow < move.fromRow){ // UP
                System.out.println("ayuh");
                for(int n = 1; move.fromRow - n != move.toRow; n++){
                    if(board[move.fromRow - n][move.fromColumn] != null){       //if up is not empty
                        return valid;
                    }
                }

                valid = true;
            }
        }

        return valid;
    }

}


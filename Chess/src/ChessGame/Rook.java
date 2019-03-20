//package p3;

public class Rook extends ChessPiece {

    public Rook(Player player) {

        super(player);

    }

    public String type() {

        return "Rook";

    }

    // determines if the move is valid for a rook piece
    public boolean isValidMove(Move move, IChessPiece[][] board) {

        //Checks parent class if isValidMove
        boolean superValid = super.isValidMove(move, board);
        boolean valid = false;
        // More code is needed

        //if in-line
        //Pick Direction
        //if Line-of-Sight
        if(move.fromRow == move.toRow){
            if(move.toColumn > move.fromColumn){ // to the right
                for(int n = 1; move.fromColumn + n != move.toColumn; n++){          //increment from from to to
                    if(board[move.fromRow][move.fromColumn + n] != null){ //if to the right is not empty
                        return valid;
                    }
                }
                valid = true;
            }
            if(move.toColumn < move.fromColumn){ // to the left
                for(int n = 1; move.fromColumn - n != move.toColumn; n++){
                    if(board[move.fromRow][move.fromColumn - n] != null){ //if to the left is not empty
                        return valid;
                    }
                }
                valid = true;
            }
        }if (move.fromColumn == move.toColumn){
            if(move.toRow > move.fromRow){ // DOWN
                for(int n = 1; move.fromRow + n != move.toRow; n++){
                    if(board[move.fromRow + n][move.fromColumn] != null){//if down is not empty
                        return valid;
                    }
                }
                valid = true;
            }
            if(move.toRow < move.fromRow){ // UP
                if(move.toRow > move.fromRow){ // DOWN
                    for(int n = 1; move.fromRow - n != move.toRow; n++){
                        if(board[move.fromRow - n][move.fromColumn] != null){//if up is not empty
                            return valid;
                        }
                    }
                }
                valid = true;
            }
        }

        return valid && superValid;
    }

}


package Project_3.IChess;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChessPanel extends JPanel {

    /** button array for board **/
    private JButton[][] board;

    /** game model **/
    private ChessModel model;

    /** extras **/
    private JButton undo;

    /** white pieces **/
    private ImageIcon wRook;
    private ImageIcon wBishop;
    private ImageIcon wQueen;
    private ImageIcon wKing;
    private ImageIcon wPawn;
    private ImageIcon wKnight;

    /** black pieces **/
    private ImageIcon bRook;
    private ImageIcon bBishop;
    private ImageIcon bQueen;
    private ImageIcon bKing;
    private ImageIcon bPawn;
    private ImageIcon bKnight;

    private boolean firstTurnFlag;
    private int fromRow;
    private int toRow;
    private int fromCol;
    private int toCol;
    private listener listener;

    /** turn variables **/
    private JLabel turn;
    private boolean wTurn;


    public ChessPanel() {
        model = new ChessModel();                                   // creates chess model
        board = new JButton[model.numRows()][model.numColumns()];   // creates chess board
        listener = new listener();                                  // creates listener
        createIcons();                                              // creates icons for the board

        JPanel boardpanel = new JPanel();                           // space for board
        JPanel buttonpanel = new JPanel();                          // space for buttons
        boardpanel.setLayout(new GridLayout(model.numRows(), model.numColumns(), 1, 1));
        buttonpanel.setLayout(new GridLayout(2, 1));

        for (int r = 0; r < model.numRows(); r++) {
            for (int c = 0; c < model.numColumns(); c++) {
                if (model.pieceAt(r, c) == null) {                          // if no piece should be there
                    board[r][c] = new JButton("", null);
                    board[r][c].addActionListener(listener);
                } else if (model.pieceAt(r, c).player() == Player.WHITE)    // white piece
                    placeWhitePieces(r, c);
                else if (model.pieceAt(r, c).player() == Player.BLACK)      // black piece
                    placeBlackPieces(r, c);

                setBackGroundColor(r, c);
                boardpanel.add(board[r][c]);
            }
        }
        add(boardpanel, BorderLayout.WEST);                                 // put board to left
        boardpanel.setPreferredSize(new Dimension(600, 600));

        // add extras
        wTurn = true;                                   // white starts out
        turn = new JLabel("");
        buttonpanel.add(turn);
        undo = new JButton("UNDO");
        undo.addActionListener(listener);
        buttonpanel.add(undo);
        add(buttonpanel);                               // put button panel to the right

        firstTurnFlag = true;                           // indicate the next cell pressed is going to be the one to move
    }

    /*************************************************************
     * setBackGroundColor
     *
     * Description : sets the color of a particular square on the
     *               board
     *
     * @param r integer of row
     * @param c integer of column
     ************************************************************/
    private void setBackGroundColor(int r, int c) {
        if ((c % 2 == 1 && r % 2 == 0) || (c % 2 == 0 && r % 2 == 1)) {
            board[r][c].setBackground(Color.LIGHT_GRAY);
        } else if ((c % 2 == 0 && r % 2 == 0) || (c % 2 == 1 && r % 2 == 1)) {
            board[r][c].setBackground(Color.WHITE);
        }
    }

    /*************************************************************
     * placeWhitePieces
     *
     * Description : places white piece in given row and col.
     *
     * @param r integer of row
     * @param c integer of column
     ************************************************************/
    private void placeWhitePieces(int r, int c) {
        if (model.pieceAt(r, c).type().equals("Pawn")) {
            board[r][c] = new JButton(null, wPawn);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Rook")) {
            board[r][c] = new JButton(null, wRook);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Knight")) {
            board[r][c] = new JButton(null, wKnight);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Bishop")) {
            board[r][c] = new JButton(null, wBishop);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Queen")) {
            board[r][c] = new JButton(null, wQueen);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("King")) {
            board[r][c] = new JButton(null, wKing);
            board[r][c].addActionListener(listener);
        }
    }

    /*************************************************************
     * placeBlackPieces
     *
     * Description : places black piece in given row and col.
     *
     * @param r integer of row
     * @param c integer of column
     ************************************************************/
    private void placeBlackPieces(int r, int c) {
        if (model.pieceAt(r, c).type().equals("Pawn")) {
            board[r][c] = new JButton(null, bPawn);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Rook")) {
            board[r][c] = new JButton(null, bRook);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Knight")) {
            board[r][c] = new JButton(null, bKnight);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Bishop")) {
            board[r][c] = new JButton(null, bBishop);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("Queen")) {
            board[r][c] = new JButton(null, bQueen);
            board[r][c].addActionListener(listener);
        }
        if (model.pieceAt(r, c).type().equals("King")) {
            board[r][c] = new JButton(null, bKing);
            board[r][c].addActionListener(listener);
        }
    }

    /*************************************************************
     * createIcons
     *
     * Description : creates icons for the black and white
     *               pieces for the board.
     ************************************************************/
    private void createIcons() {
        // Sets the Image for white player pieces
        wRook = new ImageIcon("wRook.png");
        wBishop = new ImageIcon("wBishop.png");
        wQueen = new ImageIcon("wQueen.png");
        wKing = new ImageIcon("wKing.png");
        wPawn = new ImageIcon("wPawn.png");
        wKnight = new ImageIcon("wKnight.png");

        // Sets the Image for black player pieces
        bRook = new ImageIcon("bRook.png");
        bBishop = new ImageIcon("bBishop.png");
        bQueen = new ImageIcon("bQueen.png");
        bKing = new ImageIcon("bKing.png");
        bPawn = new ImageIcon("bPawn.png");
        bKnight = new ImageIcon("bKnight.png");
    }

    /*************************************************************
     * updateTurn
     *
     * Description : updates who's turn it is
     ************************************************************/
    private void updateTurn() {
        wTurn = !wTurn;     // switch who's turn it is
        if (wTurn)  // white's turn
            turn.setText("White's Turn");
        else
            turn.setText("Black's Turn");
    }

    /*************************************************************
     * displayBoard
     *
     * Description : updates the board
     ************************************************************/
    private void displayBoard() {

        for (int r = 0; r < 8; r++) {
            for (int c = 0; c < 8; c++)
                if (model.pieceAt(r, c) == null)
                    board[r][c].setIcon(null);
                else
                if (model.pieceAt(r, c).player() == Player.WHITE) {
                    if (model.pieceAt(r, c).type().equals("Pawn"))
                        board[r][c].setIcon(wPawn);

                    if (model.pieceAt(r, c).type().equals("Rook"))
                        board[r][c].setIcon(wRook);

                    if (model.pieceAt(r, c).type().equals("Knight"))
                        board[r][c].setIcon(wKnight);

                    if (model.pieceAt(r, c).type().equals("Bishop"))
                        board[r][c].setIcon(wBishop);

                    if (model.pieceAt(r, c).type().equals("Queen"))
                        board[r][c].setIcon(wQueen);

                    if (model.pieceAt(r, c).type().equals("King"))
                        board[r][c].setIcon(wKing);

                } else if (model.pieceAt(r, c).player() == Player.BLACK) {
                    if (model.pieceAt(r, c).type().equals("Pawn"))
                        board[r][c].setIcon(bPawn);

                    if (model.pieceAt(r, c).type().equals("Rook"))
                        board[r][c].setIcon(bRook);

                    if (model.pieceAt(r, c).type().equals("Knight"))
                        board[r][c].setIcon(bKnight);

                    if (model.pieceAt(r, c).type().equals("Bishop"))
                        board[r][c].setIcon(bBishop);

                    if (model.pieceAt(r, c).type().equals("Queen"))
                        board[r][c].setIcon(bQueen);

                    if (model.pieceAt(r, c).type().equals("King"))
                        board[r][c].setIcon(bKing);
            }
        }
        revalidate();
        repaint();
    }

    /*************************************************************
     * Action listener for the buttons
     ************************************************************/
    private class listener implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            // if it was the undo button
            if (undo == event.getSource());
                // TODO : link to undo function
            else{
                // if it was the board
                for (int r = 0; r < model.numRows(); r++)
                    for (int c = 0; c < model.numColumns(); c++)
                        if (board[r][c] == event.getSource())
                            if (firstTurnFlag) {                                    // piece to move
                                fromRow = r;
                                fromCol = c;
                                firstTurnFlag = false;
                            } else {                                                // place to move
                                toRow = r;
                                toCol = c;
                                firstTurnFlag = true;
                                Move m = new Move(fromRow, fromCol, toRow, toCol);
                                if ((model.isValidMove(m))) {                       // only make the move if it is valid
                                    updateTurn();                                   // toggle who's turn it is
                                    model.move(m);
                                    displayBoard();
                                }
                            }
            }

        }
    }
}

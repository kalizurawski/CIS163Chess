package ChessGame;

import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

/**
 *   This is just a small sample of JUnits, you are to write
 *   many many more to have thorough coverage of your code; beyond 100%
 *   statement coverage
 *
 * @author Kali Zurawski
 *
 */

public class ChessModelTest{

    // count successes
    private int success = 0;
    private int total = 0;

    // initialize the ChessModel class
    ChessModel model;

    // initialize move
    Move m;

    // testing currentPlayer
    @Test
    public void testCurrentPlayer() {
        total += 1;

        // initialize model
        model = new ChessModel();

        System.out.println("testCurrentPlayer");
        assertEquals(Player.WHITE, model.currentPlayer());
        success += 1;
    }

    // testing numRows
    @Test
    public void testNumRows() {
        total += 1;

        // initialize model
        model = new ChessModel();

        System.out.println("testNumRows");
        assertEquals(8, model.numRows());
        success += 1;
    }

    // testing numColumns
    @Test
    public void testNumCols() {
        total += 1;

        // initialize model
        model = new ChessModel();

        System.out.println("testNumCols");
        assertEquals(8, model.numColumns());
        success += 1;
    }

    // testing setPiece and pieceAt
    @Test
    public void testSetPiecePieceAt() {
        total += 1;

        // initialize model
        model = new ChessModel();

        System.out.println("testSetPiece");
        model.setPiece(2, 2, new King(Player.WHITE));

        System.out.println("testPieceAt");
        assertEquals(new King(Player.WHITE), model.pieceAt(2, 2));
        success += 1;
    }

    // testing checkEmpty
    @Test
    public void testCheckEmpty() {
        total += 1;

        // initialize model
        model = new ChessModel();

        System.out.println("testCheckEmpty");
        assertTrue(model.checkEmpty(3, 3));
        assertFalse(model.checkEmpty(0, 0));
        success += 1;
    }


    // testing checkPlayer
    @Test
    public void testCheckPlayer() {
        total += 1;

        // initialize model
        model = new ChessModel();

        System.out.println("testCheckPlayer");
        assertTrue(model.checkPlayer(0, 0));
        success += 1;
    }

    // testing isValidMove (and move)
    // try to attack same player piece
    @Test
    public void testAttackSelf() {
        total += 1;

        // initialize model
        model = new ChessModel();

        // initialize move
        m = new Move(0, 1, 1, 3);   // move knight to attack ally pawn

        System.out.println("testAttackSelf");
        assertFalse(model.isValidMove(m));
        success += 1;
    }

    // try to move piece in an illegal way
    @Test
    public void testMoveIllegally() {
        total += 1;

        // initialize model
        model = new ChessModel();

        // initialize move
        m = new Move(1, 0, 3, 4);   // move pawn illegally

        System.out.println("testMoveIllegally");
        assertFalse(model.isValidMove(m));
        success += 1;
    }

    // make a valid move
    @Test
    public void moveLegally() {
        total += 1;

        // initialize model
        model = new ChessModel();

        // initialize move
        m = new Move(1, 2, 2, 2);

        System.out.println("testMoveLegally");
        assertTrue(model.isValidMove(m));
        success += 1;
    }


}
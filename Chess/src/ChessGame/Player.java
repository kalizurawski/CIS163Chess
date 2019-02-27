package ChessGame;

// DO NOT CHANGE THIS CLASS
// ITS IN THE RUBRIC

public enum Player {
    BLACK, WHITE;

    /**
     * Return the {@code Player} whose turn is next.
     *
     * @return the {@code Player} whose turn is next
     */
    public Player next() {
        if (this == BLACK)
            return WHITE;
        else
            return BLACK;

        //	return this == BLACK ? WHITE : BLACK;
    }
}
package e1;

public interface ChessPiece extends Movement {

    /**
     * Get the position of chess piece.
     * 
     * @return position of this chess piece
     */
    Pair<Integer, Integer> getPosition();

    /**
     * Set the new position of chess piece.
     * 
     * @param row    new row of piece
     * @param column new column of piece
     * @throws IllegalArgumentException if row or column are negative
     */
    void setPosition(int row, int column) throws IllegalArgumentException;
}

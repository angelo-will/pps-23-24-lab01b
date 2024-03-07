package e1;

public interface Movement {

    /**
     * Perform movement if possible, throws exception otherwise.
     * 
     * @param row    the new row
     * @param column the new colum
     * @throws IllegalArgumentException if movement logic is not respected
     */
    void move(int row, int column) throws IllegalArgumentException;

    /**
     * Check if row and column passed fulfil movement logic.
     * 
     * @param row    the new row
     * @param column the new column
     * @return true if row and column respect the movement logic
     */
    boolean isMovementLogicValid(int row, int column);
}

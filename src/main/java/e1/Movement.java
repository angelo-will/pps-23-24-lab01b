package e1;

public interface Movement {

    void move(int row, int column) throws IllegalArgumentException;

    boolean isMovementLogicValid(int row, int column);
}

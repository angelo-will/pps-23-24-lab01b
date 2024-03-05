package e1;

public interface ChessPiece extends Movement {
    Pair<Integer, Integer> getPosition();

    void setPosition(int row, int column);
}

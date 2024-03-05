package e1;

public abstract class ChessPieceAbstract implements ChessPiece {
    private Pair<Integer, Integer> position;

    public ChessPieceAbstract(int row, int column) {
        this.setPosition(row, column);
    }

    public Pair<Integer, Integer> getPosition() {
        return position;
    }

    public void setPosition(int row, int column) {
        if (this.isOverZeroPosition(row, column)) {
            this.position = new Pair<Integer, Integer>(row, column);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void move(int row, int column) {
        if (this.isMovementLogicValid(row, column) && this.isOverZeroPosition(row, column)
                && !this.isSamePosition(row, column)) {
            this.setPosition(row, column);
        } else {
            throw new IllegalArgumentException();
        }
    }

    private boolean isOverZeroPosition(int row, int column) {
        return row >= 0 && column >= 0;
    }

    private boolean isSamePosition(int row, int column) {
        return this.position.equals(new Pair<Integer, Integer>(row, column));
    }
}

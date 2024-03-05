package e1;

public abstract class ChessPiece {
    private Pair<Integer, Integer> position;

    public ChessPiece(int row, int column){
        this.setPosition(row, column);
    }

    public Pair<Integer, Integer> getPosition() {
        return position;
    }

    public void setPosition(int row, int column) {
        if (this.isSubZeroPosition(row, column)) {
            throw new IllegalArgumentException();
        } else {
            this.position = new Pair<Integer, Integer>(row, column);
        }
    }

    public void makeMovement(int row, int column) {
        if (this.isValidMovement(row, column) && !this.isSubZeroPosition(row, column) && !this.isSamePosition(row,column)) {
            this.move(row, column);
        } else {
            throw new IllegalArgumentException();
        }
    }

    protected abstract void move(int row, int column);

    protected abstract boolean isValidMovement(int row, int column);

    private boolean isSubZeroPosition(int row, int column) {
        return row < 0 || column < 0;
    }

    private boolean isSamePosition(int row, int column){
        return this.position.equals(new Pair<Integer, Integer>(row, column));
    }
}

package e1;

public class Knight extends ChessPiece {

    public Knight(int row, int column) {
        super(row, column);
    }

    @Override
    protected void move(int row, int column) {
        this.setPosition(row, column);
    }

    @Override
    protected boolean isValidMovement(int row, int column) {
        return Math.abs((this.getPosition().getX() - row) * (this.getPosition().getY() - column)) == 2;
    }

}

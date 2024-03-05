package e1;

public class Knight extends ChessPieceAbstract {

    public Knight(int row, int column) {
        super(row, column);
    }

    @Override
    public boolean isMovementLogicValid(int row, int column) {
        return Math.abs((this.getPosition().getX() - row) * (this.getPosition().getY() - column)) == 2;
    }

}

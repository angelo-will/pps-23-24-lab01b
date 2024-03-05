package e1;

public class Knight extends ChessPieceAbstract {

    public Knight(int row, int column) {
        super(row, column);
    }

    public Knight(Pair<Integer, Integer> position) {
        super(position);
    }

    @Override
    public boolean isMovementLogicValid(int row, int column) {
        System.out.println("OLD KNIGHT POSITION - " + this.getPosition());
        return Math.abs((this.getPosition().getX() - row) * (this.getPosition().getY() - column)) == 2;
    }

}

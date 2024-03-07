package e1;

public class Pawn extends ChessPieceAbstract {

    public Pawn(int row, int column) {
        super(row, column);
    }

    public Pawn(Pair<Integer, Integer> position) {
        super(position);
    }

    @Override
    public boolean isMovementLogicValid(int row, int column) {
        return true;
    }

}

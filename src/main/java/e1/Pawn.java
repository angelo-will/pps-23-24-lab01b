package e1;

public class Pawn extends ChessPieceAbstract {

    public Pawn(int row, int column) {
        super(row, column);
    }

    @Override
    public boolean isMovementLogicValid(int row, int column) {
        return true;
    }

}

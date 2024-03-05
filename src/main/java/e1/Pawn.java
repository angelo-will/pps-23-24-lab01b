package e1;

public class Pawn extends ChessPiece{

    public Pawn(int row, int column) {
        super(row, column);
    }

    @Override
    protected void move(int row, int column) {
        // In the game now not move
    }

    @Override
    protected boolean isValidMovement(int row, int column) {
        return true;
    }

}

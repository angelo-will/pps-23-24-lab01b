package e1;

public class LogicsImpl implements Logics {
	
	private final int chessboardSize;

	private ChessPiece knight;
	private final ChessPiece pawn;
	 
    public LogicsImpl(int size){
    	this.chessboardSize = size;
		// this.knight = new Knight(0,0);
		// this.pawn = new Pawn(1,1);
		this.pawn = new Pawn(Pairs.getIntegerRandomPair(size));
		var knightPosition = this.getRandomEmptyPosition(this.pawn.getPosition());
		this.knight = new Knight(knightPosition.getX(),knightPosition.getY());
    }

	public LogicsImpl(int size, Pair<Integer, Integer> pawnPosition, Pair<Integer, Integer> knightPosition) {
		this.chessboardSize = size;
		this.knight = new Knight(knightPosition);
		this.pawn = new Pawn(pawnPosition);
	}

	private final Pair<Integer,Integer> getRandomEmptyPosition(Pair<Integer, Integer> positionExcluded){
		var randomPosition = Pairs.getIntegerRandomPair(this.chessboardSize);
		return randomPosition == positionExcluded ? this.getRandomEmptyPosition(positionExcluded) : randomPosition;
    }
    
	@Override
	public boolean hit(int row, int col) {
		if (this.isPositionOutsideField(row, col)){
			throw new IllegalArgumentException();
		} else {
			System.out.println("Inside hit - row: " + row + " col: " + col);
			this.knight.move(row, col);
			return this.knight.getPosition().equals(this.pawn.getPosition());
		}
	}

	@Override
	public boolean hasKnight(int row, int col) {
		return this.knight.getPosition().equals(new Pair<>(row, col));
	}
	
	@Override
	public boolean hasPawn(int row, int col) {
		return this.pawn.getPosition().equals(new Pair<>(row, col));
	}

	private boolean isPositionOutsideField(int row, int column){
		return row < 0 || column < 0 || row >= this.chessboardSize || column >= this.chessboardSize;
	}
}

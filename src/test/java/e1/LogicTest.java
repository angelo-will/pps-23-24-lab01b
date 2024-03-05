package e1;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class LogicTest {

  private Logics logic;
  private int fieldSize = 5;
  private Pair<Integer, Integer> knightPosition;
  private Pair<Integer, Integer> pawnPositions;
  List<Pair<Integer, Integer>> captureSequence;

  @BeforeEach
  public void initializeLogic() {
    var startKnightPosition = new Pair<Integer, Integer>(1, 3);
    var startPawnPosition = new Pair<Integer, Integer>(0, 0);

    this.captureSequence = new ArrayList<>();
    this.captureSequence.add(new Pair<Integer, Integer>(2, 1));
    this.captureSequence.add(startPawnPosition);

    this.logic = new LogicsImpl(fieldSize, startPawnPosition, startKnightPosition);

    this.knightPosition = this.getKnightPosition();
    this.pawnPositions = this.getPawnPosition();
  }

  @Test
  public void testKingPosition() {
    assertTrue(this.logic.hasKnight(this.knightPosition.getX(), this.knightPosition.getY()));
  }

  @Test
  public void testWrongKingPosition() {
    Pair<Integer, Integer> wrongPosition = new Pair<Integer, Integer>((this.knightPosition.getX() + 1) % fieldSize,
        (this.knightPosition.getY() + 1) % fieldSize);
    assertFalse(this.logic.hasKnight(wrongPosition.getX(), wrongPosition.getY()));
  }

  @Test
  public void testPawnPosition() {
    assertTrue(this.logic.hasPawn(this.pawnPositions.getX(), this.pawnPositions.getY()));
  }

  @Test
  public void testWrongPawnPosition() {
    Pair<Integer, Integer> wrongPosition = new Pair<Integer, Integer>((this.pawnPositions.getX() + 1) % fieldSize,
        (this.pawnPositions.getY() + 1) % fieldSize);
    assertFalse(this.logic.hasPawn(wrongPosition.getX(), wrongPosition.getY()));
  }

  @Test
  public void testKnightMovement() {
    int newRowKnight = this.knightPosition.getX() + 2;
    int newColumnKnight = this.knightPosition.getY() + 1;
    Pair<Integer, Integer> newKnightPosition = new Pair<Integer, Integer>(newRowKnight, newColumnKnight);
    if (this.isPositionOutsideField(newKnightPosition.getX(), newKnightPosition.getY())) {
      assertThrows(IllegalArgumentException.class, () -> this.logic.hit(newRowKnight, newColumnKnight));
    } else {
      this.logic.hit(newRowKnight, newColumnKnight);
      this.knightPosition = this.getKnightPosition();
      assertEquals(newKnightPosition, this.knightPosition);
    }
  }

  @Test
  public void testMovements() {
    int movementsNumber = 20;
    IntStream.range(0, movementsNumber).forEach(step -> this.testKnightMovement());
  }

  @Test
  public void testSequencePawnCapture() {
    var sequenceToLastPositionBeforeCapture = this.captureSequence.subList(0, this.captureSequence.size() - 1);
    sequenceToLastPositionBeforeCapture
        .forEach(movement -> assertFalse(this.logic.hit(movement.getX(), movement.getY())));

    var lastMovement = this.captureSequence.get(this.captureSequence.size() - 1);
    assertTrue(this.logic.hit(lastMovement.getX(), lastMovement.getY()));
  }

  private Pair<Integer, Integer> getKnightPosition() {
    return this.getFulfilPosition(knightPosition -> this.logic.hasKnight(knightPosition.getX(), knightPosition.getY()));
  }

  private Pair<Integer, Integer> getPawnPosition() {
    return this.getFulfilPosition(pawnPosition -> this.logic.hasPawn(pawnPosition.getX(), pawnPosition.getY()));
  }

  private Pair<Integer, Integer> getFulfilPosition(Predicate<Pair<Integer, Integer>> predicate) {
    Pair<Integer, Integer> positionFound = new Pair<Integer, Integer>(null, null);
    for (int row = 0; row < this.fieldSize; row++) {
      for (int column = 0; column < this.fieldSize; column++) {
        if (predicate.test(this.createIntegerPair(row, column))) {
          positionFound = new Pair<Integer, Integer>(row, column);
        }
      }
    }
    if (positionFound.getX() == null || positionFound.getY() == null) {
      throw new NoSuchElementException();
    }
    return positionFound;
  }

  private Pair<Integer, Integer> createIntegerPair(int x, int y) {
    return new Pair<Integer, Integer>(x, y);
  }

  private boolean isPositionOutsideField(int row, int column) {
		return row < 0 || column < 0 || row >= this.fieldSize || column >= this.fieldSize;
	}
}

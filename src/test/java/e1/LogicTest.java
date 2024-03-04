package e1;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class LogicTest {

  private Logics logic;
  private int fieldSize = 5;
  private Pair<Integer, Integer> knightPosition;
  private Pair<Integer, Integer> pawnPositions;

  @BeforeEach
  public void initializeLogic() {
    this.logic = new LogicsImpl(fieldSize);
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
    if (this.isPositionInsideField(newKnightPosition)) {
      this.logic.hit(newRowKnight, newColumnKnight);
      this.knightPosition = this.getKnightPosition();
      assertEquals(newKnightPosition, this.knightPosition);
    } else {
      assertThrows(IndexOutOfBoundsException.class, () -> this.logic.hit(newRowKnight, newColumnKnight));
    }
  }

  @Test
  public void testMovements() {
    int movementsNumber = 20;
    IntStream.range(0, movementsNumber).forEach(step -> this.testKnightMovement());
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

  private boolean isPositionInsideField(Pair<Integer, Integer> position) {
    return position.getX() >= 0 && position.getX() < fieldSize
        && position.getY() >= 0 && position.getY() < fieldSize;
  }
}

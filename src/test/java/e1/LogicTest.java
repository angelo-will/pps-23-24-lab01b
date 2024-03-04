package e1;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;
import java.util.stream.IntStream;

public class LogicTest {

  private Logics logic;
  private int fieldSize = 5;
  private Pair<Integer, Integer> knightPosition;

  @BeforeEach
  public void initializeLogic() {
    this.logic = new LogicsImpl(fieldSize);
    this.knightPosition = this.getKingPosition();
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
  public void testMovement() {
    int newRowKnight = this.knightPosition.getX() + 2;
    int newColumnKnight = this.knightPosition.getY() + 1;
    Pair<Integer, Integer> newKnightPosition = new Pair<Integer, Integer>(newRowKnight, newColumnKnight);
    if (this.isPositionInsideField(newKnightPosition)) {
      this.logic.hit(newRowKnight, newColumnKnight);
      this.knightPosition = this.getKingPosition();
      assertEquals(newKnightPosition, this.knightPosition);
    } else {
      assertThrows(IndexOutOfBoundsException.class, () -> this.logic.hit(newRowKnight, newColumnKnight));
    }
  }

  @Test
  public void testMovements() {
    int movementsNumber = 20;
    IntStream.range(0, movementsNumber).forEach(step -> this.testMovement());
  }

  private Pair<Integer, Integer> getKingPosition() {
    Pair<Integer, Integer> position = new Pair<Integer, Integer>(null, null);
    for (int row = 0; row < this.fieldSize; row++) {
      for (int column = 0; column < this.fieldSize; column++) {
        if (this.logic.hasKnight(row, column)) {
          position = new Pair<Integer, Integer>(row, column);
        }
      }
    }
    if (position.getX() == null) {
      throw new NoSuchElementException();
    }
    return position;
  }

  private boolean isPositionInsideField(Pair<Integer, Integer> position) {
    return position.getX() >= 0 && position.getX() < fieldSize
        && position.getY() >= 0 && position.getY() < fieldSize;
  }
}

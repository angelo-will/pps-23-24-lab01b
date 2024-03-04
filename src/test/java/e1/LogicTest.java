package e1;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

public class LogicTest {

  private Logics logic;
  private int fieldSize = 5;
  private Pair<Integer, Integer> kingPosition;

  @BeforeEach
  public void initializeLogic() {
    this.logic = new LogicsImpl(fieldSize);
    this.kingPosition = this.getKingPosition();
  }

  @Test
  public void testKingPosition() {
    assertTrue(this.logic.hasKnight(this.kingPosition.getX(), this.kingPosition.getY()));
  }

  @Test
  public void testWrongKingPosition() {
    Pair<Integer, Integer> wrongPosition = new Pair<Integer, Integer>((this.kingPosition.getX() + 1) % fieldSize,
        (this.kingPosition.getY() + 1) % fieldSize);
    assertFalse(this.logic.hasKnight(wrongPosition.getX(), wrongPosition.getY()));
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
}

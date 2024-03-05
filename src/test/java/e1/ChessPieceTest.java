package e1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ChessPieceTest {

    private ChessPiece knight;
    private ChessPiece pawn;
    private Pair<Integer, Integer> startinKnightPosition;
    private Pair<Integer, Integer> startinPawnPosition;

    @BeforeEach
    void initializeKnightPiece() {
        this.startinKnightPosition = new Pair<Integer, Integer>(0, 0);
        this.knight = new Knight(this.startinKnightPosition.getX(), this.startinKnightPosition.getY());
    }

    @BeforeEach
    void initializePawnPiece() {
        this.startinPawnPosition = new Pair<Integer, Integer>(1, 1);
        this.pawn = new Pawn(this.startinPawnPosition.getX(), this.startinPawnPosition.getY());
    }

    @Test
    public void testStartPawnPosition() {
        assertEquals(this.startinPawnPosition, this.pawn.getPosition());
    }

    @Test
    public void testStartingKnightPosition() {
        assertEquals(this.startinKnightPosition, this.knight.getPosition());
    }

    @Test
    public void testKnightSetPosition() {
        Pair<Integer, Integer> newKnightPosition = new Pair<Integer, Integer>(2, 1);
        this.knight.setPosition(newKnightPosition.getX(), newKnightPosition.getY());
        assertEquals(newKnightPosition, this.knight.getPosition());
    }

    @Test
    public void testExceptionSubZeroMovements() {
        List<Pair<Integer, Integer>> listSubZeroPositions = new ArrayList<>();
        listSubZeroPositions.add(new Pair<Integer, Integer>(-1, -1));
        listSubZeroPositions.add(new Pair<Integer, Integer>(-1, 0));
        listSubZeroPositions.add(new Pair<Integer, Integer>(0, -1));
        listSubZeroPositions.forEach(position -> assertThrows(IllegalArgumentException.class,
                () -> this.knight.move(position.getX(), position.getY())));
    }

    @Test
    public void testInvalidMovements() {
        Pair<Integer, Integer> movementNotValid1 = new Pair<Integer, Integer>(this.startinKnightPosition.getX(),
                this.startinKnightPosition.getY());
        Pair<Integer, Integer> movementNotValid2 = new Pair<Integer, Integer>(1, 1);
        Pair<Integer, Integer> movementNotValid3 = new Pair<Integer, Integer>(2, 2);

        assertThrows(IllegalArgumentException.class,
                () -> this.knight.move(movementNotValid1.getX(), movementNotValid1.getY()));

        assertThrows(IllegalArgumentException.class,
                () -> this.knight.move(movementNotValid2.getX(), movementNotValid2.getY()));

        assertThrows(IllegalArgumentException.class,
                () -> this.knight.move(movementNotValid3.getX(), movementNotValid3.getY()));
    }

    @Test
    public void testMovementFromStartinPoint() {
        Pair<Integer, Integer> newPosition = new Pair<Integer, Integer>(2, 1);
        this.knight.move(newPosition.getX(), newPosition.getY());
        assertEquals(newPosition, this.knight.getPosition());
    }

    @Test
    public void testMovements() {
        List<Pair<Integer, Integer>> movements = new ArrayList<>();
        movements.add(new Pair<Integer, Integer>(2, 1));
        movements.add(new Pair<Integer, Integer>(1, 3));
        movements.add(new Pair<Integer, Integer>(3, 4));
        movements.forEach(move -> this.knight.move(move.getX(), move.getY()));
        var finalKnightPosition = movements.get(movements.size() - 1);
        assertEquals(finalKnightPosition, this.knight.getPosition());
    }
}

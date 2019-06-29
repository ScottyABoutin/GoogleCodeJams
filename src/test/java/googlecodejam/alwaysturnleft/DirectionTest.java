package googlecodejam.alwaysturnleft;

import static googlecodejam.alwaysturnleft.Direction.EAST;
import static googlecodejam.alwaysturnleft.Direction.NORTH;
import static googlecodejam.alwaysturnleft.Direction.SOUTH;
import static googlecodejam.alwaysturnleft.Direction.WEST;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import googlecodejam.alwaysturnleft.Direction;

class DirectionTest {
  
  private final Direction n = NORTH;
  private final Direction e = EAST;
  private final Direction s = SOUTH;
  private final Direction w = WEST;
  
  @Test
  void testTurnRight90DegreesFromNorthReturnsEast() {
    assertEquals(EAST, n.turnRight90Degrees());
  }
  
  @Test
  void testTurnRight90DegreesFromEastReturnsSouth() {
    assertEquals(SOUTH, e.turnRight90Degrees());
  }
  
  @Test
  void testTurnRight90DegreesFromSouthReturnsWest() {
    assertEquals(WEST, s.turnRight90Degrees());
  }
  
  @Test
  void testTurnRight90DegreesFromWestReturnsNorth() {
    assertEquals(NORTH, w.turnRight90Degrees());
  }
  
  @Test
  void testTurnLeft90DegreesFromNorthReturnsWest() {
    assertEquals(WEST, n.turnLeft90Degrees());
  }
  
  @Test
  void testTurnLeft90DegreesFromEastReturnsNorth() {
    assertEquals(NORTH, e.turnLeft90Degrees());
  }
  
  @Test
  void testTurnLeft90DegreesFromSouthReturnsEast() {
    assertEquals(EAST, s.turnLeft90Degrees());
  }
  
  @Test
  void testTurnLeft90DegreesFromWestReturnsSouth() {
    assertEquals(SOUTH, w.turnLeft90Degrees());
  }
  
  @Test
  void testTurn180DegreesFromNorthReturnsSouth() {
    assertEquals(SOUTH, n.turn180Degrees());
  }
  
  @Test
  void testTurn180DegreesFromEastReturnsWest() {
    assertEquals(WEST, e.turn180Degrees());
  }
  
  @Test
  void testTurn180DegreesFromSouthReturnsNorth() {
    assertEquals(NORTH, s.turn180Degrees());
  }
  
  @Test
  void testTurn180DegreesFromWestReturnsEast() {
    assertEquals(EAST, w.turn180Degrees());
  }
  
}

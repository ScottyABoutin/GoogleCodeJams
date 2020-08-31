package googlecodejam.alwaysturnleft;

import static googlecodejam.alwaysturnleft.Direction.EAST;
import static googlecodejam.alwaysturnleft.Direction.NORTH;
import static googlecodejam.alwaysturnleft.Direction.SOUTH;
import static googlecodejam.alwaysturnleft.Direction.WEST;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CoordinateInterpreterTest {
  
  private static CoordinateInterpreter interpreter;
  private CoordinateWalls coordinate;
  
  @BeforeAll
  static void setUpBeforeClass() throws Exception {
    interpreter = new CoordinateInterpreter();
  }
  
  @BeforeEach
  void setUp() throws Exception {
    coordinate = new CoordinateWalls(Coordinate.of(0, 0));
  }
  
  @Test
  void test_NorthEastSouthWestWalls_Returns_0() {
    coordinate.setWall(NORTH, true).setWall(EAST, true).setWall(SOUTH, true).setWall(WEST, true);
    assertEquals('0', interpreter.interpret(coordinate));
  }
  
  @Test
  void test_EastSouthWestWall_Returns_1() {
    coordinate.setWall(EAST, true).setWall(SOUTH, true).setWall(WEST, true);
    assertEquals('1', interpreter.interpret(coordinate));
  }
  
  @Test
  void test_NorthEastWestWall_Returns_2() {
    coordinate.setWall(NORTH, true).setWall(EAST, true).setWall(WEST, true);
    assertEquals('2', interpreter.interpret(coordinate));
  }
  
  @Test
  void test_EastWestWall_Returns_3() {
    coordinate.setWall(EAST, true).setWall(WEST, true);
    assertEquals('3', interpreter.interpret(coordinate));
  }
  
  @Test
  void test_NorthEastSouthWall_Returns_4() {
    coordinate.setWall(NORTH, true).setWall(EAST, true).setWall(SOUTH, true);
    assertEquals('4', interpreter.interpret(coordinate));
  }
  
  @Test
  void test_EastSouthWall_Returns_5() {
    coordinate.setWall(EAST, true).setWall(SOUTH, true);
    assertEquals('5', interpreter.interpret(coordinate));
  }
  
  @Test
  void test_NorthEastWall_Returns_6() {
    coordinate.setWall(NORTH, true).setWall(EAST, true);
    assertEquals('6', interpreter.interpret(coordinate));
  }
  
  @Test
  void test_EastWall_Returns_7() {
    coordinate.setWall(EAST, true);
    assertEquals('7', interpreter.interpret(coordinate));
  }
  
  @Test
  void test_NorthSouthWestWall_Returns_8() {
    coordinate.setWall(NORTH, true).setWall(SOUTH, true).setWall(WEST, true);
    assertEquals('8', interpreter.interpret(coordinate));
  }
  
  @Test
  void test_SouthWestWall_Returns_9() {
    coordinate.setWall(SOUTH, true).setWall(WEST, true);
    assertEquals('9', interpreter.interpret(coordinate));
  }
  
  @Test
  void test_NorthWestWall_Returns_a() {
    coordinate.setWall(NORTH, true).setWall(WEST, true);
    assertEquals('a', interpreter.interpret(coordinate));
  }
  
  @Test
  void test_WestWall_Returns_b() {
    coordinate.setWall(WEST, true);
    assertEquals('b', interpreter.interpret(coordinate));
  }
  
  @Test
  void test_NorthSouthWall_Returns_c() {
    coordinate.setWall(NORTH, true).setWall(SOUTH, true);
    assertEquals('c', interpreter.interpret(coordinate));
  }
  
  @Test
  void test_SouthWall_Returns_d() {
    coordinate.setWall(SOUTH, true);
    assertEquals('d', interpreter.interpret(coordinate));
  }
  
  @Test
  void test_NorthWall_Returns_e() {
    coordinate.setWall(NORTH, true);
    assertEquals('e', interpreter.interpret(coordinate));
  }
  
  @Test
  void test_NoWalls_Returns_f() {
    assertEquals('f', interpreter.interpret(coordinate));
  }
}
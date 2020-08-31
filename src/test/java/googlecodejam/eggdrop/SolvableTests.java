package googlecodejam.eggdrop;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.IntStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SolvableTests {
  
  private EggDropCodeJamSolver solver;
  
  private static final int MAX_VALUE_FOR_BASE_TESTS = 100_000;
  
  @BeforeEach
  void setUp() {
    solver = new EggDropCodeJamSolver();
  }
  
  @Test
  void test_FirstSample() {
    assertAll(
      () -> assertEquals(7, solver.solveForF(3, 3)),
      () -> assertEquals(2, solver.solveForD(3, 3, 3)),
      () -> assertEquals(1, solver.solveForB(3, 3, 3))
    );
  }
  
  @Test
  void test_SecondSample() {
    assertAll(
      () -> assertEquals(25, solver.solveForF(5, 3)),
      () -> assertEquals(3, solver.solveForD(7, 5, 3)),
      () -> assertEquals(2, solver.solveForB(7, 5, 3))
    );
  }
  
  @Test
  void test_0ValuesForD() {
    assertAll(
      IntStream.range(0, MAX_VALUE_FOR_BASE_TESTS).mapToObj(i ->
        () -> assertEquals(0, solver.solveForF(0, i)))
    );
  }
  
  @Test
  void test_0ValuesForB() {
    assertAll(
      IntStream.range(0, MAX_VALUE_FOR_BASE_TESTS).mapToObj(i ->
        () -> assertEquals(0, solver.solveForF(i, 0)))
    );
  }
  
  @Test
  void test_1ValuesForD() {
    assertAll(
      IntStream.range(1, MAX_VALUE_FOR_BASE_TESTS).mapToObj(i ->
        () -> assertEquals(1, solver.solveForF(1, i)))
    );
  }
  
  @Test
  void test_1ValuesForB() {
    assertAll(
      IntStream.range(1, MAX_VALUE_FOR_BASE_TESTS).mapToObj(i ->
        () -> assertEquals(i, solver.solveForF(i, 1)))
    );
  }
  
  @Test
  void test_SquareValues() {
    long value = 1;
    for (int i = 2; i < 33; i++) {
      value = (value + 1) * 2 - 1;
      assertEquals(value, solver.solveForF(i, i));
    }
  }
  
}
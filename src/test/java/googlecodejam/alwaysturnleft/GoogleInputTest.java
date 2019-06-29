package googlecodejam.alwaysturnleft;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class GoogleInputTest {
  
  private static AlwaysTurnLeftCodeJamSolver solver;
  private static final String fileName = "PerfectMazeTest.in";
  
  @Test
  void test_UsingWithoutWallsStrategy() throws IOException {
    solver = new AlwaysTurnLeftCodeJamSolver(new MazeWithoutWallsConstructorStrategy());
    solver.go(fileName);
  }
  
  @Test
  void test_UsingWithWallsStrategy() throws IOException {
    solver = new AlwaysTurnLeftCodeJamSolver(new MazeWithWallsConstructorStrategy());
    solver.go(fileName);
  }
}
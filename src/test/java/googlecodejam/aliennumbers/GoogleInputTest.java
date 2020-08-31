package googlecodejam.aliennumbers;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class GoogleInputTest {
  
  @Test
  void test() throws IOException {
    new AlienNumberCodeJamSolver().go("A-large-practice.in");
  }
}
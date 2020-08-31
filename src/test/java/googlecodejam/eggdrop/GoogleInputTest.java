package googlecodejam.eggdrop;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class GoogleInputTest {
  
  @Test
  void test() throws IOException {
    new EggDropCodeJamSolver().go("C-large-practice.in");
  }
}
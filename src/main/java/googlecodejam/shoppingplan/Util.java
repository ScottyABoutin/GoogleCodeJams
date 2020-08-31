package googlecodejam.shoppingplan;

public class Util {
  
  public static double distance(int x1, int y1, int x2, int y2) {
    int deltaX = x1 - x2;
    int deltaY = y1 - y2;
    return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
  }
  
  private Util() {
    throw new AssertionError("Never created");
  }
  
}

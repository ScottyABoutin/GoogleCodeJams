package googlecodejam.shoppingplan;

public class GridTraveler {
  
  private int x;
  private int y;
  
  public GridTraveler() {
    x = 0;
    y = 0;
  }
  
  public GridTraveler(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  public double calculateDistance(int newX, int newY) {
    return Util.distance(x, y, newX, newY);
  }
  
  public double move(int newX, int newY) {
    double distanceTravelled = calculateDistance(newX, newY);
    x = newX;
    y = newY;
    return distanceTravelled;
  }
  
  public int getX() {
    return x;
  }
  
  public int getY() {
    return y;
  }
}
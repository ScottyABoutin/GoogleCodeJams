package googlecodejam.shoppingplan;

import java.util.Comparator;

public class StoreDistanceFromOriginComparator implements Comparator<Store> {
  
  @Override
  public int compare(Store store1, Store store2) {
    double store1Distance = calculateDistance(0, 0, store1.getX(), store1.getY());
    double store2Distance = calculateDistance(0, 0, store2.getX(), store2.getY());
    return Double.compare(store1Distance, store2Distance);
  }
  
  private double calculateDistance(int x1, int y1, int x2, int y2) {
    int deltaX = x1 - x2;
    int deltaY = y1 - y2;
    return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
  }
  
}
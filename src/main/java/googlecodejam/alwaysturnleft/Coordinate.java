package googlecodejam.alwaysturnleft;

import java.util.Comparator;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * A {@code Coordinate} represents an X,Y coordinate on a 2-dimensional grid.
 * {@code Coordinate}s with positive, negative, and 0 x and y values are
 * allowed.
 * <p>
 * {@code Coordinate} objects are instance-controlled. There is no public
 * constructor, and new instances must be obtained through the
 * {@link Coordinate#of(int, int)} method. Two {@code Coordinate} references
 * that were constructed using the same x and y are guaranteed to be
 * referentially equal, and can be compared using '==' or
 * {@link #equals(Object)}.
 * <p>
 * {@code Coordinate}s have a related {@link Direction} to each other on the x
 * and y axes. The X axis is implemented such that {@link Direction#WEST} is in
 * the negative direction and {@link Direction#EAST} is in the positive
 * direction. The Y axis is implemented such that {@link Direction#SOUTH} is in
 * the positive direction and {@link Direction#NORTH} is in the negative
 * direction. This means that {@code Coordinate(0,0)} has the following
 * {@code Coordinate} object in each direction:
 * <ul>
 * <li>{@link Direction#NORTH}: (0, -1)
 * <li>{@link Direction#SOUTH}: (0, 1)
 * <li>{@link Direction#WEST}: (0, -1)
 * <li>{@link Direction#EAST}: (0, 1)
 * </ul>
 * <p>
 * {@code Coordinate}s have a natural sort order that is based on their x axis
 * first, followed by their y axis. They are sorted from least x to greatest x,
 * and then from least y to greatest y. If it is desired to sort by the y axis
 * first, and then the x axis, a {@code Comparator} is provided to facilitate
 * this.
 * 
 * @author Scotty Boutin
 * @version 1.0.0
 */
public class Coordinate implements Comparable<Coordinate> {
  
  /**
   * The x part of the coordinate.
   */
  private final int x;
  /**
   * The y part of the coordinate.
   */
  private final int y;
  
  /**
   * The hash code of this coordinate. It is cached on creation.
   */
  private final int cachedHashCode;
  /**
   * The {@code String} representation of this object. It is cached on creation.
   */
  private final String cachedString;
  
  /**
   * Returns a {@code Coordinate} object that is the same as any other
   * {@code Coordinate} object with the same parameters.
   * 
   * @param x
   *          The x part of the {@code Coordinate}.
   * @param y
   *          The y part of the {@code Coordinate}.
   * @return A {@code Coordinate} object that is the same as any other
   *         {@code Coordinate} object with the same parameters.
   */
  public static Coordinate of(int x, int y) {
    return CACHE.get(x, y);
  }
  
  /**
   * The cache object used to create {@code Coordinate}s only when necessary. All
   * instances of {@code Coordinate} should be retrieved from the cache.
   */
  private static final CoordinateCache CACHE = new CoordinateCache();
  
  /**
   * A {@code CoordinateCache} contains a cache of coordinate objects. When a
   * client asks for a {@code Coordinate}, the cache is used to return an already
   * existing instance if it exists, and create a single new instance per x,y
   * mapping.
   * 
   * @author Scotty Boutin
   * @version 1.0.0
   */
  private static class CoordinateCache {
    
    /**
     * The cache that stores the mapping of x to map of y to that
     * {@code Coordinate}.
     */
    private ConcurrentMap<Integer, ConcurrentMap<Integer, Coordinate>> cache = new ConcurrentSkipListMap<>();
    
    /**
     * Creates a {@code CoordinateCache}. There should only ever be one of these.
     * 
     * @see Coordinate#CACHE
     */
    private CoordinateCache() {
    }
    
    /**
     * Fetches the single instance of the {@code Coordinate} object associated with
     * the x,y mapping, or builds and caches that {@code Coordinate}.
     * 
     * @param x
     *          The x part of the desired {@code Coordinate}.
     * @param y
     *          The y part of the desired {@code Coordinate}.
     * @return The single shared instance of the {@code Coordinate} with that x,y
     *         mapping.
     */
    public Coordinate get(Integer x, Integer y) {
      cache.computeIfAbsent(x, unusedKey -> new ConcurrentSkipListMap<>());
      ConcurrentMap<Integer, Coordinate> yMap = cache.get(x);
      if (yMap.containsKey(y)) {
        return yMap.get(y);
      } else {
        /*
         * This may or may not be cached and returned from threads, but always return
         * yMap.get(y) to ensure that it is the same instance. This may result in extra
         * creation, but extra Coordinates created by thread contention will be
         * discarded by putIfAbsent.
         */
        yMap.putIfAbsent(y, new Coordinate(x, y));
        return yMap.get(y);
      }
    }
  }
  
  /**
   * Constructs a {@code Coordinate} from the given x and y values. Currently
   * caches the hash code and {@code String} for faster accessibility, but this is
   * subject to change in a future release.
   * 
   * @param x
   *          The x part of the {@code Coordinate}.
   * @param y
   *          The y part of the {@code Coordinate}.
   */
  private Coordinate(int x, int y) {
    this.x = x;
    this.y = y;
    cachedHashCode = computeHashCode();
    cachedString = computeString();
  }
  
  /**
   * Computes the hash code of this {@code Coordinate}. This is used to cache the
   * hash code, but this is subject to change in a future release.
   * 
   * @return A hash code for this {@code Coordinate}.
   */
  private int computeHashCode() {
    final int prime = 31;
    int result = 17;
    result = prime * result + x;
    result = prime * result + y;
    return result;
    
  }
  
  /**
   * Returns a {@code String} representation. This is used to cache the
   * {@link #toString()} method's return value, but this is subject to change in a
   * future release.
   * 
   * @return A {@code String} representation for this {@code Coordinate}.
   */
  private String computeString() {
    StringBuilder builder = new StringBuilder("(");
    builder.append(x).append(",").append(y).append(")");
    return builder.toString();
  }
  
  /**
   * Accessor for the x property of this {@code Coordinate}.
   * 
   * @return The x property of this {@code Coordinate}.
   */
  public int getX() {
    return x;
  }
  
  /**
   * Accessor for the y property of this {@code Coordinate}.
   * 
   * @return The y property of this {@code Coordinate}.
   */
  public int getY() {
    return y;
  }
  
  /**
   * Returns the neighbor of the {@code Coordinate} in the direction of that
   * coordinate. Note that the y-axis's 0 starts from the north and counts
   * positively as you move south, and the x-axis starts from the west and counts
   * positively as you move east.
   * 
   * @param direction
   *          The {@code Direction} of the neighbor coordinate being retrieved.
   * @return The {@code Coordinate} in the provided {@code Direction}.
   */
  public Coordinate getNeighbor(Direction direction) {
    switch (direction) {
      case NORTH:
        return Coordinate.of(x, y - 1);
      case EAST:
        return Coordinate.of(x + 1, y);
      case SOUTH:
        return Coordinate.of(x, y + 1);
      case WEST:
        return Coordinate.of(x - 1, y);
      default:
        throw new UnsupportedOperationException("Direction not yet implemented");
    }
  }
  
  /**
   * Returns a hash code for this {@code Coordinate}.
   * 
   * @return A hash code for this {@code Coordinate}.
   */
  @Override
  public int hashCode() {
    return cachedHashCode;
  }
  
  /**
   * Compares this {@code Coordinate} to the specified object for equality. The
   * result is {@code true} if and only if the other object is exactly the same,
   * as {@code Coordinate}s are instance-controlled.
   * 
   * @param obj
   *          The other object this is being compared to, {@code null} returns
   *          false.
   * @return {@code true} if this and the object are equal, {@code false}
   *         otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    return this == obj;
  }
  
  /**
   * Compares this {@code Coordinate} to another {@code Coordinate}.
   * {@code Coordinate}s have a natural sort order that is based on their x axis
   * first, followed by their y axis. They are sorted from least x to greatest x,
   * and then from least y to greatest y. Note that the natural order is
   * equivalent to using the {@link #X_THEN_Y_ORDER} comparator.
   * 
   * @param other
   *          The other {@code Coordinate} that this object is being compared to.
   * @return A negative integer, zero, or a positive integer as this
   *         {@code Coordinate}'s x value is less than, equal to, or greater than
   *         the second {@code Coordinate}'s x value.
   */
  @Override
  public int compareTo(Coordinate other) {
    return X_THEN_Y_ORDER.compare(this, other);
  }
  
  /**
   * Returns a {@code String} representation of this {@code Coordinate}.
   * 
   * @return A {@code String} representation of this {@code Coordinate}.
   */
  @Override
  public String toString() {
    return cachedString;
  }
  
  /**
   * A {@code Comparator} that orders {@code Coordinate}s by the x axis, and then
   * the y axis. They are sorted from least x to greatest x, and then from least y
   * to greatest y. This order is guaranteed to be the same as the natural order
   * of {@code Coordianate}s provided by the {@code Comparable} interface.
   */
  public static final Comparator<Coordinate> X_THEN_Y_ORDER = Comparator.comparing(Coordinate::getX)
      .thenComparing(Coordinate::getY);
  /**
   * A {@code Comparator} that orders {@code Coordinate}s by the y axis, and then
   * the x axis. They are sorted from least y to greatest y, and then from least x
   * to greatest x.
   */
  public static final Comparator<Coordinate> Y_THEN_X_ORDER = Comparator.comparing(Coordinate::getY)
      .thenComparing(Coordinate::getX);
}
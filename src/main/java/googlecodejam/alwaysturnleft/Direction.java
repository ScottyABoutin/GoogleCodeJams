package googlecodejam.alwaysturnleft;

import java.util.HashMap;
import java.util.Map;

/**
 * This enum represents a particular direction: North, East, South, or West. It also contains
 * convenince methods for getting directions based off of other directions.
 * 
 * @author Scotty Boutin
 * @version 1.0.0
 */
public enum Direction {
  /**
   * The direction north.
   */
  NORTH(0),
  /**
   * The direction east.
   */
  EAST(90),
  /**
   * The direction south.
   */
  SOUTH(180),
  /**
   * The direction west.
   */
  WEST(270);
  
  /**
   * The minimum number of degrees a direction can have.
   */
  private static final int MIN_DEGREES = 0;
  /**
   * The maximum number of degrees a direction can have.
   */
  private static final int MAX_DEGREES = 359;
  /**
   * The number of degrees in a circle. Provided for easy calcuations to keep all degree values
   * between 0 and 359 inclusive.
   */
  private static final int DEGREES_IN_CIRCLE = 360;
  
  /**
   * The cache that all {@code Direction}s are registered to for use.
   */
  private static Map<Integer, Direction> cache = populateCache();
  
  /**
   * The degrees that the {@code Direction} lies on the circular plane.
   */
  private int degrees;
  
  /**
   * Constructs a {@code Direction} given its degree value on the circular plane.
   * 
   * @param degrees The value of this on the circular plane.
   */
  private Direction(int degrees) {
    this.degrees = degrees;
  }
  
  /**
   * Populates the cache with all of the Directions. This is guaranteed to happen after all
   * directions are initialized.
   * 
   * @return A Map containing degree values to {@code Direction} mappings.
   */
  private static Map<Integer, Direction> populateCache() {
    Map<Integer, Direction> cache = new HashMap<>();
    for (Direction direction : Direction.values()) {
      cache.put(direction.degrees, direction);
    }
    return cache;
  }
  
  /**
   * Returns the {@code Direction} 90&deg; to the right of this {@code Direction}.
   * 
   * @return The {@code Direction} 90&deg; to the right of this {@code Direction}.
   */
  public Direction turnRight90Degrees() {
    return getDirection(90);
  }
  
  /**
   * Returns the {@code Direction} 90&deg; to the left of this {@code Direction}.
   * 
   * @return The {@code Direction} 90&deg; to the left of this {@code Direction}.
   */
  public Direction turnLeft90Degrees() {
    return getDirection(-90);
  }
  
  /**
   * Returns the {@code Direction} 180&deg; from this {@code Direction}.
   * 
   * @return The {@code Direction} 180&deg; from of this {@code Direction}.
   */
  public Direction turn180Degrees() {
    return getDirection(180);
  }
  
  /**
   * Gets a {@code Direction} relative to the current by the number of degrees.
   * 
   * @param degreesDelta The change in degrees from the given {@code Direction}.
   * @return The {@code Direction} relative to the current by the number of degrees.
   */
  private Direction getDirection(int degreesDelta) {
    int newDegrees = degrees + degreesDelta;
    if (newDegrees < MIN_DEGREES) {
      newDegrees += DEGREES_IN_CIRCLE;
    } else if (newDegrees > MAX_DEGREES) {
      newDegrees -= DEGREES_IN_CIRCLE;
    }
    Direction newDirection = cache.get(newDegrees);
    if (newDirection == null) {
      throw new UnsupportedOperationException("Direction not yet implemented");
    } else {
      return newDirection;
    }
  }
}
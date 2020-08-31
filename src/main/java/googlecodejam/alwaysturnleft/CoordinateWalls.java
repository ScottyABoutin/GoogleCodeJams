package googlecodejam.alwaysturnleft;

/**
 * A {@code CoordinateWalls} object represents the walls around a particular {@link Coordinate},
 * which can be in any {@link Direction}. A {@code CoordinateWalls} is considered to be equivalent
 * to any other {@code CoordinateWalls} backed by the same {@code Coordinate}. The comparison
 * methods {@link #equals(Object)}, {@link #hashCode()}, and {@link #compareTo(CoordinateWalls)} all
 * rely only on the {@code Coordinate} and not the state of the walls themselves. This makes this
 * object mutable within a {@code Collection} while being a good key or set element, even in sorted
 * collections.
 * 
 * @author Scotty Boutin
 * @version 1.0.0
 * @see Coordinate
 * @see Direction
 */
public class CoordinateWalls implements Comparable<CoordinateWalls> {
  
  /**
   * The coordinate that the walls are around.
   */
  private final Coordinate coordinate;
  /**
   * The four walls around the coordinate. As the {@code Direction} enum changes, this will need to
   * change as well.
   */
  private boolean northWall, eastWall, southWall, westWall;
  
  /**
   * Constructs a {@code CoordinateWalls} around a {@code Coordinate}. By default, all walls are
   * down.
   * 
   * @param coordinate The {@code Coordinate} that walls are being tracked around.
   */
  public CoordinateWalls(Coordinate coordinate) {
    this(coordinate, false);
    
  }
  
  /**
   * Constructs a {@code CoordinateWalls} around a {@code Coordinate}, with all possible walls built
   * up if the argument is {@code true}.
   * 
   * @param coordinate The {@code Coordinate} that walls are being tracked around.
   * @param wallsUp Whether or not the walls are initialized as down or up.
   */
  public CoordinateWalls(Coordinate coordinate, boolean wallsUp) {
    this.coordinate = coordinate;
    if (wallsUp) {
      for (Direction direction : Direction.values()) {
        setWall(direction, wallsUp);
      }
    }
    // No need for else. Walls set to default false.
  }
  
  /**
   * Constructs a {@code CoordinateWalls} from another {@code CoordinateWalls} object. This copy
   * constructor is backed by the same coordinate, and copies the state of the walls. This is used
   * instead of the {@code Cloneable} interface.
   * 
   * @param otherCoordinateWalls The {@code CoordinateWalls} that this is being constructed from.
   */
  public CoordinateWalls(CoordinateWalls otherCoordinateWalls) {
    Coordinate otherCoordinate = otherCoordinateWalls.asCoordinate();
    // Prefer to use the static factory method instead of copying the
    // reference, future-proofing.
    this.coordinate = Coordinate.of(otherCoordinate.getX(), otherCoordinate.getY());
    this.northWall = otherCoordinateWalls.northWall;
    this.eastWall = otherCoordinateWalls.eastWall;
    this.southWall = otherCoordinateWalls.southWall;
    this.westWall = otherCoordinateWalls.westWall;
  }
  
  /**
   * Returns whether or not this {@code CoordinateWalls} has walls in that particular direction.
   * 
   * @param direction The direction where a wall might be.
   * @return {@code true} if a wall is standing in that {@code Direction} from the
   *         {@code Coordinate}.
   */
  public boolean hasWall(Direction direction) {
    switch (direction) {
      case NORTH:
        return northWall;
      case EAST:
        return eastWall;
      case SOUTH:
        return southWall;
      case WEST:
        return westWall;
      default:
        throw new UnsupportedOperationException("Direction not implemented as a Coordinate wall");
    }
  }
  
  /**
   * Sets a wall in a particular {@code Direction} to either be there or not. Returns itself for
   * ease of chaining wall setting.
   * 
   * @param direction The direction where a wall is being set or removed.
   * @param hasWall {@code true} if a wall is being set, {@code false} if it is being removed.
   * @return This object.
   */
  public CoordinateWalls setWall(Direction direction, boolean hasWall) {
    switch (direction) {
      case NORTH:
        northWall = hasWall;
        break;
      case EAST:
        eastWall = hasWall;
        break;
      case SOUTH:
        southWall = hasWall;
        break;
      case WEST:
        westWall = hasWall;
        break;
      default:
        throw new UnsupportedOperationException("Direction not implemented as a Coordinate wall");
    }
    return this;
  }
  
  /**
   * Returns a view of the {@code Coordinate} backing this object.
   * 
   * @return The underlying {@code Coordinate} that the walls are around.
   */
  public Coordinate asCoordinate() {
    return this.coordinate;
  }
  
  /**
   * Returns a hash code for this {@code CoordinateWalls}. Note that any {@code CoordinateWalls}
   * that shares the same {@code Coordinate} will have the same hash code.
   * 
   * @return A hash code for this {@code CoordinateWalls}.
   */
  @Override
  public int hashCode() {
    return coordinate.hashCode();
  }
  
  /**
   * Compares this {@code CoordinateWalls} to the specified object for equality. The result is
   * {@code true} if and only if the other object is backed by the same {@code Coordinate}. Note
   * that two {@code CoordinateWall}s with different wall states can be equal to each other as a
   * result, and a supplementary equals method is provided to check for wall equality as well.
   * 
   * @param obj The other object this is being compared to, {@code null} returns false.
   * @return {@code true} if this and the object have the same backing {@code Coordinate},
   *         {@code false} otherwise.
   * @see #equalsWithWalls(CoordinateWalls)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof CoordinateWalls)) {
      return false;
    }
    CoordinateWalls other = (CoordinateWalls) obj;
    
    return coordinate.equals(other.coordinate);
  }
  
  /**
   * Compares this {@code CoordinateWalls} to the specified argument for full equality. The result
   * is {@code true} if and only if the argument is backed by the same {@code Coordinate} and all of
   * the walls are the same.
   * 
   * @param other The other {@code CoordinateWalls} this being compared, {@code null} returns false.
   * @return {@code true} if this and the other {@code CoordinateWalls} have the same backing
   *         {@code Coordinate} and the same wall state, {@code false} otherwise.
   */
  public boolean equalsWithWalls(CoordinateWalls other) {
    if (other == null || !coordinate.equals(other.coordinate))
      return false;
    
    return northWall == other.northWall && eastWall == other.eastWall
           && southWall == other.southWall && westWall == other.westWall;
  }
  
  /**
   * Compares this {@code CoordinateWalls} to another {@code CoordinateWalls}.
   * {@code CoordinateWalls} objects have a natural sort order that is based on the backing
   * {@code Coordinate}'s natural sort order.
   * 
   * @param other The other {@code CoordinateWalls} that this object is being compared to.
   * @return A negative integer, zero, or a positive integer as this {@code CoordinateWalls}'
   *         backing {@code Coordinate}'s x value is less than, equal to, or greater than the second
   *         {@code CoordinateWalls}' backing {@code Coordinate}'s x value.
   * @see Coordinate#compareTo(Coordinate)
   */
  @Override
  public int compareTo(CoordinateWalls other) {
    return coordinate.compareTo(other.coordinate);
  }
  
  /**
   * Returns a {@code String} representation of this {@code CoordinateWalls}. Currently, this starts
   * with the {@code String} representation of the backing {@code Coordinate}, followed by brackets.
   * Within the brackets, the letters n, e, s, and w follow. If they are capitalized, that wall is
   * up. Otherwise, if they are lowercase, that wall is down. This representation is subject to
   * change in the future, especially if more directions are added.
   * 
   * @return A {@code String} representation of this {@code CoordinateWalls}.
   */
  @Override
  public String toString() {
    StringBuilder string = new StringBuilder(coordinate.toString());
    string.append('[').append(northWall ? 'N'
                                        : 'n');
    string.append(eastWall ? 'E'
                           : 'e')
          .append(southWall ? 'S'
                            : 's');
    string.append(westWall ? 'W'
                           : 'w')
          .append(']');
    
    return string.toString();
  }
}
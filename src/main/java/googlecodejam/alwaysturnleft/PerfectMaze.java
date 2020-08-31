package googlecodejam.alwaysturnleft;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * A {@code PerfectMaze} is an immutable maze that follows a specific set of rules defined by the
 * Code Jam. Those rules are reprinted here for convenience.
 * <ol>
 * <li>It is a rectangular grid of rooms, R rows by C columns.
 * <li>There are exactly two openings on the outside of the maze: the entrance and the exit. The
 * entrance is always on the north wall, while the exit could be on any wall.
 * <li>There is exactly one path between any two rooms in the maze (that is, exactly one path that
 * does not involve backtracking).
 * </ol>
 * <p>
 * A {@code PerfectMaze} is constructed using a {@code PerfectMaze.Builder} implementation. There is
 * no way to build a {@code PerfectMaze} other than walking that maze using the {@code Builder}.
 * That maze is immutable: new instances (even identical ones) can be created with new
 * {@link Builder#build()} calls.
 * <p>
 * A {@code PerfectMaze} can be traversed in a for-each loop, or can be manually traversed using the
 * {@link #iterator()} method. A {@code PerfectMaze} is traversable row-by-row, moving through each
 * row from left to right. It traverses from the top row of the maze to the bottom row.
 * 
 * @author Scotty Boutin
 * @version 1.0.0
 * @see PerfectMaze.Builder
 * @see CoordinateWalls
 */
public class PerfectMaze implements Iterable<CoordinateWalls> {
  
  /**
   * A 2-dimensional array consisting of the rows and columns for this maze. To get an element at a
   * particular row-column mapping, use mazeWalls[rowNumber][columnNumber].
   */
  private final CoordinateWalls[][] mazeWalls;
  
  /**
   * Constructs a {@code PerfectMaze} from the given 2-dimensional array of {@code CoordinateWalls}.
   * 
   * @param mazeWalls The 2-dimensional array used to represent this maze. Note that it is not
   *          copied over, and the {@code Builder} should give a new 2-dimensional array for each
   *          {@code PerfectMaze} constructed.
   * @see Builder#build()
   */
  private PerfectMaze(CoordinateWalls[][] mazeWalls) {
    this.mazeWalls = mazeWalls;
  }
  
  /**
   * Accessor for the number of rows.
   * 
   * @return The number of rows.
   */
  public int getNumRows() {
    return mazeWalls.length;
  }
  
  /**
   * Accessor for the number of columns.
   * 
   * @return The number of columns.
   */
  public int getNumColumns() {
    // Since a perfect maze is rectangular, use the first row's length
    return mazeWalls[0].length;
  }
  
  /**
   * Returns an iterator over this maze's {@code CoordinateWalls}. It traverses row-by-row, moving
   * through each row from left to right. It traverses from the top row of the maze to the bottom
   * row.
   * <p>
   * This {@code Iterator} does not support the optional remove method, and may not be used to alter
   * the state of the {@code PerfectMaze}.
   * 
   * @return An iterator over this maze.
   */
  @Override
  public Iterator<CoordinateWalls> iterator() {
    return new MazeWallIterator();
  }
  
  /**
   * A {@code MazeWallIterator} iterates over a perfect maze is the way specified by the
   * {@link PerfectMaze#iterator()} method. It traverses row-by-row, from left to right, then from
   * top to bottom.
   * <p>
   * This {@code Iterator} does not support the optional remove method, and may not be used to alter
   * the state of the {@code PerfectMaze}. A {@code Builder} must create a new {@code PerfectMaze}
   * if a new state is desired.
   * 
   * @author Scotty Boutin
   * @version 1.0.0
   */
  private class MazeWallIterator implements Iterator<CoordinateWalls> {
    
    /**
     * The current row that this iterator is on.
     */
    private int currentRow = 0;
    /**
     * The current column that this iterator is on.
     */
    private int currentColumn = 0;
    
    /**
     * Creates a new {@code MazeWallIterator} initialized to the top-left of the
     * {@code PerfectMaze}.
     */
    private MazeWallIterator() {}
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasNext() {
      return !(endOfRow() && endOfColumn());
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public CoordinateWalls next() {
      if (!hasNext())
        throw new NoSuchElementException();
      if (endOfRow()) {
        currentColumn = 0;
        currentRow++;
      }
      return mazeWalls[currentRow][currentColumn++];
    }
    
    /**
     * Returns {@code true} if the iterator is at the end of a row.
     * 
     * @return {@code true} if the iterator is at the end of a row, {@code false} if not.
     */
    private boolean endOfRow() {
      return currentColumn == mazeWalls[currentRow].length;
    }
    
    /**
     * Returns {@code true} if the iterator is at the end of a column.
     * 
     * @return {@code true} if the iterator is at the end of a column, {@code false} if not.
     */
    private boolean endOfColumn() {
      return currentRow == mazeWalls.length - 1;
    }
  }
  
  /**
   * A {@code Builder} is used to construct a {@code PerfectMaze} by walking through that maze. A
   * {@code PerfectMaze} is constructed by calling the movement methods (walkForward, turnRight, and
   * turnLeft) until the end of the path is reached. Then, endOfPath needs to be called to properly
   * alert the {@code Builder} its current position is outside the maze.
   * <p>
   * All of the movement methods change either the location or the direction, according to the
   * particular method. Subclasses should override these methods to build up or tear down walls
   * based on that movement, using the inherited setEdge methods provided to change the state of the
   * {@code Builder}. Great care should be taken to always call super.walkForward(),
   * super.turnRight(), or super.turnLeft() in their implementations. Failure to do so will result
   * in a broken state. Only the superclass method and the setEdge methods should be called, or the
   * state may be irreparably damaged.
   * <p>
   * The setEdge methods are guaranteed to alter the underlying state of the maze if and only if the
   * {@code Builder} is inside the maze. The initial x,y location a {@code Builder} starts in is not
   * considered part of the maze, nor is the location that the {@code Builder} is in when
   * {@link Builder#endOfTraversal()} is called. This allows the {@code Builder} to enter and exit
   * the maze without to determine the beginning and the end properly, while safely allowing
   * {@code Builder} subclasses to freely call the setEdge commands.
   * <p>
   * Subclasses are encouraged to provide a no-arg constructor and a 3-arg constructor that calls
   * the no-arg and 3-arg constructor of this class, respectively. The starting location and the
   * direction of the {@code Builder} are saved, which allows the {@link Builder#clear()} method to
   * restore that location and direction. If a subclass tracks its own location information, it
   * should override the clear() method, or the resulting location of the {@code Builder} is
   * undefined.
   * 
   * @author Scotty Boutin
   * @version 1.0.0
   */
  public static abstract class Builder {
    
    /**
     * The default x value of the starting coordinate, {@value}.
     */
    public static final int DEFAULT_X = 0;
    /**
     * The default y value of the starting coordinate, {@value}.
     */
    public static final int DEFAULT_Y = -1;
    /**
     * The default {@code Direction} the {@code Builder} faces in the starting coordinate,
     * {@link Direction#SOUTH}.
     */
    public static final Direction DEFAULT_DIRECTION = Direction.SOUTH;
    
    /**
     * Stores the starting coordinate for ease of reuse.
     * 
     * @see #clear()
     */
    private final Coordinate startingCoordinate;
    /**
     * Stores the starting direction for ease of reuse.
     * 
     * @see #clear()
     */
    private final Direction startingDirection;
    
    /**
     * Contains the state of coordinates and their walls. This is intentionally sorted by x, and
     * then y for ease of determining the size of the maze.
     */
    private final SortedMap<Coordinate, CoordinateWalls> coordinateWalls =
                                                                         new TreeMap<>(Coordinate.X_THEN_Y_ORDER);
    
    /**
     * The current {@code Coordinate} of the {@code Builder}.
     */
    private Coordinate currentCoordinate;
    /**
     * The current {@code Direction} of the {@code Builder}.
     */
    private Direction currentDirection;
    
    /**
     * Constructs a builder using default settings.
     * 
     * @see Builder#DEFAULT_X
     * @see Builder#DEFAULT_Y
     * @see Builder#DEFAULT_DIRECTION
     */
    protected Builder() {
      this(DEFAULT_X, DEFAULT_Y, DEFAULT_DIRECTION);
    }
    
    /**
     * Constructs a builder from the given x,y location, facing in the given {@code Direction}.
     * 
     * @param startingX The x value of the starting location.
     * @param startingY The y value of the starting location.
     * @param startingDirection The starting {@code Direction}.
     */
    protected Builder(int startingX, int startingY, Direction startingDirection) {
      currentCoordinate = this.startingCoordinate = Coordinate.of(startingX, startingY);
      currentDirection = this.startingDirection = startingDirection;
    }
    
    /**
     * This updates the {@code Builder}'s location by moving forward one location in the current
     * direction. Subclasses should implement their functionality for a {@code Builder} moving
     * forward by overriding this method, calling super.walkForward, performing whatever actions
     * they want to before and after the super call, and returning themselves.
     * <p>
     * As an implementation note, this method uses the {@link #getInitialWallsState()} method to add
     * new locations that have not been traversed yet. It is up to subclasses to determine what the
     * initial state of the walls of a particular location is. By default, the state is that all
     * walls are down. Subclasses are free to implement the getInitialWallsState method with any
     * valid type: this is guaranteed to be maintained correctly with the {@link WallsState}
     * enumerated type.
     * 
     * @return This object.
     * @see WallsState
     */
    public Builder walkForward() {
      currentCoordinate = currentCoordinate.getNeighbor(currentDirection);
      CoordinateWalls coordinateWithWalls;
      switch (getInitialWallsState()) {
        case ALL_WALLS_DOWN:
          coordinateWithWalls = new CoordinateWalls(currentCoordinate, false);
          break;
        
        default:
        case ALL_WALLS_UP:
          coordinateWithWalls = new CoordinateWalls(currentCoordinate, true);
          
      }
      coordinateWalls.putIfAbsent(currentCoordinate, coordinateWithWalls);
      return this;
    }
    
    /**
     * Returns the initial state of a location's walls in a maze.
     * 
     * @return The initial state of a location's walls in a maze.
     * @see WallsState
     */
    protected abstract WallsState getInitialWallsState();
    
    /**
     * This updates the {@code Builder}'s direction by turning right. Subclasses should implement
     * their functionality for a {@code Builder} turning right by overriding this method, calling
     * super.turn right, performing whatever actions they want to before and after the super call,
     * and returning themselves.
     * 
     * @return This object.
     */
    public Builder turnRight() {
      currentDirection = currentDirection.turnRight90Degrees();
      return this;
    }
    
    /**
     * This updates the {@code Builder}'s direction by turning left. Subclasses should implement
     * their functionality for a {@code Builder} turning left by overriding this method, calling
     * super.turnLeft, performing whatever actions they want to before and after the super call, and
     * returning themselves.
     * 
     * @return This object.
     */
    public Builder turnLeft() {
      currentDirection = currentDirection.turnLeft90Degrees();
      return this;
    }
    
    /**
     * This method is called to mark the end of a particular traversal through a
     * {@code PerfectMaze}. This should be called when the {@code Builder} has moved outside of the
     * rows and columns of the maze. This method ensures that the current x,y location is not
     * included in the built {@code PerfectMaze}, and it also conveniently turns the user's
     * direction around for traversal back through the maze.
     * <p>
     * A subclass that overrides this method should call super.endOfTraversal() to ensure that the
     * {@code Builder} has the proper state, if any decoration needs to be done to this method.
     * 
     * @return This object.
     */
    public Builder endOfTraversal() {
      coordinateWalls.remove(currentCoordinate);
      currentDirection = currentDirection.turn180Degrees();
      return this;
    }
    
    /**
     * Sets the edge in the same direction that the {@code Builder} is facing up or down, according
     * to the argument. {@code true} sets the wall up, while {@code false} tears the wall down.
     * 
     * @param wallUp Whether or not the wall should be up.
     */
    protected final void setForwardsEdge(boolean wallUp) {
      setEdge(currentDirection, wallUp);
    }
    
    /**
     * Sets the edge in the direction left of the current direction that the {@code Builder} is
     * facing up or down, according to the argument. {@code true} sets the wall up, while
     * {@code false} tears the wall down.
     * 
     * @param wallUp Whether or not the wall should be up.
     */
    protected final void setLeftEdge(boolean wallUp) {
      setEdge(currentDirection.turnLeft90Degrees(), wallUp);
    }
    
    /**
     * Sets the edge in the direction right of the current direction that the {@code Builder} is
     * facing up or down, according to the argument. {@code true} sets the wall up, while
     * {@code false} tears the wall down.
     * 
     * @param wallUp Whether or not the wall should be up.
     */
    protected final void setRightEdge(boolean wallUp) {
      setEdge(currentDirection.turnRight90Degrees(), wallUp);
    }
    
    /**
     * Sets the edge in the direction opposite the current direction that the {@code Builder} is
     * facing up or down, according to the argument. {@code true} sets the wall up, while
     * {@code false} tears the wall down.
     * 
     * @param wallUp Whether or not the wall should be up.
     */
    protected final void setBackwardsEdge(boolean wallUp) {
      setEdge(currentDirection.turn180Degrees(), wallUp);
    }
    
    /**
     * Sets the wall of the current coordinate's {@code Direction} to the argument.
     * 
     * @param direction The direction of a wall that is being changed.
     * @param wallUp Whether or not the wall should be up.
     */
    private void setEdge(Direction direction, boolean wallUp) {
      CoordinateWalls coordinateWithWalls = coordinateWalls.get(currentCoordinate);
      if (coordinateWithWalls != null) {
        coordinateWithWalls.setWall(direction, wallUp);
      }
    }
    
    /**
     * Resets the state of the {@code Builder} to its initialized location and {@code Direction}.
     * This uses the arguments given to the {@code Builder}'s constructor, or the default values if
     * the no-arg constructor was used.
     */
    public void clear() {
      coordinateWalls.clear();
      currentCoordinate = startingCoordinate;
      currentDirection = startingDirection;
    }
    
    /**
     * Constructs a {@code PerfectMaze} from the current state of the {@code Builder}. This can be
     * used repeatedly on the same {@code Builder} object. Each time it is called, it creates a new
     * {@code PerfectMaze} object with no shared state to any other {@code PerfectMaze}.
     * 
     * @return A {@code PerfectMaze} constructed from the current state of the {@code Builder}.
     */
    public final PerfectMaze build() {
      int rows = getNumRows();
      int columns = getNumColumns();
      CoordinateWalls[][] mazeWalls = new CoordinateWalls[rows][columns];
      
      Iterator<Coordinate> coordinateIterator = coordinateWalls.keySet().iterator();
      for (int column = 0; column < mazeWalls[0].length; column++) {
        for (int row = 0; row < mazeWalls.length; row++) {
          if (!coordinateIterator.hasNext()) {
            throw new IllegalStateException("Set of coordinates does not match size of array");
          }
          CoordinateWalls coordinateWithWalls = coordinateWalls.get(coordinateIterator.next());
          mazeWalls[row][column] = new CoordinateWalls(coordinateWithWalls);
        }
      }
      PerfectMaze maze = new PerfectMaze(mazeWalls);
      return maze;
    }
    
    /**
     * Gets the number of rows of the {@code PerfectMaze} being constructed.
     * 
     * @return The number of rows in the {@code PerfectMaze} being constructed.
     */
    private int getNumRows() {
      /*
       * Because a PerfectMaze is rectangular, the first Coordinate is at the top-left of the maze,
       * and the last Coordinate is at the bottom-right of the maze.
       */
      Coordinate firstCoordinate = coordinateWalls.firstKey();
      Coordinate lastCoordinate = coordinateWalls.lastKey();
      return Math.abs(firstCoordinate.getY() - lastCoordinate.getY()) + 1;
    }
    
    /**
     * Gets the number of columns of the {@code PerfectMaze} being constructed.
     * 
     * @return The number of columns in the {@code PerfectMaze} being constructed.
     */
    private int getNumColumns() {
      /*
       * Because a PerfectMaze is rectangular, the first Coordinate is at the top-left of the maze,
       * and the last Coordinate is at the bottom-right of the maze.
       */
      Coordinate firstCoordinate = coordinateWalls.firstKey();
      Coordinate lastCoordinate = coordinateWalls.lastKey();
      return Math.abs(firstCoordinate.getX() - lastCoordinate.getX()) + 1;
    }
  }
}
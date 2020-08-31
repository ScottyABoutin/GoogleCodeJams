package googlecodejam.alwaysturnleft;

/**
 * A {@code PerfectMazeBuilderWithoutWalls} is a {@code Builder} that initializes locations with
 * their walls down and builds them up as they move.
 * 
 * @author Scotty Boutin
 * @version 1.0.0
 */
public class PerfectMazeBuilderWithoutWalls extends PerfectMaze.Builder {
  
  /**
   * Tracks whether the previous command was walk forward. If this class grows in complexity,
   * consider replacing the boolean flag with the State pattern.
   */
  private boolean wasPreviousCommandWalkForward = false;
  
  /**
   * {@inheritDoc}
   */
  public PerfectMazeBuilderWithoutWalls() {
    super();
  }
  
  /**
   * {@inheritDoc}
   */
  public PerfectMazeBuilderWithoutWalls(int startingX, int startingY, Direction startingDirection) {
    super(startingX, startingY, startingDirection);
  }
  
  /**
   * Updates the {@code Builder}'s location by moving forward one location in the current direction.
   */
  @Override
  public PerfectMaze.Builder walkForward() {
    if (wasPreviousCommandWalkForward) {
      setLeftEdge(true);
    }
    super.walkForward();
    wasPreviousCommandWalkForward = true;
    
    return this;
  }
  
  /**
   * Updates the {@code Builder}'s direction by turning right.
   */
  @Override
  public PerfectMaze.Builder turnRight() {
    setLeftEdge(true);
    setForwardsEdge(true);
    super.turnRight();
    wasPreviousCommandWalkForward = false;
    
    return this;
  }
  
  /**
   * Updates the {@code Builder}'s direction by turning left.
   */
  @Override
  public PerfectMaze.Builder turnLeft() {
    super.turnLeft();
    wasPreviousCommandWalkForward = false;
    
    return this;
  }
  
  /**
   * {@inheritDoc} Additionally, clears the state of this subclass.
   */
  @Override
  public void clear() {
    super.clear();
    returnToDefaultState();
  }
  
  /**
   * {@inheritDoc} Additionally, clears the state of this subclass.
   */
  @Override
  public PerfectMaze.Builder endOfTraversal() {
    super.endOfTraversal();
    returnToDefaultState();
    return this;
  }
  
  /**
   * Used to reset the state of this class to its default.
   */
  private void returnToDefaultState() {
    wasPreviousCommandWalkForward = false;
  }
  
  /**
   * Returns the initial walls state of {@link WallsState#ALL_WALLS_DOWN}.
   * 
   * @return {@link WallsState#ALL_WALLS_DOWN}.
   */
  @Override
  protected WallsState getInitialWallsState() {
    return WallsState.ALL_WALLS_DOWN;
  }
}
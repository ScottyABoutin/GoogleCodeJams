package googlecodejam.alwaysturnleft;

/**
 * @author Scotty Boutin
 * @version 1.0.0
 */
public class PerfectMazeBuilderWithWalls extends PerfectMaze.Builder {
  
  /**
   * {@inheritDoc}
   */
  public PerfectMazeBuilderWithWalls() {
    super();
  }
  
  /**
   * {@inheritDoc}
   */
  public PerfectMazeBuilderWithWalls(int startingX, int startingY, Direction startingDirection) {
    super(startingX, startingY, startingDirection);
  }
  
  /**
   * Updates the {@code Builder}'s location by moving forward one location in the current direction.
   */
  @Override
  public PerfectMaze.Builder walkForward() {
    setForwardsEdge(false);
    super.walkForward();
    setBackwardsEdge(false);
    
    return this;
  }
  
  /**
   * Returns the initial walls state of {@link WallsState#ALL_WALLS_UP}.
   * 
   * @return {@link WallsState#ALL_WALLS_UP}.
   */
  @Override
  protected WallsState getInitialWallsState() {
    return WallsState.ALL_WALLS_UP;
  }
}
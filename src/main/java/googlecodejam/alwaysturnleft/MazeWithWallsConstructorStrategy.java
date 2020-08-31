package googlecodejam.alwaysturnleft;

/**
 * Represents a strategy for creating a {@code PerfectMaze} with the walls defaulted to being up. As
 * the {@code Builder} is used to build the {@code PerfectMaze}, walls will be removed.
 * 
 * @author Scotty Boutin
 * @version 1.0.0
 */
public class MazeWithWallsConstructorStrategy implements MazeConstructorStrategy {
  
  /**
   * Creates a {@code PerfectMaze.Builder} that is initialized with all walls up.
   * 
   * @return A {@code PerfectMaze.Builder} that is initialized with all walls up.
   */
  @Override
  public PerfectMaze.Builder createPerfectMazeBuilder() {
    return new PerfectMazeBuilderWithWalls();
  }
  
  /**
   * Constructs a {@code MazeWithWallsConstructorStrategy}.
   */
  public MazeWithWallsConstructorStrategy() {}
}
package googlecodejam.alwaysturnleft;

/**
 * Represents a strategy for creating a {@code PerfectMaze} with the walls defaulted to being down.
 * As the {@code Builder} is used to build the {@code PerfectMaze}, walls will be added.
 * 
 * @author Scotty Boutin
 * @version 1.0.0
 */
public class MazeWithoutWallsConstructorStrategy implements MazeConstructorStrategy {
  
  /**
   * Creates a {@code PerfectMaze.Builder} that is initialized with all walls down.
   * 
   * @return A {@code PerfectMaze.Builder} that is initialized with all walls down.
   */
  @Override
  public PerfectMaze.Builder createPerfectMazeBuilder() {
    return new PerfectMazeBuilderWithoutWalls();
  }
  
  /**
   * Constructs a {@code MazeWithoutWallsConstructorStrategy}.
   */
  public MazeWithoutWallsConstructorStrategy() {}
}
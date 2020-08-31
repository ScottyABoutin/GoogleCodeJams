package googlecodejam.alwaysturnleft;

/**
 * Represents a strategy for creating a {@code PerfectMaze}. Since the {@code PerfectMaze.Builder}
 * nested class handles constructing the maze, this strategy exists to faciliate creating
 * {@code PerfectMaze.Builder} in an abstract manner. For each {@code PerfectMaze.Builder} sublcass
 * that is implemented, a {@code MazeConstructorStrategy} should be created that creates that
 * {@code Builder}.
 * 
 * @author Scotty Boutin
 * @version 1.0.0
 */
@FunctionalInterface
public interface MazeConstructorStrategy {
  
  /**
   * Creates a {@code PerfectMaze.Builder} object that represents the given strategy.
   * 
   * @return A {@code PerfectMaze.Builder} object that represents the given strategy.
   */
  PerfectMaze.Builder createPerfectMazeBuilder();
}
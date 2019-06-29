package googlecodejam.alwaysturnleft;

/**
 * A {@code CodeJamInputInterpreter} interprets the expected format from this Google Code Jam to
 * build a {@code PerfectMaze}. The expected format is two paths, with each path consisting of
 * characters W, L, and R only. The two paths are separated by a single space character.
 * 
 * @author Scotty Boutin
 * @version 1.0.0
 */
public class CodeJamInputInterpreter {
  
  /**
   * The strategy that this uses to construct the {@code PerfectMaze}.
   */
  private MazeConstructorStrategy mazeConstructorStrategy;
  
  /**
   * Constructs a {@code CodeJamInputInterpreter} that uses a specific
   * {@code MazeConstructorStrategy} to build a {@code PerfectMaze} from the given input.
   * 
   * @param mazeConstructorStrategy The strategy used to construct the {@code PerfectMaze} from the
   *          input.
   */
  public CodeJamInputInterpreter(MazeConstructorStrategy mazeConstructorStrategy) {
    this.mazeConstructorStrategy = mazeConstructorStrategy;
  }
  
  /**
   * Creates a {@code PerfectMaze} by interpreting a given input {@code String}.
   * 
   * @param input The input {@code String} that contains the path information.
   * @return A {@code PerfectMaze} created using the given input.
   * @throws IllegalArgumentException If the input is not valid for this Code Jam.
   */
  public PerfectMaze interpret(String input) throws IllegalArgumentException {
    String[] paths = input.split(" ");
    validateInput(paths);
    
    PerfectMaze.Builder mazeBuilder = mazeConstructorStrategy.createPerfectMazeBuilder();
    walkMaze(paths, mazeBuilder);
    PerfectMaze maze = mazeBuilder.build();
    return maze;
    
  }
  
  /**
   * Validates the input for the expected format of this Google Code Jam. The format is invalid if
   * the input does not contain only two paths, or if either path contains an invalid character. An
   * invalid character is one that is not W, L, or R.
   * 
   * @param paths The array of paths being validated.
   * @throws IllegalArgumentException If the input does not contain only two paths, or if either
   *           path contains an invalid character. An invalid character is one that is not W, L, or
   *           R.
   */
  private void validateInput(String[] paths) throws IllegalArgumentException {
    if (paths.length != 2) {
      throw new IllegalArgumentException("Input must contain two paths.");
    }
    for (String path : paths) {
      if (!path.matches("^[WLR]*$")) {
        throw new IllegalArgumentException("Input can only contain characters W, L, and R");
      }
    }
  }
  
  /**
   * Walks the maze using the given input. This changes the state of the {@code Builder} to be able
   * to build a finished {@code PerfectMaze} upon completion.
   * 
   * @param paths The path being walked to construct the maze.
   * @param mazeBuilder The {@code Builder} used to construct the maze.
   */
  private void walkMaze(String[] paths, PerfectMaze.Builder mazeBuilder) {
    for (String path : paths) {
      for (char letter : path.toCharArray()) {
        switch (letter) {
          case 'W':
            mazeBuilder.walkForward();
            break;
          case 'L':
            mazeBuilder.turnLeft();
            break;
          case 'R':
            mazeBuilder.turnRight();
            break;
          default:
            // This is reliant on the validateInput method working
            // correctly
            assert (false);
        }
      }
      mazeBuilder.endOfTraversal();
    }
  }
}
package googlecodejam.alwaysturnleft;

import static googlecodejam.alwaysturnleft.Direction.EAST;
import static googlecodejam.alwaysturnleft.Direction.NORTH;
import static googlecodejam.alwaysturnleft.Direction.SOUTH;
import static googlecodejam.alwaysturnleft.Direction.WEST;

/**
 * A {@code CoordinateInterpreter} translates a {@code CoordinateWalls} object into a character, as
 * specified by the Code Jam. The table for translation is reprinted here for convenience:
 * <table summary="Room character representation">
 * <tr>
 * <th>Character
 * <th>Can walk north?
 * <th>Can walk south?
 * <th>Can walk west?
 * <th>Can walk east?
 * <tr>
 * <td>1
 * <td>Yes
 * <td>No
 * <td>No
 * <td>No
 * <tr>
 * <td>2
 * <td>No
 * <td>Yes
 * <td>No
 * <td>No
 * <tr>
 * <td>3
 * <td>Yes
 * <td>Yes
 * <td>No
 * <td>No
 * <tr>
 * <td>4
 * <td>No
 * <td>No
 * <td>Yes
 * <td>No
 * <tr>
 * <td>5
 * <td>Yes
 * <td>No
 * <td>Yes
 * <td>No
 * <tr>
 * <td>6
 * <td>No
 * <td>Yes
 * <td>Yes
 * <td>No
 * <tr>
 * <td>7
 * <td>Yes
 * <td>Yes
 * <td>Yes
 * <td>No
 * <tr>
 * <td>8
 * <td>No
 * <td>No
 * <td>No
 * <td>Yes
 * <tr>
 * <td>9
 * <td>Yes
 * <td>No
 * <td>No
 * <td>Yes
 * <tr>
 * <td>a
 * <td>No
 * <td>Yes
 * <td>No
 * <td>Yes
 * <tr>
 * <td>b
 * <td>Yes
 * <td>Yes
 * <td>No
 * <td>Yes
 * <tr>
 * <td>c
 * <td>No
 * <td>No
 * <td>Yes
 * <td>Yes
 * <tr>
 * <td>d
 * <td>Yes
 * <td>No
 * <td>Yes
 * <td>Yes
 * <tr>
 * <td>e
 * <td>No
 * <td>Yes
 * <td>Yes
 * <td>Yes
 * <td>
 * <tr>
 * <td>f
 * <td>Yes
 * <td>Yes
 * <td>Yes
 * <td>Yes
 * </table>
 * 
 * @author Scotty Boutin
 * @version 1.0.0
 */
public class CoordinateInterpreter {
  
  /**
   * Represents having no north wall (being able to move north).
   */
  private static final int NO_NORTH_WALL = 0B0001;
  /**
   * Represents having no south well (being able to move south).
   */
  private static final int NO_SOUTH_WALL = 0B0010;
  /**
   * Represents having no west wall (being able to move west).
   */
  private static final int NO_WEST_WALL = 0B0100;
  /**
   * Represents having no east wall (being able to move east).
   */
  private static final int NO_EAST_WALL = 0B1000;
  
  /**
   * Creates a {@code CoordinateInterpreter}.
   */
  public CoordinateInterpreter() {}
  
  /**
   * Interprets an {@code CoordinateWalls} object to determine how the maze can be traversed from
   * that coordinate's room, and returns a char representing that traversal.
   * 
   * @param coordinate The {@code CoordinateWalls} being translated.
   * @return A char (0, 1, 2, 3, 4, 5, 6, 7, 8, 9, a, b, c, d, e, or f) representing the directions
   *         that can be traversed from this room.
   */
  public char interpret(CoordinateWalls coordinate) {
    /*
     * Because each possibility represents a yes/no, use a bit vector represented by an int.
     */
    int encodedDirections = 0B0000;
    if (!coordinate.hasWall(NORTH))
      encodedDirections |= NO_NORTH_WALL;
    if (!coordinate.hasWall(SOUTH))
      encodedDirections |= NO_SOUTH_WALL;
    if (!coordinate.hasWall(WEST))
      encodedDirections |= NO_WEST_WALL;
    if (!coordinate.hasWall(EAST))
      encodedDirections |= NO_EAST_WALL;
    
    return Character.forDigit(encodedDirections, 16);
  }
}
/**
 * Provides the classes that solve the Google Code Jam Always Turn Left. The description of that
 * Google Code Jam is reprinted here for convenience.
 * <p>
 * <a href="https://code.google.com/codejam/contest/32003/dashboard#s=p1">Source </a>
 * <p>
 * The Code Jam is as follows:
 * <p>
 * You find yourself standing outside of a perfect maze. A maze is defined as "perfect" if it meets
 * the following conditions:
 * <ol>
 * <li>It is a rectangular grid of rooms, <b>R</b> rows by <b>C</b> columns.
 * <li>There are exactly two openings on the outside of the maze: the entrance and the exit. The
 * entrance is always on the north wall, while the exit could be on any wall.
 * <li>There is exactly one path between any two rooms in the maze (that is, exactly one path that
 * does not involve backtracking).
 * </ol>
 * <p>
 * You decide to solve the perfect maze using the "always turn left" algorithm, which states that
 * you take the leftmost fork at every opportunity. If you hit a dead end, you turn right twice (180
 * degrees clockwise) and continue. (If you were to stick out your left arm and touch the wall while
 * following this algorithm, you'd solve the maze without ever breaking contact with the wall.) Once
 * you finish the maze, you decide to go the extra step and solve it again (still always turning
 * left), but starting at the exit and finishing at the entrance.
 * <p>
 * The path you take through the maze can be described with three characters: 'W' means to walk
 * forward into the next room, 'L' means to turn left (or counterclockwise) 90 degrees, and 'R'
 * means to turn right (or clockwise) 90 degrees. You begin outside the maze, immediately adjacent
 * to the entrance, facing the maze. You finish when you have stepped outside the maze through the
 * exit. For example, if the entrance is on the north and the exit is on the west, your path through
 * the following maze would be {@code WRWWLWWLWWLWLWRRWRWWWRWWRWLW}:
 * <p>
 * <img src="./doc-files/maze.gif" alt="maze constructed from path">
 * <p>
 * If the entrance and exit were reversed such that you began outside the west wall and finished out
 * the north wall, your path would be {@code WWRRWLWLWWLWWLWWRWWRWWLW}. Given your two paths through
 * the maze (entrance to exit and exit to entrance), your code should return a description of the
 * maze.
 * <p>
 * Input
 * <p>
 * The first line of input gives the number of cases, <b>N</b>. <b>N</b> test cases follow. Each
 * case is a line formatted as
 * <p>
 * {@code entrance_to_exit exit_to_entrance}
 * <p>
 * All paths will be at least two characters long, consist only of the characters 'W', 'L', and 'R',
 * and begin and end with 'W'.
 * <p>
 * Output
 * <p>
 * For each test case, output one line containing "Case #<b>x</b>:" by itself. The next <b>R</b>
 * lines give a description of the <b>R</b> by <b>C</b> maze. There should be <b>C</b> characters in
 * each line, representing which directions it is possible to walk from that room. Refer to the
 * following legend:
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
 * <p>
 * Limits
 * <p>
 * 1 &le; <b>N</b> &le; 100.
 * <p>
 * Small dataset
 * <p>
 * 2 &le; len(entrance_to_exit) &le; 100,<br>
 * 2 &le; len(exit_to_entrance) &le; 100.
 * <p>
 * Large dataset
 * <p>
 * 2 &le; len(entrance_to_exit) &le; 10000,<br>
 * 2 &le; len(exit_to_entrance) &le; 10000.
 * <p>
 * Sample
 * <p>
 * Input<br>
 * {@code 2}<br>
 * {@code WRWWLWWLWWLWLWRRWRWWWRWWRWLW WWRRWLWLWWLWWLWWRWWRWWLW}<br>
 * {@code WW WW}
 * <p>
 * Output<br>
 * {@code Case #1:}<br>
 * {@code ac5}<br>
 * {@code 386}<br>
 * {@code 9c7}<br>
 * {@code e43}<br>
 * {@code 9c5}<br>
 * {@code Case #2:}<br>
 * {@code 3}
 * 
 * @see googlecodejam.alwaysturnleft.AlwaysTurnLeftCodeJamSolver
 */
package googlecodejam.alwaysturnleft;
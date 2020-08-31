/**
 * Provides the classes that solve the Google Code Jam Egg Drop. The description of that Google Code
 * Jam is reprinted here for convenience.
 * <p>
 * <a href="https://code.google.com/codejam/contest/32003/dashboard#s=p2">Source </a>
 * <p>
 * The Code Jam is as follows:
 * <p>
 * Imagine that you are in a building with <b>F</b> floors (starting at floor 1, the lowest floor),
 * and you have a large number of identical eggs, each in its own identical protective container.
 * For each floor in the building, you want to know whether or not an egg dropped from that floor
 * will break. If an egg breaks when dropped from floor <i>i</i>, then all eggs are guaranteed to
 * break when dropped from any floor <i>j &ge; i</i>. Likewise, if an egg doesn't break when dropped
 * from floor <i>i</i>, then all eggs are guaranteed to never break when dropped from any floor <i>j
 * &le; i</i>.
 * <p>
 * We can define <i>Solvable(F, D, B)</i> to be true if and only if there exists an algorithm to
 * determine whether or not an egg will break when dropped from any floor of a building with
 * <b>F</b> floors, with the following restrictions: you may drop a maximum of <b>D</b> eggs (one at
 * a time, from any floors of your choosing), and you may break a maximum of <b>B</b> eggs. You can
 * assume you have at least <b>D</b> eggs in your possession.
 * <p>
 * Input
 * <p>
 * The first line of input gives the number of cases, <b>N</b>. <b>N</b> test cases follow. Each
 * case is a line formatted as:
 * <p>
 * {@code F D B}
 * <p>
 * <i>Solvable(F, D, B)</i> is guaranteed to be true for all input cases.
 * <p>
 * Output
 * <p>
 * For each test case, output one line containing "Case #<b>x</b>: " followed by three
 * space-separated integers: F<sub>max</sub>, D<sub>min</sub>, and B<sub>min</sub>. The definitions
 * are as follows:
 * <ul>
 * <li>F<sub>max</sub> is defined as the largest value of <b>F'</b> such that <i>Solvable(<b>F'</b>,
 * D, B)</i> is true, or -1 if this value would be greater than or equal to 2<sup>32</sup>
 * (4294967296).<br>
 * (In other words, F<sub>max</sub> = -1 if and only if <i>Solvable(2<sup>32</sup>, D, B)</i> is
 * true.)
 * <li>D<sub>min</sub> is defined as the smallest value of <b>D'</b> such that <i>Solvable(F,
 * <b>D'</b>, B)</i> is true.
 * <li>B<sub>min</sub> is defined as the smallest value of <b>B'</b> such that <i>Solvable(F, D,
 * <b>B'</b>)</i> is true.
 * </ul>
 * <p>
 * Limits
 * <p>
 * 1 &le; <b>N</b> &le; 100.
 * <p>
 * Small dataset
 * <p>
 * 1 &le; <b>F</b> &le; 100,<br>
 * 1 &le; <b>D</b> &le; 100,<br>
 * 1 &le; <b>B</b> &le; 100.
 * <p>
 * Large dataset
 * <p>
 * 1 &le; <b>F</b> &le; 2000000000,<br>
 * 1 &le; <b>D</b> &le; 2000000000,<br>
 * 1 &le; <b>B</b> &le; 2000000000.
 * <p>
 * Sample
 * <table summary="Example input and output">
 * <tr>
 * <th>Input
 * <th>Output
 * <tr>
 * <td>{@code 2}
 * <tr>
 * <td>{@code 3 3 3}
 * <td>{@code Case #1: 7 2 1}
 * <tr>
 * <td>{@code 7 5 3}
 * <td>{@code Case #2: 25 3 2}
 * </table>
 * 
 * @see googlecodejam.eggdrop.EggDropCodeJamSolver
 */
package googlecodejam.eggdrop;
package googlecodejam.eggdrop;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import googlecodejam.GoogleCodeJamSolver;

/**
 * An {@code EggDropCodeJamSolver} solves the "Egg Drop" Code Jam.
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
 */
public class EggDropCodeJamSolver extends GoogleCodeJamSolver {
  
  /**
   * The maximum F value that can be returned from the {@link #solveForF(int, int)} method. If the
   * returned value would be larger than this, {@link #LARGER_THAN_MAX_F_VALUE} is returned instead.
   */
  public static final long MAX_F_VALUE = 0x1_0000_0000L - 1;
  /**
   * The value that is returned if an F<sub>max</sub> value is greater than {@link #MAX_F_VALUE}.
   */
  public static final long LARGER_THAN_MAX_F_VALUE = -1L;
  
  /**
   * The maximum D value that is used to store F<sub>max</sub> values in the cache. This value was
   * experimentally determined by finding the first (D,B) value for each B value that exceeds
   * {@link #MAX_F_VALUE}. These values are given in the following table, in descending B order:
   * <table summary="First D,B value that is greater than the maximum allowed F value based on the
   * B">
   * <tr>
   * <th>B
   * <th>D
   * <tr>
   * <td>32
   * <td>32
   * <tr>
   * <td>31
   * <td>33
   * <tr>
   * <td>30
   * <td>33
   * <tr>
   * <td>29
   * <td>33
   * <tr>
   * <td>28
   * <td>33
   * <tr>
   * <td>27
   * <td>33
   * <tr>
   * <td>26
   * <td>33
   * <tr>
   * <td>25
   * <td>33
   * <tr>
   * <td>24
   * <td>33
   * <tr>
   * <td>23
   * <td>33
   * <tr>
   * <td>22
   * <td>33
   * <tr>
   * <td>21
   * <td>33
   * <tr>
   * <td>20
   * <td>33
   * <tr>
   * <td>19
   * <td>33
   * <tr>
   * <td>18
   * <td>33
   * <tr>
   * <td>17
   * <td>33
   * <tr>
   * <td>16
   * <td>34
   * <tr>
   * <td>15
   * <td>34
   * <tr>
   * <td>14
   * <td>35
   * <tr>
   * <td>13
   * <td>36
   * <tr>
   * <td>12
   * <td>38
   * <tr>
   * <td>11
   * <td>41
   * <tr>
   * <td>10
   * <td>45
   * <tr>
   * <td>9
   * <td>52
   * <tr>
   * <td>8
   * <td>63
   * <tr>
   * <td>7
   * <td>83
   * <tr>
   * <td>6
   * <td>123
   * <tr>
   * <td>5
   * <td>221
   * <tr>
   * <td>4
   * <td>568
   * <tr>
   * <td>3
   * <td>2,954
   * <tr>
   * <td>2
   * <td>92,682
   * <tr>
   * <td>1
   * <td>n/a
   * <tr>
   * <td>0
   * <td>n/a
   * </table>
   */
  private static final int MAX_D_VALUE = 92_682;
  /**
   * The maximum B value that is stored in the cache. Any B value over this number will result in
   * data greater than {@link #MAX_F_VALUE} if D &ge; B. If D &lt; B, then cache[D][B] is equivalent
   * to cache[D][MAX_B_VALUE] because the number of breaks is greater than the number of drops.
   */
  private static final int MAX_B_VALUE = 32;
  
  /**
   * The cache that stores each F<sub>max</sub> given a D value and a B value. To access the cache
   * correctly, use [D][B] to get the F<sub>max</sub> for that (D,B) value.
   */
  private final long[][] fMaxCache;
  
  /**
   * Creates an {@code EggDropCodeJamSolver}. After this is constructed, {@link #go(String[])}
   * should be called on this object to solve the Code Jam.
   */
  public EggDropCodeJamSolver() {
    super();
    fMaxCache = initializeCache();
  }
  
  /**
   * Initializes the F<sub>max</sub> cache with all of its values.
   * 
   * @return A fully initialized cache with all of the values from (0,0) to ({@value #MAX_D_VALUE},
   *         {@value #MAX_B_VALUE}) for all (D,B) combinations.
   */
  private long[][] initializeCache() {
    // +1 to length of both dimensions since the constant is the max number
    // for that dimension, and we are tracking 0 as an option in the cache.
    final long[][] cache = new long[MAX_D_VALUE + 1][MAX_B_VALUE + 1];
    
    // If 0 drops are allowed, the maximum number of floors that can be
    // determined is always 0.
    Arrays.fill(cache[0], 0);
    
    // If 1 drop is allowed, the maximum number of floors that can be
    // determined is always 1 (dropping on floor 1, does it break or not)
    // unless the number of breaks allowed is 0.
    Arrays.fill(cache[1], 1);
    cache[1][0] = 0;
    
    // Track the minB value so that values to the right are auto-filled with
    // the overlargeF value.
    int minBValueOverFMax = 32;
    
    // Only go from D=2, others are handled in previous code.
    for (int d = 2; d < cache.length; d++) {
      
      // If 0 breaks allowed, then the answer is always 0.
      cache[d][0] = 0;
      // If 1 break is allowed, then the answer is always d (1 floor at a
      // time).
      cache[d][1] = d;
      if (d <= MAX_B_VALUE) {
        long squareDBValue = (cache[d - 1][d - 1] + 1) * 2 - 1;
        Arrays.fill(cache[d], d, MAX_B_VALUE + 1, squareDBValue);
      }
      for (int b = 2; b < d && b <= minBValueOverFMax; b++) {
        cache[d][b] = cache[d - 1][b - 1] + cache[d - 1][b] + 1;
        if (cache[d - 1][b - 1] == LARGER_THAN_MAX_F_VALUE
            || cache[d - 1][b] == LARGER_THAN_MAX_F_VALUE || cache[d][b] > MAX_F_VALUE) {
          Arrays.fill(cache[d], b, MAX_B_VALUE + 1, LARGER_THAN_MAX_F_VALUE);
          minBValueOverFMax = b;
          break;
        }
      }
    }
    return cache;
  }
  
  /**
   * Solves a single test case of the Egg Drop Google Code Jam.
   */
  @Override
  protected String solve(Scanner in) throws IOException {
    int maxFloors = in.nextInt();
    int maxDrops = in.nextInt();
    int maxBreaks = in.nextInt();
    
    long fMax = solveForF(maxDrops, maxBreaks);
    int dMin = solveForD(maxFloors, maxDrops, maxBreaks);
    int bMin = solveForB(maxFloors, maxDrops, maxBreaks);
    
    StringBuilder builder = new StringBuilder();
    builder.append(fMax).append(' ').append(dMin).append(' ').append(bMin);
    
    return builder.toString();
  }
  
  /**
   * Solves for the F<sub>max</sub> value, given a number of drops and a number of breaks.
   * 
   * @param drops The number of drops allowed.
   * @param breaks The number of breaks allowed.
   * @return The highest floor that is solvable given drops and breaks, or
   *         {@value LARGER_THAN_MAX_F_VALUE} if the highest floor is larger than 2<sup>32</sup>.
   */
  public long solveForF(int drops, int breaks) {
    // If no drops or breaks allowed, than you cannot determine any floor.
    if (drops == 0 || breaks == 0) {
      return 0;
    }
    
    if (breaks == 1) {
      /*
       * Do not even use cache, this can handle any number even beyond the cache length. Also, drops
       * is always less than the Max F value.
       */
      return drops;
    }
    
    /*
     * If data is beyond cache, answer at this point must be greater than the max F value.
     */
    if (drops > MAX_D_VALUE && breaks > 1) {
      return LARGER_THAN_MAX_F_VALUE;
    }
    
    /*
     * If the breaks is greater than the max value, then use the max break value instead. Anything
     * beyond this value is out of range for F value or a copy of the max value, as any situation
     * where B > D, F(D,B) = F(D, MaxB)
     */
    if (breaks > MAX_B_VALUE) {
      breaks = MAX_B_VALUE;
    }
    
    // Use the cache to find the max F value.
    return fMaxCache[drops][breaks];
  }
  
  /**
   * Solves for the D<sub>min</sub> value, given a number of floors and a number of breaks.
   * 
   * @param floors The number of floors in the building.
   * @param maxDrops The maximum number of drops that need to be checked, assuming that
   *          <i>Solvable(floors, maxDrops, breaks)</i> is true. If it is not true, the results of
   *          this method are undefined.
   * @param breaks The number of breaks allowed.
   * @return The minimum number of drops needed to guarantee that <i>Solvable(F, D, B)</i> is true
   *         given the F and B values.
   */
  public int solveForD(int floors, int maxDrops, int breaks) {
    // Check each drop value in order. Once one is found, all after will be
    // a greater d value, so report that number as solvable.
    for (int drops = 0; drops < maxDrops; drops++) {
      long maxF = solveForF(drops, breaks);
      if (maxF == LARGER_THAN_MAX_F_VALUE || maxF >= floors) {
        return drops;
      }
    }
    // Because this is solvable, just return maxDrops rather than
    // performing another solveForF.
    return maxDrops;
  }
  
  /**
   * Solves for the B<sub>min</sub> value, given a number of floors and a number of drops.
   * 
   * @param floors The number of floors in the building.
   * @param drops The number of drops allowed.
   * @param maxBreaks The maximum number of breaks that need to be checked, assuming that
   *          <i>Solvable(floors, drops, maxBreaks)</i> is true. If it is not true, the results of
   *          this method are undefined.
   * @return The minimum number of breaks needed to guarantee that <i>Solvable(F, D, B)</i> is true
   *         given the F and D values.
   */
  public int solveForB(int floors, int drops, int maxBreaks) {
    // Check each break value in order. Once one is found, all after will be
    // a greater b value, so report that number as solvable.
    for (int breaks = 0; breaks < maxBreaks; breaks++) {
      long maxF = solveForF(drops, breaks);
      if (maxF == LARGER_THAN_MAX_F_VALUE || maxF >= floors) {
        return breaks;
      }
    }
    
    // Because this is solvable, just return maxBreaks rather than
    // performing another solveForF.
    return maxBreaks;
  }
  
  /**
   * Runs the {@code EggDropCodeJamSolver} application.
   * 
   * @param args Can optionally contain a file name. If none is provided, this application reads its
   *          input from {@code System.in}.
   * @throws IOException If an I/O error occurs.
   */
  public static void main(String[] args) throws IOException {
    new EggDropCodeJamSolver().go(args);
  }
}
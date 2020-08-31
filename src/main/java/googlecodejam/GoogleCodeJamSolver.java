package googlecodejam;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FilterInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * <p>
 * A {@code GoogleCodeJamSolver} solves a particular problem from an input
 * source. It is meant to facilitate creating a specific Google Code Jam solver.
 * <p>
 * The intended function of subclasses is as follows:
 * <ol>
 * <li>In the main method of a subclass, create an instance of that subclass.
 * <li>Call {@link #go(String[])}, passing in the arguments from main. If main
 * provides an argument, that filename is used to load input. Otherwise,
 * System.in is used for input.
 * <li>Implement the solve method to use the input in whatever way required for
 * that code jam to solve one particular test case. Return a {@code String}
 * containing that result.
 * </ol>
 * <p>
 * Because the methods all throw {@code IOException}, it is left up to the
 * subclass to determine whether or not they should handle it. It is reasonable
 * for the main method to throw an {@code IOException} if the input does not
 * match the expected format of the Code Jam.
 * 
 * @author Scotty Boutin
 * @version 1.0.0
 */
public abstract class GoogleCodeJamSolver {
  
  /**
   * Sole constructor. (For invocation by subclass constructors, typically
   * implicit.)
   */
  protected GoogleCodeJamSolver() {
  }
  
  /**
   * <p>
   * Executes a Google Code Jam to solve it. First, it reads from the arguments to
   * determine whether it should load its input from a file or from
   * {@code System.in}. It writes the results to the console and to a file named
   * "solution.txt". The format of this is as follows:
   * <p>
   * Given a number of test cases from input, write that many lines in this
   * format:
   * <p>
   * [result of {@link #caseNumber(int)}][result of {@link #solve(Scanner)}
   * ][newLine]
   * <p>
   * The default for the resultOfCaseNumber method is: "Case #[number]: ". The
   * caseNumber method can be overridden in subclasses if a specific Google Code
   * Jam has a different case number format.
   * 
   * @param args
   *          The arguments from the {@code main} method of the subclass. Used to
   *          determine whether this should load its input from a file or from
   *          {@code System.in}.
   * @throws IOException
   *           If an I/O error occurs.
   */
  public final void go(String... args) throws IOException {
    try (Scanner in = initialize(args);
        FileWriter fw = new FileWriter("solution.txt");
        BufferedWriter writer = new BufferedWriter(fw)) {
      
      int numTestCases = Integer.parseInt(in.nextLine());
      for (int testNum = 1; testNum <= numTestCases; testNum++) {
        StringBuilder messageBuilder = new StringBuilder(caseNumber(testNum));
        messageBuilder.append(solve(in));
        String answer = messageBuilder.toString();
        writer.append(answer);
        writer.newLine();
        System.out.println(answer);
      }
    }
  }
  
  /**
   * Initializes the {@code Scanner} from either file-based resource or from the
   * System input stream {@code System.in}.
   * 
   * @param args
   *          The arguments from the {@code main} method of the subclass.
   * @return a {@code Scanner} with a valid source.
   * @throws IOException
   *           If the input stream cannot be opened.
   */
  private Scanner initialize(String[] args) throws IOException {
    if (args.length > 0) {
      String resourceName = args[0];
      return new Scanner(getClass().getClassLoader().getResource(resourceName).openStream());
    } else {
      /**
       * A {@code SysInWrapper} wraps around the {@code System.in} stream to guarantee
       * that it is not closed. When used in a try-with-resources, there is a need to
       * not let the {@code System.in} stream close, so this wraps around it and turns
       * close into a no-op.
       * <p>
       * Currently, the single instance of this should be gotten from the static
       * variable {{@link #INSTANCE}. As this is a private class, this implementation
       * is subject to change.
       * 
       * @author Scotty Boutin
       * @version 1.0.0
       */
      class SysInWrapper extends FilterInputStream {
        
        /**
         * Creates a wrapper around the {@code System.in} stream.
         */
        SysInWrapper() {
          super(System.in);
        }
        
        /**
         * Do not close {@code System.in}.
         */
        @Override
        public void close() {
        }
      }
      return new Scanner(new SysInWrapper());
    }
  }
  
//  
//  private static class SysInWrapper extends FilterInputStream {
//    
//    /**
//     * The single instance maintained by the class.
//     */
//    private static final SysInWrapper INSTANCE = new SysInWrapper();
//    
//    /**
//     * Creates a wrapper around the {@code System.in} stream.
//     */
//    private SysInWrapper() {
//      super(System.in);
//    }
//    
//    /**
//     * Do not close {@code System.in}.
//     */
//    @Override
//    public void close() {
//    }
//  }
  
  /**
   * Prints the format of a case number. The default for the resultOfCaseNumber
   * method is: "Case #[number]: ". This should be overridden if a specific Google
   * Code Jam has a different case number format.
   * 
   * @param testNumber
   *          The number of the particular case.
   * @return A {@code String} representing the case number format.
   */
  protected String caseNumber(int testNumber) {
    return new StringBuilder("Case #").append(testNumber).append(": ").toString();
  }
  
  /**
   * Solves a particular test case. It is up to each subclass's implementation to
   * understand and read the format of a particular Code Jam using the input
   * source. A subclass should not need to catch exceptions thrown by the
   * {@code Scanner} based on the wrong input type (i.e. an
   * {@code InputMismatchException}).
   * 
   * @param in
   *          The input source used to read the test case's information.
   * @return A {@code String} containing the test case's solution.
   * @throws IOException
   *           If an I/O error occurs while reading input.
   */
  protected abstract String solve(Scanner in) throws IOException;
}

package googlecodejam.aliennumbers;

/**
 * This runtime exception is thrown to indicate that an {@code AlienNumeralSystem} has been asked
 * for a value that is out of range of the given system.
 * 
 * @author Scotty Boutin
 * @version 1.0.0
 */
public class AlienNumeralSystemIndexOutOfBoundsException extends IndexOutOfBoundsException {
  
  /**
   * SerialVersionUID since 1.0.0.
   */
  private static final long serialVersionUID = 3997383668689614051L;
  
  /**
   * Constructs an {@code AlienNumeralSystemIndexOutOfBoundsException} using the index value that
   * caused this to be thrown. It is stored in a user-friendly message, which is accessible through
   * the {@link Throwable#getMessage()} method.
   * 
   * @param index The index that caused this exception to be thrown.
   */
  public AlienNumeralSystemIndexOutOfBoundsException(int index) {
    super("Index out of range: " + index);
  }
  
}

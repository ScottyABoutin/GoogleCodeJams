package googlecodejam.aliennumbers;

/**
 * This runtime exception is thrown to indicate that an {@code AlienNumeralSystem} was created
 * incorrectly.
 * 
 * @author Scotty Boutin
 * @version 1.0.0
 */
public class InvalidAlienNumeralSystemException extends IllegalArgumentException {
  
  /**
   * SerialVersionUID since 1.0.0.
   */
  private static final long serialVersionUID = 1241592349518254748L;
  
  /**
   * Creates an {@code InvalidAlienNumeralSystemException} with no message.
   */
  public InvalidAlienNumeralSystemException() {
    super();
  }
  
  /**
   * Creates an {@code InvalidAlienNumeralSystemException} with the given detail message.
   * 
   * @param message The detail message (which is saved for later retrieval by the
   *          {@link Throwable#getMessage()} method).
   */
  public InvalidAlienNumeralSystemException(String message) {
    super(message);
  }
  
  /**
   * Creates an {@code InvalidAlienNumeralSystemException} with the given detail message, which is
   * appended by ": " and the letter.
   * 
   * @param message The detail message (which is saved for later retrieval by the
   *          {@link Throwable#getMessage()} method).
   * @param letter The letter that caused this exception to be thrown.
   */
  public InvalidAlienNumeralSystemException(String message, char letter) {
    this(message + ": " + letter);
  }
}
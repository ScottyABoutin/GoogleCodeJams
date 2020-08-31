package googlecodejam.aliennumbers;

/**
 * This runtime exception is thrown to indicate that a given letter is not found in a language.
 * 
 * @author Scotty Boutin
 * @version 1.0.0
 */
public class NoLetterInAlienNumeralSystemException extends IllegalArgumentException {
  
  /**
   * SerialVersionUID since 1.0.0.
   */
  private static final long serialVersionUID = 1298828879752668151L;
  
  /**
   * The letter that does not exist in a given language.
   * 
   * @serial
   */
  private final char letter;
  
  /**
   * The language that the letter does not exist in.
   * 
   * @serial
   */
  private final String language;
  
  /**
   * Constructs a {@code NoLetterInAlienNumeralSystemException} with the given detail message,
   * letter, and language.
   * 
   * @param message The detail message (which is saved for later retrieval by the
   *          {@link Throwable#getMessage()} method).
   * @param letter The letter that is not in the given language.
   * @param language The language that the letter does not exist in.
   */
  public NoLetterInAlienNumeralSystemException(String message, char letter,
                                               AlienNumeralSystem language) {
    this(message, null, letter, language);
  }
  
  /**
   * Constructs a {@code NoLetterInAlienNumeralSystemException} with the given detail message,
   * cause, letter, and language.
   * 
   * @param message The detail message (which is saved for later retrieval by the
   *          {@link Throwable#getMessage()} method).
   * @param cause The cause (which is saved for later retrieval by the {@link Throwable#getCause()}
   *          method). (A {@code null} value is permitted, and indicates that the cause is
   *          nonexistent or unknown.)
   * @param letter The letter that is not in the given language.
   * @param language The language that the letter does not exist in.
   */
  public NoLetterInAlienNumeralSystemException(String message, Throwable cause, char letter,
                                               AlienNumeralSystem language) {
    super(message, cause);
    this.letter = letter;
    this.language = language.getLanguage();
  }
  
  /**
   * Retrieves the letter associated with this exception.
   * 
   * @return The letter associated with this exception.
   */
  public char getLetter() {
    return letter;
  }
  
  /**
   * Retrieves the language associated with this exception.
   * 
   * @return The language associated with this exception.
   */
  public String getLanguage() {
    return language;
  }
  
  /**
   * Convenience method to construct a {@code NoLetterInAlienNumeralSystemException} with a common
   * message. Currently, that message is in the format "letter is not a character in this language",
   * but this is subject to change in a future release.
   * 
   * @param letter The letter that is not in the given language.
   * @param language The language that the letter does not exist in.
   * @return A {@code NoLetterInAlienNumeralSystemException} from the given arguments and the
   *         message detail described above.
   */
  public static NoLetterInAlienNumeralSystemException of(char letter, AlienNumeralSystem language) {
    return new NoLetterInAlienNumeralSystemException(letter
                                                     + " is not a character in this language",
                                                     letter, language);
  }
}
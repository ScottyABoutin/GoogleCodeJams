package googlecodejam.aliennumbers;

import java.util.HashSet;
import java.util.Set;

/**
 * An {@code AlienNumeralSystem} is a representation of an abstract numerical system. It is composed
 * of unique symbols with ASCII values 33 - 126 (or hexadecimal values 21-7E) and is at least of
 * size 2. The values of the digits within are represented based on their position in the language,
 * from 0 to the number of symbols in the language - 1. For example, the decimal numeral system
 * would be represented by the {@code String} "0123456789", the binary numeral system would be
 * represented by "01", and the hexadecimal numeral system would be represented by
 * "0123456789abcdef".
 * 
 * @author Scotty Boutin
 * @version 1.0.0
 */
public class AlienNumeralSystem {
  
  /**
   * The smallest character that is allowed in an {@code AlienNumeralSystem}.
   */
  public static final char MIN_SYMBOL = 0x0021;
  /**
   * The largest character that is allowed in an {@code AlienNumeralSystem}.
   */
  public static final char MAX_SYMBOL = 0x007E;
  
  /**
   * Exception message used during validation when a language doesn't have enough symbols.
   */
  private static final String SIZE_TOO_SMALL_EXCEPTION_MESSAGE =
                                                               "A language must contain at least 2 characters";
  /**
   * Exception message used during validation when a language contains duplicate symbols.
   */
  private static final String DUPLICATE_CHARACTER_EXCEPTION_MESSAGE =
                                                                    "A language cannot contain duplicate characters";
  /**
   * Exception message used during validation when a language contains an invalid symbol.
   */
  private static final String INVALID_CHARACTER_EXCEPTION_MESSAGE =
                                                                  "A language can only contain characters with ASCII value 33-126";
  /**
   * Exception message used during validation when a language fails more generically.
   */
  private static final String GENERIC_INVALID_LANGUAGE_EXCEPTION_MESSAGE =
                                                                         "This language either contains duplicate characters or a character outside of the proper ASCII values.";
  /**
   * Exception message used during validation when a language is {@code null}.
   */
  private static final String NULL_LANGUAGE_EXCEPTION_MESSAGE =
                                                              "Language provided must not be null";
  
  /**
   * The language of the {@code AlienNumeralSystem}.
   */
  private final String language;
  
  /**
   * Constructs an {@code AlienNumeralSystem} from the given language.
   * 
   * @param language The language that this numeral system is based off of.
   * @throws InvalidAlienNumeralSystemException When a language is not valid.
   */
  public AlienNumeralSystem(String language) throws InvalidAlienNumeralSystemException {
    validateLanguage(language);
    this.language = language;
  }
  
  /**
   * Validates a language to ensure it is legal. This is determined by the following rules:
   * <ol>
   * <li>A language must not be {@code null}.
   * <li>A language must be larger than 1 symbol (minimum base 2).
   * <li>A language cannot contain duplicate characters.
   * <li>All symbols within a language should be ASCII 33 - 126 (HEX 21-7E).
   * </ol>
   * 
   * @param language The language being validated.
   * @throws InvalidAlienNumeralSystemException When a language is not valid.
   */
  private void validateLanguage(String language) throws InvalidAlienNumeralSystemException {
    // Language cannot be null
    if (language == null) {
      throw new InvalidAlienNumeralSystemException(NULL_LANGUAGE_EXCEPTION_MESSAGE);
    }
    // Language must be at least base 2
    if (language.length() < 2) {
      throw new InvalidAlienNumeralSystemException(SIZE_TOO_SMALL_EXCEPTION_MESSAGE);
    }
    
    // Optimization to check whether an invalid character exists.
    // If there are more symbols than could possibly be unique and also be
    // within the min symbol and the max symbol, then there is either a
    // duplicate character, an invalid character, or both.
    if (language.length() > MAX_SYMBOL - MIN_SYMBOL + 1) {
      throw new InvalidAlienNumeralSystemException(GENERIC_INVALID_LANGUAGE_EXCEPTION_MESSAGE);
    }
    
    // Check each character in the language is within the bounds, and add it
    // to a Set to check for duplicates
    Set<Character> letters = new HashSet<Character>();
    for (char letter : language.toCharArray()) {
      if (letter < MIN_SYMBOL || letter > MAX_SYMBOL) {
        throw new InvalidAlienNumeralSystemException(INVALID_CHARACTER_EXCEPTION_MESSAGE, letter);
      }
      if (!letters.add(letter)) {
        throw new InvalidAlienNumeralSystemException(DUPLICATE_CHARACTER_EXCEPTION_MESSAGE, letter);
      }
    }
  }
  
  /**
   * Gets the numerical value of a letter in a language.
   * 
   * @param letter The letter that represents a numerical value in this language.
   * @return The numerical value of the letter.
   * @throws NoLetterInAlienNumeralSystemException When the letter does not exist in this
   *           {@code AlienNumeralSystem}.
   */
  public int getValue(char letter) throws NoLetterInAlienNumeralSystemException {
    int index = language.indexOf(letter);
    if (index == -1) {
      throw NoLetterInAlienNumeralSystemException.of(letter, this);
    }
    return index;
  }
  
  /**
   * Gets the letter associated with the given value.
   * 
   * @param value The numerical value represented by a letter in this language.
   * @return The letter associated with the given value.
   * @throws AlienNumeralSystemIndexOutOfBoundsException When the value is negative or is greater
   *           than or equal to the base of the language.
   */
  public char getLetter(int value) throws AlienNumeralSystemIndexOutOfBoundsException {
    if (value < 0 || value >= getBaseNumber()) {
      throw new AlienNumeralSystemIndexOutOfBoundsException(value);
    }
    return language.charAt(value);
  }
  
  /**
   * Returns the language associated with this numeral system as a {@code String}.
   * 
   * @return The language associated with this numeral system.
   */
  public String getLanguage() {
    return language;
  }
  
  /**
   * Returns the base number of this numeral system. This number is derivable from the number of
   * characters in the language. For example, the decimal numeric system "0123456789" is base-10
   * because it contains 10 characters.
   * 
   * @return The base number of this numeral system.
   */
  public int getBaseNumber() {
    return language.length();
  }
  
  /**
   * Returns a hash code for this {@code AlienNumeralSystem}.
   * 
   * @return A hash code for this {@code AlienNumeralSystem}.
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 17;
    result = prime * result + ((language == null) ? 0
                                                  : language.hashCode());
    return result;
  }
  
  /**
   * Compares this {@code AlienNumeralSystem} to the specified object for equality. The result is
   * {@code true} if and only if the other object is an {@code AlienNumeralSystem} that uses the
   * same symbols for its language as this object.
   * 
   * @param obj The other object this is being compared to, {@code null} returns false.
   * @return {@code true} if this and the object are equal, {@code false} otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!(obj instanceof AlienNumeralSystem))
      return false;
    AlienNumeralSystem other = (AlienNumeralSystem) obj;
    if (language == null) {
      if (other.language != null)
        return false;
    } else if (!language.equals(other.language))
      return false;
    return true;
  }
  
  /**
   * Returns a {@code String} representation of this {@code AlienNumeralSystem}. Specifically, it
   * returns a {@code String} that represents the language of this system.
   * 
   * @return The symbols in order from least to greatest within this object as a {@code String}.
   */
  @Override
  public String toString() {
    return language;
  }
}
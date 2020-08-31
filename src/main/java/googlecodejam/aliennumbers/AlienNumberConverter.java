package googlecodejam.aliennumbers;

/**
 * An {@code AlienNumberConverter} converts a word from a source alien numeral system to the same
 * word in a destination alien numeral system.
 * 
 * @author Scotty Boutin
 * @version 1.0.0
 */
public class AlienNumberConverter {
  
  /**
   * The {@code AlienNumeralSystem} that words are converted from.
   */
  private final AlienNumeralSystem fromLanguage;
  /**
   * The {@code AlienNumeralSystem} that words are converted to.
   */
  private final AlienNumeralSystem toLanguage;
  
  /**
   * Constructs an {@code AlienNumberConverter} from a source {@code AlienNumeralSystem} and a
   * destination {@code AlienNumeralSystem}.
   * 
   * @param fromLanguage The {@code AlienNumeralSystem} that words will be converted from.
   * @param toLanguage The {@code AlienNumeralSystem} that words will be converted to.
   */
  AlienNumberConverter(AlienNumeralSystem fromLanguage, AlienNumeralSystem toLanguage) {
    /*
     * Implementation note: package level is purposefully done to not expose AlienNumeralSystem use
     * outside this package
     */
    this.fromLanguage = fromLanguage;
    this.toLanguage = toLanguage;
  }
  
  /**
   * Constructs an {@code AlienNumberConverter} from a source language and a target language,
   * represented by {@code String}s. The rules of a language's validity are reprinted here for
   * convenience:
   * <ol>
   * <li>An alien numeral system must be at least 2 digits
   * <li>No digit will be repeated in any representation
   * <li>Each digit will either be a number 0-9, an uppercase or lowercase letter, or one of the
   * following symbols {@literal !"#$%&'()*+,-./:; <=>?@[\]^_`{|}~}
   * </ol>
   * 
   * @param fromLanguage The language used to represent an alien numeral system.
   * @param toLanguage The language used to represent an alien numeral system.
   * @throws InvalidAlienNumeralSystemException If either of the languages represent invalid alien
   *           numeral systems.
   */
  public AlienNumberConverter(String fromLanguage,
                              String toLanguage) throws InvalidAlienNumeralSystemException {
    this(new AlienNumeralSystem(fromLanguage), new AlienNumeralSystem(toLanguage));
  }
  
  /**
   * Converts a number written in the source language;s characters to the same number written in the
   * destination language's characters.
   * 
   * @param alienNumber A number written in the source language's characters.
   * @return The same number, but written in the destination language's characters.
   * @throws NoLetterInAlienNumeralSystemException If the alien number contains a character not in
   *           the source language.
   */
  public String convert(String alienNumber) throws NoLetterInAlienNumeralSystemException {
    if (alienNumber == null || alienNumber.equals("")) {
      throw new IllegalArgumentException("Empty alien number not allowed");
    }
    
    alienNumber = removeLeadingZeroes(alienNumber);
    
    // If languages are same, no need for conversion
    if (fromLanguage.equals(toLanguage)) {
      return alienNumber;
    }
    // Same numerical 'Base', so utilize a faster simple swap.
    if (fromLanguage.getBaseNumber() == toLanguage.getBaseNumber()) {
      return convertLanguagesOfSameLength(alienNumber);
    }
    
    return convertLanguagesOfDifferentLength(alienNumber);
    
  }
  
  /**
   * Removes the zeroes from the alien number using the source language.
   * 
   * @param alienNumber The number that 0's are removed from.
   * @return A new {@code String} that is the same as the input, but with the all of the leading '0'
   *         characters removed (as defined by the first character in the source language). If the
   *         entire input is only composed of 0 characters, a single 0 is returned.
   * @throws NoLetterInAlienNumeralSystemException If the alien number contains a character not in
   *           the source language.
   */
  private String
          removeLeadingZeroes(String alienNumber) throws NoLetterInAlienNumeralSystemException {
    char letter0 = fromLanguage.getLetter(0);
    StringBuilder builder = new StringBuilder(alienNumber);
    while (builder.length() > 0 && builder.charAt(0) == letter0) {
      builder.deleteCharAt(0);
    }
    if (builder.length() == 0)
      builder.append(letter0);
    return builder.toString();
  }
  
  /**
   * Converts an alien number from one language to another if both are the same length.
   * 
   * @param alienNumber The alien number that is being converted.
   * @return The alien number in the destination langauge's representation.
   * @throws NoLetterInAlienNumeralSystemException If the alien number contains a character not in
   *           the source language.
   */
  private String
          convertLanguagesOfSameLength(final String alienNumber) throws NoLetterInAlienNumeralSystemException {
    StringBuilder newNumber = new StringBuilder();
    for (char letter : alienNumber.toCharArray()) {
      int index = fromLanguage.getValue(letter);
      newNumber.append(toLanguage.getLetter(index));
    }
    return newNumber.toString();
  }
  
  /**
   * Converts an alien number from one language to another if the two languages have a different
   * length.
   * 
   * @param alienNumber The alien number that is being converted.
   * @return The alien number in the destination langauge's representation.
   * @throws NoLetterInAlienNumeralSystemException If the alien number contains a character not in
   *           the source language.
   */
  private String
          convertLanguagesOfDifferentLength(final String alienNumber) throws NoLetterInAlienNumeralSystemException {
    // Get word reversed to iterate through word's numeric value more
    // effectively.
    String reversedInput = reverseString(alienNumber);
    
    // Determine numerical value from the first language.
    long numericalValue = 0;
    for (int index = 0, fromLanguageBase = fromLanguage.getBaseNumber(), multiplier = 1;
         index < alienNumber.length(); index++, multiplier *= fromLanguageBase) {
      char letter = reversedInput.charAt(index);
      numericalValue += (fromLanguage.getValue(letter) * multiplier);
    }
    
    // If 0, return language 2's 0 value.
    if (numericalValue == 0) {
      char letter0 = toLanguage.getLetter(0);
      return Character.toString(letter0);
    }
    
    StringBuilder newNumberReversed = new StringBuilder();
    // Convert numerical value to second language
    for (int toLanguageBase = toLanguage.getBaseNumber(); numericalValue > 0;
         numericalValue /= toLanguageBase) {
      int secondBaseValue = (int) (numericalValue % toLanguageBase);
      newNumberReversed.append(toLanguage.getLetter(secondBaseValue));
    }
    
    return reverseString(newNumberReversed.toString());
  }
  
  /**
   * Reverses a {@code String}.
   * 
   * @param string The {@code String} being reversed.
   * @return The reverse of the input {@code String}.
   */
  private String reverseString(final String string) {
    int inputLength = string.length();
    char[] reversedCharArray = new char[inputLength], inputCharArray = string.toCharArray();
    
    for (int index = 0, reversedIndex = inputLength - 1; index < inputLength;
         index++, reversedIndex--) {
      reversedCharArray[reversedIndex] = inputCharArray[index];
    }
    
    return new String(reversedCharArray);
  }
}
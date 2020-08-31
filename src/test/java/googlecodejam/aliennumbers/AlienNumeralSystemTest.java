package googlecodejam.aliennumbers;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

class AlienNumeralSystemTest {
  
  // private static final String MAX_NUMBER_OF_SYMBOLS_STRING =
  // "!\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~";
  private static final String MAX_NUMBER_OF_SYMBOLS_STRING = initializeFromUnicodeValues();
  
  private static String initializeFromUnicodeValues() {
    char[] chars = new char[0x007E - 0x0021 + 1];
    char charValue = 0x0021;
    for (int i = 0; i < chars.length; i++, charValue++) {
      chars[i] = charValue;
    }
    return new String(chars);
    
  }
  
  // -------------------------------------------------------------------//
  // Testing creation of AlienNumeralSystem fails when given an invalid
  // language
  // -------------------------------------------------------------------//
  
  @Test
  void test_CreateLanguageWithNull_ThrowsException() {
    assertThrows(InvalidAlienNumeralSystemException.class,
      () -> new AlienNumeralSystem(null));
  }
  
  @Test
  void test_CreateLanguageWithEmptyString_ThrowsException() {
    assertThrows(InvalidAlienNumeralSystemException.class,
      () -> new AlienNumeralSystem(""));
  }
  
  @Test
  void test_CreateLanguageWithLength1String_ThrowsException() {
    assertThrows(InvalidAlienNumeralSystemException.class,
      () -> new AlienNumeralSystem("1"));
  }
  
  @Test
  void test_CreateLanguageWithDuplicateLetters_NextToEachOther_ThrowsException() {
    assertThrows(InvalidAlienNumeralSystemException.class,
      () -> new AlienNumeralSystem("hello"));
  }
  
  @Test
  void test_CreateLanguageWithDuplicateLetters_NotNextToEachOther_ThrowsException() {
    assertThrows(InvalidAlienNumeralSystemException.class,
      () -> new AlienNumeralSystem("local"));
  }
  
  @Test
  void test_CreateLanguage_WithASCIISymbolLessThan33_UnicodeLessThan21_ThrowsException() {
    assertThrows(InvalidAlienNumeralSystemException.class,
      () -> new AlienNumeralSystem("abc\u0020"));
  }
  
  @Test
  void test_CreateLanguage_WithMinimumCharacterValue_ThrowsException() {
    assertThrows(InvalidAlienNumeralSystemException.class,
      () -> new AlienNumeralSystem("ab" + Character.MIN_VALUE + "cd"));
  }
  
  @Test
  void test_CreateLanguage_WithASCIISymbolGreaterThan126_UnicodeGreaterThan7E_ThrowsException() {
    assertThrows(InvalidAlienNumeralSystemException.class,
      () -> new AlienNumeralSystem("abc\u007f"));
  }
  
  @Test
  void test_CreateLanguage_WithMaximumCharacterValue_ThrowsException() {
    assertThrows(InvalidAlienNumeralSystemException.class,
      () -> new AlienNumeralSystem("ab" + Character.MAX_VALUE + "cd"));
  }
  
  @Test
  void test_CreateLanguageWithTooManySymbols_ThrowsException() {
    assertThrows(InvalidAlienNumeralSystemException.class,
      () -> new AlienNumeralSystem(MAX_NUMBER_OF_SYMBOLS_STRING + "\uffff"));
  }
  
  // -------------------------------------------------------------------------//
  // Testing creation of AlienNumeralSystem succeeds in normal cases and edge
  // cases
  // -------------------------------------------------------------------------//
  
  @Test
  void test_CreateLanguageWithMinimumNumberOfPossibleCharacters_Succeeds() {
    assertDoesNotThrow(() -> new AlienNumeralSystem("01"));
  }
  
  @Test
  void test_CreateLanguageWithMaximumNumberOfPossibleCharacters_Succeeds() {
    assertDoesNotThrow(() -> new AlienNumeralSystem(MAX_NUMBER_OF_SYMBOLS_STRING));
  }
  
  @Test
  void test_CreateLanguageWithLowestPossibleCharacters_Succeeds() {
    assertDoesNotThrow(() -> new AlienNumeralSystem("!\"#$"));
  }
  
  @Test
  void test_CreateLanguageWithHighestPossibleCharacters_Succeeds() {
    assertDoesNotThrow(() -> new AlienNumeralSystem("{|}~"));
  }
  
  @Test
  void test_LanguageInstantiationFromString() {
    AlienNumeralSystem binary = new AlienNumeralSystem("01");
    assertEquals("01", binary.getLanguage());
  }
  
  @Test
  void test_LanguageEquality() {
    AlienNumeralSystem binary1 = new AlienNumeralSystem("01");
    AlienNumeralSystem binary2 = new AlienNumeralSystem("01");
    AlienNumeralSystem largest1 = new AlienNumeralSystem(MAX_NUMBER_OF_SYMBOLS_STRING);
    AlienNumeralSystem largest2 = new AlienNumeralSystem(MAX_NUMBER_OF_SYMBOLS_STRING);
    assertAll(
      () -> assertEquals(binary1, binary2),
      () -> assertEquals(binary2, binary1),
      () -> assertEquals(largest1, largest2),
      () -> assertEquals(largest2, largest1)
    );
  }
  
  @Test
  void test_LanguageBaseNumberWorksForAllSizes() {
    Map<Integer, AlienNumeralSystem> numberRange = IntStream.range(2, MAX_NUMBER_OF_SYMBOLS_STRING.length()).collect(
        HashMap::new, (map, i) -> map.put(i, new AlienNumeralSystem(MAX_NUMBER_OF_SYMBOLS_STRING.substring(0, i))),
        Map::putAll);
    assertAll("Base number does not match number properly",
      numberRange.entrySet().stream().map(entry ->
        () -> assertEquals(entry.getKey(), entry.getValue().getBaseNumber()))
    );
  }
  
  @Test
  void test_GetLetter_OnLanguageWithoutThatLetter_ThrowsException() {
    assertThrows(NoLetterInAlienNumeralSystemException.class,
      () -> new AlienNumeralSystem("helo").getValue('x'));
  }
  
}
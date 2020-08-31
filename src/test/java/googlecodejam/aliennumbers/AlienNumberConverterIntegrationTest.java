package googlecodejam.aliennumbers;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class AlienNumberConverterIntegrationTest {
  
  private static final String BINARY = "01";
  private static final String DECIMAL = "0123456789";
  private static final String HEXADECIMAL = "0123456789abcdef";
  
  // Base Case Testing
  @Test
  void test_AlienNumberConverter_WithSameTwoLanguages_ReturnsItself() {
    String input = "666";
    AlienNumberConverter converter = new AlienNumberConverter(DECIMAL, DECIMAL);
    assertEquals(input, converter.convert(input));
  }
  
  @Test
  void test_ConverterConvertsSameLength_Consistently() {
    String language1 = "0123";
    String language2 = "~!@#";
    AlienNumberConverter converter = new AlienNumberConverter(language1, language2);
    
    assertAll(
      () -> assertEquals("#~", converter.convert("30")),
      () -> assertEquals("@~!#!", converter.convert("20131"))
    );
  }
  
  @Test
  void test_ConvertingZero_ReturnsTheZeroFromTheOtherLanguage() {
    AlienNumeralSystem language1 = new AlienNumeralSystem("012345");
    AlienNumeralSystem language2 = new AlienNumeralSystem("zero");
    AlienNumberConverter converter = new AlienNumberConverter(language1, language2);
    
    assertEquals("z", converter.convert("0"));
  }
  
  // Testing regular languages
  @Test
  void test_ConvertingFromBinaryToDecimal_WorksWithAlienLanguage() {
    AlienNumberConverter converter = new AlienNumberConverter(BINARY, DECIMAL);
    
    assertAll(
      () -> assertEquals("4", converter.convert("100")),
      () -> assertEquals("259", converter.convert("100000011"))
    );
  }
  
  @Test
  void test_ConvertingFromDecimalToBinary_WorksWithAlienLanguage() {
    AlienNumberConverter converter = new AlienNumberConverter(DECIMAL, BINARY);
    
    assertAll(
      () -> assertEquals("100", converter.convert("4")),
      () -> assertEquals("100000011", converter.convert("259"))
    );
  }
  
  @Test
  void test_ConvertingWithLeadingZeroes_WorksWithAlienLanguage() {
    AlienNumberConverter converter = new AlienNumberConverter(DECIMAL, BINARY);
    assertEquals("10000", converter.convert("00016"));
  }
  
  @Test
  void test_ConvertingWithInputNotInFromLanguage_ThrowsException() {
    AlienNumberConverter converter = new AlienNumberConverter(BINARY, HEXADECIMAL);
    assertThrows(NoLetterInAlienNumeralSystemException.class,
      () -> converter.convert("100101002001101"));
  }
  
  @Test
  void test_ConvertingWithLeadingZerosOnTwoLanguagesWithSameLength() {
    AlienNumberConverter converter = new AlienNumberConverter("0123", "abcd");
    assertEquals("bc", converter.convert("0012"));
  }
  
  @Test
  void test_ConvertingFromBinaryToHexadecimal_WorksWithAlienLanguage() {
    AlienNumberConverter converter = new AlienNumberConverter(BINARY, HEXADECIMAL);
    assertEquals("face", converter.convert("1111101011001110"));
  }
  
  @Test
  void test_ConvertingFromHexadecimalToBinary_WorksWithAlienLanguage() {
    AlienNumberConverter converter = new AlienNumberConverter(HEXADECIMAL, BINARY);
    assertEquals("1111101011001110", converter.convert("face"));
  }
  
  // Google code jam sample test cases
  @Test
  void testCase1() {
    AlienNumberConverter converter = new AlienNumberConverter(DECIMAL, "oF8");
    assertEquals("Foo", converter.convert("9"));
  }
  
  @Test
  void testCase2() {
    AlienNumberConverter converter = new AlienNumberConverter("oF8", DECIMAL);
    assertEquals("9", converter.convert("Foo"));
  }
  
  @Test
  void testCase3() {
    AlienNumberConverter converter = new AlienNumberConverter(HEXADECIMAL, BINARY);
    assertEquals("10011", converter.convert("13"));
  }
  
  @Test
  void testCase4() {
    AlienNumberConverter converter = new AlienNumberConverter("O!CDE?", "A?JM!.");
    assertEquals("JAM!", converter.convert("CODE"));
  }
}
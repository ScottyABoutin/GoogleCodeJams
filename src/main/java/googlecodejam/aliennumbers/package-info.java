/**
 * Provides the classes that solve the Google Code Jam Alien Numbers. The description of that Google
 * Code Jam is reprinted here for convenience.
 * <p>
 * <a href="https://code.google.com/codejam/contest/32003/dashboard#s=p0">Source </a>
 * <p>
 * The Code Jam is as follows:
 * <p>
 * The decimal numeral system is composed of ten digits, which we represent as "0123456789" (the
 * digits in a system are written from lowest to highest). Imagine you have discovered an alien
 * numeral system composed of some number of digits, which may or may not be the same as those used
 * in decimal. For example, if the alien numeral system were represented as "oF8", then the numbers
 * one through ten would be (F, 8, Fo, FF, F8, 8o, 8F, 88, Foo, FoF). We would like to be able to
 * work with numbers in arbitrary alien systems. More generally, we want to be able to convert an
 * arbitrary number that's written in one alien system into a second alien system.
 * <p>
 * Input
 * <p>
 * The first line of input gives the number of cases, <b>N</b>. <b>N</b> test cases follow. Each
 * case is a line formatted as
 * <p>
 * {@code alien_number source_language target_language}
 * <p>
 * Each language will be represented by a list of its digits, ordered from lowest to highest value.
 * No digit will be repeated in any representation, all digits in the alien number will be present
 * in the source language, and the first digit of the alien number will not be the lowest valued
 * digit of the source language (in other words, the alien numbers have no leading zeroes). Each
 * digit will either be a number 0-9, an uppercase or lowercase letter, or one of the following
 * symbols {@literal !"#$%&'()*+,-./:;<=>?@[\]^_`{|}~}
 * <p>
 * Output
 * <p>
 * For each test case, output one line containing "Case #<b>x</b>: " followed by the alien number
 * translated from the source language to the target language.
 * <p>
 * Limits
 * <p>
 * 1 &le; <b>N</b> &le; 100.
 * <p>
 * Small dataset
 * <p>
 * 1 &le; num digits in <b>alien_number</b> &le; 4,<br>
 * 2 &le; num digits in <b>source_language</b> &le; 16,<br>
 * 2 &le; num digits in <b>target_language</b> &le; 16.
 * <p>
 * Large dataset
 * <p>
 * 1 &le; <b>alien_number</b> (in decimal) &le; 1000000000,<br>
 * 2 &le; num digits in <b>source_language</b> &le; 94,<br>
 * 2 &le; num digits in <b>target_language</b> &le; 94.
 * <p>
 * Sample
 * <table summary="Example input and output">
 * <tr>
 * <th>Input
 * <th>Output
 * <tr>
 * <td>{@code 4}
 * <tr>
 * <td>{@code 9 0123456789 oF8}
 * <td>{@code Case #1: Foo}
 * <tr>
 * <td>{@code Foo oF8 0123456789}
 * <td>{@code Case #2: 9}
 * <tr>
 * <td>{@code 13 0123456789abcdef 01}
 * <td>{@code Case #3: 10011}
 * <tr>
 * <td>{@code CODE O!CDE? A?JM!.}
 * <td>{@code Case #4: JAM!}
 * </table>
 * 
 * @see googlecodejam.aliennumbers.AlienNumberCodeJamSolver
 */
package googlecodejam.aliennumbers;
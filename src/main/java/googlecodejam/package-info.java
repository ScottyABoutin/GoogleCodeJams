/**
 * Provides the base class and package for all Code Jams to be created. All new Code Jams should be
 * placed in their own package within this one. All solver classes should extend from the class
 * {@code GoogleCodeJamSolver}, construct themselves, and call
 * {@link googlecodejam.GoogleCodeJamSolver#go(String[])}. Unless otherwise noted, passing a null
 * argument to a constructor or method in any class or interface in this package or any subpackage
 * will cause a {@code NullPointerException} to be thrown.
 * 
 * @see googlecodejam.GoogleCodeJamSolver
 */
package googlecodejam;
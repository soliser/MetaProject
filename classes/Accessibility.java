/*
 * @(#)Accessibility.java 1.0 2010/03/24
 */

package classes;

/**
 * The <code>Accessibility</code> enum is designed to hold the enumerators
 * for Accessibility of a Class, Attribute or method.
 * <p>
 * The four Accessibility parameters specified are: <br />
 * <strong>PUBLIC</strong> for public access
 * <strong>DEFAULT</strong> for the default, non-specified access type
 * <strong>PROTECTED</strong> for protected access
 * <strong>PRIVATE</strong> for private access
 * <p>
 * These <code>Accessibility</code>s will help to identify the
 * Object-Orientedness of a program.
 * 
 * @version 1.1 2010/03/24
 * @author William Croft
 */
public enum Accessibility{
	PUBLIC,
	DEFAULT,
	PROTECTED,
	PRIVATE,
}
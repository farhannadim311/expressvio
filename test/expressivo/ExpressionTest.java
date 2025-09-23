/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package expressivo;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for the Expression abstract data type.
 */
public class ExpressionTest {

    // Testing strategy
    //   TODO
    private static Expression parse(String s) {
    	return Expression.parse(s);    
    	}
    private static void assertRoundTrips(String s) {
    	Expression e = parse(s);
    	String printed = e.toString();
    	Expression reparsed = parse(printed);
    	assertEquals("toString() must be a parsable representation (round-trip)", e, reparsed);
    }
    
    private static void assertEqualAndHash(Expression a, Expression b) {
    	assertEquals("structural eqauality expected", a , b);
    	assertEquals("hashCode must agree with equals" , a.hashCode(), b.hashCode());	
    }
    private static void assertNotEqual(Expression a, Expression b) {
    	assertNotEquals("expression should not be equal", a, b);
    }
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    @Test public void testParseInteger() {
    	Expression e = parse("0");
    	assertRoundTrips("0");
    	assertRoundTrips("17");
    	assertRoundTrips("1234567890");
    }
    
    @Test public void testParseFloat() {
    	assertRoundTrips("0.0");
    	assertRoundTrips("2.5");
    	assertRoundTrips("10.0001");
    }
    
    @Test public void testParseVariable() {
    	assertRoundTrips("x");
    	assertRoundTrips("X");
    	assertRoundTrips("abc");
    	assertRoundTrips("Var");
    }
    
    @Test public void testWhitespaceIgnored() {
    	Expression a = parse("x+2* y");
    	Expression b = parse("x + 2 * y");
    	assertEqualAndHash(a, b);
    	assertEqualAndHash(parse("( x + (2*y ) ) )"),  parse("(x+(2*y))"));
    }
    
    @Test public void testLeftAssociativity() {
    	assertEqualAndHash(parse("a+b+c"), parse("(a+b)+c"));
    	assertNotEqual(parse("a*(b+c)"), parse("a*b + c"));
    }
    
    @Test public void testStructuralNotAlgebraic() {
    	assertNotEqual(parse("x+y"), parse("y+x"));
    	assertNotEqual(parse("2+3"), parse("5"));
    	assertNotEqual(parse("2*x"), parse("x*2"));	
    }
    
    @Test public void testEqualsAndHashCodeSameTree() {
    	Expression a = parse("(x + 2) * (y + 3)");
    	Expression b = parse("(x+2)*(y+3)");
    	assertEqualAndHash(a, b);
    }
    
    @Test public void testRoundTripSimple() {
    	assertRoundTrips("x");
    	assertRoundTrips("42");
    	assertRoundTrips("3.14159");
    }
    
    @Test public void testRoundTripCompound() {
    	assertRoundTrips("x+2*y");
    	assertRoundTrips("(a+b)*c");
    	assertRoundTrips("((a+b)*(c+d))+e*f");
    	assertRoundTrips("x*x + y*y + 2*x*y");
    }
    
    @Test public void testLargeNumbers() {
        assertRoundTrips("999999999");
        assertRoundTrips("12345.6789");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidEmpty() {
        parse("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidGarbage() {
        parse("@");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidNegativeNumber() {
        // PS3 grammar: nonnegative numbers only
        parse("-1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidVariableWithDigitsOrUnderscore() {
        // variables are nonempty strings of letters (A-Za-z) only
        parse("x1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidUnderscoreVar() {
        parse("foo_bar");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidFloatFormats1() {
        parse(".5"); // reject if your grammar requires leading digit
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidFloatFormats2() {
        parse("5."); // reject if your grammar requires trailing digit
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDoubleDot() {
        parse("2..3");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidOperatorRun() {
        parse("1++2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidDanglingOperator() {
        parse("1+");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidEmptyParens() {
        parse("()");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidMismatchedParens() {
        parse("(1+2");
    }
    
    @Test public void testImmutabilityBlackBox() {
        // Black-box sanity: parsing then printing then reparsing doesn’t change value.
        Expression e1 = parse("(a+b)*c");
        String s = e1.toString();
        Expression e2 = parse(s);
        assertEqualAndHash(e1, e2);
        // If your concrete implementation later offers getters/collections, you’ll add
        // representation-exposure tests in their own test classes.
    }
    // TODO tests for Expression
    
}

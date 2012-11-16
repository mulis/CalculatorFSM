import calculator.CalculationException;
import calculator.Calculator;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;

import static java.math.BigDecimal.valueOf;
import static org.junit.Assert.assertEquals;

public class CalculatorTests {

    private static final Calculator calculator = new Calculator();

    @Test
    public void numberTest() throws CalculationException {
        assertEquals("Result of expression with integer number", valueOf(1), calculator.calculate("1"));
        assertEquals("Result of expression with decimal number", valueOf(1.1), calculator.calculate("1.1"));
        assertEquals("Result of expression with number with spaces", valueOf(1), calculator.calculate(" 1 "));
        assertEquals("Result of expression with negative number", valueOf(-1), calculator.calculate(" -1 "));
    }

    @Test
    public void additionTest() throws CalculationException {
        assertEquals("Result of expression with integer addend", valueOf(3), calculator.calculate(" 1 + 2 "));
        assertEquals("Result of expression with decimal addend", valueOf(3.5), calculator.calculate(" 1 + 2.5 "));
        assertEquals("Result of expression with negative addend", valueOf(-1), calculator.calculate(" 1 + -2 "));
        assertEquals("Result of expression with negative addend in parentheses", valueOf(-1), calculator.calculate(" 1 + (-2) "));
    }

    @Test
    public void subtractionTest() throws CalculationException {
        assertEquals("Result of expression with integer subtrahend", valueOf(-1), calculator.calculate(" 1 - 2 "));
        assertEquals("Result of expression with decimal subtrahend", valueOf(-1.5), calculator.calculate(" 1 - 2.5 "));
        assertEquals("Result of expression with not spaced integer subtrahend", valueOf(-1), calculator.calculate(" 1 -2 "));
    }

    @Test
    public void multiplicationTest() throws CalculationException {
        assertEquals("Result of expression with integer multiplier", valueOf(2), calculator.calculate(" 1 * 2 "));
        assertEquals("Result of expression with decimal multiplier", valueOf(2.5), calculator.calculate(" 1 * 2.5 "));
        assertEquals("Result of expression with integer divider", valueOf(-2), calculator.calculate(" 1 * -2 "));
    }

    @Test
    public void divisionTest() throws CalculationException {
        assertEquals("Result of expression with integer divider", valueOf(0.5), calculator.calculate(" 1 / 2 "));
        assertEquals("Result of expression with decimal divider", valueOf(0.4), calculator.calculate(" 1 / 2.5 "));
        assertEquals("Result of expression with negative divider", valueOf(-0.5), calculator.calculate(" 1 / -2 "));
    }

    @Test
    public void exponentiationTest() throws CalculationException {
        assertEquals("Result of expression with integer exponent", valueOf(16), calculator.calculate(" 4 ^ 2 "));
        assertEquals("Result of expression with decimal exponent", valueOf(2), calculator.calculate(" 4 ^ 0.5 "));
        assertEquals("Result of expression with zero exponent", valueOf(1), calculator.calculate(" 4 ^ 0 "));
        assertEquals("Result of expression with negative exponent", valueOf(0.25), calculator.calculate(" 4 ^ -1 "));
    }

    @Test
    public void operationsPriorityTest() throws CalculationException {
        assertEquals("Result of expression with operations priority", valueOf(6.2), calculator.calculate(" 1 + 2 * 3 - 4 / 5 "));
        assertEquals("Result of expression with operations priority", valueOf(-2.25), calculator.calculate(" 1 * 2 + 3 / 4 - 5 "));
    }

    @Test
    public void squareRootTest() throws CalculationException {
        assertEquals("Result of expression with square root of integer", valueOf(3), calculator.calculate("0 + sqr(9) - 0"));
        assertEquals("Result of expression with square root of decimal", valueOf(1.5), calculator.calculate("0 + sqr(2.25) - 0"));
        assertEquals("Result of expression with square root of sum", valueOf(4), calculator.calculate("0 + sqr(10 + 6) - 0"));
        assertEquals("Result of expression with square root of product", valueOf(5), calculator.calculate("0 + sqr(5 * 5) - 0"));
    }

    @Test
    public void minimumTest() throws CalculationException {
        assertEquals("Result of expression with minimum function", valueOf(-5), calculator.calculate("0 + min(1,2-3, sqr(4), sqr(2+2), (-5)) - 0"));
    }

    @Test
    public void maximumTest() throws CalculationException {
        assertEquals("Result of expression with maximum function", valueOf(2), calculator.calculate("0 + max(1,2-3, sqr(4), sqr(2+2), (-5)) - 0"));
    }

    @Test
    public void summationTest() throws CalculationException {
        assertEquals("Result of expression with summation function", valueOf(-1), calculator.calculate("0 + sum(1,2-3, sqr(4), sqr(2+2), (-5)) - 0"));
    }

    @Test
    public void piFunctionTest() throws CalculationException {
        assertEquals("Result of expression with pi function", valueOf(Math.PI), calculator.calculate("pi()"));
    }

    @Test
    public void parenthesisTest() throws CalculationException {
        assertEquals("Result of expression with parentheses", valueOf(4), calculator.calculate(" 1 + ( 2 - 3 ) + 4 "));
        assertEquals("Result of expression with parentheses", valueOf(-8), calculator.calculate(" 1 - ( 2 + 3 ) - 4 "));
        assertEquals("Result of expression with parentheses", valueOf(10), calculator.calculate(" 1 + ( 2 + 3 ) + 4 "));
        assertEquals("Result of expression with parentheses", valueOf(-2), calculator.calculate(" 1 - ( 2 - 3 ) - 4"));
        assertEquals("Result of expression with parentheses", valueOf(5), calculator.calculate(" 1 * ( 2 + 3 ) "));
        assertEquals("Result of expression with parentheses nesting", valueOf(0.6), calculator.calculate(" 1 + ( 2 * ( 3 - 4 ) / 5 ) "));
        assertEquals("Result of expression with parentheses nesting", valueOf(0.6), calculator.calculate(" ( 1 + ( 2 * 3 - 4 ) ) / 5 "));
    }

    @Test
    public void lostOperatorTest() {

        try {
            calculator.calculate(" 1 2 ");
        } catch (CalculationException exception) {
            assertEquals("Exception position in expression with lost operator", 3, exception.getPosition());
        }

        try {
            calculator.calculate(" 1 (-2) ");
        } catch (CalculationException exception) {
            assertEquals("Exception position in expression with lost operator", 3, exception.getPosition());
        }

    }

    @Test
    public void lostOperandTest() {

        try {
            calculator.calculate(" + ");
        } catch (CalculationException exception) {
            assertEquals("Exception position in expression with lost operator", 1, exception.getPosition());
        }

        try {
            calculator.calculate(" 1 + - ");
        } catch (CalculationException exception) {
            assertEquals("Exception position in expression with lost operator", 5, exception.getPosition());
        }

    }

    @Test
    public void lostParenthesisTest() {

        try {
            calculator.calculate(" 1 + (-2)) ");
        } catch (CalculationException exception) {
            assertEquals("Exception position in expression with unclosed parenthesis", 10, exception.getPosition());
        }

        try {
            calculator.calculate(" 1 + ((-2) ");
        } catch (CalculationException exception) {
            assertEquals("Exception position in expression with unclosed parenthesis", 6, exception.getPosition());
        }

    }

    @Test
    public void functionArgumentCountTest() {

        try {
            calculator.calculate(" min(1) ");
        } catch (CalculationException exception) {
            assertEquals("Exception position in expression with function with wrong argument count", 4, exception.getPosition());
        }

        try {
            calculator.calculate(" sqr(9, 16) ");
        } catch (CalculationException exception) {
            assertEquals("Exception position in expression with function with wrong argument count", 4, exception.getPosition());
        }

    }

    @Test
    public void squareRootArgumentTest() {

        try {
            calculator.calculate(" sqr(-1) ");
        } catch (CalculationException exception) {
            assertEquals("Exception position in expression with function with wrong arguments", 4, exception.getPosition());
        }

    }

    @Test
    public void mathContextTest() throws CalculationException {

        assertEquals("Result of expression calculated with mathContext", valueOf(0.67), calculator.calculate(" 2/3 ", new MathContext(2)));
        assertEquals("Result of expression calculated with mathContext", valueOf(1.7), calculator.calculate(" 5/3 ", new MathContext(2)));
        assertEquals("Result of expression calculated with mathContext", new BigDecimal(0.01, new MathContext(2)), calculator.calculate(" sqr(0.0001) ", new MathContext(2)));
        assertEquals("Result of expression calculated with mathContext", valueOf(1.0), calculator.calculate(" min(1, 1.11111, 0.99999) ", new MathContext(2)));
        assertEquals("Result of expression calculated with mathContext", valueOf(1.1), calculator.calculate(" max(1, 1.11111, 0.99999) ", new MathContext(2)));
        assertEquals("Result of expression calculated with mathContext", valueOf(3.1), calculator.calculate(" sum(1, 1.11111, 0.99999) ", new MathContext(2)));
        assertEquals("Result of expression calculated with mathContext", valueOf(2.1), calculator.calculate(" sum(1, 1.01, 0.04) ", new MathContext(2)));

    }

}

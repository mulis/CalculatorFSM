import calculator.Calculator;
import calculator.exception.CalculationException;
import calculator.exception.UnexpectedElementException;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class CalculatorTests {

    private static final Calculator calculator = new Calculator();

    //@Test
    public void errorTest() throws CalculationException {
        assertEquals(calculator.calculate("2 2"), BigDecimal.valueOf(4));
    }

    @Test
    public void numberTest() throws CalculationException {
        assertEquals(calculator.calculate("1"), BigDecimal.valueOf(1));
        assertEquals(calculator.calculate("1.1"), BigDecimal.valueOf(1.1));
        assertEquals(calculator.calculate(" 1 "), BigDecimal.valueOf(1));
        assertEquals(calculator.calculate(" -1 "), BigDecimal.valueOf(-1));
    }

    @Test
    public void arithmeticTest() throws CalculationException {
        assertEquals(BigDecimal.valueOf(3), calculator.calculate(" 1 + 2 "));
        assertEquals(BigDecimal.valueOf(-1), calculator.calculate(" 1 - 2 "));
        assertEquals(BigDecimal.valueOf(-1), calculator.calculate(" 1 -2 "));
        assertEquals(BigDecimal.valueOf(-1), calculator.calculate(" 1 + -2 "));
        assertEquals(BigDecimal.valueOf(-1), calculator.calculate(" 1 + (-2) "));
        assertEquals(BigDecimal.valueOf(2), calculator.calculate(" 1 * 2 "));
        assertEquals(BigDecimal.valueOf(0.5), calculator.calculate(" 1 / 2 "));
        assertEquals(BigDecimal.valueOf(6.2), calculator.calculate(" 1 + 2 * 3 - 4 / 5 "));
        assertEquals(BigDecimal.valueOf(-2.25), calculator.calculate(" 1 * 2 + 3 / 4 - 5 "));
        assertEquals(BigDecimal.valueOf(16), calculator.calculate(" 4 ^ 2 "));
        assertEquals(BigDecimal.valueOf(1), calculator.calculate(" 4 ^ 0 "));
        assertEquals(BigDecimal.valueOf(0.25), calculator.calculate(" 4 ^ -1 "));
        assertEquals(BigDecimal.valueOf(2), calculator.calculate(" 4 ^ 0.5 "));
    }

    @Test
    public void parenthesisTest() throws CalculationException {
        assertEquals(BigDecimal.valueOf(4), calculator.calculate(" 1 + ( 2 - 3 ) + 4 "));
        assertEquals(BigDecimal.valueOf(-8), calculator.calculate(" 1 - ( 2 + 3 ) - 4 "));
        assertEquals(BigDecimal.valueOf(10), calculator.calculate(" 1 + ( 2 + 3 ) + 4 "));
        assertEquals(BigDecimal.valueOf(-2), calculator.calculate(" 1 - ( 2 - 3 ) - 4"));
        assertEquals(BigDecimal.valueOf(5), calculator.calculate(" 1 * ( 2 + 3 ) "));
        assertEquals(BigDecimal.valueOf(0.6), calculator.calculate(" 1 + ( 2 * ( 3 - 4 ) / 5 ) "));
        assertEquals(BigDecimal.valueOf(0.6), calculator.calculate(" ( 1 + ( 2 * 3 - 4 ) ) / 5 "));
    }

    @Test
    public void exceptionTest() throws CalculationException {
        assertEquals(UnexpectedElementException.class, catchCalculatorException(" 1 2 ").getClass());
        assertEquals(UnexpectedElementException.class, catchCalculatorException(" 1 (-2) ").getClass());
        assertEquals(UnexpectedElementException.class, catchCalculatorException(" + ").getClass());
        assertEquals(UnexpectedElementException.class, catchCalculatorException(" 1 + - ").getClass());
    }

    private Exception catchCalculatorException(String expression) {
        Exception result = null;
        try {
            calculator.calculate(expression);
        } catch (Exception exception) {
            result = exception;
        }
        return result;
    }

}

package calculator;

import calculator.exception.CalculationException;

import java.math.BigDecimal;
import java.math.MathContext;

public interface IMathExpressionCalculator {

    public BigDecimal calculate(String expression) throws CalculationException;

    public BigDecimal calculate(String expression, MathContext mathContext) throws CalculationException;

}

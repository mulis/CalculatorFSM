package calculator;

import java.math.BigDecimal;
import java.math.MathContext;

public interface MathExpressionCalculator {

    public BigDecimal calculate(String expression) throws CalculationException;

    public BigDecimal calculate(String expression, MathContext mathContext) throws CalculationException;

}

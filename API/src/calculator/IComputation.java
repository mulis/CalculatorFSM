package calculator;

import java.math.BigDecimal;
import java.math.MathContext;

public interface IComputation {

    public String getSymbol();

    public int getPriority();

    public boolean checkValuesCount(int count);

    public abstract BigDecimal compute(BigDecimal[] values, MathContext mathContext);

}

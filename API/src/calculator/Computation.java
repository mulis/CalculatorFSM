package calculator;

import java.math.BigDecimal;
import java.math.MathContext;

public interface Computation {

    public String getSymbol();

    public boolean checkValuesCount(int count);

    public abstract BigDecimal compute(BigDecimal[] values, MathContext mathContext);

}

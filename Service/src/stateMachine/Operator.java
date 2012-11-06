package stateMachine;

import java.math.BigDecimal;
import java.math.MathContext;

public enum Operator {

    ADDITION("+") {
        public BigDecimal compute(BigDecimal[] values, MathContext mathContext) {
            return values[0].add(values[1], mathContext);
        }
    },
    SUBTRACTION("-") {
        public BigDecimal compute(BigDecimal[] values, MathContext mathContext) {
            return values[0].subtract(values[1], mathContext);
        }
    },
    MULTIPLICATION("*") {
        public BigDecimal compute(BigDecimal[] values, MathContext mathContext) {
            return values[0].multiply(values[1], mathContext);
        }
    },
    DIVISION("/") {
        public BigDecimal compute(BigDecimal[] values, MathContext mathContext) {
            return values[0].divide(values[1], mathContext);
        }
    };

    private final String sign;

    Operator(String sign) {
        this.sign = sign;
    }

    public String getSign() {
        return sign;
    }

    public static String getPattern() {

        StringBuilder pattern = new StringBuilder();

        for (Operator operator : Operator.values()) {
            pattern.append("\\G\\").append(operator.sign).append("|");
        }

        if (pattern.length() > 0) {
            pattern.deleteCharAt(pattern.length() - 1);
        }

        return pattern.toString();

    }

    public abstract BigDecimal compute(BigDecimal[] operands, MathContext mathContext);

}

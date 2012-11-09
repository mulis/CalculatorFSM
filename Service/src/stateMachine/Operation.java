package stateMachine;

import java.math.BigDecimal;
import java.math.MathContext;

public enum Operation {

    ADDITION("+", 2, 1) {
        public BigDecimal compute(BigDecimal[] values, MathContext mathContext) {
            return values[0].add(values[1], mathContext);
        }
    },
    SUBTRACTION("-", 2, 1) {
        public BigDecimal compute(BigDecimal[] values, MathContext mathContext) {
            return values[0].subtract(values[1], mathContext);
        }
//    },
//    MULTIPLICATION("*", 2, 2) {
//        public BigDecimal compute(BigDecimal[] values, MathContext mathContext) {
//            return values[0].multiply(values[1], mathContext);
//        }
//    },
//    DIVISION("/", 2, 2) {
//        public BigDecimal compute(BigDecimal[] values, MathContext mathContext) {
//            return values[0].divide(values[1], mathContext);
//        }
//    },
//    EXPONENTIATION("^", 2, 2) {
//        public BigDecimal compute(BigDecimal[] values, MathContext mathContext) {
//            return new BigDecimal(Math.pow(values[0].doubleValue(), values[1].doubleValue()), mathContext);
//        }
    };

    private final String symbol;
    private final int operandCount;
    private final int priority;

    Operation(String symbol, int operandCount, int priority) {
        this.symbol = symbol;
        this.operandCount = operandCount;
        this.priority = priority;
    }

    public abstract BigDecimal compute(BigDecimal[] operands, MathContext mathContext);

    public String getSymbol() {
        return symbol;
    }

    public int getOperandCount() {
        return operandCount;
    }

    public int getPriority() {
        return priority;
    }

    public static Operation getOperation(String symbol) {

        for (Operation operation : Operation.values()) {
            if (symbol.equals(operation.symbol)) return operation;
        }

        return null;

    }

    public static String getPattern() {

        StringBuilder pattern = new StringBuilder();

        for (Operation operation : Operation.values()) {
            pattern.append("\\G\\").append(operation.symbol).append("|");
        }

        if (pattern.length() > 0) {
            pattern.deleteCharAt(pattern.length() - 1);
        }

        return pattern.toString();

    }

}

package org.mulis.calculator.service.stateMachine;

import org.mulis.calculator.api.calculator.Computation;

import java.math.BigDecimal;
import java.math.MathContext;

public enum Operation implements Computation {

    ADDITION("+", 2, 1) {
        @Override
        public BigDecimal compute(BigDecimal[] values, MathContext mathContext) {
            return values[0].add(values[1], mathContext);
        }
    },
    SUBTRACTION("-", 2, 1) {
        @Override
        public BigDecimal compute(BigDecimal[] values, MathContext mathContext) {
            return values[0].subtract(values[1], mathContext);
        }
    },
    MULTIPLICATION("*", 2, 2) {
        @Override
        public BigDecimal compute(BigDecimal[] values, MathContext mathContext) {
            return values[0].multiply(values[1], mathContext);
        }
    },
    DIVISION("/", 2, 2) {
        @Override
        public BigDecimal compute(BigDecimal[] values, MathContext mathContext) {
            return values[0].divide(values[1], mathContext);
        }
    },
    EXPONENTIATION("^", 2, 2) {
        @Override
        public BigDecimal compute(BigDecimal[] values, MathContext mathContext) {
            return new BigDecimal(Math.pow(values[0].doubleValue(), values[1].doubleValue()), mathContext);
        }
    };

    private final String symbol;
    private final int operandCount;
    private final int priority;

    Operation(String symbol, int operandCount, int priority) {
        this.symbol = symbol;
        this.operandCount = operandCount;
        this.priority = priority;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public boolean checkValuesCount(int count) {
        return count == operandCount;
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
            pattern.append("\\G\\").append(operation.getSymbol()).append("|");
        }

        if (pattern.length() > 0) {
            pattern.deleteCharAt(pattern.length() - 1);
        }

        return pattern.toString();

    }

}

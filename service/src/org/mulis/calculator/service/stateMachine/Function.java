package org.mulis.calculator.service.stateMachine;

import org.mulis.calculator.api.calculator.Computation;

import java.math.BigDecimal;
import java.math.MathContext;

public enum Function implements Computation {

    SQR("sqr", 1, 1) {
        @Override
        public BigDecimal compute(BigDecimal[] values, MathContext mathContext) {
            return new BigDecimal(Math.sqrt(values[0].doubleValue()), mathContext);
        }
    },
    MIN("min", 2, Integer.MAX_VALUE) {
        @Override
        public BigDecimal compute(BigDecimal[] values, MathContext mathContext) {
            BigDecimal result = values[0];
            for (int i = 1; i < values.length; ++i) {
                result = result.min(values[i]);
            }
            return result.multiply(BigDecimal.ONE, mathContext);
        }
    },
    MAX("max", 2, Integer.MAX_VALUE) {
        @Override
        public BigDecimal compute(BigDecimal[] values, MathContext mathContext) {
            BigDecimal result = values[0];
            for (int i = 1; i < values.length; ++i) {
                result = result.max(values[i]);
            }
            return result.multiply(BigDecimal.ONE, mathContext);
        }
    },
    SUM("sum", 2, Integer.MAX_VALUE) {
        @Override
        public BigDecimal compute(BigDecimal[] values, MathContext mathContext) {
            BigDecimal result = values[0];
            for (int i = 1; i < values.length; ++i) {
                result = result.add(values[i]);
            }
            return result.multiply(BigDecimal.ONE, mathContext);
        }
    },
    PI("pi", 0, 0) {
        @Override
        public BigDecimal compute(BigDecimal[] values, MathContext mathContext) {
            return new BigDecimal(Math.PI, mathContext);
        }
    };

    private final String symbol;
    private final int minArgumentCount;
    private final int maxArgumentCount;

    Function(String symbol, int minArgumentCount, int maxArgumentCount) {
        this.symbol = symbol;
        this.minArgumentCount = minArgumentCount;
        this.maxArgumentCount = maxArgumentCount;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    public int getMinArgumentCount() {
        return minArgumentCount;
    }

    public int getMaxArgumentCount() {
        return maxArgumentCount;
    }

    @Override
    public boolean checkValuesCount(int count) {
        return count >= getMinArgumentCount() && count <= getMaxArgumentCount();
    }

    public static Function getFunction(String symbol) {

        for (Function function : Function.values()) {
            if (symbol.equals(function.symbol)) return function;
        }

        return null;

    }

    public static String getPattern() {

        StringBuilder pattern = new StringBuilder();

        for (Function function : Function.values()) {
            pattern.append("\\G").append(function.getSymbol()).append("|");
        }

        if (pattern.length() > 0) {
            pattern.deleteCharAt(pattern.length() - 1);
        }

        return pattern.toString();

    }

}

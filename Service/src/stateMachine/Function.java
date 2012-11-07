package stateMachine;

import java.math.BigDecimal;
import java.math.MathContext;

public enum Function {

    SQR("sqr", 2, 3) {
        @Override
        public BigDecimal compute(BigDecimal[] arguments, MathContext mathContext) {
            checkArgumentsCount(arguments);
            BigDecimal result = new BigDecimal(Math.sqrt(arguments[0].doubleValue()), mathContext);
            return result;
        }
    },
    MIN("min", Integer.MAX_VALUE, 3) {
        @Override
        public BigDecimal compute(BigDecimal[] arguments, MathContext mathContext) {
            checkArgumentsCount(arguments);
            BigDecimal result = arguments[0];
            for (int i = 1; i < arguments.length; ++i) {
                result = result.min(arguments[i]);
            }
            return result;
        }
    },
    MAX("max", Integer.MAX_VALUE, 3) {
        @Override
        public BigDecimal compute(BigDecimal[] arguments, MathContext mathContext) {
            checkArgumentsCount(arguments);
            BigDecimal result = arguments[0];
            for (int i = 1; i < arguments.length; ++i) {
                result = result.max(arguments[i]);
            }
            return result;
        }
    },
    SUM("sum", Integer.MAX_VALUE, 3) {
        @Override
        public BigDecimal compute(BigDecimal[] arguments, MathContext mathContext) {
            checkArgumentsCount(arguments);
            BigDecimal result = arguments[0];
            for (int i = 1; i < arguments.length; ++i) {
                result = result.add(arguments[i], mathContext);
            }
            return result;
        }
    };

    private static void checkArgumentsCount(BigDecimal[] arguments) {
        //To change body of created methods use File | Settings | File Templates.
    }

    private final String symbol;
    private final int argumentCount;
    private final int priority;

    Function(String symbol, int argumentCount, int priority) {
        this.symbol = symbol;
        this.argumentCount = argumentCount;
        this.priority = priority;
    }

    public abstract BigDecimal compute(BigDecimal[] arguments, MathContext mathContext);

    public String getSymbol() {
        return symbol;
    }

    public int getArgumentCount() {
        return argumentCount;
    }

    public int getPriority() {
        return priority;
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
            pattern.append("\\G").append(function.symbol).append("|");
        }

        if (pattern.length() > 0) {
            pattern.deleteCharAt(pattern.length() - 1);
        }

        return pattern.toString();

    }

}

package calculator;

import calculator.exception.CalculationException;
import finiteStateMachine.exception.StateMachineException;
import stateMachine.StateMachine;

import java.math.BigDecimal;
import java.math.MathContext;

public class Calculator implements IMathExpressionCalculator {

    @Override
    public BigDecimal calculate(String expression) throws CalculationException {

        return calculate(expression, MathContext.DECIMAL64);

    }

    @Override
    public BigDecimal calculate(String expression, MathContext mathContext) throws CalculationException {

        Context context = new Context(expression, mathContext);
        StateMachine machine = new StateMachine();

        try {
            machine.run(context);
        } catch (StateMachineException exception) {
            throw new CalculationException(exception.getMessage(), exception.getPosition());
        }

        return context.getValues().removeLast();

    }

}

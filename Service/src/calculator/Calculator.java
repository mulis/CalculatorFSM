package calculator;

import calculator.exception.CalculationException;
import calculator.exception.CalculationExceptionFactory;
import finiteStateMachine.exception.StateMachineException;
import stateMachine.State;
import stateMachine.StateMachine;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

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
            List<State> states = machine.run(context);
        } catch (StateMachineException exception) {
            throw CalculationExceptionFactory.makeException(exception);
        }

        BigDecimal result = context.getOperandStack().removeLast();

        return result;

    }

}

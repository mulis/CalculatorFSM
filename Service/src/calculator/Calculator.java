package calculator;

import finiteStateMachine.exception.StateMachineException;
import stateMachine.State;
import stateMachine.StateMachine;

import java.math.BigDecimal;
import java.util.List;

public class Calculator implements IMathExpressionCalculator {

    @Override
    public BigDecimal calculate(String expression) {

        Context context = new Context(expression);
        StateMachine machine = new StateMachine();

        try {

            List<State> states = machine.run(context);


        } catch (StateMachineException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {

        Calculator calculator = new Calculator();
        BigDecimal result = calculator.calculate(" 22 .1+ 2.3   ");
        System.out.println(result);

    }

}

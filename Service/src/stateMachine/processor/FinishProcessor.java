package stateMachine.processor;

import calculator.Context;
import finiteStateMachine.StateMachineException;
import stateMachine.State;

import java.math.BigDecimal;

public class FinishProcessor extends AbstractProcessor {

    @Override
    public boolean process(Context context, State state) throws StateMachineException {

        while (!context.isStorageEmpty()) {

            if (context.getParenthesisFlag()) {
                throw new StateMachineException("Not matched left parenthesis.", context.getStartPosition());
            }

            BigDecimal operand = performLastComputation(context);
            context.restore();
            context.addValue(operand);

        }

        if (!context.getComputations().isEmpty()) {

            BigDecimal operand = performLastComputation(context);
            context.addValue(operand);
        }

        return true;

    }

}

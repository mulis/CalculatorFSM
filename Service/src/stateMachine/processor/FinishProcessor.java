package stateMachine.processor;

import calculator.Context;
import finiteStateMachine.exception.ProcessingException;
import stateMachine.State;

import java.math.BigDecimal;

public class FinishProcessor extends AbstractProcessor {

    @Override
    public boolean process(Context context, State state) throws ProcessingException {

        while (!context.isStorageEmpty()) {

            if (context.getParenthesisFlag()) {
                throw new ProcessingException("Not matched left parenthesis.", context.getStartPosition());
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

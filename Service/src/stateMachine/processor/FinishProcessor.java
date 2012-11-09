package stateMachine.processor;

import calculator.Context;
import finiteStateMachine.exception.ProcessingException;
import stateMachine.State;

import java.math.BigDecimal;

public class FinishProcessor extends AbstractProcessor {

    @Override
    public boolean process(Context context, State state) throws ProcessingException {

        while (context.hasStoredStacks()) {

            if (context.getParenthesisFlag()) {
                throw new ProcessingException("Not matched left parenthesis.", context.getStartPosition());
            }

            performAllStackedOperations(context);

            BigDecimal operand = context.getOperandStack().removeLast();
            context.restore();
            context.addOperand(operand);

        }

        performAllStackedOperations(context);

        return true;

    }

}

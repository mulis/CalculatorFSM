package stateMachine.processor;

import calculator.Context;
import finiteStateMachine.exception.ProcessingException;
import stateMachine.State;

import java.math.BigDecimal;

public class ParenthesisProcessor extends AbstractProcessor {

    @Override
    public boolean process(Context context, State state) throws ProcessingException {

        if (state.equals(State.PARENTHESIS_LEFT)) {

            context.setParenthesisFlag();
            context.store();

        } else if (state.equals(State.PARENTHESIS_RIGHT)) {

            performAllStackedOperations(context);

            if (context.hasStoredStacks()) {
                BigDecimal operand = context.getOperandStack().removeLast();
                context.restore();
                context.addOperand(operand);
            } else {
                throw new ProcessingException("Not matched right parenthesis.", context.getPosition());
            }


        }

        return true;

    }

}

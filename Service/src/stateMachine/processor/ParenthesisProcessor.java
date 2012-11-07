package stateMachine.processor;

import calculator.Context;
import finiteStateMachine.exception.ProcessingException;
import stateMachine.State;

public class ParenthesisProcessor extends AbstractProcessor {

    @Override
    public boolean process(Context context, State state) throws ProcessingException {

        if (state.equals(State.PARENTHESIS_LEFT)) {

            context.storeAndClearContextStacks();

        } else if (state.equals(State.PARENTHESIS_RIGHT)) {

            performAllStackedOperations(context);

            context.wide();

        }

        return true;

    }

}

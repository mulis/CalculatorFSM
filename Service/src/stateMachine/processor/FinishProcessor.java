package stateMachine.processor;

import calculator.Context;
import finiteStateMachine.StateMachineException;
import stateMachine.State;

public class FinishProcessor extends AbstractProcessor {

    @Override
    public boolean process(Context context, State state) throws StateMachineException {

        while (!context.isStorageEmpty()) {

            if (context.getParenthesisFlag()) {
                throw new StateMachineException("Not matched left parenthesis.", context.getStartPosition());
            }

            performComputation(context);
            closeContext(context);

        }

        performComputation(context);

        return true;

    }

}

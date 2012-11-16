package stateMachine.processor;

import calculator.Context;
import finiteStateMachine.StateMachineException;
import stateMachine.State;

public class ParenthesisRightProcessor extends AbstractProcessor {

    @Override
    public boolean process(Context context, State state) throws StateMachineException {

        if (context.isStorageEmpty()) {
            throw new StateMachineException("Not matched right parenthesis.", context.getPosition());
        }

        performComputation(context);
        closeContext(context);

        if (context.getFunctionFlag()) {

            performComputation(context);
            closeContext(context);

        }

        return true;

    }

}

package stateMachine.processor;

import calculator.Context;
import finiteStateMachine.StateMachineException;
import stateMachine.State;

public class FunctionArgumentSeparatorProcessor extends AbstractProcessor {

    @Override
    public boolean process(Context context, State state) throws StateMachineException {

        performComputation(context);
        closeContext(context);

        context.store();
        context.setParenthesisFlag(true);

        return true;

    }

}

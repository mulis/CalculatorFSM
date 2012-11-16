package stateMachine.processor;

import calculator.Context;
import finiteStateMachine.StateMachineException;
import stateMachine.State;

public class StartProcessor extends AbstractProcessor {

    @Override
    public boolean process(Context context, State state) throws StateMachineException {

        return true;

    }

}
package stateMachine.processor;

import calculator.Context;
import finiteStateMachine.exception.ProcessingException;
import stateMachine.State;

public class FunctionProcessor extends AbstractProcessor {

    @Override
    public boolean process(Context context, State state) throws ProcessingException {
        return true;
    }

}

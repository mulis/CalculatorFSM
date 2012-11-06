package stateMachine;

import calculator.Context;
import finiteStateMachine.IStateProcessor;
import finiteStateMachine.exception.ProcessingException;

public class Processor implements IStateProcessor<Context, State> {

    @Override
    public void process(Context context, State state) throws ProcessingException {

    }
}

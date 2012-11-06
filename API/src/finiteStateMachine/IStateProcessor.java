package finiteStateMachine;

import finiteStateMachine.exception.ProcessingException;

public interface IStateProcessor<Context, State> {

    public void process(Context context, State state) throws ProcessingException;

}

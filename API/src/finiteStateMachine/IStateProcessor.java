package finiteStateMachine;

import finiteStateMachine.exception.ProcessingException;

public interface IStateProcessor<Context, State> {

    public boolean process(Context context, State state) throws ProcessingException;

}

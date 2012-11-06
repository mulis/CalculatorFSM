package finiteStateMachine;

import finiteStateMachine.exception.RecognitionException;

public interface IStateRecognizer<Context, State> {

    public boolean recognize(Context context, State state) throws RecognitionException;

}

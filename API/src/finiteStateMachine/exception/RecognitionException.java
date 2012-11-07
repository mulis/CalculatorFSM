package finiteStateMachine.exception;

import finiteStateMachine.ISateMachineContext;

public class RecognitionException extends StateMachineException {

    public <Context extends ISateMachineContext> RecognitionException(Context context) {
        super("Recognition failed.", context);
    }

}

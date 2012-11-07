package finiteStateMachine.exception;

import finiteStateMachine.ISateMachineContext;

public class ProcessingException extends StateMachineException {

    public <Context extends ISateMachineContext> ProcessingException(Context context) {
        super("Processing failed.", context);
    }

}

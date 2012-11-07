package finiteStateMachine.exception;

import finiteStateMachine.ISateMachineContext;

public class NextStateNotFoundException extends StateMachineException {

    public <Context extends ISateMachineContext> NextStateNotFoundException(Context context) {
        super("Next state not found.", context);
    }

}

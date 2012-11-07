package finiteStateMachine.exception;

import finiteStateMachine.ISateMachineContext;

public class StateMachineException extends Exception {

    private final int position;

    public <Context extends ISateMachineContext> StateMachineException(String message, Context context) {
        super(message + " Position: " + context.getPosition() + ".");
        position = context.getPosition();
    }

    public int getPosition() {
        return position;
    }
}

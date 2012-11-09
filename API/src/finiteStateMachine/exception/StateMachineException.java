package finiteStateMachine.exception;

public class StateMachineException extends Exception {

    private final int position;

    public StateMachineException(String message, int position) {
        super(message + " Position: " + position + ".");
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}

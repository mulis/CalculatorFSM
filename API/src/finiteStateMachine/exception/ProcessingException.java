package finiteStateMachine.exception;

public class ProcessingException extends StateMachineException {

    public ProcessingException(String message, int position) {
        super(message, position);
    }

}

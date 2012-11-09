package finiteStateMachine.exception;

public class RecognitionException extends StateMachineException {

    public RecognitionException(String message, int position) {
        super(message, position);
    }

}

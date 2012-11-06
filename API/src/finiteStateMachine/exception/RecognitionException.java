package finiteStateMachine.exception;

public class RecognitionException extends StateMachineException {

    RecognitionException() {
        super("Recognition failed.");
    }

}

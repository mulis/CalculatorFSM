package finiteStateMachine.exception;

public class ProcessingException extends StateMachineException {

    ProcessingException() {
        super("Processing failed.");
    }

}

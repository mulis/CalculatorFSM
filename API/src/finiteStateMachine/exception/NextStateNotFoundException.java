package finiteStateMachine.exception;

public class NextStateNotFoundException extends StateMachineException {

    public NextStateNotFoundException(String message, int position) {
        super(message, position);
    }

}

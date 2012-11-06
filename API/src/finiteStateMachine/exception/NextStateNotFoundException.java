package finiteStateMachine.exception;

public class NextStateNotFoundException extends StateMachineException {

    public NextStateNotFoundException() {
        super("Next state not found.");
    }

}

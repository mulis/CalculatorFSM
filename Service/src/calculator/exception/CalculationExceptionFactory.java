package calculator.exception;

import finiteStateMachine.exception.NextStateNotFoundException;
import finiteStateMachine.exception.ProcessingException;
import finiteStateMachine.exception.StateMachineException;

public class CalculationExceptionFactory {

    public static CalculationException makeException(StateMachineException exception) {

        CalculationException returnException;

        if (exception.getClass().equals(NextStateNotFoundException.class)) {
            returnException = new UnexpectedElementException(exception.getPosition());
        } else if (exception.getClass().equals(ProcessingException.class)) {
            returnException = new NotComputableOperationException(exception.getPosition());
        } else {
            returnException = new CalculationException(exception.getPosition());
        }

        return returnException;

    }

}

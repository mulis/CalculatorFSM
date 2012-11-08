package stateMachine.processor;

import calculator.Context;
import finiteStateMachine.IStateProcessor;
import finiteStateMachine.exception.ProcessingException;
import stateMachine.Operation;
import stateMachine.State;

import java.math.BigDecimal;

public abstract class AbstractProcessor implements IStateProcessor<Context, State> {

    public void checkLastProcessedState(Context context, State state) throws ProcessingException {

        if ((context.getLastProcessedState() != null) && context.getLastProcessedState().equals(state)) {
            throw new ProcessingException(context);
        }

    }

    public void performAllStackedOperations(Context context) throws ProcessingException {

        while (!context.getOperationStack().isEmpty()) {

            performLastStackedOperation(context);

        }

    }

    public void performLastStackedOperation(Context context) throws ProcessingException {

        Operation operation = context.getOperationStack().removeLast();

        if (operation.getOperandCount() > context.getOperandStack().size()) {
            throw new ProcessingException(context);
        }

        BigDecimal[] values = new BigDecimal[operation.getOperandCount()];

        for (int i = values.length - 1; i > -1; --i) {
            values[i] = context.getOperandStack().removeLast();
        }

        context.getOperandStack().addLast(operation.compute(values, context.getMathContext()));

    }

}

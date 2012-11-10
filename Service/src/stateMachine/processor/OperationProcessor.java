package stateMachine.processor;

import calculator.Context;
import calculator.IComputation;
import finiteStateMachine.exception.ProcessingException;
import stateMachine.Operation;
import stateMachine.State;

import java.math.BigDecimal;

public class OperationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Context context, State state) throws ProcessingException {

        String symbol = state.getRecognizer().getMatcher().group();
        Operation operation = Operation.getOperation(symbol);
        IComputation lastComputation = context.getLastComputation();

        if (lastComputation == null) {

            context.addComputation(operation);

        } else {

            if (lastComputation.getPriority() >= operation.getPriority()) {

                BigDecimal operand = performLastComputation(context);
                context.addValue(operand);
                context.addComputation(operation);

            } else {

                BigDecimal operand = context.getValues().removeLast();
                context.store();
                context.addValue(operand);
                context.addComputation(operation);

            }

        }

        return true;

    }

}

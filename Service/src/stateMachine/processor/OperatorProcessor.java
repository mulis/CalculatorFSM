package stateMachine.processor;

import calculator.Context;
import finiteStateMachine.exception.ProcessingException;
import stateMachine.Operation;
import stateMachine.State;

import java.math.BigDecimal;

public class OperatorProcessor extends AbstractProcessor {

    @Override
    public boolean process(Context context, State state) throws ProcessingException {

        String operator = state.getRecognizer().getMatcher().group();
        Operation operation = Operation.getOperation(operator);
        Operation lastOperation = context.getLastOperation();

        if (lastOperation == null) {

            context.addOperation(operation);

        } else {

            if (lastOperation.getPriority() < operation.getPriority()) {

                BigDecimal operand = context.getOperandStack().removeLast();
                context.store();
                context.addOperand(operand);
                context.addOperation(operation);

            } else if (lastOperation.getPriority() >= operation.getPriority()) {

//                while ((context.getOperationStack().size() > 0) &&
//                        (context.getLastOperation().getPriority() >= operation.getPriority())) {
//
//                    performLastStackedOperation(context);
//
//                }

                performAllStackedOperations(context);

                context.addOperation(operation);

            }

        }

        return true;

    }

}

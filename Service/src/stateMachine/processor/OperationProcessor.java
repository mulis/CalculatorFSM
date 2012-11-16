package stateMachine.processor;

import calculator.Context;
import finiteStateMachine.StateMachineException;
import stateMachine.Operation;
import stateMachine.State;

public class OperationProcessor extends AbstractProcessor {

    @Override
    public boolean process(Context context, State state) throws StateMachineException {

        String symbol = state.getRecognizer().getMatcher().group();
        Operation operation = Operation.getOperation(symbol);

        if (!context.getComputations().isEmpty()) {

            if (operation.getPriority() > ((Operation) context.peekLastComputation()).getPriority()) {

                openContext(context);

            } else {

                performComputation(context);

            }

        }

        context.addComputation(operation);

        return true;

    }

}

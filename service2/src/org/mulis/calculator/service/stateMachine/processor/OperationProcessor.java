package org.mulis.calculator.service.stateMachine.processor;

import org.mulis.calculator.api.finiteStateMachine.StateMachineException;
import org.mulis.calculator.service.calculator.Context;
import org.mulis.calculator.service.stateMachine.Operation;
import org.mulis.calculator.service.stateMachine.State;

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

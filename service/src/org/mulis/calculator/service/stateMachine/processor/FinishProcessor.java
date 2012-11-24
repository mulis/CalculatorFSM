package org.mulis.calculator.service.stateMachine.processor;

import org.mulis.calculator.api.finiteStateMachine.StateMachineException;
import org.mulis.calculator.service.calculator.Context;
import org.mulis.calculator.service.stateMachine.State;

public class FinishProcessor extends AbstractProcessor {

    @Override
    public boolean process(Context context, State state) throws StateMachineException {

        while (!context.isStorageEmpty()) {

            if (context.getParenthesisFlag()) {
                throw new StateMachineException("Not matched left parenthesis.", context.getStartPosition());
            }

            performComputation(context);
            closeContext(context);

        }

        performComputation(context);

        return true;

    }

}

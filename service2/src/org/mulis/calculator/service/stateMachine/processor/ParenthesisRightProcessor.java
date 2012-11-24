package org.mulis.calculator.service.stateMachine.processor;

import org.mulis.calculator.api.finiteStateMachine.StateMachineException;
import org.mulis.calculator.service.calculator.Context;
import org.mulis.calculator.service.stateMachine.State;

public class ParenthesisRightProcessor extends AbstractProcessor {

    @Override
    public boolean process(Context context, State state) throws StateMachineException {

        if (context.isStorageEmpty()) {
            throw new StateMachineException("Not matched right parenthesis.", context.getPosition());
        }

        performComputation(context);
        closeContext(context);

        if (context.getFunctionFlag()) {

            performComputation(context);
            closeContext(context);

        }

        return true;

    }

}

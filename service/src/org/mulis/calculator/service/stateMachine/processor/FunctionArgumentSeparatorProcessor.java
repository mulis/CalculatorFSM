package org.mulis.calculator.service.stateMachine.processor;

import org.mulis.calculator.api.finiteStateMachine.StateMachineException;
import org.mulis.calculator.service.calculator.Context;
import org.mulis.calculator.service.stateMachine.State;

public class FunctionArgumentSeparatorProcessor extends AbstractProcessor {

    @Override
    public boolean process(Context context, State state) throws StateMachineException {

        performComputation(context);
        closeContext(context);

        context.store();
        context.setParenthesisFlag(true);

        return true;

    }

}

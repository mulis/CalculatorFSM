package org.mulis.calculator.service.stateMachine.processor;

import org.mulis.calculator.api.finiteStateMachine.StateMachineException;
import org.mulis.calculator.service.calculator.Context;
import org.mulis.calculator.service.stateMachine.Function;
import org.mulis.calculator.service.stateMachine.State;

public class FunctionProcessor extends AbstractProcessor {

    @Override
    public boolean process(Context context, State state) throws StateMachineException {

        String symbol = state.getRecognizer().getMatcher().group();
        Function function = Function.getFunction(symbol);

        context.store();
        context.addComputation(function);
        context.setFunctionFlag(true);

        return true;

    }

}

package org.mulis.calculator.service.stateMachine.processor;

import org.mulis.calculator.api.finiteStateMachine.StateMachineException;
import org.mulis.calculator.service.calculator.Context;
import org.mulis.calculator.service.stateMachine.State;

public class StartProcessor extends AbstractProcessor {

    @Override
    public boolean process(Context context, State state) throws StateMachineException {

        return true;

    }

}

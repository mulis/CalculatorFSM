package org.mulis.calculator.service.stateMachine.processor;

import org.mulis.calculator.api.finiteStateMachine.StateMachineException;
import org.mulis.calculator.service.calculator.Context;
import org.mulis.calculator.service.stateMachine.State;

import java.math.BigDecimal;

public class NumberProcessor extends AbstractProcessor {

    @Override
    public boolean process(Context context, State state) throws StateMachineException {

        String number = state.getRecognizer().getMatcher().group();
        context.addValue(new BigDecimal(number));

        return true;

    }

}

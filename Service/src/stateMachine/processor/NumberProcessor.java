package stateMachine.processor;

import calculator.Context;
import finiteStateMachine.StateMachineException;
import stateMachine.State;

import java.math.BigDecimal;

public class NumberProcessor extends AbstractProcessor {

    @Override
    public boolean process(Context context, State state) throws StateMachineException {

        String number = state.getRecognizer().getMatcher().group();
        context.addValue(new BigDecimal(number));

        return true;

    }

}

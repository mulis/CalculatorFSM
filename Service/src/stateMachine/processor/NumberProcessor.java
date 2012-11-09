package stateMachine.processor;

import calculator.Context;
import finiteStateMachine.exception.ProcessingException;
import stateMachine.State;

import java.math.BigDecimal;

public class NumberProcessor extends AbstractProcessor {

    @Override
    public boolean process(Context context, State state) throws ProcessingException {

        String number = state.getRecognizer().getMatcher().group();
        context.addOperand(new BigDecimal(number));

        return true;

    }

}

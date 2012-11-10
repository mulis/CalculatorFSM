package stateMachine.processor;

import calculator.Context;
import finiteStateMachine.exception.ProcessingException;
import stateMachine.State;

import java.math.BigDecimal;

public class FunctionArgumentSeparatorProcessor extends AbstractProcessor {

    @Override
    public boolean process(Context context, State state) throws ProcessingException {

        if (context.getComputations().isEmpty()) {

            BigDecimal value = context.getValues().removeLast();
            context.restore();
            context.addValue(value);

        } else {

            BigDecimal value = performLastComputation(context);
            context.restore();
            context.addValue(value);

        }

        context.store();
        context.setParenthesisFlag();

        return true;

    }

}

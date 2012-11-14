package stateMachine.processor;

import calculator.Computation;
import calculator.Context;
import finiteStateMachine.StateMachineException;
import finiteStateMachine.StateProcessor;
import stateMachine.State;

import java.math.BigDecimal;

public abstract class AbstractProcessor implements StateProcessor<Context, State> {

    protected BigDecimal performLastComputation(Context context) throws StateMachineException {

        Computation computation = context.getComputations().removeLast();

        if (!computation.checkValuesCount(context.getValues().size())) {
            throw new StateMachineException("Value count not acceptable.", context.getStartPosition());
        }

        BigDecimal[] values = getValuesArray(context);
        BigDecimal result;

        try {
            result = computation.compute(values, context.getMathContext());
        } catch (Exception exception) {
            throw new StateMachineException("Values not acceptable.", context.getStartPosition());
        }

        return result;

    }

    protected BigDecimal[] getValuesArray(Context context) {

        BigDecimal[] values = new BigDecimal[context.getValues().size()];

        for (int i = values.length - 1; i > -1; --i) {
            values[i] = context.getValues().removeLast();
        }

        return values;

    }

}

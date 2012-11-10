package stateMachine.processor;

import calculator.Context;
import calculator.IComputation;
import finiteStateMachine.IStateProcessor;
import finiteStateMachine.exception.ProcessingException;
import stateMachine.State;

import java.math.BigDecimal;

public abstract class AbstractProcessor implements IStateProcessor<Context, State> {

    public BigDecimal performLastComputation(Context context) throws ProcessingException {

        IComputation computation = context.getComputations().removeLast();

        if (!computation.checkValuesCount(context.getValues().size())) {
            throw new ProcessingException("Value count not acceptable.", context.getStartPosition());
        }

        BigDecimal[] values = getValuesArray(context);

        return computation.compute(values, context.getMathContext());

    }

    public BigDecimal[] getValuesArray(Context context) {

        BigDecimal[] values = new BigDecimal[context.getValues().size()];

        for (int i = values.length - 1; i > -1; --i) {
            values[i] = context.getValues().removeLast();
        }

        return values;

    }

}

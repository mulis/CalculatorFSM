package org.mulis.calculator.service.stateMachine.processor;

import org.mulis.calculator.api.calculator.Computation;
import org.mulis.calculator.api.finiteStateMachine.StateMachineException;
import org.mulis.calculator.api.finiteStateMachine.StateProcessor;
import org.mulis.calculator.service.calculator.Context;
import org.mulis.calculator.service.stateMachine.State;

import java.math.BigDecimal;

public abstract class AbstractProcessor implements StateProcessor<Context, State> {

    protected void openContext(Context context) throws StateMachineException {

        BigDecimal value = context.getValues().removeLast();
        context.store();
        context.addValue(value);

    }

    protected void closeContext(Context context) throws StateMachineException {

        BigDecimal[] values = getValuesArray(context);
        context.restore();
        for (BigDecimal value : values) {
            context.addValue(value);
        }

    }

    protected void performComputation(Context context) throws StateMachineException {

        if (!context.getComputations().isEmpty()) {

            BigDecimal value = performLastComputation(context);
            context.addValue(value);

        }

    }

    private BigDecimal performLastComputation(Context context) throws StateMachineException {

        Computation computation = context.getComputations().removeLast();

        if (!computation.checkValuesCount(context.getValues().size())) {
            throw new StateMachineException("Values count not acceptable.", context.getStartPosition());
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

    private BigDecimal[] getValuesArray(Context context) {

        BigDecimal[] values = new BigDecimal[context.getValues().size()];

        for (int i = values.length - 1; i > -1; --i) {
            values[i] = context.getValues().removeLast();
        }

        return values;

    }

}

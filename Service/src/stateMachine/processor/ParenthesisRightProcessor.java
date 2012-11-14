package stateMachine.processor;

import calculator.Context;
import finiteStateMachine.StateMachineException;
import stateMachine.State;

import java.math.BigDecimal;

public class ParenthesisRightProcessor extends AbstractProcessor {

    @Override
    public boolean process(Context context, State state) throws StateMachineException {

        if (context.isStorageEmpty()) {
            throw new StateMachineException("Not matched right parenthesis.", context.getPosition());
        }

        if (context.getComputations().isEmpty()) {

            BigDecimal[] values = getValuesArray(context);
            context.restore();
            for (BigDecimal value : values) {
                context.addValue(value);
            }

        } else {

            BigDecimal value = performLastComputation(context);
            context.restore();
            context.addValue(value);

        }

        if (context.getFunctionFlag()) {

            BigDecimal value = performLastComputation(context);
            context.restore();
            context.addValue(value);

        }

        return true;

    }

}

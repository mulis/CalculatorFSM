package stateMachine.processor;

import calculator.Context;
import finiteStateMachine.exception.ProcessingException;
import stateMachine.State;

import java.math.BigDecimal;

public class ParenthesisProcessor extends AbstractProcessor {

    @Override
    public boolean process(Context context, State state) throws ProcessingException {

        if (state.equals(State.PARENTHESIS_LEFT)) {

            context.store();
            context.setParenthesisFlag();

        } else if (state.equals(State.PARENTHESIS_RIGHT)) {

            if (context.isStorageEmpty()) {
                throw new ProcessingException("Not matched right parenthesis.", context.getPosition());
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

        }

        return true;

    }

}

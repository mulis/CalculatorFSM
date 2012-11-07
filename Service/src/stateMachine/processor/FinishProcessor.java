package stateMachine.processor;

import calculator.Context;
import finiteStateMachine.exception.ProcessingException;
import stateMachine.State;

public class FinishProcessor extends AbstractProcessor {

    @Override
    public boolean process(Context context, State state) throws ProcessingException {

        while (context.hasStoredStacks()) {

            performAllStackedOperations(context);

            context.wide();

        }

        performAllStackedOperations(context);

        return true;

    }

}

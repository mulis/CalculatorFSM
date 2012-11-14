package stateMachine.processor;

import calculator.Context;
import finiteStateMachine.StateMachineException;
import stateMachine.Function;
import stateMachine.State;

public class FunctionProcessor extends AbstractProcessor {

    @Override
    public boolean process(Context context, State state) throws StateMachineException {

        String symbol = state.getRecognizer().getMatcher().group();
        Function function = Function.getFunction(symbol);

        context.store();
        context.addComputation(function);
        context.setFunctionFlag(true);

        return true;

    }

}

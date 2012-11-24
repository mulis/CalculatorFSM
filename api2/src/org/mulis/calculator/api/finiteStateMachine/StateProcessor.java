package org.mulis.calculator.api.finiteStateMachine;

public interface StateProcessor<Context, State> {

    public boolean process(Context context, State state) throws StateMachineException;

}

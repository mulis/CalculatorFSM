package org.mulis.calculator.api.finiteStateMachine;

public interface StateRecognizer<Context, State> {

    public boolean recognize(Context context, State state) throws StateMachineException;

}

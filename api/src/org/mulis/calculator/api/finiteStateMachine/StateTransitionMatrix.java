package org.mulis.calculator.api.finiteStateMachine;

import java.util.Set;

public interface StateTransitionMatrix<State> {

    public State getStartState();

    public State getFinishState();

    public Set<State> getNextStates(State state);

}

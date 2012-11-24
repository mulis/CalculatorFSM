package org.mulis.calculator.api.finiteStateMachine;

import java.util.List;

public interface SateMachineContext<State> {

    public String getExpression();

    public int getPosition();

    void setPosition(int position);

    public List<State> getPassedStates();

    void addPassedState(State state);

    public State getLastRecognizedState();

    void setLastRecognizedState(State state);

    public State getLastProcessedState();

    void setLastProcessedState(State state);

}

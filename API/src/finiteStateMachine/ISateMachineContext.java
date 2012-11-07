package finiteStateMachine;

import java.util.List;

public interface ISateMachineContext<State> {

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

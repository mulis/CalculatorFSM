package finiteStateMachine;

import java.util.List;

public interface ISateMachineContext<State> {

    public String getExpression();

    public int getPosition();

    public void setPosition(int position);

    public List<State> getStates();

    public void addState(State state);

    public State getCurrentState();


}

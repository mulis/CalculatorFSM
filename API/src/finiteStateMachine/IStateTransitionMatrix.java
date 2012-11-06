package finiteStateMachine;

import java.util.Set;

public interface IStateTransitionMatrix<State> {

    public State getStartState();

    public State getFinishState();

    public Set<State> getNextStates(State state);

}

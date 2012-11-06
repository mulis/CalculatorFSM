package stateMachine;

import finiteStateMachine.IStateTransitionMatrix;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static stateMachine.State.*;

public class TransitionMatrix implements IStateTransitionMatrix<State> {

    private static final Map<State, Set<State>> TRANSITIONS = new HashMap<State, Set<State>>() {{
        put(null, EnumSet.of(START));
        put(START, EnumSet.of(SPACE, NUMBER));
        put(SPACE, EnumSet.of(NUMBER, OPERATOR, FINISH));
        put(NUMBER, EnumSet.of(SPACE, OPERATOR, FINISH));
        put(OPERATOR, EnumSet.of(SPACE, NUMBER, FINISH));
        put(FINISH, EnumSet.noneOf(State.class));
    }};

    @Override
    public State getStartState() {
        return START;
    }

    @Override
    public State getFinishState() {
        return FINISH;
    }

    @Override
    public Set<State> getNextStates(State state) {
        return TRANSITIONS.get(state);
    }

}

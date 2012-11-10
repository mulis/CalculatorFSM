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
        put(START, EnumSet.of(BLANK, NUMBER, FUNCTION, PARENTHESIS_LEFT));
        put(BLANK, EnumSet.range(NUMBER, FINISH));
        put(NUMBER, EnumSet.of(BLANK, OPERATOR, PARENTHESIS_RIGHT, FUNCTION_ARGUMENT_SEPARATOR, FINISH));
        put(OPERATOR, EnumSet.of(BLANK, NUMBER, FUNCTION, PARENTHESIS_LEFT));
        put(FUNCTION, EnumSet.of(PARENTHESIS_LEFT));
        put(FUNCTION_ARGUMENT_SEPARATOR, EnumSet.of(BLANK, NUMBER, FUNCTION, PARENTHESIS_LEFT));
        put(PARENTHESIS_LEFT, EnumSet.of(BLANK, NUMBER, FUNCTION, PARENTHESIS_LEFT, PARENTHESIS_RIGHT));
        put(PARENTHESIS_RIGHT, EnumSet.of(BLANK, OPERATOR, PARENTHESIS_RIGHT, FUNCTION_ARGUMENT_SEPARATOR, FINISH));
        put(FINISH, EnumSet.noneOf(State.class));
    }};

    @Override
    public State getStartState() {
        return START;
    }

    @Override
    public State getBlankState() {
        return BLANK;
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

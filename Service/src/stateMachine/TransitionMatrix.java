package stateMachine;

import finiteStateMachine.StateTransitionMatrix;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.util.EnumSet.noneOf;
import static java.util.EnumSet.of;
import static stateMachine.State.*;

public class TransitionMatrix implements StateTransitionMatrix<State> {

    private static final Map<State, Set<State>> TRANSITIONS = new HashMap<State, Set<State>>() {{
        put(null, of(START));
        put(START, of(BLANK, NUMBER, FUNCTION, PARENTHESIS_LEFT));
        put(BLANK, of(NUMBER, OPERATOR, FUNCTION, FUNCTION_ARGUMENT_SEPARATOR, PARENTHESIS_LEFT, PARENTHESIS_RIGHT, FINISH));
        put(NUMBER, of(BLANK, OPERATOR, PARENTHESIS_RIGHT, FUNCTION_ARGUMENT_SEPARATOR, FINISH));
        put(OPERATOR, of(BLANK, NUMBER, FUNCTION, PARENTHESIS_LEFT));
        put(FUNCTION, of(PARENTHESIS_LEFT));
        put(FUNCTION_ARGUMENT_SEPARATOR, of(BLANK, NUMBER, FUNCTION, PARENTHESIS_LEFT));
        put(PARENTHESIS_LEFT, of(BLANK, NUMBER, FUNCTION, PARENTHESIS_LEFT, PARENTHESIS_RIGHT));
        put(PARENTHESIS_RIGHT, of(BLANK, OPERATOR, PARENTHESIS_RIGHT, FUNCTION_ARGUMENT_SEPARATOR, FINISH));
        put(FINISH, noneOf(State.class));
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

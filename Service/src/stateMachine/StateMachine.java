package stateMachine;

import calculator.Context;
import finiteStateMachine.AbstractFiniteStateMachine;
import finiteStateMachine.StateProcessor;
import finiteStateMachine.StateRecognizer;
import finiteStateMachine.StateTransitionMatrix;

public class StateMachine extends AbstractFiniteStateMachine<Context, State> {

    private static final StateTransitionMatrix<State> TRANSITION_MATRIX = new TransitionMatrix();

    @Override
    public StateTransitionMatrix<State> getTransitionMatrix() {
        return TRANSITION_MATRIX;
    }

    @Override
    public StateRecognizer<Context, State> getRecognizer(State state) {
        return state.getRecognizer();
    }

    @Override
    public StateProcessor<Context, State> getProcessor(State state) {
        return state.getProcessor();
    }

}

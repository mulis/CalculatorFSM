package stateMachine;

import calculator.Context;
import finiteStateMachine.AbstractFiniteStateMachine;
import finiteStateMachine.IStateProcessor;
import finiteStateMachine.IStateRecognizer;
import finiteStateMachine.IStateTransitionMatrix;

public class StateMachine extends AbstractFiniteStateMachine<Context, State> {

    private static final IStateTransitionMatrix TRANSITION_MATRIX = new TransitionMatrix();

    @Override
    public IStateTransitionMatrix getTransitionMatrix() {
        return TRANSITION_MATRIX;
    }

    @Override
    public IStateRecognizer getRecognizer(State state) {
        return state.getRecognizer();
    }

    @Override
    public IStateProcessor<Context, State> getProcessor(State state) {
        return state.getProcessor();
    }

}

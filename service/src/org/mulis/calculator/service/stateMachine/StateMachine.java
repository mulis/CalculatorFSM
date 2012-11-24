package org.mulis.calculator.service.stateMachine;

import org.mulis.calculator.api.finiteStateMachine.AbstractFiniteStateMachine;
import org.mulis.calculator.api.finiteStateMachine.StateProcessor;
import org.mulis.calculator.api.finiteStateMachine.StateRecognizer;
import org.mulis.calculator.api.finiteStateMachine.StateTransitionMatrix;
import org.mulis.calculator.service.calculator.Context;

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

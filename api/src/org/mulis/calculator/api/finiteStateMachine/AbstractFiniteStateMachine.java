package org.mulis.calculator.api.finiteStateMachine;

import java.util.List;

public abstract class AbstractFiniteStateMachine<Context extends SateMachineContext<State>, State> {

    public List<State> run(Context context) throws StateMachineException {

        State state = getTransitionMatrix().getStartState();

        while (state != getTransitionMatrix().getFinishState()) {
            state = nextState(context, state);
        }

        return context.getPassedStates();

    }

    public State nextState(Context context, State state) throws StateMachineException {

        for (State possibleState : getTransitionMatrix().getNextStates(state)) {

            if (getRecognizer(possibleState).recognize(context, possibleState)) {

                context.setLastRecognizedState(possibleState);

                if (getProcessor(possibleState).process(context, possibleState)) {

                    context.setLastProcessedState(possibleState);
                    context.addPassedState(possibleState);

                    return possibleState;

                }

            }

        }

        throw new StateMachineException("Unexpected element.", context.getPosition());

    }

    public abstract StateTransitionMatrix<State> getTransitionMatrix();

    public abstract StateRecognizer<Context, State> getRecognizer(State state);

    public abstract StateProcessor<Context, State> getProcessor(State state);

}

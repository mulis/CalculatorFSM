package finiteStateMachine;

import finiteStateMachine.exception.NextStateNotFoundException;
import finiteStateMachine.exception.StateMachineException;

import java.util.List;

public abstract class AbstractFiniteStateMachine<Context extends ISateMachineContext<State>, State> {

    public List<State> run(Context context) throws StateMachineException {

        State state = reset(context);

        while (state != getTransitionMatrix().getFinishState()) {
            context.addState(state);
            getProcessor(state).process(context, state);
            state = nextState(context, state);
        }

        return context.getStates();

    }

    public State reset(Context context) throws StateMachineException {
        context.setPosition(0);
        return nextState(context, null);
    }

    public State nextState(Context context, State state) throws StateMachineException {

        for (State nextState : getTransitionMatrix().getNextStates(state)) {

            if (getRecognizer(nextState).recognize(context, nextState)) {
                return nextState;
            }

        }

        throw new NextStateNotFoundException();

    }

    public abstract IStateTransitionMatrix<State> getTransitionMatrix();

    public abstract IStateRecognizer<Context, State> getRecognizer(State state);

    public abstract IStateProcessor<Context, State> getProcessor(State state);

}

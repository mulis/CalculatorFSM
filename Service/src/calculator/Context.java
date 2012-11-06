package calculator;

import finiteStateMachine.ISateMachineContext;
import stateMachine.Operator;
import stateMachine.State;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Context implements ISateMachineContext<State> {

    private final String expression;

    private int position;
    private List<State> states;

    private final Deque<BigDecimal> operandStack = new ArrayDeque<BigDecimal>();
    private final Deque<Operator> operatorStack = new ArrayDeque<Operator>();

    private final Context parentContext = null;

    public Context(String expression) {
        this.expression = expression;
        this.states = new ArrayList<State>();
        setPosition(0);
    }

    private Context(Context parentContext) {
        this.expression = parentContext.expression;
        this.states = parentContext.states;
        setPosition(parentContext.getPosition());
    }

    @Override
    public String getExpression() {
        return expression;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public List<State> getStates() {
        return states;
    }

    @Override
    public void addState(State state) {
        states.add(state);
    }

    @Override
    public State getCurrentState() {
        return states.get(states.size() - 1);
    }

    public Context makeChildContext(Context parentContext) {
        return new Context(parentContext);
    }

}

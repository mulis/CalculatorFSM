package calculator;

import finiteStateMachine.SateMachineContext;
import stateMachine.State;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Context implements SateMachineContext<State> {

    private final String expression;
    private final MathContext mathContext;

    private int position;
    private List<State> states;

    private State lastRecognizedState;
    private State lastProcessedState;

    private final Deque<BigDecimal> values = new ArrayDeque<BigDecimal>();
    private final Deque<Computation> computations = new ArrayDeque<Computation>();
    private Boolean parenthesisFlag = false;
    private Boolean functionFlag = false;
    private int startPosition;

    private final Deque<Deque<BigDecimal>> valuesStorage = new ArrayDeque<Deque<BigDecimal>>();
    private final Deque<Deque<Computation>> computationsStorage = new ArrayDeque<Deque<Computation>>();
    private final Deque<Boolean> parenthesisFlagStorage = new ArrayDeque<Boolean>();
    private final Deque<Boolean> functionFlagStorage = new ArrayDeque<Boolean>();
    private final Deque<Integer> startPositionStorage = new ArrayDeque<Integer>();

    public Context(String expression) {
        this(expression, MathContext.DECIMAL64);
    }

    public Context(String expression, MathContext mathContext) {
        this.expression = expression;
        this.mathContext = mathContext;
        this.states = new ArrayList<State>();
        this.position = 0;
        this.startPosition = 0;
    }

    @Override
    public String getExpression() {
        return expression;
    }

    public MathContext getMathContext() {
        return mathContext;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public void setPosition(int position) {
        this.position = position;
    }

    public int getStartPosition() {
        return startPosition;
    }

    @Override
    public List<State> getPassedStates() {
        return states;
    }

    @Override
    public void addPassedState(State state) {
        states.add(state);
    }

    @Override
    public State getLastRecognizedState() {
        return lastRecognizedState;
    }

    @Override
    public void setLastRecognizedState(State state) {
        lastRecognizedState = state;
    }

    @Override
    public State getLastProcessedState() {
        return lastProcessedState;
    }

    @Override
    public void setLastProcessedState(State state) {
        lastProcessedState = state;
    }

    public void addValue(BigDecimal value) {
        values.addLast(value);
    }

    public Deque<BigDecimal> getValues() {
        return values;
    }

    public void addComputation(Computation computation) {
        computations.add(computation);
    }

    public Deque<Computation> getComputations() {
        return computations;
    }

    public Computation peekLastComputation() {
        return computations.peek();
    }

    public void store() {
        valuesStorage.addLast(new ArrayDeque<BigDecimal>(values));
        values.clear();
        computationsStorage.addLast(new ArrayDeque<Computation>(computations));
        computations.clear();
        parenthesisFlagStorage.addLast(parenthesisFlag);
        parenthesisFlag = false;
        functionFlagStorage.addLast(functionFlag);
        functionFlag = false;
        startPositionStorage.addLast(startPosition);
        startPosition = position;
    }

    public void restore() {
        values.clear();
        values.addAll(valuesStorage.removeLast());
        computations.clear();
        computations.addAll(computationsStorage.removeLast());
        parenthesisFlag = parenthesisFlagStorage.removeLast();
        functionFlag = functionFlagStorage.removeLast();
        startPosition = startPositionStorage.removeLast();
    }

    public boolean isStorageEmpty() {
        return valuesStorage.isEmpty() &&
                computationsStorage.isEmpty() &&
                parenthesisFlagStorage.isEmpty() &&
                functionFlagStorage.isEmpty() &&
                startPositionStorage.isEmpty();
    }

    public Boolean getParenthesisFlag() {
        return parenthesisFlag;
    }

    public void setParenthesisFlag(boolean flag) {
        parenthesisFlag = flag;
    }

    public Boolean getFunctionFlag() {
        return functionFlag;
    }

    public void setFunctionFlag(boolean flag) {
        functionFlag = flag;
    }

}

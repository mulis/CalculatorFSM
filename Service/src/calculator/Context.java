package calculator;

import finiteStateMachine.ISateMachineContext;
import stateMachine.State;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Context implements ISateMachineContext<State> {

    private final String expression;
    private final MathContext mathContext;

    private int position;
    private List<State> states;

    private State lastRecognizedState;
    private State lastProcessedState;

    private final Deque<BigDecimal> values = new ArrayDeque<BigDecimal>();
    private final Deque<IComputation> computations = new ArrayDeque<IComputation>();
    private Boolean parenthesisFlag = false;
    private Boolean functionFlag = false;
    private int startPosition;

    private final Deque<Deque> valuesStorage = new ArrayDeque<Deque>();
    private final Deque<Deque> computationsStorage = new ArrayDeque<Deque>();
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

    public void addValue(BigDecimal operand) {
        values.addLast(operand);
    }

    public Deque<BigDecimal> getValues() {
        return values;
    }

    public void addComputation(IComputation computation) {
        computations.add(computation);
    }

    public Deque<IComputation> getComputations() {
        return computations;
    }

    public IComputation getLastComputation() {
        return computations.peek();
    }

    public void store() {
        valuesStorage.addLast(new ArrayDeque<BigDecimal>(values));
        values.clear();
        computationsStorage.addLast(new ArrayDeque<IComputation>(computations));
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

    public void setParenthesisFlag() {
        parenthesisFlag = true;
    }

    public void resetParenthesisFlag() {
        parenthesisFlag = false;
    }

    public Boolean getFunctionFlag() {
        return functionFlag;
    }

    public void setFunctionFlag() {
        functionFlag = true;
    }

    public void resetFunctionFlag() {
        functionFlag = false;
    }

}

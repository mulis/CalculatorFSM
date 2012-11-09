package calculator;

import finiteStateMachine.ISateMachineContext;
import stateMachine.Operation;
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

    private State lastRecognized;
    private State lastProcessed;

    private final Deque<BigDecimal> operandStack = new ArrayDeque<BigDecimal>();
    private final Deque<Operation> operationStack = new ArrayDeque<Operation>();
    private Boolean parenthesisFlag = false;
    private int startPosition;

    private final Deque<Deque> operandStackStorage = new ArrayDeque<Deque>();
    private final Deque<Deque> operationStackStorage = new ArrayDeque<Deque>();
    private final Deque<Boolean> parenthesisFlagStorage = new ArrayDeque<Boolean>();
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
        return lastRecognized;
    }

    @Override
    public void setLastRecognizedState(State state) {
        lastRecognized = state;
    }

    @Override
    public State getLastProcessedState() {
        return lastProcessed;
    }

    @Override
    public void setLastProcessedState(State state) {
        lastProcessed = state;
    }

    public void addOperand(BigDecimal operand) {
        operandStack.addLast(operand);
    }

    public Deque<BigDecimal> getOperandStack() {
        return operandStack;
    }

    public void addOperation(Operation operation) {
        operationStack.add(operation);
    }

    public Deque<Operation> getOperationStack() {
        return operationStack;
    }

    public Operation getLastOperation() {
        return operationStack.peek();
    }

    public void store() {
        operandStackStorage.addLast(new ArrayDeque<BigDecimal>(operandStack));
        operandStack.clear();
        operationStackStorage.addLast(new ArrayDeque<Operation>(operationStack));
        operationStack.clear();
        parenthesisFlagStorage.addLast(parenthesisFlag);
        parenthesisFlag = false;
        startPositionStorage.addLast(startPosition);
        startPosition = position;
    }

    public void restore() {
        operandStack.clear();
        operandStack.addAll(operandStackStorage.removeLast());
        operationStack.clear();
        operationStack.addAll(operationStackStorage.removeLast());
        parenthesisFlag = parenthesisFlagStorage.removeLast();
        startPosition = startPositionStorage.removeLast();
    }

    public boolean hasStoredStacks() {
        return !(operandStackStorage.isEmpty() && operationStackStorage.isEmpty());
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

}

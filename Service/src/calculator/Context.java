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

    private final Deque<Deque> operandStackStorage = new ArrayDeque<Deque>();
    private final Deque<Deque> operationStackStorage = new ArrayDeque<Deque>();

    public Context(String expression) {
        this(expression, MathContext.DECIMAL64);
    }

    public Context(String expression, MathContext mathContext) {
        this.expression = expression;
        this.mathContext = mathContext;
        this.states = new ArrayList<State>();
        setPosition(0);
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

    public void narrow() {
        BigDecimal operand = operandStack.removeLast();
        storeAndClearContextStacks();
        addOperand(operand);
    }

    public void wide() {
        BigDecimal operand = operandStack.removeLast();
        clearAndRestoreContextStacks();
        addOperand(operand);
    }

    public void storeAndClearContextStacks() {
        operandStackStorage.addLast(new ArrayDeque(operandStack));
        operationStackStorage.addLast(new ArrayDeque(operationStack));
        operandStack.clear();
        operationStack.clear();
    }

    public void clearAndRestoreContextStacks() {
        operandStack.clear();
        operationStack.clear();
        operandStack.addAll(operandStackStorage.removeLast());
        operationStack.addAll(operationStackStorage.removeLast());
    }

    public boolean hasStoredStacks() {
        return !(operandStackStorage.isEmpty() && operationStackStorage.isEmpty());
    }

}

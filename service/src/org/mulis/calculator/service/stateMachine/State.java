package org.mulis.calculator.service.stateMachine;

import org.mulis.calculator.service.stateMachine.processor.*;

public enum State {

    START(new Recognizer("\\G^"), new StartProcessor()),
    BLANK(new Recognizer("\\G +"), new BlankProcessor()),
    //    NUMBER(new Recognizer("\\G[\\+|\\-]?\\d*\\.?\\d+"), new NumberProcessor()),
    NUMBER(new Recognizer("\\G[\\+|\\-]?\\d*\\.?\\d+([eE][\\+\\-]?\\d+)?"), new NumberProcessor()),
    OPERATOR(new Recognizer(Operation.getPattern()), new OperationProcessor()),
    FUNCTION(new Recognizer(Function.getPattern()), new FunctionProcessor()),
    FUNCTION_ARGUMENT_SEPARATOR(new Recognizer("\\G\\,"), new FunctionArgumentSeparatorProcessor()),
    PARENTHESIS_LEFT(new Recognizer("\\G\\("), new ParenthesisLeftProcessor()),
    PARENTHESIS_RIGHT(new Recognizer("\\G\\)"), new ParenthesisRightProcessor()),
    FINISH(new Recognizer("\\G$"), new FinishProcessor());

    private final Recognizer recognizer;
    private final AbstractProcessor processor;

    State(Recognizer recognizer, AbstractProcessor processor) {
        this.recognizer = recognizer;
        this.processor = processor;
    }

    public Recognizer getRecognizer() {
        return recognizer;
    }

    public AbstractProcessor getProcessor() {
        return processor;
    }

}

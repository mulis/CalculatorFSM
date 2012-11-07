package stateMachine;

import stateMachine.processor.*;

public enum State {

    START(new Recognizer("\\G^"), new StartProcessor()),
    BLANK(new Recognizer("\\G +"), new BlankProcessor()),
    NUMBER(new Recognizer("\\G[\\+|\\-]?\\d*\\.?\\d+"), new NumberProcessor()),
    OPERATOR(new Recognizer(Operation.getPattern()), new OperatorProcessor()),
    //FUNCTION(new Recognizer(Function.getPattern()), new FunctionProcessor()),
    PARENTHESIS_LEFT(new Recognizer("\\G\\("), new ParenthesisProcessor()),
    PARENTHESIS_RIGHT(new Recognizer("\\G\\)"), new ParenthesisProcessor()),
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

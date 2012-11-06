package stateMachine;

import finiteStateMachine.IStateProcessor;
import finiteStateMachine.IStateRecognizer;

public enum State {

    START(new Recognizer("\\G^"), new Processor() {

    }),
    SPACE(new Recognizer("\\G +"), new Processor()),
    NUMBER(new Recognizer("\\G\\d*\\.?\\d+"), new Processor()),
    OPERATOR(new Recognizer(Operator.getPattern()), new Processor()),
    FINISH(new Recognizer("\\G$"), new Processor());

    private final IStateRecognizer recognizer;
    private final IStateProcessor processor;

    State(IStateRecognizer recognizer, IStateProcessor processor) {
        this.recognizer = recognizer;
        this.processor = processor;
    }

    public IStateRecognizer getRecognizer() {
        return recognizer;
    }

    public IStateProcessor getProcessor() {
        return processor;
    }

}
